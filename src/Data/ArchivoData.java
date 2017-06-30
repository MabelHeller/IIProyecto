/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.NodoArbol;
import Domain.Arbol;
import GUI.ArbolGraphics;
import GUI.Ventana;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Heller
 */
public class ArchivoData {

    public ArrayList<String> listaArchivo = new ArrayList<String>();
    public ArrayList<String> listaArbol = new ArrayList<String>();
    public String ruta;
    public String path;
    public ArbolGraphics arbolG;
    public String informacion;
    public Arbol arbol = new Arbol();
    public String c;
        
    public ArrayList<String> getListaArchivo() {
        return listaArchivo;
    }

    public void setListaArchivo(ArrayList<String> listaArchivo) {
        this.listaArchivo = listaArchivo;
    }

    public ArrayList<String> getListaArbol() {
        return listaArbol;
    }

    public void setListaArbol(ArrayList<String> listaArbol) {
        this.listaArbol = listaArbol;
    }
    
    public String fileReader() {
        Ventana v = new Ventana();
        informacion = "";
        //path = v.AbrirArchivo();
        FileReader fr = null;
        try {
            File archivo = v.AbrirArchivo();
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                this.listaArchivo.add(linea + "\n");
                informacion += linea + " ";
            }
            codificacion();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return informacion;
    }
       
    public void guardarArbol(NodoArbol nodo) throws FileNotFoundException {        
        File file = new File(this.path);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(nodo.getDato() + " " + nodo.getPosicion());
                
    }
    
    public void guardarNodoArbol(NodoArbol nodo, String rute) throws FileNotFoundException {
        if (nodo != null) {
            guardarArbol(nodo);
            guardarNodoArbol(nodo.izquierdo, rute);
            guardarNodoArbol(nodo.derecho, rute);
        }
    }   

    public ArrayList<String> codificacion() {
        Ventana v = new Ventana();
        this.c = " ";
        int p = 0;
        NodoArbol nodo = new NodoArbol();
        for (int i = 0; i < this.listaArchivo.size(); i++) {
            String linea = this.listaArchivo.get(i);
            String palabra[] = linea.split(" ");
            for (int j = 0; j < palabra.length; j++) {
                nodo.setDato(palabra[j].codePointAt(0) + "");
                nodo.setPosicion(j + "");
                nodo.setPalabra(palabra[j]);
                p = palabra[j].codePointAt(0);
                this.listaArbol.add(p + " ");
                this.c += p + " ";
            }
        }
        System.out.print(informacion);
        v.llenarArbol(listaArbol);
        //v.crearArbol(this.c);
        //return this.c;
        return this.listaArbol;
    }
}
