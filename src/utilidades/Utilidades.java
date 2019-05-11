package utilidades;

import clasesBasicas.TransferenciaImpl;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Utilidades {


    /* INTERFAZ
     * Comentario: Escribe un registro nuevo en un fichero determinado
     * Prototipo: public boolean escribirRegistroEnFichero(String registro, String rutaFichero)
     * Entrada: Un String con el registro a escribir, y otro String con la ruta del fichero donde se escribira
     * Precondiciones: No hay
     * Salida: Un boolean indicando si se ha escrito correctamente o no.
     * Postcondiciones: Asociado al nombre devuelve:
     * 					-> True si se ha escrito correctamente el registro en el fichero correspondiente
     * 					-> False si no se ha escrito correctamente
     * 					* Puede lanzar IOException si hay algun error al escribir
     */
    /**
     * Escribe un registro nuevo en un fichero determinado
     *
     * @param registro El registro a escribir.
     * @param rutaFichero La ruta del fichero donde se escribir� el registro.
     * @return Devuelve true si se ha escrito correctamente el registro en el fichero correspondiente, o false si no se ha escrito correctamente
     */
    public boolean escribirRegistroEnFichero(String registro, String rutaFichero) {
        boolean escrito = false;

        File fichero = new File(rutaFichero);
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(fichero, true);
            bw = new BufferedWriter(fw);

            bw.write(registro);
            escrito = true;
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return escrito;
    }


    /*
     *Signatura: public void imprimirMovimientos(TransferenciaImpl movimientos)
     * Comentario: imprime un movimiento. Método sobrecargado
     * */

    /**
     * Imprime un movimiento. Método sobrecargado
     * @param element Objeto de tipo TransferenciaImpl del cual se imprimiran sus datos
     */
    public void imprimirMovimientos(TransferenciaImpl element){
    	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0.00");
    	String cantidad;
    	
        String tipo = " ";
            System.out.println("-------------------------------");
            System.out.println("Fecha: " + element.toStringFecha());
            System.out.println("Tipo: " + ((element.isIngresoOrRetirada())?"INGRESO":"RETIRADA"));
            cantidad = df.format(element.getCantidad());
            System.out.println("Cantidad: " + cantidad + " �");
            System.out.println("Concepto: " + element.getConcepto());

    }

    /*
    *Signatura: public void imprimirMovimientos(List<String> movimientos)
    * Comentario: imprime una lista de Strings en pantalla
    * */

    /**
     * Imprime una lista de transferencias en pantalla
     * @param movimientos Lista de Transferencias que se mostraran en pantalla
     */
    public void imprimirMovimientos(List<TransferenciaImpl> movimientos) {
    	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0.00");
    	//df.setGroupingSize(3);
    	
    	String cantidad;
        String tipo = " ";
        for(TransferenciaImpl element:movimientos)
        {
            System.out.println("-------------------------------");
            System.out.println("Fecha: " + element.toStringFecha());
            System.out.println("Tipo: " + ((element.isIngresoOrRetirada())?"INGRESO":"RETIRADA"));
            
            //cantidad = String.valueOf(element.getCantidad());
            cantidad = df.format(element.getCantidad());
            
            System.out.println("Cantidad: " + cantidad + " �");
            System.out.println("Concepto: " + element.getConcepto());
        }
    }

    /**
     * Imprime datos de una cuenta en pantalla bien formateados
     * @param datosCuenta Datos a imprimir
     */
    public void imprimirDatosCuenta(String datosCuenta)
    {
    	String[] campos = datosCuenta.split(",");
    	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0.00");
    	//df.setGroupingSize(3);
    	
    	System.out.println("Numero de cuenta: " + campos[0]);
    	campos[1] = df.format(Double.parseDouble(campos[1]));
    	System.out.println("Saldo: " + campos[1] + " �");
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

    /**
     * Ordena el fichero de una ruta en base a la lexicografía de un campo de un fichero
     * @param ruta ruta del fichero que se desea ordenar
     * @param campoClave posicion del campo por el que se desea ordenar el fichero
     */
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

    /**
     * Borra un fichero dada su ruta
     * @param fichero ruta del fichero a borrar
     * @return Boolean que es true si se ha borrado correctamente y false si no
     */
    public boolean borrarFichero(String fichero)
    {
    	boolean borrado = false;
    	File file = new File(fichero);
    	
    	borrado = file.delete();
    	
    	return borrado;
    }
    
    /*
    * INTERFAZ
    * Comentario: Método que sustituye a renameTo. Dadas dos rutas, una de origen y otra de destino, renombra la primera a la segunda.
    * Signatura: public boolean renombrarFichero(String fichero, String nuevoNombre)
    * Precondiciones: Los ficheros deben ser de texto
    * Entradas: String fichero, que es el fichero de origen, String nuevoNombre, que es la nueva ruta
    * Salidas: boolean
    * Postcondiciones: asociado al nombre se devuelve un boolean que será true si se ha renombrado con éxito y false si no. Si alguna de las rutas
    *   no existen, no se renombrará el fichero.
    * */

    /**
     * Método que sustituye a renameTo. Dadas dos rutas, una de origen y otra de destino, renombra la primera a la segunda.
     * @param fichero Es el fichero de origen
     * @param nuevoNombre Es la nueva ruta o fichero de destino
     * @return asociado al nombre se devuelve un boolean que será true si se ha renombrado con éxito y false si no. Si alguna de las rutas no existen, no se renombrará el fichero.
     */
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


    /*
     * INTERFAZ
     * Comentario: Método que sustituye a renameTo. Dadas dos rutas, una de origen y otra de destino, renombra la primera a la segunda.
     * Signatura: public boolean renombrarFicheroBinario(String fichero, String nuevoNombre, Object obj)
     * Precondiciones: Los ficheros deben ser binarios
     * Entradas: String fichero, que es el fichero de origen, String nuevoNombre, que es la nueva ruta, y Object obj, para saber el tipo de objeto que
     *          se encuentra dentro del fichero de origen
     * Salidas: boolean
     * Postcondiciones: asociado al nombre se devuelve un boolean que será true si se ha renombrado con éxito y false si no. Si alguna de las rutas
     *   no existen, no se renombrará el fichero.
     * */
    /**
     * Método que sustituye a renameTo. Dadas dos rutas, una de origen y otra de destino, renombra la primera a la segunda. Renombra ficheros binarios.
     * @param fichero Es el fichero de origen
     * @param nuevoNombre Es la nueva ruta o fichero de destino
     * @param obj Objeto del tipo que habra en el fichero de origen.
     * @return asociado al nombre se devuelve un boolean que será true si se ha renombrado con éxito y false si no. Si alguna de las rutas no existen, no se renombrará el fichero.
     */
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

                //leer.close();

            } catch (EOFException e){
            	try
            	{
            		leer.close();
            	}
            	catch(IOException err)
            	{
            		err.printStackTrace();
            	}

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