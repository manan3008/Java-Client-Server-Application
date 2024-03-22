# Java UDP Client/Server Application for City Transport System

## Overview

This project implements a client/server model using Java UDP sockets to manage a city council's initiative for reducing private car usage in inner-city areas. Residents are issued membership cards for free or reduced-rate travel on public transport. The system tracks sign-ins and sign-offs at kiosks via a membership card with a client ID and PIN, handling transactions and validations over a UDP network.

## Objectives

- Implement Java UDP networking for concurrent client-server communication.
- Utilize Java Object Serialization/Deserialization for data storage and retrieval.
- Manage file reading and writing for persistent data storage.

## System Requirements

- Java Development Kit (JDK) installed on your machine.
- Basic knowledge of UDP networking, serialization/deserialization, and file handling in Java.

## Implementation Details

### Server

- Initializes from a text file (`Member.txt`) containing customer records and loads these into an ArrayList.
- Handles incoming UDP packets from clients, parsing messages for sign-in, sign-out, and exit actions.
- Performs all necessary validations and updates customer records accordingly.
- Periodically serializes the ArrayList to a binary file (`Member.dat`) for data persistence.

### Client

- Offers a menu-driven interface for users to sign in, sign out, or exit.
- Sends user actions and credentials to the server via UDP packets.
- Displays responses from the server, including validation messages and errors.

### Customer Class

- Implements Serializable interface.
- Contains relevant information such as client ID, PIN, and travel records.

### WriteToFile Class

- Manages serialization of customer records from the ArrayList to the binary file `Member.dat`.

## How to Run

1. **Start the Server**: Compile and run `UDPServer.java`. This initializes the server, loads existing records, and starts listening for UDP requests.
2. **Run the Client Application**: Compile and run `UDPClient.java` on the same or a different machine. Follow the on-screen prompts to sign in or sign out.

## Data Persistence

- The server periodically serializes the customer records to `Member.dat`, ensuring data is saved even between server restarts.
