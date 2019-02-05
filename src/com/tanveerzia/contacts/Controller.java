package com.tanveerzia.contacts;

import com.tanveerzia.contacts.datamodel.Contact;
import com.tanveerzia.contacts.datamodel.ContactsData;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private TableView contactTable;

    @FXML
    private BorderPane parent;
    private ContextMenu contextMenu;
    private FilteredList<Contact> filteredList;

    public void initialize() {
        makeTable();
        contactTable.getItems().addAll(ContactsData.getInstance().getContacts());
    }

    public void makeTable() {
        TableColumn<Contact, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Contact, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Contact, String> phoneNoCol = new TableColumn<>("Phone Number");
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<Contact, String> notesCol = new TableColumn<>("Notes");
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

        contactTable.getColumns().addAll(firstNameCol, lastNameCol, phoneNoCol, notesCol);

    }

    @FXML
    public void addContact() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(parent.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addContactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            AddContactController controller = fxmlLoader.getController();

            contactTable.getItems().add(controller.processData());

        }
    }

    public void deleteContact(Contact contact) {
        ContactsData.getInstance().delete(contact);
        contactTable.getItems().remove(contact);

    }
   @FXML
    public void editContact() {

        Contact contact = (Contact)(contactTable.getItems().get(contactTable.getSelectionModel().getFocusedIndex()));
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(parent.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addContactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddContactController controller = fxmlLoader.getController();
        controller.edit(contact);
        int index = contactTable.getItems().indexOf(contact);


        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteContact(contact);
            contactTable.getItems().add(index,controller.processData());
        }
    }

    @FXML
    public void deleteContact(){
       Contact contact = (Contact)contactTable.getItems().get(contactTable.getSelectionModel().getFocusedIndex());
       ContactsData.getInstance().delete(contact);
        contactTable.getItems().remove(contact);
    }
}
