/*
 * Nombre: Cuenta
 * Propiedades:
 *   -Basicas:
 *       private String IBAN  consultable y no modificable
 *       private double cantidadDinero consultable y modificable
 *
 *   -Derivadas:
 *   	 codigo_bancoCentral es derivada de IBAN
 *       codigo_bancoComercial es derivada de IBAN
 *
 *   -Compartidas:
 *
 * Restricciones:
 * Metodos interface:
 *   public String getIBAN();
 *   
 *   public double getCantidadDinero();
 *
 *   public void setCantidadDinero(double cantidadDinero);
 *   
 *   public String getCodigoBancoCentral();
 *   
 *   public String getCodigoBancoComercial();
 * */
package interfaces;
public interface Cuenta {
    public String getIBAN();
    
    public double getCantidadDinero();
    
    public void setCantidadDinero(double cantidadDinero);
    
    public String getCodigoBancoCentral();
    
    public String getCodigoBancoComercial();
}
