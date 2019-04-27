package utilidades;

import java.util.GregorianCalendar;
import java.util.Scanner;

import gestion.GestionBancoCentral;
import gestion.GestionBancoComercial;

public class ValidacionesProgramaBancoComercial 
{
	/* INTERFAZ
	 * Comentario: Lee y valida un inicio de sesion.
	 * Prototipo: public void iniciarSesion()
	 * Entrada: No hay
	 * Precondiciones: No hay
	 * Salida: Un String con el IBAN de la cuenta que ha iniciado sesion
	 * Postcondiciones: Asociado al nombre devuelve un String con el IBAN de la cuenta que ha iniciado sesion
	 */
	public String iniciarSesion()
	{
		GestionBancoCentral gestion = new GestionBancoCentral();
		Scanner teclado = new Scanner(System.in);
		String IBAN;
		
		System.out.println("Iniciar sesion...");
		
		do
		{
			System.out.print("IBAN: ");
			IBAN = teclado.next();
		}while(gestion.IBANRegistrado(IBAN) == false);		//OK
		
		System.out.println("Inicio de sesion correcto en la cuenta " + IBAN);
		
		return IBAN;
	}
	
	/* INTERFAZ
	 * Comentario: Muestra el menu principal y valida una opcion elegida
	 * Prototipo: public int mostrarMenuYValidarOpcionElegida
	 * Entrada: No hay
	 * Precondiciones: No hay
	 * Salida: un entero con la opcion elegida
	 * Postcondiciones: Asociado al nombre devuelve un entero con la opcion del menu elegida
	 */
	public int mostrarMenuYValidarOpcionElegida()
	{
		int opcion;
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("MENU PRINCIPAL");
		System.out.println("1) Realizar transferencia bancaria");
		System.out.println("2) Ver datos de la cuenta en el banco central");
		System.out.println("3) Buscar movimientos de la cuenta en el banco central");
		System.out.println("4) Cliente nuevo");
		System.out.println("5) Gestionar una cuenta determinada");
		System.out.println("6) Eliminar permanentemente las cuentas marcadas");
		System.out.println("0) Salir del programa");
		
		do
		{
			System.out.println("Elige una opcion:");
			opcion = teclado.nextInt();
		}while(opcion < 0 || opcion > 6);
		
		return opcion;
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida si existe una cuenta
	 * Prototipo: public String leerYValidarCuentaDestino()
	 * Entrada: No hay
	 * Precondiciones: No hay
	 * Salida: Un String con el IBAN de la cuenta
	 * Postcondiciones: Asociado al nombre devuelve un String con el IBAN de la cuenta
	 */
	public String leerYValidarCuentaDestino()
	{
		String cuentaDestino;
		GestionBancoCentral gestion = new GestionBancoCentral();
		Scanner teclado = new Scanner(System.in);
		
		do
		{
			System.out.print("Introduce IBAN de destino: ");
			cuentaDestino = teclado.next();
		}while(gestion.IBANRegistrado(cuentaDestino) == false); 	//OK
		
		return cuentaDestino;
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida una fecha
	 * Prototipo: public GregorianCalendar leerYValidarFecha()
	 * Entrada: No hay
	 * Precondiciones: No hay
	 * Salida: Un objeto tipo GregorianCalendar
	 * Postcondiciones: Asociado al nombre devuelve un GregorianCalendar que representa la fecha validada.
	 */
	public GregorianCalendar leerYValidarFecha()
	{
		int dia = 0;
		int mes = 0;
		int anho = 0;
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Dia: ");
		dia = teclado.nextInt();
		
		System.out.print("Mes: ");
		mes = teclado.nextInt();
		
		System.out.print("Anho: ");
		anho = teclado.nextInt();
		
		return new GregorianCalendar(anho, mes, dia);
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida un DNI
	 * Prototipo: public Strnig leerYValidarDNI()
	 * Entrada: un String con el BIC del banco donde se comprobará si el DNI existe o no
	 * Precondiciones: el BIC debe ser de un banco existente
	 * Salida: Un String con un DNI
	 * Postcondiciones: Asociado al nombre devuelve un String con un DNI validado
	 */
	public String leerYValidarDNI(String BIC)
	{
		String DNI;
		Scanner teclado = new Scanner(System.in);
		GestionBancoComercial gestion = new GestionBancoComercial();
		
		do
		{
			System.out.print("Introduce DNI: ");
			DNI = teclado.next();
		}while(gestion.DNIRegistrado(DNI, BIC));
		
		return DNI;
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida una cantidad de ingresos mensuales
	 * Prototipo: public double leerYValdiarIngresosMensuales()
	 * Entrada: No hay
	 * Precondiciones: No hay
	 * Salida: un double con una cantidad de ingresos mensuales
	 * Postcondiciones: Asociado al nombre devuelve un double con un cantidad de ingresos mensuales positiva
	 */
	public double leerYValidarIngresosMensuales()
	{
		Scanner teclado = new Scanner(System.in);
		double ingresosMensuales;
		
		do
		{
			System.out.print("Introduce ingresos mensuales: ");
			ingresosMensuales = teclado.nextDouble();
		}while(ingresosMensuales < 0);
		
		return ingresosMensuales;
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida un IBAN de un banco comercial.
	 * Prototipo: public void LeerYValidarIBANCliente()
	 * Entrada: un String con el BIC del banco donde se quiere comprobar el IBAN
	 * Precondiciones: el BIC debe ser de un banco existente
	 * Salida: Un String con el IBAN de la cuenta
	 * Postcondiciones: Asociado al nombre devuelve un String con el IBAN de la cuenta
	 */
	public String LeerYValidarIBANCliente(String BIC)
	{
		//GestionBancoCentral gestion = new GestionBancoCentral();
		GestionBancoComercial gestion = new GestionBancoComercial();
		Scanner teclado = new Scanner(System.in);
		String IBAN, BICInsertado;
		
		do
		{
			System.out.print("IBAN Cliente: ");
			IBAN = teclado.next();
			BICInsertado = gestion.obtenerBICporIBAN(IBAN);
			
		}while(gestion.isIBANvalido(IBAN) == false || BICInsertado.equals(BIC) == false );
		
		System.out.println("Cuenta de cliente " + IBAN + " correcta.");
		
		return IBAN;
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida una cantidad de dinero a insertar en una cuenta cliente
	 * Prototipo: public double leerYValidarCantidadInsertar()
	 * Entrada: No hay
	 * Precondiciones: No hay
	 * Salida: Un double con la cantidad validad
	 * Postcondiciones: Asociado al nombre devuelve un double con la cantidad de dinero a insertar validada
	 */
	public double leerYValidarCantidadInsertar()
	{
		double cantidad;
		Scanner teclado = new Scanner(System.in);
		
		do
		{
			System.out.println("Introduce cantidad a insertar: ");
			cantidad = teclado.nextDouble();
		}while(cantidad <= 0);
		
		return cantidad;
	}
	
	/* INTERFAZ
	 * Comentario: Lee y valida una cantidad de dinero a sacar de una cuenta cliente
	 * Prototipo: public double leerYValidarCantidadSacar(String IBAN)
	 * Entrada: un String con el IBAN del cliente
	 * Precondiciones: No hay
	 * Salida: Un double con la cantidad validada
	 * Postcondiciones: Asociado al nombre devuelve un double con la cantidad de dinero a sacar validada
	 */
	public double leerYValidarCantidadSacar(String IBAN)
	{
		double cantidad;
		Scanner teclado = new Scanner(System.in);
		
		GestionBancoComercial gestion = new GestionBancoComercial();
		String cuenta = gestion.datosCuenta(IBAN);
		double saldo = Double.parseDouble(cuenta.split(",")[1]);
		
		do
		{
			System.out.println("Introduce cantidad a sacar: ");
			cantidad = teclado.nextDouble();
		}while(cantidad <= 0);
		
		return cantidad;
	}
	
	public int leerYValidarOpcionModificarDinero()
	{
		int opcionModificarDinero;
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("1) Insertar dinero");
		System.out.println("2) Sacar dinero");
		System.out.println("0) Volver");
	
		do
			{
				System.out.println("Elige una opcion: ");
				opcionModificarDinero = teclado.nextInt();
			}while(opcionModificarDinero < 0 || opcionModificarDinero > 2);
		
		return opcionModificarDinero;
	}
	
	public int mostrarMenuYValidarOpcionMenuCliente()
	{
		int opcionMenuCliente;
		Scanner teclado = new Scanner(System.in);
		
			System.out.println("MENU Cliente");
			System.out.println("1) Ver datos de la cuenta");
			System.out.println("2) Ver movimientos de la cuenta");
			System.out.println("3) Modificar dinero de la cuenta");
			System.out.println("4) Eliminar cuenta");
			System.out.println("0) Volver atras");
			
			do
			{
				System.out.println("Elige una opcion: ");
				opcionMenuCliente = teclado.nextInt();
			}while(opcionMenuCliente < 0 || opcionMenuCliente > 4);
			
		return opcionMenuCliente;
	}
	
	public char leerYValidarBorrarCuentas()
	{
		char respuestaBorrarCuentas;
		
		Scanner teclado = new Scanner(System.in);
		
		do
			{
				System.out.println("Estas seguro que deseas borrar permanentemente todas las cuentas marcadas como borradas? (S/N): ");
				respuestaBorrarCuentas = teclado.next().charAt(0);
			}while(respuestaBorrarCuentas != 'S' && respuestaBorrarCuentas != 'N');
		
		return respuestaBorrarCuentas;
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


}
