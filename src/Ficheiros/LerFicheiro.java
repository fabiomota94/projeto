/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheiros;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import projeto.LoginMain;

/**
 *
 * @author Fábio
 */
public class LerFicheiro {

        public ArrayList<LoginMain> LerFilePublishers() throws IOException,ClassNotFoundException {
            ArrayList<LoginMain> p1 = new ArrayList();
     
            try{           
       
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("publishers.dat"));
            p1 = (ArrayList<LoginMain>) ois.readObject();
            ois.close();
     
            }
            catch(IOException e){
                System.out.println(e);
            }
            
            return p1;
      
        }
        public int LerID() throws IOException,ClassNotFoundException {
           
            int idi = 0 ;
             try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ids.dat"));
           
            idi = ois.readInt();
            ois.close();
             }
           catch(IOException e){
                System.out.println(e);
            }
            return idi;
            
        }
        public ArrayList<LoginMain> LerFileSubscribers() throws IOException,ClassNotFoundException {
            
            ArrayList<LoginMain> subs = new ArrayList();
     
            try{           
       
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("subscribers.dat"));
            subs = (ArrayList<LoginMain>) ois.readObject();
            ois.close();
     
            }
            catch(IOException e){
                System.out.println(e);
            }
            
            return subs;
      
        }
        
        
      
           
}
