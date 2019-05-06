package utilidades;

import creacionFicheros.crearFicheros;

import java.io.File;

public class Reset {

    public static void main(String[] args) {
        File carpetaFiles = new File("./Files");
        crearFicheros reset = new crearFicheros();

        if(carpetaFiles.isDirectory() && carpetaFiles.exists()){
            carpetaFiles.delete();

        }
        reset.main(null);
    }

}
