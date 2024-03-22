// ----- Imported packages -----

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

// ----- UDPClient Class -----

public class UDPClient 
{
	// ----- Main Method Declaration -----
	
	public static void main(String[] args) 
	{
		// ----- DatagramSocket declared and initalised -----
		
		DatagramSocket socket = null;        
		
		try     // Try block for Exception handling 
		{
			
			socket = new DatagramSocket();       // DatagramSocket assigned.
			int serverPort = 6789;               // Integer variable for assigning Server port number 
			int choice = 0;                      // Integer variable for choice making
			
			Scanner input = new Scanner(System.in);   // Scanner instance assigned.
			
			// ----- Host address designated to localhost -----
			
			InetAddress aHost = InetAddress.getByName("localhost");
			
			// ----- Do While loop declaration for input system -----
			
			do
			{
		
				// ----- Prompts for Entering the Choice IN/OUT/EXIT -----
				
				System.out.println("*************Travel Kiosk*************");
				System.out.println("\t\t\t1: IN");
				System.out.println("\t\t\t2: OUT");
				System.out.println("\t\t\t3: EXIT");
				
				System.out.println("Enter: ");
				choice=input.nextInt();
				
				// ----- If condition declaration -----
				
				if(choice==1)     // Condition will execute when customer enter the choice 1.
				{
					 
					input.nextLine();
					
					// ----- Prompts for entering the client id and pin number -----
					
					System.out.println("Enter client id: ");
					String clientID=input.nextLine();      // Local String variable declared for storing client id.
					
					System.out.println("Enter client pin: ");
					int pinNumber=input.nextInt();         // Local Integer variable declared for storing client pin number.
					
					// ----- Converting the client id variable and pin number variable into byte using getBytes() method ------
			
					
					byte[] requestText= (clientID+":"+pinNumber+":"+"I").getBytes();   // Storing the converted text in byte array name as requestText.
					
					
					// ----- Datagram packet object is declared for sending the data to the server -----
					
					DatagramPacket request =new DatagramPacket(requestText,requestText.length, aHost, serverPort);
					socket.send(request);
					
					// ----- Byte array used to receive a maximum of 1024 characters -----
					
					byte[] replyText = new byte[1024];
					
					// ----- Datagram packet object is declared for receiving the data from the server -----
					
					DatagramPacket reply = new DatagramPacket(replyText, replyText.length);					
					socket.receive(reply);
					
					System.out.println("Server Response: " + new String(reply.getData()).trim());    // Printing the server response.
					
				}
				
				if(choice==2)     // Condition will execute when customer enter the choice 2.
				{
					
					input.nextLine();
					
					// ----- Prompts for entering the client id and pin number -----
					
					System.out.println("Enter client id: ");
					String clientID=input.nextLine();      // Local String variable declared for storing client id.
					
					System.out.println("Enter the client pin: ");
					int pinNumber=input.nextInt();         // Local Integer variable declared for storing client pin number.
					
					// ----- Converting the client id variable and pin number variable into byte using getBytes() method ------
					
					byte[] requestText= (clientID+":"+pinNumber+":"+"O").getBytes();     // Storing the converted text in byte array name as requestText.
					
					// ----- Datagram packet object is declared for sending the data to the server -----
					
					DatagramPacket request =new DatagramPacket(requestText,requestText.length, aHost, serverPort);
					socket.send(request);
					
					// ----- Byte array used to receive a maximum of 1024 characters -----
					
					byte[] replyText=new byte[1024];
					
					// ----- Datagram packet object is declared for receiving the data from the server -----
					
					DatagramPacket reply = new DatagramPacket(replyText, replyText.length);
					socket.receive(reply);
					
					System.out.println("Server Response: " + new String(reply.getData()).trim());    // Printing the server response.
					
				}
		
			}while(choice != 3);  // End of the Do while loop once the choice is "3" that is exit !!
			
		}//end of try block
		
		// ----- Catch blocks declaration -----
		
		catch (SocketException e)    // Socket Exception 
		{
			System.out.println("Socket: " + e.getMessage());
		}
		
		catch (IOException e)     // IO Exception  
		{
			System.out.println("IO: " + e.getMessage());
		}
		
		// ----- Finally block declaration -----
		
		finally
		{
			if(socket != null) socket.close();   // close the socket if it is null.
		}
	}
}
