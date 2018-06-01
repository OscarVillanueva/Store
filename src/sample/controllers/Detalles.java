package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import org.controlsfx.control.Rating;
import sample.App;
import sample.MySQL;
import sample.dao.AppDao;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Detalles implements Initializable {
    private String guia;
    private String path;
    private String id;
    private ArrayList<String> urls = new ArrayList<>();
    private int cont;
    private boolean isLog;
    private boolean bandera;
    private boolean isCaptura;
    private String idUser;
    private String icono = "/src";
    private ArrayList<String> caps;
    private int veces;

    @FXML
    ImageView imageView;

    @FXML
    ScrollPane scroll;

    @FXML
    TextField txtVendedor, txtComp, txtPrecio, txtCategoria, txtTamanio, txtPais;

    @FXML
    TextField txtVersion, txtIdioma, txtNombre;

    @FXML
    ComboBox comboVendedor,comboCategoria,comboPais,comboIdioma;

    @FXML
    Button btnCambiar,btnValorar,btnGuardar,btnEdit,btnDelete,btnComprar,btnAdd,btnElim,btnGuardarVal;

    @FXML
    Label labelTitulo,labelPromedio;

    @FXML
    TextArea txtADesc,txtAreaUser,txtCaract;

    @FXML
    ImageView imageCap;

    @FXML
    Rating ratingBar;

    @FXML
    HBox botones;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        caps = new ArrayList<>();
        caps.add("/src/sample/recursos/Aquarelo1.png");
        veces = 0;
        isCaptura =false;
        scroll.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });

        /*initComponents();
        setType();
        getRootPath();*/
    }

    private void initComponents(){
        GridPane.setMargin(txtVendedor,new Insets(0,0,0,180));
        GridPane.setMargin(txtComp,new Insets(0,0,0,180));
        GridPane.setMargin(botones,new Insets(0,0,0,120));
        GridPane.setMargin(txtPrecio,new Insets(0,0,0,180));
        GridPane.setMargin(btnValorar,new Insets(0,0,0,180));
        GridPane.setMargin(btnGuardarVal,new Insets(0,0,0,180));
        GridPane.setMargin(btnGuardar,new Insets(0,0,0,180));
        btnComprar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if((guia.equals("2")||guia.equals("1"))&&btnComprar.getText().equals("Cambiar")){
                    icono = "/src";
                    isCaptura = false;
                    String file = chooser();
                    icono = icono + file;
                    //imageView.setImage(new Image(file));
                }
                else{
                    if(isLog) {
                        insertIntoCarrito();
                        btnComprar.setText("App en el carrito");
                        btnComprar.setDisable(true);
                    }
                    else{
                        ocurio("No puedes comprar hasta que te inicies sesion");
                    }
                }
            }
        });

    }

    public void setInit(String icono,String behaivior, double pop,String seller, String id, String nombre,
                        String compat,String price, String category, String tam,String from,
                        String ver,String lang,String desc,String others,ArrayList url,boolean log,String idUser){
        this.idUser = idUser;
        this.id = id;
        setIcon(icono);
        guia = behaivior;
        txtVendedor.setText(seller);
        txtComp.setText(compat);
        txtPrecio.setText(price);
        btnComprar.setText(price);
        txtCategoria.setText(category);
        txtTamanio.setText(tam);
        txtPais.setText(from);
        txtVersion.setText(ver);
        txtIdioma.setText(lang);
        txtADesc.setText(desc);
        txtCaract.setText(others);
        btnCambiar.setVisible(false);
        btnAdd.setVisible(false);
        btnElim.setVisible(false);
        ratingBar.setRating(pop);
        labelPromedio.setText(String.valueOf(pop));
        isLog = log;
        labelTitulo.setText(nombre);
        txtNombre.setText(nombre);
        if(url==null||url.size()==0) {
            cont = -1;
            bandera = false;
        }
        else {
            bandera = true;
            cont = 0;
            urls = url;
            setFiles();
            imageCap.setImage(new Image(urls.get(cont)));
        }
        initComponents();
        setType();
        getRootPath();
    }

    private void setType(){
        switch (guia){
            case "1":
                isVisible(true);
                btnValorar.setVisible(false);
                break;
            case "0":
                isVisible(false);
                break;
            case "2":
                isVisible(true);
                activate();
                break;
        }
    }

    private void isVisible(boolean bandera){
        btnDelete.setVisible(bandera);
        btnEdit.setVisible(bandera);
        btnCambiar.setVisible(bandera);
        btnAdd.setVisible(bandera);
        btnElim.setVisible(bandera);
    }

    @FXML
    public void initEdit(){
        activate();
    }

    @FXML
    public void initDelete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Elimar");
        alert.setHeaderText("Eliminar aplicación");
        alert.setContentText("¿Estás seguro de eliminar la aplicación?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // consulta delete con la variable string delete
            confimation();
        }
    }

    @FXML
    public void update(){
        boolean bandera = false;
        String mensaje;
        if(guia.equals("add"))
            mensaje = "¿Estás seguro de agregar la aplicación?";
        else
            mensaje = "¿Estás seguro de agregar la aplicación?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Actulización de datos");
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            App app = new App();
            String descrip = txtADesc.getText();
            double prom = ratingBar.getRating();
            int cat = idCategoria(comboCategoria.getValue().toString());
            int devep = idVendedor(comboVendedor.getValue().toString());
            String tam = txtTamanio.getText();
            int from = idPais(comboPais.getValue().toString());
            String comp = txtComp.getText();
            String vers = txtVersion.getText();
            String carac = txtCaract.getText();
            String price = txtPrecio.getText();
            int lang = idIdioma(comboIdioma.getValue().toString());
            String nombre = txtNombre.getText();
            app.setNombre(nombre);
            app.setIcono(icono);
            app.setDescripcion(descrip);
            app.setPromedio(prom);
            app.setCategoria(String.valueOf(cat));
            app.setVendedor(String.valueOf(devep));
            app.setTamanio(Double.parseDouble(tam));
            app.setPais(String.valueOf(from));
            app.setCompatibilidad(comp);
            app.setVersion(vers);
            app.setCaracteristicas(carac);
            app.setPrecio(Double.parseDouble(price));
            app.setIdioma(String.valueOf(lang));
            app.setCapturas(caps);
            //Aqui va la consulta update
            if(!bandera) {
                AppDao appDao = new AppDao(MySQL.getConnection());
                appDao.insertApp(app);
                //System.load(path);
            }
            else{
                //consulta update
            }

            confimation();
        }
    }

    private void activate(){
        labelTitulo.setVisible(false);
        txtNombre.setVisible(true);
        txtVendedor.setVisible(false);
        comboVendedor.setVisible(true);
        comboVendedor.setItems(getVendedores());
        comboVendedor.setValue("Apple Inc.");
        txtComp.setEditable(true);
        txtPrecio.setEditable(true);
        comboCategoria.setVisible(true);
        comboCategoria.setItems(getCategorias());
        comboCategoria.setValue("Juegos");
        txtCategoria.setVisible(false);
        txtTamanio.setEditable(true);
        comboPais.setVisible(true);
        comboPais.setItems(getPais());
        comboPais.setValue("México");
        txtVersion.setEditable(true);
        comboIdioma.setVisible(true);
        comboIdioma.setItems(getIdiomas());
        comboIdioma.setValue("Todos los Idiomas");
        txtIdioma.setVisible(false);
        txtADesc.setEditable(true);
        txtCaract.setEditable(true);
        btnCambiar.setVisible(true);
        btnAdd.setVisible(true);
        btnElim.setVisible(true);
        btnComprar.setText("Cambiar");
        btnGuardar.setVisible(true);
        ratingBar.setDisable(false);
    }

    private void confimation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Listo!");
        alert.setHeaderText(null);
        alert.setContentText("La operación se ejecuto correctamente");
        alert.showAndWait();
    }

    private void errorNotification(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("La operación no se logro");
        alert.showAndWait();
    }

    private String chooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setTitle("Open Resource File");
        File selectFile = fileChooser.showOpenDialog(null);
        if(selectFile!=null){
            Path movefrom = FileSystems.getDefault().getPath(selectFile.getPath());
            //System.out.println(movefrom);
            //Path target = FileSystems.getDefault().getPath("/sample/recursos/"+selectFile.getName());
            //Path targetDir = FileSystems.getDefault().getPath("/Users/oscarvillanuva/IdeaProjects/TableView/src/sample/recursos/"+selectFile.getName());
            Path targetDir = FileSystems.getDefault().getPath(path+selectFile.getName());
            //System.out.println(targetDir);
            try{
                Files.copy(movefrom,targetDir,StandardCopyOption.REPLACE_EXISTING);
                confimation();
                //System.load(targetDir.toString());
                //System.out.println("/src/sample/recursos/"+selectFile.getName());

            }catch (IOException e){}
        }
        else{
            errorNotification();
        }
        String temp = "/sample/recursos/"+selectFile.getName();
        Image imageTemp = new Image(selectFile.toURI().toString());
        if(!isCaptura){
            imageView.setImage(imageTemp);
        }
        else {
            imageCap.setImage(imageTemp);
            if(veces == 0) {
                veces = veces + 1;
                caps = null;
                caps = new ArrayList<>();
            }
            urls.add(selectFile.toURI().toString());
            caps.add("/src" + temp);
        }
        return temp;
    }

    private void getRootPath(){
        Path current = Paths.get("");
        path = current.toAbsolutePath().toString() + "/src/sample/recursos/";
        //System.out.println(path);
    }

    @FXML
    public void cambiar(){
        int i = -1;
        do{
            i = i + 1;
            //System.out.println(urls.get(i));
            //System.out.println(urls.get(cont));
        }
        while (!urls.get(i).equals(urls.get(cont)));
        String file = chooser();
        urls.set(i,file);
        //imageCap.setImage(new Image(file));
    }

    @FXML
    public void addImage(){
        bandera = true;
        isCaptura = true;
        String file = chooser();
        /*urls.add(file);
        if(bandera){
            cont = urls.size();
        }
        else{
            cont = cont + 1;
        }*/
    }

    @FXML
    public void siguiente(){
        if(cont<(urls.size()-1)){
            cont = cont + 1;
            imageCap.setImage(new Image(urls.get(cont)));
        }
        else{
            cont = 0;
            imageCap.setImage(new Image(urls.get(cont)));
        }
    }

    @FXML
    public void anterior(){
        if(cont>0){
            cont = cont - 1;
            imageCap.setImage(new Image(urls.get(cont)));
        }
        else{
            cont = urls.size() - 1;
            imageCap.setImage(new Image(urls.get(cont)));
        }
    }

    @FXML
    public void deleteImage(){
        int rec = cont;
        int ant = rec;
        while (rec<urls.size()){
            ant = rec;
            rec = rec + 1;
            if(rec<urls.size()){
                urls.set(ant,urls.get(rec));
            }
            else {
                urls.set(ant,"");
            }
        }
        imageCap.setImage(new Image(urls.get(cont)));
    }

    @FXML
    public void valorar(){
        if(isLog) {
            ratingBar.setDisable(false);
            btnGuardarVal.setVisible(true);
            btnValorar.setVisible(false);
            txtAreaUser.setEditable(true);
        }
        else
            ocurio("Necesitas estar logeado para valorar la app");
    }

    @FXML
    public void guardarVal(){
        //Aquí va la consulta para guardar la valoración
        double rank = ratingBar.getRating();
        if(rank>0){
            String resenia = txtAreaUser.getText();
            //se hace la consulta para guardar la resenia
            ratingBar.setDisable(true);
            btnGuardarVal.setVisible(false);
            btnValorar.setVisible(true);
            txtAreaUser.setEditable(false);
            txtAreaUser.setText("");
        }
    }

    private void ocurio(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Esto es vergonzoso");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void insertIntoCarrito(){
        Connection conn = MySQL.getConnection();
        try {
            String query = "insert into Carrito "
                    + " values (?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,idUser);
            st.setString(2,id);


            st.execute();
            confimation();
            //data.add(usuario);
        } catch (Exception e) {
            ocurio("Ocurrio un error");
        }
    }

    private ObservableList<String> getCategorias(){
        Connection connection = MySQL.getConnection();
        String cap = "";
        ObservableList<String> ids =  FXCollections.observableArrayList();
        ResultSet rs;
        try{
            String query = "select nombre from Categoria order by nombre";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                cap = rs.getString("nombre");
                ids.add(cap);
            }
        }
        catch (Exception e){

        }
        return ids;
    }

    private int idCategoria(String categoria){
        Connection connection = MySQL.getConnection();
        int id = 0;
        ResultSet rs;
        try{
            String query = "select idCategoria from Categoria where nombre = "+"'"+categoria+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("idCategoria");
            }
        }
        catch (Exception e){

        }
        return id;
    }

    private ObservableList<String> getVendedores(){
        Connection connection = MySQL.getConnection();
        String cap = "";
        ObservableList<String> ids =  FXCollections.observableArrayList();
        ResultSet rs;
        try{
            String query = "select nombre from Vendedores order by nombre";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                cap = rs.getString("nombre");
                ids.add(cap);
            }
        }
        catch (Exception e){

        }
        return ids;
    }

    private int idVendedor(String vendedor){
        Connection connection = MySQL.getConnection();
        int id = 0;
        ResultSet rs;
        try{
            String query = "select idVendedor from Vendedores where nombre = "+"'"+vendedor+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("idVendedor");
            }
        }
        catch (Exception e){

        }
        return id;
    }

    private ObservableList<String> getIdiomas(){
        Connection connection = MySQL.getConnection();
        String cap = "";
        ObservableList<String> ids =  FXCollections.observableArrayList();
        ResultSet rs;
        try{
            String query = "select descripcion from Idioma order by descripcion";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                cap = rs.getString("descripcion");
                ids.add(cap);
            }
        }
        catch (Exception e){

        }
        return ids;
    }

    private int idIdioma(String categoria){
        Connection connection = MySQL.getConnection();
        int id = 0;
        ResultSet rs;
        try{
            String query = "select idIdioma from Idioma where descripcion = "+"'"+categoria+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("idIdioma");
            }
        }
        catch (Exception e){

        }
        return id;
    }

    private ObservableList<String> getPais(){
        Connection connection = MySQL.getConnection();
        String cap = "";
        ObservableList<String> ids =  FXCollections.observableArrayList();
        ResultSet rs;
        try{
            String query = "select nombre from Pais order by nombre";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                cap = rs.getString("nombre");
                ids.add(cap);
            }
        }
        catch (Exception e){

        }
        return ids;
    }

    private int idPais(String categoria){
        Connection connection = MySQL.getConnection();
        int id = 0;
        ResultSet rs;
        try{
            String query = "select idPais from Pais where nombre = "+"'"+categoria+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("idPais");
            }
        }
        catch (Exception e){

        }
        return id;
    }

    private void setFiles(){
        getRootPath();
        int i;
        for(i=0;i<urls.size();i++){
            String aux = urls.get(i);
            aux = aux.substring(17);
            File file = new File(path+aux);
            urls.set(i,file.toURI().toString());
        }
    }

    private void setIcon(String url){
        getRootPath();
        url = url.substring(17);
        File file = new File(path+url);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
}

