package utilidades;

import gestion.GestionBancoComercial;

import java.util.Scanner;

public class ValidacionProgramaCliente {


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
        System.out.println("Inicio de sesión: ");
        System.out.println("Introduce el IBAN de la cuenta:");
        iban_cuenta = this.iban();

        do {
            System.out.println("Introduce el DNI asociado a la cuenta: ");
            dni = sc.next().toUpperCase();
        } while (!gbc.DNIRegistrado(dni,gbc.obtenerBICporIBAN(iban_cuenta)));

    }while (!gbc.isPropietario(dni,iban_cuenta));
        System.out.println("Sesión iniciada correctamente");
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
            } while (!gbc.isIBANvalido(iban_cuenta));

        return iban_cuenta;
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
            System.out.println("Introduce el año de el o los movimiento(s) a buscar: ");
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
            System.out.println("¿Estás seguro de que deseas eliminar tu cuenta bancaria? SI/NO");
            respuesta = sc.nextLine().toUpperCase();
        }while(!respuesta.equals("SI") && !respuesta.equals("NO"));

        if (respuesta.equals("SI")){
            seguro = true;
        }
        return seguro;
    }


}
