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
public class Arbol {

    public NodoArbol root;
    private int position;

    public Arbol() {
        this.root = null;
        this.position = 0;
    }

    public int Balance(NodoArbol nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.balance;
        }
    }
    public NodoArbol RotacionIzquierda(NodoArbol nodo) {
        NodoArbol aux = nodo.derecho;
        nodo.derecho = aux.izquierdo;
        aux.izquierdo = nodo;
        nodo.balance = Math.max(Balance(nodo.izquierdo), Balance(nodo.derecho)) + 1;
        aux.balance = Math.max(Balance(aux.izquierdo), Balance(aux.derecho));

        return aux;
    }// leftRotation

    //max=Devuelve el valor del máximo de dos valores numéricos.
    public NodoArbol RotacionDerecha(NodoArbol nodo) {
        NodoArbol aux = nodo.izquierdo;
        nodo.izquierdo = aux.derecho;
        aux.derecho = nodo;

        nodo.balance = Math.max(Balance(nodo.izquierdo), Balance(nodo.derecho));
        aux.balance = Math.max(Balance(aux.izquierdo), Balance(aux.derecho));

        return aux;
    }//rightRotation

    public NodoArbol dobleRotacionDerecha(NodoArbol nodo) {
        NodoArbol temp;
        nodo.izquierdo = RotacionIzquierda(nodo.izquierdo);
        temp = RotacionDerecha(nodo);

        return temp;

    }// left_right_rotation

    public NodoArbol dobleRotacionIzquierda(NodoArbol nodo) {
        NodoArbol temp;
        nodo.derecho = RotacionDerecha(nodo.derecho);
        temp = RotacionIzquierda(nodo);
        return temp;
    }
//Número positivo: la cadena 1 es mayor que la cadena 2.
// 0 las cadenas son iguales.
//Número negativo: la cadena 1 es menor que la cadena 2.
    public NodoArbol insertarArbol(NodoArbol nodo1, NodoArbol nodo2) {
        NodoArbol nodoNuevo = nodo2;
        if (nodo1.getDato().compareTo(nodo2.getDato()) < 0) {
            if (nodo2.izquierdo == null) {
                nodo2.izquierdo = nodo1;
                nodo2.izquierdo.setPosicion(nodo2.izquierdo.getPosicion() + this.position + " ");
                this.position++;
            } else {
                nodo2.izquierdo = insertarArbol(nodo1, nodo2.izquierdo);
                if ((Balance(nodo2.izquierdo) - Balance(nodo2.derecho) == 2)) {
                    if (nodo1.getDato().compareTo(nodo2.izquierdo.getDato()) < 0) {
                        nodoNuevo = RotacionDerecha(nodo2);
                    } else {
                        nodoNuevo = dobleRotacionDerecha(nodo2);
                    }
                }
            }
        } else if (nodo1.getDato().compareTo(nodo2.getDato()) > 0) {
            if (nodo2.derecho == null) {
                nodo2.derecho = nodo1;
                nodo2.derecho.setPosicion(nodo2.derecho.getPosicion()+ this.position + " ");
                this.position++;
            } else {
                nodo2.derecho = insertarArbol(nodo1, nodo2.derecho);

                if ((Balance(nodo2.izquierdo) - Balance(nodo2.derecho) == -2)) {
                    if (nodo1.getDato().compareTo(nodo2.derecho.getDato()) > 0) {
                        nodoNuevo = RotacionIzquierda(nodo2);
                    } else {
                        nodoNuevo = dobleRotacionIzquierda(nodo2);
                    }
                }
            }
        } else {
            nodo2.setPosicion(nodo2.getPosicion() + this.position + " ");
            this.position++;
        }
        if ((nodo2.izquierdo == null) && (nodo2.derecho != null)) {
            nodo2.balance = nodo2.derecho.balance + 1;
        } else if ((nodo2.derecho == null) && (nodo2.izquierdo != null)) {
            nodo2.balance = nodo2.izquierdo.balance + 1;
        } else {
            nodo2.balance = Math.max(Balance(nodo2.izquierdo), Balance(nodo2.derecho)) + 1;
        }
        return nodoNuevo;
    }

    public void insertar(String c) {
        NodoArbol nuevoNodo = new NodoArbol(c);
        if (this.root == null) {
            this.root = nuevoNodo;
            this.root.setPosicion(this.root.getPosicion() + this.position + " ");
            this.position++;
        } else {
            this.root = insertarArbol(nuevoNodo, root);
        }
    }//insert

}//class
