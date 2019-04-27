package resguardos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import clasesBasicas.TransferenciaImpl;
import utilidades.MyObjectOutputStream;

public class GestionBancoCentralResguardo 
{
	/* INTERFAZ
     * Comentario: Escribe un registro nuevo en un fichero determinado
     * Prototipo: public boolean escribirRegistroEnFichero(String registro, String rutaFichero)
     * Entrada: Un String con el registro a escribir, y otro String con la ruta del fichero donde se escribirá.
     * Precondiciones: No hay
     * Salida: Un boolean indicando si se ha escrito correctamente o no.
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si se ha escrito correctamente el registro en el fichero correspondiente
     * 					-> False si no se ha escrito correctamente.
     */
    public boolean escribirRegistroEnFichero(String registro, String rutaFichero)
    {
    	boolean escrito = false;
    	
        System.out.println("escribirRegistroEnFichero en resguardo");
        
        return escrito;
    }
    
    /* INTERFAZ
     * Signatura: public void modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta,double cantidad)
     * Comentario: modifica, en el fichero de Cuentas, el registro del saldo total.
     * Precondiciones: Se pasa por referencia el ID de la cuenta a modificar y por valor la cantidad a aÃƒÂ±adir o substraer. Se pasa boolean que es true si aÃƒÂ±ade la cantidad o false si la resta
     * Entrada: String IBAN, boolean sumaOresta,double cantidad
     * Salida: Un boolean indicando si se ha modificado correctamente o no el saldo
     * Entrada/Salida: No hay
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha modificado correctamente el saldo de la cuenta en el fichero de cuentas
     * 					-> false si no se ha modificado correctamente.
     * */
    public boolean modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta,double cantidad)
    {
        boolean saldoModificado = false;

        System.out.println("modificarSaldoEnFicheroCuentas en resguardo");
        
        return saldoModificado;
    }
    
    /* INTERFAZ
     * Signatura: public void insertarMovimientoEnFicheroMovimientos(String IBAN,boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: Añade un nuevo movimiento en el fichero de movimientos de la cuenta.
     * Precondiciones: Se pasa por referencia el ID de la cuenta y por valor la cantidad de dinero a mover. Se pasa
     *                  un boolean que es true si el movimiento es un ingreso o false si es una retirada de dinero. Tambien se pasa la fecha como tres valores enteros (se supone vÃƒÂ¡lida)
     * Entrada: String IBAN,boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha
     * Salida: Un boolean que indica si se ha insertado correctamente el movimiento o no
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha insertado correctamente el registro del movimiento en el fichero de transferencias
     * 					-> false si no se ha insertado correctamente.
     * */
	public boolean insertarMovimientoEnFicheroMovimientos(String IBAN,boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha)
	{
        boolean movimientoInsertado = false;
        
        System.out.println("insertarMovimientoEnFicheroMovimientos");
        
        return movimientoInsertado;
	}
	
	/*
     * INTERFAZ
     * Signatura: public ArrayList<String> buscarMovimientosPorFecha(String iban_cuenta,int dia_buscado, int mes_buscado, int anyo_buscado)
     * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
     * Precondiciones: Se pasa un iban y tres int
     * Entrada: String iban,int dia_buscado, int mes_buscado, int anyo_buscado
     * Salida: arraylist de cadenas con el / los movimientos requeridos
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre devuelve un arraylist
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia,int mes,int anyo){
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        
        System.out.println("buscarMovimientosPorFecha en resguardo");

        return registros_buscados;
    }
    
    /*
     * INTERFAZ
     * Signatura: public void marcarCuentaComoBorrada(String iban_cuenta)
     * Comentario: Escribe en el fichero CuentasBorradas la cuenta indicada
     * Precondiciones: Se pasa un iban
     * Entrada: String iban
     * Salida:
     * Entrada/Salida:
     * Postcondiciones: modifica el fichero de cuentas borradas
     * */
    public boolean marcarCuentaComoBorrada(String iban_cuenta){
    	boolean borrada = false;
    	
    	System.out.println("marcarCuentaComoBorrada en resguardo");
    	
        return borrada;
    }
    
    /*
     * INTERFAZ
     * Signatura: public void eliminarCuenta(String IBAN)
     * Comentario: Elimina definitivamente el rastro de una cuenta.
     *              (borra la cuenta del fichero de cuentas, borra su historial de movimientos, borra al cliente ya que de momento cada cliente solo puede tener una cuenta, borra del fichero cuentas-clientes...)
     * Precondiciones: No hay
     * Entrada: String IBAN
     * Salida: un boolean indicando si se borro correctamente la cuenta o no
     * Entrada/Salida:
     * Postcondiciones: Modifica los ficheros de cuentas, clientes, clientes_cuenta borrando al cliente y a la cuenta determinada
     * */
    public boolean eliminarCuenta(String IBAN) 
    {
    	boolean eliminada = false;

    	System.out.println("eliminarCuenta en resguardo");
        
        return eliminada;
    }
    
    /* INTERFAZ
     * Comentario: Actualiza un fichero maestro determinado, mirando los registros de su fichero de movimiento correspondiente.
     * Prototipo: public boolean actualizarFichero(String fichero)
     * Entrada: Un string con la ruta del fichero y un int con la posicion que ocupa el campo clave en cada registro
     * Precondiciones: No hay
     * Salida: Un boolean indicando si se actualizó correctamente el fichero maestro
     * Postcondiciones: Devuelve true si se actualizaó bien, flase de lo contrario
     */
    public boolean actualizarFichero(String fichero, int posicionCampoClave)
    {
        boolean actualizado = false;
        
        System.out.println("actualizarFichero en resguardo");
        
        return actualizado;
    }
}
