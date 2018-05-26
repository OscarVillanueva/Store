package sample.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Cuenta;
import sample.MySQL;
import sample.Usuario;
import sample.dao.UsuarioDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ControllerCuenta implements Initializable {
    @FXML
    Button btnCrear, btnEditar,btnOk,btnPassword;
    @FXML
    GridPane index;
    @FXML
    TextField txtNombre,txtEmail,txtDireccion,txtTelefono,txtDatosTarjeta,txtPassword;
    @FXML
    DatePicker dpFecha;
    @FXML
    RadioButton rbCredito,rbRegalo;
    private FXMLLoader loader;
    private Parent parent;
    UsuarioDAO usuarioDAO=new UsuarioDAO(MySQL.getConnection());
    String admin="0";
    int id;
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
                Usuario usuario= new Usuario();
                usuario.setNombre(txtNombre.getText());
                usuario.setCorreo(txtEmail.getText());
                usuario.setTelefono(txtTelefono.getText());
                usuario.setDireccion(txtDireccion.getText());
                usuario.setAdmin(admin);
                //usuario.setPassword(txtPassword.getText());
                usuario.setFechaNac(Date.valueOf(dpFecha.getValue()));

                usuario.setId(id);
               usuarioDAO.update(usuario);
            }
            if(event.getSource()==btnPassword)
            {
                showNewPasswordStage();
            }

        }
    };

    public void recibecuenta(Cuenta c)
    {
        String desc;
        txtNombre.setText(c.getNombre());
        txtEmail.setText(c.getCorre());
        desc=c.getDescripcion().toString();
        txtDireccion.setText(c.getDireccion());
        txtDatosTarjeta.setText(c.getDatos());
        //LocalDate localDate = c.getFechaNac().toLocalDate();
        txtTelefono.setText(c.getTelefono());
        dpFecha.setValue(c.getFechaNac().toLocalDate());
        if(c.getAdmin().equals("1"))
        {

            admin="1";
            btnCrear.setVisible(true);
            btnEditar.setVisible(true);
        }
        if(desc.equals("Tarjeta de regalo"))
        {
            rbRegalo.setSelected(true);
            rbCredito.setDisable(true);
        }
        else
        {
            rbCredito.setSelected(true);
            rbRegalo.setDisable(true);
        }
        id=c.getId();


    }

    private void showNewPasswordStage() {

            Stage stage=new Stage();
            stage.setTitle("New Password");
            stage.setResizable(false);


            FXMLLoader loader= new FXMLLoader(getClass().getResource("../fxml/newPassword.fxml"));
            ControllerNewPassword controller= new ControllerNewPassword();

            try {
                controller=loader.getController();
                //controller.getPassword();
                Parent parent = loader.load();
                loader.setController(controller);

                Scene scene=new Scene(parent,500,250);
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


        }


    private void setContent(){

        index.getChildren().add(parent);
    }
}
