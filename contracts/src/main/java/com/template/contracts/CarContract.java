package com.template.contracts;

import com.template.states.CarState;
import com.template.states.TemplateState;
import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.ContractState;
import net.corda.core.transactions.LedgerTransaction;
import org.jetbrains.annotations.NotNull;

import java.security.PublicKey;
import java.util.List;

import static net.corda.core.contracts.ContractsDSL.requireThat;

// ************
// * Contract *
// ************
public class CarContract implements Contract {
    // This is used to identify our contract when building a transaction.
    public static final String CAR_CONTRACT_ID = "com.template.contracts.CarContract";

    // A transaction is valid if verify() method of the contract of all the transaction's input and output states
    // does not throw an exception.
    @Override
    public void verify(@NotNull LedgerTransaction tx) throws IllegalArgumentException {
        if (tx.getCommands().size() != 1) {
            throw new IllegalArgumentException("There can only be one command");
        }

        Command command = tx.getCommand(0);
        CommandData commandType = command.getValue();
        List<PublicKey> requiredSigners = command.getSigners();

        if (commandType instanceof shipment) {
            // Shape rules
            if (tx.getInputStates().size() != 0) {
                throw new IllegalArgumentException("There should be no input state for shipment");
            }
            if (tx.getOutputStates().size() != 1) {
                throw new IllegalArgumentException("Only one vehicle can be shipped at a time");
            }

            // ✅ FIXED: define outputState before using
            ContractState outputState = tx.getOutput(0);

            // Content rules
            if (!(outputState instanceof CarState)) {
                throw new IllegalArgumentException("Output must be a CarState");
            }

            CarState carState = (CarState) outputState;

            if (!carState.getModel().equals("CyberTruck")) {
                throw new IllegalArgumentException("Only a CyberTruck can be shipped");
            }

            // Signer rules
            PublicKey manufacturerKey = carState.getManufacturer().getOwningKey();
            if (!requiredSigners.contains(manufacturerKey)) {
                throw new IllegalArgumentException("Manufacturer must sign the transaction");
            }
        }
    }




   public static class shipment implements CommandData {}
}