package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.template.contracts.CarContract;
import com.template.contracts.TemplateContract;
import com.template.states.CarState;
import com.template.states.TemplateState;
import net.corda.core.flows.*;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.template.contracts.CarContract.CAR_CONTRACT_ID;

@InitiatingFlow
@StartableByRPC
public class  ShipmentFlow extends FlowLogic<SignedTransaction> {

    private String model;
    private Party owner;

    private final ProgressTracker progressTracker = new ProgressTracker();



    public ShipmentFlow(String model, Party owner) {
        this.model = model;
        this.owner = owner;

    }

    @Override
        public ProgressTracker getProgressTracker() {
            return progressTracker;
        }

        @Override
        @Suspendable
        public SignedTransaction call() throws FlowException {
            //initiator flow logic goes here.

            if(getOurIdentity().getName().getOrganisation().equals("Tesla")){
                System.out.println("This is not Tesla");
            } else{
                throw new FlowException("This is not Tesla");
            }

            Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

            CarState outputstate = new CarState(model, owner, getOurIdentity());
            TransactionBuilder txBuilder = new TransactionBuilder(notary)
                .addOutputState(outputstate, CAR_CONTRACT_ID)
                    .addCommand(new CarContract.shipment(), getOurIdentity().getOwningKey());

            SignedTransaction shipmentTx = getServiceHub().signInitialTransaction(txBuilder);

            FlowSession otherPartySession = initiateFlow(owner);


            return subFlow(new FinalityFlow(shipmentTx,otherPartySession));
        }


}

