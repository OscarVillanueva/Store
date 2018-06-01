package sample.dao;

import sample.App;
import sample.Cuenta;

import java.sql.*;
import java.util.ArrayList;

public class AppDao {
    Connection connection;

    public AppDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<App> getAll(){
        ResultSet rs = null;
        ArrayList<App> apps = new ArrayList<>();
        try {
            String query ="Select a.idApp,icono,a.nombre as Appname,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad, "+
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais, " +
                    "(select avg(calificacion) from Comentarios where a.idApp = Comentarios.idApp) as promedio "+
                    "from App a " +
                    "inner join Vendedores v on a.idVendedor = v.idVendedor " +
                    "inner join Categoria c on a.idCategoria = c.idCategoria " +
                    "inner join Pais p on a.idPais = p.idPais limit 21";
            System.out.println(query);
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            App app = null;
            while (rs.next()) {
                app = new App();
                app.setIdApp(rs.getInt("idApp"));
                app.setIcono(ruta(rs.getString("icono")));
                app.setCompatibilidad(rs.getString("compatibilidad"));
                app.setNombre(rs.getString("AppName"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPromedio(rs.getDouble("promedio"));
                app.setPais(rs.getString("pais"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
                app.setCapturas(getCapturas(String.valueOf(app.getIdApp())));
                apps.add(app);
            }
            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return apps;
    }


    public ArrayList<App> getCategoryApp(String category){
        ResultSet rs = null;
        ArrayList<App> apps = new ArrayList<>();
        try {
            String query ="Select a.idApp,icono,a.nombre as Appname,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad, "+
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais, " +
                    "(select avg(calificacion) from Comentarios where a.idApp = Comentarios.idApp) as promedio "+
                    "from App a " +
                    "inner join Vendedores v on a.idVendedor = v.idVendedor " +
                    "inner join Categoria c on a.idCategoria = c.idCategoria " +
                    "inner join Pais p on a.idPais = p.idPais where " +
                    "c.nombre = "+"'"+category+"'"+" limit 21";
            System.out.println(query);
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            App app = null;
            while (rs.next()) {
                app = new App();
                app.setIdApp(rs.getInt("idApp"));
                app.setIcono(ruta(rs.getString("icono")));
                app.setNombre(rs.getString("AppName"));
                app.setCompatibilidad(rs.getString("compatibilidad"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPais(rs.getString("pais"));
                app.setPromedio(rs.getDouble("promedio"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
                app.setCapturas(getCapturas(String.valueOf(app.getIdApp())));
                apps.add(app);
            }
            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return apps;
    }

    public ArrayList<App> getEspecificApp(String name){
        ResultSet rs = null;
        ArrayList<App> apps = new ArrayList<>();
        try {
            String query ="Select a.idApp,icono,a.nombre as Appname,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad, "+
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais, " +
                    "(select avg(calificacion) from Comentarios where a.idApp = Comentarios.idApp) as promedio "+
                    "from App a " +
                    "inner join Vendedores v on a.idVendedor = v.idVendedor " +
                    "inner join Categoria c on a.idCategoria = c.idCategoria " +
                    "inner join Pais p on a.idPais = p.idPais where " +
                    "a.nombre like "+"'%"+name+"%'"+" limit 21";
            System.out.println(query);
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            App app = null;
            while (rs.next()) {
                app = new App();
                app.setIdApp(rs.getInt("idApp"));
                app.setIcono(ruta(rs.getString("icono")));
                app.setNombre(rs.getString("AppName"));
                app.setCompatibilidad(rs.getString("compatibilidad"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPais(rs.getString("pais"));
                app.setPromedio(rs.getDouble("promedio"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
                app.setCapturas(getCapturas(String.valueOf(app.getIdApp())));
                apps.add(app);
            }
            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return apps;
    }

    public ArrayList<App> getTopApp(){
        ResultSet rs = null;
        ArrayList<App> apps = new ArrayList<>();
        try {
            String query ="Select a.idApp,icono,a.nombre as Appname,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad, "+
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais, " +
                    "(select avg(calificacion) from Comentarios where a.idApp = Comentarios.idApp) as promedio "+
                    "from App a " +
                    "inner join Vendedores v on a.idVendedor = v.idVendedor " +
                    "inner join Categoria c on a.idCategoria = c.idCategoria " +
                    "inner join Pais p on a.idPais = p.idPais " +
                    "having promedio >= 3 limit 21";
            System.out.println(query);
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            App app = null;
            while (rs.next()) {
                app = new App();
                app.setIdApp(rs.getInt("idApp"));
                app.setIcono(ruta(rs.getString("icono")));
                app.setCompatibilidad(rs.getString("compatibilidad"));
                app.setNombre(rs.getString("AppName"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPromedio(rs.getDouble("promedio"));
                app.setPais(rs.getString("pais"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
                app.setCapturas(getCapturas(String.valueOf(app.getIdApp())));
                apps.add(app);
            }
            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return apps;
    }

    public ArrayList<App> getCarrito(String idUser){
        ResultSet rs = null;
        ArrayList<App> apps = new ArrayList<>();
        try {
            String query ="Select a.idApp,icono,a.nombre as Appname,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad, "+
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais, " +
                    "(select avg(calificacion) from Comentarios where a.idApp = Comentarios.idApp) as promedio "+
                    "from App a " +
                    "inner join Vendedores v on a.idVendedor = v.idVendedor " +
                    "inner join Categoria c on a.idCategoria = c.idCategoria " +
                    "inner join Pais p on a.idPais = p.idPais " +
                    "where a.idApp in (select idApp from Carrito k where k.idUsuario = "+"'"+idUser+"'"+")";
            System.out.println(query);
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            App app = null;
            while (rs.next()) {
                app = new App();
                app.setIdApp(rs.getInt("idApp"));
                app.setIcono(ruta(rs.getString("icono")));
                app.setCompatibilidad(rs.getString("compatibilidad"));
                app.setNombre(rs.getString("AppName"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPromedio(rs.getDouble("promedio"));
                app.setPais(rs.getString("pais"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
                app.setCapturas(getCapturas(String.valueOf(app.getIdApp())));
                apps.add(app);
            }
            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return apps;
    }

    public ArrayList<App> getCompras(String idUser){
        ResultSet rs = null;
        ArrayList<App> apps = new ArrayList<>();
        try {
            String query ="Select a.idApp,icono,a.nombre as AppName,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad," +
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais, (select avg(calificacion) " +
                    "from Comentarios where a.idApp = Comentarios.idApp) as promedio, k.fechaCompra " +
                    "from App a " +
                    "inner join Vendedores v on a.idVendedor = v.idVendedor " +
                    "inner join Categoria c on a.idCategoria = c.idCategoria " +
                    "inner join Pais p on a.idPais = p.idPais " +
                    "inner join Compras k on a.idApp = k.idApp " +
                    "where a.idApp in (select idApp from Compras k where k.idUsuario = "+"'"+idUser+"'"+")" +
                    "group by a.idApp,icono,a.nombre,a.descripcion,precio,caracteristicas,version,tamaño,compatibilidad," +
                    "v.nombre,c.nombre,p.nombre, k.fechaCompra";
            System.out.println(query);
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
            App app = null;
            while (rs.next()) {
                app = new App();
                app.setIdApp(rs.getInt("idApp"));
                app.setIcono(ruta(rs.getString("icono")));
                app.setCompatibilidad(rs.getString("compatibilidad"));
                app.setNombre(rs.getString("AppName"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPromedio(rs.getDouble("promedio"));
                app.setFecha(String.valueOf(rs.getDate("fechaCompra")));
                app.setPais(rs.getString("pais"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
                app.setCapturas(getCapturas(String.valueOf(app.getIdApp())));
                apps.add(app);
            }
            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return apps;
    }

    public void insertApp(App app){
        ResultSet rs;
        int idApp;
        ArrayList<Integer> idCap;
        String query = "insert into App "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, 0);
            st.setString(2, app.getIcono());
            st.setString(3, app.getNombre());
            st.setString(4, app.getDescripcion());
            st.setDouble(5, app.getPrecio());
            st.setString(6, app.getCaracteristicas());
            st.setString(7, app.getVersion());
            st.setDouble(8, app.getTamanio());
            st.setString(9, app.getCompatibilidad());
            /*int vendedor = getVendedor(app.getVendedor());
            st.setInt(10, vendedor);
            int categoria = getCategoria(app.getCategoria());
            st.setInt(11,categoria);
            int pais = getPais(app.getPais());
            st.setInt(12,pais);
            insertIdiomaApp(app.getIdApp(),getIdiomas(app.getIdioma()));*/
            insertCaps(app.getCapturas());
            idCap = getCapsid(app.getCapturas());
            st.setInt(10,Integer.parseInt(app.getVendedor()));
            st.setInt(11,Integer.parseInt(app.getCategoria()));
            st.setInt(12,Integer.parseInt(app.getPais()));
            st.execute();
            idApp = getidApp(app.getNombre());
            insertAppCapturas(idCap,idApp);
            insertIdiomaApp(idApp,Integer.parseInt(app.getIdioma()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getVendedor(String vendedor){
        int vendeor = 0;
        ResultSet rs;
        try{
            String query = "select idVendedor from Vendedor where nombre= "+"'"+vendedor+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                vendeor = rs.getInt("idVendedor");
            }
        }
        catch (Exception e){

        }

        if(vendeor==0){
            insertVendedor(vendedor);
            vendeor = getVendedor(vendedor);
        }
        return vendeor;
    }

    public int getidApp(String vendedor){
        int vendeor = 0;
        ResultSet rs;
        try{
            String query = "select idApp from App where nombre= "+"'"+vendedor+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                vendeor = rs.getInt("idApp");
            }
        }
        catch (Exception e) {

        }
        return vendeor;
    }

    public void insertIdiomaApp(int app, int idioma){
        ResultSet rs;
        String query = "insert into idiomaApp "
                + " values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1,app);
            st.setInt(2, idioma);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertVendedor(String vendedor){
        ResultSet rs;
        String query = "insert into Vendedores "
                + " values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);

            st.setString(2, vendedor);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int getCategoria(String categoria){
        int vendeor = 0;
        ResultSet rs;
        try{
            String query = "select idCategoria from Categoria where nombre= "+"'"+categoria+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                vendeor = rs.getInt("idCategoria");
            }
        }
        catch (Exception e){

        }

        if(vendeor==0){
            insertCategoria(categoria);
            vendeor = getCategoria(categoria);
        }
        return vendeor;
    }





    public void insertCategoria(String categoria){
        ResultSet rs;
        String query = "insert into Categoria "
                + " values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, 0);
            st.setString(2, categoria);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPais(String pais){
        int vendeor = 0;
        ResultSet rs;
        try{
            String query = "select idPais from Pais where nombre= "+"'"+pais+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                vendeor = rs.getInt("idPais");
            }
        }
        catch (Exception e){

        }

        if(vendeor==0){
            insertPais(pais);
            vendeor = getPais(pais);
        }
        return vendeor;
    }


    public void insertCaps(ArrayList<String> caps){
        ResultSet rs;
        String query = "insert into Capturas (imagen) "
                + " values (?)";
        try {
            for(String aux : caps) {
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, aux);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getCapsid(ArrayList<String> caps){
        int cap = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        ResultSet rs;
        try{
            for(String aux : caps) {
                String query = "select idCaptura from Capturas where imagen= " + "'" + aux + "'";
                Statement st = connection.createStatement();
                //System.out.println(query);
                rs = st.executeQuery(query);
                while (rs.next()) {
                    cap = rs.getInt("idCaptura");
                    ids.add(cap);
                }
            }
        }
        catch (Exception e){

        }
        return ids;
    }


    public void insertAppCapturas(ArrayList<Integer> caps,int idApp){
        ResultSet rs;
        String query = "insert into appCapturas "
                + " values (?, ?)";
        try {
            for(int i : caps) {
                PreparedStatement st = connection.prepareStatement(query);
                st.setInt(1, idApp);
                st.setInt(2, i);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPais(String pais){
        ResultSet rs;
        String query = "insert into Pais "
                + " values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, 0);
            st.setString(2, pais);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdiomas(String idioma){
        int vendeor = 0;
        ResultSet rs;
        try{
            String query = "select idIdioma from Idioma where nombre= "+"'"+idioma+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                vendeor = rs.getInt("idIdioma");
            }
        }
        catch (Exception e){

        }

        if(vendeor==0){
            insertIdioma(idioma);
            vendeor = getIdiomas(idioma);
        }
        return vendeor;
    }



    public void insertIdioma(String vendedor){
        ResultSet rs;
        String query = "insert into idioma "
                + " values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, 0);
            st.setString(2, vendedor);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIdioma(String app){
        String idioma = "";
        ResultSet rs;
        try{
            String query = "select descripcion from Idioma where " +
                    "idIdioma in (select idIdioma from idiomaApp " +
                    "where idApp="+"'"+app+"'"+")";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                idioma = idioma + rs.getString("descripcion");
            }
        }
        catch (Exception e){

        }
        return idioma;
    }

    public ArrayList<String> getCapturas(String app){
        ArrayList<String> capturas = new ArrayList<>();
        ResultSet rs;
        try{
            String query = "select imagen from Capturas where " +
                    "idCaptura in (select idCaptura from appCapturas " +
                    "where idApp="+"'"+app+"'"+")";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                String temp = ruta(rs.getString("imagen"));
                capturas.add(temp);
            }
        }
        catch (Exception e){

        }
        return capturas;
    }

    private String ruta(String url){
        if(url == ""){
            url = "/sample/recursos/iTunesArtwork@2x.png";
        }
        else {
            url = url.substring(4);
        }
        return url;
    }
}
