package sample;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cuenta {
    String nombre,corre,direccion,telefono,descripcion,datos,admin,passwd;
    Date fechaNac;
    int id;
    public Cuenta()
    {

    }
    public Cuenta(int id, String nombre, String corre,String passwd, Date fechaNac, String direccion, String telefono, String descripcion, String datos,String admin) {
        this.id = id;
        this.nombre = nombre;
        this.corre = corre;
        this.passwd=passwd;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.datos = datos;
        this.fechaNac = fechaNac;
        this.admin=admin;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorre() {
        return corre;
    }

    public void setCorre(String corre) {
        this.corre = corre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}
