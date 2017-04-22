/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUpServer;

import Classes.Topico;
import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TiagoMartins
 */
public class Connection extends Thread {
    
    private Socket S = null;
    ArrayList <Topico> tpbackup = new ArrayList();
    GuardarDados gd = new GuardarDados();
    LerFicheiro lf = new LerFicheiro();
    
    public Connection (Socket s, ArrayList <Topico> topicosbackup) throws IOException, ClassNotFoundException {
        
        super();
               
        tpbackup=lf.LerBackup();
        S = s;
        tpbackup = topicosbackup;        
        start();
    
    }
    
    
    public void run (){ 
    
    
        System.out.println("Estou dentro da thread ehehehehe");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(S.getOutputStream());
            oos.writeObject(tpbackup);
            oos.flush();
            oos.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    
    
}
