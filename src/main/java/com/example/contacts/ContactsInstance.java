package com.example.contacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ContactsInstance {
    private static final ContactsInstance contactsInstance=new ContactsInstance();
    private final ObservableList<Contacts> members= FXCollections.observableArrayList();

    private final Connection conn;

    {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/database.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ContactsInstance getInstance()
    {
        return contactsInstance;
    }

    public ObservableList<Contacts> getMembers() {
        return members;
    }


    public void loadContacts()
    {

        if(conn!=null)
        {
            try (Statement stmt=conn.createStatement();
                 ResultSet result=stmt.executeQuery("SELECT * FROM Contacts"))
            {
                while(result.next())
                {
                    String fname=result.getString("firstName");
                    String lname=result.getString("lastName");
                    String phonenumber= result.getString("phoneNumber");
                    String notes=result.getString("notes");
                    Contacts contact=new Contacts(fname,lname,phonenumber,notes);
                    members.add(contact);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }
    public void add(Contacts contacts)
    {
        if(conn!=null) {

            try (Statement stmt = conn.createStatement();) {

          stmt.execute("INSERT INTO contacts VALUES('"+ contacts.getFirstName() + "','" + contacts.getLastName()
                        + "','" + contacts.getPhoneNumber()
                        + "','" + contacts.getNotes() + "' )");
                members.add(contacts);
                //stmt.execute("Insert into contacts values('Surya','Ranjitham','2836218','Myself')");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeContact(Contacts contact) {
        try( Statement stmt=conn.createStatement())
        {
            stmt.execute("Delete from Contacts where phoneNumber='"+contact.getPhoneNumber()+"'");
            members.remove(contact);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        members.remove(contact);
    }
}
