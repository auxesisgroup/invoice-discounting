
package com.auxesisgroup.airpay.contracts;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;

import com.auxesisgroup.airpay.blockchain.AddressHelper;
import com.auxesisgroup.airpay.blockchain.Balance;
import com.auxesisgroup.airpay.blockchain.KeyPair;
import com.auxesisgroup.airpay.entity.InvoiceAndChallan;
import com.auxesisgroup.airpay.entity.MerchantAndVendor;
import com.auxesisgroup.airpay.entity.Payment;
import com.auxesisgroup.airpay.entity.PurchaseOrder;
import com.auxesisgroup.airpay.entity.Response;
import com.auxesisgroup.airpay.entity.contractAndTxHash;
import com.auxesisgroup.airpay.entity.loanDetails;
import com.auxesisgroup.airpay.entity.loanPayment;
import com.auxesisgroup.airpay.smartcontract.AirPay;

@Service
@Configuration
@PropertySource("classpath:blockchain.properties")
public class SmartContractImpl implements ISmartContract {

	// @Value("${web3j.url}")
	private String webUrl = "http://172.31.39.115:30303";
	// @Value("${web3j.masterKey}")
	// private String masterKey;

	// BlochchainProperties _BlochchainProperties = new BlochchainProperties();
	// variable
	final static Logger LOGGER = LoggerFactory.getLogger(SmartContractImpl.class);

	private Web3j WEB3J = Web3j.build(new HttpService(webUrl));
	
	private String senderPrivKey = "0xf989fac1c1c1b1d1e66d1e0873e129626ad18ddc329a578d25fde961c56c24bf";
	// f989fac1c1c1b1d1e66d1e0873e129626ad18ddc329a578d25fde961c56c24bf
	// Addrress : 0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB
	private String gasPrice = "99999999999";
	private BigInteger _gasPrice = new BigInteger(gasPrice);
	BigInteger _gasLimit = BigInteger.valueOf(3999756);

	// class
	private KeyPair keyPair = new KeyPair();
	private Balance balance = new Balance();
	private AddressHelper addressHelper = new AddressHelper();

	@Override
	public Response smartContractDeploy(MerchantAndVendor MarchantAndVendor) {
		Response res = new Response();
		try {
			
			boolean feesCheck = balance.transactionFeeCalculation(MarchantAndVendor.getPrivateKey(), _gasPrice,
					_gasLimit, senderPrivKey);
			LOGGER.info("feesCheck : " + feesCheck);
			if (!feesCheck) {
				LOGGER.error("smartContractDeploy  : Insuffient funds.");
				res.setMessage("Insuffient funds.");
				return res;
			}
			res = keyPair.getKeyFromPrivateKey(MarchantAndVendor.getPrivateKey());
			if (res.getMessage() != null) {
				LOGGER.error("smartContractDeploy  :  " + res.getMessage());
				return res;
			}
			Credentials creds = (Credentials) res.getResult();

			List<byte[]> merchantId = addressHelper.stringToBytes32Array(MarchantAndVendor.getMerchantId());
			List<byte[]> vendorId = addressHelper.stringToBytes32Array(MarchantAndVendor.getVendorId());
			List<byte[]> quotationHash = addressHelper.stringToBytes32Array(MarchantAndVendor.getQuotationHash());
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			AirPay contract = AirPay.deploy(WEB3J, creds, _gasPrice, _gasLimit, merchantId, quotationHash, vendorId,
					MarchantAndVendor.getVendorAddress()).sendAsync().get();
			if (!contract.isValid()) {
				LOGGER.error("invalid contract in  :" + contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(contract.getContractAddress());
			result.setTransactionHash(contract.getTransactionReceipt().get().getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	@Override
	public Response setPoDetail(PurchaseOrder PurchaseOrder) {
		Response res = new Response();
		try {
			boolean feesCheck = balance.transactionFeeCalculation(PurchaseOrder.getPrivateKey(), _gasPrice, _gasLimit,
					senderPrivKey);
			LOGGER.info("feesCheck: " + feesCheck);
			if (!feesCheck) {
				LOGGER.error("setPoDetail  : Insuffient funds. ");
				res.setMessage("Insuffient funds.");
				return res;
			}
			res = keyPair.getKeyFromPrivateKey(PurchaseOrder.getPrivateKey());
			if (res.getMessage() != null) {
				LOGGER.error("setPoDetail  :  " + res.getMessage());
				return res;
			}
			Credentials creds = (Credentials) res.getResult();
			List<byte[]> poId = addressHelper.stringToBytes32Array(PurchaseOrder.getPoId());
			List<byte[]> poHash = addressHelper.stringToBytes32Array(PurchaseOrder.getPoHash());
			AirPay contract = AirPay.load(PurchaseOrder.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);

			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger> transact1 = contract
					.getContractDetails().sendAsync().get();

			if (!creds.getAddress().contentEquals(transact1.getValue2())) {
				LOGGER.error("Invalid merchant private key :  " + creds.getAddress() + " is shuod be : "
						+ transact1.getValue2());
				res.setMessage("Invalid private key.");
				return res;
			}

			TransactionReceipt transact = contract.setPoDetail(poId, poHash, PurchaseOrder.getPoAmount()).sendAsync()
					.get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(contract.getContractAddress());
			result.setTransactionHash(transact.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			return res;
		}
	}

	@Override
	public Response setInvoiceDetail(InvoiceAndChallan InvoiceAndChallan) {
		Response res = new Response();
		try {

			Credentials credsMaster = Credentials.create(senderPrivKey);
			AirPay _contract = AirPay.load(InvoiceAndChallan.getContractAddress(), WEB3J, credsMaster, _gasPrice,
					_gasLimit);
			if (!_contract.isValid()) {
				res.setMessage("Invalid contract");
				LOGGER.error("invalid contract :" + InvoiceAndChallan.getContractAddress());
				return res;
			}

			Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger> poDetails = _contract.getPoDetails()
					.sendAsync().get();

			LOGGER.info("poAmount check : " + poDetails.getValue3().equals(InvoiceAndChallan.getChallanAmount()));
			LOGGER.info("poId Check : "
					+ addressHelper.bytes32ArrayToString(poDetails.getValue1()).equals(InvoiceAndChallan.getPoId()));

			if (!poDetails.getValue3().equals(InvoiceAndChallan.getChallanAmount())
					|| !addressHelper.bytes32ArrayToString(poDetails.getValue1()).equals(InvoiceAndChallan.getPoId())) {

				LOGGER.error("poAmount and challanAmount not same. " + poDetails.getValue3() + " != "
						+ InvoiceAndChallan.getChallanAmount() + " OR "
						+ addressHelper.bytes32ArrayToString(poDetails.getValue1()) + "!="
						+ InvoiceAndChallan.getPoId());

				res.setMessage("Invalid challan details.");
				return res;
			}

			boolean feesCheck = balance.transactionFeeCalculation(InvoiceAndChallan.getPrivateKey(), _gasPrice,
					_gasLimit, senderPrivKey);
			LOGGER.info("feesCheck : " + feesCheck);
			if (!feesCheck) {
				LOGGER.error("setInvoiceDetail  : Insuffient funds. ");
				res.setMessage("Insuffient funds.");
				return res;
			}
			res = keyPair.getKeyFromPrivateKey(InvoiceAndChallan.getPrivateKey());
			if (res.getMessage() != null) {
				LOGGER.error("setInvoiceDetail  :  " + res.getMessage());
				return res;
			}
			Credentials creds = (Credentials) res.getResult();
			List<byte[]> invoiceId = addressHelper.stringToBytes32Array(InvoiceAndChallan.getInvoiceId());
			List<byte[]> invoiceHash = addressHelper.stringToBytes32Array(InvoiceAndChallan.getInvoiceHash());
			List<byte[]> challanId = (InvoiceAndChallan.getChallanId() != null)
					? addressHelper.stringToBytes32Array(InvoiceAndChallan.getChallanId())
					: addressHelper.stringToBytes32Array("null");
			List<byte[]> challanHash = (InvoiceAndChallan.getChallanHash() != null)
					? addressHelper.stringToBytes32Array(InvoiceAndChallan.getChallanHash())
					: addressHelper.stringToBytes32Array("null");
			AirPay contract = AirPay.load(InvoiceAndChallan.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}

			Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger> transact1 = contract
					.getContractDetails().sendAsync().get();

			if (!creds.getAddress().contentEquals(transact1.getValue5())) {
				LOGGER.error("Invalid merchant private key :  " + creds.getAddress() + " is shuod be : "
						+ transact1.getValue2());
				res.setMessage("Invalid private key.");
				return res;
			}

			TransactionReceipt transact = contract.setInvoiceDetail(invoiceId, invoiceHash, challanId, challanHash,
					InvoiceAndChallan.getChallanAmount()).sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(contract.getContractAddress());
			result.setTransactionHash(transact.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			LOGGER.error("Exception  in setInvoiceDetail:" + ex);
			res.setMessage("Exception" + ex);
			return res;
		}
	}

	@Override
	public Response setLoanDetails(loanDetails loanDetails) {
		Response res = new Response();
		try {
			boolean feesCheck = balance.transactionFeeCalculation(loanDetails.getPrivateKey(), _gasPrice, _gasLimit,
					senderPrivKey);
			LOGGER.info("feesCheck :: " + feesCheck);
			if (!feesCheck) {
				LOGGER.error("setLoanDetails  : Insuffient funds. ");
				res.setMessage("Insuffient funds.");
				return res;
			}
			res = keyPair.getKeyFromPrivateKey(loanDetails.getPrivateKey());
			if (res.getMessage() != null) {
				LOGGER.error("setLoanDetails  :  " + res.getMessage());
				return res;
			}
			Credentials creds = (Credentials) res.getResult();
			List<byte[]> loadId = addressHelper.stringToBytes32Array(loanDetails.getLoanId());

			AirPay contract = AirPay.load(loanDetails.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}

			Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger> transact1 = contract
					.getContractDetails().sendAsync().get();

			if (!creds.getAddress().contentEquals(transact1.getValue2())) {
				LOGGER.error("Invalid merchant private key :  " + creds.getAddress() + " is shuod be : "
						+ transact1.getValue2());
				res.setMessage("Invalid private key.");
				return res;
			}

			TransactionReceipt transact = contract.setLoanDetails(loadId, loanDetails.getDiscountPercentage())
					.sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(contract.getContractAddress());
			result.setTransactionHash(transact.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			LOGGER.error("Exception  in setLoanDetails: " + ex);
			res.setMessage("Exception" + ex);
			return res;
		}
	}

	@Override
	public Response setLoanPayment(loanPayment loanPayment) {
		Response res = new Response();
		try {
			boolean feesCheck = balance.transactionFeeCalculation(loanPayment.getPrivateKey(), _gasPrice, _gasLimit,
					senderPrivKey);
			LOGGER.info("feesCheck :: " + feesCheck);
			if (!feesCheck) {
				LOGGER.error("setLoanDetails  : Insuffient funds. ");
				res.setMessage("Insuffient funds.");
				return res;
			}
			res = keyPair.getKeyFromPrivateKey(loanPayment.getPrivateKey());
			if (res.getMessage() != null) {
				LOGGER.error("setLoanDetails  :  " + res.getMessage());
				return res;
			}
			Credentials creds = (Credentials) res.getResult();
			List<byte[]> transactionId = addressHelper.stringToBytes32Array(loanPayment.getTransactionId());
			List<byte[]> transactionMode = addressHelper.stringToBytes32Array(loanPayment.getTransactionMode());

			AirPay contract = AirPay.load(loanPayment.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}

			Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger> transact1 = contract
					.getContractDetails().sendAsync().get();

			if (!creds.getAddress().contentEquals(transact1.getValue2())) {
				LOGGER.error("Invalid merchant private key :  " + creds.getAddress() + " is should be : "
						+ transact1.getValue2());
				res.setMessage("Invalid private key.");
				return res;
			}
			TransactionReceipt transact = contract.setPaymentDetails(transactionId, transactionMode).sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(contract.getContractAddress());
			result.setTransactionHash(transact.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			LOGGER.error("Exception  in setLoanDetails: " + ex);
			res.setMessage("Exception" + ex);
			return res;
		}
	}

	@Override
	public Response getPaymentTransactionDetails(String _contractAddress) {
		Response res = new Response();
		try {

			System.out.println("web3j url 1" + webUrl);
			System.out.println("web3j url" + _gasPrice);
			System.out.println("Key " + _gasLimit);
			System.out.println("Key " + senderPrivKey);

			// BigInteger _gasLimit = BigInteger.valueOf(1068756);
			Credentials creds = Credentials.create(senderPrivKey);
			AirPay contract = AirPay.load(_contractAddress, WEB3J, creds, _gasPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				LOGGER.error("invalid contract :" + _contractAddress);
				return res;
			}

			Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger> contractDetails = contract
					.getContractDetails().sendAsync().get();

			Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger> poDetails = contract.getPoDetails().sendAsync()
					.get();

			Tuple6<List<Bytes32>, List<Bytes32>, List<Bytes32>, List<Bytes32>, BigInteger, BigInteger> invoiceAndChallanDetail = contract
					.getInvoiceDetails().sendAsync().get();

			Tuple3<List<Bytes32>, BigInteger, BigInteger> loadDetails = contract.getLoanDetails().sendAsync().get();

			Tuple3<BigInteger, List<Bytes32>, List<Bytes32>> transactionDetails = contract.getTransactionDetails()
					.sendAsync().get();

			Payment Payment = new Payment();
			Payment.setMerchantId(addressHelper.bytes32ArrayToString(contractDetails.getValue1()));
			Payment.setMerchantAddress(contractDetails.getValue2());
			Payment.setQuotationHash(addressHelper.bytes32ArrayToString(contractDetails.getValue3()));
			Payment.setVendorId(addressHelper.bytes32ArrayToString(contractDetails.getValue4()));
			Payment.setVendorAddress((contractDetails.getValue5()));
			Payment.setContractCreaatedOn(contractDetails.getValue7());

			Payment.setPoId(addressHelper.bytes32ArrayToString(poDetails.getValue1()));
			Payment.setPoHash(addressHelper.bytes32ArrayToString(poDetails.getValue2()));
			Payment.setPoAmount(poDetails.getValue3());
			Payment.setPoUpdateTimestamp(poDetails.getValue4());

			Payment.setChallanId(addressHelper.bytes32ArrayToString(invoiceAndChallanDetail.getValue1()));
			Payment.setChallanHash(addressHelper.bytes32ArrayToString(invoiceAndChallanDetail.getValue2()));
			Payment.setInvoiceId(addressHelper.bytes32ArrayToString(invoiceAndChallanDetail.getValue3()));
			Payment.setInvoiceHash(addressHelper.bytes32ArrayToString(invoiceAndChallanDetail.getValue4()));
			Payment.setChallanAmount((invoiceAndChallanDetail.getValue5()));
			Payment.setInvoiceAndChallanUpdateTimestamp(invoiceAndChallanDetail.getValue6());

			Payment.setLoanId(addressHelper.bytes32ArrayToString(loadDetails.getValue1()));
			Payment.setDiscountPercentage(loadDetails.getValue2());
			Payment.setLoanUpdateTimestamp(loadDetails.getValue3());

			Payment.setTransactionId(addressHelper.bytes32ArrayToString(transactionDetails.getValue2()));
			Payment.setTransactionMode(addressHelper.bytes32ArrayToString(transactionDetails.getValue3()));
			Payment.setPaymentUpdateTimestamp((transactionDetails.getValue1()));
			Payment.setContractAddress(_contractAddress);

			res.setResult(Payment);
			return res;
		} catch (Exception ex) {
			LOGGER.error("Exception in getPaymentTransactionDetails : " + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("getCertificateDetails");
			return res;
		}
	}

}
