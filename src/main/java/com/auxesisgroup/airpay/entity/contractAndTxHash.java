package com.auxesisgroup.airpay.entity;

public class contractAndTxHash {
	private String contractAddress;
	private String transactionHash;

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	@Override
	public String toString() {
		return "contractAndTxHash [transactionHash=" + transactionHash + ", contractAddress=" + contractAddress + "]";
	}
}
