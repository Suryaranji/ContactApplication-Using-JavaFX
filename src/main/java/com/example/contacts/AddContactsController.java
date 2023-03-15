package com.example.contacts;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddContactsController {
    @FXML
    public TextField fname;
    @FXML
    public TextField lname;
    @FXML
    public TextField number;
    @FXML
    public TextField notes;
    @FXML

    public Contacts addContacts()
    {
        Contacts contacts=new Contacts(fname.getText(),lname.getText(),number.getText(),notes.getText());
        ContactsInstance.getInstance().add(contacts);
        return  contacts;
    }
}
