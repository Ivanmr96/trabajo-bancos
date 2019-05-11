package gestion;
import clasesBasicas.CuentaImpl;
import clasesBasicas.TransferenciaImpl;
import utilidades.Utilidades;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import clasesBasicas.TransferenciaImpl;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class GestionBancoCentral {

    
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
    /**
     * Modifica el registro del saldo total en el fichero de cuentas del banco central.
     * 
     * @param IBAN El IBAN de la cuenta que se desea modificar su saldo.
     * @param sumaOresta Indica si se le sumar� o se le restar� la cantidad.
     * @param cantidad La cantidad de dinero a sumar/restar.
     * @return Devuelve true si se ha modificado correctamente el saldo de la cuenta en el fichero de cuentas, o false si no se ha modificado correctamente.
     */
    public boolean modificarSaldoEnFicheroCuentas(String IBAN, boolean sumaOresta, double cantidad)
    {
    	File ficheroCuentas = null;
    	RandomAccessFile randAccessFile = null;
    	boolean modificado = false;
               
        
        //Abrir fichero de las cuentas del banco
        try
        {
        	ficheroCuentas = new File("./Files/BancoCentral/Cuentas_BancoCentral_Maestro.txt");
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
     * Signatura: public boolean insertarTransferenciaEnFicheroTransferencias(String IBAN,boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: A�ade un nuevo movimiento en el fichero de transferencias de la cuenta.
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
    /**
     * A�ade un nuevo movimiento en el fichero de transferencias de la cuenta.
     * 
     * @param IBAN El IBAN de la cuenta donde se desea a�adir el movimiento.
     * @param isIngresoOrRetirada Indica si la transferencia es un ingreso o una retirada de dinero.
     * @param concepto El concepto de la transferencia.
     * @param cantidad La cantidad de dinero a ingresar/retirar.
     * @param fecha La fecha de la transferencia.
     * 
     * @return Devuelve true si se ha insertado correctamente el registro del movimiento en el fichero de transferencias, o false si no se ha insertado correctamente.
     *
     */
    public boolean insertarTransferenciaEnFicheroTransferencias(String IBAN, boolean isIngresoOrRetirada, String concepto, double cantidad, GregorianCalendar fecha) {
        File ficheroCuentas = new File("./Files/BancoCentral/TransferenciasCuentas/Transferencias_" + IBAN + ".dat");
        TransferenciaImpl trans = new TransferenciaImpl(IBAN, isIngresoOrRetirada, concepto, cantidad, fecha);
        boolean movimientoInsertado = false;
        MyObjectOutputStream oos = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(fecha);
        
        try {
            oos = new MyObjectOutputStream(new FileOutputStream(ficheroCuentas, true));
            oos.writeObject(trans);
            movimientoInsertado = true;
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    /**
     * Acceder al fichero de cuentas y busca una cuenta por su IBAN para leer sus datos.
     * 
     * @param IBAN El IBAN de la cuenta que se desea buscar.
     * 
     * @return Devuelve los datos de la cuenta. <b>(Ejemplo: "ESPCAIXESBBXXX0000000,8673155.35")</b>
     *
     */
    public String datosCuenta(String IBAN) {
        String cuenta = null;
        File ficheroCuentas = new File("./Files/BancoCentral/Cuentas_BancoCentral_Maestro.txt");
        FileReader fr = null;
        BufferedReader br = null;
        String registro;
        String[] campos;

        try {
            fr = new FileReader(ficheroCuentas);
            br = new BufferedReader(fr);

            while (br.ready()) {
                //buscar la cuenta y guardarla en el String que se devuelve
                registro = br.readLine();
                campos = registro.split(",");

                if (campos[0].equals(IBAN))
                    cuenta = registro;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    /**
     * Comprueba si existe un cliente (buscando por su BIC) registrado en el banco central.
     * 
     * @param BIC El BIC del cliente a comprobar.
     * 
     * @return Devuelve true si el BIC est� registrado en el banco o false si no lo est�.
     */
    public boolean BICRegistrado(String BIC) {
        boolean registrado = false;

        File ficheroClientes = new File("./Files/BancoCentral/Clientes_BancoCentral_Maestro.txt");
        FileReader leer = null;
        BufferedReader br = null;

        String campos[] = null;
        String registro = " ";

        try {
            leer = new FileReader(ficheroClientes);
            br = new BufferedReader(leer);

            while (br.ready()) {
                registro = br.readLine();
                campos = registro.split(",");

                if (campos[0].equals("ESP" + BIC))
                    registrado = true;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    /**
     * Comprueba si existe una cuenta (buscando por su IBAN) en el banco central.
     * 
     * @param IBAN El IBAN de la cuenta a comprobar.
     * 
     * @return Devuelve true si el IBAN est� registrado en el banco o false de lo contrario
     */
    public boolean IBANRegistrado(String IBAN) {
        boolean registrado = false;
        File ficheroCuentas = new File("./Files/BancoCentral/Cuentas_BancoCentral_Maestro.txt");
        FileReader leer = null;
        BufferedReader br = null;
        String campos[] = null;
        String registro = " ";

        try {
            leer = new FileReader(ficheroCuentas);
            br = new BufferedReader(leer);

            while (br.ready()) {
                registro = br.readLine();
                campos = registro.split(",");

                if (campos[0].equals(IBAN))
                    registrado = true;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    /**
     * Busca los movimientos que se hicieron en una cuenta en el a�o dada.
     * 
     * @param IBAN El IBAN del que se buscar�n los movimientos.
     * @param anyo El a�o en el que se buscar�n los movimientos.
     * 
     * @return Devuelve una lista de transferencias con los movimientos del a�o indicado.
     *
     */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int anyo) {
        File file_movimientos = new File("./Files/BancoCentral/TransferenciasCuentas/Transferencias_" + IBAN + ".dat");
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
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
    /**
     * busca los movimientos que se hicieron en una cuenta en el mes y a�o dado
     * @param IBAN El IBAN del que se buscar�n los movimientos.
     * @param mes El mes en el que se buscar�n los movimientos.
     * @param anyo El a�o en el que se buscar�n los movimientos.
     * 
     * @return Devuelve una lista con las transferencias del mes en el a�o indicado.
     *
     */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int mes, int anyo) {
        File file_movimientos = new File("./Files/BancoCentral/TransferenciasCuentas/Transferencias_" + IBAN + ".dat");
        ObjectInputStream leer = null;
        List<TransferenciaImpl> registros_buscados = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;
        boolean cont = true;

        try {
            leer = new ObjectInputStream(new FileInputStream(file_movimientos));
            while (cont) {
                registro = (TransferenciaImpl) leer.readObject();
                
                if (registro.getFecha().get(Calendar.YEAR) == anyo && registro.getFecha().get(Calendar.MONTH) == mes - 1) {   /*Se pone así porque los meses de Calendar van de 0(enero) a 11(diciembre)*/
                    registros_buscados.add(registro);
                }
            }
            leer.close();
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
    /**
     * Busca los movimientos que se hicieron en una cuenta en la fecha dada
     * 
     * @param IBAN El IBAN del que se buscar�n los movimientos.
     * @param dia El dia en el que se buscar�n los movimientos.
     * @param mes El mes en el que se buscar�n los movimientos.
     * @param anyo El a�o en el que se buscar�n los movimientos.
     * 
     * @return Devuelve una lista con las transferencias en el d�a indicado.
     */
    public List<TransferenciaImpl> buscarMovimientosPorFecha(String IBAN, int dia, int mes, int anyo) {
        File file_movimientos = new File("./Files/BancoCentral/TransferenciasCuentas/Transferencias_" + IBAN + ".dat");
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
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

    @Deprecated
    public boolean marcarCuentaComoBorrada(String iban_cuenta) {
        boolean borrada = false;
        Utilidades util = new Utilidades();

        String registroCuenta = iban_cuenta + ",*\n";
        String registroCliente = "ESP" + this.obtenerBICporIBAN(iban_cuenta) + ",*\n";
        String registroClientesCuentas = "*," + iban_cuenta + "\n";

        if (IBANRegistrado(iban_cuenta)) {
            util.escribirRegistroEnFichero(registroCuenta, "./Files/BancoCentral/Cuentas_BancoCentral_Movimientos.txt");
            util.escribirRegistroEnFichero(registroCliente, "./Files/BancoCentral/Clientes_BancoCentral_Movimientos.txt");
            util.escribirRegistroEnFichero(registroClientesCuentas, "./Files/BancoCentral/Clientes_Cuentas_BancoCentral_Movimientos.txt");
            borrada = true;
        }

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
    /**
     * Devuelve los ultimos diez movimientos de la cuenta.
     * 
     * @param iban_cuenta el IBAN de la cuenta 
     * @return Devuelve una lista de transferencias con los �ltimos 10 movimientos.
     *
     */
    public List<TransferenciaImpl> ultimosDiezMovimientos(String iban_cuenta) {

        File f_cuentas = new File("./Files/BancoCentral/TransferenciasCuentas/Transferencias_" + iban_cuenta + ".dat");
        ObjectInputStream leer = null;
        List<TransferenciaImpl> registros = new ArrayList<TransferenciaImpl>();
        TransferenciaImpl registro = null;
        int lineas = 0;
        boolean cont = true;


        if (f_cuentas.exists()) {
            try {
                leer = new ObjectInputStream(new FileInputStream(f_cuentas));
                while (cont && lineas < 10) {

                    registro = (TransferenciaImpl) leer.readObject();
                    registros.add(registro);
                    lineas++;

                }
                leer.close();
            } catch (EOFException e) {

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
    /**
     * Devuelve el BIC de un banco dado su nombre.
     * 
     * @param nombre_banco El nombre del banco del que se desea saber su nombre.
     * 
     * @return Devuelve el BIC del banco o un espacio en blanco si no existe el banco buscado.
     *
     */
    public String obtenerBICporNombre(String nombre_banco) {
        File clientesBancoCentral = new File("./Files/BancoCentral/Clientes_BancoCentral_Maestro.txt");
        FileReader leer = null;
        BufferedReader br = null;
        String registro = " ";
        String campos[] = null;
        String bic = " ";
        try {
            leer = new FileReader(clientesBancoCentral);
            br = new BufferedReader(leer);
            while (br.ready()) {
                registro = br.readLine();
                campos = registro.split(",");

                if (campos[1].equals(nombre_banco)) {
                    bic = campos[0].substring(3, 14);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    /**
     * Devuelve el nombre de un banco dado el IBAN de una cuenta.
     * 
     * @param iban_cuenta El IBAN de la cuenta de la que se desea saber el nombre del banco al que pertenece.
     * @return Devuelve el nombre del banco comercial o un espacio en blanco si no existe.
     */
    public String obtenerNombreBancoComercialPorIBAN(String iban_cuenta) {

        return obtenerNombrePorBIC(iban_cuenta.substring(3, 14));
    }

    /* INTERFAZ
     * Comentario: A partir de un IBAN, obtiene el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Prototipo: public String obtenerBICporIBAN(String IBAN)
     * Entrada: Un String con el IBAN
     * Precondiciones: El IBAN debe tener una longitud minima de 14 caracteres
     * Salida: Un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     * Postcondiciones: Asociado al nombre devuelve un string con el BIC del banco central al que pertenece el banco que gestiona la cuenta
     */
    /**
     * A partir de un IBAN, obtiene el BIC del banco central al que pertenece el banco que gestiona la cuenta.
     * 
     * @param IBAN El IBAN de la cuenta de la que se desea obtener el BIC del banco central al que pertenece.
     * 
     * @return Devuelve el BIC del banco central al que pertenece el banco comercial que gestiona esta cuenta.
     */
    public String obtenerBICporIBAN(String IBAN) {
        return IBAN.substring(3, 14);
    }

    /* INTERFAZ
     * Comentario: Obtiene el numero de cuenta de un IBAN
     * Prototipo: public String obtenerNumCuentaPorIBAN(String IBAN)
     * Entrada: Un String con el IBAN del que se quiere obtener su numero de cuenta
     * Precondiciones: El IBAN debe tener una longitud minima de 21 caracteres
     * Salida: Un String con el numero de cuenta del IBAN especificado
     * Postcondiciones: Asociado al nombre devuelve un String con el numero de cuenta del IBAN especificado
     */
    /**
     * Obtiene el numero de cuenta de un IBAN.
     * 
     * @param IBAN El IBAN de la cuenta.
     * @return Devuelve el n�mero de cuenta del IBAN especificado.
     */
    public String obtenerNumCuentaPorIBAN(String IBAN) {
        return IBAN.substring(14, 21);
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
    /**
     * Devuelve el nombre de un banco dando su BIC.
     * @param bic El BIC del banco del que se desea obtener su nombre.
     * @return Devuelve el nombre del banco seg�n el BIC dado, o un espacio en blanco si no existe el banco.
     *
     */
    public String obtenerNombrePorBIC(String bic) {
        File clientesBancoCentral = new File("./Files/BancoCentral/Clientes_BancoCentral_Maestro.txt");
        FileReader leer = null;
        BufferedReader br = null;
        String registro = " ";
        String campos[] = null;
        String nombre = " ";
        try {
            leer = new FileReader(clientesBancoCentral);
            br = new BufferedReader(leer);
            while (br.ready()) {
                registro = br.readLine();
                campos = registro.split(",");

                if (campos[0].substring(3, 14).equals(bic)) {
                    nombre = campos[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    /**
     * Ingresa una cantidad dada en una cuenta.
     * 
     * @param IBAN El IBAN de la cuenta donde se ingresar� la cantidad.
     * @param concepto El concepto de la transferencia.
     * @param cantidad La cantidad de dinero a ingresar.
     * @param fecha La fecha de la transferencia.
     * @return Devuelve true si se ha ingresado el dinero con �xito, insertando el movimiento en el fichero de movimientos y modificando el saldo en el fichero de cuentas.
     * 					O devuelve false si no se h apodido realizar con �xito la operaci�n.
     */
    public boolean ingresarDinero(String IBAN, String concepto, double cantidad, GregorianCalendar fecha) {
        boolean ingresado = false;

        boolean insertado = insertarTransferenciaEnFicheroTransferencias(IBAN, true, concepto, cantidad, fecha);
        boolean modificado = modificarSaldoEnFicheroCuentas(IBAN, true, cantidad);

        if (insertado && modificado)
            ingresado = true;

        return ingresado;
    }

    /* INTERFAZ
     * Signatura: public boolean realizarMovimiento(String IBANOrigen, String IBANDestino, String concepto, double cantidad, GregorianCalendar fecha)
     * Comentario: Realiza un movimiento bancario, sacando una cantidad de la cuenta de origen e ingresandola en la cuenta destino.
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
     * 					-> true si se ha realizado bien la transferencia.
     * 					-> false si no se ha realizado bien la transferencia.
     * 					* Puede lanzar IOException si hay algun error al leer
     * */
    /**
     * Realiza un movimiento bancario, sacando una cantidad de la cuenta de origen e ingres�ndola en la cuenta destino.
     * 
     * @param IBANOrigen El IBAN de la cuenta de origen.
     * @param IBANDestino El IBAN de la cuenta de destino.
     * @param concepto El concepto de la transferencia.
     * @param cantidad La cantida de dinero a traspasar de una cuenta a otra.
     * @param fecha La fecha de la transferencia.
     * @return Devuelve true si se ha realizado bien la transferencia. O false si no se ha realizado correctamente.
     */
    public boolean realizarMovimiento(String IBANOrigen, String IBANDestino, String concepto, double cantidad, GregorianCalendar fecha) {

        boolean movimientoRealizado = false;

        boolean sacado = sacarDinero(IBANOrigen, concepto, cantidad, fecha);
        boolean ingresado = ingresarDinero(IBANDestino, concepto, cantidad, fecha);


        if (sacado && ingresado)
            movimientoRealizado = true;

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
    /**
     * Saca una cantidad dada de una cuenta.
     * @param IBAN El IBAN de la cuenta de la que se desea retirar el dinero.
     * @param concepto El concepto de la retirada.
     * @param cantidad La cantidad de dinero a retirar.
     * @param fecha La fecha de la retirada.
     * @return Devuelve true si se ha sacado bien el dinero de la cuenta, insertando el movimiento en el fichero de movimientos y modificando el saldo en el fichero de cuentas.
     * 					O false si no se ha podido realizar bien la operaci�n.
     */
    public boolean sacarDinero(String IBAN, String concepto, double cantidad, GregorianCalendar fecha) {

        boolean dineroSacado = false;

        boolean movimientoInsertado = insertarTransferenciaEnFicheroTransferencias(IBAN, false, concepto, cantidad, fecha);
        boolean modificado = modificarSaldoEnFicheroCuentas(IBAN, false, cantidad);

        if (movimientoInsertado && modificado) {
            dineroSacado = true;
        }

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
    /**
     * Actualiza un fichero maestro determinado, mirando los registros de su fichero de movimiento correspondiente.
     * @param fichero La ruta del fichero sin especificar la �ltima parte.
     * 					<b>Ejemplo: "./Files/BancoCentral/Cuentas_BancoCentral"</b>. El m�todo dentro se encarga de trabajar con los dos ficheros ("_Maestro.txt" y "_Movimientos.txt").
     * @param posicionCampoClave La posici�n en cada registro del fichero donde se encuentra el campo clave, la primera posici�n es 0.
     * 					<br><b>Ejemplo: "ESPCAIXESBBXXX0000000,712542.22". Su campo clave ser�a 0.</b>
     * @return Devuelve true si se sincroniz� correctamente, false si no se sincroniz� bien.
     */
    public boolean actualizarFichero(String fichero, int posicionCampoClave) {
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

        utils.ordenarFicheroPorClave(ficheroMaestro.getPath(), 0);

        try {
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

            if (registroMovimientos != null && registroMaestro != null) {
                campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];
                campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
            }

            //Mientras no sea fin de fichero en ninguno de los dos
            while (registroMovimientos != null && registroMaestro != null) {
                if (campoClaveMovimientos.compareTo(campoClaveMaestro) == 0) {

                    if (!registroMovimientos.contains("*")) {
                        bwMaestroAct.write(registroMovimientos + "\n");
                    }

                    //Se mueven los dos punteros
                    registroMovimientos = brMovimientos.readLine();
                    if (registroMovimientos != null)
                        campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];

                    registroMaestro = brMaestro.readLine();
                    if (registroMaestro != null)
                        campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
                } else if (campoClaveMovimientos.compareTo(campoClaveMaestro) > 0) {

                    while (campoClaveMovimientos.compareTo(campoClaveMaestro) > 0 && registroMaestro != null) {
                        //Escribir registro de maestro en maestroAct
                        bwMaestroAct.write(registroMaestro + "\n");

                        //leer registro de maestro

                        registroMaestro = brMaestro.readLine();

                        if (registroMaestro != null) {
                            campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
                        }
                    }
                } else if (campoClaveMovimientos.compareTo(campoClaveMaestro) < 0) {
                    //Es un alta o un error
                    bwMaestroAct.write(registroMovimientos + "\n");

                    //Leer registro de movimiento
                    registroMovimientos = brMovimientos.readLine();

                    if (registroMovimientos != null)
                        campoClaveMovimientos = registroMovimientos.split(",")[posicionCampoClave];
                }
            }
            //Se ha acabado el fichero de movimientos y aun quedan registros en el maestro
            while (registroMaestro != null) {
                //Escribir registro de maestro en maestroAct
                bwMaestroAct.write(registroMaestro + "\n");

                //leer registro de maestro
                registroMaestro = brMaestro.readLine();

                if (registroMaestro != null)
                    campoClaveMaestro = registroMaestro.split(",")[posicionCampoClave];
            }
            //Se ha acabado el fichero maestro y aun quedan registros en el de movimientos
            while (registroMovimientos != null) {
                //Escribir registro de movimientos en maestroAct
                bwMaestroAct.write(registroMovimientos + "\n");

                //leer registro de movimientos
                registroMovimientos = brMovimientos.readLine();

                if (registroMovimientos != null)
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
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    /**
     * Obitene el saldo de una cuenta proporcionando su IBAN.
     * @param IBAN El IBAN de la cuenta.
     * @return Devuelve un double con el saldo de la cuenta.
     */
    public double obtenerSaldoPorIBAN(String IBAN) {
        String cuenta = datosCuenta(IBAN);
        double saldo = 0;

        if (cuenta != null)
            saldo = Double.parseDouble(cuenta.split(",")[1]);

        return saldo;
    }
}