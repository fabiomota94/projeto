/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Classes.Noticias;
import Classes.Topico;

/**
 *
 * @author Fábio
 */
public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface{
    
    public static ArrayList<Topico> d = new ArrayList();
    
    public ServerRMIIMP() throws RemoteException{
        
        super(); 
  
        }
    public boolean addTopico(String s ) throws java.rmi.RemoteException
    {
        if(checkTopic(s,d)==false)
        {
            Topico c = new Topico();
            c.setNomeTopico(s);
            d.add(c);
            
            System.out.println("Server : " + d.toString());
            return false;
           
        }
        else
        {
            System.out.println("Já existe este topico");
            return true;
            //adicionar noticia 
        }
        
      
    }
    public boolean checkTopic(String s ,ArrayList<Topico> d) throws java.rmi.RemoteException
    {
      
            for (int i = 0; i < d.size(); i++) {
                if(d.get(i).getNometopico().equals(s))
                    return true;
               
            }
          return false; 
        
       
    }
     public ArrayList <Topico> ConsultarTopicos() throws java.rmi.RemoteException
    {
        
        
          return d;
    }
    
    @Override
    public String toString() {
        return "Array{" + d.toString() ;
    }
    
    
    public boolean checkTopic2(String s ) throws java.rmi.RemoteException
    {
      
            for (int i = 0; i < d.size(); i++) {
                if(d.get(i).getNometopico().equals(s))
                    return true;
               
            }
          return false; 
        
       
    }
     public boolean addNoticia(Noticias noticia ,String ntopico) throws java.rmi.RemoteException
    {
        for (int i = 0; i < d.size(); i++) {
                if(d.get(i).getNometopico().equals(ntopico))
                {
                    d.get(i).addNovaNoticia(noticia);
                }
        }
        
      return true;
    }
    
}
