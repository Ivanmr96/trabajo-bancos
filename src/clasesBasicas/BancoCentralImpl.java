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

    public BancoCentralImpl()
    {
        this.tasaInteres = 0;
        this.coeficienteCaja = 0;
    }

    public BancoCentralImpl(String ID,double tasaInteres, double coeficienteCaja)
    {
        this.ID = ID;
        this.tasaInteres = tasaInteres;
        this.coeficienteCaja = coeficienteCaja;
    }
    
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

    //Representacion como cadena: sus atributos separados por comas
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

