package com.example.contacts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    public TableColumn<Contacts,String> firstName;
    @FXML
    public TableColumn<Contacts,String> lastname;
    @FXML
    public TableColumn<Contacts,String> phoneNumber;
    @FXML
    public TableColumn<Contacts,String> notes;
    @FXML
    public BorderPane mainScene;
    @FXML
    private TableView<Contacts> table;
    private ContextMenu contextMenu;
    public void initialize()
    {
        contextMenu=new ContextMenu();
        MenuItem add=new MenuItem("Add");
        MenuItem delete=new MenuItem("Delete");
        MenuItem edit=new MenuItem("Edit");
        EventHandler<ActionEvent> deleteEvent= actionEvent -> deleteContact();
        delete.setOnAction(deleteEvent);
        add.setOnAction(
                actionEvent -> handleAdd()
        );
        contextMenu.getItems().add(add);
        contextMenu.getItems().add(delete);
        contextMenu.getItems().add(edit);
        table.setContextMenu(contextMenu);
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        table.setItems(ContactsInstance.getInstance().getMembers());

    }

    private void deleteContact() {
        Contacts contact=table.getSelectionModel().getSelectedItem();
        if(contact!=null) {
            ContactsInstance.getInstance().removeContact(contact);
        }

    }

    public void handleAdd() {
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(mainScene.getScene().getWindow());
        FXMLLoader loader=new FXMLLoader(Application.class.getResource("add_contacts.fxml"));
        try
        {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result =dialog.showAndWait();
        if(result.isPresent()&&result.get().equals(ButtonType.OK))
        {
            AddContactsController c=loader.getController();
            Contacts contact=c.addContacts();
            table.getSelectionModel().select(contact);

        }
    }

}