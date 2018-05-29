package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import sample.Cuenta;
import sample.InfoUser;
import sample.MySQL;
import sample.Persistencia;
import sample.dao.CuentaDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Barra implements Initializable {
    private Parent parent;
    private FXMLLoader loader;
    private InfoUser infoUser;

    @FXML
    BorderPane index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home();
        Persistencia persistencia = new Persistencia();
        try {
            persistencia.emptyFile("data.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void carrito(){
        setUser();
        if(infoUser!=null) {
            if (infoUser.isLog()) {
                loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/fxml/compras.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {
                    System.out.println(e);
                }
                Compras compras = loader.getController();
                compras.setId("Carrito",String.valueOf(infoUser.getIdUser()));
                parent = loader.getRoot();
                setContent();
            }
        }
        else
            ocurio("Debes estar loggeado para ver las compras");
    }

    @FXML
    public void categorias(){
        String temp = buscarCat();
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
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxml/categoria.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                System.out.println(e);
            }
            Categoria categoria = loader.getController();
            categoria.especificApp(temp);
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
        categoria.setId("Top");
        parent = loader.getRoot();
        setContent();
    }

    @FXML
    public void compras(){
        setUser();
        if(infoUser!=null) {
            if (infoUser.isLog()) {
                loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/fxml/compras.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {
                    System.out.println(e);
                }
                Compras compras = loader.getController();
                compras.setId("Compras",String.valueOf(infoUser.getIdUser()));
                parent = loader.getRoot();
                setContent();
            }
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
        //isLog = true;
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

        //idUser = al usuario;
        //tipo = tipo de usuario
    }

    @FXML
    public void cuenta(){
        setUser();
        if(infoUser!=null) {
            if (infoUser.isLog()) {
                //modificar para mandar llamar las pantallas de mariana
                loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../fxml/Cuenta.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {
                    System.out.println(e);
                }
                ControllerCuenta controllerCuenta = loader.getController();
                controllerCuenta.recibecuenta(getCuenta());
                parent = loader.getRoot();
                setContent();
            }
        }
        else
            ocurio("Debes estar loggeado para ver tu cuenta");
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
        setUser();
        CuentaDAO cuentaDAO=new CuentaDAO(MySQL.getConnection());

        return cuentaDAO.cuenta(String.valueOf(infoUser.getIdUser()));

    }

    private void setUser(){
        Persistencia persistencia = new Persistencia();
        try {
            infoUser = persistencia.checarUsuario();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<String> opciones(){
        Connection connection = MySQL.getConnection();
        List<String> choices = new ArrayList<>();
        ResultSet rs;
        try{
            String query = "select nombre from Categoria";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                choices.add(rs.getString("nombre"));
            }
        }
        catch (Exception e){

        }
        return choices;
    }

    private String buscarCat(){
        List<String> list = opciones();
        ChoiceDialog<String> dialog = new ChoiceDialog<>(list.get(0), list);
        dialog.setTitle("Buscar");
        dialog.setHeaderText("Categorias");
        dialog.setContentText("Elige la categoria que desees buscar: ");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return result.get();
    }
}
