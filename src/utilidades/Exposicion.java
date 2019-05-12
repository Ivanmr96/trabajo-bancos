package utilidades;

import gestion.GestionBancoComercial;

import java.util.GregorianCalendar;

public class Exposicion {
    public static void main(String[] args) {
        GestionBancoComercial gbc = new GestionBancoComercial();
        Utilidades u = new Utilidades();
        GregorianCalendar fecha = new GregorianCalendar();

        String ibanOrigen = "ESPCAIXESBBXXX0000003";
        String ibanDestino = "ESPCAIXESBBXXX0000001";
        int dia = 1;
        int mes = 5;
        int anyo = 2019;
        String concepto = "nomina";
        double cantidad = 1000;

        fecha.set(GregorianCalendar.YEAR, anyo);
        fecha.set(GregorianCalendar.MONTH, mes);
        fecha.set(GregorianCalendar.DATE, dia);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,concepto, cantidad, fecha );

/*
        fecha.set(GregorianCalendar.YEAR, 1999);
        fecha.set(GregorianCalendar.MONTH, 1);
        fecha.set(GregorianCalendar.DATE, 20);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"asun apruebame", 5, fecha );


        fecha.set(GregorianCalendar.YEAR, 2000);
        fecha.set(GregorianCalendar.MONTH, 5);
        fecha.set(GregorianCalendar.DATE, 10);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"buenos dias por la mañana", 1, fecha );

        fecha.set(GregorianCalendar.YEAR, 2017);
        fecha.set(GregorianCalendar.MONTH, 7);
        fecha.set(GregorianCalendar.DATE, 23);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"ivan callate", 1, fecha );

        fecha.set(GregorianCalendar.YEAR, 2018);
        fecha.set(GregorianCalendar.MONTH, 9);
        fecha.set(GregorianCalendar.DATE, 3);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"merge this", 3, fecha );


        fecha.set(GregorianCalendar.YEAR, 1996);
        fecha.set(GregorianCalendar.MONTH, 3);
        fecha.set(GregorianCalendar.DATE, 2);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"un dia como hoy nacio ivan", 5, fecha );


        fecha.set(GregorianCalendar.YEAR, 1997);
        fecha.set(GregorianCalendar.MONTH, 7);
        fecha.set(GregorianCalendar.DATE, 23);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"un dia como hoy nacio angela", 5, fecha );

        fecha.set(GregorianCalendar.YEAR, 2019);
        fecha.set(GregorianCalendar.MONTH, 1);
        fecha.set(GregorianCalendar.DATE, 23);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"otro movimiento", 5, fecha );

        fecha.set(GregorianCalendar.YEAR, 2018);
        fecha.set(GregorianCalendar.MONTH, 10);
        fecha.set(GregorianCalendar.DATE, 3);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"dame veneno que me quiero morir", 5, fecha );

        fecha.set(GregorianCalendar.YEAR, 2008);
        fecha.set(GregorianCalendar.MONTH, 4);
        fecha.set(GregorianCalendar.DATE, 30);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"HOY SE ESTRENA IRON MAN!!!", 5, fecha );

        fecha.set(GregorianCalendar.YEAR, 2019);
        fecha.set(GregorianCalendar.MONTH, 1);
        fecha.set(GregorianCalendar.DATE, 23);
        gbc.realizarMovimiento(ibanOrigen,ibanDestino,"esto funciona perfe claro que si", 5, fecha );
*/
       // gbc.ordenarMovimientosPorFecha(ibanOrigen);
       // u.imprimirMovimientos(gbc.ultimosDiezMovimientos(ibanOrigen));


    }
}
