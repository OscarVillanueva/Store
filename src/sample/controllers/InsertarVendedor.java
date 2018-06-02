package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertarVendedor {
    private String guia;

    @FXML
    Label labelInsertar;

    @FXML
    TextField textNombre;

    @FXML
    NumberTextField textComision;

    public void initInsert(String que){
        guia = que;
        labelInsertar.setText(labelInsertar.getText()+guia);
        setPrompt();
    }

    public void setPrompt(){
        switch (guia){
            case "Vendedor":
                textNombre.setPromptText("Microsoft");
                break;
            case "Categoria":
                textNombre.setPromptText("Juegos");
                textComision.setVisible(false);
                break;
            case "Idioma":
                textNombre.setPromptText("Ingles");
                textComision.setVisible(false);
                break;
            case "Pais":
                textNombre.setPromptText("México");
                textComision.setVisible(false);
                break;
        }
    }

    @FXML
    public void insertar(){
        switch (guia){
            case "Vendedor":
                boolean isNombre = textNombre.getText().isEmpty() == false || textNombre.getText().trim().isEmpty() == false;
                boolean isComision = textComision.getText().isEmpty() == false || textComision.getText().trim().isEmpty() == false;
                if(isNombre && isComision)
                    insertVendedores();
                else
                    error();
                break;
            case "Categoria":
                isNombre = textNombre.getText().isEmpty() == false || textNombre.getText().trim().isEmpty() == false;
                if(isNombre)
                    insertCategoria();
                else
                    error();
                break;
            case "Pais":
                isNombre = textNombre.getText().isEmpty() == false || textNombre.getText().trim().isEmpty() == false;
                if(isNombre)
                    insertPais();
                else
                    error();
                break;
            case "Idioma":
                isNombre = textNombre.getText().isEmpty() == false || textNombre.getText().trim().isEmpty() == false;
                if(isNombre)
                    insertIdioma();
                else
                    error();
                break;
        }
    }

    public void insertCategoria(){
        Connection connection = MySQL.getConnection();
        ResultSet rs;
        String query = "insert into Categoria (nombre)"
                + " values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,textNombre.getText());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        correcto();
    }

    public void insertVendedores(){
        Connection connection = MySQL.getConnection();
        ResultSet rs;
        //Agregar en el query la comision
        String query = "insert into Vendedores (nombre)"
                + " values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,textNombre.getText());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        correcto();
    }

    public void insertIdioma(){
        Connection connection = MySQL.getConnection();
        ResultSet rs;
        //Agregar en el query la comision
        String query = "insert into Idioma (descripcion)"
                + " values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,textNombre.getText());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        correcto();
    }

    public void insertPais(){
        Connection connection = MySQL.getConnection();
        ResultSet rs;
        //Agregar en el query la comision
        String query = "insert into Pais (nombre)"
                + " values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,textNombre.getText());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        correcto();
    }

    public void error(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Precaución");
        alert.setHeaderText("Erro al insertar");
        alert.setContentText("No escribiste nada");

        alert.showAndWait();
    }

    public void correcto(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("Se insertó correctamente");
        alert.showAndWait();
        textNombre.setText("");
        textComision.setText("");
    }
}
