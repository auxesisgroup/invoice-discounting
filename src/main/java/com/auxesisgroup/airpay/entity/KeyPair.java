package com.auxesisgroup.airpay.entity;

public class KeyPair {

	private String privateKey;
	private String publicKey;
	private String address;

	public KeyPair(final String privateKey,final String publicKey,final String address) {
		super();
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.address = address;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "KeyPair [privateKey=" + privateKey + ", publicKey=" + publicKey + ", address=" + address + "]";
	}

}
