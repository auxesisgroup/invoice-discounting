package com.auxesisgroup.airpay.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class loanPayment {

	@NotBlank(message = "The transactionId field is required.")
	private String transactionId;

	@NotBlank(message = "The transactionMode field is required.")
	private String transactionMode;

	@NotBlank(message = "The privateKey field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{64}$", message = "Invalid privateKey (must match \"^0x[a-fA-F0-9]{64}$\").")
	private String privateKey;

	@NotBlank(message = "The contractAddress field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{40}$", message = "Invalid vendorAddress (must match \"^0x[a-fA-F0-9]{40}$\").")
	private String contractAddress;

	public String getTransactionId() {
		return transactionId;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
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

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String toString() {
		return "loanPayment [transactionId=" + transactionId + ", transactionMode=" + transactionMode + ", privateKey="
				+ privateKey + ", contractAddress=" + contractAddress + "]";
	}

}
