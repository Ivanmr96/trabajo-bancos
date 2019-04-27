package tests;

import clasesBasicas.CuentaImpl;

public class CuentaImplTest {

	public static void main(String[] args) 
	{
		CuentaImpl porDefecto = new CuentaImpl();
		CuentaImpl conParametro = new CuentaImpl("ESPCAIXESBBXXX12XXXXXXX");
		CuentaImpl conParametros = new CuentaImpl("ESPBSCHESMMXXX13XXXXXXX", 13505.23);
		CuentaImpl deCopia = new CuentaImpl(conParametros);
		
		System.out.println("porDefecto.getCodigoBancoCentral(): " + porDefecto.getCodigoBancoCentral());
		System.out.println("conParametro.getCodigoBancoComercial(): " + conParametro.getCodigoBancoComercial());
		System.out.println("conParametros.getIBAN(): " + conParametros.getIBAN());
		System.out.println("deCopia.getCantidadDinero(): " + deCopia.getCantidadDinero());
		
		System.out.println("-------------------------------------------");
		
		System.out.println("conParametro.setCantidadDinero(1000)");
		System.out.println("ANTES -> " + conParametro.getCantidadDinero());
		conParametro.setCantidadDinero(1000);
		System.out.println("DESPUES -> " + conParametro.getCantidadDinero());
		
		System.out.println("-------------------------------------------");
		
		System.out.println("deCopia.toString(): " + deCopia.toString());
	}

}
