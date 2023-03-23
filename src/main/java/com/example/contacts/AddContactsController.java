package com.example.contacts;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
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
    public DialogPane dialog;

    @FXML

    public String addContacts()
    {
        if(validityChecker().equals(""))
        {
            Contacts contacts=new Contacts(fname.getText(),lname.getText(),number.getText(),notes.getText());
            ContactsInstance.getInstance().add(contacts);
        }
        else return validityChecker();
        return  "";
    }

    private String validityChecker() {
        String firstName=fname.getText();
        String lastName=lname.getText();
        String phoneNumber=number.getText();
        String notes_String=notes.getText();
        if(firstName.equals("")||lastName.equals("")||phoneNumber.equals("")||notes_String.equals(""))return "Empty Field";
        if(!phoneNumber.matches("[0-9]{10}"))return "Mobile Number Not Valid";
        if(!firstName.matches("([A-Z]|[a-z])+")||!lastName.matches("([A-Z]|[a-z])+"))return "Names Are Not Valid";
        return "";

    }

    public String editContacts(Contacts contact) {
        if(validityChecker().equals("")) {
            ContactsInstance.getInstance().removeContact(contact);
            contact.setFirstName(fname.getText());
            contact.setLastName(lname.getText());
            contact.setPhoneNumber(number.getText());
            contact.setNotes(notes.getText());
            ContactsInstance.getInstance().add(contact);
        }
        else return validityChecker();
        return  "";

    }

    public void showEditDetails(Contacts contact) {
        fname.setText(contact.getFirstName());
        lname.setText(contact.getLastName());
        number.setText(contact.getPhoneNumber());
        notes.setText(contact.getNotes());
    }
}
