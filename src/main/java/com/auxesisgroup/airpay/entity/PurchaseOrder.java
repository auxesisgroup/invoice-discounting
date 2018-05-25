package com.auxesisgroup.airpay.entity;

import java.math.BigInteger;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class PurchaseOrder {

	@NotBlank(message = "The poId field is required.")
	private String poId;

	@NotBlank(message = "The poHash field is required.")
	private String poHash;

	//@NotBlank(message = "The poAmount field is required.")
	private BigInteger poAmount;

	@NotBlank(message = "The contractAddress field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{40}$", message = "Invalid vendorAddress (must match \"^0x[a-fA-F0-9]{40}$\").")
	private String contractAddress;

	@NotBlank(message = "The privateKey field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{64}$", message = "Invalid privateKey (must match \"^0x[a-fA-F0-9]{64}$\").")
	private String privateKey;

	private String poUpdateTimestamp;

	public BigInteger getPoAmount() {
		return poAmount;
	}

	public void setPoAmount(BigInteger poAmount) {
		this.poAmount = poAmount;
	}

	public String getPoUpdateTimestamp() {
		return poUpdateTimestamp;
	}

	public void setPoUpdateTimestamp(String poUpdateTimestamp) {
		this.poUpdateTimestamp = poUpdateTimestamp;
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
		return "PurchaseOrder [poId=" + poId + ", poHash=" + poHash + ", contractAddress=" + contractAddress
				+ ", privateKey=" + privateKey + ", poUpdateTimestamp=" + poUpdateTimestamp + "]";
	}

}
