package sample.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCuenta implements Initializable {
    @FXML
    Button btnCrear, btnEditar,btnOk,btnPassword;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCrear.setOnAction(handler);
        btnEditar.setOnAction(handler);
        btnOk.setOnAction(handler);
        btnPassword.setOnAction(handler);

    }
    private EventHandler handler= new EventHandler() {
        @Override
        public void handle(Event event) {
            if(event.getSource()==btnOk)
            {
                //REGRESAR A PANTALLA PRINCIPAL
            }
            if(event.getSource()==btnPassword)
            {
                //IR A PANTALLA EDITAR CONTRASEÑA
            }

        }
    };
}
