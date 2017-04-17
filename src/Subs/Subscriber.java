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
import java.util.ArrayList;
import java.util.Date;
import projeto.Ler;

/**
 *
 * @author Fábio, Tiago,
 */
public class Subscriber extends java.rmi.server.UnicastRemoteObject implements SubscriberInterface, Serializable {

    private int id;
    private int tipo;
    public Registry registry;
    public String serverName;

    public Subscriber() throws RemoteException {

        super();

    }

    public void EscreverNoticia(Noticias n) throws java.rmi.RemoteException {

        System.out.println(n.toString());

    }
    
    
   
    
    

    public Subscriber(int idi) throws RemoteException {
        System.setSecurityManager(new SecurityManager());
        
        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");
            

            while (true) {
                int opcao = 0;
                Noticias noticia;
                System.out.println("1 - Consultar Noticia de um topico");
                System.out.println("2 - Consultar a Ultima noticia do Topico");
                System.out.println("0 - Sair");
                opcao = Ler.umInt();
                if (opcao == 1) {
                    System.out.println("Indique o Topico onde vai procurar as Noticias.");
                    String nomeTopico1 = Ler.umaString();
                    
                   Date dataMaisRecente = null; 
                   Date dataMaisVelha = null;
                   ArrayList<Noticias> noticiasResultado = new ArrayList();
                   
                    System.out.println("Indique o ano da data mais recente:");
                    int ano = Ler.umInt()-1900;
                    
                    System.out.println("Indique o mes da data mais recente:");
                    int mes = Ler.Mes()-1;
                    
                    System.out.println("Indique o dia da data mais recente:");
                    int dia = Ler.dia();
                    
                    System.out.println("Indique a hora da data mais recente:");
                    int hrs = Ler.umInt();
                    
                    
                    System.out.println("Indique o minuto da data mais recente:");
                    int min = Ler.umInt();
                    
                    dataMaisRecente = new Date(ano, mes, dia, hrs, min);
                    
                    System.out.println("Indique o ano da data mais antiga:");
                    int ano2 = Ler.umInt()-1900;//rever
                    
                    System.out.println("Indique o mes da data mais antiga:");
                    int mes2 = Ler.Mes()-1;
                    
                    System.out.println("Indique o dia da data mais antiga:");
                    int dia2 = Ler.dia();
                    
                    System.out.println("Indique a hora da data mais antiga:");
                    int hrs2 = Ler.umInt();
                    
                    
                    System.out.println("Indique o minuto da data mais antiga:");
                    int min2 = Ler.umInt();
                    
                    dataMaisVelha = new Date(ano2, mes2, dia2, hrs2, min2);
                    
                    noticiasResultado = si.MostarNoticiasEntreDatas(nomeTopico1, dataMaisRecente, dataMaisVelha);                  
                    if(noticiasResultado == null){
                        System.out.println("Sem noticias para serem mostradas entre essas datas.");
                    }
                    else{
                    System.out.println(noticiasResultado.toString());
                    }
                    
                } else if (opcao == 2) {
                    System.out.println("Indique o topico");
                    String nometopico = Ler.umaString();
                    noticia = si.UltimaNoticia(nometopico);
                    System.out.println(noticia.toString());
                } else if (opcao == 0) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Subscriber(int tip, int idi) throws RemoteException {
        this.tipo = tip;
        this.id = idi;
        System.setSecurityManager(new SecurityManager());

        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");

            System.out.println("Subscriber:");
            //SUBS COM REGISGO

            while (true) {

                Noticias noticia;

                System.out.println("1 - Subscrever um tópico");
                System.out.println("2 - Constular Noticia de um topico");
                System.out.println("3 - Consultar a Ultima noticia do Topico");
                System.out.println("0 - Sair");
                int opcao = Ler.umInt();
                System.out.println("Opcao escolhida");
                if (opcao == 1) {
                    System.out.println("1 - Subscrever um tópico");
                    String nt = Ler.umaString();
                    Subscriber sub = new Subscriber();
                    si.subscribe(nt, (SubscriberInterface) sub);
                    System.out.println("Topico Subscrito");

                } else if (opcao == 3) {
                    System.out.println("Indique o topico");
                    String nometopico = Ler.umaString();
                    noticia = si.UltimaNoticia(nometopico);
                    System.out.println(noticia.toString());
                } else if (opcao == 0) {
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
