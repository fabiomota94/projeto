
package Servidores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Classes.Noticias;
import Classes.Subscritores;
import Classes.Topico;
import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import Subs.SubscriberInterface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.LoginMain;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface {

    public static ArrayList<Topico> d = new ArrayList();
    public static ArrayList<Topico> topbackup = new ArrayList();
    public static ArrayList<LoginMain> pub = new ArrayList(); //array de Publishers
    public static ArrayList<LoginMain> subs = new ArrayList(); //array de subscribers

    public static int id_user = 0;

    LerFicheiro lf = new LerFicheiro();
    GuardarDados gd = new GuardarDados();

    public int limite_max;

    public ServerRMIIMP() throws RemoteException, IOException, ClassNotFoundException {

        super();
        d = lf.LerTopico();
        limite_max = lf.LerLimite();
        topbackup = lf.LerBackup();
        pub = lf.LerFilePublishers();
        subs = lf.LerFileSubscribers();
        id_user = lf.LerID();

        System.out.println("Topicos:" + d.toString());
        System.out.println("Publisher:" + pub.toString());
        System.out.println("Subscriber: " + subs.toString());
        System.out.println("TopicosBackup:" + topbackup.toString());
        System.out.println("ID: " + id_user);
        System.out.println("----Leitura dos Ficheiros OK!-----");
    }

    //Registar
    public boolean createUser(String nome, String pass, int tipo) throws java.rmi.RemoteException {

        if (tipo == 1) { //Publisher
            LoginMain publishers = new LoginMain();
            id_user++;
            publishers.setNome(nome);
            publishers.setPass(pass);
            publishers.setTipo(tipo);
            publishers.setId(id_user);
            pub.add(publishers);

            try {
                gd.SaveFilePublishers(pub);
                gd.SaveFile_id(id_user);
            } catch (IOException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        if (tipo == 2) { //Subscriber
            LoginMain subscribers = new LoginMain();
            id_user++;
            subscribers.setNome(nome);
            subscribers.setPass(pass);
            subscribers.setTipo(tipo);
            subscribers.setId(id_user);
            subs.add(subscribers);
            try {
                gd.SaveFile_Consumers(subs);
                gd.SaveFile_id(id_user);
            } catch (IOException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        }

        return false;
    }

    public boolean checkuser(String nome, int tipo) throws java.rmi.RemoteException {

        if (tipo == 1) {
            for (int i = 0; i < pub.size(); i++) {

                if (pub.get(i).getNome().equals(nome)) {
                    return true;
                }

            }
        }
        if (tipo == 2) {
            for (int i = 0; i < subs.size(); i++) {

                if (subs.get(i).getNome().equals(nome)) {
                    return true;
                }

            }
        }

        return false;

    }

    //LOGIN
    public int LOGIN(String nome, String pass, int tipo) throws java.rmi.RemoteException {

        if (tipo == 1) { //Publisher
            for (int i = 0; i < pub.size(); i++) {

                if (pub.get(i).nome.equals(nome))//user encontrado
                {

                    if (pub.get(i).pass.equals(pass)) //pass correcta
                    {

                        return pub.get(i).getId();
                    }

                }

            }
        }

        if (tipo == 2) { //Subscribers
            for (int i = 0; i < subs.size(); i++) {

                if (subs.get(i).nome.equals(nome))//user encontrado
                {

                    if (subs.get(i).pass.equals(pass)) //pass correcta
                    {
                        return subs.get(i).getId();
                    }

                }

            }
        }

        return -1;
    }

    public boolean addTopico(String s) throws java.rmi.RemoteException {

        if (checkTopic(s, d) == false) {
            Topico c = new Topico();
            c.setNomeTopico(s);
            d.add(c);
            try {
                gd.guardartop(d);
                
                Socket newsocket = new Socket("172.20.10.3", 2222);
                ObjectOutputStream falar = new ObjectOutputStream(newsocket.getOutputStream());
                               
                
                falar.writeInt(4);
                falar.flush();
                falar.writeObject(s);
                falar.flush();
                
                ObjectInputStream ouvir = new ObjectInputStream (newsocket.getInputStream());
                String teste ="";
                teste = (String) ouvir.readObject();
                
               falar.close();
               ouvir.close();
               newsocket.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            return false;

        } else {
            

            return true;

        }

    }

    public boolean checkTopic(String s, ArrayList<Topico> d) throws java.rmi.RemoteException {

        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getNometopico().equals(s)) {
                return true;
            }

        }
        return false;

    }

    public ArrayList<Topico> ConsultarTopicos() throws java.rmi.RemoteException {

        return d;
    }

    @Override
    public String toString() {
        return "Array{" + d.toString();
    }

    public boolean checkTopic2(String s) throws java.rmi.RemoteException {

        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getNometopico().equals(s)) {
                return true;
            }

        }
        return false;

    }

    public boolean addNoticia(Noticias noticia, String ntopico) throws java.rmi.RemoteException {

        ArrayList<Subscritores> subscribersn = null;

        int posicao = 0;

        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getNometopico().equals(ntopico)) {
                posicao = i;
                d.get(i).addNovaNoticia(noticia);
                //System.out.println("Adicionado ao Array D a noticia " + noticia.toString());
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
        subscribersn = d.get(posicao).getSubscribers();

        for (int i = 0; i < subscribersn.size(); i++) {

            try {
          //      System.out.println("A enviar para o subs " + subscribersn.get(i).toString());
                subscribersn.get(i).getSubscribers().EscreverNoticia(noticia);
            } catch (Exception e) {
                System.out.println("Cliente offline");
            }
        }

        //verificação de backup
        int posicao2 = -1;
        
            try {
                
            if ((limite_max/2)<d.get(posicao).getNoticias().size()){
                
                Socket s = new Socket("172.20.10.3", 2222);
                ArrayList<Noticias> noticias_enviar = new ArrayList();
            
                ObjectOutputStream falar = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ouvir = new ObjectInputStream (s.getInputStream());
            
   
                falar.writeInt(3);
                falar.flush();
                
                falar.writeObject(ntopico);
                falar.flush();
            
                while (d.get(posicao).getNoticias().size() > (limite_max/2)) {
                            noticias_enviar.add(d.get(posicao).getNoticias().get(0));
                            d.get(posicao).getNoticias().remove(0);
                            gd.guardartop(d);
                           
                }
                
                falar.writeObject(noticias_enviar);
                falar.flush();
                
                s.close();
                falar.close();
                ouvir.close();
            
            }
           
        } catch (IOException ex) {
            Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return true;
    }

    public ArrayList<Noticias> ConsultarNoticisPub(int id) throws java.rmi.RemoteException {

        //System.out.println("Mostrar Topicos");
        //System.out.println(d.toString());

        ArrayList<Noticias> n;
        ArrayList<Noticias> n1 = new ArrayList();


        for (int i = 0; i < d.size(); i++) {

            System.out.println("Noticia " + d.get(i).getNoticias());
            n = d.get(i).getNoticias();
            System.out.println("N : " + n.toString());
            for (int j = 0; j < n.size(); j++) {

                if (n.get(j).getAutor() == id) {
                    n1.add(n.get(j));
                }

            }

        }
        
        try {
            Socket s = new Socket("172.20.10.3", 2222);
            
            ObjectOutputStream falar = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ouvir = new ObjectInputStream (s.getInputStream());
            ArrayList<Noticias> n3 = new ArrayList();
                    
            falar.writeInt(2);
            falar.flush();
            falar.writeInt(id);
            falar.flush();
            
            n3 = (ArrayList<Noticias>) ouvir.readObject();
            
            n1.addAll(n3);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerRMIIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return n1;
    }

    public Noticias UltimaNoticia(String nometopico) {
        ArrayList<Noticias> n = new ArrayList<Noticias>();
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).getNometopico().equals(nometopico)) {

                n = d.get(i).getNoticias();
                return n.get(n.size() - 1);

            }

        }
        return null;
    }

    public boolean subscribe(String tp, int id, SubscriberInterface subs) throws java.rmi.RemoteException {
        
        Subscritores objeto = new Subscritores();
        ArrayList<Subscritores> array = null;
        int posicao=-1;

        for (int i = 0; i < d.size(); i++) {

            if (d.get(i).getNometopico().equals(tp)) {

                array = d.get(i).getSubscribers();

                for (int j = 0; j < array.size(); j++) {
                    if (array.get(j).getIds() == id) {
                        return false;   
                        
                    }
                }
            posicao = i;   
            }
        }
            if (posicao!=-1){
                objeto.setIds(id);
                objeto.setSubscribers(subs);
                d.get(posicao).addSubcritores(objeto);
                System.out.println("Nao devia chegar aqui!");
                return true;
            }
            else 
                return false;
            
            

    }

    public void updatesubs(int id, SubscriberInterface subs) {
        ArrayList<Subscritores> array = null;

        for (int i = 0; i < d.size(); i++) {
            array = d.get(i).getSubscribers();
            for (int j = 0; j < array.size(); j++) {
                if (array.get(j).getIds() == id) {
                    array.get(j).setSubscribers(subs);
                }
            }
        }

    }

    public ArrayList<Noticias> MostarNoticiasEntreDatas(String nomeTopico, Date dataMaisRecente, Date dataMaisAntiga) throws java.rmi.RemoteException {

        ArrayList<Noticias> noticiasResult = new ArrayList<Noticias>();
        ArrayList<Noticias> noticias = new ArrayList<Noticias>();

        for (int i = 0; i < d.size(); i++)//verificar
        {
            if (d.get(i).getNometopico().equals(nomeTopico)) {

                noticias = d.get(i).getNoticias();

            }

        }

        for (int i = 0; i < noticias.size(); i++) {

            if (dataMaisRecente.before(noticias.get(i).getData()) && dataMaisAntiga.after(noticias.get(i).getData())) {
                noticiasResult.add(noticias.get(i));
            }

        }

        return noticiasResult;
    }

}
