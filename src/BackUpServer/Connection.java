/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUpServer;

import Classes.Noticias;
import Classes.Topico;
import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import java.io.IOException;
import java.io.ObjectInputStream;
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
            
            /** O server vai ouvir primeiro, e aqui vem o nome do Topico onde queremos procurar as noticias **/
            ObjectInputStream ois = new ObjectInputStream(S.getInputStream());
            String nomeTopico = (String) ois.readObject();
            ArrayList<Noticias> resultado = filtraNoticias_de_um_topico(nomeTopico);
                        
            
            
            
            
            ObjectOutputStream oos = new ObjectOutputStream(S.getOutputStream());
            oos.writeObject(resultado);
            oos.flush();
            
            
            ois.close();
            oos.flush();
            oos.close();
            
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public ArrayList<Noticias> filtraNoticias_de_um_topico(String nomeTopico){
        System.out.println("NOME TOPICO "  + nomeTopico );
        ArrayList<Noticias> resultado = new ArrayList();
        
      
        for (int i = 0; i < tpbackup.size() ; i++) {
            System.out.println("SOUT DENTRO DA FUNCAO " + tpbackup.get(i).getNometopico());
            if(tpbackup.get(i).getNometopico().equals(nomeTopico)){
                
                System.out.println("DEVERIA PASSAR NO if ");
                System.out.println(tpbackup.get(i).getNoticias().size());
                
                for (int j = 0; j < tpbackup.get(i).getNoticias().size(); j++) {
                    
                
                     resultado.add(tpbackup.get(i).getNoticias().get(j));
                

                        System.out.println("PRINT TESTE " + tpbackup.get(i).getNoticias().get(j));
                    
                
                }
 
               
            }
        }
        
        return resultado;
    }
    
    
    
    
}
