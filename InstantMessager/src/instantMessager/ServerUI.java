/*package instantMessager;

	import javafx.application.Application;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Button;
	import javafx.scene.control.ListView;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;
	import javafx.application.Platform;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	
	import java.io.*;
	import java.net.*;
	import java.util.ArrayList;

	public class ServerUI extends Application {
			
		
		ArrayList<Thread> th;
	    Button sendButton;
	    private TextArea log = new TextArea();
	    private TextField textbox = new TextField();
		
	public static void main(String[]args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		th = new ArrayList<Thread>();
		
		primaryStage.setTitle("SERVER");
		primaryStage.setScene(new Scene(setServerUI()));
		primaryStage.show();
	}
	
	
	
	 private Parent setServerUI() throws Exception{
	    	
		 
		 
	    	log.setPrefHeight(550);
	        VBox root = new VBox(0, log, textbox);
	        root.setPrefSize(600, 600);
	        sendButton = new Button();
	        sendButton.setMaxSize(600, 0);
	        sendButton.setText("Send");
	        
	        Server s;
	    	
	    	s = new Server(6000);
	    	Thread cThread = new Thread(s);
	    	cThread.setDaemon(true);
	    	th.add(cThread);
	    	
	        ListView<String> view = new ListView<String>();
	        view.setItems(s.serverLog);
	        
	        sendButton.setOnAction((event) ->{
	        	
	        	s.serverSend(textbox.getText());
	        	log.appendText(textbox.getText());
	        	textbox.clear();
	        	
	        });
	        root.getChildren().add(sendButton);
	        
	        return root;
	    }
	
	   
	}*/