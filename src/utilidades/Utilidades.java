package utilidades;

import clasesBasicas.TransferenciaImpl;

import java.io.*;
import java.util.*;

public class Utilidades {

    /*
     *Signatura: public void imprimirMovimientos(TransferenciaImpl movimientos)
     * Comentario: imprime un movimiento. Método sobrecargado
     * */
    public void imprimirMovimientos(TransferenciaImpl element){

        String tipo = " ";
            System.out.println("-------------------------------");
            System.out.println("Fecha: " + element.toStringFecha());
            System.out.println("Tipo: " + ((element.isIngresoOrRetirada())?"INGRESO":"RETIRADA"));
            System.out.println("Cantidad: " + element.getCantidad());
            System.out.println("Concepto: " + element.getConcepto());

    }

    /*
    *Signatura: public void imprimirMovimientos(List<String> movimientos)
    * Comentario: imprime una lista de Strings en pantalla
    * */
    public void imprimirMovimientos(List<TransferenciaImpl> movimientos){

        String tipo = " ";
        for(TransferenciaImpl element:movimientos)
        {
            System.out.println("-------------------------------");
            System.out.println("Fecha: " + element.toStringFecha());
            System.out.println("Tipo: " + ((element.isIngresoOrRetirada())?"INGRESO":"RETIRADA"));
            System.out.println("Cantidad: " + element.getCantidad());
            System.out.println("Concepto: " + element.getConcepto());
        }
    }
    
    public void imprimirDatosCuenta(String datosCuenta)
    {
    	String[] campos = datosCuenta.split(",");
    	
    	System.out.println("Numero de cuenta: " + campos[0]);
    	System.out.println("Saldo: " + campos[1]);
    }

    /*
    * INTERFAZ
    * Signatura: public void ordenarFicheroPorClave(String ruta, int campoClave)
    * Comentario: ordena el fichero de una ruta en base a la lexicografía de un campo de un fichero
    * Precondiciones:
    * Entradas: Se pasará como String la ruta del fichero a ordenar y como entero el campo clave que se evaluará para ordenar
    * Salidas:
    * Postcondiciones: El fichero quedará ordenado en base a ese campo lexicográficamente.
    * */
    public void ordenarFicheroPorClave(String ruta, int campoClave){
        File ficheroAOrdenar = new File(ruta);
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        List<String> registros = new ArrayList<String>();    //arraylist - considerar cambiar a array
        String registro = " ";
        String aux=" "; //para el bubblesort de mÃ¡s abajo

        try{
            fr = new FileReader(ficheroAOrdenar);
            br = new BufferedReader(fr);
            while(br.ready()){
                registro = br.readLine();
                registros.add(registro);
            }
            br.close();
            fr.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        //ordenamiento burbuja

        for (int i = 0; i < registros.size()-1;i++){

            for (int j = registros.size()-1; j>i; j--){
                if (registros.get(j).compareTo(registros.get(j-1)) < 0) {
                    //se produce el intercambio de elementos
                    aux = registros.get(j);
                    registros.set(j,registros.get(j-1));
                    registros.set(j-1, aux);
                }
            }
        }
        try{
            fw = new FileWriter(ficheroAOrdenar);
            bw = new BufferedWriter(fw);

            for(int i = 0; i < registros.size(); i ++){
                bw.write(registros.get(i));
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch ( IOException e ){
            e.printStackTrace();
        }

    }
    
    public boolean borrarFichero(String fichero)
    {
    	boolean borrado = false;
    	File file = new File(fichero);
    	
    	borrado = file.delete();
    	
    	return borrado;
    }
    
    //Este metodo sustituye File.renameTo() (pero funciona)
    public boolean renombrarFichero(String fichero, String nuevoNombre)
    {
    	boolean renombrado = false;
    	File file = new File(fichero);
    	File newName = new File (nuevoNombre);
    	
    	FileReader fr = null;
    	FileWriter fw = null;
    	BufferedReader br = null;
    	BufferedWriter bw = null;
    	List<String> registros = new ArrayList<String>();
    	String registro = null;
    	
    	//renombrado = file.renameTo(newName);
    	if(file.exists() && newName.exists())
    	{
    		
	    	try
	    	{
	    		fr = new FileReader(file);
	    		br = new BufferedReader(fr);
	    		
	    		System.out.println("ready(leer)? " + br.ready());
	    		while(br.ready())
	    		{
	    			registro = br.readLine();
	    			
	    			registros.add(registro);
	    		}
	    		
	    		br.close();
	    		fr.close();
	    		
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	borrarFichero(fichero);
	    	//borrarFichero(nuevoNombre);
	    	
	    	try 
	    	{
				newName.createNewFile();
				
				fw = new FileWriter(newName);
				bw = new BufferedWriter(fw);
				
				System.out.println("longitud: " + registros.size());
				for(int i = 0 ; i < registros.size(); i++)
				{
					bw.write(registros.get(i));
					bw.newLine();
				}
				
				bw.close();
				fw.close();
				
				renombrado = true;
				
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			}
    	}
    	
    	return renombrado;
    }


    //Este metodo sustituye File.renameTo() (pero funciona) -- VERSION PARA ARCHIVOS BINARIOS
    public boolean renombrarFicheroBinario(String fichero, String nuevoNombre, Object obj)
    {
        boolean renombrado = false;
        File file = new File(fichero);
        File newName = new File (nuevoNombre);
        ObjectInputStream leer = null;
        ObjectOutputStream escribir = null;
        MyObjectOutputStream myoos = null;
        List<Object> registros = new ArrayList<Object>();
        Object registro = null;
        boolean cont = true;


        if(file.exists() && newName.exists())
        {

            try
            {
                leer = new ObjectInputStream(new FileInputStream(file));

                while(cont)
                {
                    registro = leer.readObject();

                    registros.add(registro);
                }

                leer.close();

            } catch (EOFException e){

            }catch (ClassNotFoundException e){

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }


            borrarFichero(fichero);

            try
            {
                newName.createNewFile();
                escribir = new ObjectOutputStream(new FileOutputStream(newName));
                for(int i = 0; i < registros.size(); i ++){
                    escribir.writeObject(registros.get(i));
                }
                escribir.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


        }

        return renombrado;
    }

}
