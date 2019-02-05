package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private TableView contactTable;

    @FXML
    private BorderPane parent;

    public void initialize(){
        makeTable();
    }

    public void makeTable(){
        TableColumn<Contact, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Contact, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Contact, String> phoneNoCol = new TableColumn<>("Phone Number");
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<Contact, String> notesCol = new TableColumn<>("Notes");
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

        contactTable.getColumns().addAll(firstNameCol,lastNameCol,phoneNoCol,notesCol);
        contactTable.getItems().add(new Contact("Tanveer","Zia","12312144","programmer"));
    }

    @FXML
    public void addContact(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(parent.getScene().getWindow());
        FXMLLoader fxmlLoader  = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addContactDialog.fxml"));
        try{
            fxmlLoader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){

        }
    }
}
