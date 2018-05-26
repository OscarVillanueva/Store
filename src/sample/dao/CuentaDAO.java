package sample.dao;

import sample.Cuenta;

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
            String query ="select Usuarios.idUsuario as id,admin,nombre, correo, fechaNac, direccion, telefono,descripcion, datos from Usuarios inner join formaPago on formaPago.idUsuario=Usuarios.idUsuario inner join tipoPago on formaPago.tipoPago=tipoPago.tipoPago where Usuarios.idUsuario='"+id+"'";

            System.out.println(query);
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Cuenta p = null;
            while (rs.next()) {
                er = new Cuenta(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        //rs.getString("password"),
                        rs.getDate("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("descripcion"),
                        rs.getString("datos")  ,
                rs.getString("admin"));


            }


            //data.add(usuario);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return er;
    }
}
