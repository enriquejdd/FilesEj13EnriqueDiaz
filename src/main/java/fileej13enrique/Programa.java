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
        String destinoArchivoTSV = "./appstsv/aplicaciones.tsv";
        String destinoArchivoXML = "./appsxml/aplicaciones.xml";
        String destinoArchivoJSON = "./appsjson/aplicaciones.json";
        String rutaAppsJSON = "./aplicaciones";

        ArrayList<App> listaApps = crearListaApps(50);
        enseñarListaApps(listaApps);

        crearDirectorios();

        crearArchivoTSL(listaApps, destinoArchivoTSV);
        crearArchivoXML(listaApps, destinoArchivoXML);
        crearArchivoJSON(listaApps, destinoArchivoJSON);

        crearArchivoPorAPP(listaApps, rutaAppsJSON);
    }

    // Crea apps aleatorias hasta que llege al numero dado por parametro
    public static ArrayList<App> crearListaApps(int cantidad) {
        int numeroApps = cantidad;
        System.out.println("Creamos las listas de aplicaciones");
        System.out.println("");
        ArrayList<App> listaApps = new ArrayList<>();
        for (int i = 0; i < numeroApps; i++) {
            listaApps.add(App.crearAppAleatoria());
        }

        return listaApps;
    }

    // Enseñamos la lista de aplicaciones que le pasemos por parametro
    public static void enseñarListaApps(ArrayList<App> apps) {
        System.out.println("Enseñamos los valores de la lista");
        System.out.println("");
        apps.forEach(System.out::println);
    }

    // Crea los directorios que nos hacen falta
    public static void crearDirectorios() {
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
    }

    // Crea un archivo TSL con todas las apps del arrayList en la ruta dada
    public static void crearArchivoTSL(ArrayList<App> apps, String ruta) {
        System.out.println("Creamos el archivos TSV");
        System.out.println("");
//        String destinoArchivoTSV = "./appstsv/aplicaciones.tsv";
        ServicioFicheroTSV.crearArchivosTSV(apps, ruta);
    }

    // Crea un archivo XML con todas las apps del arrayList en la ruta dada
    public static void crearArchivoXML(ArrayList<App> apps, String ruta) {
        System.out.println("Creamos el archivos XML");
        System.out.println("");
//        String destinoArchivoXML = "./appsxml/aplicaciones.xml";
        try {
            ServicioFicheroXML.crearArchivoXMLMarshall(apps, ruta);
        } catch (JAXBException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Crea un archivo JSON con todas las apps del arrayList en la ruta dada
    public static void crearArchivoJSON(ArrayList<App> apps, String ruta) {
        System.out.println("Creamos el archivos JSON");
        System.out.println("");
//        String destinoArchivoJSON = "./appsjson/aplicaciones.json";
        try {
            ServicioFicheroJSON.crearArchivosJSON(apps, ruta);
        } catch (IOException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Crea un archivo por cada app dentro de la lista en el siguiente directorio
    public static void crearArchivoPorAPP(ArrayList<App> apps, String ruta){
        System.out.println("Creamos un archivos JSON por cada aplicacion");
        System.out.println("");
//        String rutaAppsJSON = "./aplicaciones";

        for (int i = 0; i < apps.size(); i++) {
            ServicioFicheroJSON.crearListaJSONporApp(apps.get(i), ruta);
        }
    }
}
