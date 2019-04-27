package tests;

import gestion.*;

import utilidades.Utilidades;

public class MetodosBuscarPorFechaConductor {
    public static void main(String[] args) {
        GestionBancoCentral gbc = new GestionBancoCentral();
        Utilidades util = new Utilidades();
        GestionBancoComercial gb = new GestionBancoComercial();


        //util.imprimirMovimientos(gbc.buscarMovimientosPorFecha("ESPCAIXESBBXXX0000000",25,04,2019));
       // util.imprimirMovimientos(gb.buscarMovimientosPorFecha("ESPCAIXESBBXXX0000001",04,2019));
        gb.ordenarMovimientosPorFecha("ESPCAIXESBBXXX0000001");
        util.imprimirMovimientos(gbc.ultimosDiezMovimientos("ESPCAIXESBBXXX0000001"));

    }
}
