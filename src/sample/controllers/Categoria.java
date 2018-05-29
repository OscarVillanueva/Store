package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.App;
import sample.MySQL;
import sample.dao.AppDao;

import java.net.URL;
import java.util.ArrayList;
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

    public void initCategoria(ArrayList<App> apps) {
        ScrollPane centro = new ScrollPane();
        centro.getStylesheets().add(getClass().getResource("/sample/css/estilos.css").toExternalForm());
        centro.setMaxSize(1400,800);
        centro.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        GridPane tableApps = new GridPane();
        tableApps.setAlignment(Pos.CENTER);
        tableApps.setHgap(15);
        int contador = 0;
        int j = 0;
        int i = 0;
        int limit = apps.size() / 7;
        limit++;
        for (j = 0; j < limit; j++) {
            //for (i = 0; i < 7; i++) {
                while (contador<apps.size()&&i<7) {
                    App detaApp = apps.get(contador);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/fxml/modeloCategoria.fxml"));
                    try {
                        loader.load();
                    } catch (Exception e) {

                    }
                    ModeloCategoria modeloCategoria = loader.getController();
                    //se llenan con los datos que tengan los arreglos
                    //modeloCategoria.setData("/sample/recursos/abacus.png", "Ferrari", "");
                    modeloCategoria.setData(detaApp.getIcono(), detaApp.getNombre(), String.valueOf(detaApp.getIdApp()));
                    modeloCategoria.setApp(detaApp);
                    Parent app = loader.getRoot();
                    GridPane.setHalignment(app, HPos.CENTER);
                    tableApps.add(app, i, j);
                    i++;
                    if (contador < apps.size())
                        contador++;
                    else
                        break;
                }
                i=0;
           // }
        }
        centro.setContent(tableApps);
        panelCategoria.getChildren().add(centro);
    }

    public void setId(String id){
        this.id = id;
        ArrayList<App> apps = new ArrayList<>();
        AppDao appDao = new AppDao(MySQL.getConnection());
        switch (id){
            case "Algunas Apps":
                apps = appDao.getAll();
                break;
            case "Top":
                apps = appDao.getTopApp();
                break;
            default:
                apps = appDao.getCategoryApp(id);
                break;
        }
        labelTitulo.setText(id);
        initCategoria(apps);
    }

    public void especificApp(String name){
        ArrayList<App> apps = new ArrayList<>();
        AppDao appDao = new AppDao(MySQL.getConnection());
        apps = appDao.getEspecificApp(name);
        if(apps.size()>0) {
            labelTitulo.setText(name);
        }
        else{
            newOpciones();
            apps = appDao.getAll();
        }
        initCategoria(apps);
    }

    private void newOpciones(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText("No hay aplicaciones con el nombre que buscaste");
        alert.setContentText("Pero te mostraremos algunas otras");
        alert.showAndWait();
    }
}