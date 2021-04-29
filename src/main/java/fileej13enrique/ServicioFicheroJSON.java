/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileej13enrique;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrique
 */
public class ServicioFicheroJSON {

    // Crea un archivo JSON en la ubicacion de la ruta para el archivo con los datos del array
    public static void crearArchivosJSON(ArrayList<App> arrayListApps, String rutaYNombreArchivo) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);
        // Escribe en un fichero JSON el catálogo de muebles
        mapeador.writeValue(new File(rutaYNombreArchivo), arrayListApps);
    }

    // Crea tantos ficheros JSON como el tamaño del array que se le pase. Usaran la ruta del archivo pero el nombre será el de la app.
    public static void crearListaJSONporApp(App app, String rutaYNombreArchivo) throws IOException {

        String rutaArchivo = rutaYNombreArchivo + "/" + app.getNombre() + ".json";
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            //llamamos al método writeValue que se le pasa como parametros
            //un constructor new File con el idFichero que pasamos anteriormente
            //y la lista de donde sacará los objetos en cuestión
            mapeador.writeValue(new File(rutaArchivo), app);
        } catch (IOException ex) {
            Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }

    }
}
