/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import dataStructure.BinaryTree;
import dataStructure.CircularDoublyLinkedList;
import dataStructure.Node;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author larios
 */
public class UserInteraction {
    private Node song;
    private boolean loadFiles;
    private BinaryTree bt;
    private CircularDoublyLinkedList cl;
    private final String menu = "--- Escoja una opción ---\n"
                              + "0.- Salir\n"
                              + "1.- Escojer la carpeta\n"
                              + "2.- Seleccionar canciones\n"
                              + "3.- Reproducir\n"
                              + "4.- Siguiente canción\n"
                              + "5.- Anterior canción\n"
                              + "6.- Pausar\n"
                              + "7.- Resumir\n"
                              + "8.- Detener"; 
    
    public UserInteraction() {
        bt = new BinaryTree();
        loadFiles = false;
        cl = new CircularDoublyLinkedList(); 
    }
    
    public void start() throws BasicPlayerException {
        int option;
        
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
                chooseOption(option);
            } catch(HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Inserte una opción correcta");
                option = 9;
            }
        } while(option != 0);
        
        song.player.stop();
    }
    
    private void chooseOption(int option) throws BasicPlayerException {
        if (!loadFiles && option != 1) {
            JOptionPane.showMessageDialog(null, "Primero debe seleccionar una carpeta");
            return;
        }
        
        switch(option) {
            case 1: findFiles(); break;
            case 2: chooseMusic(); break;
            case 3: play(); break;
            case 4: next(); break;
            case 5: previous(); break;
            case 6: pause(); break;
            case 7: resume(); break;
            case 8: stop(); break;
        }
    }
    
    private void next() throws BasicPlayerException {
        if (song.next != null) {
            song.player.stop();
            song = song.next;
            song.player.play();
        }
    }
    
    private void previous() throws BasicPlayerException {
        if(song.previous != null) {
            song.player.stop();
            song = song.previous;
            song.player.play();
        }
    }
    
    private void play() throws BasicPlayerException {
        if(cl.getSize() != 0) {
            song = cl.getStart();
            song.player.play();
        }
    }
    
    private void chooseMusic() {
        Object[] names = bt.getNames().toArray();
        String song = (String) JOptionPane.showInputDialog(null, "Seleccione una canción para agregarla a la lista", "Escoger", 
                                                                 JOptionPane.INFORMATION_MESSAGE, null, names, names[0]);
        
        if(song != null) {
            cl.add(bt.find(song));
            System.out.println(cl.getSize());
        }
    }
    
    private void findFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File folder = new File(chooser.getSelectedFile().getAbsolutePath());
            loadFiles(folder.listFiles());
        }
    }
    
    private void loadFiles(File[] files) {
        for(File file : files) {
            bt.add(new Player(file.getAbsolutePath()));
        }
        
        bt.showTree();
        loadFiles = true;
    }

    private void pause() throws BasicPlayerException {
        song.player.pause();
    }

    private void resume() throws BasicPlayerException {
        song.player.resume();
    }

    private void stop() throws BasicPlayerException {
        song.player.stop();
    }
}
