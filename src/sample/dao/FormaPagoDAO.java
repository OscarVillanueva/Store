package sample.dao;

import sample.FormaPago;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormaPagoDAO {
    Connection conn;
    public FormaPagoDAO(Connection conn) {
        this.conn = conn;
    }
    public Boolean insert(FormaPago formaPago) {
        try {
            String query = "insert into formaPago "
                    + " (idUsuario,tipoPago,datos)"
                    + " values (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,formaPago.getIdUsuario());
            st.setInt(2,formaPago.getTipoPago());
            st.setString(3,formaPago.getDatos());
            System.out.println(query);
            st.execute();
            //data.add(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
