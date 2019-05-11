package resguardos;

import clasesBasicas.TransferenciaImpl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class GestionBancoCentralResguardo 
{
    /* INTERFAZ
     * Comentario: Escribe un registro nuevo en un fichero determinado
     * Prototipo: public boolean escribirRegistroEnFichero(String registro, String rutaFichero)
     * Entrada: Un String con el registro a escribir, y otro String con la ruta del fichero donde se escribira
     * Precondiciones: No hay
     * Salida: Un boolean indicando si se ha escrito correctamente o no.
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si se ha escrito correctamente el registro en el fichero correspondiente
     * 					-> False si no se ha escrito correctamente
     * 					* Puede lanzar IOException si hay algun error al escribir
     */
    public boolean escribirRegistroEnFichero(String registro, String rutaFichero) {
        boolean escrito = false;


        return escrito;
    }

    /* INTERFAZ
     * Signatura: public void modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta,double cantidad)
     * Comentario: modifica, en el fichero de Cuentas, el registro del saldo total.
     * Precondiciones: Se pasa por referencia el ID de la cuenta a modificar y por valor la cantidad a a�adir o substraer. Se pasa boolean que es true si a�ade la cantidad o
     * 				   false si la resta
     * Entrada: String IBAN, boolean sumaOresta,double cantidad
     * Salida: Un boolean indicando si se ha modificado correctamente o no el saldo
     * Entrada/Salida: No hay
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha modificado correctamente el saldo de la cuenta en el fichero de cuentas
     * 					-> false si no se ha modificado correctamente.
     * 					* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta, double cantidad)
    {
        boolean modificado = false;


        return modificado;
    }

    /* INTERFAZ
     * Signatura: public boolean insertarMovimientoEnFicheroMovimientos(String IBAN,boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: A�ade un nuevo movimiento en el fichero de movimientos de la cuenta.
     * Precondiciones: Se pasa por referencia el ID de la cuenta y por valor la cantidad de dinero a mover. Se pasa
     *                  un boolean que es true si el movimiento es un ingreso o false si es una retirada de dinero. Tambien se pasa la fecha como tres valores enteros (se supone valida)
     * Entrada: String IBAN,boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha
     * Salida: Un boolean que indica si se ha insertado correctamente el movimiento o no
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha insertado correctamente el registro del movimiento en el fichero de transferencias
     * 					-> false si no se ha insertado correctamente.
     * 					* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean insertarMovimientoEnFicheroMovimientos(String IBAN, boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha) {
       boolean movimientoInsertado = false;


        return movimientoInsertado;
    }

    /* INTERFAZ
     * Comentario: Accede al fichero de cuentas y busca una cuenta por su IBAN para leer sus datos
     * Prototipo: public String datosCuenta(String IBAN)
     * Precondiciones: No hay
     * Entrada: el IBAN de la cuenta
     * Salida: un String con los datos de la cuenta
     * Postcondiciones: Asociado al nombre devuelve un String con los datos de la cuenta separados por comas, o null
     * 					Si el IBAN no esta registrado en el fichero.
     * 					* Puede lanzar IOException si hay algun error al leer.
     */
    public String datosCuenta(String IBAN) {
        String cuenta = null;


        return cuenta;
    }

    /* INTERFAZ
     * Comentario: Comprueba si existe un cliente(BIC) registrado en el banco central
     * Prototipo: public boolean BICRegistrado(String BIC)
     * Entrada: un String con el BIC del cliente a comprobar
     * Precondiciones: No hay
     * Salida: un boolean indicando si el BIC esta registrado ya o no
     * Postcondiciones: Asociado al nombre devuelve true si el BIC esta ya registrado en el banco o false de lo contrario.
     * 					* Puede lanzar IOException si hay algun error al leer
     */
    public boolean BICRegistrado(String BIC) {
        boolean registrado = false;
        return registrado;
    }

    /* INTERFAZ
     * Comentario: Comprueba si existe una cuenta(IBAN) registrada en el banco central
     * Prototipo: public boolean IBANRegistrado(String IBAN)
     * Entrada: un String con el IBAN de la cuenta a comprobar
     * Precondiciones: No hay
     * Salida: un boolean indicando si el IBAN esta registrado ya o no
     * Postcondiciones: Asociado al nombre devuelve true si el IBAN esta ya registrado en el banco o false de lo contrario.
     * 					* Puede lanzar IOException si hay algun error al leer
     */
    public boolean IBANRegistrado(String IBAN) {
        boolean registrado = false;
        return registrado;
    }

    /*
     * INTERFAZ
     * Signatura: public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int anyo)
     * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
     * Precondiciones: El IBAN debe ser un IBAN registrado.
     * Entrada:
     * 			-> un String con el IBAN del que se buscaran los movimientos
     * 			-> un int para el a�o.
     * Salida: arraylist de cadenas con el / los movimientos requeridos
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve un arraylist de TransferenciaImpl con los movimientos del a�o indicado.
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int anyo) {
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;


        return registros_buscados;
    }


    /*
     * INTERFAZ
     * Signatura: public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int mes, int anyo)
     * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
     * Precondiciones: El IBAN debe ser un IBAN registrado.
     * Entrada:
     * 			-> un String con el IBAN del que se buscaran los movimientos
     * 			-> un int para el mes
     * 			-> un int para el a�o
     * Salida: arraylist de cadenas con el / los movimientos requeridos
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve un arraylist con los movimientos del mes en el a�o indicado.
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int mes, int anyo) {
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;


        return registros_buscados;
    }


    /*
     * INTERFAZ
     * Signatura: public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia, int mes, int anyo)
     * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
     * Precondiciones: El IBAN debe ser un IBAN registrado.
     * Entrada:
     * 			-> String con el IBAN del que se buscaran los movimientos
     * 			-> int para el dia
     * 			-> int para el mes
     * 			-> int para el a�o
     * Salida: arraylist de cadenas con el / los movimientos requeridos
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve un arraylist con los movimientos en el dia indicado
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia, int mes, int anyo) {
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;


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
     * 					* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean marcarCuentaComoBorrada(String iban_cuenta) {
        boolean borrada = false;


        return borrada;
    }

    /*
     * INTERFAZ
     * Signatura: public List<TransferenciaImpl> ultimosDiezMovimientos(String iban_cuenta)
     * Comentario: devuelve los ultimos diez movimientos de la cuenta
     * Precondiciones: El IBAN debe estar registrado
     * Entrada: String iban
     * Salida: una lista de TransferenciaImpl
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre devuelve una lista de TransferenciaImpl con los ultimos 10 movimientos
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> ultimosDiezMovimientos(String iban_cuenta) {
        List<TransferenciaImpl> registros = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;

        return registros;
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerBICporNombre(String nombre_banco)
     * Comentario: devuelve el BIC de un banco dado su nombre
     * Precondiciones: No hay
     * Entrada: String nombre
     * Salida: String BIC
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve un String con el BIC del banco o un espacio en blanco si no existe el banco buscado.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerBICporNombre(String nombre_banco) {
        String bic = " ";

        return bic;
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta)
     * Comentario: devuelve el nombre de un banco dado el IBAN de una cuenta
     * Precondiciones: El IBAN debe tener una longitud minima de 14 caracteres
     * Entrada: String iban_cuenta
     * Salida: String nombre
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve un String con el nombre del banco comercial o un espacio en blanco si no existe el banco.
     * */
    public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta) {

        return " ";
    }

    /* INTERFAZ
     * Comentario: A partir de un IBAN, obtiene el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Prototipo: public String obtenerBICporIBAN(String IBAN)
     * Entrada: Un String con el IBAN
     * Precondiciones: El IBAN debe tener una longitud minima de 14 caracteres
     * Salida: Un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Postcondiciones: Asociado al nombre devuelve un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     */
    public String obtenerBICporIBAN(String IBAN){ return " ";
    }

    /* INTERFAZ
     * Comentario: Obtiene el numero de cuenta de un IBAN
     * Prototipo: public String obtenerNumCuentaPorIBAN(String IBAN)
     * Entrada: Un String con el IBAN del que se quiere obtener su numero de cuenta
     * Precondiciones: El IBAN debe tener una longitud minima de 21 caracteres
     * Salida: Un String con el numero de cuenta del IBAN especificado
     * Postcondiciones: Asociado al nombre devuelve un String con el numero de cuenta del IBAN especificado
     */
    public String obtenerNumCuentaPorIBAN(String IBAN) {
        return " ";
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerNombrePorBIC(String BIC)
     * Comentario: devuelve el nombre de un banco dando su BIC
     * Precondiciones:
     * Entrada: String BIC
     * Salida: String nombre
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve un String con el nombre del banco segun el BIC dado, o un espacio en blanco si no existe el banco.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerNombrePorBIC(String bic) {
        String nombre = " ";
        return nombre;
    }

    /* INTERFAZ
     * Signatura: public boolean ingresarDinero(String IBAN, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: ingresa una cantidad dada de una cuenta
     * Precondiciones: el IBAN tiene que estar registrado, y la cantidad debe ser positiva.
     * Entrada:
     * 				- String con el IBAN donde se hara el ingreso
     * 				- String con el concepto del ingreso
     * 				- double para la cantidad del ingreso
     * 				- GregorianCalendar para la fecha del ingreso.
     * Salida: Un boolean indiciando si se ha ingresado el dinero con exito o no
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 				-> true si se ha ingresado el dinero con exito, insertando el movimiento en el fichero de movimientos y modificando el saldo en el fichero de cuentas
     * 				-> false si no se ha podido realizar con exito la operacion
     * 				* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean ingresarDinero(String IBAN, String concepto, double cantidad, GregorianCalendar fecha) {
        boolean ingresado = false;


        return ingresado;
    }

    /* INTERFAZ
     * Signatura: public boolean realizarMovimiento(String IBANOrigen, String IBANDestino, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: Realiza un movimiento bancario, sacando una cantidad de la cuenta de origen e ingresÃ¡ndola en la cuenta destino.
     *              Llama a los metodos sacarDinero e ingresarDinero.
     * Precondiciones: el IBAN tiene que estar registrado, y la cantidad debe ser positiva.
     * Entrada: -> Un String con el el IBAN de origen
     * 			-> Un string con el IBAN de destino
     * 			-> Un string para el concepto de la transferencia
     * 			-> Un double para la cantidad
     * 			-> Un GregorianCalendar con la fecha de la transferencia
     * Salida: Un boolean indicando si se ha realizado bien el movimiento o no
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha realizado bien el movimiento
     * 					-> false si no se ha realizado bien el movimiento
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public boolean realizarMovimiento(String IBANOrigen, String IBANDestino, String concepto, double cantidad, GregorianCalendar fecha) {

        boolean movimientoRealizado = false;

        return movimientoRealizado;
    }

    /*INTERFAZ
     * Signatura: public boolean sacarDinero(String IBAN, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: saca una cantidad dada de una cuenta
     * Precondiciones: el IBAN tiene que estar registrado, y la cantidad debe ser positiva.
     * Entrada:
     * 			-> Un String con el el IBAN de origen
     * 			-> Un string con el IBAN de destino
     * 			-> Un string para el concepto de la transferencia
     * 			-> Un double para la cantidad
     * 			-> Un GregorianCalendar con la fecha de la transferencia
     * Salida: Un boolean indicando si se ha sacado bien el dinero
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha sacado bien el dinero de la cuenta, insertando el movimiento en el fichero de movimientos y modificando el saldo en el fichero de cuentas
     * 					-> false si no se ha podido realizar bien la operacion.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public boolean sacarDinero(String IBAN, String concepto, double cantidad, GregorianCalendar fecha) {

        boolean dineroSacado = false;


        return dineroSacado;
    }

    /* INTERFAZ
     * Comentario: Actualiza un fichero maestro determinado, mirando los registros de su fichero de movimiento correspondiente.
     * Prototipo: public boolean actualizarFichero(String fichero)
     * Entrada: Un string con la ruta del fichero y un int con la posicion que ocupa el campo clave en cada registro
     * Precondiciones: No hay
     * Salida: Un boolean indicando si se actualiz� correctamente el fichero maestro
     * Postcondiciones: Devuelve true si se actualiza� bien, flase de lo contrario
     * 					* Puede lanzar IOException si hay algun error al leer o escribir
     */
    public boolean actualizarFichero(String fichero, int posicionCampoClave) {
        boolean actualizado = false;


        return actualizado;
    }

    /* INTERFAZ
     * Comentario: Obtiene el saldo de una cuenta(IBAN)
     * Prototipo: public double obtenerSaldoPorIBAN(String IBAN)
     * Entrada: Un String con el IBAN del que se quiere obtener su saldo
     * Precondiciones: El IBAN ha de ser de una cuenta existente
     * Salida: un double con el saldo de la cuenta
     * Postcondiciones: Asociado al nombre devuelve un double con el saldo de la cuenta.
     */
    public double obtenerSaldoPorIBAN(String IBAN) {
    return 0;
    }
}
