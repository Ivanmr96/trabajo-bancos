package tests;

import clasesBasicas.PrestamoImpl;

public class PrestamoImplTest {

	public static void main(String[] args) 
	{
		PrestamoImpl porDefecto = new PrestamoImpl();
		PrestamoImpl conParametros = new PrestamoImpl(5, 25000, 120);
		PrestamoImpl deCopia = new PrestamoImpl(conParametros);
		
		System.out.println("porDefecto.getID(): " + porDefecto.getID());
		System.out.println("conParametros.getCantidad(): " + conParametros.getCantidad());
		System.out.println("deCopia.getTiempoMeses(): " + deCopia.getTiempoMeses());
		
		System.out.println("conParametros.getMensualidad(): " + conParametros.getMensualidad());
		
		System.out.println("deCopia.hashCode(): " + deCopia.hashCode());
		
		System.out.println("porDefecto.toString(): " + porDefecto.toString());
		
		System.out.println("-----------------------------------------");
		
		System.out.println("copia = conParametros.clone()");
		PrestamoImpl copia = conParametros.clone();
		System.out.println("copia.toString(): " + copia.toString());
		
	}

}
