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


## ✅ Business Rules Enforced
- Only CyberTruck model can be shipped.
- Only the manufacturer is allowed to sign the shipment transaction.
- Only one vehicle can be shipped per transaction.

## 📌 Sample X500 Legal Names
Make sure your node configurations use valid ISO 3166 country codes (e.g., C=AE not C=UAE).

## 🤝 Contributing
Feel free to fork this project and customize it for your own CorDapp experiments.

## 🛠 Tech Stack

- [Corda](https://www.r3.com/corda/) 4.12 (Community Edition)
- Java 17
- Gradle 7.6
- IntelliJ IDEA

## 🗂 Project Structure
Tesla_cordApp/
├── contracts/ # Contains CarContract and state definitions
├── workflows/ # Contains flows: ShipmentFlow & ReceiveShipmentFlow
├── build.gradle # Gradle build configuration
├── nodes/ # Auto-generated node folders

### Deploy nodes
./gradlew clean deployNodes

### Run nodes
./build/nodes/runnodes


