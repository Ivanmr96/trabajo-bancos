/* ESTUDIO DE INTERFAZ
 *
 * Propiedades basicas:
 *      -> ID: String, consultable, modificable //se necesita el set
 * 		-> tasaInteres: double, consultable, modificable
 * 		-> coeficienteCaja: double, consultable, modificable
 *
 * Propiedades derivadas: No hay
 * Propiedades comaprtidas: No hay
 */

/* INTERFAZ
 * public String getID();
 * public double getTasaInteres();
 * public double getCoeficienteCaja();
 *
 * public void setID(String ID);
 * public void setTasaInteres(double tasaInteres);
 * public void setCoeficienteCaja(double coeficienteCaja);
 */
package clasesBasicas;

public class BancoCentralImpl implements Cloneable
{
    private String ID;
    private double tasaInteres;
    private double coeficienteCaja;

    /**
     * Constructor por defecto.
     */
    public BancoCentralImpl()
    {
        this.tasaInteres = 0;
        this.coeficienteCaja = 0;
    }

    /**
     * Constructor con par�metros.
     * 
     * @param ID El identificador que tendr� el banco central.
     * @param tasaInteres El porcentaje en el que est� invertido el capital. Ser� necesario para el sistema de cr�ditos y pr�stamos.
     * @param coeficienteCaja Proporci�n de dep�sitos que deben mantener los bancos como reserva l�quida. Ser� necesario para el sistema de cr�ditos y pr�stamos.
     */
    public BancoCentralImpl(String ID,double tasaInteres, double coeficienteCaja)
    {
        this.ID = ID;
        this.tasaInteres = tasaInteres;
        this.coeficienteCaja = coeficienteCaja;
    }
    
    /**
     * Constructor de copia
     * @param otro Banco central del que se har� la copia.
     */
    public BancoCentralImpl(BancoCentralImpl otro)
    {
    	this.ID = otro.ID;
    	this.tasaInteres = otro.tasaInteres;
    	this.coeficienteCaja = otro.coeficienteCaja;
    }

    public double getTasaInteres() { return this.tasaInteres; }
    public double getCoeficienteCaja() { return this.coeficienteCaja; }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }
    public void setCoeficienteCaja(double coeficienteCaja) { this.coeficienteCaja = coeficienteCaja; }

    /**
     * Representaci�n como cadena: sus atributos separados por comas.
     * 
     * <br> <b>Ejemplo: "ESP,3.5,15.0"</b>
     */
    @Override
    public String toString()
    {
        return this.ID+"," + this.tasaInteres + "," + this.coeficienteCaja;
    }

    public BancoCentralImpl clone()
    {
    	BancoCentralImpl copia = null;

        try
        {
            copia = (BancoCentralImpl)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        return copia;
    }
}

