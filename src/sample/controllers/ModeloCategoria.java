package sample.controllers;


import com.sun.jndi.toolkit.url.Uri;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.App;
import sample.InfoUser;
import sample.Persistencia;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ModeloCategoria implements Initializable {
    private Popup popup;
    private App app;
    private InfoUser infoUser;

    @FXML
    ImageView imageView;

    @FXML
    Label labelNombre;

    @FXML
    Button btnDetalles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUser();
        labelNombre.setPadding(new Insets(10,0,0,0));
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
            detallar.setInit(app.getIcono(),"0", app.getPromedio(), app.getVendedor(), String.valueOf(app.getIdApp()),
                    app.getNombre(), app.getCompatibilidad(), String.valueOf(app.getPrecio()), app.getCategoria(),
                    String.valueOf(app.getTamanio()), app.getPais(), app.getVersion(), app.getIdioma(), app.getDescripcion(),
                    app.getCaracteristicas(), app.getCapturas(), false,"");
        }
        Parent root = loader.getRoot();
        /*Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("/sample/css/estilos.css").toExternalForm());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        //primaryStage.setResizable(false);
        primaryStage.show();*/
        //closeStage();

        Stage stage = (Stage)imageView.getScene().getWindow();
        stage.setOpacity(0.5);

        Button button = new Button();
        button.getStyleClass().add("closeButton");
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
        //popup.setHeight(00);
        popup.show(stage);
    }

    public void setData(String url,String name, String id){
         url = url.substring(17);
         Path current = Paths.get("");
         String path = current.toAbsolutePath().toString() + "/src/sample/recursos/";
         File file = new File(path+url);
         Image image = new Image(file.toURI().toString());
         imageView.setImage(image);
         labelNombre.setText(name);
    }

    public void setApp(App datos){
        app = datos;
    }


    private void closeStage(){
        Stage stage = (Stage)imageView.getScene().getWindow();
        stage.close();
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
}

