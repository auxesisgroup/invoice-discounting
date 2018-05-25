package com.auxesisgroup.airpay.entity;

import java.math.BigInteger;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class InvoiceAndChallan {

	@NotBlank(message = "The invoiceId field is required.")
	private String invoiceId;

	@NotBlank(message = "The invoiceHash field is required.")
	private String invoiceHash;

	// @NotBlank(message = "The challanId field is required.")
	private String challanId;

	// @NotBlank(message = "The challanHash field is required.")
	private String challanHash;

	//@NotBlank(message = "The challanAmount field is required.")
	private BigInteger challanAmount;

	@NotBlank(message = "The poId field is required.")
	private String poId;

	public BigInteger getChallanAmount() {
		return challanAmount;
	}

	public void setChallanAmount(BigInteger challanAmount) {
		this.challanAmount = challanAmount;
	}

	@NotBlank(message = "The contractAddress field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{40}$", message = "Invalid vendorAddress (must match \"^0x[a-fA-F0-9]{40}$\").")
	private String contractAddress;

	@NotBlank(message = "The privateKey field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{64}$", message = "Invalid privateKey (must match \"^0x[a-fA-F0-9]{64}$\").")
	private String privateKey;

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
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

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String toString() {
		return "InvoiceAndChallan [invoiceId=" + invoiceId + ", invoiceHash=" + invoiceHash + ", challanId=" + challanId
				+ ", challanHash=" + challanHash + ", challanAmount=" + challanAmount + ", poId=" + poId
				+ ", contractAddress=" + contractAddress + ", privateKey=" + privateKey + "]";
	}

}
