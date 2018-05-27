package sample.controllers;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.FormaPago;
import sample.InfoUser;
import sample.MySQL;
import sample.Usuario;
import sample.dao.FormaPagoDAO;
import sample.dao.UsuarioDAO;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerRegistro extends InfoUser implements Initializable {
    @FXML
    Button btnOK;
    @FXML
    TextField txtNombre,txtEmail,txtPassword,txtDireccion,txtTelefono,txtDatosTarjeta;
    @FXML
    DatePicker dpFecha;
    @FXML
    RadioButton rbCredito,rbRegalo,rbAdministrador,rbUsuario;
    /*Boolean loggeado;
    String tipo;*/




    private UsuarioDAO usuarioDAO = new UsuarioDAO(MySQL.getConnection());
    private FormaPagoDAO FormaPagoDAO = new FormaPagoDAO(MySQL.getConnection());
    // private Usuario us;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnOK.setOnAction(eventHandler);
    }
    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if(event.getSource()==btnOK)
            {
                Usuario us=new Usuario();
                FormaPago forma= new FormaPago();
                us.setNombre(txtNombre.getText());
                us.setCorreo(txtEmail.getText());
                LocalDate localDate = dpFecha.getValue();
                us.setFechaNac(Date.valueOf(localDate));
                us.setDireccion(txtDireccion.getText());
                us.setTelefono(txtTelefono.getText());
                us.setPasswd(txtPassword.getText());


                if(rbCredito.isSelected())
                {

                    forma.setTipoPago(usuarioDAO.tipoPago("Tarjeta Deb/Cred"));
                }
                if (rbRegalo.isSelected()) {

                    forma.setTipoPago(usuarioDAO.tipoPago("Tarjeta de regalo"));
                }
                forma.setDatos(txtDatosTarjeta.getText());
                if (rbUsuario.isSelected()) {
                    us.setAdmin("0");
                    tipo="usuario";
                }
                if (rbAdministrador.isSelected())
                {
                    us.setAdmin("1");
                    tipo="admin";
                }

                saveUsuario(us);
                idUser = usuarioDAO.id(txtEmail.getText());
                forma.setIdUsuario(idUser);
                saveFormaPago(forma);




                txtNombre.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                //dpFecha.setValue(00-00-00);
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtPassword.setText("");
                txtDatosTarjeta.setText("");
                showmessage("Iniciar sesión con su nueva cuenta en login");
                isLog = true;
            }
        }
    };
    private boolean saveFormaPago(FormaPago forma)
    {
        return FormaPagoDAO.insert(forma);
    }
    private boolean saveUsuario(Usuario us)
    {
        return usuarioDAO.insert(us);
    }
    public void showmessage(String mensaje)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setContentText(mensaje);
        alert.show();
    }
}