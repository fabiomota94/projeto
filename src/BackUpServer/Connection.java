/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUpServer;

import Classes.Topico;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author TiagoMartins
 */
public class Connection extends Thread {
    
    private Socket S = null;
    ArrayList <Topico> tpbackup = new ArrayList();
        
    public Connection (Socket s, ArrayList <Topico> topicosbackup) {
        
        super();
        
        S = s;
        tpbackup = topicosbackup;
        
        start();
    
    }
    
    
    public void run (){ 
    
    
        System.out.println("Estou dentro da thread ehehehehe");
    
    
    
    }
    
    
    
    
}
