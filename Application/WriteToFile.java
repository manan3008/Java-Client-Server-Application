import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TimerTask;

public class WriteToFile extends TimerTask // Extends timertask class

{
	ArrayList<Customer> c1 = new ArrayList<Customer>(); // creadting array list of cutomer class
	
	
	public static void writeToFile(ArrayList<Customer> c1) throws FileNotFoundException, IOException
	{
		try
		{
			
		// ----- creating objectOutput  stream object and member.dat file -----
		
		
		ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream("Member.dat"));
		objectOutputStream.writeObject(c1);
		objectOutputStream.flush();
		objectOutputStream.close(); // closing the file
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void run()  // run method which autommatically calls
	{
		
		WriteToFile wr=new WriteToFile();
		try {
			wr.writeToFile(c1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}