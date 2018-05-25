package sample.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.MySQL;
import sample.dao.UsuarioDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable{
    @FXML
    GridPane index;

    @FXML
    Button btnLogin, btnForgotPassword;
    @FXML
    TextField txtcorreo, txtcontrasena;
    private UsuarioDAO usuarioDAO = new UsuarioDAO(MySQL.getConnection());
    private FXMLLoader loader;
    private Parent parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnLogin.setOnAction(handler);
        btnForgotPassword.setOnAction(handler);

    }
    private EventHandler handler=new EventHandler()  {
        @Override
        public void handle(Event event) {

            if(event.getSource()==btnForgotPassword)
            {
                showmessage("Se ha enviado un correo ");
            }
            if(event.getSource()==btnLogin)
            {
                boolean existe=true;
                existe=usuarioDAO.existeRegistro(txtcorreo.getText());
                if(existe==true) {

                    loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../fxml/home.fxml"));
                    try {
                        loader.load();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    parent = loader.getRoot();
                    setContent();
                }
                else
                {
                    showmessage("correo o contraseña invalidos");
                }
            }
        }
    };
    public void showmessage(String mensaje)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setContentText(mensaje);
        alert.show();
    }
    private void setContent(){
        //index.getChildren().add(parent);
        index.getChildren().removeAll();
        index.getChildren().clear();
        index.getChildren().add(parent);
    }

    public String correo()
    {
        String correo=txtcorreo.getText();
        return correo;
    }
}
