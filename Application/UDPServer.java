// ----- Imported packages -----

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// ----- UDPServer class declaration -----

public class UDPServer 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		// ----- DatagramSocket declared and initalised -----
		
		DatagramSocket aSocket = null;      
		
		// ----- ArrayList of customer class declared and initalised -----
		
		ArrayList<Customer> c1=new ArrayList<Customer>();
		
		// ----- creating file instance to reference text file -----
		
		File text = new File("Member.txt");
		
		// ----- Creating scanner instance to read the file -----
		
		Scanner scnr = new Scanner(text);
		
		int num = 0;
		
		// ----- Reading each line of file using scanner class -----
		
		int lineNumber = 0;
		
        while(scnr.hasNextLine())                   // While Loop declaration for creaing all obejct of customer class and storing in the data in array list.
        {
        	Customer cs=new Customer();             // Creating customer class object every time until the while loop perform.
        	
            String line = scnr.nextLine();          // Reading the data from text file and storing in Line variable.
            
            String[] splitstr = line.split(" ");    // Splitting the data and storing in string array
            
            // ----- Setting the Client ID, Pin number, status and number of travel using customer class object -----
            
            cs.setClientId(splitstr[lineNumber]);
            
            cs.setPinNumber(Integer.parseInt(splitstr[lineNumber+1]));
            
            cs.setStatus(false);
            
            cs.setNumberOfTravels(0);
            
            // ----- Adding the data in array list using same class of customer-----
            
            c1.add(cs);
                         
        }      
        
        // ----- Persisting the data in binary file during ever 2 minutes -----
        
        int interval = 120000;      // Integer variable declaration for time 

        java.util.Timer tm = new java.util.Timer(); // Timer is used from Java.util package.

        // ----- Schedule timer to write to file after interval and repeat every interval -----
        
        tm.schedule(new WriteToFile(), interval, interval);
            
        
		try{
			
			// ----- Datagram socket assign with the port number -----
			
	    	aSocket = new DatagramSocket(6789);    
	    	
			byte[] buffer = new byte[1024];         // byte array
			
			// ----- Infinite loop to listen to clients -----
			
			System.out.println("UDP Server running...");
			
 			while(true)
 			{
 				// ----- Packet prepared to receive -----
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
 				
  				aSocket.receive(request);     // Receive
  				
  				// ----- Splitting data ------
  				
  				String m =  new String(request.getData()).trim();
				String[] m1 = m.split(":");

				// ----- Condition will only execute if customer wants to sign in -----
				
				
				if (m1[2].equals("I"))
				{
					
	  				for(int i=0;i<c1.size();i++)   // for loop to read the data in arraylist
	  				{
	  					
	  					// ----- check condition if client id is wrong -----
	  					
	  					if(m1[0].equals(c1.get(i).getClientId())==false)
						{
	  						String invlid = "In valid client ID";
  							buffer = invlid.getBytes();
  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
  							aSocket.send(reply);
  							Arrays.fill(buffer, (byte)0);
  							break;
						}
	  					
	  					else if(m1[0].equals(c1.get(i).getClientId()) == true && Integer.parseInt(m1[1]) == c1.get(i).getPinNumber())
						{
	  						// ----- check the status of the client -----
	  						
	  						if(c1.get(i).getStatus() == true)
							{
  								
  								String st = "Alredy login"; 
	  							buffer = st.getBytes();
	  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
	  							aSocket.send(reply);
	  							Arrays.fill(buffer, (byte)0);
	  							break;
	  							
							}
							
							else  // not already login
							{
								
								c1.get(i).setStatus(true);  // set status true
								
								num = c1.get(i).getNumberOfTravels();
								
								// ----- calculating cost and total number of travels -----
								
								if(num>=5)
								{
									c1.get(i).calculateCost();
									
								}
									
								num = num+1;
								c1.get(i).setNumberOfTravels(num);
							
								System.out.println(c1.get(i).getClientId()+"  "+c1.get(i).getNumberOfTravels()+"          $"+c1.get(i).getTotalCost());
								
								
	  							//System.out.println("Client Request: " + new String(request.getData()).trim());
	  						
	  							String invlid1 = "Welcome you have login";
	  							buffer = invlid1.getBytes();
	  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
	  							aSocket.send(reply);
	  							Arrays.fill(buffer, (byte)0);
	  							break;
	  							
							}
						}
	  					
	  					// ----- setting up invalid client pin message -----
	  					
	  					else
	  					{
	  						String invlid2 = "In valid client pin";
  							buffer = invlid2.getBytes();
  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
  							aSocket.send(reply);
  							Arrays.fill(buffer, (byte)0);
  							break;
		  							
		  							
	  					}		
	  				}
				}
  				
  				
  				// ----- Condition will execute if customer wants to log out -----
  				
  				if (m1[2].equals("O"))
  	            {
  					for(int i=0;i<c1.size();i++)
  					{
  						
	  					if(m1[0].equals(c1.get(i).getClientId()) == true && Integer.parseInt(m1[1]) == c1.get(i).getPinNumber())
						{
	  						// ----- logout condition check -----
	  						
	  						if(c1.get(i).getStatus() == true)
								{
											
									c1.get(i).setStatus(false);
									
		  							String st = "Goodbye log out successful";
		  							
		  							buffer = st.getBytes();
		  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
		  							aSocket.send(reply);
		  							Arrays.fill(buffer, (byte)0);
		  							break;
		  						
								}
								
								else
								{
									
		  							String invlid = "Already logout";   // already logout 
		  							buffer = invlid.getBytes();
		  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
		  							aSocket.send(reply);
		  							Arrays.fill(buffer, (byte)0);
		  							break;
		  							
								}
	  						
						}
	  					
	  					// ----- checking client id -----
	  					
	  					else if(m1[0].equals(c1.get(i).getClientId())== false)
						{
	  						String invlid1 = "In valid client ID";
  							buffer = invlid1.getBytes();
  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
  							aSocket.send(reply);
  							Arrays.fill(buffer, (byte)0);
  							break;
						}
	  					
	  				// ----- checking client password -----
	  					
	  					else
	  					{
	  						 								
  	  							
	  						String invlid12 = "In valid client pin";
  							buffer = invlid12.getBytes();
  							DatagramPacket reply = new DatagramPacket(buffer, buffer.length,request.getAddress(), request.getPort());
  							aSocket.send(reply);
  							Arrays.fill(buffer, (byte)0);
  							break;
  							
	  					}
  					}
  	            }
  							
    		}//end of loop
 			
		}//end of try block
		
		catch (SocketException e)  //socket exception handling.
		{System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e) // IOException handling
		{System.out.println("IO: " + e.getMessage());
		}
		finally {if(aSocket != null) aSocket.close();}
	}

}
