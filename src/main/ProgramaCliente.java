/*
* ANALISIS
* Comentario: Programa para el cliente desde donde puede realizar transferencias bancarias a otras cuentas,
*               ver el saldo de su propia cuenta, buscar un movimiento de su cuenta y cancelar su cuenta, entre otras cosas.
*               Tambien puede crearse una cuenta en un banco.
* Entradas:
*   -Entradas del usuario:
*       Para registrarse:
*       -BIC del banco
*       -DNI
*       -Ingresos mensuales
*
*       Para validar el inicio de sesion:
*       -DNI
*       -IBAN cuenta
*
*       -Opcion para el menu
*
*       Para realizar transferencia bancaria a otra cuenta
*       -IBAN destino
*       -Concepto
*       -Cantidad
*
*       Para buscar movimientos por fecha
*       -dia
*       -mes
*       -año
*
*       Para borrar cuenta
*       -Respuesta a si desea borrar la cuenta
*
* Salidas:
*      -Eco de los datos y distintos mensajes de ayuda al usuario, comunicando cuando las distintas operaciones se realicen con exito o no.
*      -Menu
*
* Restricciones:
*      Inicio de sesion:
*           -DNI e IBAN deben existir en los ficheros maestros correspondientes y no estar en los de movimientos. No podrán iniciar sesión si
*           la cuenta se encuentra en solicitud de baja.
*      Registro:
*           -El DNI no debe existir y debe ser valido
*           -El banco donde se vaya a crear la cuenta debe estar registrado en el banco central
*           -Los ingresos mensuales no pueden ser negativos
*      Menu:
*           -La opcion del menu debe estar en el rango de opciones validas (0 - 4)
*
*      Realizar transferencia:
*           -El IBAN de destino debe ser un IBAN válido de cualquier entidad bancaria comercial registrada en el banco central. No puede ser
*            el IBAN propio.
*           -La cantidad de dinero a enviar no puede ser negativa.
*
* 	   Para buscar movimientos por fecha:
* 			- El dia debe ser un numero entre 0 y 31 (0 indica que busque en todos los dias del mes)
* 			- El mes debe ser un numero entre 0 y 12 (0 indica que busque en todos los meses del año)
* 			- El año debe ser un numero mayor que 1582
*
* PSEUDOCODIGO MAIN - VISTA DEL CLIENTE
* inicio
* repetir
*   preguntarLogInOrSignUp
*   segun respuesta
*       caso log in
*           pedirValidarInicioSesion    //pide el DNI del cliente y el IBAN de la cuenta propia
*           repetir
*               mostrarMenuPedirValidarOpcion
*                   si (opcion no es salir)
*                       segun(opcion)
*                           caso 1: realizar transferencia bancaria
*                           caso 2: ver datos de la cuenta propia
*                           caso 3: buscar movimientos
*                           caso 4: cancelar cuenta
*                       finSegun
*                   finSi
*           mientras(opcion no sea salir)
*       caso registrarse
*           pedirValidarBanco
*               si (opcion no es salir)
*                   pedirValidarDatos
*                   solicitarAltaCliente
*               finSi
*   finSegun
* mientras(opcion no sea salir)
* fin
* */
package main;

import clasesBasicas.TransferenciaImpl;
import gestion.GestionBancoComercial;
//import resguardos.ResguardoGestionBancoComercial;
import utilidades.Utilidades;
import utilidades.ValidacionProgramaCliente;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ProgramaCliente {
    public static void main(String[] args) {
        ValidacionProgramaCliente validar = new ValidacionProgramaCliente();
        //ResguardoGestionBancoComercial resguardo = new ResguardoGestionBancoComercial();
        Utilidades utilidad = new Utilidades();
        GestionBancoComercial gestion = new GestionBancoComercial();
        GregorianCalendar fecha = null;
        int opcion, dia, mes, anyo;
        double cantidad;
        String bic;
        String dni;
        String nuevoIBAN = " ";
        double ingresos = 0.0;
        String iban_cuenta, iban_destino, concepto;
        String mensajeUsuario;  //para almacenar mensajes de ayuda al usuario
        List<TransferenciaImpl> movimientos = new ArrayList<TransferenciaImpl>();
        int inicioSesionORegistro = 0;

        do{
            //preguntarLogInOrSignUp
            inicioSesionORegistro = validar.logInORSignUp();
            switch(inicioSesionORegistro) {
                case 1:
                	
                    //pedirValidarInicioSesion
                    iban_cuenta = validar.inicioSesion();
                    do {
                        opcion = validar.menu();
                        switch (opcion) {
                            case 1:
                                //realizar transferencia bancaria
                                System.out.println("Va a realizar una transferencia bancaria.");
                                iban_destino = validar.ibanDestino(iban_cuenta);
                                concepto = validar.concepto();
                                cantidad = validar.cantidadATransferir();
                                fecha = new GregorianCalendar();

                                if(!validar.umbralNumerosRojos(new TransferenciaImpl(iban_cuenta, false, concepto, cantidad, fecha))){
                                    mensajeUsuario = "Saldo insuficiente para realizar ese movimiento";
                                }else{
                                    mensajeUsuario = (gestion.realizarMovimiento(iban_cuenta, iban_destino, concepto, cantidad, fecha)) ? "Transferencia realizada con exito" : "Problemas en su operacion. Vuelva a intentarlo mas tarde";
                                }

                                System.out.println(mensajeUsuario);

                                break;
                            case 2:
                                //Ver datos de la cuenta propia
                                utilidad.imprimirDatosCuenta(gestion.datosCuenta(iban_cuenta));
                                break;

                            case 3:
                                //ver movimientos de la cuenta propia
                                System.out.println("Movimientos de la cuenta:");
                                gestion.ordenarMovimientosPorFecha(iban_cuenta);    //YA FUNCIONA! =D
                                if (gestion.ultimosDiezMovimientos(iban_cuenta).size() > 0)
                                    utilidad.imprimirMovimientos(gestion.ultimosDiezMovimientos(iban_cuenta));
                                else
                                    System.out.println("No existen transferencias.");

                                break;
                            case 4:
                                //buscar movimientos
                                dia = validar.dia();
                                mes = validar.mes();
                                anyo = validar.anyo();
                                if (mes == 0 && dia == 0) {
                                    movimientos = gestion.buscarMovimientosPorFecha(iban_cuenta, anyo);
                                } else if (mes != 0 && dia == 0) {
                                    movimientos = gestion.buscarMovimientosPorFecha(iban_cuenta, mes, anyo);
                                } else {
                                    movimientos = gestion.buscarMovimientosPorFecha(iban_cuenta, dia, mes, anyo);
                                }

                                if (movimientos.size() > 0) {
                                    utilidad.imprimirMovimientos(movimientos);
                                } else {
                                    System.out.println("No existen movimientos con esas caracteristicas.");
                                }
                                
                                break;
                            case 5:
                                //cancelar cuenta
                                if (validar.borrarCuenta()) {

                                    if (gestion.marcarCuentaComoBorrada(iban_cuenta)) {
                                        System.out.println("Su cuenta ha sido borrada.");
                                        System.out.println("Se le forzara el cierre de sesion.");
                                        opcion = 0;
                                    } else {
                                        System.out.println("Hubo un error inesperado, no se borrara su cuenta.");
                                    }

                                } else {
                                    System.out.println("No se borrara su cuenta.");
                                }
                                
                                break;
                        }

                    } while (opcion != 0);
                    break;

                case 2:
                    //pedirValidarBanco
                    bic = validar.bancoCentral();
                    if (!bic.equals("NOVALUE")) {
                        //pedirValidarDatos
                        dni = validar.dniCliente(bic);
                        ingresos = validar.ingresosCliente();
                        //solicitarAltaCliente
                        nuevoIBAN = gestion.solicitarAltaCliente(bic, dni, ingresos);
                        if( nuevoIBAN!= null){
                            System.out.println("Tu nuevo IBAN es: "+ nuevoIBAN);
                            System.out.println("Tu solicitud esta enviada. En alrededor de unas horas procesaremos su nueva cuenta y podra iniciar sesion con ella.");
                        }else{
                            System.out.println("Error al enviar la solicitud, intentelo de nuevo mas tarde");
                        }

                        }
                    break;
            }
    }while(inicioSesionORegistro != 0);


    }
}
