package com.auxesisgroup.airpay.blockchain;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

public class Balance {

	static Web3j WEB3JS = Web3j.build(new HttpService("http://139.59.213.205:7007"));
	final static Logger logger = LoggerFactory.getLogger(Balance.class);

	public EthGetBalance getAccountBalance(String accountAddress) throws Exception {
		logger.info("accountAddress :", accountAddress);
		EthGetBalance balance = WEB3JS.ethGetBalance(accountAddress, DefaultBlockParameterName.LATEST).sendAsync()
				.get();
		return balance;
	}

	public boolean transactionFeeCalculation(String accountAddress, BigInteger _garPrice, BigInteger _garLimit,
			String privateKey) {
		try {
			Credentials resAccount = Credentials.create(accountAddress.trim());
			String userAddress = resAccount.getAddress();
			BigInteger balance = getAccountBalance(userAddress).getBalance();
			BigInteger fees = _garPrice.multiply(_garLimit);
			logger.info("fess  : " + fees + "\n balance :" + balance);
			logger.info("Balance check : :" + (balance.intValue() - fees.intValue()) + "  " + balance.compareTo(fees));
			if (balance.compareTo(fees) == -1) {
				System.out.println("fund transfer to the address : " + userAddress);
				Credentials creds = Credentials.create(privateKey);

				System.out.print("creds" + creds.getAddress());
				EthersToAccount sendEthers = new EthersToAccount();
				sendEthers.sendEther(creds, userAddress);
			}
			return true;
		} catch (Exception ex) {
			logger.error("Exception :" + ex);
			return false;
		}
	}

}
