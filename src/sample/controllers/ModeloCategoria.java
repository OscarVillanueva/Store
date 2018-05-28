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

import java.net.URL;
import java.util.ResourceBundle;

public class ModeloCategoria implements Initializable {
    private Popup popup;
    private String id;

    @FXML
    ImageView imageView;

    @FXML
    Label labelNombre;

    @FXML
    Button btnDetalles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        //detallar.setInit(); con los datos de la consulta
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

    public void setData(String url,String name, String id){
        Image image = new Image(url);
        imageView.setImage(image);
        labelNombre.setText(name);
        this.id = id;
    }

    private void closeStage(){
        Stage stage = (Stage)imageView.getScene().getWindow();
        stage.close();
    }
}

