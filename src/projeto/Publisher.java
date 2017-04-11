/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.Serializable;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Fábio
 */
public class Publisher {

    public static void main(String[] args) {

        ArrayList<Topico> consultar;
        System.setSecurityManager(new SecurityManager());
        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");
            //Publisher p = new Publisher();

            while (true) {
                System.out.println("Publisher\n1 - Add topico \n2 - Consultar Tópicos Existentes \n3 - Inserir Noticia em Topico \n4 - Consultar  Todas as noticias Publicadas");
                System.out.println("0 - Sair");
                int op = Ler.umInt();
                if (op == 1) {
                    String s = "";
                    System.out.println("Nome do topico ? ");
                    s = Ler.umaString();
                    if (si.addTopico(s) == true) {
                        System.out.println("Já existe este topico");
                        continue;
                    }
                    System.out.println("Topico adicionado");

                } else if (op == 2) {
                    consultar = si.ConsultarTopicos();
                    System.out.println("Topicos Existentes:");
                    for (int i = 0; i < consultar.size(); i++) {
                        System.out.println(consultar.get(i).getNometopico());
                    }
                } else if (op == 3) {
                    String s = "";
                    String s2 = "";
                    GregorianCalendar data = new GregorianCalendar();
                    int dia, mes;
                    Noticias novanoticia = new Noticias();
                    System.out.println("Topico para adicionar noticia");
                    s = Ler.umaString();
                    if (si.checkTopic2(s) == true) {
                        System.out.println("Escreva a noticia");
                        s2 = Ler.umaString();
                        novanoticia.setConteudo(s2);
                        System.out.println("Dia da publicação?");
                        novanoticia.setDia(dia = Ler.dia());
                        System.out.println("Mes da publicação?");
                        novanoticia.setDia(mes = Ler.Mes());
                        si.addNoticia(novanoticia, s);
                        System.out.println("Noticia adicionada com sucesso");
                        //data.get(Calendar.YEAR); para adicionar com gregorian
                    }
                    else
                        System.out.println("Topic não existe");
                    
                    
                } else if (op == 4) {
                    

                    
                } 
                else {
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
