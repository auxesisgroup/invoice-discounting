package com.auxesisgroup.airpay.config;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:blockchain.properties")
public class BlochchainProperties {

	@Value("${web3j.url}")
	private String webUrl;

	@Value("${web3j.masterKey}")
	private String masterKey;

	@Value("${web3j.gasPrice}")
	private BigInteger BigInteger;

	@Value("${web3j.gasLimit}")
	private String gasLimit;

	public String getWebUrl() {

		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public BigInteger getBigInteger() {
		return BigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		BigInteger = bigInteger;
	}

	public String getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(String gasLimit) {
		this.gasLimit = gasLimit;
	}
}
