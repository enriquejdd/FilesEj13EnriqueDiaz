/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileej13enrique;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Enrique
 */
public class PruebaEj13 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String respuesta;
        String destinoArchivoJSON = "./aplicaciones";

        // ArrayList que contendrá todas las apps para poder comprobar que la que pida el usuario esté.
        ArrayList<String> nombreAplicacionesJSON = listaNombresAplicacionesJSON(destinoArchivoJSON);
        mostrarMenu(destinoArchivoJSON);
        respuesta = teclado.nextLine();

        while (!nombreAplicacionesJSON.contains(respuesta)) {
            System.out.println("Aplicación no encontrada. Elija otra");
            respuesta = teclado.nextLine();
        }

        String rutaApp = destinoArchivoJSON + "/" + respuesta;
        App appJSON = ServicioFicheroJSON.leerAppJSON(rutaApp);

        mostrarAplicacionSeleccionada(appJSON);

        borrarApp(rutaApp);

        System.out.println("");
        System.out.println("Comprobamos que la app se borrara correctamente");
        mostrarAplicaciones(destinoArchivoJSON);

//        System.out.println("Creamos una lista con las Apps que sigan dentro del directorio");
//        ArrayList<App> aplicacionesJSON = listaAplicacionesJSON(destinoArchivoJSON);
//        listaAplicacionesJSON(destinoArchivoJSON);
        System.out.println("Creamos una lista con las apps JSON");
        ArrayList<App> listaApps = ServicioFicheroJSON.leerArchivosJSON("./appsjson/aplicaciones.json");

        Map<String, LocalDate> listaAplicacionesTam = CrearMapTamanio200_500(listaApps);
        System.out.println("");
        System.out.println("Imprimimos el map");
        listaAplicacionesTam.keySet().forEach(key -> {
            System.out.printf("Nombre(clave) %s -- Fecha(valor) %s %n", key, listaAplicacionesTam.get(key));
        });
    }

    // Muestra el menú de las opciones y muestra también todas las aplicaciones llamando al método mostrarAplicaciones
    public static void mostrarMenu(String rutaArchivo) {
        System.out.println("¿Qué aplicación desea leer?");
        System.out.println("Las aplicaciones disponibles son las siguientes:");
        mostrarAplicaciones(rutaArchivo);
        System.out.println("Fin lista aplicaciones");
        System.out.println("");
    }

    // Lee todos los archivos dentro del directorio que le damos por parámetro y muestra sus nombres
    public static void mostrarAplicaciones(String rutaArchivo) {
        File f = new File(rutaArchivo);
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }
    }

    // Lee todos los archivos dentro del directorio que le damos por parámetro y añade los nombres a una lista que nos devuelve
    public static ArrayList<String> listaNombresAplicacionesJSON(String rutaArchivo) {
        ArrayList<String> listaNombres = new ArrayList<>();
        File f = new File(rutaArchivo);
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                listaNombres.add(file2.getName());
            }
        }

        return listaNombres;
    }

    // Mostramos los datos de la aplicacion que le demos por parámetro
    public static void mostrarAplicacionSeleccionada(App app) {
        System.out.println("");
        System.out.println("Mostrando aplicación");
        System.out.println("Nombre aplicacion \t Descripción \t Tamaño \t Fecha Creación");
        System.out.println(app.toString());
    }

    // Le pasamos la ruta del archivo para borrarla del directorio
    public static void borrarApp(String rutaArchivo) {
        System.out.println("");
        System.out.println("Procedemos a borrar la app");

        Path file = Paths.get(rutaArchivo);
        try {
            Files.delete(file);
        } catch (IOException e) {
            System.out.println("Problema borrando el elemento " + rutaArchivo);
        }
    }

    // Usando Streams, guarda en una estructura Map el nombre de la app (como clave) y la fecha de creación (como valor) de aquellas apps cuyo tamaño esté 
    //entre 200 y 500 kb.
    public static Map CrearMapTamanio200_500(ArrayList<App> app) {
        Map<String, LocalDate> listaTam = app.stream().filter(p -> p.getTamanioKB() > 200 && p.getTamanioKB() < 500)
                .sorted((v1, v2) -> v1.getNombre().compareTo(v2.getNombre())).collect(Collectors.toMap(p -> p.getNombre(), p -> p.getFecCreacion()));
        return listaTam;
    }

// NO FUNCIONAN, FALTA VER COMO REALIZARLO
    // Lee todos los archivos dentro del directorio que le damos por parámetro y los añade a una lista que nos devuelve
    public static void listaAplicacionesJSON(String rutaArchivo) {
        ArrayList<App> listaAPPs = new ArrayList<>();

        int contador = 1;

        String[] tokens;
        String linea;

        File f = new File(rutaArchivo);
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                try (Scanner datosFichero = new Scanner(new FileReader(rutaArchivo + "/" + file2.getName()))) {

                    while (datosFichero.hasNextLine()) {
                        linea = datosFichero.nextLine();
                        tokens = linea.split(",");
                        System.out.println(tokens[0]);
                        System.out.println("");

//                        App app = new App();
//                        app.setCodigo(obtenerCodigo(file2.getName()));
//                        app.setNombre(file2.getName());
//                        
//                        switch (contador) {
//                            case 2:
//                                app.setDescripcion(obtenerDatos(linea));
//                                contador++;
//                                break;
//                            case 3:
//                                app.setTamanioKB(Double.valueOf(obtenerDatos(linea)));
//                                contador++;
//                                break;
//                            case 4:
//                                app.setFecCreacion(obtenerFecha(linea));
//                                contador++;
//                                break;
//                            default:
//                                contador++;
//                                break;
//                        }
                    }
                    contador = 0;

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

//        return listaAPPs;
    }

    // Le pasamos el nombre de una aplicacion y obtiene el numero que hay en el.
    public static int obtenerCodigo(String nombreArchivo) {
        int codigo = 0;
        String linea = nombreArchivo;
        String[] tokens = linea.split("");
        if (nombreArchivo.length() == 5) {
            codigo = Integer.valueOf(tokens[4]);
            return codigo;
        } else {
            String cod = tokens[4] + tokens[5];
            codigo = Integer.valueOf(cod);
            return codigo;
        }
    }

    // Separa el token en 2 partes para separar el nombre del dato y el dato que necesitamos
    public static String obtenerDatos(String datos) {

        String linea = datos;
        String[] tokens = linea.split(":");

        System.out.println(tokens[0]);
        System.out.println(tokens[1]);

        String datosAñadir = tokens[1];

        return datosAñadir;
    }

    public static LocalDate obtenerFecha(String datos) {

        String linea = datos;
        String[] tokens = linea.split(":");

        String partesTokens = tokens[1];
        String[] partes = partesTokens.split("/");

        LocalDate fecha = LocalDate.of(Integer.valueOf(partes[0]), Integer.valueOf(partes[1]), Integer.valueOf(partes[2]));

        return fecha;
    }

    // Metodo el cual quita las comillas de los string
    public static String quitarComillas(String a) {
        String sinComillas = "";

        String[] palabra;
        palabra = a.split("");

        for (int i = 1; i < palabra.length - 1; i++) {
            sinComillas += palabra[i];
        }

        return sinComillas;
    }

}
