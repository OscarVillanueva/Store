package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import sample.Cuenta;
import sample.MySQL;
import sample.dao.CuentaDAO;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Barra implements Initializable {
    private boolean isLog;
    private Parent parent;
    private FXMLLoader loader;
    private String idUser;
    private String tipo;

    @FXML
    BorderPane index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home();
        isLog = false;
    }

    @FXML
    public void home(){
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

    @FXML
    public void categorias(){
        String temp = pregunta("Categoria a buscar");
        boolean isTemp = !temp.trim().isEmpty() || temp.isEmpty() == false;
        if(isTemp) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/categoria.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            Categoria categoria = loader.getController();
            categoria.setId(temp);
            parent = loader.getRoot();
            setContent();
        }
        else
            ocurio("Oops, ocurrio un error");
    }

    @FXML
    public void buscar(){
        String temp = pregunta("App a buscar");
        boolean isTemp = !temp.trim().isEmpty() || temp.isEmpty() == false;
        if(isTemp){
            //Realizamos la consulta que me regrese la info de la app para mandar llamar el detalle
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/detalles.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            Detalles detalles = loader.getController();
            //detalles.setInit(); aqui mando todos los datos de la app a buscar
            parent = loader.getRoot();
            setContent();
        }
        else
            ocurio("Oops, ocurrio un error");
    }

    @FXML
    public void topApp(){
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fxml/categoria.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        Categoria categoria = loader.getController();
        categoria.setId("Top Apps");
        parent = loader.getRoot();
        setContent();
    }

    @FXML
    public void compras(){
        if(isLog) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/compras.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            Compras compras = loader.getController();
            compras.setId(idUser);
            parent = loader.getRoot();
            setContent();
        }
        else
            ocurio("Debes estar loggeado para ver las compras");
    }

    @FXML
    public void login(){

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fxml/Login.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        parent = loader.getRoot();
        setContent();
        isLog = true;
        //idUser = al usuario;
        //tipo = tipo de usuario que es
    }

    @FXML
    public void registrar(){
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fxml/Registro.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        parent = loader.getRoot();
        setContent();
        isLog = true;
        //idUser = al usuario;
        //tipo = tipo de usuario
    }

    @FXML
    public void cuenta(){
        if(isLog){
            //modificar para mandar llamar las pantallas de mariana
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/Cuenta.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            ControllerCuenta controllerCuenta=loader.getController();
            controllerCuenta.recibecuenta(getCuenta());
            parent = loader.getRoot();
            setContent();
        }
        else
            ocurio("Destar loggeado para ver tu cuenta");
    }

    private void setContent(){
        //index.getChildren().add(parent);
        index.setCenter(parent);
    }

    private String pregunta(String mensaje){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar");
        dialog.setHeaderText(mensaje);
        dialog.setContentText("Introduce lo que deseas buscar: ");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        else
            return "";
    }


    private void ocurio(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Esto es vergonzoso");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private Cuenta getCuenta()
    {
        CuentaDAO cuentaDAO=new CuentaDAO(MySQL.getConnection());

        return cuentaDAO.cuenta("2");

    }
}
