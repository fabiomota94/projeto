/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheiros;

import Classes.Topico;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import projeto.LoginMain;

/**
 *
 * @author Fábio
 */


public class GuardarDados {
    
    public void SaveFilePublishers(ArrayList<LoginMain> publicadores) throws IOException,ClassNotFoundException{
            
        File ficheiro = new File("publishers.dat");
        
        
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ficheiro));
        
        os.writeObject(publicadores);
        
        os.flush();
        os.close();
    }
    
    
    public void SaveFile_Consumers (ArrayList<LoginMain> Subscritores) throws IOException,ClassNotFoundException{
            
        File ficheiro1 = new File("subscribers.dat");
        
        
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ficheiro1));
        os.writeObject(Subscritores);
        
        os.flush();
        os.close();
    }
     public void SaveFile_id (int id) throws IOException,ClassNotFoundException{
            
        File ficheiro1 = new File("ids.dat");
        
        
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ficheiro1));
        os.writeInt(id);
        os.flush();
        os.close();
    }
     public void guardartop (ArrayList<Topico> topicos) throws IOException,ClassNotFoundException{
            
        File ficheiro1 = new File("topicos.dat");
        
        
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ficheiro1));
        os.writeObject(topicos);
        os.flush();
        os.close();
    }
    
    
    
}
