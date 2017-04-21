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
import Publisher.Publisher;
import Subs.SubscriberInterface;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.Ler;
import projeto.LoginMain;

/**
 *
 * @author Fábio
 */
public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface{
    
    public static ArrayList<Topico> d = new ArrayList();
    public static ArrayList<Topico> topbackup = new ArrayList();
    public static ArrayList<LoginMain> pub = new ArrayList(); //array de Publishers
    public static ArrayList<LoginMain> subs = new ArrayList(); //array de subscribers
    
    LerFicheiro lf = new LerFicheiro();
    GuardarDados gd = new GuardarDados();
       
     
     public int max ;
       
    public ServerRMIIMP() throws RemoteException, IOException, ClassNotFoundException{
        
        
        super(); 
        d = lf.LerTopico();
        topbackup = lf.LerBackup();
        pub = lf.LerFilePublishers();
        subs = lf.LerFileSubscribers();
        System.out.println(d.toString());
        System.out.println(pub.toString());
        System.out.println(subs.toString());
    }
    
    //Registar
    
    public boolean createUser(String nome, String pass, int tipo, int id, int op) throws java.rmi.RemoteException {
        

                if (op == 1) {
                    LoginMain publishers = new LoginMain();
                    publishers.setNome(nome);
                    publishers.setPass(pass);
                    publishers.setTipo(tipo);
                    publishers.setId(id);
                    pub.add(publishers);
                    
                    try {
                        gd.SaveFilePublishers(pub);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }
                if (op == 2) {
                    LoginMain subscribers = new LoginMain();
                    subscribers.setNome(nome);
                    subscribers.setPass(pass);
                    subscribers.setTipo(tipo);
                    subscribers.setId(id);
                    subs.add(subscribers);
                    try {
                        gd.SaveFile_Consumers(subs);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                 return true;  
                }
        
                return false;
    }
    
    //LOGIN
    
    
    public boolean LOGIN(String nome, String pass, int tipo, int id, int op, ServerRMIInterface si) throws java.rmi.RemoteException {
        
                
                int flag = 0;
                
                for (int i = 0; i < pub.size(); i++) {

                         if(pub.get(i).nome.equals(nome))//user encontrado
                         {
                             
                             if(pub.get(i).pass.equals(pass))
                             {
                               id = pub.get(i).id;
                               flag++;  
                             }
                             
                         } 

                    }

                    if (flag > 0) {
                        System.out.println("ID DO PUBLISHER  "+ id);
                        Publisher p = new Publisher(id, (ServerRMIInterface) si);
                    }
                    
                    return false;
    }
    
    
    
    
    public boolean addTopico(String s) throws java.rmi.RemoteException 
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
        
         
        // System.out.println("DATA MAIS RECENTE " + dataMaisRecente.toString());         
        // System.out.println("DATA MAIS ANTIGAA " + dataMaisAntiga.toString());
        
        
         
         for (int i = 0; i < noticias.size(); i++) {
             
                if(dataMaisRecente.before(noticias.get(i).getData()) && dataMaisAntiga.after(noticias.get(i).getData())){
                  //  System.out.println("\npassou aqui alguma ves?\n");
                    noticiasResult.add(noticias.get(i));
                    
                }
        /**
         *  FAZER EXATAMENTE O MESMO PARA O SERVIDOR DE BACKUP AGORA
         * 
         *  - Passar todas as noticias dentro de uma determinada data para dentro do array noticiasResult
         * 
         **/
         
         
     }
   
         return noticiasResult;
  }
     
}
