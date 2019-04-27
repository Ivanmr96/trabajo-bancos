package tests;
import gestion.GestionBancoCentral;
import gestion.GestionBancoComercial;
import utilidades.Utilidades;

import java.util.GregorianCalendar;

public class UtilidadesTest {
    public static void main(String[] args) {
        Utilidades util = new Utilidades();
        GestionBancoCentral g = new GestionBancoCentral();
        GestionBancoComercial gb = new GestionBancoComercial();
       gb.insertarMovimientoEnFicheroMovimientos("ESPCAIXESBBXXX0000001",true,"siete",45,new GregorianCalendar());
        //g.insertarMovimientoEnFicheroMovimientos("ESPCAIXESBBXXX0000000",true,"Hola",45,new GregorianCalendar());
        //util.imprimirMovimientos(g.ultimosDiezMovimientos("ESPCAIXESBBXXX0000000"));
        util.imprimirMovimientos(gb.ultimosDiezMovimientos("ESPCAIXESBBXXX0000001"));

    }
}