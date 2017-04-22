/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import java.rmi.RemoteException;
import java.util.ArrayList;
import Classes.Noticias;
import Classes.Topico;
import Subs.Subscriber;
import Subs.SubscriberInterface;
import java.util.Date;

/**
 *
 * @author Fábio
 */
public interface ServerRMIInterface extends java.rmi.Remote{
    
   public boolean createUser(String nome, String pass, int tipo) throws java.rmi.RemoteException;
   public int LOGIN(String nome, String pass, int tipo) throws java.rmi.RemoteException;
   public boolean checkuser(String nome, int tipo) throws java.rmi.RemoteException;
   public boolean addTopico(String s ) throws java.rmi.RemoteException;
   public boolean checkTopic(String s ,ArrayList<Topico> d)throws java.rmi.RemoteException;
   public ArrayList <Topico> ConsultarTopicos() throws java.rmi.RemoteException;
   public ArrayList<Noticias> ConsultarNoticisPub(int id) throws java.rmi.RemoteException;
   public boolean checkTopic2(String s ) throws java.rmi.RemoteException;    
   public boolean addNoticia(Noticias noticia ,String ntopico) throws java.rmi.RemoteException;
   public Noticias UltimaNoticia(String nometopico) throws java.rmi.RemoteException;
   public void subscribe (String tp,int id, SubscriberInterface subs) throws java.rmi.RemoteException;
   public ArrayList<Noticias> MostarNoticiasEntreDatas (String nomeTopico, Date dataMaisRecente, Date dataMaisVelha)throws java.rmi.RemoteException;
   public void updatesubs (int id, SubscriberInterface subs)throws java.rmi.RemoteException;
}
