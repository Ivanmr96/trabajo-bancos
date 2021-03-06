package gestion;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import clasesBasicas.ClienteImpl;
import clasesBasicas.CuentaImpl;
import clasesBasicas.TransferenciaImpl;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;


public class GestionBancoComercial {

    /*
     * INTERFAZ
     * Signatura: public double obtenerIngresosPorCliente(String dniCliente, String iban)
     * Comentario: Devuelve los ingresos mensuales de un cliente dado su DNI.
     * Precondiciones: Que el dni exista en el fichero maestro
     * Entradas: String dniCliente
     * Salida: double
     * Postcondiciones: asociado al nombre se devuelve un double que es el ingreso que tiene un cliente al mes
     * */

    /**
     * Devuelve los ingresos mensuales de un cliente dado su DNI.
     * @param dniCliente El DNI del cliente del cual se desea obtener los ingresos.
     * @param iban El IBAN de la cuenta del cliente del cual se desea obtener los ingresos.
     * @return Asociado al nombre se devuelve un double que es el ingreso que tiene el cliente al mes
     */
    public double obtenerIngresosPorClientes(String dniCliente, String iban){
        double ingresos = 0.0;
        String nombreBanco = obtenerNombreBancoComercialPorIBAN(iban);
        File ficheroClientes = new File("./Files/BancosComerciales/"+nombreBanco+"/Clientes_"+nombreBanco+"_Maestro.txt");
        FileReader fr = null;
        BufferedReader br = null;
        String registro = " ";
        try{
            fr = new FileReader(ficheroClientes);
            br = new BufferedReader(fr);

            while (br.ready()){
                registro = br.readLine();

                if(registro.split(",")[1].equals(dniCliente)){
                    ingresos = Double.parseDouble(registro.split(",")[2]);
                }
            }

            br.close();
            fr.close();
        }catch (IOException e){
            e.getStackTrace();
        }
        return ingresos;
    }

    /*
    * INTERFAZ
    * Signatura: public double umbralNumerosRojos(String iban)
    * Comentario: Este metodo controla el umbral de saldo negativo que puede tener una cuenta.
    *               Una cuenta puede tener un saldo negativo de hasta un 20% de los ingresos de un
    *               cliente.
    * Precondiciones: El IBAN debe estar registrado.
    * Entradas: String iban
    * Salida: un double con el saldo minimo posible.
    * Postcondiciones: asociado al nombre se devuelve un double que es el saldo minimo que puede tener una cuenta
    * */

    /**
     *  Este metodo controla el umbral de saldo negativo que puede tener una cuenta.
     *  Una cuenta puede tener un saldo negativo de hasta un 20% de los ingresos de un cliente.
     *
     * @param iban El IBAN debe estar registrado.
     * @return asociado al nombre se devuelve un double que es el saldo minimo que puede tener una cuenta
     */

    public double umbralNumerosRojos(String iban){
        double saldoMinimo = 0.0;
        String dniCliente = this.obtenerClientePorIBAN(iban);
        double ingresosCliente = obtenerIngresosPorClientes(dniCliente, iban);
        saldoMinimo = (ingresosCliente * 0.2) * -1;    //saca el 20% y lo convierte en negativo

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

    /**
     * Obtiene un nuevo numero de cuenta, siguiente al ultimo actual
     * @param bic BIC del banco del cual se va a generarl nuevo numero de cuenta.
     * @return Asociado al nombre devuelve un String con el nuevo numero de cuenta
     */
    public String obtenerNuevoNumeroCuenta(String bic){
        String nombreBanco = this.obtenerNombrePorBIC(bic);
        File ficheroCuentas = new File("./Files/BancosComerciales/" + nombreBanco + "/Cuentas_" + nombreBanco + "_Maestro.txt");
        File ficheroCuentasMov = new File("./Files/BancosComerciales/" + nombreBanco + "/Cuentas_" + nombreBanco + "_Movimientos.txt");
        Utilidades util = new Utilidades();
        FileReader fr = null;
        BufferedReader br = null;
        String registro = null;
        String[] campos = null;
        String IBANUltimaCuenta = null;
        String IBANUltimaCuentaMov = null;
        String numeroCuentaUltima = null;
        String numeroCuentaUltimaMov = null;
        String IBAN = null;

        util.ordenarFicheroPorClave(ficheroCuentas.getPath(), 0);
        util.ordenarFicheroPorClave(ficheroCuentasMov.getPath(), 0);

        //Ultimo numero de cuenta
        try {
            fr = new FileReader(ficheroCuentas);
            br = new BufferedReader(fr);

            while (br.ready()) {
                registro = br.readLine();

                if (br.ready() == false) {
                    campos = registro.split(",");
                    if (campos != null) {
                        IBANUltimaCuenta = campos[0];

                        numeroCuentaUltima = this.obtenerNumCuentaPorIBAN(IBANUltimaCuenta);
                    }
                }
            }
            br.close();
            fr.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
            fr = new FileReader(ficheroCuentasMov);
            br = new BufferedReader(fr);

            while (br.ready()) {
                registro = br.readLine();
                if (br.ready() == false) {
                    campos = registro.split(",");
                    if (campos != null) {
                        IBANUltimaCuentaMov = campos[0];
                        numeroCuentaUltimaMov = this.obtenerNumCuentaPorIBAN(IBANUltimaCuentaMov);

                    }

                }
            }
            br.close();
            fr.close();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

          if (IBANUltimaCuenta == null){
              IBANUltimaCuenta = "ESP" + bic + "0000000";
          }

            //Coge el ultimo numero de cuenta dado, mirando tanto en el fichero maestro como en el de movimientos
            if(IBANUltimaCuentaMov != null && IBANUltimaCuenta != null) {

                if (Integer.parseInt(numeroCuentaUltimaMov) > Integer.parseInt(numeroCuentaUltima)) {
                    IBANUltimaCuenta = IBANUltimaCuentaMov;
                    numeroCuentaUltima = numeroCuentaUltimaMov;
                }
            }

        numeroCuentaUltima = String.valueOf((Integer.parseInt(numeroCuentaUltima) + 1));

        while (numeroCuentaUltima.length() < 7) {
            numeroCuentaUltima = "0" + numeroCuentaUltima;
        }
        IBANUltimaCuenta = "ESP"+bic+numeroCuentaUltima;


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

    /**
     * Realiza una solicitud de alta de un cliente.
     * @param BIC Un string con el BIC del banco donde se insertara el nuevo cliente
     * @param DNI un String con el DNI del cliente
     * @param ingresosMensuales un double con los ingresos mensuales del cliente
     * @return Un String indicando el IBAN de la cuenta asociada al cliente nuevo creado.
     */
    public String solicitarAltaCliente(String BIC, String DNI, double ingresosMensuales)
    {
        Utilidades u = new Utilidades();
        //Insertar marca de alta en el fichero de movimientos
        String registroCliente = new ClienteImpl(BIC, DNI,ingresosMensuales).toString();
        String nombreBanco = this.obtenerNombrePorBIC(BIC);
        File ficheroClientes = new File("./Files/BancosComerciales/" + nombreBanco + "/Clientes_" + nombreBanco + "_Movimientos.txt");
        File ficheroCuentas = new File("./Files/BancosComerciales/" + nombreBanco + "/Cuentas_" + nombreBanco + "_Movimientos.txt");
        File ficheroClientesCuentas = new File("./Files/BancosComerciales/" + nombreBanco + "/Clientes_Cuentas_" + nombreBanco + "_Movimientos.txt");


        String IBAN = this.obtenerNuevoNumeroCuenta(BIC);

        CuentaImpl cuenta = new CuentaImpl(IBAN);
        String registroCuenta = cuenta.toString();
        String registroClienteCuenta = DNI + "," + cuenta.getIBAN();
        if(ficheroClientes.exists() && ficheroCuentas.exists() && ficheroClientesCuentas.exists()) {
            u.escribirRegistroEnFichero(registroCliente, ficheroClientes.getPath());
            u.escribirRegistroEnFichero(registroCuenta, ficheroCuentas.getPath());
            u.escribirRegistroEnFichero(registroClienteCuenta, ficheroClientesCuentas.getPath());

        }

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

    /**
     * Acepta las solicitudes de alta y baja.
     * @param BIC BIC del banco de donde se van a aceptar las solicitudes.
     */
    public void aceptarAltasBajasClientes (String BIC) {
        Utilidades utils = new Utilidades();
        String nombreBanco = this.obtenerNombrePorBIC(BIC);
        File ficheroCuentas = new File("./Files/BancosComerciales/"+nombreBanco+"/Cuentas_"+nombreBanco+"_Movimientos.txt");
        String IBAN = " ";
        String registro = " ";
        FileReader fr = null;
        BufferedReader br = null;
        //Crear fichero Transferencias
        try{
            fr = new FileReader(ficheroCuentas);
            br = new BufferedReader(fr);
            while(br.ready() ){
                registro = br.readLine();
                IBAN = registro.split(",")[0];
                if (registro.contains("*")) {

                    //Eliminar el fichero de las transferencias si es una baja
                    utils.borrarFichero("./Files/BancosComerciales/"+nombreBanco+"/Transferencias/Transferencias_Cuenta_"+IBAN+".dat");

                }else{

                    //Crear fichero transferencias si es un alta
                    this.crearFicheroCuentaTransferencias(IBAN);

                }
            }

            br.close();
            fr.close();
        }catch ( IOException e){
            e.printStackTrace();
        }

        //Actualizar fichero maestro
        actualizarFichero("./Files/BancosComerciales/" + nombreBanco + "/Clientes_" + nombreBanco, 1);
        actualizarFichero("./Files/BancosComerciales/" + nombreBanco + "/Cuentas_" + nombreBanco, 0);
        actualizarFichero("./Files/BancosComerciales/" + nombreBanco + "/Clientes_Cuentas_" + nombreBanco, 0);

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

    /**
     * Crea un fichero de transferencias para el IBAN dado.
     * @param nuevo_iban IBAN para el que se creará el fichero de transferencias.
     * @return boolean que sera true si el fichero se ha creado correctamente y false si no o si bien ya existia.
     */
    public boolean crearFicheroCuentaTransferencias(String nuevo_iban){
        String nombreBanco = obtenerNombreBancoComercialPorIBAN(nuevo_iban);
        File carpetaTransferencias = new File("./Files/BancosComerciales/"+nombreBanco+"/Transferencias/");
        File fichero_nuevo = null;
        ObjectOutputStream oos = null;
        boolean exito = false;

        fichero_nuevo = new File(carpetaTransferencias, "Transferencias_Cuenta_"+nuevo_iban+".dat");
        //Crea el fichero
        try{
            oos = new ObjectOutputStream(new FileOutputStream(fichero_nuevo));
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        if(fichero_nuevo.exists()){
            exito = true;
        }

        return exito;
    }

    /*
     * INTERFAZ
     * Signatura: public boolean marcarCuentaComoBorrada(String iban_cuenta)
     * Comentario: Marca como borrada con * una cuenta
     * Precondiciones: Se pasa un iban que debera existir.
     * Entrada: String con el IBAN de la cuenta a marcar como borrada.
     * Salida: boolean
     * Entrada/Salida:
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si se marco correctamente la cuenta como borrada.
     * 					-> False si no se marco correctamente.
     *
     * */

    /**
     * Marca como borrada con * una cuenta
     * @param iban_cuenta String con el IBAN de la cuenta a marcar como borrada.
     * @return Asociado al nombre devuelve:
     *     -True si se marco correctamente la cuenta como borrada.
     *     -False si no se marco correctamente.
     *
     */
    public boolean marcarCuentaComoBorrada(String iban_cuenta)
    {
        boolean borrada = false;
        Utilidades u = new Utilidades();
        String registroCuenta = iban_cuenta + ",*";
        String registroCliente = this.obtenerBICporIBAN(iban_cuenta) + "," + this.obtenerClientePorIBAN(iban_cuenta) + ",*," + this.obtenerSaldoPorIBAN(iban_cuenta);
        String registroClientesCuentas = this.obtenerClientePorIBAN(iban_cuenta) + "," + iban_cuenta + ",*";
        
        if(isIBANvalido(iban_cuenta))
        {
            u.escribirRegistroEnFichero(registroCuenta, "./Files/BancosComerciales/"+obtenerNombreBancoComercialPorIBAN(iban_cuenta)+"/Cuentas_"+obtenerNombreBancoComercialPorIBAN(iban_cuenta)+"_Movimientos.txt");
            u.escribirRegistroEnFichero(registroCliente, "./Files/BancosComerciales/"+obtenerNombreBancoComercialPorIBAN(iban_cuenta)+"/Clientes_"+obtenerNombreBancoComercialPorIBAN(iban_cuenta)+"_Movimientos.txt");
            u.escribirRegistroEnFichero(registroClientesCuentas, "./Files/BancosComerciales/"+obtenerNombreBancoComercialPorIBAN(iban_cuenta)+"/Clientes_Cuentas_"+obtenerNombreBancoComercialPorIBAN(iban_cuenta)+"_Movimientos.txt");
        	borrada = true;
        }

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

    /**
     * Busca los movimientos que se hicieron en una cuenta en la fecha dada
     * @param IBAN  un String con el IBAN del que se buscaran los movimientos
     * @param anyo un int para el año.
     * @return asociado al nombre devuelve un arraylist con los movimientos buscados, o null si no hay.
     */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int anyo){
        File file_movimientos = new File("./Files/BancosComerciales/"+obtenerNombrePorBIC(obtenerBICporIBAN(IBAN))+"/Transferencias/Transferencias_Cuenta_"+IBAN+".dat");
        ObjectInputStream leer = null;
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;
        boolean cont = true;

       try {
            leer = new ObjectInputStream(new FileInputStream(file_movimientos));
            while (cont) {
                registro = (TransferenciaImpl) leer.readObject();
                if (registro.getFecha().get(Calendar.YEAR) == anyo) {
                    registros_buscados.add(registro);
                }
            }
            leer.close();
        }catch (EOFException e){
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

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

    /**
     * Busca los movimientos que se hicieron en una cuenta en la fecha dada
     * @param IBAN un String con el IBAN del que se buscaran los movimientos
     * @param mes un int para el mes
     * @param anyo un int para el año
     * @return asociado al nombre devuelve un arraylist con los movimientos buscados, o null si no hay.
     */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int mes, int anyo){
        File file_movimientos = new File("./Files/BancosComerciales/"+obtenerNombrePorBIC(obtenerBICporIBAN(IBAN))+"/Transferencias/Transferencias_Cuenta_"+IBAN+".dat");
        ObjectInputStream leer = null;
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro= null;
        boolean cont = true;


        try {
            leer = new ObjectInputStream(new FileInputStream(file_movimientos));
            while (cont) {
                registro = (TransferenciaImpl) leer.readObject();
                if (registro.getFecha().get(Calendar.YEAR) == anyo && registro.getFecha().get(Calendar.MONTH) == mes - 1) {
                    registros_buscados.add(registro);
                }
            }
            leer.close();
        }catch (EOFException e){

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

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

    /**
     * Busca los movimientos que se hicieron en una cuenta en la fecha dada
     * @param IBAN un String con el IBAN del que se buscaran los movimientos
     * @param dia un int para el dia
     * @param mes un int para el mes
     * @param anyo un int para el año
     * @return asociado al nombre devuelve un arraylist con los movimientos buscados, o null si no hay.
     */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia,int mes,int anyo){
        File file_movimientos = new File("./Files/BancosComerciales/"+obtenerNombrePorBIC(obtenerBICporIBAN(IBAN))+"/Transferencias/Transferencias_Cuenta_"+IBAN+".dat");
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;
        ObjectInputStream leer = null;
        boolean cont = true;
        try {
            leer = new ObjectInputStream(new FileInputStream(file_movimientos));
            while (cont) {
                registro = (TransferenciaImpl) leer.readObject();
                if (registro.getFecha().get(Calendar.YEAR) == anyo && registro.getFecha().get(Calendar.MONTH) == mes - 1 && registro.getFecha().get(Calendar.DAY_OF_MONTH) == dia) {
                    registros_buscados.add(registro);
                }
            }
            leer.close();
        }catch (EOFException e){

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

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

    /**
     * Devuelve los ultimos diez movimientos de la cuenta
     * @param iban_cuenta un String con el IBAN del que se buscaran los ultimos diez movimientos
     * @return una lista de TransferenciaImpl con los ultimos diez movimientos (o menos si hay menos de diez), o null si no hay ninguno.
     */
 public List<TransferenciaImpl> ultimosDiezMovimientos(String iban_cuenta){

        File f_cuentas = new File("./Files/BancosComerciales/"+obtenerNombrePorBIC(obtenerBICporIBAN(iban_cuenta))+"/Transferencias/Transferencias_Cuenta_" + iban_cuenta + ".dat");
        ObjectInputStream leer = null;
        List<TransferenciaImpl> registros = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;
        int lineas=0;
        boolean cont = true;


        if (f_cuentas.exists()){
            try {
                leer = new ObjectInputStream(new FileInputStream(f_cuentas));

                while (cont && lineas < 10) {
                    registro = (TransferenciaImpl) leer.readObject();
                    registros.add(registro);
                    lineas++;
                }

            }catch (EOFException e){
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
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

    /**
     * Dado un iban devuelve true si este existe o false si no.
     * @param iban_cuenta  IBAN de la cuenta a revisar
     * @return asociado al nombre se devuelve un boolean que devuelve true si existe y false si no
     */
    public boolean isIBANvalido(String iban_cuenta){
        String nombre_banco = " ";
        File f_cuentas = null;
        FileReader fr = null;
        BufferedReader br = null;
        boolean isValido = false;

        if(iban_cuenta.length() >= 13) {
            nombre_banco = obtenerNombreBancoComercialPorIBAN(iban_cuenta);
            f_cuentas = new File("./Files/BancosComerciales/" + nombre_banco + "/Cuentas_" + nombre_banco + "_Maestro.txt");
            if (f_cuentas.exists()){
                try {
                    fr = new FileReader(f_cuentas);
                    br = new BufferedReader(fr);
                    while (br.ready()) {
                        if (br.readLine().split(",")[0].equals(iban_cuenta)) {
                            isValido = true;
                        }
                    }
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    /**
     * Dado un iban devuelve true si esta marcado como borrado
     * @param iban_cuenta IBAN de la cuenta a revisar si esta marcada como borrada
     * @return  asociado al nombre se devuelve un boolean que devuelve true si esta marcada como borrada y false si no
     */
    public boolean isIBANParaBorrar(String iban_cuenta){
        String nombre_banco = " ";
        File f_cuentasMov = null;
        FileReader fr = null;
        BufferedReader br = null;
        String registro = " ";
        boolean isMarcado = false;

        if(iban_cuenta.length() >= 13) {
            nombre_banco = obtenerNombreBancoComercialPorIBAN(iban_cuenta);
            f_cuentasMov = new File("./Files/BancosComerciales/" + nombre_banco + "/Cuentas_" + nombre_banco + "_Movimientos.txt");
            if (f_cuentasMov.exists()){
                try {
                    fr = new FileReader(f_cuentasMov);
                    br = new BufferedReader(fr);
                    while (br.ready()) {
                        registro = br.readLine();

                        if (registro.split(",")[0].equals(iban_cuenta) && registro.contains("*")) {
                            isMarcado = true;
                        }
                    }
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    /**
     * Dado un iban y un dni de cliente, devuelve true si este iban pertenece a este cliente o false si no
     * @param dni_cliente DNI del cliente a comprobar
     * @param iban_cuenta IBAN de la cuenta a comprobar
     * @return asociado al nombre se devuelve true si este iban pertenece a este cliente o false si no
     */
    public boolean isPropietario(String dni_cliente, String iban_cuenta)
    {
        String nombre_banco = obtenerNombreBancoComercialPorIBAN(iban_cuenta);
        File f_clientes_cuentas = new File("./Files/BancosComerciales/"+nombre_banco+"/Clientes_Cuentas_"+nombre_banco+"_Maestro.txt");
        FileReader fr = null;
        BufferedReader br = null;
        boolean isProp = false;
        String registro = " ";

        if(f_clientes_cuentas.exists()) {
            try {
                fr = new FileReader(f_clientes_cuentas);
                br = new BufferedReader(fr);
                while (br.ready()) {
                    registro = br.readLine();
                    if (registro.split(",")[0].equals(dni_cliente) && registro.split(",")[1].equals(iban_cuenta)) {
                        isProp = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    /**
     * dado el iban de la cuenta, te devuelve el cliente al que pertenece la cuenta
     * @param iban_cuenta IBAN de la cuenta a comprobar
     * @return Asociado al nombre se devuelve el DNI del cliente al que pertenece la cuenta o un espacio en blanco si no lo encuentra.
     */
    public String obtenerClientePorIBAN(String iban_cuenta){
        String nombre_banco = obtenerNombreBancoComercialPorIBAN(iban_cuenta);
        File fichero_clientes_cuentas = new File("./Files/BancosComerciales/"+nombre_banco+"/Clientes_Cuentas_"+nombre_banco+"_Maestro.txt");
        FileReader fr = null;
        BufferedReader br = null;
        String registro = " ";
        String dni_cliente = " ";

        try{
            fr = new FileReader(fichero_clientes_cuentas);
            br = new BufferedReader(fr);
            while (br.ready()){
                registro = br.readLine();

                if (registro.split(",")[1].equals(iban_cuenta)){
                    dni_cliente = registro.split(",")[0];
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
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

    /**
     * dado el nombre del banco y el dni del propietario de la cuenta, te devuelve el IBAN de la cuenta
     * @param nombre_banco Nombre del banco donde mirara el IBAN
     * @param dni_cliente DNI del cliente cuya cuenta buscara
     * @return Asociado al nombre se devuelve el IBAN de la cuenta que pertenece a dicho cliente o un espacio en blanco si no existe.
     */
    public String obtenerIBANPorCliente(String nombre_banco, String dni_cliente){
        File fichero_clientes_cuentas = new File("./Files/BancosComerciales/"+nombre_banco+"/Clientes_Cuentas_"+nombre_banco+"_Maestro.txt");
        FileReader fr = null;
        BufferedReader br = null;
        String registro = " ";
        String iban_cuenta = " ";

        try{
            fr = new FileReader(fichero_clientes_cuentas);
            br = new BufferedReader(fr);
            while (br.ready()){
                registro = br.readLine();

                if (registro.split(",")[0].equals(dni_cliente.toUpperCase())){
                    iban_cuenta = registro.split(",")[1];
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
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

    /**
     * Ordena un fichero de movimientos de una cuenta en base a las fechas, deja primero los mas recientes.
     * @param iban IBAN de la cuenta cuyos movimientos ordeanara por fecha
     */
    public void ordenarMovimientosPorFecha(String iban){
        String nombre_banco = obtenerNombreBancoComercialPorIBAN(iban);
        File ficheroMovimientosCuenta = new File ("./Files/BancosComerciales/"+nombre_banco+"/Transferencias/Transferencias_Cuenta_"+iban+".dat");
        File temp = new File ("./Files/BancosComerciales/"+nombre_banco+"/Transferencias/Transferencias_Cuenta_"+iban+"_temp.dat");
        ObjectInputStream leer = null;
        ObjectOutputStream escribir = null;
        MyObjectOutputStream escribirMyObject = null;
        List<TransferenciaImpl> registros = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;
        TransferenciaImpl aux = null;
        boolean cont = true;
        Utilidades util = new Utilidades();

        try {
            leer = new ObjectInputStream(new FileInputStream(ficheroMovimientosCuenta));
            while (cont) {
                registro = (TransferenciaImpl) leer.readObject();
                registros.add(registro);
            }
            leer.close();
        }catch (EOFException e){

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        //ordenamiento burbuja

        for (int i = 0; i < registros.size()-1;i++){
            for (int j = registros.size()-1; j>i; j--){
                if (registros.get(j-1).getFecha().before(registros.get(j).getFecha()) || registros.get(j).getFecha().equals(registros.get(j-1).getFecha()) ) {
                    //se produce el intercambio de elementos
                    aux = registros.get(j);
                    registros.set(j,registros.get(j-1));
                    registros.set(j-1, aux);
                }
            }
        }

        try{
            escribir = new ObjectOutputStream(new FileOutputStream(temp));

            for(int i = 0; i < registros.size(); i ++){
                escribir.writeObject(registros.get(i));

            }
            escribir.close();
        }catch ( IOException e ){
            e.printStackTrace();
        }

        util.renombrarFicheroBinario(temp.getPath(),ficheroMovimientosCuenta.getPath(),registro);

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

    /**
     *  Este metodo se encarga de modificar en el fichero de movimientos de la cuenta, a�adiendo un nuevo movimiento.
     * @param ID_Cuenta IBAN de la cuenta
     * @param isIngresoOrRetirada boolean que es true si el movimiento es un ingreso y false si es una retirada de saldo
     * @param concepto Concepto de la transferencia
     * @param cantidad Cantidad de la transferencia
     * @param fecha Fecha de la transferencia
     * @return Asociado al nombre devuelve true si el movimiento se inserto correctamente o false si no.
     */
     public boolean insertarMovimientoEnFicheroMovimientos(String ID_Cuenta,boolean isIngresoOrRetirada, String concepto, double cantidad,GregorianCalendar fecha){
        String nombre_banco = obtenerNombreBancoComercialPorIBAN(ID_Cuenta);
        File ficheroCuentas = new File ("./Files/BancosComerciales/"+nombre_banco+"/Transferencias/Transferencias_Cuenta_"+ID_Cuenta+".dat");
        TransferenciaImpl trans = new TransferenciaImpl(ID_Cuenta,isIngresoOrRetirada,concepto,cantidad,fecha);
        boolean movimientoInsertado = false;

        MyObjectOutputStream oos = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(fecha);
        
        try
        {
            oos = new MyObjectOutputStream(new FileOutputStream(ficheroCuentas,true));
            oos.writeObject(trans);
            movimientoInsertado = true;
            oos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

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

    /**
     * Obtiene el saldo de una cuenta(IBAN)
     * @param IBAN IBAN de la cuenta de la que se desea obtener el saldo
     * @return Asociado al nombre devuelve un double con el saldo de la cuenta.
     */
    public double obtenerSaldoPorIBAN(String IBAN)
    {
    	return Double.parseDouble(datosCuenta(IBAN).split(",")[1]);
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

    /**
     * Modifica el saldo de una cuenta
     * @param IBAN Un String con el IBAN de la cuenta en de la que se modificara su saldo
     * @param sumaOresta Un boolean indicando si se sumar� (true) o restar� (false) la cantidad
     * @param cantidad Un double con la cantidad
     * @return Un boolean indicando si la modificacion del saldo tuvo exito(true) o no(false)
     */
    public boolean modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta, double cantidad)
    {
    	File ficheroCuentas = null;
    	String nombreBanco = obtenerNombreBancoComercialPorIBAN(IBAN);
    	RandomAccessFile randAccessFile = null;
    	boolean modificado = false;

        //Abrir fichero de las cuentas del banco
        try
        {
        	ficheroCuentas = new File("./Files/BancosComerciales/"+nombreBanco+"/Cuentas_"+nombreBanco+"_Maestro.txt");
        	randAccessFile = new RandomAccessFile(ficheroCuentas, "rw");
        	
        	//Leer registro de la cuenta cuenta
        	String linea = randAccessFile.readLine();
        	long puntero = 0;
        	CuentaImpl cuenta = null;
        	boolean encontrado = false;
        	String[] campos = null;
        	
        	while(linea != null && encontrado == false)
        	{
        		//Dividir el registro en campos separados por coma
        		campos = linea.split(",");
        		
        		//Si el campo del IBAN es igual al IBAN a buscar
        		if(campos[0].equals(IBAN))
        		{
        			randAccessFile.seek(puntero);
        			
        			//Modificar el saldo de la cuenta
        			cuenta = new CuentaImpl(campos[0], Double.parseDouble(campos[1]));
        			if(sumaOresta)
        				cuenta.setCantidadDinero(cuenta.getCantidadDinero() + cantidad);
        			else
        				cuenta.setCantidadDinero(cuenta.getCantidadDinero() - cantidad);
        			
        			linea = cuenta.toString();
        			
        			//Sobreescribir el registro
        			randAccessFile.writeBytes(linea);
     			
        			modificado = true;
        			encontrado = true;
        		}
        		
        		puntero = randAccessFile.getFilePointer();	//guarda la posicion del puntero
        		linea = randAccessFile.readLine();			//Lee el siguiente registro
        	}
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		randAccessFile.close();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
        }
        
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

    /**
     * Comprueba si un cliente (DNI) esta registrado en un banco(BIC), ya sea porque este dado de alta, o la haya solicitado.
     * @param DNI DNI del cliente que se comprobara
     * @param BIC BIC del banco donde se comprobara la existencia del cliente
     * @return Asociado al nombre deuvelve true si el DNI esta registrado en el banco (BIC), o false si no lo esta.
     */
    public boolean DNIRegistrado(String DNI, String BIC)
    {
    	boolean registrado = false;
    	String nombreBanco = this.obtenerNombrePorBIC(BIC);
    	File ficheroClientes = new File ("./Files/BancosComerciales/"+nombreBanco+"/Clientes_"+nombreBanco+"_Maestro.txt");
        File ficheroClientesMov = new File ("./Files/BancosComerciales/"+nombreBanco+"/Clientes_"+nombreBanco+"_Movimientos.txt");
		FileReader leer = null;
	    BufferedReader br = null;
	    String campos[] = null;
	    String registro = null;
	    
	    try 
        {
            leer = new FileReader(ficheroClientes);
            br = new BufferedReader(leer);

            while(br.ready())
            {
                registro = br.readLine();
                
                if(registro != null)
                {
	                campos = registro.split(",");
	                if(campos[1].equals(DNI))
	                		registrado = true;
                }
            }
            
            br.close();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }

        try
        {
            leer = new FileReader(ficheroClientesMov);
            br = new BufferedReader(leer);

            while(br.ready())
            {
                registro = br.readLine();

                if(registro != null)
                {
                    campos = registro.split(",");
                    if(campos[1].equals(DNI))
                        registrado = true;
                }
            }

            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    	
    	return registrado;
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

    /**
     * Accede al fichero de cuentas y busca una cuenta por su IBAN para leer sus datos
     * @param IBAN el IBAN de la cuenta.
     * @return Asociado al nombre devuelve un String con los datos de la cuenta o null si no existe la cuenta
     */
	public String datosCuenta(String IBAN)
	{
		String cuenta = null;
		String nombreBanco = this.obtenerNombreBancoComercialPorIBAN(IBAN);
		File ficheroCuentas = new File("./Files/BancosComerciales/"+nombreBanco+"/Cuentas_"+nombreBanco+"_Maestro.txt");
		FileReader fr = null;
		BufferedReader br = null;
		String registro;
		String[] campos;
		
		try
		{
			fr = new FileReader(ficheroCuentas);
			br = new BufferedReader(fr);
			
			while(br.ready())
			{
				//buscar la cuenta y guardarla en el String que se devuelve
				registro = br.readLine();
				campos = registro.split(",");
				
				if(campos[0].equals(IBAN))
					cuenta = registro;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		
		return cuenta;
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

    /**
     * devuelve el BIC de un banco dando su nombre
     * @param nombre_banco Nombre del banco del cual se quiere obtener el BIC
     * @return Asociado al nombre se devuelve un String con el BIC del banco. Si no existe devuelve un espacio en blanco.
     */
    public String obtenerBICporNombre(String nombre_banco){
        File clientesBancoCentral = new File("./Files/BancoCentral/Clientes_BancoCentral_Maestro.txt");
        FileReader leer = null;
        BufferedReader br = null;
        String registro = " ";
        String campos[] = null;
        String bic = " ";
        try{
            leer = new FileReader(clientesBancoCentral);
            br = new BufferedReader(leer);
            while(br.ready()){
                registro = br.readLine();
                campos = registro.split(",");

                if ( campos[1].equals(nombre_banco)){
                    bic = campos[0].substring(3,14);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


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

    /**
     * devuelve el nombre de un banco dado el IBAN de una cuenta
     * @param iban_cuenta IBAN de la cuenta de la que se quiere obtener el nombre del banco al que pertenece dicha cuenta
     * @return Asociado al nombre se devuelve un String con el nombre del banco comercial al que pertenece
     */
    public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta){

        return obtenerNombrePorBIC(iban_cuenta.substring(3,14));
    }

    /* INTERFAZ
     * Comentario: A partir de un IBAN, obtiene el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Prototipo: public String obtenerBICporIBAN(String IBAN)
     * Entrada: Un String con el IBAN
     * Precondiciones: El IBAN debe tener minimo una longitud de 14 caracteres
     * Salida: Un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Postcondiciones: Asociado al nombre devuelve un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     */

    /**
     * A partir de un IBAN, obtiene el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * @param IBAN IBAN de la cuenta de la que se quiere obtener el nombre del banco central al que pertenece dicha cuenta
     * @return Asociado al nombre devuelve un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     */
    public String obtenerBICporIBAN(String IBAN)
    {
        return IBAN.substring(3, 14);
    }

    /* INTERFAZ
     * Comentario: Obtiene el numero de cuenta de un IBAN
     * Prototipo: public String obtenerNumCuentaPorIBAN(String IBAN)
     * Entrada: Un String con el IBAN del que se quiere obtener su numero de cuenta
     * Precondiciones: El IBAN debe tener minimo una longitud de 21 caracteres
     * Salida: Un String con el numero de cuenta del IBAN especificado
     * Postcondiciones: Asociado al nombre devuelve un String con el numero de cuenta del IBAN especificado
     */

    /**
     * Obtiene el numero de cuenta de un IBAN
     * @param IBAN Un String con el IBAN del que se quiere obtener su numero de cuenta
     * @return Un String con el numero de cuenta del IBAN especificado
     */
    public String obtenerNumCuentaPorIBAN(String IBAN)
    {
        return IBAN.substring(14, 21);
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

    /**
     * devuelve el nombre de un banco dando su BIC
     * @param bic BIC del banco del que se quiere obtener su nombre
     * @return Asociado al nombre se devuelve un String con el nombre del banco, o un espacio en blanco si no lo encuentra.
     */
    public String obtenerNombrePorBIC(String bic){
        File clientesBancoCentral = new File("./Files/BancoCentral/Clientes_BancoCentral_Maestro.txt");
        FileReader leer = null;
        BufferedReader br = null;
        String registro = " ";
        String campos[] = null;
        String nombre = " ";
        try{
            leer = new FileReader(clientesBancoCentral);
            br = new BufferedReader(leer);
            while(br.ready()){
                registro = br.readLine();
                campos = registro.split(",");

                if ( campos[0].substring(3,14).equals(bic)){
                    nombre = campos[1];
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


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

    /**
     * ingresa una cantidad dada en una cuenta
     * @param IBAN IBAN de la cuenta en la que se desea ingresar la cantidad dada
     * @param concepto Concepto de la transferencia
     * @param cantidad Cantidad de la transferencia
     * @param fecha Fecha de la transferencia
     * @return Un boolean indicando si se ha ingresado el dinero con exito (true) o no (false)
     */
    public boolean ingresarDinero(String IBAN,String concepto, double cantidad, GregorianCalendar fecha){

        boolean ingresado = false;

        boolean insertado = insertarMovimientoEnFicheroMovimientos(IBAN, true, concepto, cantidad, fecha);
        boolean modificado = modificarSaldoEnFicheroCuentas(IBAN, true, cantidad);

        if(insertado && modificado)
            ingresado = true;

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

    /**
     * Realiza un movimiento bancario, sacando una cantidad de la cuenta de origen e ingresandola en la cuenta destino.
     *    Llama a los metodos sacarDinero e ingresarDinero.
     * @see #sacarDinero(String, String, double, GregorianCalendar)
     * @see #ingresarDinero(String, String, double, GregorianCalendar)
     * @param IBANOrigen IBAN de la cuenta origen que inicia la transferencia
     * @param IBANDestino IBAN de la cuenta destino a quien va dirigida la transferencia
     * @param concepto Concepto de la transferencia
     * @param cantidad Cantidad de la transferencia
     * @param fecha Fecha de la transferencia
     * @return Un boolean indicando si se ha realizado bien el movimiento (true) o no (false)
     */
    public boolean realizarMovimiento(String IBANOrigen,String IBANDestino, String concepto,double cantidad, GregorianCalendar fecha){

        boolean movimientoRealizado = false;

        boolean sacado = sacarDinero(IBANOrigen, concepto, cantidad, fecha);
        boolean ingresado = ingresarDinero(IBANDestino, concepto, cantidad, fecha);


        if(sacado && ingresado)
            movimientoRealizado = true;

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

    /**
     * Saca una cantidad dada de una cuenta
     * @param IBAN IBAN de la cuenta donde se realiza el ingreso
     * @param concepto Concepto de la transferencia
     * @param cantidad Cantidad de la transferencia
     * @param fecha Fecha de la transferencia
     * @return Un boolean indicando si se ha sacado bien el dinero (true) o no (false)
     */
    public boolean sacarDinero(String IBAN,String concepto, double cantidad, GregorianCalendar fecha){

        boolean dineroSacado = false;

        boolean movimientoInsertado = insertarMovimientoEnFicheroMovimientos(IBAN, false, concepto, cantidad, fecha);
        boolean modificado = modificarSaldoEnFicheroCuentas(IBAN, false, cantidad);

        if (movimientoInsertado && modificado)
        {
            dineroSacado = true;
        }

        return dineroSacado;
    }

    /* INTERFAZ
     * Comentario: Actualiza un fichero maestro determinado, mirando los registros de su fichero de movimiento correspondiente.
     * Prototipo: public boolean actualizarFichero(String fichero, int posicionCampoClave)
     * Entrada: Un string con la ruta (sin especificar la parte de "_Maestro.txt" o "_Movimientos.txt) del fichero
     *          Un int indicando la posicion del campo clave
     * Precondiciones: Los ficheros deben existir
     * Salida: Un boolean indicando si se ha actualizado correctamente o no.
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si la sincronizacion tuvo exito, por lo tanto, los registros del fichero de movimientos quedan actualizados al maestro.
     * 					-> False si hubo algun error y no se pudo sincronizar.
     * 					* Puede lanzar IOException si hay algun error al leer o escribir
     */

    /**
     * Actualiza un fichero maestro determinado, mirando los registros de su fichero de movimiento correspondiente.
     * @param fichero Un string con la ruta (sin especificar la parte de "_Maestro.txt" o "_Movimientos.txt) del fichero
     * @param posicionCampoClave posicion del campo clave del fichero
     * @return Un boolean indicando si se ha actualizado correctamente o no.
     */
    public boolean actualizarFichero(String fichero, int posicionCampoClave)
    {
        boolean actualizado = false;
        File ficheroMaestro = new File(fichero + "_Maestro.txt");
        File ficheroMovimientos = new File(fichero + "_Movimientos.txt");
        File ficheroMaestroAct = new File(fichero + "_Maestro_act.txt");
        FileReader frMaestro = null;
        FileReader frMovimientos = null;
        FileWriter fwMaestroAct = null;
        BufferedReader brMaestro = null;
        BufferedReader brMovimientos = null;
        BufferedWriter bwMaestroAct = null;
        Utilidades utils = new Utilidades();
        
        utils.ordenarFicheroPorClave(ficheroMaestro.getPath(), posicionCampoClave);
        utils.ordenarFicheroPorClave(ficheroMovimientos.getPath(), posicionCampoClave);

        try
        {
            frMaestro = new FileReader(ficheroMaestro);
            frMovimientos = new FileReader(ficheroMovimientos);
            fwMaestroAct = new FileWriter(ficheroMaestroAct);

            brMaestro = new BufferedReader(frMaestro);
            brMovimientos = new BufferedReader(frMovimientos);
            bwMaestroAct = new BufferedWriter(fwMaestroAct);

            //Leer primeros registros de ficheroMovimientos y ficheroMaestro
            String registroMovimientos = brMovimientos.readLine();
            String registroMaestro = brMaestro.readLine();
            
            String campoClaveMovimientos = null;
            String campoClaveMaestro = null;
            
            if(registroMovimientos != null && registroMaestro != null)
            {
	            campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];
	            campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
            }


            //Mientras no sea fin de fichero en ninguno de los dos
            while(registroMovimientos != null && registroMaestro != null)
            {
                if(campoClaveMovimientos.compareTo(campoClaveMaestro) == 0)
                {

                    if(!registroMovimientos.contains("*"))
                    {
                        bwMaestroAct.write(registroMovimientos + "\n");
                    }

                    //Se mueven los dos punteros
                    registroMovimientos = brMovimientos.readLine();
                    if(registroMovimientos != null)
                        campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];

                    registroMaestro = brMaestro.readLine();
                    if(registroMaestro != null)
                        campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
                }
                else if(campoClaveMovimientos.compareTo(campoClaveMaestro) > 0)
                {

                    while(campoClaveMovimientos.compareTo(campoClaveMaestro) > 0 && registroMaestro != null)
                    {
                        //Escribir registro de maestro en maestroAct
                        if(!registroMovimientos.contains("*")) {
                            bwMaestroAct.write(registroMaestro + "\n");
                        }
                        //leer registro de maestro
                        registroMaestro = brMaestro.readLine();

                        if(registroMaestro != null)
                        {
                            campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
                        }
                    }
                }
                else if(campoClaveMovimientos.compareTo(campoClaveMaestro) < 0)
                {
                    //Es un alta o un error
                    if(!registroMovimientos.contains("*")) {
                        bwMaestroAct.write(registroMovimientos + "\n");
                    }
                    //Leer registro de movimiento
                    registroMovimientos = brMovimientos.readLine();

                    if(registroMovimientos != null)
                        campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];
                }
            }
            //Se ha acabado el fichero de movimientos y aun quedan registros en el maestro
            while(registroMaestro != null)
            {
                //Escribir registro de maestro en maestroAct
                bwMaestroAct.write(registroMaestro + "\n");

                //leer registro de maestro
                registroMaestro = brMaestro.readLine();

                if(registroMaestro != null)
                    campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
            }
            //Se ha acabado el fichero maestro y aun quedan registros en el de movimientos
            while(registroMovimientos != null)
            {
                //Escribir registro de movimientos en maestroAct
                if(!registroMovimientos.contains("*")) {
                    bwMaestroAct.write(registroMovimientos + "\n");
                }

                //leer registro de movimientos
                registroMovimientos = brMovimientos.readLine();

                if(registroMovimientos != null)
                    campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];
            }

            //Cerrar archivos
            brMaestro.close();
            brMovimientos.close();
            bwMaestroAct.close();
            
            //utils.borrarFichero(ficheroMaestro.getPath());
            utils.renombrarFichero(ficheroMaestroAct.getPath(), ficheroMaestro.getPath());
            

            ficheroMovimientos.delete();
            ficheroMovimientos.createNewFile();
           
            actualizado = true;

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return actualizado;
    }

}
