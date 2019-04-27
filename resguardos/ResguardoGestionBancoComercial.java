/*Aquí están los resguardos de los métodos más complicados del Banco Comercial como por ejemplo:
* -Cobrar gastos administración cada X tiempo a X cuentas
* -Realizar movimientos en cuentas de cliente
* -La cosa esa de la reserva fraccionaria
* -Aplicar comisión por descubierto
*
* */
package resguardos;

import java.util.GregorianCalendar;
import java.util.List;

public class ResguardoGestionBancoComercial {

    /*
    * INTERFAZ
    * Signatura:
    * Comentario:
    * Precondiciones:
    * Entrada:
    * Salida:
    * Entrada/Salida:
    * Postcondiciones:
    * */
    /*
     *  Voy a codificar aquÃ­ los mÃ©todos que he puesto en "ResguardoGestionBancoComercial"
     *   a pesar de que creo que irÃ­an en la clase bÃ¡sica "BancoComercial" pero por tenerlos separados.
     * Si luego vemos que son de BancoComercial, los cambio a allÃ­.
     * */

        /*
         * INTERFAZ
         * Signatura: public boolean isCuentaBorrada(String iban)
         * Comentario: Comprueba si el iban pertenece a una cuenta del fichero CuentasBorradas
         * Precondiciones: Se pasa un iban
         * Entrada: String iban
         * Salida: boolean
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre devuelve true si el iban corresponde a una cuenta del fichero CuentasBorradas y false si no
         * */
        public boolean isCuentaBorrada(String iban){
            System.out.println("En resguardo");
            return false;
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
        public void marcarCuentaComoBorrada(String iban_cuenta){
            System.out.println("En resguardo");
        }

        /*
         * INTERFAZ
         * Signatura: public void eliminarCuentasBorradasDefinitivamente(String bic)
         * Comentario: Elimina definitivamente el rastro de las cuentas marcadas como borradas en el fichero CuentasBorradas.
         *              (borra la cuenta del fichero de cuentas, borra su historial de movimientos, borra al cliente ya que de momento cada cliente solo puede tener una cuenta, borra del fichero cuentas-clientes...)
         * Precondiciones: Se pasa el BIC del banco
         * Entrada: String bic
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: modifica varios ficheros
         * */
        public void eliminarCuentasBorradasDefinitivamente(String bic) {
            System.out.println("En resguardo");
        }



        /*
         * INTERFAZ
         * Signatura: public ArrayList<String> buscarMovimientosPorAnyo(String iban_cuenta, int anyo_buscado)
         * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
         * Precondiciones: Se pasa un iban y un int
         * Entrada: String iban, int anyo_buscado
         * Salida: arraylist de cadenas con el / los movimientos requeridos
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre devuelve un arraylist
         * */
        public List<String> buscarMovimientosPorAnyo(String iban_cuenta, int anyo_buscado){
            System.out.println("En resguardo");
            return null;
        }

        /*
         * INTERFAZ
         * Signatura: public ArrayList<String> buscarMovimientosPorMesYAnyo(String iban_cuenta, int mes_buscado, int anyo_buscado)
         * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
         * Precondiciones: Se pasa un iban y dos int
         * Entrada: String iban, int mes_buscado, int anyo_buscado
         * Salida: arraylist de cadenas con el / los movimientos requeridos
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre devuelve un arraylist
         * */
        public List<String> buscarMovimientosPorMesYAnyo(String iban_cuenta, int mes_buscado,int anyo_buscado){
            System.out.println("En resguardo");
            return null;
        }



        /*
         * INTERFAZ
         * Signatura: public ArrayList<String> buscarMovimientosPorDiaMesYAnyo(String iban_cuenta,int dia_buscado, int mes_buscado, int anyo_buscado)
         * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
         * Precondiciones: Se pasa un iban y tres int
         * Entrada: String iban,int dia_buscado, int mes_buscado, int anyo_buscado
         * Salida: arraylist de cadenas con el / los movimientos requeridos
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre devuelve un arraylist
         * */
        public List<String> buscarMovimientosPorDiaMesYAnyo(String iban_cuenta, int dia_buscado,int mes_buscado,int anyo_buscado){
            System.out.println("En resguardo");
            return null;
        }

        /*
         * INTERFAZ
         * Signatura: public List<String> ultimosDiezMovimientos(String iban_cuenta)
         * Comentario: devuelve los ultimos diez movimientos de la cuenta
         * Precondiciones: Se pasa un iban
         * Entrada: String iban
         * Salida: una lista de String
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre devuelve una lista de String
         * */
        public List<String> ultimosDiezMovimientos(String iban_cuenta){
            System.out.println("En resguardo");
            return null;
        }

        /*
         * INTERFAZ
         * Signatura: public boolean isDNIvalido(String nombre_banco,String dni_cliente)
         * Comentario: Dado un DNI, devuelve true si este pertenece a un cliente existente del banco y false si no
         * Precondiciones: Se pasa el nombre del banco y el dni del cliente
         * Entrada: String nombre_banco y String dni_cliente
         * Salida: boolean
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre se devuelve un boolean que devuelve true si este pertenece a un cliente existente del banco y false si no
         * */
        @Deprecated
        public boolean isDNIvalido(String nombre_banco,String dni_cliente){
            System.out.println("En resguardo");
            return false;
        }

        /*
         * INTERFAZ
         * Signatura: public boolean isIBANvalido(String iban_cuenta)
         * Comentario: Dado un iban devuelve true si este existe o false si no
         * Precondiciones: Se pasa el iban de la cuenta
         * Entrada: String iban_cuenta
         * Salida: boolean
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre se devuelve un boolean que devuelve true si existe y false si no
         * */
        public boolean isIBANvalido(String iban_cuenta){
            System.out.println("En resguardo");
            return false;
        }

        /*
         * INTERFAZ
         * Signatura: public boolean isPropietario(String dni_cliente, String iban_cuenta)
         * Comentario: Dado un iban y un dni de cliente, devuelve true si este iban pertenece a este cliente o false si no
         * Precondiciones: Se pasa el iban de la cuenta
         * Entrada: String dni_cliente, String iban_cuenta
         * Salida: boolean
         * Entrada/Salida:
         * Postcondiciones: asociado al nombre se devuelve true si este iban pertenece a este cliente o false si no
         * */
        public boolean isPropietario(String dni_cliente, String iban_cuenta){
            System.out.println("En resguardo");
            return false;
        }




        /*
         * INTERFAZ
         * Signatura: public String obtenerClientePorIBAN(String iban_cuenta)
         * Comentario: dado el iban de la cuenta, te devuelve el cliente al que pertenece la cuenta
         * Precondiciones: se pasa el iban de la cuenta
         * Entrada: String iban_cuenta
         * Salida: String que es el dni del cliente
         * Entrada/Salida:
         * Postcondiciones: Asociado al nombre se devuelve el DNI del cliente al que pertenece la cuenta
         * */
        public String obtenerClientePorIBAN(String iban_cuenta){
            System.out.println("En resguardo");
            return " ";
        }
        /*
         * INTERFAZ
         * Signatura: public String obtenerIBANPorCliente(String nombre_banco, String dni_cliente)
         * Comentario: dado el nombre del banco y el dni del propietario de la cuenta, te devuelve el IBAN de la cuenta
         * Precondiciones: se pasan el nombre del banco y el dni del propietario
         * Entrada: String nombre_banco, String dni_cliente
         * Salida: String que es el IBAN de la cuenta
         * Entrada/Salida:
         * Postcondiciones: Asociado al nombre se devuelve el IBAN de la cuenta que pertenece a dicho cliente
         * */
        public String obtenerIBANPorCliente(String nombre_banco, String dni_cliente){
            System.out.println("En resguardo");
            return " ";
        }



        /*
         * INTERFAZ
         * Signatura: public void sacarDinero(String ID_Cuenta, String concepto,double cantidad, GregorianCalendar fecha)
         * Comentario: saca una cantidad dada de una cuenta
         * Precondiciones: Por valor se pasa una cantidad, por referencia la ID de una cuenta(IBAN) y un objeto fecha
         * Entrada: String ID_Cuenta, double cantidad, GregorianCalendar fecha
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: Se modificarÃ¡n los ficheros de Cuentas modificando el saldo y de movimientos, aÃ±adiendo el movimiento correspondiente.
         * */
        public void sacarDinero(String ID_Cuenta, String concepto, double cantidad, GregorianCalendar fecha){
            System.out.println("En resguardo");
        }

        /*
         * INTERFAZ
         * Signatura: public void ingresarDinero(String ID_Cuenta,String concepto, double cantidad, GregorianCalendar fecha)
         * Comentario: ingresa una cantidad dada de una cuenta
         * Precondiciones: Por valor se pasa una cantidad, por referencia la ID de una cuenta y objeto fecha
         * Entrada: String ID_Cuenta, double cantidad, GregorianCalendar fecha
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: Se modificarÃ¡n los ficheros de Cuentas modificando el saldo y de movimientos, aÃ±adiendo el movimiento correspondiente.
         * */
        public void ingresarDinero(String ID_Cuenta,String concepto, double cantidad, GregorianCalendar fecha){
            System.out.println("En resguardo");
        }

        /*
         * INTERFAZ
         * Signatura: public void realizarMovimiento(String cuenta_origen,String cuenta_destino, String concepto,double cantidad, GregorianCalendar fecha)
         * Comentario: Realiza un movimiento bancario, sacando una cantidad de la cuenta de origen e ingresÃ¡ndola en la cuenta destino.
         *              Llama a los mÃ©todos sacarDinero e ingresarDinero.
         * Precondiciones: Por referencia se pasan las ID de las cuentas por valor se pasa la cantidad. Tambien se pasa por referencia el concepto y objeto fecha
         * Entrada: (String cuenta_origen,String cuenta_destino, String concepto,double cantidad, GregorianCalendar fecha)
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: Se modificarÃ¡n los ficheros de cuentas y de movimientos correspondientes.
         * */
        public void realizarMovimiento(String cuenta_origen,String cuenta_destino, String concepto,double cantidad, GregorianCalendar fecha){
            System.out.println("En resguardo");
        }


        /*
         * INTERFAZ
         * Signatura: public void ordenarMovimientosPorFecha(String iban)
         * Comentario: Ordena un fichero de movimientos de una cuenta en base a las fechas, deja primero los mÃ¡s recientes.
         * Precondiciones: Se le pasa por referencia el IBAN de la cuenta
         * Entrada: String IBAN
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: Se modifica el fichero y se deja ordenado de forma descendente
         * */
        public void ordenarMovimientosPorFecha(String iban){
            System.out.println("En resguardo");
        }

        /*
         * INTERFAZ
         * Signatura: public void insertarMovimientoEnFicheroMovimientos(String ID_Cuenta,boolean isIngresoOrRetirada, String concepto, double cantidad,GregorianCalendar fecha)
         * Comentario: Este metodo se encarga de modificar en el fichero de movimientos de la cuenta, aÃ±ade un nuevo movimiento.
         * Precondiciones: Se pasa por referencia el ID de la cuenta y por valor la cantidad de dinero a mover. Se pasa
         *                  un boolean que es true si el movimiento es un ingreso o false si es una retirada de dinero. Tambien se pasa la fecha
         * Entrada: (String ID_Cuenta,boolean isIngresoOrRetirada, double cantidad,GregorianCalendar fecha)
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: Se modifica el fichero de movimientos de cuentas, aÃ±adiendo un movimiento nuevo.
         * */
        public void insertarMovimientoEnFicheroMovimientos(String ID_Cuenta,boolean isIngresoOrRetirada, String concepto, double cantidad,GregorianCalendar fecha){
            System.out.println("En resguardo");

        }


        /*
         * INTERFAZ
         * Signatura: public void modificarSaldoEnFicheroCuentas(String iban_cuenta, boolean sumaOresta,double cantidad)
         * Comentario: Este metodo se encarga de modificar en el fichero de Cuentas, el registro del saldo total (campo 2).
         * Precondiciones: Se pasa por referencia el ID de la cuenta a modificar y por valor la cantidad a aÃ±adir o substraer. Se pasa boolean que es true si aÃ±ade la cantidad o false si la resta
         * Entrada: String ID_Cuenta, boolean sumaOresta, double cantidad
         * Salida:
         * Entrada/Salida:
         * Postcondiciones: Se modifica el fichero de Cuentas y se actualiza el saldo pertinente.
         * */
        public void modificarSaldoEnFicheroCuentas(String iban_cuenta, boolean sumaOresta,double cantidad){
            System.out.println("En resguardo");
        }


        /*
         * INTERFAZ
         * Signatura: public String obtenerBICporNombre(String nombre_banco)
         * Comentario: devuelve el BIC de un banco dando su nombre
         * Precondiciones: por referencia se pasa un string
         * Entrada: String nombre
         * Salida: String BIC
         * Entrada/Salida:
         * Postcondiciones: Asociado al nombre se devuelve un String
         * */
        public String obtenerBICporNombre(String nombre_banco){
            System.out.println("En resguardo");
            return " ";
        }

        /*
         * INTERFAZ
         * Signatura: public String obtenerNombrePorBIC(String BIC)
         * Comentario: devuelve el nombre de un banco dando su BIC
         * Precondiciones: por referencia se pasa un string
         * Entrada: String BIC
         * Salida: String nombre
         * Entrada/Salida:
         * Postcondiciones: Asociado al nombre se devuelve un String
         * */

        public String obtenerNombrePorBIC(String bic){
            System.out.println("En resguardo");
            return " ";
        }

        /*
         * INTERFAZ
         * Signatura: public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta)
         * Comentario: devuelve el nombre de un banco dado el IBAN de una cuenta
         * Precondiciones: por referencia se pasa un string
         * Entrada: String iban_cuenta
         * Salida: String nombre
         * Entrada/Salida:
         * Postcondiciones: Asociado al nombre se devuelve un String
         * */
        public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta){

            return " ";
        }

        /* INTERFAZ
         * Comentario: A partir de un IBAN, obtiene el BIC del banco comercial que gestiona la cuenta
         * Prototipo: public String obtenerBICporIBAN(String IBAN)
         * Entrada: Un String con el IBAN
         * Precondiciones: No hay
         * Salida: Un string con el BIC del banco que gestiona la cuenta
         * Postcondiciones: Asociado al nombre devuelve un string con el BIC del banco que gestiona la cuenta
         */
        public String obtenerBICporIBAN(String IBAN)
        {
            return " ";
        }

        /* INTERFAZ
         * Comentario: Comprueba si un cliente (DNI) está registrado en un banco(BIC)
         * Prototipo: public boolean DNIRegistrado(String DNI, String BIC)
         * Entrada: Un string con el DNI a comprobar, y un String con el BIC del banco donde se quiere comprobar
         * Precondiciones: No hay
         * Salida: Un boolean indicando si el DNI está registrado en el banco o no
         * Postcondiciones: Asociado al nombre deuvelve true si el DNI está registrado en el banco (BIC), o false si no lo está.
         */
        public boolean DNIRegistrado(String DNI, String BIC)
        {
            System.out.println("En resguardo");

            return false;
        }

        /* INTERFAZ
         * Comentario: crea un nuevo cliente y una cuenta asociada a él en un banco determinado.
         * Prototipo: public boolean insertarCliente(String BIC, String DNI, double ingresosMensuales)
         * Entrada:
         * 		-> Un string con el BIC del banco donde se insertará el nuevo cliente
         * 		-> un String con el DNI del cliente
         * 		-> un double con los ingresos mensuales del cliente
         * Precondiciones: El BIC debe ser de un banco existente.
         * Salida: Un String indicando el IBAN de la cuenta asociada al cliente nuevo creado.
         * Postcondiciones: Asociado al nombre devuelve un String, que será el IBAN de la cuenta asociada al cliente nuevo, o null
         * 					Si no se creó correctamente.
         */
        public String insertarCliente(String BIC, String DNI, double ingresosMensuales)
        {

            return " ";
        }

        /* INTERFAZ
         * Comentario: Obtiene el numero de cuenta de un IBAN
         * Prototipo: public String obtenerNumCuentaPorIBAN(String IBAN)
         * Entrada: Un String con el IBAN del que se quiere obtener su numero de cuenta
         * Precondiciones: No hay
         * Salida: Un String con el numero de cuenta del IBAN especificado
         * Postcondiciones: Asociado al nombre devuelve un String con el numero de cuenta del IBAN especificado
         */
        public String obtenerNumCuentaPorIBAN(String IBAN)
        {
            return " ";
        }


}
