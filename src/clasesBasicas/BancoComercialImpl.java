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
package clasesBasicas;

public class BancoComercialImpl implements Cloneable{

    private String ID_BancoCentral;
    private String BIC;
    private String nombre;

    public BancoComercialImpl()
    {
    	this.ID_BancoCentral = "ESP";
    	this.BIC = "XXXXXXXXXXX";
    	this.nombre = " ";
    }
    
    public BancoComercialImpl(String ID_BancoCentral,String BIC, String nombre) {
        this.ID_BancoCentral = ID_BancoCentral;
        this.BIC = BIC;
        this.nombre = nombre;
    }
    
    public BancoComercialImpl(BancoComercialImpl otro)
    {
    	this.ID_BancoCentral = otro.ID_BancoCentral;
    	this.BIC = otro.BIC;
    	this.nombre = otro.nombre;
    }

    //setters y getters
    public String getBIC() {
        return BIC;
    }

    public String getNombre() {
        return nombre;
    }

    public String getID_BancoCentral() {
        return ID_BancoCentral;
    }

    public void setID_BancoCentral(String ID_BancoCentral) {
        this.ID_BancoCentral = ID_BancoCentral;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Representacion como cadena: sus atributos separados por comas
    @Override
    public String toString(){
        return   getID_BancoCentral()+getBIC()+","+getNombre();
    }

    public BancoComercialImpl clone()
    {
    	BancoComercialImpl copia = null;

        try
        {
            copia = (BancoComercialImpl)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        return copia;
    }
}
