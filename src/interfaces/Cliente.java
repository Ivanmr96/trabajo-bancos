/*
* Nombre: Cliente
* Propiedades:
*   -Basicas:
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
package interfaces;
public interface Cliente {

    public String getDNI();
    public String getBIC_Banco();
    public void setBIC_Banco(String BIC_Banco);
    public double getIngresoMensual();
    public void setIngresoMensual(double ingresoMensual);

}
