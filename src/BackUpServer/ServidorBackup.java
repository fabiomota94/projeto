/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author TiagoMartins
 */
public class ServidorBackup{
    
     public static void main(String[] args) throws IOException, ClassNotFoundException{  
   
         
         ServerSocket servers = null;
         Socket s;
         Connection t;
  
        // ler no ficheiro ficheiro back
        
        //ObjectInputStream ois = new ObjectInputStream(new FileInputStream("aluno.dat"));
         

        servers = new ServerSocket (2222);
  
        try {
            while (true) {
                
                s = servers.accept();
                t = new Connection ();
                
            } 
        }
        catch (IOException e) {
        
            System.out.println("Erro!");
        } 
    }     

}