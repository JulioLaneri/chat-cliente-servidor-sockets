package Server;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    private String remitente;
    private Date time;
    private String mensaje;

    public Mensaje(String remitente, Date time, String mensaje) {
        this.remitente = remitente;
        this.time = time;
        this.mensaje = mensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public Date getTime() {
        return time;
    }

    public String getMensaje() {
        return mensaje;
    }

}
