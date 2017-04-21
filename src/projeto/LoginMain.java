/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import Publisher.Publisher;
import Servidores.ServerRMIInterface;
import Subs.Subscriber;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Fábio
 */
public class LoginMain implements Serializable {

    public int tipo;
    public String nome;
    public String pass;
    public int id = 0;

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
        

        
        int idi = 0;
        int flag3=0;
        LerFicheiro LF = new LerFicheiro();
        
        idi = LF.LerID();
        
        System.setSecurityManager(new SecurityManager());

        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");
        

        

        while (true) {
            
            System.out.println("1 - Registar \n2 - Login \n3 - Subscribers sem registo \n0 - Sair");
            int opcaologin = Ler.umInt(); //Ler a opçao de cima
            
            
            System.out.println("Opcao no login "+ opcaologin);
            
            if (opcaologin == 1) {
                
                int op;
                String user = "";
                String pw = "";
                
                System.out.println("1 - Publisher \n2 - Subscribers");
                
                op = Ler.umInt();
                System.out.println("Username:");
                user = Ler.umaString();
                System.out.println("Passowrd:");
                pw = Ler.umaString();
                

                if (op == 1) {
                    
                    //registar publisher do lado do servidor;
                    if (si.createUser(user, pw, op, idi, op))
                        System.out.println("Registado como publisher!");
                    else
                        System.out.println("User não registado!");
                    
                }
                if (op == 2) {
                    
                    //registar subscribers do lado do servidor;
                    if (si.createUser(user, pw, op, idi, op))
                        System.out.println("Registado com subscriber!");
                    else
                        System.out.println("User não registado!");
                    
                }

            }
        
            
            if (opcaologin == 2) {
                
                int op;
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
                    System.out.println("Password?");
                    password = Ler.umaString();

                    si.LOGIN(user, password, id, id, op,si);

                }
                
            } 
            else if (opcaologin == 3) {
                    Subscriber sub = new Subscriber(3);
          }
            else if (opcaologin == 0)
            {
                
                    //System.out.println("A = 0 ");
                    flag3++;
                     break;
                    
            }  
    }
            

    
     } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
}
