package interfaces;
/* ESTUDIO DE INTERFAZ
*
* Propiedades basicas:
*    ->  ID: String, consultable, modificable //se necesita el set
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
public interface BancoCentral 
{
	public String getID();
	public double getTasaInteres();
	public double getCoeficienteCaja();
	
	public void setID(String ID);
	public void setTasaInteres(double tasaInteres);
	public void setCoeficienteCaja(double coeficienteCaja);
}
