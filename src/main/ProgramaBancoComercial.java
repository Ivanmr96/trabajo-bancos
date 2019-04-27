/* ANALISIS
 * Comentario: Programa destinado al uso por trabajadores de un banco comercial para que gestionen su cuenta en el banco central o bien
 * 				que puedan gestionar las cuentas de sus clientes.
 * Entradas: entradas del usuario: opcion menus, iban de la cuenta en el banco central para iniciar sesion, iban de la cuenta en el banco comercial para gestionar la cuenta de un cliente
 * Salidas: eco de los datos, mensajes de ayuda al usuario
 * Restricciones: No se podra iniciar sesion con un IBAN que no pertenezca a una cuenta del banco central. No se podra gestionar
 * 					una cuenta que no esté creada. Las opciones de los menus estarán delimitadas por el programador.
 */

/* PSEUDOCODIGO
 * Inicio
 * 	pedirValidarInicioSesion
 * 	mostrarMenuPedirValidarOpcion
 * 	Mientras (opcionElegida no sea salir)
 * 		Segun(opcionElegida)
 * 			caso 1: realizar transferencia bancaria
 * 			caso 2: ver datos de la cuenta en el banco central
 * 			caso 3: buscar movimientos de la cuenta en el banco central
 * 			caso 4: cliente nuevo
 * 			caso 5: gestionar una cuenta determinada
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
 * 			caso 4: Eliminar cuenta
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
    	GregorianCalendar fecha, fechaActual;
    	char respuestaBorrarCuentas;
    	boolean cuentaBorrada, borradoDefinitivo, ingresado;
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

		  			gestionCentral.realizarMovimiento(IBAN, cuentaDestino, concepto, cantidad, fechaActual); //TODO Aqui pondría que devolviera un boolean para saber si se realizo el movimiento bien o no.
		  			
		  			break;
		  		case 2: 
		  			//ver datos de la cuenta en el banco central
		  			utils.imprimirDatosCuenta(gestionCentral.datosCuenta(IBAN));		//TODO Estaría bien hacerle un pretty print en la clase de utilidad a este metodo
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
		  			
		  			IBANNuevoCliente = gestionComercial.insertarCliente(BIC, DNI,ingresosMensuales);
		  			if(IBANNuevoCliente != null)
		  				System.out.println("Nuevo cliente creado, apunta el IBAN de su cuenta: " + IBANNuevoCliente);
		  			else
		  				System.out.println("El cliente no ha podido crearse, inténtalo de nuevo");
		  			break;
		  		case 5: 
		  			//gestionar una cuenta determinada
	  				//Leer y validar IBAN de cliente
		  			IBANCliente = validaciones.LeerYValidarIBANCliente(BIC);
		  			
	  				//Mostrar menu y validar opcionMenuCliente
		  			opcionMenuCliente = validaciones.mostrarMenuYValidarOpcionMenuCliente();
		  			
		  			while(opcionMenuCliente != 0)
		  			{
		  				switch(opcionMenuCliente)
		  				{
		  					case 1: 
		  						//ver datos de la cuenta
		  						utils.imprimirDatosCuenta(gestionComercial.datosCuenta(IBANCliente));
		  						break;
		  						
	  						case 2: 
	  							//ver movimientos de la cuenta
	  							System.out.println(gestionComercial.ultimosDiezMovimientos(IBANCliente));
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
	  			  								System.out.println("No se pudo ingresar el dinero intentelo de nuevo");
	  			  							break;
	  			  						case 2:
	  			  							//Sacar dinero
	  			  							cantidad = validaciones.leerYValidarCantidadSacar(IBANCliente);
		  			  						fechaActual = new GregorianCalendar();
	  			  							gestionComercial.sacarDinero(IBANCliente, "Retirada" , cantidad, fechaActual); 	//TODO Algun mensaje de ayuda al usuario para que sepa si se realizó bien la operación o no
	  			  							break;
	  			  					}
	  			  					
	  			  					opcionModificarDinero = validaciones.leerYValidarOpcionModificarDinero();
	  			  				}
	  			  				break;
	  			  				
	  			  			case 4: 
	  			  				//Eliminar cuenta
	  			  				cuentaBorrada = gestionComercial.eliminarCuenta(IBANCliente);	//TODO Algun mensaje de ayuda al usuario para que sepa si se realizó bien la operación o no
	  			  				if(cuentaBorrada)
	  			  				{
	  			  					System.out.println("Cuenta con IBAN " + IBANCliente + " borrada");
	  			  				}
	  			  				else
	  			  					System.out.println("La cuenta no pudo borrarse, vuelva a intentarlo");
	  			  				
	  			  				break;
	  			  				
		  				}
		  				
		  				//Mostrar menu y validar opcionMenuCliente
			  			opcionMenuCliente = validaciones.mostrarMenuYValidarOpcionMenuCliente();
		  			}
		  			break;
    		}
	  		
	  		//Mostrar menu y validar opcion elegida
	    	opcionElegida = validaciones.mostrarMenuYValidarOpcionElegida();
    	}
    }
}
