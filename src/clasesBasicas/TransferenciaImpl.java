/*
 * Nombre: TransferenciaImpl
 * Propiedades:
 *   -Basicas:
 *        String ID_Cuenta
 *        boolean isIngresoOrRetirada
 *        String concepto
 *        double cantidad
 *        GregorianCalendar fecha
 *
 *   -Derivadas:
 *
 *   -Compartidas:
 *
 * Restricciones:
 * Metodos interface:
 *
 *  public String getID_Cuenta()
    public void setID_Cuenta(String ID_Cuenta)
    public boolean isIngresoOrRetirada()
    public void setIngresoOrRetirada(boolean ingresoOrRetirada)
    public String getConcepto()
    public void setConcepto(String concepto)
    public double getCantidad()
    public void setCantidad(double cantidad)
    public GregorianCalendar getFecha()
    public void setFecha(GregorianCalendar fecha)
 * */
package clasesBasicas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class TransferenciaImpl implements Serializable {
    String ID_Cuenta;
    boolean isIngresoOrRetirada;
    String concepto;
    double cantidad;
    GregorianCalendar fecha;
    
    public TransferenciaImpl()
    {
    	this.ID_Cuenta = "ESPBANCOESBBXX0000000";
    	this.isIngresoOrRetirada = true;
    	this.concepto = " ";
    	this.cantidad = 0.0;
    	this.fecha = new GregorianCalendar();
    }

    public TransferenciaImpl(String ID_Cuenta, boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha) {
        this.ID_Cuenta = ID_Cuenta;
        this.isIngresoOrRetirada = isIngresoOrRetirada;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    
    public TransferenciaImpl(String ID_Cuenta, boolean isIngresoOrRetirada, String concepto, double cantidad)
    {
    	this.ID_Cuenta = ID_Cuenta;
        this.isIngresoOrRetirada = isIngresoOrRetirada;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = new GregorianCalendar();
    }
    
    public TransferenciaImpl(TransferenciaImpl otra)
    {
    	this.ID_Cuenta = otra.ID_Cuenta;
        this.isIngresoOrRetirada = otra.isIngresoOrRetirada;
        this.concepto = otra.concepto;
        this.cantidad = otra.cantidad;
        this.fecha = otra.fecha;
    }

    public String getID_Cuenta() {
        return ID_Cuenta;
    }

    public void setID_Cuenta(String ID_Cuenta) {
        this.ID_Cuenta = ID_Cuenta;
    }

    public boolean isIngresoOrRetirada() {
        return isIngresoOrRetirada;
    }

    public void setIngresoOrRetirada(boolean ingresoOrRetirada) {
        isIngresoOrRetirada = ingresoOrRetirada;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(fecha);
        String fechaformateada = sdf.format(fecha.getTime());
        return ID_Cuenta+","+isIngresoOrRetirada+","+concepto+","+cantidad+","+fechaformateada;
    }

    public String toStringFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(fecha);
        String fechaformateada = sdf.format(fecha.getTime());
        return fechaformateada;
    }
}
