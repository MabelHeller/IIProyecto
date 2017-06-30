/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Arbol;
import Data.ArchivoData;
import Domain.NodoArbol;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Heller
 */
public class Ventana extends JFrame implements ActionListener {

    private JMenu jmMenu;
    private JMenuItem jmiCargarArchivo;
    private JMenuItem jmiCrearArbol;
    private JMenuItem jmiBuscarD;
    private JMenuItem jmiGuardarA;
    private JFileChooser chooser;
    private JMenuBar jmbBarra;
    private JDesktopPane desktopPane;
    private JScrollPane scroll;
    private ArbolGraphics arbolG;
    private Arbol arbol;
    public ArchivoData archivoData=new ArchivoData();
    public String ruta;
    public File archivo;
    public ArrayList<String> lista;
    public ArrayList<String> listaPA;
    public JButton jbtnGuardar;

    public Ventana() {
        super();
        this.setPreferredSize(new Dimension(800, 600));
        init();
    }//constructor

    private void init() {
        arbol = new Arbol();
        listaPA = new ArrayList<String>();
        this.desktopPane = new JDesktopPane();
        this.setBounds(0, 0, 800, 600);
        this.desktopPane.setLayout(null);
        this.jmMenu = new JMenu("Menu");
        this.jmiCargarArchivo = new JMenuItem("Cargar y Leer Archivo");
        this.jmiCrearArbol = new JMenuItem("Crear Árbol");

        this.jbtnGuardar = new JButton("Guardar Arbol");
        this.jbtnGuardar.setBounds(450, 10, 137, 20);
        this.add(this.jbtnGuardar);
        this.jbtnGuardar.addActionListener(this);
        
        this.jmiBuscarD = new JMenuItem("Buscar Dato");
        this.jmiGuardarA = new JMenuItem("Guardar Arbol");
        this.jmbBarra = new JMenuBar();
        this.jmbBarra.setBounds(5, 5, 70, 50);
        this.add(this.jmMenu);
        jmbBarra.add(jmMenu);
        jmMenu.add(jmiCargarArchivo);
        jmMenu.add(jmiCrearArbol);
        jmMenu.add(jmiBuscarD);
        //jmMenu.add(jmiListado);
        this.jmiBuscarD.addActionListener(this);
        this.jmiGuardarA.addActionListener(this);
        this.jmiCrearArbol.addActionListener(this);
        this.jmiCargarArchivo.addActionListener(this);
        this.setJMenuBar(jmbBarra);
        this.getContentPane().add(this.desktopPane);
    }

    public void llenarArbol(ArrayList<String> lista) {
        //System.out.println(lista);
        //lista=archivoData.getListaArbol();
        //String[] createArray = c.split("[ \n]");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            this.arbol.insertar(lista.get(i));
        }//for i
    }//createAndPaintTree 

    public void createAndPaintTree(String info) {
        String[] createArray = info.split("[ \n]");
        for (int i = 0; i < createArray.length; i++) {
            this.arbol.insertar(createArray[i]);
        }//for i

    }//createAndPaintTre

    public void paint() {
        this.arbolG = new ArbolGraphics(this.arbol);
        this.scroll = new JScrollPane();
        this.scroll.setBounds(new Rectangle(0, 50, 900, 700));
        this.scroll.setViewportView(this.arbolG);
        this.scroll.getViewport().setView(this.arbolG);
        this.arbolG.setPreferredSize(new Dimension(800, 600));
        this.arbolG.repaint();
        this.arbolG.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.desktopPane.add(this.scroll);
    }//paint

    public File AbrirArchivo() {
        JFileChooser file = new JFileChooser();//carga la ventana
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("txt File", "txt");
        file.setFileFilter(filtro);
        int resultado = file.showOpenDialog(this);
        archivo = file.getSelectedFile();
        ruta = archivo.getPath();
        file.setDialogType(JFileChooser.SAVE_DIALOG);
        return archivo;
    }
    
    private void Archivo() throws FileNotFoundException {
        archivoData.guardarNodoArbol(arbol.root,"Arbol.txt");       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmiCargarArchivo) {
            CargarArchivo r = new CargarArchivo();
            r.setBounds(0, 0, 400, 400);
            this.desktopPane.add(r);
            r.show();
            r.setClosable(true);
        } else if (e.getSource() == jmiCrearArbol) {
            createAndPaintTree(archivoData.fileReader());
            paint();
            repaint();
            revalidate();
//            this.arbolG = new ArbolGraphics(arbol);
//            arbolG.setSize(600, 600);
//            arbolG.setBounds(0, 0, 800, 800);
//            arbolG.setVisible(true);
        } else if (e.getSource() == jmiBuscarD) {
            BuscarDato r = new BuscarDato();
            r.setBounds(0, 0, 400, 400);
            this.desktopPane.add(r);
            r.show();
            r.setClosable(true);
        } else if (e.getSource() == jmiGuardarA) {
     
            try {
                Archivo();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        }
    }
}
