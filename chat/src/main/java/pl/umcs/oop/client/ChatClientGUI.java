package pl.umcs.oop.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientGUI extends Application {
    private TextArea chatArea;
    private TextField inputField;
    private PrintWriter out;

    @Override
    public void start(Stage stage) throws Exception {
        Socket socket = new Socket("localhost", 12345);
        out = new PrintWriter(socket.getOutputStream(), true);
        chatArea = new TextArea();
        chatArea.setEditable(false);
        inputField = new TextField();


        ClientReceiver receiver = new ClientReceiver(socket, chatArea);
        receiver.setDaemon(true);

        receiver.start();

        ListView<String> userList = new ListView<>();
        userList.getItems().add("user1");
        userList.getItems().add("user2");


        Button sendButton = new Button("Wyślij");
        sendButton.setOnAction(e -> {
            System.out.println("Wcisnieto przycisk");
            sendMessage();
        });
        inputField.setOnAction(e -> sendMessage());

        BorderPane pane = new BorderPane();
        pane.setCenter(chatArea);

        HBox bottomPanel = new HBox(inputField, sendButton);
        pane.setBottom(bottomPanel);

        VBox rightPanel = new VBox(new Label("Lista użytkowników"), userList);
        pane.setRight(rightPanel);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if(!message.isEmpty()){
//            chatArea.appendText(message+"\n");
            out.println(message);
            inputField.clear();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
