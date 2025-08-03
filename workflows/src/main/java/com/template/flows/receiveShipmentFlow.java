package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.template.contracts.TemplateContract;
import com.template.states.TemplateState;
import net.corda.core.flows.*;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@InitiatedBy(ShipmentFlow.class)
public class receiveShipmentFlow extends FlowLogic<SignedTransaction> {

    private FlowSession otherPartySession;

    public receiveShipmentFlow(FlowSession otherPartySession) {
        this.otherPartySession = otherPartySession;
    }



        @Suspendable
        @Override
        public SignedTransaction call() throws FlowException {

                System.out.println("Received Shipment");

                return subFlow(new ReceiveFinalityFlow(otherPartySession));
        }
}

