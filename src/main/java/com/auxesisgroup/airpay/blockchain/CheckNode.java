package com.auxesisgroup.airpay.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import com.auxesisgroup.airpay.entity.Response;

@Service
public class CheckNode {
	final static Logger LOGGER = LoggerFactory.getLogger(CheckNode.class);
	static Web3j web3 = Web3j.build(new HttpService());

	public Response getConnection() {
		Response res = new Response();
		try {
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			LOGGER.debug("Connect blockchain server..." + clientVersion);
			res.setMessage("Connect blockchain server..." + clientVersion);
			res.setResult(true);
			return (res);
		} catch (Exception ex) {
			LOGGER.error("Unable to connect blockchain server...");
			LOGGER.error(" Exception", ex);
			res.setMessage("Unable to connect blockchain server...");
			res.setResult(false);
			return (res);
		}
	}

}
