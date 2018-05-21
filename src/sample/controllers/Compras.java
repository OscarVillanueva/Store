package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Compras implements Initializable {
    private String id;

    @FXML
    VBox vboxCompras;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initCompras();
    }

    public void initCompras(){
        VBox temp = new VBox(10);
        temp.setAlignment(Pos.CENTER);
        ScrollPane centro = new ScrollPane();
        centro.getStylesheets().add(getClass().getResource("/sample/css/estilos.css").toExternalForm());
        centro.setMaxSize(1200,600);
        centro.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Separator separator;
        for(int i=0;i<13;i++) {
            separator = new Separator(Orientation.HORIZONTAL);
            separator.setMaxWidth(1220);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxml/modeloCompras.fxml"));
            try {
                loader.load();
            } catch (Exception e) {

            }
            //Se llena con los datos de la consulta con el id
            ModeloCompras modeloCompras = loader.getController();
            modeloCompras.setData("/sample/recursos/Aquarelo.png","Ferrari2","Ayer","08");
            Parent compra = loader.getRoot();
            temp.getChildren().addAll(separator,compra);
        }
        centro.setContent(temp);
        vboxCompras.getChildren().addAll(centro);
    }

    public void setId(String id){
        this.id = id;
        initCompras();
    }
}

