/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import Publisher.Publisher;
import Subs.Subscriber;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fábio
 */
public class LoginMain implements Serializable {

    private int tipo;
    private String nome;
    private String pass;
    private int id = 0;

    public LoginMain() {

        tipo = 0;
        nome = "";
        pass = "";
        id++; // acho que temos de mandar o nome tambem

    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int idi) {
        this.id = idi;
    }

    @Override
    public String toString() {
        return "LoginMain{" + "tipo=" + tipo + ", nome=" + nome + ", pass=" + pass + " id " + id + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Publisher p= new Publisher();

        ArrayList<LoginMain> pub = new ArrayList(); //array de Publishers
        ArrayList<LoginMain> subs = new ArrayList(); //array de subscribers
        int idi = 0;
        LerFicheiro LF = new LerFicheiro();
        pub = LF.LerFilePublishers();
        subs = LF.LerFileSubscribers();
        idi = LF.LerID();

        System.out.println(pub.toString()); //verificação apagar depois....
        System.out.println(subs.toString());

        while (true) {
            System.out.println("1 - Registar \n2 - Login \n3 - Subscribers sem registo \n0 - Sair");
            int a = Ler.umInt(); //Ler a opçao de cima
            int op;
            if (a == 1) {

                String user = "";
                String pw = "";
                System.out.println("1 - Publisher \n2 - Subscribers");
                op = Ler.umInt();
                System.out.println("Username:");
                user = Ler.umaString();
                System.out.println("Passowrd:");
                pw = Ler.umaString();
                GuardarDados gd = new GuardarDados();

                if (op == 1) {
                    LoginMain publishers = new LoginMain();
                    publishers.setNome(user);
                    publishers.setPass(pw);
                    publishers.setTipo(1);
                    publishers.setId(idi);
                    pub.add(publishers);
                    System.out.println("Registado como Publisher");
                    gd.SaveFilePublishers(pub);
                    idi++;
                    gd.SaveFile_id(idi);

                }
                if (op == 2) {
                    LoginMain subscribers = new LoginMain();
                    subscribers.setNome(user);
                    subscribers.setPass(pw);
                    subscribers.setTipo(2);
                    subscribers.setId(idi);
                    subs.add(subscribers);
                    System.out.println("Registado como consumidor");
                    gd.SaveFile_Consumers(subs);

                    idi++;
                    System.out.println("OK");
                    gd.SaveFile_id(idi);
                }

            }
            if (a == 2) {
                String user = "";
                String password = "";
                int flag = 0;
                int id = 0;
                System.out.println("1 - Publisher \n2 - Subscriber");
                op = Ler.umInt();
                if (op == 1) {
                   flag=0;
                    System.out.println("Username?");
                    user = Ler.umaString();

                    for (int i = 0; i < pub.size(); i++) {

                         if(pub.get(i).nome.equals(user))//user encontrado
                         {
                             System.out.println("Password?");
                             password = Ler.umaString();
                             if(pub.get(i).pass.equals(password))
                             {
                               id = pub.get(i).id;
                               flag++;  
                             }
                             else
                             {
                                 System.out.println("Password errada");
                             }
                         } 

                    }

                    if (flag > 0) {
                        System.out.println("ID DO PUBLISHER  "+ id);
                        Publisher p = new Publisher(id);
                    }

                }
                if (op == 2) {
                    //LOGIN
                    flag=0;
                    System.out.println("Username?");
                    user = Ler.umaString();

                    for (int i = 0; i < subs.size(); i++) {
                        
                        if(subs.get(i).nome.equals(user))//user encontrado
                         {
                             System.out.println("Password?");
                             password = Ler.umaString();
                             if(subs.get(i).pass.equals(password))
                             {
                               //id = pub.get(i).id;
                               flag++;  
                             }
                             else
                             {
                                 System.out.println("Password errada");
                             }
                         } 

                    }
                    if (flag > 0) {
                        Subscriber sub = new Subscriber();

                    }
                }
            } 
                if (a == 3) {

                }
                if (a == 0) {
                      break;
                }         

    }
}
}
