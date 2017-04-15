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
import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fábio
 */
public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface{
    
    public static ArrayList<Topico> d = new ArrayList();
     LerFicheiro lf = new LerFicheiro();
     GuardarDados gd = new GuardarDados();
       
    public ServerRMIIMP() throws RemoteException, IOException, ClassNotFoundException{
        
        
        super(); 
        d = lf.LerTopico();
        }
    public boolean addTopico(String s ) throws java.rmi.RemoteException 
    {
       
        
        if(checkTopic(s,d)==false)
        {
            Topico c = new Topico();
            c.setNomeTopico(s);
            d.add(c);
            try {
                gd.guardartop(d);
            } catch (IOException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            System.out.println("Server : " + d.toString());
            return false;
           
        }
        else
        {
            System.out.println("Já existe este topico");
            
            return true;
            
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
     public boolean addNoticia(Noticias noticia ,String ntopico) throws java.rmi.RemoteException{
        for (int i = 0; i < d.size(); i++) {
                if(d.get(i).getNometopico().equals(ntopico))
                {
                    d.get(i).addNovaNoticia(noticia);
                }
        }
        try {
                gd.guardartop(d);
            } catch (IOException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        
      return true;
    }
     
    public ArrayList<Noticias> ConsultarNoticisPub(int id) throws java.rmi.RemoteException{
        
        System.out.println("Mostrar Topcios");    
        System.out.println(d.toString());
        
        ArrayList<Noticias> n;
        ArrayList<Noticias> n1 = new ArrayList();
        
        for (int i = 0; i < d.size(); i++) {
            
            System.out.println("Noticia " + d.get(i).getNoticias());
            n = d.get(i).getNoticias();
             System.out.println("N : " + n.toString());   
            for (int j = 0; j < n.size(); j++) {
                
                if (n.get(j).getAutor()==id)
                {
                    n1.add(n.get(j));
                }
                
                
            }
                    
            }
        
        
        return n1;
    }
    
    public Noticias UltimaNoticia(String nometopico)
    {
        ArrayList<Noticias> n = new ArrayList<Noticias>();
        for(int i = 0 ;i<d.size();i++)
        {
            if(d.get(i).getNometopico().equals(nometopico))
            {
                
                n = d.get(i).getNoticias();
                return n.get(n.size() - 1);
                    
               
            }
            
        }
       return null;
    }
        
        
  }
     
    

