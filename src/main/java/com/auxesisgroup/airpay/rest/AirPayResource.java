package com.auxesisgroup.airpay.rest;

import java.util.regex.Pattern;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import com.auxesisgroup.airpay.blockchain.KeyPair;
import com.auxesisgroup.airpay.contracts.ISmartContract;
import com.auxesisgroup.airpay.entity.InvoiceAndChallan;
import com.auxesisgroup.airpay.entity.MerchantAndVendor;
import com.auxesisgroup.airpay.entity.PurchaseOrder;
import com.auxesisgroup.airpay.entity.Response;
import com.auxesisgroup.airpay.entity.loanDetails;
import com.auxesisgroup.airpay.entity.loanPayment;

@RestController
//@RequestMapping("oapi/airpay")
public class AirPayResource {
	final static Logger LOGGER = LoggerFactory.getLogger(AirPayResource.class);
	private String webUrl = "http://172.31.39.115:8545";
	
	
	//private String webUrl = "http://172.31.47.255:8545";
	// @Value("${web3j.masterKey}")
	// private String masterKey;

	// BlochchainProperties _BlochchainProperties = new BlochchainProperties();
	// variable
//	final static Logger LOGGER = LoggerFactory.getLogger(SmartContractImpl.class);

	private Web3j WEB3J = Web3j.build(new HttpService(webUrl));
	
	@Autowired
	private ISmartContract contract;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> init() {
		Response res = new Response();
		try {
		//	Request<?, NetListening> number=WEB3J.;
			
			
			Web3ClientVersion web3ClientVersion = WEB3J.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			LOGGER.info("Started...........");
			res.setResult("Started...........");
			return new ResponseEntity<Object>(clientVersion, HttpStatus.OK);
		} catch (Exception Ex) {
			LOGGER.error("Exception :" + Ex);
			res.setMessage("Exception " + Ex);
			res.setResult(null);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/keypair", method = RequestMethod.GET)
	public ResponseEntity<Object> createCertificate() {
		// System.out.println("getKeyPair");
		Response res = new Response();
		try {
			KeyPair keyPair = new KeyPair();
			res = keyPair.getCredentials();
			if (res.getMessage() != null) {
				res.setResult(null);
				LOGGER.error("unable to generate key");
				res.setMessage("unable to generate key");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in keypair : " + Ex);
			res.setMethod("keypair");
			res.setMessage("Exception : " + Ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/paymentcontract", method = RequestMethod.POST)
	public ResponseEntity<Object> setNewPaymentContract(@Valid @RequestBody MerchantAndVendor MerchantAndVendor,
			BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("paymentcontract input : " + MerchantAndVendor.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			res = contract.smartContractDeploy(MerchantAndVendor);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("paymentcontract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("paymentcontract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("paymentcontract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/podetails", method = RequestMethod.POST)
	public ResponseEntity<Object> setPoDetails(@Valid @RequestBody PurchaseOrder PurchaseOrder,
			BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("podetails input : " + PurchaseOrder.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			// System.out.println("setPoDetails");
			res = contract.setPoDetail(PurchaseOrder);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("podetails");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("podetails");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in podetails :" + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("podetails");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/invoiceandchallan", method = RequestMethod.POST)
	public ResponseEntity<Object> setInvoiceAndChallan(@Valid @RequestBody InvoiceAndChallan InvoiceAndChallan,
			BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("invoiceandchallan input : " + InvoiceAndChallan.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMethod("invoiceandchallan");
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			res = contract.setInvoiceDetail(InvoiceAndChallan);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("invoiceandchallan");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("invoiceandchallan");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in invoiceandchallan :" + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("invoiceandchallan");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/loandetails", method = RequestMethod.POST)
	public ResponseEntity<Object> loanDetail(@Valid @RequestBody loanDetails loanDetails,
			BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("loandetails input : " + loanDetails.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMethod("loandetail");
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			res = contract.setLoanDetails(loanDetails);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("loandetail");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("loandetail");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in invoiceandchallan : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("loandetail");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/paymentapproved", method = RequestMethod.POST)
	public ResponseEntity<Object> loanPaymentDetail(@Valid @RequestBody loanPayment loanPayment,
			BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("paymentapproved input : " + loanPayment.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMethod("loanpaymentdetail");
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			res = contract.setLoanPayment(loanPayment);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("loanpaymentdetail");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("loanpaymentdetail");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in loanpaymentdetail :" + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("loanpaymentdetail");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/transactiondetails/{contractAddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> getTransactionDetails(@PathVariable String contractAddress) {
		Response res = new Response();
		try {
			LOGGER.info("transactiondetails input : " + contractAddress.toString());
			if (!Pattern.matches("^0x[a-fA-F0-9]{40}$", contractAddress)) {
				res.setResult(null);
				res.setMethod("transactiondetails");
				LOGGER.error("Invalid contract Address");
				res.setMessage("Invalid contract Address.");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			// System.out.println(Pattern.matches("^0x[a-fA-F0-9]{40}$", contractAddress));
			res = contract.getPaymentTransactionDetails(contractAddress);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("transactiondetails");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("transactiondetails");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in transactiondetails :" + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("transactiondetails");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}
}
