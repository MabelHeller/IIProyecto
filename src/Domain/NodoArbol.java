/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Heller
 */
public class NodoArbol {
    public int balance;
    private String palabra;
    private String dato;
    private String posicion;
    public NodoArbol izquierdo;
    public NodoArbol derecho;

    public NodoArbol(String dato) {
        this.balance = 0;
        this.izquierdo = null;
        this.derecho = null;
        this.dato = dato;
        this.posicion = "";
    }//constructor

    public NodoArbol() {
        this.balance = 0;
        this.izquierdo = null;
        this.derecho = null;
        this.dato = dato;
        this.posicion = "";
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public NodoArbol getIzquierda() {
        return izquierdo;
    }

    public void setIzquierda(NodoArbol izquierda) {
        this.izquierdo = izquierda;
    }

    public NodoArbol getDerecha() {
        return derecho;
    }

    public void setDerecha(NodoArbol derecha) {
        this.derecho = derecha;
    }

    @Override
    public String toString() {
        return "NodoArbol{" + "palabra=" + palabra + ", dato=" + dato + ", posicion=" + posicion + '}';
    }
    
    

}
