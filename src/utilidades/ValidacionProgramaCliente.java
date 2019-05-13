package utilidades;

import clasesBasicas.TransferenciaImpl;
import gestion.GestionBancoComercial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidacionProgramaCliente {


    public int logInORSignUp(){
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("�Que desea hacer?");
            System.out.println("0. Salir");
            System.out.println("1. Iniciar sesion con mi cuenta\n" +
                    "2. Crear una cuenta nueva" );
            op = sc.nextInt();
        }while(op < 0 && op >2);
        return op;
    }


    public int menu(){
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("MENU");
            System.out.println("0. Salir");
            System.out.println("1. Realizar transferencia bancaria\n" +
                    "2. Ver datos de la cuenta propia\n" +
                    "3. Ver movimientos\n" +
                    "4. Buscar movimientos\n" +
                    "5. Cancelar cuenta ");
            op = sc.nextInt();
        }while(op < 0 && op >5);
        return op;
    }
    /*
    * INTERFAZ
    * Signatura: public boolean umbralNumerosRojos(TransferenciaImpl trans)
    * Comentario: Comprueba si una transferencia puede hacerse atentiendo a criterios economicos
    * Precondiciones:
    * Entradas: TransferenciaImpl trans
    * Salidas: boolean
    * Postcondiciones: asociado al nombre se devuelve un boolean que es true si se puede efectuar la transferencia
    *                   y false si no
    * */
    public boolean umbralNumerosRojos(TransferenciaImpl trans){
        boolean isPosible = false;
        GestionBancoComercial gestion = new GestionBancoComercial();
        double saldoMinimo = gestion.umbralNumerosRojos(trans.getID_Cuenta());
        double saldoActual = Double.parseDouble(gestion.datosCuenta(trans.getID_Cuenta()).split(",")[1]);
        if (!trans.isIngresoOrRetirada() && ((saldoActual - trans.getCantidad()) >= saldoMinimo )  ){
            isPosible = true;
        }
        return isPosible;
    }


    /*
     * INTERFAZ
     * Signatura: public String dniCliente(String bic)
     * Comentario: pide, lee y valida un DNI de un nuevo cliente. Revisa que no exista ya en ese banco.
     * Precondiciones:
     * Entradas:   String bic del banco donde debe comprobar si existe o no ya
     * Salidas: String
     * Postcondiciones: asociado al nombre se devuelve un String que es el DNI del nuevo cliente validado
     * */
    public String dniCliente (String bic){        //TODO mejorar validacion de DNI
        Scanner sc = new Scanner(System.in);
        String dni= " ";
        String letra=" ";
        GestionBancoComercial g = new GestionBancoComercial();
        Utilidades u = new Utilidades();
        boolean valido = false;
        boolean registrado = false;
        do{
        System.out.println("Introduce un DNI valido en formato: 12345678-A");
        dni = sc.next();
        dni = dni.toUpperCase();
        }while(!u.isDNIValido(dni) || g.DNIRegistrado(dni,bic));

        return dni;
    }

    /*
     * INTERFAZ
     * Signatura: public double ingresosCliente()
     * Comentario: pide, lee y valida unos ingresos mensuales
     * Precondiciones:
     * Entradas:
     * Salidas: double
     * Postcondiciones: asociado al nombre se devuelve un double que es el valor de los ingresos del cliente
     * */
    public double ingresosCliente(){
        Scanner sc = new Scanner(System.in);
        double ingresos= 0.0;
        do{
            System.out.println("Introduce tus ingresos netos mensuales");
            ingresos = sc.nextDouble();

        }while(ingresos < 0.0);

        return ingresos;
    }

    /*
     * INTERFAZ
     * Signatura: public String bancoCentral()
     * Comentario: pide, lee y valida un banco central
     * Precondiciones:
     * Entradas:
     * Salidas: String
     * Postcondiciones: asociado al nombre se devuelve un String que es el BIC del banco central elegido o "NOVALUE" si el usuario desea salir
     * */
    public String bancoCentral(){
        Scanner sc = new Scanner(System.in);
        File ficheroClientesBancoCentral = new File("./Files/BancoCentral/Clientes_Cuentas_BancoCentral_Maestro.txt");
        List<String> bancosDisponibles = new ArrayList<String>();
        List<String> bicsBancosDisponibles = new ArrayList<String>();
        GestionBancoComercial g = new GestionBancoComercial();
        FileReader fr = null;
        BufferedReader br = null;
        String registro = " ";
        String bic = " ";
        String banco = " ";
        int op = 0;
        String ret = " ";

        try{
            fr = new FileReader(ficheroClientesBancoCentral);
            br = new BufferedReader(fr);
            while(br.ready()){
                registro = br.readLine();
                banco = registro.split(",")[0];
                bancosDisponibles.add(banco);

                bic = g.obtenerBICporIBAN(registro.split(",")[1]);
                bicsBancosDisponibles.add(bic);
            }

            br.close();
            fr.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        do{
            System.out.println("Indica en que banco deseas crear una cuenta");
            System.out.println("Actualmente los disponibles son: ");
            for(int i = 0; i < bancosDisponibles.size() ; i ++){
                System.out.println((i+1) + ". "+ bancosDisponibles.get(i) );
            }
            System.out.println("0. Salir");
            op = sc.nextInt();
            op -= 1;
        }while (op < -1 || op >= bancosDisponibles.size() );

        if(op == -1){
            ret = "NOVALUE";
        }else{
            ret = bicsBancosDisponibles.get(op);
        }

       return ret;
    }

    /*
    * INTERFAZ
    * Signatura: public String inicioSesion()
    * Comentario: pide, lee y valida un inicio de sesión
    * Precondiciones:
    * Entradas:
    * Salidas: String
    * Postcondiciones: asociado al nombre se devuelve un String que es el IBAN de la cuenta que se va a manejar
    * */
    public String inicioSesion(){
        Scanner sc = new Scanner(System.in);
        GestionBancoComercial gbc = new GestionBancoComercial();
        String dni, iban_cuenta;
    do {
        System.out.println("Inicio de sesion: ");
        System.out.println("Introduce el IBAN de la cuenta:");
        iban_cuenta = this.iban();

        do {
            System.out.println("Introduce el DNI asociado a la cuenta: ");
            dni = sc.next().toUpperCase();
        } while (!gbc.DNIRegistrado(dni,gbc.obtenerBICporIBAN(iban_cuenta)));

    }while (!gbc.isPropietario(dni,iban_cuenta));
        System.out.println("Sesion iniciada correctamente");
        System.out.println("Datos: ");
        System.out.println("DNI: "+dni);
        System.out.println("IBAN: "+iban_cuenta);
    return iban_cuenta;
    }

    /*
     * INTERFAZ
     * Signatura: public String iban()
     * Comentario: pide, lee y valida un IBAN
     * Precondiciones:
     * Entradas:
     * Salidas: String
     * Postcondiciones: asociado al nombre se devuelve un String que es el IBAN de la cuenta
     * */
    public String iban(){
        Scanner sc = new Scanner(System.in);
        GestionBancoComercial gbc = new GestionBancoComercial();
        String iban_cuenta;
            do {
                System.out.println("IBAN: ");
                iban_cuenta = sc.next().toUpperCase();
            } while (!gbc.isIBANvalido(iban_cuenta) || gbc.isIBANParaBorrar(iban_cuenta) );

        return iban_cuenta;
    }
    
    /*
     * INTERFAZ
     * Signatura: public String iban()
     * Comentario: pide, lee y valida un IBAN
     * Precondiciones:
     * Entradas:
     * Salidas: String
     * Postcondiciones: asociado al nombre se devuelve un String que es el IBAN de la cuenta
     * */
    public String ibanDestino(String IBANOrigen)
    {
    	Scanner sc = new Scanner(System.in);
        GestionBancoComercial gbc = new GestionBancoComercial();
        String IBANDestino;
            do {
                System.out.println("IBAN: ");
                IBANDestino = sc.next().toUpperCase();
            } while (IBANOrigen.equals(IBANDestino) || !gbc.isIBANvalido(IBANDestino) || gbc.isIBANParaBorrar(IBANDestino) );

        return IBANDestino;
    }

    /*
     * INTERFAZ
     * Signatura: public String concepto()
     * Comentario: pide un concepto para una transferencia
     * Precondiciones:
     * Entradas:
     * Salidas: String
     * Postcondiciones: asociado al nombre se devuelve un String que es el concepto
     * */
    public String concepto(){
        Scanner sc = new Scanner(System.in);
        String concepto;
        do {
            System.out.println("Concepto: ");
            concepto = sc.nextLine().toUpperCase();
        }while(concepto.length()<= 2);

        return concepto;
    }

    /*
     * INTERFAZ
     * Signatura: public double cantidadATransferir()
     * Comentario: pide una cantidad para una transferencia
     * Precondiciones:
     * Entradas:
     * Salidas: String
     * Postcondiciones: asociado al nombre se devuelve un double que es la cantidad
     * */
    public double cantidadATransferir(){
        Scanner sc = new Scanner(System.in);
        double cantidad;
        do {
            System.out.println("Cantidad a transferir: ");
            cantidad = sc.nextDouble();
        }while(cantidad <= 0.0);

        return cantidad;
    }

    /*
     * INTERFAZ
     * Signatura: public int anyo()
     * Comentario: pide y valida un año
     * Precondiciones:
     * Entradas:
     * Salidas: int
     * Postcondiciones: asociado al nombre se devuelve un año
     * */
    public int anyo(){
        Scanner sc = new Scanner(System.in);
        int anyo_buscado = 1582;
        do {
            System.out.println("Introduce el a�o de el o los movimiento(s) a buscar: ");
            anyo_buscado = sc.nextInt();
        }while(anyo_buscado < 1582);

        return anyo_buscado;
    }

    /*
     * INTERFAZ
     * Signatura: public int mes()
     * Comentario: pide y valida un mes. El mes 0 significa que el usuario no desea tener en cuenta el mes en la búsqueda.
     * Precondiciones:
     * Entradas:
     * Salidas: int
     * Postcondiciones: asociado al nombre se devuelve un mes
     * */
    public int mes(){
        Scanner sc = new Scanner(System.in);
        int mes_buscado ;
        do {
            System.out.println("Introduce el mes de el o los movimiento(s) a buscar. Escribe 0 si no deseas tener en cuenta el mes: ");
            mes_buscado = sc.nextInt();
        }while(mes_buscado < 0 || mes_buscado > 12);

        return mes_buscado;
    }

    /*
     * INTERFAZ
     * Signatura: public int dia()
     * Comentario: pide y valida un dia. El dia 0 significa que el usuario no desea tener en cuenta el dia en la búsqueda.
     * Precondiciones:
     * Entradas:
     * Salidas: int
     * Postcondiciones: asociado al nombre se devuelve un dia
     * */
    public int dia(){
        Scanner sc = new Scanner(System.in);
        int dia_buscado ;
        do {
            System.out.println("Introduce el dia de el o los movimiento(s) a buscar. Escribe 0 si no deseas tener en cuenta el dia: ");
            dia_buscado = sc.nextInt();
        }while(dia_buscado < 0 || dia_buscado > 31);

        return dia_buscado;
    }

    /*
     * INTERFAZ
     * Signatura: public boolean borrarCuenta()
     * Comentario: pide y valida si el usuario desea realmente eliminar su cuenta bancaria.
     * Precondiciones:
     * Entradas:
     * Salidas: boolean
     * Postcondiciones: asociado al nombre se devuelve un boolean que será true si el usuario efectivamente desea eliminar su cuenta y false si no
     * */
    public boolean borrarCuenta(){
        Scanner sc = new Scanner(System.in);
        boolean seguro = false;
        String respuesta=" ";
        do {
            System.out.println("�Estas seguro de que deseas eliminar tu cuenta bancaria? SI/NO");
            respuesta = sc.nextLine().toUpperCase();
        }while(!respuesta.equals("SI") && !respuesta.equals("NO"));

        if (respuesta.equals("SI")){
            seguro = true;
        }
        return seguro;
    }


}
