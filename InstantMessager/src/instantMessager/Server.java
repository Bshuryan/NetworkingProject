package instantMessager;

import java.io.*;
import java.net.*;

public class Server {
	
	private ServerSocket server;
	private Socket link;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String usn;
	private int port;
	
	public Server(String usn, int port) {
		
		this.usn = usn;
		this.port = port;
	
	}
	
	public void run() throws IOException {
		
		server = new ServerSocket(port, 100);
		
		//output = new ObjectOutputStream(link.getOutputStream());
		//input = new ObjectInputStream(link.getInputStream());
		
		connect();
		
		/*String text = "Welcome!";
		sendMessage(text);
		
		
		
		try {
		
		while(!text.equals("Client: TERMINATE")) {
			
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
		} */
	
	}
	
	
	
	
	
	public void sendMessage(String text) {
		
		try {
			output.writeObject("Server: " +text);
		}
		
		catch(IOException e)
		{
			System.out.println("Error: Message unable to be sent.");
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
	
	
		
	public void connect() {
		
		System.out.println("Waiting...");
		try {
		
		link = server.accept();
		System.out.println("Connected!");
		}
		
		catch(IOException e) {
			System.out.println("Error: server unable to connect");
		}
	}
	
	
	
	
	
	
	

}
