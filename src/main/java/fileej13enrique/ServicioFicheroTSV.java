/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileej13enrique;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class ServicioFicheroTSV {

    // Crea un archivo TSV en la ubicacion de la ruta para el archivo con los datos del array
    public static void crearArchivosTSV(ArrayList<App> arrayListApps, String rutaYNombreArchivo) {

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(rutaYNombreArchivo))) {
            String respuesta = "";
            for (int i = 0; i < arrayListApps.size(); i++) {
                respuesta = arrayListApps.get(i).toString();
                flujo.write(respuesta);
                flujo.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
