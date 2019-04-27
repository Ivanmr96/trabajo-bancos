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
 *
 * Metodos añadidos:
 * Metodos object:
 *   public String toString();
 * */
package interfaces;
import java.util.GregorianCalendar;
public interface Transferencia {
    public String getID_Cuenta();
    public void setID_Cuenta(String ID_Cuenta);
    public boolean isIngresoOrRetirada();
    public void setIngresoOrRetirada(boolean ingresoOrRetirada);
    public String getConcepto();
    public void setConcepto(String concepto);
    public double getCantidad();
    public void setCantidad(double cantidad);
    public GregorianCalendar getFecha();
    public void setFecha(GregorianCalendar fecha);
}
