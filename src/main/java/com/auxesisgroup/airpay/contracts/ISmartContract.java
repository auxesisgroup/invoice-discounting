package com.auxesisgroup.airpay.contracts;

import org.springframework.context.annotation.ComponentScan;

import com.auxesisgroup.airpay.entity.InvoiceAndChallan;
import com.auxesisgroup.airpay.entity.MerchantAndVendor;
import com.auxesisgroup.airpay.entity.PurchaseOrder;
import com.auxesisgroup.airpay.entity.Response;
import com.auxesisgroup.airpay.entity.loanDetails;
import com.auxesisgroup.airpay.entity.loanPayment;

@ComponentScan
public interface ISmartContract {
	Response smartContractDeploy(MerchantAndVendor MarchantAndVendor);

	Response setPoDetail(PurchaseOrder PurchaseOrder);

	Response setInvoiceDetail(InvoiceAndChallan InvoiceAndChallan);

	Response getPaymentTransactionDetails(String _contractAddress);

	public Response setLoanDetails(loanDetails loanDetails);

	public Response setLoanPayment(loanPayment loanPayment);
}
