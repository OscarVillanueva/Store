package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public ObservableList<Usuario> fetchAll() {
        ObservableList<Usuario> Usuarios = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Usuarios limit 1000";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Usuario p = null;
            while (rs.next()) {
                p = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        //rs.getString("password"),
                        rs.getDate("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("admin"));
                Usuarios.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Usuarios;
    }

    Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Usuario> findAll() {
        List<Usuario> Usuarios = new ArrayList<Usuario>();
        try {
            String query = "SELECT * FROM Usuarios limit 1000";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Usuario p = null;
            while (rs.next()) {
                p = new Usuario(

                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        //rs.getString("password"),
                        rs.getDate("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("admin"));
                Usuarios.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Usuarios;
    }

    public Usuario fetch(String correo) {
        ResultSet rs = null;
        Usuario e = null;
        try {
            String query = "SELECT * FROM Usuarios where correo = '" +correo + "'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            e = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    //rs.getString("password"),
                    rs.getDate("fechaNac"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("admin"));
        } catch (SQLException ex) {
            showmessage("Contraseña o correo inválidos");
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public void showmessage(String mensaje)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setContentText(mensaje);
        alert.show();
    }
    public Boolean delete(int id) {
        try {
            String query = "delete from Usuarios where correo= ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Usuario usuario) {
        try {
            String query = "insert into Usuarios "
                    + " (nombre, correo, fechaNac, direccion, telefono,admin)"//agregar password despues de correo
                    + " values (?, ?, ?, ?, ?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,usuario.getNombre());
            st.setString(2,usuario.getCorreo());
            //st.setString(3,usuario.getPassword());
            st.setDate(3,usuario.getFechaNac());
            st.setString(4,usuario.getDireccion());
            st.setString(5,usuario.getTelefono());
            st.setString(6,usuario.getAdmin());

            st.execute();
            //data.add(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public int id(String correo) {
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
                        //rs.getString("password"),
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
    public int tipoPago(String tipoPago) {
        ResultSet rs = null;
        int id=0;
        Usuario er = null;
        try {
            String query = "SELECT * from tipoPago WHERE descripcion= '"+tipoPago+"'";
            System.out.println(query);
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Usuario p = null;
            while (rs.next()) {
               id=rs.getInt("tipoPago");
                System.out.println(id);

            }

            //data.add(usuario);
           // System.out.println(""+p.getNombre().toString());
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        return 0;
    }
    public Boolean update(Usuario usuario) {
        try {
            String query = "update Usuarios "
                    + " set nombre = ?, correo = ?, fechaNac = ?, direccion =?, telefono = ?, admin = ?"//agregar password despues de correo
                    + " where idUsuario=?";
            System.out.println(query + "updating....");
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1,usuario.getNombre());
            st.setString(2,usuario.getCorreo());
            //st.setString(3,usuario.getPassword());
            st.setDate(3,usuario.getFechaNac());
            st.setString(4,usuario.getDireccion());
            st.setString(5,usuario.getTelefono());
            st.setString(6,usuario.getAdmin());
            st.setInt(7, usuario.getId());
            st.execute();
            System.out.println(" g");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
}
    public  boolean existeRegistro( String correo) {
        Statement oSt = null;
        ResultSet oRs = null;
        String sSQL = " ";
        boolean dbexisteRegistro = false;

        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            sSQL = "SELECT * FROM Usuarios WHERE correo='" + correo + "'";


            oSt = conn.createStatement();
            oRs = oSt.executeQuery(sSQL);

            if (oRs.next()) {
                if (oRs.getRow() > 0) {
                    dbexisteRegistro = true;
                }
            }

            if (oSt != null) {
                oSt.close();
                oSt = null;
            }
            if (oRs != null) {
                oRs.close();
                oRs = null;
            }
        } catch (SQLException err) {

            oSt = null;
            oRs = null;
            sSQL = null;
        } catch (Exception err) {

            oSt = null;
            oRs = null;
            sSQL = null;
        } finally {
            oSt = null;
            oRs = null;
            sSQL = null;
        }
        return dbexisteRegistro;
    }

}
