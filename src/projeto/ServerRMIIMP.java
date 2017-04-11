/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Fábio
 */
public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface{
    public static ArrayList<Topico> d ;
    
    public ServerRMIIMP() throws RemoteException{
        
        super(); 
  
        }
    public void addTopico(String s ) throws java.rmi.RemoteException
    {
        if(checkTopic(s,d)==false)
        {
            Topico c = new Topico();
            c.setNomeTopico(s);
            d.add(c);
            
            System.out.println("Server : " + d.toString());
           
        }
        else
        {
            System.out.println("Já existe este topico");
            //adicionar noticia 
        }
        
      
    }
    public boolean checkTopic(String s ,ArrayList<Topico> d) throws java.rmi.RemoteException
    {
        if(d.contains(s))
            return true;
       else
            return false;
    }
    
    @Override
    public String toString() {
        return "Array{" + d.toString() ;
    }
    
    
    
}
