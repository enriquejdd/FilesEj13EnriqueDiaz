/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileej13enrique;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Enrique
 */
public class PruebaEj12 {

    public static void main(String[] args) {
        String destinoArchivoXML = "./appsxml/aplicaciones.xml";

        System.out.println("Leemos el fichero XML");
        System.out.println("");
        try {
            ServicioFicheroXML.leerFicherosXML(destinoArchivoXML);
        } catch (JAXBException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
