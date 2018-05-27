package sample.dao;

import sample.Cuenta;
import sample.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CuentaDAO {
    Connection conn;

    public CuentaDAO(Connection conn) {
        this.conn = conn;
    }

    public Cuenta cuenta(String id)
    {
        ResultSet rs = null;
        Cuenta er = null;
        try {
            String query ="select Usuarios.idUsuario as id,admin,nombre, correo,passwd, fechaNac, direccion, telefono,descripcion, datos from Usuarios inner join formaPago on formaPago.idUsuario=Usuarios.idUsuario inner join tipoPago on formaPago.tipoPago=tipoPago.tipoPago where Usuarios.idUsuario='"+id+"'";

            System.out.println(query);
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Cuenta p = null;
            while (rs.next()) {
                er = new Cuenta(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("passwd"),
                        rs.getDate("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("descripcion"),
                        rs.getString("datos")  ,
                        rs.getString("admin"));


            }



            //data.add(usuario);
            //System.out.println(""+p.getNombre().toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return er;
    }
    public  int id(String correo) {
        ResultSet rs = null;
        Usuario er = null;
        try {
            String query = "SELECT * from Usuarios WHERE correo= '"+correo+"'";
            System.out.println(query);
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Usuario p = null;
            while (rs.next()) {
                p = new Usuario(

                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("passwd"),
                        rs.getDate("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("admin"));

            }

            //data.add(usuario);
            System.out.println(""+p.getNombre().toString());
            return p.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        return 0;
    }
}
