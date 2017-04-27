package Subs;

import Classes.Noticias;
import Servidores.ServerRMIInterface;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import projeto.Ler;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD
public class Subscriber extends java.rmi.server.UnicastRemoteObject implements SubscriberInterface, Serializable {

    private int id;
    private int tipo;
    private String nome;
    public String serverName;
    private ServerRMIInterface si;

    public Subscriber() throws RemoteException {

        super();
        this.si = si;
        this.tipo = tipo;
        this.id = id;
        this.nome = nome;

    }

    public void EscreverNoticia(Noticias n) throws java.rmi.RemoteException {

        System.out.println(n.toString());

    }

    public Subscriber(int tipo, ServerRMIInterface si) throws RemoteException { //SEM REGISTO

        this.si = si;

        try {

            while (true) {
                System.out.println("------Subscriber sem Registo------\n");
                int opcao = 0;
                Noticias noticia;
                System.out.println("1 - Consultar noticia de um topico(entre datas)");
                System.out.println("2 - Consultar a ultima noticia do topico");
                System.out.println("0 - Sair");
                opcao = Ler.umInt();
                if (opcao == 1) {
                    System.out.println("Indique o Topico onde vai procurar as Noticias.");
                    String nomeTopico1 = Ler.umaString();
                    boolean verificador;

                    verificador = si.checkTopic2(nomeTopico1);

                    if (verificador == false) {
                        System.out.println("Topico nao existente!");
                        continue;
                    }

                    Date dataMaisRecente = null;
                    Date dataMaisVelha = null;
                    ArrayList<Noticias> noticiasResultado = new ArrayList();
                    ArrayList<Noticias> noticiasResultado_backup = new ArrayList();

                    System.out.println("Indique o ANO da data mais Antiga:");
                    int ano = Ler.umInt() - 1900;

                    System.out.println("Indique o MES da data mais Antiga:");
                    int mes = Ler.Mes() - 1;

                    System.out.println("Indique o DIA da data mais Antiga:");
                    int dia = Ler.dia();

                    System.out.println("Indique a HORA da data mais Antiga:");
                    int hrs = Ler.umInt();

                    System.out.println("Indique o MINUTO da data mais Antiga:");
                    int min = Ler.umInt();

                    dataMaisRecente = new Date(ano, mes, dia, hrs, min);

                    System.out.println("Indique o ANO da data mais Recente:");
                    int ano2 = Ler.umInt() - 1900;//rever

                    System.out.println("Indique o MES da data mais Recente:");
                    int mes2 = Ler.Mes() - 1;

                    System.out.println("Indique o DIA da data mais Recente:");
                    int dia2 = Ler.dia();

                    System.out.println("Indique a HORA da data mais Recente:");
                    int hrs2 = Ler.umInt();

                    System.out.println("Indique o MINUTO da data mais Recente:");
                    int min2 = Ler.umInt();

                    dataMaisVelha = new Date(ano2, mes2, dia2, hrs2, min2);

                    noticiasResultado = si.MostarNoticiasEntreDatas(nomeTopico1, dataMaisRecente, dataMaisVelha);
                    String ip = si.ipdobackup();
                    int port = si.portdobackup();
                   
                    
                    Socket subsSocket = new Socket(ip,port);

                    ObjectOutputStream falar = new ObjectOutputStream(subsSocket.getOutputStream());

                    falar.writeInt(1);
                    falar.flush();

                    falar.writeObject(nomeTopico1);
                    falar.flush();

                    falar.writeObject(dataMaisRecente);
                    falar.flush();

                    falar.writeObject(dataMaisVelha);
                    falar.flush();

                    ObjectInputStream ouvir;
                    ouvir = new ObjectInputStream(subsSocket.getInputStream());

                    noticiasResultado_backup = (ArrayList<Noticias>) ouvir.readObject();

                    ouvir.close();
                    falar.close();
                    subsSocket.close();

                    if (noticiasResultado.isEmpty() && noticiasResultado_backup.isEmpty()) {
                        System.out.println("Sem noticias para serem mostradas entre essas datas.");
                        continue;
                    }
                    if (!noticiasResultado.isEmpty()) {
                        System.out.println(noticiasResultado.toString());
                    }
                    if (!noticiasResultado_backup.isEmpty()) {
                        System.out.println("Backup: " + noticiasResultado_backup.toString());
                    }

                } else if (opcao == 2) {
                    System.out.println("Indique o topico");
                    String nometopico = Ler.umaString();
                    noticia = si.UltimaNoticia(nometopico);

                    if (noticia != null) {
                        System.out.println(noticia.toString());

                    } else {
                        System.out.println("Topico Inválido ou sem noticas ainda!");
                    }
                } else if (opcao == 0) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } //SEM REGISTO ACABA AQUI!!

    public Subscriber(String nome, int idi, ServerRMIInterface si) throws RemoteException { //COM REGISTO

        try {

            Subscriber subupdate = new Subscriber();
            si.updatesubs(idi, subupdate);

            while (true) {

                Noticias noticia;
                System.out.println("------Subscriber: " + nome + " ------\n");
                System.out.println("1 - Subscrever um tópico");
                System.out.println("2 - Consultar Noticia de um topico (Entre datas)");
                System.out.println("3 - Consultar a Ultima noticia do Topico");
                System.out.println("0 - Sair");
                int opcao = Ler.umInt();

                if (opcao == 1) {
                    System.out.println("1 - Topico que pretende subscrever?");
                    String nt = Ler.umaString();
                    Subscriber sub = new Subscriber();

                    if (si.subscribe(nt, idi, (SubscriberInterface) sub)) {
                        System.out.println("Topico Subscrito");
                    } else {
                        System.out.println("Topico já subscrito ou não existe");
                    }

                } else if (opcao == 2) {

                    System.out.println("Indique o Topico onde vai procurar as Noticias.");
                    String nomeTopico1 = Ler.umaString();
                    boolean verificador;

                    verificador = si.checkTopic2(nomeTopico1);

                    if (verificador == false) {
                        System.out.println("Topico nao existente!");
                        continue;
                    }

                    Date dataMaisRecente = null;
                    Date dataMaisVelha = null;
                    ArrayList<Noticias> noticiasResultado = new ArrayList();
                    ArrayList<Noticias> noticiasResultado_backup = new ArrayList();

                    System.out.println("Indique o ANO da data mais Antiga:");
                    int ano = Ler.umInt() - 1900;

                    System.out.println("Indique o MES da data mais Antiga:");
                    int mes = Ler.Mes() - 1;

                    System.out.println("Indique o DIA da data mais Antiga:");
                    int dia = Ler.dia();

                    System.out.println("Indique a HORA da data mais Antiga:");
                    int hrs = Ler.umInt();

                    System.out.println("Indique o MINUTO da data mais Antiga:");
                    int min = Ler.umInt();

                    dataMaisRecente = new Date(ano, mes, dia, hrs, min);

                    System.out.println("Indique o ANO da data mais Recente:");
                    int ano2 = Ler.umInt() - 1900;//rever

                    System.out.println("Indique o MES da data mais Recente:");
                    int mes2 = Ler.Mes() - 1;

                    System.out.println("Indique o DIA da data mais Recente:");
                    int dia2 = Ler.dia();

                    System.out.println("Indique a HORA da data mais Recente:");
                    int hrs2 = Ler.umInt();

                    System.out.println("Indique o MINUTO da data mais Recente:");
                    int min2 = Ler.umInt();

                    dataMaisVelha = new Date(ano2, mes2, dia2, hrs2, min2);

                    noticiasResultado = si.MostarNoticiasEntreDatas(nomeTopico1, dataMaisRecente, dataMaisVelha);
                    String ip = si.ipdobackup();
                    int port = si.portdobackup();
                   
                    
                    Socket subsSocket = new Socket(ip,port);
                    

                    ObjectOutputStream falar = new ObjectOutputStream(subsSocket.getOutputStream());

                    falar.writeInt(1);
                    falar.flush();

                    falar.writeObject(nomeTopico1);
                    falar.flush();

                    falar.writeObject(dataMaisRecente);
                    falar.flush();

                    falar.writeObject(dataMaisVelha);
                    falar.flush();

                    ObjectInputStream ouvir;
                    ouvir = new ObjectInputStream(subsSocket.getInputStream());

                    noticiasResultado_backup = (ArrayList<Noticias>) ouvir.readObject();

                    ouvir.close();
                    falar.close();
                    subsSocket.close();

                    if (noticiasResultado.isEmpty() && noticiasResultado_backup.isEmpty()) {
                        System.out.println("Sem noticias para serem mostradas entre essas datas.");
                        continue;
                    }
                    if (!noticiasResultado.isEmpty()) {
                        System.out.println(noticiasResultado.toString());
                    }
                    if (!noticiasResultado_backup.isEmpty()) {
                        System.out.println("Backup:" + noticiasResultado_backup.toString());
                    }

                } else if (opcao == 3) {
                    System.out.println("Indique o topico");
                    String nometopico = Ler.umaString();
                    noticia = si.UltimaNoticia(nometopico);
                    if (noticia != null) {
                        System.out.println(noticia.toString());
                    } else {
                        System.out.println("Topico Inválido ou sem noticas ainda!");
                    }
                } else if (opcao == 0) {
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
