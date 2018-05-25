package com.auxesisgroup.airpay.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class MerchantAndVendor {
	@NotBlank(message = "The merchantId field is required.")
	private String merchantId;

	@NotBlank(message = "The quotationHash field is required.")
	private String quotationHash;

	@NotBlank(message = "The vendorId field is required.")
	private String vendorId;

	@NotBlank(message = "The vendorAddress field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{40}$", message = "Invalid vendorAddress (must match \"^0x[a-fA-F0-9]{40}$\").")
	private String vendorAddress;

	@NotBlank(message = "The privateKey field is required.")
	@Pattern(regexp = "^0x[a-fA-F0-9]{64}$", message = "Invalid privateKey (must match \"^0x[a-fA-F0-9]{64}$\").")
	private String privateKey;

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateAddress) {
		this.privateKey = privateAddress;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	@Override
	public String toString() {
		return "MarchantAndVendor [merchantId=" + merchantId + ", quotationHash=" + quotationHash + ", vendorId="
				+ vendorId + ", vendorAddress=" + vendorAddress + ", privateKey=" + privateKey + "]";
	}

}
