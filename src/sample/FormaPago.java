package sample;

public class FormaPago {
    int idUsuario,tipoPago;
    String datos;
    public FormaPago(){ }

    public FormaPago(int idUsuario, int tipoPago, String datos) {
        this.idUsuario = idUsuario;
        this.tipoPago = tipoPago;
        this.datos = datos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}
