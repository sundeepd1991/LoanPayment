package com.projectdemo.paymentinfo;

import java.io.Serializable;

/**
 * This class comprises the fields needed to display the Loan Schedule for a
 * Loan. This response object will be sent to any client when they will make a
 * call to Loan API.
 * 
 **/
public class PaymentInformationVO implements Serializable {

	private static final long serialVersionUID = -6832055544079452151L;

	private String borrowerPaymentAmount;
	private String date;
	private String initialOutstandingPrincipal;
	private String interest;
	private String principal;
	private String remainingOutstandingPrincipal;

	public String getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}

	public void setBorrowerPaymentAmount(String borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}

	public String getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}

	public void setInitialOutstandingPrincipal(String initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}

	public void setRemainingOutstandingPrincipal(String remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}