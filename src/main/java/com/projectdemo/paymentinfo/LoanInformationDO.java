package com.projectdemo.paymentinfo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class has all the attributes needed to calculate the Annuity schedule
 * for a Loan.
 **/
public class LoanInformationDO implements Serializable {

	private static final long serialVersionUID = 6707074216470724875L;

	private long principal;
	private double annualInterestRate;
	private int loanTerm;
	private LocalDate startDate;

	/**
	 * @return the principal
	 */
	public long getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(long principal) {
		this.principal = principal;
	}

	/**
	 * @return the annualInterestRate
	 */
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	/**
	 * @param annualInterestRate the annualInterestRate to set
	 */
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	/**
	 * @return the loanTerm
	 */
	public int getLoanTerm() {
		return loanTerm;
	}

	/**
	 * @param loanTerm the loanTerm to set
	 */
	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
