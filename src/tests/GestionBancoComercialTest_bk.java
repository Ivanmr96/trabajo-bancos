package tests;

import java.util.GregorianCalendar;

import gestion.GestionBancoComercial;

public class GestionBancoComercialTest_bk {
    public static void main(String[] args) {
        GestionBancoComercial gestion = new GestionBancoComercial();
/*
        System.out.println(gestion.obtenerBICporNombre("LaCaixa"));
        System.out.println(gestion.obtenerNombrePorBIC("CAIXESBBXXX"));

        gestion.modificarSaldoEnFicheroCuentas("ESPCAIXESBBXXX12XXXXXXX", true,50);

        gestion.insertarMovimientoEnFicheroMovimientos("ESPCAIXESBBXXX0000001",true,"PRUEBA",60,1,1,1999  );
        gestion.insertarMovimientoEnFicheroMovimientos("ESPCAIXESBBXXX0000001",true,"PRUEBA",60,1,1,1989  );
        gestion.insertarMovimientoEnFicheroMovimientos("ESPCAIXESBBXXX0000002",true,"PRUEBA",60,1,1,2015  );
        gestion.insertarMovimientoEnFicheroMovimientos("ESPINGDESMMXXX0000002",true,"PRUEBA",60,1,1,1999  );
        gestion.insertarMovimientoEnFicheroMovimientos("ESPBSCHESMMXXX0000003",true,"PRUEBA",60,1,1,1999  );

        //gestion.ordenarMovimientosPorFecha("ESPCAIXESBBXXX12XXXXXXX");

        gestion.realizarMovimiento("ESPBSCHESMMXXX0000003", "ESPCAIXESBBXXX0000001","prueba de transaccion", 512,5,12,2019 );

       System.out.println(gestion.obtenerClientePorIBAN("ESPBSCHESMMXXX0000003"));
        System.out.println(gestion.obtenerIBANPorCliente("LaCaixa", "28835488-C"));

        System.out.println(gestion.obtenerNombreBancoComercialPorIBAN("ESPCAIXESBBXXX0000001"));
*/
/*
        System.out.println("Este dni en este banco: "+gestion.isDNIvalido("LaCaixa","28835488-C"));
        System.out.println("Este IBAN existe: "+gestion.isIBANvalido("ESPINGDESMMXXX0000001"));
        System.out.println("Este dni es propietario de esta cuenta: "+gestion.isPropietario("28835488-C", "ESPINGDESMMXXX0000001"));
        System.out.println(gestion.isDNIvalido("Santander","55555"));
        System.out.println(gestion.isIBANvalido("555555555"));  //nota: el IBAN debe tener al menos 13 caracteres o peta
        System.out.println(gestion.isPropietario("28835488-C","ESPINGDESMMXXX0000003"));
        */


        //gestion.ordenarMovimientosPorFecha("ESPCAIXESBBXXX0000001");    //funciona bien tras el cambio a GregorianCalendar

        //gestion.imprimirUltimosDiezMovimientos("ESPCAIXESBBXXX0000001");
        
        //System.out.println(gestion.DNIRegistrado("98835488-C", "CAIXESBBXX"));
        //String IBAN = gestion.insertarCliente("CAIXESBBXXX", "30216092-X", 415.23);
        //System.out.println(IBAN);
       /*
        System.out.println(gestion.obtenerNumCuentaPorIBAN("ESPCAIXESBBXXX0000001"));
        System.out.println(gestion.obtenerNombreBancoComercialPorIBAN("ESPCAIXESBBXXX0000001"));
        System.out.println(gestion.obtenerNombrePorBIC(gestion.obtenerBICporIBAN("ESPCAIXESBBXXX0000001")));
        System.out.println(gestion.obtenerNumCuentaPorIBAN("ESPCAIXESBBXXX0000001"));
        System.out.println(gestion.obtenerNombrePorBIC("CAIXESBBXXX"));

        */
       //gestion.ordenarMovimientosPorFecha("ESPCAIXESBBXXX0000001");

/*
        for(String element: gestion.buscarMovimientosPorAnyo("ESPCAIXESBBXXX0000001",2017)){
            System.out.println(element);
        }

        for(String element: gestion.buscarMovimientosPorDiaMesYAnyo("ESPCAIXESBBXXX0000001",19,4,2019)){
            System.out.println(element);
        }

 */
/*
        for(String element: gestion.buscarMovimientosPorMesYAnyo("ESPCAIXESBBXXX0000001",4,2017)){
            System.out.println(element);
        }
*/
/*
        System.out.println(gestion.obtenerBICporIBAN("ESPCAIXESBBXXX0000001"));
        System.out.println(gestion.obtenerNombrePorBIC("CAIXESBBXXX"));
 */
        //gestion.marcarCuentaComoBorrada("ESPBSCHESMMXXX0000001");
        //System.out.println(gestion.isIBANvalido("ESPBSCHESMMXXX0000002"));
        //System.out.println(gestion.marcarCuentaComoBorrada("ESPBSCHESMMXXX0000002"));
        //System.out.println(gestion.eliminarCuenta("ESPBSCHESMMXXX0000002"));
       //gestion.eliminarCuentasBorradasDefinitivamente("BSCHESMMXXX");

        //System.out.println(gestion.datosCuenta("ESPBSCHESMMXXX0000002"));
        //System.out.println(gestion.obtenerSaldoPorIBAN("ESPCAIXESBBXXX0000001"));
        //System.out.println(gestion.modificarSaldoEnFicheroCuentas("ESPCAIXESBBXXX0000001", true, 400));
        //System.out.println(gestion.obtenerSaldoPorIBAN("ESPCAIXESBBXXX0000001"));
        //System.out.println(gestion.obtenerClientePorIBAN("ESPCAIXESBBXXX0000001"));
        
        //System.out.println(gestion.insertarCliente("CAIXESBBXXX", "30216092-E", 1000));
        
        System.out.println(gestion.sacarDinero("ESPCAIXESBBXXX0000001", "Conceptazo", 100, new GregorianCalendar()));
        //System.out.println(gestion.ultimosDiezMovimientos("ESPCAIXESBBXXX0000001"));
    }
}
