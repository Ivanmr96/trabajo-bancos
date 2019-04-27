/*
 * Nombre: Cliente
 * Propiedades:
 *   -Basicas:
 *      private String BIC_Banco consultable y modificable
 *       private String DNI  consultable y no modificable
 *       private double ingresoMensual consultable y modificable
 *
 *   -Derivadas:
 *
 *   -Compartidas:
 *
 * Restricciones: El DNI debe ser válido y el ingreso mensual debe ser igual o mayor que 0
 * Metodos interface:
 *   public String getDNI();
 *   public double getIngresoMensual();
 *
 *   public void setIngresoMensual(double ingresoMensual);
 * Metodos añadidos:
 *   public void sacarDinero(Cuenta cuenta)
 *   public void ingresarDinero(Cuenta cuenta)
 *   public void realizarTransferencia(Cuenta origen, Cuenta destino)
 *   public void verHistorialTransferencias(Cuenta cuenta)
 * Metodos object:
 *   public String toString();
 * */
package clasesBasicas;
import interfaces.Cliente;

public class ClienteImpl implements Cliente{

    private String BIC_Banco;
    private String DNI;
    private double ingresoMensual;

    public ClienteImpl()
    {
    	this.BIC_Banco = "XXXXXXXXXXX";
    	this.DNI = "00000000-A";
    	this.ingresoMensual = 0;
    }

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
    
    //Metodos Object

    @Override
    public String toString(){
        return  getBIC_Banco()+","+  getDNI()+","+getIngresoMensual();
    }
}
