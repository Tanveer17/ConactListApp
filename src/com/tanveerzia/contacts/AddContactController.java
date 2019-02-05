package com.tanveerzia.contacts;

import com.tanveerzia.contacts.datamodel.Contact;
import com.tanveerzia.contacts.datamodel.ContactsData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddContactController {
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField notes;


    public Contact processData(){
        Contact contact = new Contact(firstName.getText(),lastName.getText(),phoneNumber.getText(),notes.getText());
        ContactsData.getInstance().addContact(contact);
        return contact;
    }

    public void edit(Contact contact){
        firstName.setText( contact.getFirstName());
        lastName.setText(contact.getLastName());
        phoneNumber.setText(contact.getPhoneNumber());
        notes.setText(contact.getNotes());
    }


}
