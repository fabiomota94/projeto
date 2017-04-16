/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Classes.Noticias;
import Classes.Topico;
import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import Subs.Subscriber;
import Subs.SubscriberInterface;
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
        System.out.println(d.toString());
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
        
         ArrayList<SubscriberInterface> subscribersn = null;
         int posicao=0;
         
         
         for (int i = 0; i < d.size(); i++) {
                if(d.get(i).getNometopico().equals(ntopico))
                {   
                    posicao=i;
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
              
         subscribersn = d.get(posicao).getSUBS();
         
         for (int i = 0; i < subscribersn.size(); i++) {
             
            try{
                System.out.println("A enviar para o subs "+ subscribersn.get(i).toString());
                subscribersn.get(i).EscreverNoticia(noticia);
            }
             catch(Exception e)
             {
                 System.out.println("Cliente offline");
             }
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
    
    
    public void subscribe (String tp, SubscriberInterface subs) throws java.rmi.RemoteException
    {
        
        for (int i = 0; i < d.size(); i++) {
            
            if (d.get(i).getNometopico().equals(tp)){
                
                d.get(i).addSub(subs);
            }

        } 
   
    }
    
     
     public ArrayList<Noticias> MostarNoticiasEntreDatas (String nomeTopico, Date dataMaisRecente, Date dataMaisAntiga)throws java.rmi.RemoteException{
         
         ArrayList<Noticias> noticiasResult = new ArrayList<Noticias>();
         ArrayList<Noticias> noticias = new ArrayList<Noticias>();
         
         for(int i = 0 ;i<d.size();i++)//verificar
        {
            if(d.get(i).getNometopico().equals(nomeTopico))
            {
                
                noticias = d.get(i).getNoticias();
               
            }
            
        }
         // depois de ter as noticias todas daquele topico no ArrayList noticias, vamos filtrar pela DATA
         //a ultima é a mais velha
         //primeira é a mais recente
         
         System.out.println("DATA MAIS RECENTE " + dataMaisRecente.toString());
         
         System.out.println("DATA MAIS ANTIGAA " + dataMaisAntiga.toString());
         
         for (int i = 0; i < noticias.size(); i++) {
             
                if(dataMaisRecente.before(noticias.get(i).getData()) && dataMaisAntiga.after(noticias.get(i).getData())){
                    System.out.println("\npassou aqui alguma ves?\n");
                    noticiasResult.add(noticias.get(i));
                    
                }
        
         
         
     }
   
         return noticiasResult;
  }
     
}