/*
 * Nombre: Cuenta
 * Propiedades:
 *   -Basicas:
 *
 *       private String IBAN  consultable y no modificable
 *       private double cantidadDinero consultable y modificable
 *
 *   -Derivadas:
 *         codigo_bancoCentral es derivada de IBAN
 *         codigo_bancoComercial es derivada de IBAN
 *
 *   -Compartidas:
 *
 * Restricciones:
 * Metodos interface:
 *   public String getIBAN();
 *   public double getCantidadDinero();
 *
 *   public void setCantidadDinero(double cantidadDinero);
 * */
package clasesBasicas;

import interfaces.Cuenta;

public class CuentaImpl implements Cuenta {

    private String IBAN;
    private double cantidadDinero;

    /**
     * Constructor por defecto.
     */
    public CuentaImpl()
    {
    	this.IBAN = "ESPBSCHESMMXXXXXXXXXXXX";
    	this.cantidadDinero = 0.0;
    }
    
    /**
     * Constructor con parámetro.
     * <br> La cuenta se creará con un saldo de 0.0
     * @param IBAN El identificador de la cuenta.
     */
    public CuentaImpl(String IBAN){

        this.IBAN = IBAN;
        this.cantidadDinero = 0.0;
    }
    
    /**
     * Constructor con parámetros.
     * 
     * @param IBAN El identificador de la cuenta.
     * @param cantidadDinero El saldo de la cuenta.
     */
    public CuentaImpl(String IBAN, double cantidadDinero){

        this.IBAN = IBAN;
        this.cantidadDinero = cantidadDinero;
    }

    /**
     * Constructor de copia.
     * @param otra La otra cuenta de la cual se copiará.
     */
    public CuentaImpl(CuentaImpl otra)
    {
    	this.IBAN = otra.IBAN;
    	this.cantidadDinero = otra.cantidadDinero;
    }

    public String getCodigoBancoCentral() {
        String cod_bancoCentral = this.IBAN.substring(0,3);
        return cod_bancoCentral;
    }
    
    public String getCodigoBancoComercial() {
        String cod_bancoComercial = this.IBAN.substring(3,14);
        return cod_bancoComercial;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getCantidadDinero() {
        return cantidadDinero;
    }

    public void setCantidadDinero(double cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    /**
     * Representación como cadena: sus atributos separados por coma.
     * 
     * <br> <b>Ejemplo: "ESPCAIXESBBXXX0000015,1085.33"</b>
     */
    @Override
    public String toString(){
    	
    	String ret = getIBAN()+","+getCantidadDinero();
    	
    	for(int i = Double.toString(cantidadDinero).length() ; i < 20 ; i++)
    	{
    		ret += " ";
    	}
    	return ret;
    }
}
