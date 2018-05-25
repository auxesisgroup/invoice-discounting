package com.auxesisgroup.airpay.entity;

import java.math.BigInteger;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class loanDetails {

	@NotBlank(message = "The loadId field is required.")
	private String loanId;

	@Digits(integer = 10, fraction = 2, message = "Invalid discountPercentage.")
	private BigInteger discountPercentage;

	@NotBlank(message = "The privateKey field is required.")
	private String privateKey;

	@NotBlank(message = "The contractAddress field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{40}$", message = "Invalid vendorAddress (must match \"^0x[a-fA-F0-9]{40}$\").")
	private String contractAddress;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loadId) {
		this.loanId = loadId;
	}

	public BigInteger getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigInteger discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	@Override
	public String toString() {
		return "loanDetails [loadId=" + loanId + ", discountPercentage=" + discountPercentage + ", privateKey="
				+ privateKey + ", contractAddress=" + contractAddress + "]";
	}

}
