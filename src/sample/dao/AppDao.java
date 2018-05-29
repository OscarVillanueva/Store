package sample.dao;

import sample.App;
import sample.Cuenta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
