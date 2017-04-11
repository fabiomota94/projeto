/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.Serializable;
import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Fábio
 */
public class Publisher {
     
    public static void main(String[] args) {
        
        int flag=0;
        
        System.setSecurityManager(new SecurityManager());
        try{
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");
            //Publisher p = new Publisher();
            System.out.println("1-Add topico");
            String s = "" ;
           // ArrayList<Topico> d = new ArrayList();
            s= Ler.umaString();
            si.addTopico(s);
            System.out.println("Addd ok ");
            System.out.println("publisher  ");
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        while(flag!=0)
        {}
        
        
        
        
        
        
        
        
        
    }
}
