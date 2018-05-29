package sample.controllers;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.App;
import sample.InfoUser;
import sample.MySQL;
import sample.Persistencia;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ModeloCompras implements Initializable {
    private Popup popup;
    private String id;
    private InfoUser infoUser;
    private App app;

    @FXML
    ImageView imageView;

    @FXML
    Label labelNombre,labelFecha;

    @FXML
    Button btnVer,btnBaja;

    @FXML
    GridPane modeloCompras;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUser();
        /*labelNombre.setPadding(new Insets(20,0,0,0));
        labelFecha.setPadding(new Insets(20,0,0,0));
        btnVer.setPadding(new Insets(20,0,0,0));*/
    }

    @FXML
    public void eliminarCarrito(){
        ResultSet rs = null;
        Connection connection = MySQL.getConnection();
        try {
            String query = "delete from Carrito where idApp = "+"'"+app.getIdApp()+"'"+" and "+" idUsuario= '"+infoUser.getIdUser()+"'";
            System.out.println(query);
            Statement st = connection.createStatement();
            st.execute(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Se ha eliminado del carrito");
            alert.setContentText("Solo basta regrescar la pantalla, dando click en carrito");

            alert.showAndWait();

        }
        catch (Exception e){

        }
    }

    @FXML
    public void detallar(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fxml/detalles.fxml"));
        try {
            loader.load();
        } catch (Exception e) {

        }
        Detalles detallar = loader.getController();
        if(infoUser != null) {
            detallar.setInit(app.getIcono(),infoUser.getTipo(), app.getPromedio(), app.getVendedor(), String.valueOf(app.getIdApp()),
                    app.getNombre(), app.getCompatibilidad(), String.valueOf(app.getPrecio()), app.getCategoria(),
                    String.valueOf(app.getTamanio()), app.getPais(), app.getVersion(), app.getIdioma(), app.getDescripcion(),
                    app.getCaracteristicas(), app.getCapturas(), infoUser.isLog(),String.valueOf(infoUser.getIdUser()));
        }
        else {
            detallar.setInit(app.getIcono(), "0", app.getPromedio(), app.getVendedor(), String.valueOf(app.getIdApp()),
                    app.getNombre(), app.getCompatibilidad(), String.valueOf(app.getPrecio()), app.getCategoria(),
                    String.valueOf(app.getTamanio()), app.getPais(), app.getVersion(), app.getIdioma(), app.getDescripcion(),
                    app.getCaracteristicas(), app.getCapturas(), false, "");
        }
        Parent root = loader.getRoot();
        /*Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("/sample/css/estilos.css").toExternalForm());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        //primaryStage.setResizable(false);
        primaryStage.show();
        closeStage();*/

        Stage stage = (Stage)imageView.getScene().getWindow();
        stage.setOpacity(0.5);

        Button button = new Button();
        MaterialDesignIconView iconView = new MaterialDesignIconView(MaterialDesignIcon.CLOSE_CIRCLE);
        iconView.setFill(Color.RED);
        iconView.setSize("40");
        button.setGraphic(iconView);
        button.setBackground(Background.EMPTY);
        button.setPadding(new Insets(10,0,0,10));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setOpacity(1);
                popup.hide();
            }
        });

        popup = new Popup();
        popup.getContent().add(root);
        popup.getContent().add(button);
        //popup.setHeight(800);
        popup.show(stage);
    }

    private void closeStage() {
        Stage stage = (Stage)imageView.getScene().getWindow();
        stage.close();
    }

    public void setData(String url,String name, String date, String id){
        Image image = new Image(url);
        imageView.setImage(image);
        labelNombre.setText(name);
        labelFecha.setText(date);
        this.id = id;
    }

    public void isCarrito(){
        btnBaja.setVisible(true);
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


    public void setApp(App datos){
        app = datos;
    }
}
