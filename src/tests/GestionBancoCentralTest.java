package tests;

import java.util.GregorianCalendar;

import gestion.GestionBancoCentral;

public class GestionBancoCentralTest 
{

	public static void main(String[] args) 
	{
		GestionBancoCentral gestion = new GestionBancoCentral();

		/*System.out.println("gestion.BICRegistrado(\"CAIXESBBXXX\"): " + gestion.BICRegistrado("CAIXESBBXXX"));
		
		System.out.println("gestion.datosCuenta(\"ESPCAIXESBBXXX0000000\"): " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		
		System.out.println("gestion.IBANRegistrado(\"ESPBSCHESMMXXX0000000\"): " + gestion.IBANRegistrado("ESPBSCHESMMXXX0000000"));
		
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.obtenerBICporIBAN(\"ESPBSCHESMMXXX0000000\"): " + gestion.obtenerBICporIBAN("ESPBSCHESMMXXX0000000"));
		
		System.out.println("gestion.obtenerNombreBancoComercialPorIBAN(\"ESPCAIXESBBXXX0000000\"): " + gestion.obtenerNombreBancoComercialPorIBAN("ESPCAIXESBBXXX0000000"));
		
		System.out.println("gestion.obtenerNombrePorBIC(\"INGDESMMXXX\"): " + gestion.obtenerNombrePorBIC("INGDESMMXXX"));
		
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.ingresarDinero(\"ESPINGDESMMXXX0000000\", \"Concepto\", 25000, 10, 4, 2019)");
		System.out.println("ANTES -> " + gestion.datosCuenta("ESPINGDESMMXXX0000000"));
		gestion.ingresarDinero("ESPINGDESMMXXX0000000", "Concepto", 25000, new GregorianCalendar());
		System.out.println("DESPUES -> " + gestion.datosCuenta("ESPINGDESMMXXX0000000"));
		
		System.out.println("-------------------------------------------");*/
		
		System.out.println("gestion.modificarSaldoEnFicheroCuentas(\"ESPCAIXESBBXXX0000000\", true, 500)");
		System.out.println("ANTES -> " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		gestion.modificarSaldoEnFicheroCuentas("ESPCAIXESBBXXX0000000", true, 200);
		System.out.println("DESPUES -> " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		//System.out.println(gestion.obtenerSaldoPorIBAN("ESPCAIXESBBXXX0000000"));
		/*
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.realizarMovimiento(\"ESPCAIXESBBXXX0000000\", \"ESPINGDESMMXXX0000000\", \"test_realizarMovimiento\", fechaActual)");
		System.out.println("ANTES:");
		System.out.println("CUENTA 1 -> " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		System.out.println("CUENTA 2 -> " + gestion.datosCuenta("ESPINGDESMMXXX0000000"));
		gestion.realizarMovimiento("ESPCAIXESBBXXX0000000", "ESPINGDESMMXXX0000000", "test_realizarMovimiento", 200, new GregorianCalendar());
		System.out.println("DESPUES:");
		System.out.println("CUENTA 1 -> " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		System.out.println("CUENTA 2 -> " + gestion.datosCuenta("ESPINGDESMMXXX0000000"));
		
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.sacarDinero(\"ESPCAIXESBBXXX0000000\", \"Concepto\", 25000, 10, 4, 2019)");
		System.out.println("ANTES -> " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		gestion.sacarDinero("ESPCAIXESBBXXX0000000", "Concepto", 25000, new GregorianCalendar());
		System.out.println("DESPUES -> " + gestion.datosCuenta("ESPCAIXESBBXXX0000000"));
		
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.buscarMovimientosPorFecha(\"ESPINGDESMMXXX0000000\", 2019)");
		System.out.println(gestion.buscarMovimientosPorFecha("ESPINGDESMMXXX0000000", 2019));
		
		System.out.println();
		
		System.out.println("gestion.buscarMovimientosPorFecha(\"ESPCAIXESBBXXX0000000\", 4, 2019)");
		System.out.println(gestion.buscarMovimientosPorFecha("ESPCAIXESBBXXX0000000", 4, 2019));
		
		System.out.println();
		
		System.out.println("gestion.buscarMovimientosPorFecha(\"ESPCAIXESBBXXX0000000\", 19, 4, 2019)");
		System.out.println(gestion.buscarMovimientosPorFecha("ESPCAIXESBBXXX0000000", 19, 4, 2019));
		
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.isCuentaBorrada(\"ESPCAIXESBBXXX0000000\"): " + gestion.isCuentaBorrada("ESPCAIXESBBXXX12XXXXXX"));
		
		System.out.println("gestion.marcarCuentaComoBorrada(\"ESPINGDESMMXXX0000000\")");
		System.out.println("ANTES -> " + gestion.isCuentaBorrada("ESPINGDESMMXXX0000000"));
		gestion.marcarCuentaComoBorrada("ESPINGDESMMXXX0000000");
		System.out.println("DESPUES -> " + gestion.isCuentaBorrada("ESPINGDESMMXXX0000000"));
		
		System.out.println("-------------------------------------------");
		
		System.out.println("gestion.eliminarCuentasBorradasDefinitivamente()");
		System.out.println("ANTES -> gestion.isCuentaBorrada(\"ESPINGDESMMXXX0000000\") " + gestion.IBANRegistrado("ESPINGDESMMXXX0000000"));
		gestion.eliminarCuentasBorradasDefinitivamente();
		System.out.println("DESPUES -> gestion.isCuentaBorrada(\"ESPINGDESMMXXX0000000\") " + gestion.IBANRegistrado("ESPINGDESMMXXX0000000"));
	*/
	}

}
