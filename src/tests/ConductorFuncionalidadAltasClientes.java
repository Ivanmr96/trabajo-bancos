package tests;

import gestion.GestionBancoComercial;

public class ConductorFuncionalidadAltasClientes {
    public static void main(String[] args) {
        GestionBancoComercial g = new GestionBancoComercial();
/*
        g.solicitarAltaCliente("CAIXESBBXXX", "12345678-L",9999.9);
        g.solicitarAltaCliente("CAIXESBBXXX", "22345678-L",499.9);
        g.solicitarAltaCliente("CAIXESBBXXX", "32345678-L",5999.9);
        g.solicitarAltaCliente("CAIXESBBXXX", "42345678-L",199.9);
*/


        g.aceptarAltasClientes("CAIXESBBXXX");
    }
}
