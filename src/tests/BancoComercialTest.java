package tests;

import clasesBasicas.BancoComercial;

public class BancoComercialTest {

	public static void main(String[] args) 
	{
		BancoComercial porDefecto = new BancoComercial();
		BancoComercial conParametros = new BancoComercial("ESP", "BNCPRBMMXXX", "Banco Prueba");
		BancoComercial deCopia = new BancoComercial(conParametros);
		
		System.out.println("conParametros.getBIC(): " + conParametros.getBIC());
		System.out.println("deCopia.getNombre(): " + deCopia.getNombre());
		
		System.out.println("--------------------------------------");
		
		System.out.println("porDefecto.setID_BancoCentral(\"ITA\")");
		System.out.println("ANTES -> " + porDefecto.getID_BancoCentral());
		porDefecto.setID_BancoCentral("ITA");
		System.out.println("DESPUES -> " + porDefecto.getID_BancoCentral());
		
		System.out.println("--------------------------------------");
		
		System.out.println("deCopia.setNombre(\"Banco Prueba 2.0\")");
		System.out.println("ANTES -> " + deCopia.getNombre());
		deCopia.setNombre("Banco Prueba 2.0");
		System.out.println("DESPUES -> " + deCopia.getNombre());
		
		System.out.println("--------------------------------------");
		
		System.out.println("conParametros.toString(): " + conParametros.toString());
	}

}
