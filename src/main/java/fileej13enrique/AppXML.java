/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileej13enrique;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Enrique
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class AppXML {

    // XmLElementWrapper define un contenedor para la lista 
    // de muebles
    @XmlElementWrapper(name = "AppXML")

    // XmlElement establece el nombre de los elementos
    // Cada elemento de la lista llevará esta etiqueta en el fichero xml
    @XmlElement(name = "Apps")
    private ArrayList<App> listaAppXml;

    public ArrayList<App> getListaMuebles() {
        return listaAppXml;
    }

    public void setLista(ArrayList<App> lista) {
        this.listaAppXml = lista;
    }

}
