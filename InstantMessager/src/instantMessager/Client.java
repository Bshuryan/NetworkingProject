package instantMessager;

import java.io.*;
import java.net.*;

public class Client {
	
	private String IP;
	private Socket link;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	
	
	
	public Client(int port, String IP_address)
	{
		try {
			IP = IP_address;
			link = new Socket(InetAddress.getByName(IP), port);
			sendMessage("Connection established!");
	}
		catch(IOException e) {
			System.out.println("Error: Unable to connect.");
		}
		
		}
	
		
	
public void sendMessage(String text) {
		
		try {
			output.writeObject("Client: " +text);
		}
		
		catch(IOException e)
		{
			System.out.println("Error: Message unable to be sent.");
		}
	}




public void run() throws IOException {
	
	output = new ObjectOutputStream(link.getOutputStream());
	input = new ObjectInputStream(link.getInputStream());
	
	String text = "Welcome!";
	sendMessage(text);
	
	try {
	
	while(!text.equals("Server: TERMINATE")) {
		
		try {
			text = input.readObject().toString();
			sendMessage(text);
		}
		
		catch(ClassNotFoundException e)
		{
			System.out.println("Error: Unknown class. Please try again.");
		}
	
	catch(EOFException e)
	{
		System.out.println("Client has terminated the connection.");
		
	}
		
	}
	}
	
	finally {
		closeConnection();
	}

}


public void closeConnection() {
	
	sendMessage("Goodbye!");
	
	try {
		
	output.close();
	input.close();
	link.close();
	
	}
	
	catch(IOException e) {
		System.out.println("Error: Unable to close connection. Please try again.");
	}
}



public static void main(String args[]) throws IOException {
	
	Client testClient = new Client(156, "127.0.0.1");
	testClient.run();
	
	
}


}
