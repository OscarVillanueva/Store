package sample;

import java.sql.Date;

public class Usuario {
    String nombre,correo,direccion,telefono,admin,passwd;
    int id;
    Date fechaNac;

    //agregar String password despues de correo
    public Usuario(int id,String nombre, String correo,String passwd,  Date fechaNac, String direccion, String telefono, String admin) {
        this.nombre = nombre;
        this.correo = correo;
        this.passwd=passwd;
        this.direccion = direccion;
        this.telefono = telefono;
        this.admin = admin;
        this.id = id;
        //this.password=password;
        this.fechaNac = fechaNac;
    }

    public Usuario() {

    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /*public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}
