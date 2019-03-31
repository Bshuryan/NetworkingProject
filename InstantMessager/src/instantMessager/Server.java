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
	private JButton disconnect;
	
	private ObjectOutputStream outgoing;
	private ObjectInputStream incoming;
	private ServerSocket server;
	private Socket link;
	private String received;
	private boolean isOpen;
	
	
	
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
			public void actionPerformed(ActionEvent e){
			
				if(isOpen) {
					String text = textbox.getText();
					send(text);
					textbox.setText("");
				}
				else
					displayServer("Error: Connection is not open!");
				
		}
			
			
	}
);
		//button that will void the connection between two users
		disconnect = new JButton("DISCONNECT");
		disconnect.setBounds(500,5, 95,35);
		disconnect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				disconnect();
				
			}
			
		});
		
		serverLog = new JTextArea();
		serverLog.setEditable(false);
		add(new JScrollPane(serverLog));
		
		serverLog.add(disconnect);
		setSize(600, 600); 
		setVisible(true);
		//where messages will be displayed (users not able to edit)
		isOpen = false;
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
					
				
						
						while(isOpen) {
							
						try {
						received = (String)incoming.readObject();
						
						//if(received == "************* Connection has been closed *************")
							//isOpen = false;
							
						displayClient(received);
						}
							
						catch(ClassNotFoundException e){
								displayClient("Error: Unable to read");
							}
						
						}
						
						
					}
					
					
					
					
				catch(EOFException e){
					e.printStackTrace();
					isOpen = false;
					
					}
					
					catch (IOException e) {
					
					e.printStackTrace();
				} 
				
			
			}
		

	
	private void connect() throws IOException{
		
		displayServer("------Connecting------");
		//waiting for another socket to connect
		link = server.accept();
		//another socket has connected on same port #
		isOpen = true;
		
	}
	

	
	
	private void send(String message){
		
	
		try{
			
			
			outgoing.writeObject(message);
			//sends message on server's output stream for client to receive on its input stream
			displayServer(message);
			//displays message that server sent to keep continuous log of conversation
			
		}
		catch(IOException e){
			
			e.printStackTrace();
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
		
		serverLog.append(text+"\n");
	}
	
	
	public void disconnect(){
		
		
		send("************* Connection has been closed **************");
		
		
		try{
			outgoing.close(); 
			incoming.close();
			//closes streams
			link.close(); 
			//closes socket
			isOpen = false;
			
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////////
	public static void main(String[] args) {
		
		Server s = new Server();
		s.run();
	}
}

