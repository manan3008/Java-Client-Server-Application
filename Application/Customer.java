import java.io.Serializable;

// Customer class which IMPLEMENTS Serializable

public class Customer implements Serializable
{
	// ----- Variable Declaration -----
	
	String clientId;           // String variable for clientId
	int pinNumber;             // Integer variable for Pin number
	Boolean status;            // Boolean variable for checking the status of the customer
	int numberOfTravels;       // Integer variable for counting total number of travel of the customer
	double totalCost = 0;      // Double variable for counting total Cost of the customer
	
	// ----- Default Constructor Declaration -----
	
	public Customer()
	{
		// Default constructor of class customer
	}
	
	// ----- Method declaration for getting the Client ID -----
	
	public String getClientId() 
	{
		return clientId;       // Return Client ID
	}
	
	// ----- Method declaration for setting the Client ID -----
	
	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}
	
	// ----- Method declaration for getting the Pin number -----
		
	public int getPinNumber() 
	{
		return pinNumber;       // Return Pin number
	}
	
	// ----- Method declaration for setting the Pin number -----
	
	public void setPinNumber(int pinNumber) 
	{
		this.pinNumber = pinNumber;
	}
	
	// ----- Method declaration for getting the status of customer -----
	
	public Boolean getStatus() 
	{
		return status;           // Return status
	}
	
	// ----- Method declaration for setting the status of customer -----
	
	public void setStatus(Boolean status)
	{
		this.status = status;
	}
	
	// ----- Method declaration for getting the number of travels -----
	
	public int getNumberOfTravels() 
	{
		return numberOfTravels;   // Return number of travels
	}
	
	// ----- Method declaration for setting the number of travels -----
	
	public void setNumberOfTravels(int numberOfTravels) 
	{
		this.numberOfTravels = numberOfTravels;
	}
	
	// ----- Method declaration for getting the total cost -----
	
	public double getTotalCost() 
	{
		return totalCost;          // Return total cost of customer
	}
	
	// ----- Method declaration for calculating total cost -----
	
	public void calculateCost()
	{
		totalCost = totalCost+ 3;  // Calculating total cost using specific formula
	}
	
	// ----- Declaration of toString() method for returning string representation of the customer class object -----
	
	public String toString()
	{
		return new StringBuffer("client ID").append(clientId).append("Total number of travel").append(numberOfTravels).append("Cost").append(totalCost).toString();    // Return String message using String Buffer function
	}
	
} // End Customer class
