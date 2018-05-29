package sample.dao;

import javafx.scene.control.Alert;
import sample.Carrito;
import sample.InfoUser;
import sample.MySQL;
import sample.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CarritoDao {
    InfoUser infoUser;
    Connection connection;

    public CarritoDao(){
        connection = MySQL.getConnection();
        Persistencia persistencia = new Persistencia();
        try {
            infoUser = persistencia.checarUsuario();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Carrito> getCarrito(String idUser){
        ArrayList<Carrito> carrito = new ArrayList<>();
        ResultSet rs;
        try{
            String query = "select * from Carrito where idUsuario = "+"'"+idUser+"'";
            Statement st = connection.createStatement();
            //System.out.println(query);
            rs = st.executeQuery(query);
            Carrito apps = null;
            while(rs.next()){
                apps = new Carrito();
                apps.setIdApp(rs.getInt("idApp"));
                apps.setIdUser(rs.getInt("idUsuario"));
                carrito.add(apps);
            }
        }
        catch (Exception e){

        }
        return carrito;
    }

    public void insertCompras(ArrayList<Carrito> carrito){
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {
            fecha = formateador.parse(formateador.format(ahora));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(fecha.getTime());
        try {
            for(Carrito aux : carrito) {
                String query = "insert into Compras "
                        + " values (?, ?, ?)";
                PreparedStatement st = connection.prepareStatement(query);
                st.setInt(1, aux.getIdUser());
                st.setInt(2, aux.getIdApp());
                st.setDate(3,sql);
                st.execute();
            }
            //data.add(usuario);
        } catch (Exception e) {
        }
        cleanCarrito(carrito);
    }

    private void cleanCarrito(ArrayList<Carrito> carrito){
        ResultSet rs = null;
        Connection connection = MySQL.getConnection();
        try {
            for (Carrito aux : carrito) {
                String query = "delete from Carrito where idApp = " + "'" + aux.getIdApp() + "'" + " and " + " idUsuario= '" + infoUser.getIdUser() + "'";
                System.out.println(query);
                Statement st = connection.createStatement();
                st.execute(query);
            }

        }
        catch (Exception e){

        }
    }
}
