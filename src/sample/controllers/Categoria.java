package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Categoria implements Initializable {
    String[] urls;
    String[] names;
    String[] ids;
    String id;

    @FXML
    Label labelTitulo;

    @FXML
    VBox panelCategoria;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initCategoria();
    }

    public void initCategoria() {
        ScrollPane centro = new ScrollPane();
        centro.getStylesheets().add(getClass().getResource("/sample/css/estilos.css").toExternalForm());
        centro.setMaxSize(1400,800);
        centro.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        GridPane tableApps = new GridPane();
        tableApps.setAlignment(Pos.CENTER);
        tableApps.setHgap(15);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 7; i++) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/fxml/modeloCategoria.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {

                }
                ModeloCategoria modeloCategoria = loader.getController();
                //se llenan con los datos que tengan los arreglos
                modeloCategoria.setData("/sample/recursos/abacus.png", "Ferrari", "");
                Parent app = loader.getRoot();
                GridPane.setHalignment(app,HPos.CENTER);
                tableApps.add(app,i,j);
            }
        }
        centro.setContent(tableApps);
        panelCategoria.getChildren().add(centro);
    }

    public void setId(String id){
        this.id = id;
        labelTitulo.setText(id);
        initCategoria();
    }
}