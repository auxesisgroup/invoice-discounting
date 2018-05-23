package com.auxesisgroup.airpay.blockchain;

import java.math.BigInteger;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import com.auxesisgroup.airpay.entity.Response;

@Component
public class KeyPair {
	final static Logger LOGGER = LoggerFactory.getLogger(KeyPair.class);

	private String senderPrivKey = "0xf989fac1c1c1b1d1e66d1e0873e129626ad18ddc329a578d25fde961c56c24bf";

	public Response getCredentials() {
		Response res = new Response();
		System.out.println("################################ getCredentials");
		try {
			byte[] reci = Hash.sha3(UUID.randomUUID().toString().getBytes());
			ECKeyPair keys = ECKeyPair.create(reci);
			String address = new Address(Keys.getAddress(keys)).toString();
			BigInteger publicKey = keys.getPublicKey();
			String publicKeyHex = Numeric.toHexStringWithPrefix(publicKey);
			BigInteger privateKey = keys.getPrivateKey();
			String privateKeyHex = Numeric.toHexStringWithPrefix(privateKey);

			Credentials credentials = Credentials.create(privateKeyHex);
			System.out.println("address: '" + address + "'\n" + credentials.getAddress());
			System.out.println("private key address :" + publicKeyHex);
			System.out.println("public key address :" + publicKey);
			LOGGER.info("public key: '" + publicKeyHex + "'");
			LOGGER.info("address: '" + address + "'\n" + credentials.getAddress());
			Credentials credentialOfSender = Credentials.create(senderPrivKey);
			LOGGER.info("Master address :" + credentialOfSender.getAddress());
			// EthersToAccount sendEther = new EthersToAccount();
			// res = sendEther.sendEther(credentialOfSender, address);
			// if (res.getMessage() != null) {
			// res.setResult(null);
			// return res;
			// }
			com.auxesisgroup.airpay.entity.KeyPair keyPairObj = new com.auxesisgroup.airpay.entity.KeyPair(
					privateKeyHex, publicKeyHex, address);
			res.setResult(keyPairObj);
			return res;
		} catch (Exception ex) {
			LOGGER.error("Exception in getCredentials :" + ex);
			res.setMessage("Error in generating key pair  :" + ex);
			res.setResult(null);
			return res;
		}
	}

	public Response getKeyFromPrivateKey(String _privateKey) {
		Response res = new Response();
		try {
			Credentials credentials = Credentials.create(_privateKey.trim());
			LOGGER.debug("address2  : '" + credentials.getAddress() + "'\n");
			LOGGER.debug("Private key 2  : '"
					+ Numeric.toHexStringWithPrefix(credentials.getEcKeyPair().getPrivateKey()) + "'\n");
			LOGGER.debug("public key  : '" + Numeric.toHexStringWithPrefix(credentials.getEcKeyPair().getPublicKey())
					+ "'\n");
			res.setResult(credentials);
		} catch (Exception ex) {
			LOGGER.error("Exception in getKeyFromPrivateKey :" + ex);
			res.setMessage("Error in generating key pair fron private key");
			res.setResult(null);
		}
		return res;
	}

}
