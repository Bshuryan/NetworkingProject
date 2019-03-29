package instantMessager;

	import javafx.application.Application;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Button;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;

	public class TestUI  extends Application implements EventHandler<ActionEvent> {

	    public static void main(String[] args) {
	        launch(args);
	    }
	    Button sendButton;
	    private TextArea messages = new TextArea();

	    private Parent createContent() {
	        messages.setPrefHeight(550);
	        TextField input = new TextField();
	        VBox root = new VBox(0, messages, input);
	        root.setPrefSize(600, 600);
	        sendButton = new Button();
	        sendButton.setMaxSize(600, 0);
	        sendButton.setText("Send");
	        sendButton.setOnAction(this);
	        root.getChildren().add(sendButton);
	        return root;
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        primaryStage.setScene(new Scene(createContent()));
	        primaryStage.show();
	        primaryStage.setTitle("Instant Messenger");

	    }

	    @Override
	    public void handle(ActionEvent event) {
	        System.out.println("hey");
	    }

	}

