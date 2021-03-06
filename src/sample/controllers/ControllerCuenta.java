package sample.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.*;
import sample.dao.FormaPagoDAO;
import sample.dao.UsuarioDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerCuenta implements Initializable {
    @FXML
    Button btnCrear, btnEditar, btnOk, btnPassword;
    @FXML
    GridPane index;
    @FXML
    TextField txtNombre, txtEmail, txtDireccion, txtTelefono, txtDatosTarjeta, txtPassword;
    @FXML
    DatePicker dpFecha;
    @FXML
    RadioButton rbCredito, rbRegalo;
    String desc = "";
    private FXMLLoader loader;
    private Parent parent;
    UsuarioDAO usuarioDAO = new UsuarioDAO(MySQL.getConnection());
    FormaPagoDAO formaDAO = new FormaPagoDAO(MySQL.getConnection());
    String admin = "0";
    int id, tip = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCrear.setOnAction(handler);
        btnEditar.setOnAction(handler);
        btnOk.setOnAction(handler);
        btnPassword.setOnAction(handler);
        btnEditar.setOnAction(handler);
    }


    private EventHandler handler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource() == btnOk) {
                Usuario usuario = new Usuario();
                FormaPago forma = new FormaPago();
                usuario.setNombre(txtNombre.getText());
                usuario.setCorreo(txtEmail.getText());
                usuario.setTelefono(txtTelefono.getText());
                usuario.setDireccion(txtDireccion.getText());
                usuario.setAdmin(admin);
                usuario.setPasswd(txtPassword.getText());
                usuario.setFechaNac(Date.valueOf(dpFecha.getValue()));

                usuario.setId(id);
                usuarioDAO.update(usuario);
                forma.setDatos(txtDatosTarjeta.getText());
                forma.setIdUsuario(id);
                forma.setTipoPago(tip);
                formaDAO.update(forma);
                confirmacion();

            }
            else{
                if(event.getSource() == btnEditar){
                    Persistencia persistencia = new Persistencia();
                    try {
                        persistencia.emptyFile("data.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    confirmacion();
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
                else {
                    if(event.getSource() == btnCrear){
                        insert();
                    }
                }
            }

        }
    };

    public void recibecuenta(Cuenta c) {

        txtNombre.setText(c.getNombre());
        txtEmail.setText(c.getCorre());
        desc = c.getDescripcion().toString();
        txtDireccion.setText(c.getDireccion());
        txtDatosTarjeta.setText(c.getDatos());
        txtPassword.setText(c.getPasswd());

        txtTelefono.setText(c.getTelefono());
        dpFecha.setValue(c.getFechaNac().toLocalDate());
        if (c.getAdmin().equals("1")) {

            admin = "1";
            btnCrear.setVisible(true);
            //btnEditar.setVisible(true);
        }
        if (desc.equals("Tarjeta de regalo")) {
            rbRegalo.setSelected(true);
            rbCredito.setDisable(true);
            tip = 2;
        } else {
            rbCredito.setSelected(true);
            rbRegalo.setDisable(true);
        }
        // txtDatosTarjeta.setDisable(true);
        id = c.getId();


    }

    private void showNewPasswordStage() {

        Stage stage = new Stage();
        stage.setTitle("New Password");
        stage.setResizable(false);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/newPassword.fxml"));
        ControllerNewPassword controller = new ControllerNewPassword();

        try {
            controller = loader.getController();
            //controller.getPassword();
            Parent parent = loader.load();
            loader.setController(controller);

            Scene scene = new Scene(parent, 500, 250);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void setContent() {
        //index.getChildren().add(parent);
        index.getChildren().removeAll();
        index.getChildren().clear();
        index.getChildren().add(parent);
    }


    private void confirmacion(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Actualización");
        alert.setHeaderText("Exito");
        alert.setContentText("Operación relizada con exito");
        alert.showAndWait();
    }

    private void insert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Administración");
        alert.setHeaderText("Administración de la Base de Datos");
        alert.setContentText("Selecciona lo que desees insertar");

        ButtonType buttonTypeOne = new ButtonType("App");
        ButtonType buttonTypeTwo = new ButtonType("Vendedor");
        ButtonType buttonTypeThree = new ButtonType("Categoría");
        ButtonType buttonTypeFour = new ButtonType("Idioma");
        ButtonType buttonTypeFive = new ButtonType("País");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree,buttonTypeFour,buttonTypeFive,buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/detalles.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            Detalles detalles = loader.getController();
            detalles.setInit("/sample/recursos/add.png","2",0,"","","",
                        "","","","","","","","","",null,true,
                                "");
            parent = loader.getRoot();
            setContent();
        } else if (result.get() == buttonTypeTwo) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/insertarVendedor.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            InsertarVendedor insertarVendedor = loader.getController();
            insertarVendedor.initInsert("Vendedor");
            parent = loader.getRoot();
            setContent();
        } else if (result.get() == buttonTypeThree) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/insertarVendedor.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            InsertarVendedor insertarVendedor = loader.getController();
            insertarVendedor.initInsert("Categoria");
            parent = loader.getRoot();
            setContent();
        } else if (result.get() == buttonTypeFour) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/insertarVendedor.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            InsertarVendedor insertarVendedor = loader.getController();
            insertarVendedor.initInsert("Idioma");
            parent = loader.getRoot();
            setContent();
            // ... user chose CANCEL or closed the dialog
        } else if(result.get() == buttonTypeFive){
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/insertarVendedor.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            InsertarVendedor insertarVendedor = loader.getController();
            insertarVendedor.initInsert("Pais");
            parent = loader.getRoot();
            setContent();
        }
    }
}