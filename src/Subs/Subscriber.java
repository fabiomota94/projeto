/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subs;

import Classes.Noticias;
import Servidores.ServerRMIInterface;

import java.rmi.Naming;

import projeto.Ler;

/**
 *
 * @author Fábio
 */
public class Subscriber {
    private int id ;
   public Subscriber(int tipo){
      id = tipo;
       System.setSecurityManager(new SecurityManager());
        
        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");
       
         
       System.out.println("Subscriber:");
       if(id == 2)
       {
           //subs com registo
       }
       if(id == 3)
       {
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
           if(opcao==0)
               break;
           }
       }
   
    }
    catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
}
}
