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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fábio
 */
public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface{
    
    public static ArrayList<Topico> d = new ArrayList();
    public static ArrayList<Topico> topbackup = new ArrayList();
     LerFicheiro lf = new LerFicheiro();
     GuardarDados gd = new GuardarDados();
     public int max ;
       
    public ServerRMIIMP() throws RemoteException, IOException, ClassNotFoundException{
        
        
        super(); 
        d = lf.LerTopico();
        topbackup = lf.LerBackup();
        System.out.println(d.toString());
        }
    public boolean addTopico(String s ) throws java.rmi.RemoteException 
    {
       
        
        if(checkTopic(s,d)==false)
        {
            Topico c = new Topico();
            c.setNomeTopico(s);
            d.add(c);
            topbackup.add(c);
            try {
                gd.guardartop(d);
                gd.guardarbackup(topbackup);
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
                    System.out.println("Adicionado ao Array D a noticia " + noticia.toString());
                }
        }
        try {
                gd.guardartop(d);
            } catch (IOException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
        //Verificação de subscribers
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
         
         
         //verificação de backup
       
         
         
         int posicao2=-1;
         try {
            topbackup = lf.LerBackup();
            max = lf.LerLimite();
            System.out.println("MAX LIDO " + max);
            System.out.println("Antes");
            System.out.println("D " + d.toString());
            System.out.println("Backup " + topbackup.toString());
            
           
            int flag = 0 ;
            int sizenoticias = d.get(posicao).getNoticias().size();
            int mx = (max/2);
            int sizedobackup = topbackup.size();
            System.out.println("\n sizedobackup= " + sizedobackup + "\n");
            for(int i = 0 ;i<sizedobackup;i++)
            {
                System.out.println("POS: " + i + " " + topbackup.get(i).toString());
            }
            if(sizedobackup>0)
            {
                //procuramos o topico
                for(int i = 0 ;i<sizedobackup;i++)
                {
                    //Posicao onde esta topico
                    if(topbackup.get(i).getNometopico().equals(ntopico))
                    {
                        flag++;
                        posicao2 = i;
                    }
                    
                }
                //encontramos o topico
                if(flag>0)
                {
                    
                    System.out.println("Tamanho Noticias "+ sizenoticias);
                    if(sizenoticias>mx)
                    {
                        while(sizenoticias>mx)
                        {
                          topbackup.get(posicao2).addNovaNoticia(d.get(posicao).getNoticias().get(0));
                          d.get(posicao).getNoticias().remove(0);
                          sizenoticias = d.get(posicao).getNoticias().size();
                          gd.guardartop(d);
                          gd.guardarbackup(topbackup);
                        }
                        
                    }
                    
                }
            }  

        } catch (IOException ex) {
            Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("\nDepois");
         System.out.println("D " + d.toString());
         System.out.println("Backup " + topbackup.toString());  
       
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
    
     
     
  }
     
    

