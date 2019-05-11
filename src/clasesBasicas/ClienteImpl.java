/*
 * Nombre: Cliente
 * Propiedades:
 *   -Basicas:
 *       private String BIC_Banco consultable y modificable
 *       private String DNI  consultable y no modificable
 *       private double ingresoMensual consultable y modificable
 *
 *   -Derivadas:
 *
 *   -Compartidas:
 *
 * Restricciones: El ingreso mensual debe ser igual o mayor que 0
 * Metodos interface:
 *   public String getDNI();
 *   public double getIngresoMensual();
 *
 *   public void setIngresoMensual(double ingresoMensual);
 * */
package clasesBasicas;
import interfaces.Cliente;

public class ClienteImpl implements Cliente{

    private String BIC_Banco;
    private String DNI;
    private double ingresoMensual;
    
    /**
     * Constructor por defecto.
     */
    public ClienteImpl()
    {
    	this.BIC_Banco = "XXXXXXXXXXX";
    	this.DNI = "00000000-A";
    	this.ingresoMensual = 0;
    }

    /**
     * Constructor con parámetros.
     * 
     * @param BIC_Banco El identificador del banco comercial al que pertenece el cliente.
     * @param DNI El identificador del cliente.
     * @param ingresoMensual Los ingresos mensuales serán útiles para calcular cuáles son los posibles préstamos que puede solicitar el cliente.
     */
    public ClienteImpl(String BIC_Banco, String DNI, double ingresoMensual) {
        this.BIC_Banco = BIC_Banco;
        this.DNI = DNI;
        this.ingresoMensual = ingresoMensual;
    }

    public ClienteImpl(ClienteImpl o){
        this.BIC_Banco = o.BIC_Banco;
        this.DNI = o.DNI;
        this.ingresoMensual = o.ingresoMensual;
    }

    //metodos interface

    public String getDNI() {
        return DNI;
    }
    
    public String getBIC_Banco(){return BIC_Banco;}
    
    public void setBIC_Banco(String BIC_Banco){this.BIC_Banco = BIC_Banco;}
    
    public double getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(double ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }
    
    
    /**
     * Representacion como cadena: sus atributos separados por comas.
     * 
     * <br> <b>Ejemplo: "CAIXESBBXXX,12345678-A,1545.35"</b>
     */
    @Override
    public String toString(){
        return  getBIC_Banco()+","+  getDNI()+","+getIngresoMensual();
    }
}
