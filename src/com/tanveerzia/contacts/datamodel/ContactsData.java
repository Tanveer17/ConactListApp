package com.tanveerzia.contacts.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ContactsData {
    private static ContactsData intance = new ContactsData();
    private String file = "contacts.txt";

    private ObservableList<Contact> contacts;



    public static ContactsData getInstance(){
        return intance;
    }

    public ObservableList<Contact> getContacts(){
        return contacts;
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void loadTodoItems() throws IOException {
        contacts = FXCollections.observableArrayList();

        Path path = Paths.get(file);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try{
            while ((input = br.readLine()) != null) {

                String[] contactPieces = input.split("\t");

               String firstName =  contactPieces[0];
               String lastName = contactPieces[1];
               String phoneNumber = contactPieces[2];
               String notes = contactPieces[3];
                Contact contact = new Contact(firstName,lastName,phoneNumber,notes);
                contacts.add(contact);
            }

        }
        finally {
            if(br != null){
                br.close();
            }
        }

    }

    public void storeContacts() throws IOException {
        Path path = Paths.get(file);
        BufferedWriter br = Files.newBufferedWriter(path);
        try {
            Iterator<Contact> iter = contacts.iterator();
            while (iter.hasNext()) {
                Contact contact = iter.next();
                br.write(String.format("%s\t%s\t%s\t%s", contact.getFirstName(), contact.getLastName(),
                        contact.getPhoneNumber(), contact.getNotes()));
                br.newLine();
            }
        }
        finally {
            if(br !=null){
                br.close();
            }
        }
    }

    public void delete(Contact contact){
        contacts.remove(contact);
    }

}
