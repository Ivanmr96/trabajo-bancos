package tests;

import clasesBasicas.BancoCentralImpl;
//import clasesBasicas.CuentaImpl;

public class BancoCentralTest {

	public static void main(String[] args) 
	{
		BancoCentralImpl porDefecto = new BancoCentralImpl();
		BancoCentralImpl conParametros = new BancoCentralImpl("BANCOCENTRAL",1.5, 10);
		BancoCentralImpl deCopia = new BancoCentralImpl(conParametros);
		
		System.out.println("porDefecto.getTasaInteres(): " + porDefecto.getTasaInteres());
		System.out.println("conParametros.getCoeficienteCaja(): " + conParametros.getCoeficienteCaja());
		
		System.out.println("deCopia.toString(): " + deCopia.toString());
		
		System.out.println("-----------------------------------------------------");
		
		System.out.println("porDefecto.setTasaInteres(2)");
		System.out.println("ANTES -> " + porDefecto.getTasaInteres());
		porDefecto.setTasaInteres(2);
		System.out.println("DESPUES -> " + porDefecto.getTasaInteres());
		
		System.out.println("-----------------------------------------------------");
		
		System.out.println("porDefecto.setCoeficienteCaja(8)");
		System.out.println("ANTES -> " + porDefecto.getCoeficienteCaja());
		porDefecto.setCoeficienteCaja(8);
		System.out.println("DESPUES -> " + porDefecto.getCoeficienteCaja());
		
		System.out.println("-----------------------------------------------------");
		
		System.out.println("copia = porDefecto.clone()");
		
		BancoCentralImpl copia = porDefecto.clone();
		
		System.out.println("copia.toString(): " + copia.toString());
	}

}
