package com.example.contacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactsInstance {
    private static final ContactsInstance contactsInstance=new ContactsInstance();
    private ObservableList<Contacts> members= FXCollections.observableArrayList();
    public static ContactsInstance getInstance()
    {
        return contactsInstance;
    }
    public ObservableList<Contacts> getMembers() {
        return members;
    }

    public void loadContacts()
    {
        Contacts surya=new Contacts("Surya","Vellimuthu","9626312953","Myself");
        Contacts saravanan=new Contacts("saravanan","T D","962838292","Friend");
        Contacts Kathir=new Contacts("Kathir","Pandian","2871873923","Friend");
        Contacts MuthuVishal=new Contacts("Muthu","Vishal","273128319","Friend");
        members.add(surya);
        members.add(saravanan);
        members.add(Kathir);
        members.add(MuthuVishal);
    }
    public void add(Contacts contacts)
    {
            members.add(contacts);
    }

    public void removeContact(Contacts contact) {
        members.remove(contact);
    }
}
