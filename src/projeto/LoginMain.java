/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import Publisher.Publisher;
import java.util.ArrayList;

/**
 *
 * @author Fábio
 */
public class LoginMain {
    private String tipo;
    private String nome;
    private String pass;
    private static int id=0;
    
    public LoginMain()
    {
        
        tipo = "";
        nome = "";
        pass = "";
       
        
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
    
    
    public static void main(String[] args) {
        //Publisher p= new Publisher();
        ArrayList<LoginMain> p1 = new ArrayList();
        ArrayList<LoginMain> c1 = new ArrayList();
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
                System.out.println("Registado como Publisher");
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
