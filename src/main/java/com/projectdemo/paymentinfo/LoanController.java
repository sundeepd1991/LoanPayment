package com.projectdemo.paymentinfo;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is basically the API which can provide services like post/ get the
 * data basing on the client request for annuity calculation.
 * 
 **/
@RestController
@RequestMapping("/api")
public class LoanController {
	@PostMapping(value = "/generatePlan")
	public List<PaymentInformationVO> loanDetails(@RequestBody Map<String, Object> payload) {

		LoanPaymentService loanPaymentService = new LoanPaymentService();
		LoanInformationDO requestData = loanPaymentService.processInputData(payload);
		List<PaymentInformationVO> paymentInformationVOs = loanPaymentService.getPaymentInformation(requestData);
		return paymentInformationVOs;

	}

}
