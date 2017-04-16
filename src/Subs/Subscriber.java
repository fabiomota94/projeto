/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subs;

import Classes.Noticias;
import Servidores.ServerRMIInterface;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;

import java.rmi.registry.*;
import projeto.Ler;

/**
 *
 * @author Fábio, Tiago, 
 */
public class Subscriber extends java.rmi.server.UnicastRemoteObject implements SubscriberInterface, Serializable{
    
    private int id;
    private int tipo ;
    public Registry registry;
    public String serverName ;
    public Subscriber() throws RemoteException{
        
        super();
        
    } 
    
    
    public void EscreverNoticia (Noticias n) throws java.rmi.RemoteException{
        
        
        System.out.println(n.toString());
         
        
    }
    public Subscriber(int idi) throws RemoteException
    {
         System.setSecurityManager(new SecurityManager());
        
        try {
           ServerRMIInterface si = (ServerRMIInterface) this.registry.lookup("rmi://127.0.0.1:1099/ServerRMI");
           serverName = java.net.InetAddress.getLocalHost().getHostName();
           
        while(true)
           {
           int opcao =0;
           Noticias noticia;
           System.out.println("1 - Constular Noticia de um topico");
           System.out.println("2 - Consultar a Ultima noticia do Topico");
           System.out.println("0 - Sair");
           opcao = Ler.umInt();
           if(opcao == 2)
           {
               System.out.println("Indique o topico");
               String nometopico = Ler.umaString();
               noticia = si.UltimaNoticia(nometopico);
               System.out.println(noticia.toString());
           }
           else if(opcao==0)
               break;
           }
        }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   public Subscriber(int tip,int idi) throws RemoteException {
      this.tipo = tip;
      this.id= idi;
       System.setSecurityManager(new SecurityManager());
        
        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");
       
         
       System.out.println("Subscriber:");
        //SUBS COM REGISGO
       
          while(true)
           {
               
           
           Noticias noticia;

           System.out.println("1 - Subscrever um tópico");
           System.out.println("2 - Constular Noticia de um topico");
           System.out.println("3 - Consultar a Ultima noticia do Topico");
           System.out.println("0 - Sair");
           int  opcao = Ler.umInt();
           System.out.println("Opcao escolhida");
           if(opcao == 1)
           {
               System.out.println("1 - Subscrever um tópico");
               String nt = Ler.umaString();
               Subscriber sub = new Subscriber();
               si.subscribe(nt, (SubscriberInterface) sub);
               System.out.println("Topico Subscrito");
               
           }
           else if(opcao == 3)
           {
               System.out.println("Indique o topico");
               String nometopico = Ler.umaString();
               noticia = si.UltimaNoticia(nometopico);
               System.out.println(noticia.toString());
           }
           else if(opcao == 0)
               break;
           
         }
           
       }
    
    catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
}
}
