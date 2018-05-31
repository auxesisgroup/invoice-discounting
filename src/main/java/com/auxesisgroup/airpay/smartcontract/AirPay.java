package com.auxesisgroup.airpay.smartcontract;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.0.2.
 */
public final class AirPay extends Contract {
    private static final String BINARY = "606060405234156200001057600080fd5b6040516200144038038062001440833981016040528080518201919060200180518201919060200180518201919060200180519150600090508480516200005c929160200190620000d1565b5060018054600160a060020a03191633600160a060020a031617905560028380516200008d929160200190620000d1565b506003828051620000a3929160200190620000d1565b5060048054600160a060020a031916600160a060020a03929092169190911790555050426005555062000143565b82805482825590600052602060002090810192821562000111579160200282015b82811115620001115782518255602090920191600190910190620000f2565b506200011f92915062000123565b5090565b6200014091905b808211156200011f57600081556001016200012a565b90565b6112ed80620001536000396000f3006060604052600436106100e55763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166319b1696581146100ea578063200d2ed21461010f57806336663154146101365780634811b1411461014c578063549d0d371461017b5780636c0e86ed1461028c5780637c2fce1d1461031d57806384ef449b146103d75780639df8ed43146104665780639dfc911714610479578063a3b9e39d14610595578063b36e00861461060a578063d8a5cc0314610620578063db23d4f8146106d3578063df9d8420146107fa578063f59fba0b1461084b575b600080fd5b34156100f557600080fd5b6100fd610861565b60405190815260200160405180910390f35b341561011a57600080fd5b610122610867565b604051901515815260200160405180910390f35b341561014157600080fd5b6100fd600435610888565b341561015757600080fd5b61015f6108a7565b604051600160a060020a03909116815260200160405180910390f35b341561018657600080fd5b610122600460248135818101908301358060208181020160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284375094965050933593506108b692505050565b341561029757600080fd5b610122600460248135818101908301358060208181020160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843750949650509335935061094b92505050565b341561032857600080fd5b6103306109a0565b604051808060200180602001858152602001848152602001838103835287818151815260200191508051906020019060200280838360005b83811015610380578082015183820152602001610368565b50505050905001838103825286818151815260200191508051906020019060200280838360005b838110156103bf5780820151838201526020016103a7565b50505050905001965050505050505060405180910390f35b34156103e257600080fd5b610122600460248135818101908301358060208181020160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843750949650610ab895505050505050565b341561047157600080fd5b61015f610b40565b341561048457600080fd5b61048c610b4f565b604051600160a060020a0380881660208301528416608082015282151560a082015260c0810182905260e080825281906040820190606083019083018b818151815260200191508051906020019060200280838360005b838110156104fb5780820151838201526020016104e3565b50505050905001848103835289818151815260200191508051906020019060200280838360005b8381101561053a578082015183820152602001610522565b50505050905001848103825288818151815260200191508051906020019060200280838360005b83811015610579578082015183820152602001610561565b505050509050019a505050505050505050505060405180910390f35b34156105a057600080fd5b6105a8610e28565b6040518080602001848152602001838152602001828103825285818151815260200191508051906020019060200280838360005b838110156105f45780820151838201526020016105dc565b5050505090500194505050505060405180910390f35b341561061557600080fd5b6100fd600435610ec0565b341561062b57600080fd5b610633610ece565b604051808481526020018060200180602001838103835285818151815260200191508051906020019060200280838360005b8381101561067d578082015183820152602001610665565b50505050905001838103825284818151815260200191508051906020019060200280838360005b838110156106bc5780820151838201526020016106a4565b505050509050019550505050505060405180910390f35b34156106de57600080fd5b6106e6610fde565b604051808060200180602001806020018060200187815260200186815260200185810385528b818151815260200191508051906020019060200280838360005b8381101561073e578082015183820152602001610726565b5050505090500185810384528a818151815260200191508051906020019060200280838360005b8381101561077d578082015183820152602001610765565b50505050905001858103835289818151815260200191508051906020019060200280838360005b838110156107bc5780820151838201526020016107a4565b505050509050018581038252888181518152602001915080519060200190602002808383600083811015610579578082015183820152602001610561565b341561080557600080fd5b610122600460248135818101908301358060208181020160405190810160405280939291908181526020018383602002808284375094965050933593506111f792505050565b341561085657600080fd5b6100fd600435611237565b60055481565b60045474010000000000000000000000000000000000000000900460ff1681565b600380548290811061089657fe5b600091825260209091200154905081565b600454600160a060020a031681565b60045460009033600160a060020a039081169116146108d457600080fd5b600854828082146108e457600080fd5b600a8880516108f7929160200190611245565b50600b87805161090b929160200190611245565b50600c849055600d868051610924929160200190611245565b50600e858051610938929160200190611245565b505042600f555060019695505050505050565b60015460009033600160a060020a0390811691161461096957600080fd5b600684805161097c929160200190611245565b506007838051610990929160200190611245565b5050600855505042600955600190565b6109a8611292565b6109b0611292565b6000806000806109be611292565b6109c6611292565b6006549350836040518059106109d95750595b90808252806020026020018201604052509150600092505b83831015610a34576006805484908110610a0757fe5b906000526020600020900154828481518110610a1f57fe5b602090810290910101526001909201916109f1565b600754935083604051805910610a475750595b90808252806020026020018201604052509050600092505b83831015610aa2576007805484908110610a7557fe5b906000526020600020900154818481518110610a8d57fe5b60209081029091010152600190920191610a5f565b6008546009549299919850965090945092505050565b60015460009033600160a060020a03908116911614610ad657600080fd5b6013838051610ae9929160200190611245565b506014828051610afd929160200190611245565b5050426015556004805474ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000179055600192915050565b600154600160a060020a031681565b610b57611292565b6000610b61611292565b610b69611292565b6000806000806000610b79611292565b610b81611292565b610b89611292565b600054945084604051805910610b9c5750595b90808252806020026020018201604052509250600093505b84841015610bf7576000805485908110610bca57fe5b906000526020600020900154838581518110610be257fe5b60209081029091010152600190930192610bb4565b600254945084604051805910610c0a5750595b90808252806020026020018201604052509150600093505b84841015610c65576002805485908110610c3857fe5b906000526020600020900154828581518110610c5057fe5b60209081029091010152600190930192610c22565b600354945084604051805910610c785750595b90808252806020026020018201604052509050600093505b84841015610cd3576003805485908110610ca657fe5b906000526020600020900154818581518110610cbe57fe5b60209081029091010152600190930192610c90565b600154600454600554600080549093600160a060020a0390811693600293600393928216927401000000000000000000000000000000000000000090920460ff16918790602080820201604051908101604052809291908181526020018280548015610d5f57602002820191906000526020600020905b81548152600190910190602001808311610d4a575b5050505050965084805480602002602001604051908101604052809291908181526020018280548015610db257602002820191906000526020600020905b81548152600190910190602001808311610d9d575b5050505050945083805480602002602001604051908101604052809291908181526020018280548015610e0557602002820191906000526020600020905b81548152600190910190602001808311610df0575b505050505093509b509b509b509b509b509b509b50505050505090919293949596565b610e30611292565b600080600080610e3e611292565b601054925082604051805910610e515750595b90808252806020026020018201604052509050600091505b82821015610eac576010805483908110610e7f57fe5b906000526020600020900154818381518110610e9757fe5b60209081029091010152600190910190610e69565b601154601254919650945092505050909192565b600280548290811061089657fe5b6000610ed8611292565b610ee0611292565b600080610eeb611292565b610ef3611292565b601354925082604051805910610f065750595b90808252806020026020018201604052509150600093505b82841015610f61576013805485908110610f3457fe5b906000526020600020900154828581518110610f4c57fe5b60209081029091010152600190930192610f1e565b601454925082604051805910610f745750595b90808252806020026020018201604052509050600093505b82841015610fcf576014805485908110610fa257fe5b906000526020600020900154818581518110610fba57fe5b60209081029091010152600190930192610f8c565b60155497919650945092505050565b610fe6611292565b610fee611292565b610ff6611292565b610ffe611292565b60008060008061100c611292565b611014611292565b61101c611292565b611024611292565b600d549550856040518059106110375750595b90808252806020026020018201604052509350600094505b8585101561109257600d80548690811061106557fe5b90600052602060002090015484868151811061107d57fe5b6020908102909101015260019094019361104f565b600e549550856040518059106110a55750595b90808252806020026020018201604052509250600094505b8585101561110057600e8054869081106110d357fe5b9060005260206000209001548386815181106110eb57fe5b602090810290910101526001909401936110bd565b600a549550856040518059106111135750595b90808252806020026020018201604052509150600094505b8585101561116e57600a80548690811061114157fe5b90600052602060002090015482868151811061115957fe5b6020908102909101015260019094019361112b565b600b549550856040518059106111815750595b90808252806020026020018201604052509050600094505b858510156111dc57600b8054869081106111af57fe5b9060005260206000209001548186815181106111c757fe5b60209081029091010152600190940193611199565b600c54600f54949d939c50919a509850965090945092505050565b60015460009033600160a060020a0390811691161461121557600080fd5b6010838051611228929160200190611245565b50506011555042601255600190565b600080548290811061089657fe5b828054828255906000526020600020908101928215611282579160200282015b828111156112825782518255602090920191600190910190611265565b5061128e9291506112a4565b5090565b60206040519081016040526000815290565b6112be91905b8082111561128e57600081556001016112aa565b905600a165627a7a723058209a974e0771198edd8afaf549354365ac081dea052714dbcd157e3a0af2aa69e20029";

    private AirPay(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private AirPay(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<PoDetailsConfirmEventResponse> getPoDetailsConfirmEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("poDetailsConfirm", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<PoDetailsConfirmEventResponse> responses = new ArrayList<PoDetailsConfirmEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            PoDetailsConfirmEventResponse typedResponse = new PoDetailsConfirmEventResponse();
            typedResponse.poId = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.poHash = (List<byte[]>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PoDetailsConfirmEventResponse> poDetailsConfirmEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("poDetailsConfirm", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, PoDetailsConfirmEventResponse>() {
            @Override
            public PoDetailsConfirmEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                PoDetailsConfirmEventResponse typedResponse = new PoDetailsConfirmEventResponse();
                typedResponse.poId = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.poHash = (List<byte[]>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<IncoiceAndChallanDetailsConfirmEventResponse> getIncoiceAndChallanDetailsConfirmEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("incoiceAndChallanDetailsConfirm", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<IncoiceAndChallanDetailsConfirmEventResponse> responses = new ArrayList<IncoiceAndChallanDetailsConfirmEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            IncoiceAndChallanDetailsConfirmEventResponse typedResponse = new IncoiceAndChallanDetailsConfirmEventResponse();
            typedResponse.invoiceId = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.challanId = (List<byte[]>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<IncoiceAndChallanDetailsConfirmEventResponse> incoiceAndChallanDetailsConfirmEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("incoiceAndChallanDetailsConfirm", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, IncoiceAndChallanDetailsConfirmEventResponse>() {
            @Override
            public IncoiceAndChallanDetailsConfirmEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                IncoiceAndChallanDetailsConfirmEventResponse typedResponse = new IncoiceAndChallanDetailsConfirmEventResponse();
                typedResponse.invoiceId = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.challanId = (List<byte[]>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<BigInteger> contractCreaatedOn() {
        Function function = new Function("contractCreaatedOn", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> status() {
        Function function = new Function("status", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<byte[]> vendorId(BigInteger param0) {
        Function function = new Function("vendorId", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> vendorAddress() {
        Function function = new Function("vendorAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setInvoiceDetail(List<byte[]> _invoiceId, List<byte[]> _invoiceHash, List<byte[]> _challanId, List<byte[]> _challanHash, BigInteger invoiceAmount) {
        Function function = new Function(
                "setInvoiceDetail", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_invoiceId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_invoiceHash, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_challanId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_challanHash, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(invoiceAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setPoDetail(List<byte[]> poId, List<byte[]> poHash, BigInteger poAmount) {
        Function function = new Function(
                "setPoDetail", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(poId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(poHash, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(poAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>> getPoDetails() {
        final Function function = new Function("getPoDetails", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>>(
                new Callable<Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple4<List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>(
                                (List<Bytes32>) results.get(0).getValue(), 
                                (List<Bytes32>) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setPaymentDetails(List<byte[]> _transactionId, List<byte[]> _transactionMode) {
        Function function = new Function(
                "setPaymentDetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_transactionId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_transactionMode, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> merchantAddress() {
        Function function = new Function("merchantAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger>> getContractDetails() {
        final Function function = new Function("getContractDetails", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger>>(
                new Callable<Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger>>() {
                    @Override
                    public Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<List<Bytes32>, String, List<Bytes32>, List<Bytes32>, String, Boolean, BigInteger>(
                                (List<Bytes32>) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (List<Bytes32>) results.get(2).getValue(), 
                                (List<Bytes32>) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<Tuple3<List<Bytes32>, BigInteger, BigInteger>> getLoanDetails() {
        final Function function = new Function("getLoanDetails", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<List<Bytes32>, BigInteger, BigInteger>>(
                new Callable<Tuple3<List<Bytes32>, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<List<Bytes32>, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<List<Bytes32>, BigInteger, BigInteger>(
                                (List<Bytes32>) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<byte[]> quotationHash(BigInteger param0) {
        Function function = new Function("quotationHash", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple3<BigInteger, List<Bytes32>, List<Bytes32>>> getTransactionDetails() {
        final Function function = new Function("getTransactionDetails", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteCall<Tuple3<BigInteger, List<Bytes32>, List<Bytes32>>>(
                new Callable<Tuple3<BigInteger, List<Bytes32>, List<Bytes32>>>() {
                    @Override
                    public Tuple3<BigInteger, List<Bytes32>, List<Bytes32>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<BigInteger, List<Bytes32>, List<Bytes32>>(
                                (BigInteger) results.get(0).getValue(), 
                                (List<Bytes32>) results.get(1).getValue(), 
                                (List<Bytes32>) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple6<List<Bytes32>, List<Bytes32>, List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>> getInvoiceDetails() {
        final Function function = new Function("getInvoiceDetails", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<List<Bytes32>, List<Bytes32>, List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>>(
                new Callable<Tuple6<List<Bytes32>, List<Bytes32>, List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<List<Bytes32>, List<Bytes32>, List<Bytes32>, List<Bytes32>, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<List<Bytes32>, List<Bytes32>, List<Bytes32>, List<Bytes32>, BigInteger, BigInteger>(
                                (List<Bytes32>) results.get(0).getValue(), 
                                (List<Bytes32>) results.get(1).getValue(), 
                                (List<Bytes32>) results.get(2).getValue(), 
                                (List<Bytes32>) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setLoanDetails(List<byte[]> _loadId, BigInteger _discountPercentage) {
        Function function = new Function(
                "setLoanDetails", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_loadId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(_discountPercentage)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> merchantId(BigInteger param0) {
        Function function = new Function("merchantId", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public static RemoteCall<AirPay> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<byte[]> _merchantId, List<byte[]> _quotationHash, List<byte[]> _vendorId, String _vendorAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_merchantId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_quotationHash, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_vendorId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.Address(_vendorAddress)));
        return deployRemoteCall(AirPay.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<AirPay> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<byte[]> _merchantId, List<byte[]> _quotationHash, List<byte[]> _vendorId, String _vendorAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_merchantId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_quotationHash, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_vendorId, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.Address(_vendorAddress)));
        return deployRemoteCall(AirPay.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static AirPay load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AirPay(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static AirPay load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AirPay(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class PoDetailsConfirmEventResponse {
        public List<byte[]> poId;

        public List<byte[]> poHash;
    }

    public static class IncoiceAndChallanDetailsConfirmEventResponse {
        public List<byte[]> invoiceId;

        public List<byte[]> challanId;
    }
}