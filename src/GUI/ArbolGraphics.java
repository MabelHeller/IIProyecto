/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Arbol;
import Domain.NodoArbol;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Heller
 */
public class ArbolGraphics extends JPanel {

    private Arbol arbol;
    private NodoArbol nodo;
    private Graphics2D graphics2D;
    private int x;
    private int y;

    public ArbolGraphics(Arbol myTree) {
        super();
        this.arbol = myTree;
        this.nodo = this.arbol.root;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintTree(g, nodo, 350, 20, 110, 40, 0);
    }

    private void paintTree(Graphics g, NodoArbol nodo, int x, int y, int xoff, int yoff, int nivel) {
        if (nodo == null) {
            return;
        }
        g.setColor(Color.pink);
        if (nodo.izquierdo != null) {
            g.drawLine(x + 10, y + 20, x - xoff + (nivel * 2) + 30, (y + yoff) + 100);
        }
        if (nodo.derecho != null) {
            g.drawLine(x + 10, y + 20, x + xoff - (nivel * 2) - 30, (y + yoff) + 100);
        }
        g.fillOval(x - 50, y - 10, 130, 50);
        g.setColor(Color.DARK_GRAY);
        //g.setFont(new Font("Monospace", Font.CENTER_BASELINE, 12));
        g.drawString(nodo.getDato() + " [" + nodo.getPosicion() + "]", x - 25, y + 15);
        paintTree(g, nodo.izquierdo, (int) (x - xoff) + 30, (y + yoff) + 70, xoff + nivel * 2, yoff, nivel + 1);
        paintTree(g, nodo.derecho, (int) (x + xoff) - 50, (y + yoff) + 70, xoff - nivel * 2, yoff, nivel + 1);
    }
}//class
