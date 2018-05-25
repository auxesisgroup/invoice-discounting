package com.auxesisgroup.airpay.entity;

import java.math.BigInteger;

public class Payment {
	private String merchantId;
	private String merchantAddress;
	private String quotationHash;
	private String vendorId;
	private String vendorAddress;
	private String poId;
	private String poHash;
	private BigInteger poAmount;
	private String invoiceId;
	private String invoiceHash;
	private BigInteger challanAmount;
	private String challanId;
	private String challanHash;
	private String contractAddress;
	private String transactionId;
	private String transactionMode;
	private String loanId;
	private BigInteger discountPercentage;
	private BigInteger contractCreaatedOn;
	private BigInteger poUpdateTimestamp;
	private BigInteger invoiceAndChallanUpdateTimestamp;
	private BigInteger loanUpdateTimestamp;
	private BigInteger paymentUpdateTimestamp;

	public BigInteger getContractCreaatedOn() {
		return contractCreaatedOn;
	}

	public BigInteger getPoAmount() {
		return poAmount;
	}

	public void setPoAmount(BigInteger poAmount) {
		this.poAmount = poAmount;
	}

	public BigInteger getChallanAmount() {
		return challanAmount;
	}

	public void setChallanAmount(BigInteger challanAmount) {
		this.challanAmount = challanAmount;
	}

	public void setContractCreaatedOn(BigInteger contractCreaatedOn) {
		this.contractCreaatedOn = contractCreaatedOn;
	}

	public BigInteger getPoUpdateTimestamp() {
		return poUpdateTimestamp;
	}

	public void setPoUpdateTimestamp(BigInteger poUpdateTimestamp) {
		this.poUpdateTimestamp = poUpdateTimestamp;
	}

	public BigInteger getInvoiceAndChallanUpdateTimestamp() {
		return invoiceAndChallanUpdateTimestamp;
	}

	public void setInvoiceAndChallanUpdateTimestamp(BigInteger invoiceAndChallanUpdateTimestamp) {
		this.invoiceAndChallanUpdateTimestamp = invoiceAndChallanUpdateTimestamp;
	}

	public BigInteger getLoanUpdateTimestamp() {
		return loanUpdateTimestamp;
	}

	public void setLoanUpdateTimestamp(BigInteger loanUpdateTimestamp) {
		this.loanUpdateTimestamp = loanUpdateTimestamp;
	}

	public BigInteger getPaymentUpdateTimestamp() {
		return paymentUpdateTimestamp;
	}

	public void setPaymentUpdateTimestamp(BigInteger paymentUpdateTimestamp) {
		this.paymentUpdateTimestamp = paymentUpdateTimestamp;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getQuotationHash() {
		return quotationHash;
	}

	public void setQuotationHash(String quotationHash) {
		this.quotationHash = quotationHash;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getPoHash() {
		return poHash;
	}

	public void setPoHash(String poHash) {
		this.poHash = poHash;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceHash() {
		return invoiceHash;
	}

	public void setInvoiceHash(String invoiceHash) {
		this.invoiceHash = invoiceHash;
	}

	public String getChallanId() {
		return challanId;
	}

	public void setChallanId(String challanId) {
		this.challanId = challanId;
	}

	public String getChallanHash() {
		return challanHash;
	}

	public void setChallanHash(String challanHash) {
		this.challanHash = challanHash;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public BigInteger getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigInteger discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public String toString() {
		return "Payment [merchantId=" + merchantId + ", merchantAddress=" + merchantAddress + ", quotationHash="
				+ quotationHash + ", vendorId=" + vendorId + ", vendorAddress=" + vendorAddress + ", poId=" + poId
				+ ", poHash=" + poHash + ", poAmount=" + poAmount + ", invoiceId=" + invoiceId + ", invoiceHash="
				+ invoiceHash + ", challanAmount=" + challanAmount + ", challanId=" + challanId + ", challanHash="
				+ challanHash + ", contractAddress=" + contractAddress + ", transactionId=" + transactionId
				+ ", transactionMode=" + transactionMode + ", loanId=" + loanId + ", discountPercentage="
				+ discountPercentage + ", contractCreaatedOn=" + contractCreaatedOn + ", poUpdateTimestamp="
				+ poUpdateTimestamp + ", invoiceAndChallanUpdateTimestamp=" + invoiceAndChallanUpdateTimestamp
				+ ", loanUpdateTimestamp=" + loanUpdateTimestamp + ", paymentUpdateTimestamp=" + paymentUpdateTimestamp
				+ "]";
	}
}
