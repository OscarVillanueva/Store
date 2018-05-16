package sample.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable{

    @FXML
    Button btnback,btnRegister,btnLogin, btnForgotPassword;
    @FXML
    TextField txtusername, txtpassword;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnback.setOnAction(handler);
        btnRegister.setOnAction(handler);
        btnLogin.setOnAction(handler);
        btnForgotPassword.setOnAction(handler);

    }
    private EventHandler handler=new EventHandler()  {
        @Override
        public void handle(Event event) {
            if(event.getSource()==btnback)
            {
                //IR A LA PANTALLA PRINCIPAL
            }
            if(event.getSource()==btnRegister)
            {
             //IR A LA PANTALLA DE REGISTRO
            }
            if(event.getSource()==btnForgotPassword)
            {
                //INSERTAR MENSAJE DE "SE HA ENVIADO UN CORREO"
            }
            if(event.getSource()==btnLogin)
            {
                //VERIFICAR SI EXISTE, Y SI LLEVAR A PANTALLA PRINCIPAL.
            }
        }
    };
}
