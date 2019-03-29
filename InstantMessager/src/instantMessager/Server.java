package instantMessager;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Server extends JFrame {
	
	private JTextField textbox;
	private JTextArea serverLog;
	private JButton send;
	
	private ObjectOutputStream outgoing;
	private ObjectInputStream incoming;
	private ServerSocket server;
	private Socket link;
	private String received;
	
	
	
	public Server(){
		
		
		textbox = new JTextField(20);
		textbox.setEditable(true);
		//where users will type messages to send
		add(textbox);
		textbox.setBounds(2,516,590, 30);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
		textbox.setBorder(border);
		
		send = new JButton("SEND");
		add(send, BorderLayout.SOUTH);
		
		send.addActionListener(new ActionListener(){
		//on pressing send, will get text currently in the textbox and display it on both server and client's end
			public void actionPerformed(ActionEvent event){
			
			String text = textbox.getText();
			send(text);
			textbox.setText("");
			
		}
	}
);
		
		
		
		serverLog = new JTextArea();
		serverLog.setEditable(false);
		add(new JScrollPane(serverLog));
		setSize(600, 600); 
		setVisible(true);
		//where messages will be displayed (users not able to edit)
	}
	
	public void run(){
		try{
			server = new ServerSocket(6000); 
			//setting up TCP connection
			connect();
			//waits for socket to connect on same port
			displayServer("Success!");
			//reaches here when another user successfully connects 
					
					
					
					outgoing = new ObjectOutputStream(link.getOutputStream());
					incoming = new ObjectInputStream(link.getInputStream());
					
				
						
						do {
							
						try {
						received = (String)incoming.readObject();
							
								displayClient(received);
						}
							
						catch(ClassNotFoundException e){
								displayClient("Error: Unable to read");
							}
						
						}
						while(!received.equals("TERMINATE"));
						
						//waits for messages to be sent over the input stream, if receives a message that equals
						//TERMINATE then closes the connection
						
					}
					
					
					
					
				catch(EOFException e){
					e.printStackTrace();
					
					}
					
					catch (IOException e) {
					
					e.printStackTrace();
				} 
				
				finally
				{
					//reaches here if receives the word TERMINATE on the input stream
					disconnect(); 
				}
			}
		

	
	private void connect() throws IOException{
		
		displayServer("------Connecting------");
		//waiting for another socket to connect
		link = server.accept();
		//another socket has connected on same port #
		
	}
	

	
	
	private void send(String message){
		try{
			
			
			outgoing.writeObject(message);
			//sends message on server's output stream for client to receive on its input stream
			displayServer(message);
			//displays message that server sent to keep continuous log of conversation
			
		}
		catch(IOException e){
			
			displayServer("Error: Unable to send. Please try again.");
		}
	}
	
	
	
	private void displayClient(String text){
		//displays messages sent by client
			serverLog.append("Client: "+text+"\n");
		
	}
	
	private void displayServer(String text){
		//displays messages sent by the server	
		serverLog.append("Server: "+text+"\n");
	
	}
	
	private void display(String text) {
		
		serverLog.append(text);
	}
	
	
	public void disconnect(){
		
		display("************* Connection has been closed *************");
		
		try{
			outgoing.close(); 
			incoming.close();
			//closes streams
			link.close(); 
			//closes socket
			
		}
		
		catch(IOException e)
		{
			displayServer("Error: Unable to disconnect");
		}
	}
	
	//////////////////////////////////////////////////////
	public static void main(String[] args) {
		
		Server s = new Server();
		s.run();
	}
}

