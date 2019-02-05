package com.tanveerzia.contacts;

import com.tanveerzia.contacts.datamodel.ContactsData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        ContactsData.getInstance().loadTodoItems();
    }

    @Override
    public void stop() throws Exception{
        ContactsData.getInstance().storeContacts();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
