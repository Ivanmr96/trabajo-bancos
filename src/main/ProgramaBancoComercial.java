/* ANALISIS
 * Comentario: Programa destinado al uso por trabajadores de un banco comercial para que gestionen su cuenta en el banco central o bien
 * 				que puedan gestionar las cuentas de sus clientes.
 * 
 * Entradas: 
 * 		- entradas del usuario: 
 * 			> Para validar el inicio de sesion:
 * 				* un IBAN
 * 			> Una opcion para el menu principal
 * 			> Para realizar una transferencia:
 * 				* Un IBAN de destino
 * 				* Una cantidad
 * 				* Un concepto
 * 			> Para buscar movimientos por fecha:
 * 				* Un dia (opcional)
 * 				* Un mes (opcional)
 * 				* Un a�o
 * 			> Para crear un cliente nuevo:
 * 				* Un DNI
 * 				* Unos ingresos mensuales
 * 			> Para iniciar sesion en una cuenta de un cliente determinado:
 * 				* Un IBAN
 * 			> Una opcion para el submenu de gestion de una cuenta determinada
 * 			> Para modificar el dinero de una cuenta de un cliente determinado:
 * 				* Una opcion (insertar o sacar)
 * 				* Una cantidad
 * 			> iban de la cuenta en el banco central para iniciar sesion
 * 			> iban de la cuenta en el banco comercial para gestionar la cuenta de un cliente
 * 
 * Salidas: 
 * 		- Eco de los datos y distintos mensajes de ayuda al usuario, comunicando cuando las distintas operaciones se realicen con exito o no.
 * 		- Dos menus, un menu principal con estas opciones:
 * 				0) Salir
 * 				1) Realizar transferencia bancaria
 * 				2) Ver datos de la cuenta en el banco central
 * 				3) Buscar movimientos de la cuenta en el banco central
 * 				4) Cliente nuevo
 * 				5) Gestionar una cuenta determinada
 * 				6) Actualizar altas y bajas
 * 			As� como un submenu para la opcion numero 5 "Gestionar una cuenta determinada", con estas opciones:
 * 				0) Volver atras
 * 				1) Ver datos de la cuenta
 * 				2) Ver movimientos de la cuenta
 * 				3) Modificar dinero de la cuenta
 * 				4) Marcar cuenta como borrada
 * 
 * Restricciones: 
 * 		- Inicio de sesion:
 * 			> el IBAN debe ser un IBAN existente en las cuentas del banco central.
 * 		- La opcion del menu principal debe ser un numero entre 0 y 6 (0 para salir)
 * 		- Para realizar una transferencia:
 * 			> El IBAN de destino debe ser un IBAN de un cliente del banco central, es decir, otro banco comercial.
 * 			> La cantidad de dinero a enviar en la transferencia debe ser un numero positivo
 * 		- Para buscar movimientos por fecha:
 * 			> El dia debe ser un numero entre 0 y 31 (0 indica que busque en todos los dias del mes)
 * 			> El mes debe ser un numero entre 0 y 12 (0 indica que busque en todos los meses del a�o)
 * 			> El a�o debe ser un numero mayor que 1582
 * 		- Para crear un cliente nuevo:
 * 			> El DNI debe ser un DNI que no este registrado ya en el banco como un cliente
 * 			> Los ingresos mensuales no pueden ser negativos
 * 		- Para iniciar sesion en una cuenta de un cliente determinado:
 * 			> El IBAN debe estar registrado en el banco
 * 		- La opcion del submenu "Gestionar una cuenta determinada" debe ser un numero entre 0 y 4 (0 para volver al menu principal)
 * 		- Para modificar el dinero de una cuenta de un cliente determinado:
 * 			> La opcion de ingresar o sacar dinero debe ser un numero entre 0 y 2 (0 para volver al menu anterior)
 * 			> La cantida debe ser un numero positivo.
 */

/* PSEUDOCODIGO
 * Inicio
 * 	Leer y validar inicio de sesion
 * 	Mostrar menu y validar opcion elegida
 * 	Mientras (opcionElegida no sea salir)
 * 		Segun(opcionElegida)
 * 			caso 1: realizar transferencia bancaria
 * 			caso 2: ver datos de la cuenta en el banco central
 * 			caso 3: buscar movimientos de la cuenta en el banco central
 * 			caso 4: cliente nuevo
 * 			caso 5: gestionar una cuenta determinada
 * 			caso 6: actualizar altas y bajas
 * 		FinSegun
 * 		Mostrar menu y validar opcion elegida
 * 	FinMientras
 * Fin
 */

/* PSEUDOCODIGO (modulos)
 * 
 * - gestionar una cuenta determinada
 * Inicio
 * 	Leer y validar IBAN de cliente
 *	Mostar menu y validar opcion
 * 	Mientras (opcion no sea volver atras)
 * 		Segun(opcion)
 * 			caso 1: ver datos de la cuenta
 * 			caso 2: ver movimientos de la cuenta
 * 			caso 3: modificar dinero de la cuenta
 * 			caso 4: Marcar cuenta como borrada
 * 		FinSegun
 * 	FinMientras
 * Fin
 */

package main;

import java.util.*;

import clasesBasicas.TransferenciaImpl;
import gestion.GestionBancoCentral;
import gestion.GestionBancoComercial;
import utilidades.Utilidades;
import utilidades.ValidacionesProgramaBancoComercial;

public class ProgramaBancoComercial 
{
	public static void main(String[] args)
    {
		Scanner teclado = new Scanner(System.in);
    	ValidacionesProgramaBancoComercial validaciones = new ValidacionesProgramaBancoComercial();
    	Utilidades utils = new Utilidades();
    	double cantidad, ingresosMensuales;
    	int opcionElegida, opcionModificarDinero, opcionMenuCliente;
    	GestionBancoComercial gestionComercial = new GestionBancoComercial();
    	GestionBancoCentral gestionCentral = new GestionBancoCentral();
    	String cuentaDestino, IBAN, DNI, BIC, concepto, IBANNuevoCliente, IBANCliente;
    	GregorianCalendar fechaActual;
    	boolean ingresado;
    	boolean cuentaBorrada = false;
    	int dia, mes, anyo;
    	List<TransferenciaImpl> movimientos = new ArrayList<TransferenciaImpl>();
    	
	 	//Leer y validar inicio de sesion
    	IBAN = validaciones.iniciarSesion();
    	BIC = gestionCentral.obtenerBICporIBAN(IBAN);
    	
	  	//Mostrar menu y validar opcion elegida
    	opcionElegida = validaciones.mostrarMenuYValidarOpcionElegida();
    	
    	while(opcionElegida != 0)
    	{
	  		switch(opcionElegida)
	  		{
		  		case 1: 
		  			//realizar transferencia bancaria
		  			cuentaDestino = validaciones.leerYValidarCuentaDestino();
		  			
		  			System.out.println("Cantidad: ");
		  			cantidad = teclado.nextDouble();
		  			
		  			teclado.nextLine();
		  			System.out.print("Concepto: ");
		  			concepto = teclado.nextLine();
		  			
		  			fechaActual = new GregorianCalendar();

		  			if(gestionCentral.realizarMovimiento(IBAN, cuentaDestino, concepto, cantidad, fechaActual))
		  				System.out.println("La transferencia se hizo correctamente.");
		  			else
		  				System.out.println("La trasferencia no pudo hacerse, intentelo de nuevo.");
		  			
		  			break;
		  		case 2: 
		  			//ver datos de la cuenta en el banco central
		  			utils.imprimirDatosCuenta(gestionCentral.datosCuenta(IBAN));
		  			break;
		  		case 3:
		  			//buscar movimientos por fecha de la cuenta en el banco central
					dia = validaciones.dia();
					mes = validaciones.mes();
					anyo = validaciones.anyo();
					if(mes == 0 && dia == 0){
						movimientos = gestionCentral.buscarMovimientosPorFecha(IBAN,anyo);
					}else if (mes != 0 && dia == 0){
						movimientos = gestionCentral.buscarMovimientosPorFecha(IBAN, mes,anyo);
					}else{
						movimientos = gestionCentral.buscarMovimientosPorFecha(IBAN,dia,mes,anyo);
					}

					if(movimientos.size() > 0 ){
						utils.imprimirMovimientos(movimientos);
					}else{
						System.out.println("No existen movimientos con esas caracteristicas.");
					}
		  				break;
		  		case 4: 
		  			//cliente nuevo
		  			DNI = validaciones.leerYValidarDNI(BIC);		
		  			
		  			ingresosMensuales = validaciones.leerYValidarIngresosMensuales();
		  			
		  			IBANNuevoCliente = gestionComercial.insertarCliente(BIC, DNI,ingresosMensuales);		//TODO Cuando el banco quiera hacer un alta nueva, que se haga directamente.
		  			if(IBANNuevoCliente != null)
		  				System.out.println("Nuevo cliente creado, apunta el IBAN de su cuenta: " + IBANNuevoCliente);
		  			else
		  				System.out.println("El cliente no ha podido crearse, int�ntalo de nuevo");
		  			break;
		  		case 5: 
		  			//gestionar una cuenta determinada

	  				//Leer y validar IBAN de cliente
		  			IBANCliente = validaciones.LeerYValidarIBANCliente(BIC);	//El banco si puede entrar en las que estan marcadas como borradas porque es el banco el gestor
		  			
	  				//Mostrar menu y validar opcionMenuCliente
		  			opcionMenuCliente = validaciones.mostrarMenuYValidarOpcionMenuCliente();
		  			
		  			while(opcionMenuCliente != 0 && !cuentaBorrada)
		  			{
		  				switch(opcionMenuCliente)
		  				{
		  					case 1: 
		  						//ver datos de la cuenta
		  						utils.imprimirDatosCuenta(gestionComercial.datosCuenta(IBANCliente));
		  						break;
		  						
	  						case 2: 
	  							//ver movimientos de la cuenta
	  							gestionComercial.ordenarMovimientosPorFecha(IBANCliente);
	  							if(gestionComercial.ultimosDiezMovimientos(IBANCliente) != null)
	  								utils.imprimirMovimientos(gestionComercial.ultimosDiezMovimientos(IBANCliente));
	  							else
	  								System.out.println("No existen transferencias.");
	  							break;
	  							
	  			  			case 3: 
	  			  				//modificar dinero de la cuenta
	  			  				
	  			  				opcionModificarDinero = validaciones.leerYValidarOpcionModificarDinero();
		
	  			  				while(opcionModificarDinero != 0)
	  			  				{
	  			  					switch(opcionModificarDinero)
	  			  					{
	  			  						case 1:
	  			  							//Insertar dinero
	  			  							cantidad = validaciones.leerYValidarCantidadInsertar();
	  			  							fechaActual = new GregorianCalendar();
	  			  							ingresado = gestionComercial.ingresarDinero(IBANCliente, "Ingreso" , cantidad, fechaActual);
	  			  							if(ingresado)
	  			  								System.out.println("Dinero ingresado con exito");
	  			  							else
	  			  								System.out.println("No se pudo ingresar el dinero, intentelo de nuevo");
	  			  							break;
	  			  						case 2:
	  			  							//Sacar dinero
	  			  							cantidad = validaciones.leerYValidarCantidadSacar(IBANCliente);
		  			  						fechaActual = new GregorianCalendar();
	  			  							if(gestionComercial.sacarDinero(IBANCliente, "Retirada" , cantidad, fechaActual))
	  			  								System.out.println("Dinero sacado con exito");
	  			  							else
	  			  								System.out.println("No se pudo sacar el dinero, intentelo de nuevo");
	  			  							break;
	  			  					}
	  			  					
	  			  					opcionModificarDinero = validaciones.leerYValidarOpcionModificarDinero();
	  			  				}
	  			  				break;
	  			  				
	  			  			case 4: 
	  			  				//Marcar cuenta como borrada
	  			  				cuentaBorrada = gestionComercial.marcarCuentaComoBorrada(IBANCliente);
	  			  				//cuentaBorrada = gestionComercial.eliminarCuenta(IBANCliente);
	  			  				if(cuentaBorrada)
	  			  				{
	  			  					System.out.println("Cuenta con IBAN " + IBANCliente + " ha sido marcada como borrada. Actualiza las altas/bajas para confirmar la baja.");
	  			  				}
	  			  				else
	  			  					System.out.println("La cuenta no pudo marcarse como borrada, vuelva a intentarlo.");
	  			  				
	  			  				break;
	  			  				
		  				}
		  				
		  				//Mostrar menu y validar opcionMenuCliente
		  				if(!cuentaBorrada)
		  					opcionMenuCliente = validaciones.mostrarMenuYValidarOpcionMenuCliente();
		  			}
		  			cuentaBorrada = false;
		  			break;
		  		case 6:
		  			//actualizar altas y bajas
					gestionComercial.aceptarAltasBajasClientes(BIC);
					System.out.println("Se han actualizado las nuevas altas y bajas del banco.");
					System.out.println("Ahora los nuevos clientes podr�n iniciar sesi�n con su cuenta ");
		  			break;
    		}
	  		
	  		//Mostrar menu y validar opcion elegida
	    	opcionElegida = validaciones.mostrarMenuYValidarOpcionElegida();
    	}
    }
}
