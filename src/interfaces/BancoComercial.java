package interfaces;
/*
 * Nombre: BancoComercial
 * Propiedades:
 *   -Basicas:
 *       private String ID_BancoCentral consultable y modificable
 *       private String BIC  consultable y no modificable
 *       private String nombre consultable y modificable
 *
 *   -Derivadas:
 *
 *   -Compartidas:
 *
 * Restricciones:
 * Metodos interface:
 *   public String getBIC();
 *   public String getNombre();
 *   public String getID_BancoCentral();
 *
 *	 public void setID_BancoCentral(String ID_BancoCentral);
 *   public void setNombre(String nombre);
 * */
public interface BancoComercial 
{
	public String getBIC();
	public String getNombre();
	public String getID_BancoCentral();
	
	public void setID_BancoCentral(String ID_BancoCentral);
	public void setNombre(String nombre);
}
