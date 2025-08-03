# Tesla_cordApp 🚗⚡

A sample Corda CorDapp simulating the secure shipment and registration of Tesla vehicles using distributed ledger technology.

## 📦 Overview

This CorDapp demonstrates how Corda can be used to:

- Issue and track vehicle shipment records
- Ensure manufacturer-only transactions using smart contracts
- Exchange shipment data between network parties securely

The project includes:
- `CarState`: A state representing a Tesla car with model, manufacturer, and owner.
- `ShipmentFlow`: Initiates the shipment transaction.
- `ReceiveShipmentFlow`: Responds to the shipment transaction.
- `CarContract`: Validates business rules, including model restrictions and required signers.

## 🛠 Tech Stack

- [Corda](https://www.r3.com/corda/) 4.12 (Community Edition)
- Java 8
- Gradle 7.6
- IntelliJ IDEA

### Deploy nodes
./gradlew clean deployNodes

### Run nodes
./build/nodes/runnodes


