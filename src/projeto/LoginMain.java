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
public class LoginMain implements Serializable{
    private int tipo;
    private String nome;
    private String pass;
    private static int id=1;
    
    public LoginMain()
    {
        
        tipo = 0;
        nome = "";
        pass = "";
        id = id; // acho que temos de mandar o nome tambem
        
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

    public void setId(int id) {
        LoginMain.id = id;
    }

    @Override
    public String toString() {
        return "LoginMain{" + "tipo=" + tipo + ", nome=" + nome + ", pass=" + pass + '}';
    }
    
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Publisher p= new Publisher();
        
        ArrayList<LoginMain> pub = new ArrayList(); //array de Publishers
        ArrayList<LoginMain> subs = new ArrayList(); //array de subscribers
        
        LerFicheiro LF = new LerFicheiro(); 
        pub = LF.LerFilePublishers();
        subs = LF.LerFileSubscribers();
        
        
        
        System.out.println(pub.toString()); //verificação apagar depois....
        System.out.println(subs.toString());
        
        while(true){
        System.out.println("1 - Registar \n2 - Login \n3 - Subscribers sem registo \n0 - Sair");
        int a = Ler.umInt(); //Ler a opçao de cima
        int op;
        if(a==1){
            
            String user ="";
            String pw = "";
            System.out.println("1 - Publisher \n2 - Subscribers");
            op = Ler.umInt();
            System.out.println("Username:");    
            user = Ler.umaString();
            System.out.println("Passowrd:");
            pw = Ler.umaString();
            GuardarDados gd = new GuardarDados();
            
            if(op==1){
                LoginMain publishers = new LoginMain();
                publishers.setNome(user);
                publishers.setPass(pw);
                publishers.setTipo(1);
                publishers.setId(id);
                pub.add(publishers);
                System.out.println("Registado como Publisher");
                gd.SaveFilePublishers(pub);
                id++;
                Publisher p= new Publisher(id);
                
            }
            if(op==2){
                LoginMain subscribers = new LoginMain();
                subscribers.setNome(user);
                subscribers.setPass(pw);
                subscribers.setTipo(2);
                subscribers.setId(id);
                subs.add(subscribers);
                System.out.println("Registado como consumidor");
                gd.SaveFile_Consumers(subs);
                id++;
                Subscriber sub = new Subscriber();
            }
           
           
        }
        if(a==2){
            System.out.println("1 - Publisher 2 - Consumidor");
            op = Ler.umInt();   
        
        
        }
        if(a==3){
           
        
        
        }
        if(a==0){
            break;
        }
      }
    }
}
