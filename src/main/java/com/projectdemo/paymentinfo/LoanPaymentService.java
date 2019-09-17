package com.projectdemo.paymentinfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class consists of the calculations required for Annuity payment
 * 
 **/
public class LoanPaymentService {

	private final double monthlyInterestDivisor = 12d * 100d;
	private double monthlyInterest = 0d;
	private long monthlyPaymentAmount = 0; //

	/**
	 * This method performs all the required calculations which is required for
	 * annuity payments information
	 * 
	 **/
	public List<PaymentInformationVO> getPaymentInformation(LoanInformationDO requestdata)
			throws IllegalArgumentException {

		List<Double> monthlyPaymentAmountList = new ArrayList<Double>();
		List<Double> currentMonthlyInterestList = new ArrayList<Double>();
		List<Double> currentBalanceList = new ArrayList<Double>();
		List<Double> totalAmountPaidList = new ArrayList<Double>();
		List<Double> totalInterestPaidList = new ArrayList<Double>();
		List<PaymentInformationVO> informationVOs = new ArrayList();

		monthlyPaymentAmount = calculateMonthlyPayment(requestdata);

		long balance = requestdata.getPrincipal();
		int paymentNumber = 0;
		long totalPayments = 0;
		long totalInterestPaid = 0;

		monthlyPaymentAmountList.add(0d);
		currentMonthlyInterestList.add(0d);
		currentBalanceList.add(requestdata.getPrincipal() / 100d);
		totalAmountPaidList.add(totalPayments / 100d);
		totalInterestPaidList.add(totalInterestPaid / 100d);
		final int maxNumberOfPayments = requestdata.getLoanTerm();

		while ((balance > 0) && (paymentNumber < maxNumberOfPayments)) {
			PaymentInformationVO paymentInfoVO = new PaymentInformationVO();
			paymentInfoVO.setBorrowerPaymentAmount(String.valueOf(monthlyPaymentAmount));
			paymentNumber++;
			long curMonthlyInterest = Math.round(((double) balance) * monthlyInterest);

			long curPayoffAmount = balance + curMonthlyInterest;

			long curMonthlyPaymentAmount = Math.min(monthlyPaymentAmount, curPayoffAmount);

			if ((paymentNumber == maxNumberOfPayments)
					&& ((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest))) {
				curMonthlyPaymentAmount = curPayoffAmount;
			}

			long curMonthlyPrincipalPaid = curMonthlyPaymentAmount - curMonthlyInterest;

			long curBalance = balance - curMonthlyPrincipalPaid;

			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;

			monthlyPaymentAmountList.add(curMonthlyPaymentAmount / 100d);
			currentMonthlyInterestList.add(curMonthlyInterest / 100d);
			currentBalanceList.add(curBalance / 100d);
			totalAmountPaidList.add(totalPayments / 100d);
			totalInterestPaidList.add(totalInterestPaid / 100d);
			balance = curBalance;

			paymentInfoVO.setInterest(String.valueOf(curMonthlyInterest / 100d));
			paymentInfoVO.setInitialOutstandingPrincipal(String.valueOf(curBalance / 100d));
			paymentInfoVO.setPrincipal(String.valueOf(curMonthlyPrincipalPaid / 100d));
			paymentInfoVO.setDate(String.valueOf(requestdata.getStartDate().plusMonths(paymentNumber)));
			paymentInfoVO.setRemainingOutstandingPrincipal(String.valueOf(curBalance / 100d));
			informationVOs.add(paymentInfoVO);
		}

		return informationVOs;
	}

	/**
	 * This method is used for passing data objects where ever required
	 * 
	 **/
	public LoanInformationDO processInputData(Map<String, Object> payload) {
		Long loanAmount = Long.valueOf(payload.get("loanAmount").toString());
		Long nominalRate = Long.valueOf(payload.get("nominalRate").toString());
		Integer duration = Integer.valueOf(payload.get("duration").toString());
		String date = payload.get("startDate").toString();
		LoanInformationDO informationDo = new LoanInformationDO();
		informationDo.setPrincipal(Math.round(loanAmount * 100));
		informationDo.setAnnualInterestRate(nominalRate);
		informationDo.setLoanTerm(duration * 12);
		informationDo.setStartDate(processDate(date));
		return informationDo;
	}

	/**
	 * This method helps in parsing the date format
	 * 
	 **/
	private LocalDate processDate(String date) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		return LocalDate.parse("2018-04-10T04:00:00.000Z", inputFormatter);
	}

	/**
	 * This method comprises of calculations for monthly payments based on interest
	 * rate
	 * 
	 **/

	public long calculateMonthlyPayment(LoanInformationDO loanInformationDO) throws IllegalArgumentException {

		monthlyInterest = loanInformationDO.getAnnualInterestRate() / monthlyInterestDivisor;
		double tmp = Math.pow(1d + monthlyInterest, -1);
		tmp = Math.pow(tmp, loanInformationDO.getLoanTerm());
		tmp = Math.pow(1d - tmp, -1);
		double rc = loanInformationDO.getPrincipal() * monthlyInterest * tmp;
		if (Math.round(rc) > loanInformationDO.getPrincipal()) {
			throw new IllegalArgumentException();
		}
		return Math.round(rc);
	}

}
