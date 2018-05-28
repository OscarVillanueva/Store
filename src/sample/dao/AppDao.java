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
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais " +
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
                app.setNombre(rs.getString("AppName"));
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPais(rs.getString("pais"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
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
                    "v.nombre as vendedor,c.nombre as categoria,p.nombre as pais " +
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
                app.setDescripcion(rs.getString("descripcion"));
                app.setPrecio(rs.getDouble("precio"));
                app.setCaracteristicas(rs.getString("caracteristicas"));
                app.setVersion(rs.getString("version"));
                app.setTamanio(rs.getDouble("tamaño"));
                app.setVendedor(rs.getString("vendedor"));
                app.setCategoria(rs.getString("categoria"));
                app.setPais(rs.getString("pais"));
                app.setIdioma(getIdioma(String.valueOf(app.getIdApp())));
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

    private String ruta(String url){
        url = url.substring(4);
        return url;
    }
}
