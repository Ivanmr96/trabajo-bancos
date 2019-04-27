package tests;

import clasesBasicas.ClienteImpl;

public class ClienteImplTest {

	public static void main(String[] args) 
	{
		ClienteImpl porDefecto = new ClienteImpl();
		ClienteImpl conParametros = new ClienteImpl("CAIXESBBXXX", "30317099E", 891.44);
		ClienteImpl deCopia = new ClienteImpl(conParametros);
		
		System.out.println("porDefecto.getDNI(): " + porDefecto.getDNI());
		System.out.println("conParametros.getBIC_Banco(): " + conParametros.getBIC_Banco());
		System.out.println("deCopia.getIngresoMensual(): " + deCopia.getIngresoMensual());
		
		System.out.println("-----------------------------------------");

		System.out.println("porDefecto.setBIC_Banco(\"INGDESMMXXX\")");
		System.out.println("ANTES -> " + porDefecto.getBIC_Banco());
		porDefecto.setBIC_Banco("INGDESMMXXX");
		System.out.println("DESPUES -> " + porDefecto.getBIC_Banco());
		
		System.out.println("-----------------------------------------");
		
		System.out.println("deCopia.setIngresoMensual(2104.21)");
		System.out.println("ANTES -> " + deCopia.getIngresoMensual());
		deCopia.setIngresoMensual(2104.21);
		System.out.println("DESPUES -> " + deCopia.getIngresoMensual());
		
		System.out.println("-----------------------------------------");
		
		System.out.println("conParametros.toString(): " + conParametros.toString());
	}

}
