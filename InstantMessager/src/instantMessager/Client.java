package instantMessager;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Client extends JFrame{
	
	private JTextField textbox;
	private JTextArea clientLog;
	private JButton send;
	private JButton disconnect;
	private ObjectOutputStream outgoing;
	private ObjectInputStream incoming;
	private String received;
	private String IP;
	private Socket link;
	private boolean isOpen;
	
	
	public Client(String IP){
		
		this.IP = IP;
		//saves client's specified IP
		
		textbox = new JTextField(20);
		textbox.setEditable(true);
		add(textbox);
		//text box where user will type messages to send
	
		textbox.setBounds(2,516,590, 30);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
		textbox.setBorder(border);
		
		send = new JButton("SEND");
		add(send, BorderLayout.SOUTH);
		
		send.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent event){
			//gets text and sends to server when button is pressed
				
			if(isOpen) {
				String text = textbox.getText();
				send(text);
				textbox.setText("");
			}
			else
				displayClient("Error: Connection is not open!");
			
		}
	}
);
		
		disconnect = new JButton("DISCONNECT");
		disconnect.setBounds(500,5, 120,35);
		disconnect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				disconnect();
				
			}
			
		});
		
		clientLog = new JTextArea();
		clientLog.setEditable(false);
		add(new JScrollPane(clientLog));
		clientLog.add(disconnect);
		setSize(600, 600); 
		setVisible(true);
		//where the log of the conversation will be kept
		
		isOpen = false;
	}
	
	
	public void run(){
		try{

			connect();
			//tries to connect to a server socket on the same port
			displayClient("Success!");
			
			outgoing = new ObjectOutputStream(link.getOutputStream());
			incoming = new ObjectInputStream(link.getInputStream());
			//sets up input/output streams to get text from server or push text to server 
			
			
			while(isOpen) {
				
				try {
				received = (String)incoming.readObject();
				//waits for text to be sent, stores as string
				
				
				displayServer(received);
				//displays stored text		
					}
				catch(ClassNotFoundException e){
						displayClient("Error: Unable to read");
					}
				
				}
			
			
		}
		
		catch(EOFException e){
			e.printStackTrace();
			isOpen = false;
			//when the other user ends connection this executes
			
		}
		
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	
	private void connect() throws IOException{
		
		link = new Socket(IP, 6000);
		//sets up new client socket with their specified IP, port #
		displayClient("------Connection Established------");
		//reaches here after successfully connecting to server socket on same port #
		
		isOpen = true;
	}
	
	
	
	
		
	
	
	
	
	private void send(String text){
		
		
		try{
			outgoing.writeObject(text);
			//writes object to output stream
			displayClient(text);
			//displays the message client just sent in conversation log
		
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
		
	
	
	
	private void displayClient(String message){
			
		clientLog.append("Client: "+message+"\n");
		//shows messages sent by the client
		}

	
	private void displayServer(String message) {
		
			clientLog.append("Server: "+message+"\n");	
			//shows messages sent by server
		}
	
	private void display(String message) {
		
		clientLog.append(message+"\n");
	}
		
	private void disconnect(){
		//called when disconnect button pressed
		send("************* Connection has been closed *************");
		
		//notifies that connection has closed
		try{
			
			outgoing.close();
			incoming.close();
			//closes streams
			link.close();
			//closes socket
			
			isOpen = false;
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	/////////////////////////////////////////////////////////
	public static void main(String[] args) {
		
		Client testclient= new Client("127.0.0.1");
		testclient.run();
	}
}