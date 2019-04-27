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
 *
 *   public void setNombre(String nombre);
 * Metodos añadidos:
 *  public boolean realizarIngreso (ClienteImpl cl_origen, CuentaImpl cu_origen, ClienteImpl cl_destino, CuentaImpl cu_destino, double cantidad)
 *  public boolean aplicarComisionDescubierto(Cliente cliente, Cuenta cuenta, double cantidad)
 * Metodos object:
 *   public String toString();
 * */
package clasesBasicas;

public class BancoComercial {

    private String ID_BancoCentral;
    private String BIC;
    private String nombre;

    public BancoComercial()
    {
    	this.ID_BancoCentral = "ESP";
    	this.BIC = "XXXXXXXXXXX";
    	this.nombre = " ";
    }
    
    public BancoComercial(String ID_BancoCentral,String BIC, String nombre) {
        this.ID_BancoCentral = ID_BancoCentral;
        this.BIC = BIC;
        this.nombre = nombre;
    }
    
    public BancoComercial(BancoComercial otro)
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
    
    @Override
    public String toString(){
        return   getID_BancoCentral()+getBIC()+","+getNombre();
    }


}
