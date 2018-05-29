package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import sample.App;
import sample.Carrito;
import sample.MySQL;
import sample.dao.AppDao;
import sample.dao.CarritoDao;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Compras implements Initializable {
    private String id;
    private String idUser;

    @FXML
    VBox vboxCompras;

    @FXML
    Label labelTitulo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initCompras();
    }

    public void initCompras(ArrayList<App> apps){
        VBox temp = new VBox(10);
        temp.setAlignment(Pos.CENTER);
        ScrollPane centro = new ScrollPane();
        centro.getStylesheets().add(getClass().getResource("/sample/css/estilos.css").toExternalForm());
        centro.setMaxSize(1200,600);
        centro.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Separator separator;
        for(int i=0;i<apps.size();i++) {
            App detaApp = apps.get(i);
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
            if(id.equals("Carrito")){
                modeloCompras.isCarrito();
            }
            modeloCompras.setData(detaApp.getIcono(), detaApp.getNombre(), detaApp.getFecha(),String.valueOf(detaApp.getIdApp()));
            modeloCompras.setApp(detaApp);
            Parent compra = loader.getRoot();
            temp.getChildren().addAll(separator,compra);
        }
        centro.setContent(temp);
        vboxCompras.getChildren().addAll(centro);
        if(id.equals("Carrito")){
            Button button = new Button("Comprar");
            button.getStyleClass().add("compra");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    CarritoDao carritoDao = new CarritoDao();
                    carritoDao.insertCompras(carritoDao.getCarrito(idUser));
                    confirmacion();
                }
            });
            vboxCompras.getChildren().add(button);
        }
    }

    public void setId(String id,String idUser){
        this.id = id;
        this.idUser = idUser;
        ArrayList<App> apps = new ArrayList<>();
        AppDao appDao = new AppDao(MySQL.getConnection());
        switch (id){
            case "Carrito":
                apps = appDao.getCarrito(idUser);
                break;
            case "Compras":
                apps = appDao.getCompras(idUser);
                break;
        }
        labelTitulo.setText(id);
        initCompras(apps);
    }

    public void confirmacion(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra exitosa");
        alert.setHeaderText(null);
        alert.setContentText("Puedes revisar tu compra en la pestaña de compras");

        alert.showAndWait();
    }
}

