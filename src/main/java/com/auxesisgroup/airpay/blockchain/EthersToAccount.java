package com.auxesisgroup.airpay.blockchain;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;

import com.auxesisgroup.airpay.entity.Response;

public class EthersToAccount {
	final static Logger logger = LoggerFactory.getLogger(EthersToAccount.class);
	private static Web3j WEB3J = Web3j.build(new HttpService("http://139.59.213.205:7007"));

	public Response sendEther(Credentials credentials, String address) {
		Response res = new Response();
		TransactionReceipt transactionReceipt = new TransactionReceipt();
		try {
			logger.info("fund transfer to : " + address + " from " + credentials.getAddress());
			transactionReceipt = Transfer.sendFunds(WEB3J, credentials, address, BigDecimal.valueOf(2), Unit.ETHER)
					.sendAsync().get();
			res.setResult(transactionReceipt);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Exception :" + ex);
			logger.error("Exception :" + ex);
			System.out.println("Exception in sendEther : " + ex);
			return res;
		}
	}
}