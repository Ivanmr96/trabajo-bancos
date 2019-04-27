/*
 * Nombre: Cuenta
 * Propiedades:
 *   -Basicas:
 *       private String IBAN  consultable y no modificable
 *       private double cantidadDinero consultable y modificable
 *
 *   -Derivadas:
 *
 *   -Compartidas:
 *
 * Restricciones:
 * Metodos interface:
 *   public String getIBAN();
 *   public double getCantidadDinero();
 *
 *   public void setCantidadDinero(double cantidadDinero);
 * Metodos añadidos:
 * Metodos object:
 *   public String toString();
 * */
package interfaces;
public interface Cuenta {
    public String getIBAN();
    public double getCantidadDinero();
    public void setCantidadDinero(double cantidadDinero);

}
