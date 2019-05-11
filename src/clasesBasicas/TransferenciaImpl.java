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
    
    /**
     * Constructor por defecto.
     */
    public TransferenciaImpl()
    {
    	this.ID_Cuenta = "ESPBANCOESBBXX0000000";
    	this.isIngresoOrRetirada = true;
    	this.concepto = " ";
    	this.cantidad = 0.0;
    	this.fecha = new GregorianCalendar();
    }
    
    /**
     * Constructor con fecha
     * 
     * @param ID_Cuenta El identificador de la cuenta donde se ha hecho la transferencia.
     * @param isIngresoOrRetirada Indica si la transferencia es un ingreso o una retirada de dinero.
     * @param concepto El concepto de la transferencia.
     * @param cantidad La cantidad de dinero.
     * @param fecha La fecha en la que se realizó la transferencia.
     */
    public TransferenciaImpl(String ID_Cuenta, boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha) {
        this.ID_Cuenta = ID_Cuenta;
        this.isIngresoOrRetirada = isIngresoOrRetirada;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    
    /**
     * Constructor sin fecha.
     * <br>Se creará la transferencia con la fecha actual.
     * 
     * @param ID_Cuenta El identificador de la cuenta donde se ha hecho la transferencia.
     * @param isIngresoOrRetirada Indica si la transferencia es un ingreso o una retirada de dinero.
     * @param concepto El concepto de la transferencia.
     * @param cantidad La cantidad de dinero.
     */
    public TransferenciaImpl(String ID_Cuenta, boolean isIngresoOrRetirada, String concepto, double cantidad)
    {
    	this.ID_Cuenta = ID_Cuenta;
        this.isIngresoOrRetirada = isIngresoOrRetirada;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = new GregorianCalendar();
    }
    
    /**
     * Constructor de copia.
     * 
     * @param otra La transferencia que se desea copiar.
     */
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

    /**
     * Representación como cadena: sus atributos separados por comas. La fecha estará formateada para que sea legible.
     * <br><b>Ejemplo: "ESPINGDESMMXXX0000008,true,Ingreso anonimo,35.20,6/9/2009"</b>
     */
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
