package sample;

import java.io.Serializable;

public class InfoUser implements Serializable{
    protected int idUser;
    protected boolean isLog = false;
    protected String tipo;

    public InfoUser(int idUser, boolean isLog, String tipo) {
        this.idUser = idUser;
        this.isLog = isLog;
        this.tipo = tipo;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


}
