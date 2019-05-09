/*Aquí están los resguardos de los métodos más complicados del Banco Comercial como por ejemplo:
* -Cobrar gastos administración cada X tiempo a X cuentas
* -Realizar movimientos en cuentas de cliente
* -La cosa esa de la reserva fraccionaria
* -Aplicar comisión por descubierto
*
* */
package resguardos;
import clasesBasicas.TransferenciaImpl;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ResguardoGestionBancoComercial {


    /*
     * INTERFAZ
     * Signatura: public double obtenerIngresosPorCliente(String dniCliente, String iban)
     * Comentario: Devuelve los ingresos mensuales de un cliente dado su DNI.
     * Precondiciones: Que el dni exista en el fichero maestro
     * Entradas: String dniCliente
     * Salida: double
     * Postcondiciones: asociado al nombre se devuelve un double que es el ingreso que tiene un cliente al mes
     * */
    public double obtenerIngresosPorClientes(String dniCliente, String iban){
        double ingresos = 0.0;
        return ingresos;
    }

    /*
     * INTERFAZ
     * Signatura: public double umbralNumerosRojos(String iban)
     * Comentario: Este metodo controla el umbral de saldo negativo que puede tener una cuenta.
     *               Una cuenta puede tener un saldo negativo de hasta un 20% de los ingresos de un
     *               cliente.
     * Precondiciones: El IBAN debe estar registrado.
     * Entradas: TransferenciaImpl transferencia
     * Salida: un double con el saldo minimo posible.
     * Postcondiciones: asociado al nombre se devuelve un double que es el saldo minimo que puede tener una cuenta
     * */
    public double umbralNumerosRojos(String iban){
        double saldoMinimo = 0.0;
        return saldoMinimo;
    }


    /*
     * INTERFAZ
     * Signatura: public String obtenerNuevoNumeroCuenta(String bic)
     * Comentario: Obtiene un nuevo numero de cuenta, siguiente al ultimo actual
     * Precondiciones: el bic debe existir
     * Entradas: String bic
     * Salidas: String numero de cuenta
     * Postcondiciones: Asociado al nombre devuelve un String con el nuevo numero de cuenta
     *					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerNuevoNumeroCuenta(String bic){
        String IBANUltimaCuenta = " ";
        return IBANUltimaCuenta;

    }

    /* INTERFAZ
     * Comentario: Realiza una solicitud de alta de un cliente.
     * Prototipo: public String solicitarAltaCliente(String BIC, String DNI, double ingresosMensuales)
     * Entrada:
     * 		-> Un string con el BIC del banco donde se insertara el nuevo cliente
     * 		-> un String con el DNI del cliente
     * 		-> un double con los ingresos mensuales del cliente
     * Precondiciones:
     * 		-> El BIC debe estar registrado
     * 		-> El DNI debe no estar registrado
     * 		-> Los ingresos mensuales no deben ser negativos
     * Salida: Un String indicando el IBAN de la cuenta asociada al cliente nuevo creado.
     * Postcondiciones: Asociado al nombre devuelve un String, que sera el IBAN de la cuenta asociada al cliente nuevo, o null
     * 					Si no se crea correctamente.
     * 					* Puede lanzar IOException si hay algun error al escribir
     */
    public String solicitarAltaCliente(String BIC, String DNI, double ingresosMensuales)
    {
        String IBAN = " ";
        return IBAN;
    }


    /*
     * Signatura: public void aceptarAltasBajasClientes (String bic)
     * Comentario: Acepta las solicitudes de alta y baja.
     * Precondiciones: el BIC debe estar registrado.
     * Entradas: String bic del banco
     * Salidas:
     * Postcondiciones: quedaran a�adidos todos los nuevos clientes, con sus correspondientes cuentas, y ficheros necesarios creados y/o modificados.
     * 				   Tambien quedaran eliminados las cuentas que tuvieran solicitud de baja.
     *					* Puede lanzar IOException si hay algun error al leer o escribir
     * */
    public void aceptarAltasBajasClientes (String BIC) {

    }


    /*
     * INTERFAZ
     * Signatura: public boolean crearFicheroCuentaTransferencias(String nuevo_iban)
     * Comentario: Crea un fichero de transferencias para el IBAN dado.
     * Precondiciones: No hay
     * Entrada: String iban
     * Salida: Un boolean indicando si se creo o no.
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre devuelve false si el fichero ya existe o si no se pudo crear. True si lo creo.
     * 					* Puede lanzar IOException si hay algun error al leer.
     * */
    public boolean crearFicheroCuentaTransferencias(String nuevo_iban){
        boolean exito = false;
        return exito;
    }

    /*
     * INTERFAZ
     * Signatura: public boolean marcarCuentaComoBorrada(String iban_cuenta)
     * Comentario: Marca como borrada con * una cuenta
     * Precondiciones: Se pasa un iban que debera existir.
     * Entrada: String con el IBAN de la cuenta a marcar como borrada.
     * Salida:
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si se marco correctamente la cuenta como borrada.
     * 					-> False si no se marco correctamente.
     * 					* Puede lanzar IOException si hay algun error al escribir.
     * */
    public boolean marcarCuentaComoBorrada(String iban_cuenta)
    {
        boolean borrada = false;


        return borrada;
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
     * Postcondiciones: asociado al nombre devuelve un arraylist
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int anyo){

        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;

        return registros_buscados;
    }

    /*
     * INTERFAZ
     * Signatura: public ArrayList<String> buscarMovimientosPorFecha(String iban_cuenta, int mes, int anyo)
     * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
     * Precondiciones: El IBAN debe ser un IBAN registrado.
     * Entrada:
     * 			-> un String con el IBAN del que se buscaran los movimientos
     * 			-> un int para el mes
     * 			-> un int para el a�o
     * Salida: arraylist de cadenas con el / los movimientos requeridos
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre devuelve un arraylist
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int mes, int anyo){

        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro= null;

        return registros_buscados;
    }

    /*
     * INTERFAZ
     * Signatura: public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia,int mes,int anyo)
     * Comentario: busca los movimientos que se hicieron en una cuenta en la fecha dada
     * Precondiciones: El IBAN debe ser un IBAN registrado.
     * Entrada:
     * 			-> String con el IBAN del que se buscaran los movimientos
     * 			-> int para el dia
     * 			-> int para el mes
     * 			-> int para el a�o
     * Salida: arraylist de cadenas con el / los movimientos requeridos
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre devuelve un arraylist
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia,int mes,int anyo){
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;

        return registros_buscados;
    }

    /*
     * INTERFAZ
     * Signatura: List<TransferenciaImpl> ultimosDiezMovimientos(String iban_cuenta)
     * Comentario: devuelve los ultimos diez movimientos de la cuenta
     * Precondiciones: El IBAN debe estar registrado.
     * Entrada: un String con el IBAN del que se buscaran los ultimos diez movimientos
     * Salida: una lista de TransferenciaImpl
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre devuelve una lista de TransferenciaImpl con los ultimos dies movimientos de la cuenta
     * 					* Puede lanzar IOException si hay algun error al leer
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public List<TransferenciaImpl> ultimosDiezMovimientos(String iban_cuenta){

        List<TransferenciaImpl> registros = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;

        return registros;
    }

    /*
     * INTERFAZ
     * Signatura: public boolean isIBANvalido(String iban_cuenta)
     * Comentario: Dado un iban devuelve true si este existe o false si no.
     * Precondiciones: No hay
     * Entrada: String iban_cuenta
     * Salida: boolean
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre se devuelve un boolean que devuelve true si existe y false si no
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public boolean isIBANvalido(String iban_cuenta){

        boolean isValido = false;

        return isValido;
    }

    /*
     * INTERFAZ
     * Signatura: public boolean isIBANParaBorrar(String iban_cuenta)
     * Comentario: Dado un iban devuelve true si est� marcado como borrado
     * Precondiciones: No hay
     * Entrada: String iban_cuenta
     * Salida: Un boolean indicando si esta marcado para borrar o no.
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre se devuelve un boolean que devuelve true si esta marcada como borrada y false si no
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public boolean isIBANParaBorrar(String iban_cuenta){

        boolean isMarcado = false;

        return isMarcado;
    }

    /*
     * INTERFAZ
     * Signatura: public boolean isPropietario(String dni_cliente, String iban_cuenta)
     * Comentario: Dado un iban y un dni de cliente, devuelve true si este iban pertenece a este cliente o false si no
     * Precondiciones: No hay
     * Entrada: String dni_cliente, String iban_cuenta
     * Salida: boolean
     * Entrada/Salida:
     * Postcondiciones: asociado al nombre se devuelve true si este iban pertenece a este cliente o false si no
     * 					* Puede lanzar IOException si hay algun error al leer
     */
    public boolean isPropietario(String dni_cliente, String iban_cuenta)
    {
        boolean isProp = false;

        return isProp;
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerClientePorIBAN(String iban_cuenta)
     * Comentario: dado el iban de la cuenta, te devuelve el cliente al que pertenece la cuenta
     * Precondiciones: se pasa el iban de la cuenta
     * Entrada: String iban_cuenta
     * Salida: String que es el dni del cliente
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve el DNI del cliente al que pertenece la cuenta o un espacio en blanco si no lo encuentra.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerClientePorIBAN(String iban_cuenta){

        String dni_cliente = " ";

        return dni_cliente;
    }
    /*
     * INTERFAZ
     * Signatura: public String obtenerIBANPorCliente(String nombre_banco, String dni_cliente)
     * Comentario: dado el nombre del banco y el dni del propietario de la cuenta, te devuelve el IBAN de la cuenta
     * Precondiciones: El nombre del banco debe existir.
     * Entrada: String nombre_banco, String dni_cliente
     * Salida: String que es el IBAN de la cuenta
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve el IBAN de la cuenta que pertenece a dicho cliente o un espacio en blanco si no existe.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerIBANPorCliente(String nombre_banco, String dni_cliente){

        String iban_cuenta = " ";

        return iban_cuenta;
    }

    /*
     * INTERFAZ
     * Signatura: public void ordenarMovimientosPorFecha(String iban)
     * Comentario: Ordena un fichero de movimientos de una cuenta en base a las fechas, deja primero los mas recientes.
     * Precondiciones: el IBAN debe estar registrado
     * Entrada: String IBAN
     * Salida:
     * Entrada/Salida:
     * Postcondiciones: Se modifica el fichero y se deja ordenado de forma descendente por fecha.
     * 					* Puede lanzar IOException si hay algun error al leer o escribir
     * 					* Puede lanzar ClassNotFoundException si la clase leida no se encuentra definida.
     * */
    public void ordenarMovimientosPorFecha(String iban){


    }

    /*
     * INTERFAZ
     * Signatura: public boolean insertarMovimientoEnFicheroMovimientos(String ID_Cuenta,boolean isIngresoOrRetirada, String concepto, double cantidad,GregorianCalendar fecha)
     * Comentario: Este metodo se encarga de modificar en el fichero de movimientos de la cuenta, a�adiendo un nuevo movimiento.
     * Precondiciones: La Cuenta debe existir.
     * Entrada: (String ID_Cuenta,boolean isIngresoOrRetirada, double cantidad,GregorianCalendar fecha)
     * Salida: boolean indicando si se inserto correctamente o no.
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve true si el movimiento se inserto correctamente o false si no.
     * 					* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean insertarMovimientoEnFicheroMovimientos(String ID_Cuenta,boolean isIngresoOrRetirada, String concepto, double cantidad,GregorianCalendar fecha){

        boolean movimientoInsertado = false;

        return movimientoInsertado;
    }

    /* INTERFAZ
     * Comentario: Obtiene el saldo de una cuenta(IBAN)
     * Prototipo: public double obtenerSaldoPorIBAN(String IBAN)
     * Entrada: Un String con el IBAN del que se quiere obtener su saldo
     * Precondiciones: El IBAN ha de ser de una cuenta existente
     * Salida: un double con el saldo de la cuenta
     * Postcondiciones: Asociado al nombre devuelve un double con el saldo de la cuenta.
     */
    public double obtenerSaldoPorIBAN(String IBAN)
    {
        return 0;
    }

    /* INTERFAZ
     * Comentario: Modifica el saldo de una cuenta
     * Prototipo: public boolean modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta, double cantidad)
     * Entrada:
     * 			-> Un String con el IBAN del que se modificar� su saldo
     * 			-> Un boolean indicando si se sumar� (true) o restar� (false) la cantidad
     * 			-> Un double con la cantidad
     * Precondiciones: No hay
     * Salida: Un boolean indicando si la modificaci�n del saldo tuvo exito o no
     * Postcondiciones: asociado al nombre devuelve:
     * 					-> true si se modific� correctamente el saldo de la cuenta
     * 					-> false si no se pudo modificar el saldo (Por ejemplo, en caso de que el IBAN no exista)
     */
    public boolean modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta, double cantidad)
    {

        boolean modificado = false;

        return modificado;
    }

    /* INTERFAZ
     * Comentario: Comprueba si un cliente (DNI) esta registrado en un banco(BIC), ya sea porque este dado de alta, o la haya solicitado.
     * Prototipo: public boolean DNIRegistrado(String DNI, String BIC)
     * Entrada: Un string con el DNI a comprobar, y un String con el BIC del banco donde se quiere comprobar
     * Precondiciones: el BIC debe ser de un banco existente.
     * Salida: Un boolean indicando si el DNI esta registrado en el banco o no
     * Postcondiciones: Asociado al nombre deuvelve true si el DNI esta registrado en el banco (BIC), o false si no lo esta.
     * 					* Puede lanzar IOException si hay algun error al leer
     */
    public boolean DNIRegistrado(String DNI, String BIC)
    {
        boolean registrado = false;


        return registrado;
    }

    /* INTERFAZ
     * Comentario: crea un nuevo cliente y una cuenta asociada a el en un banco determinado.
     * Prototipo: public String insertarCliente(String BIC, String DNI, double ingresosMensuales)
     * Entrada:
     * 		-> Un string con el BIC del banco donde se insertara el nuevo cliente
     * 		-> un String con el DNI del cliente
     * 		-> un double con los ingresos mensuales del cliente
     * Precondiciones:
     * 				   -> El BIC debe ser de un banco existente.
     * 				   -> Los ingresos mensuales no deben ser negativos.
     * Salida: Un String indicando el IBAN de la cuenta asociada al cliente nuevo creado.
     * Postcondiciones: Asociado al nombre devuelve un String, que sera el IBAN de la cuenta asociada al cliente nuevo, o null
     * 					Si no se creo correctamente.
     * 					* Puede lanzar IOException si hay algun error al leer o escribir
     */
    public String insertarCliente(String BIC, String DNI, double ingresosMensuales)
    {
        String IBAN = " ";
        return IBAN;
    }

    /* INTERFAZ
     * Comentario: Accede al fichero de cuentas y busca una cuenta por su IBAN para leer sus datos
     * Prototipo: public String datosCuenta(String IBAN)
     * Precondiciones: No hay
     * Entrada: el IBAN de la cuenta.
     * Salida: un String con los datos de la cuenta
     * Postcondiciones: Asociado al nombre devuelve un String con los datos de la cuenta o null si no existe la cuenta
     * 					* Puede lanzar IOException si hay algun error al leer
     */
    public String datosCuenta(String IBAN)
    {
        String cuenta = null;

        return cuenta;
    }

    /* INTERFAZ
     * Comentario: Escribe un registro nuevo en un fichero de movimientos determinado
     * Prototipo: public boolean escribirRegistroEnMovimientos(String registro, String rutaFichero)
     * Entrada: Un String con el registro a escribir, y otro String con la ruta del fichero donde se escribira.
     * Precondiciones: No hay
     * Salida: Un boolean indicando si se ha escrito correctamente o no.
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si se ha escrito correctamente el registro en el fichero correspondiente
     * 					-> False si no se ha escrito correctamente.
     * 					-> Devuelve false si el fichero no existe
     * 					* Puede lanzar IOException si hay algun error al escribir.
     */
    public boolean escribirRegistroEnMovimientos(String registro, String rutaFichero)
    {
        boolean escrito = false;

        return escrito;
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerBICporNombre(String nombre_banco)
     * Comentario: devuelve el BIC de un banco dando su nombre
     * Precondiciones: No hay
     * Entrada: String nombre
     * Salida: String BIC
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve un String con el BIC del banco. Si no existe devuelve un espacio en blanco.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerBICporNombre(String nombre_banco){
        String bic = " ";
        return bic;
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta)
     * Comentario: devuelve el nombre de un banco dado el IBAN de una cuenta
     * Precondiciones: El IBAN debe tener minimo una longitud de 14 caracteres
     * Entrada: String iban_cuenta
     * Salida: String nombre
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve un String con el nombre del banco comercial.
     * */
    public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta){

        return " ";
    }

    /* INTERFAZ
     * Comentario: A partir de un IBAN, obtiene el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Prototipo: public String obtenerBICporIBAN(String IBAN)
     * Entrada: Un String con el IBAN
     * Precondiciones: El IBAN debe tener minimo una longitud de 14 caracteres
     * Salida: Un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Postcondiciones: Asociado al nombre devuelve un strnig con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     */
    public String obtenerBICporIBAN(String IBAN)
    {
        return " ";
    }

    /* INTERFAZ
     * Comentario: Obtiene el numero de cuenta de un IBAN
     * Prototipo: public String obtenerNumCuentaPorIBAN(String IBAN)
     * Entrada: Un String con el IBAN del que se quiere obtener su numero de cuenta
     * Precondiciones: El IBAN debe tener minimo una longitud de 21 caracteres
     * Salida: Un String con el numero de cuenta del IBAN especificado
     * Postcondiciones: Asociado al nombre devuelve un String con el numero de cuenta del IBAN especificado
     */
    public String obtenerNumCuentaPorIBAN(String IBAN)
    {
        return " ";
    }

    /*
     * INTERFAZ
     * Signatura: public String obtenerNombrePorBIC(String BIC)
     * Comentario: devuelve el nombre de un banco dando su BIC
     * Precondiciones: No hay
     * Entrada: String BIC
     * Salida: String nombre
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre se devuelve un String con el nombre del banco, o un espacio en blanco si no lo encuentra.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    public String obtenerNombrePorBIC(String bic){
        String nombre = " ";

        return nombre;
    }

    /* INTERFAZ
     * Signatura: public boolean ingresarDinero(String IBAN,String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: ingresa una cantidad dada en una cuenta
     * Precondiciones: el IBAN debe existir y la cantidad debe ser positiva
     * Entrada: String nombre_banco, String ID_Cuenta, double cantidad, GregorianCalendar fecha
     * Salida: Un boolean indicando si se ha ingresado el dinero con exito o no
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 				-> true si se ha ingresado el dinero con exito, insertando el movimiento en el fichero de movimientos y modificando el saldo en el fichero de cuentas
     * 				-> false si no se ha podido realizar con exito la operacion
     * 				* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean ingresarDinero(String IBAN,String concepto, double cantidad, GregorianCalendar fecha){

        boolean ingresado = false;


        return ingresado;

    }

    /* INTERFAZ
     * Signatura: public boolean realizarMovimiento(String IBANOrigen,String IBANDestino, String concepto,double cantidad, GregorianCalendar fecha)
     * Comentario: Realiza un movimiento bancario, sacando una cantidad de la cuenta de origen e ingresandola en la cuenta destino.
     *              Llama a los metodos sacarDinero e ingresarDinero.
     * Precondiciones: IBAN de origen y de destino deben existir, y la cantidad debe ser positiva.
     * Entrada: String IBANOrigen
     * 			String IBANDestino
     * 			String concepto
     *			double cantidad
     *			GregorianCalendar fecha
     * Salida: Un boolean indicando si se ha realizado bien el movimiento o no
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha realizado bien el movimiento
     * 					-> false si no se ha realizado bien el movimiento
     * 					* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean realizarMovimiento(String IBANOrigen,String IBANDestino, String concepto,double cantidad, GregorianCalendar fecha){

        boolean movimientoRealizado = false;


        return movimientoRealizado;
    }

    /*INTERFAZ
     * Signatura: public boolean sacarDinero(String IBAN,String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: saca una cantidad dada de una cuenta
     * Precondiciones: El IBAN debe existir y la cantidad debe ser positiva.
     * Entrada: String IBAN
     * 			String concepto
     * 			double cantidad
     * 			GregorianCalendar fecha
     * Salida: Un boolean indicando si se ha sacado bien el dinero
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> true si se ha sacado bien el dinero de la cuenta, insertando el movimiento en el fichero de movimientos y modificando el saldo en el fichero de cuentas
     * 					-> false si no se ha podido realizar bien la operacion.
     * 					* Puede lanzar IOException si hay algun error al escribir
     * */
    public boolean sacarDinero(String IBAN,String concepto, double cantidad, GregorianCalendar fecha){

        boolean dineroSacado = false;

        return dineroSacado;
    }

    /* INTERFAZ
     * Comentario: Actualiza un fichero maestro determinado, mirando los registros de su fichero de movimiento correspondiente.
     * Prototipo: public boolean actualizarFichero(String fichero, int posicionCampoClave)
     * Entrada: Un string con la ruta (sin especificar la parte de "_Maestro.txt" o "_Movimientos.txt) del fichero
     * Precondiciones: Los ficheros deben existir
     * Salida: Un boolean indicando si se ha actualizado correctamente o no.
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si la sincronizacion tuvo exito, por lo tanto, los registros del fichero de movimientos quedan actualizados al maestro.
     * 					-> False si hubo algun error y no se pudo sincronizar.
     * 					* Puede lanzar IOException si hay algun error al leer o escribir
     */
    public boolean actualizarFichero(String fichero, int posicionCampoClave)
    {
        boolean actualizado = false;

        return actualizado;
    }

}
