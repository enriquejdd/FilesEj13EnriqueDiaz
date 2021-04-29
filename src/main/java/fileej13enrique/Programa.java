/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileej13enrique;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Enrique
 */
public class Programa {

    public static void main(String[] args) {
        int numeroApps = 50;

        System.out.println("Creamos las listas de aplicaciones");
        System.out.println("");
        ArrayList<App> listaApps = new ArrayList<>();
        for (int i = 0; i < numeroApps; i++) {
            listaApps.add(App.crearAppAleatoria());
        }
        System.out.println("Enseñamos los valores de la lista");
        System.out.println("");
        listaApps.forEach(System.out::println);

        System.out.println("Creamos los directorios");
        System.out.println("");
        Path appstsv = Paths.get("./appstsv");
        Path appsxml = Paths.get("./appsxml");
        Path appsjson = Paths.get("./appsjson");
        Path c = Paths.get("./copias");
        Path aplicaciones = Paths.get("./aplicaciones");
        try {
            Files.createDirectory(appstsv);
            Files.createDirectory(appsxml);
            Files.createDirectory(appsjson);
            Files.createDirectory(c);
            Files.createDirectory(aplicaciones);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio.");
            System.out.println(e.toString());
        }

        System.out.println("Creamos el archivos TSV");
        System.out.println("");
        String destinoArchivoTSV = "./appstsv/aplicaciones.tsv";
        ServicioFicheroTSV.crearArchivosTSV(listaApps, destinoArchivoTSV);

        System.out.println("Creamos el archivos XML");
        System.out.println("");
        String destinoArchivoXML = "./appsxml/aplicaciones.xml";
        try {
            ServicioFicheroXML.crearArchivoXMLMarshall(listaApps, destinoArchivoXML);
        } catch (JAXBException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Creamos el archivos JSON");
        System.out.println("");
        String destinoArchivoJSON = "./appsjson/aplicaciones.json";
        try {
            ServicioFicheroJSON.crearArchivosJSON(listaApps, destinoArchivoJSON);
        } catch (IOException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Creamos un archivos JSON por cada aplicacion");
        System.out.println("");
        String rutaAppsJSON = "./aplicaciones";

        for (int i = 0; i < listaApps.size(); i++) {
            try {
                ServicioFicheroJSON.crearListaJSONporApp(listaApps.get(i), rutaAppsJSON);
            } catch (IOException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Leemos el fichero XML");
        System.out.println("");
        try {
            ServicioFicheroXML.leerFicherosXML(destinoArchivoXML);
        } catch (JAXBException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
