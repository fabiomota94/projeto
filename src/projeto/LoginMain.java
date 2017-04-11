/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import Publisher.Publisher;
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
        id = id;
        
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
        ArrayList<LoginMain> p1 = new ArrayList();
        
        ArrayList<LoginMain> c1 = new ArrayList();
        LerFicheiro LF = new LerFicheiro(); 
         p1= LF.LerFilePublishers();
        System.out.println(p1.toString());
        
        while(true)
        {
        System.out.println("1 - Registar \n2 - LOG \n3 - Consumidor sem registo \n 0 - Sair");
        int a = Ler.umInt(); //Ler a opçao de cima
         int op;
        if(a==1)
        {
            String user ="";
            String pw = "";
            System.out.println("1 - Publisher 2 - Consumidor");
            op = Ler.umInt();
            System.out.println("User?");    
            user = Ler.umaString();
            System.out.println("Pw ?");
            pw = Ler.umaString();
            if(op == 1 )
            {
                LoginMain publishers = new LoginMain();
                GuardarDados gd = new GuardarDados();
                publishers.setNome(user);
                publishers.setPass(pw);
                publishers.setTipo(1);
                publishers.setId(id);
                p1.add(publishers);
                System.out.println("Registado como Publisher");
                gd.SaveFilePublishers(p1);
                id++;
                Publisher p= new Publisher(id);
                
            }
            if(op == 2)
            {
                System.out.println("Registado como consumidor");
            }
           
           
        }
        if(a==2)
        {
            System.out.println("1 - Publisher 2 - Consumidor");
            op = Ler.umInt();
           
        }
        if(a==3)
        {
           
        }
        if(a==0)
        {
            break;
        }
      }
    }
}
