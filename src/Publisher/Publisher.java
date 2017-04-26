/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publisher;

import Classes.Noticias;
import Classes.Topico;
import Servidores.ServerRMIInterface;
import java.util.ArrayList;
import java.util.Date;
import projeto.Ler;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public class Publisher {

    private int id;
    private String nome;
    private ServerRMIInterface si;

    public Publisher(int idi, String nome, ServerRMIInterface si) {

        id = idi;
        this.si = si;
        this.nome = nome;
        ArrayList<Topico> consultar = null;


        try {

            while (true) {
                System.out.println("------Publisher: " + nome + " ------\n");
                System.out.println("1 - Add topico \n2 - Consultar Tópicos Existentes \n3 - Inserir Noticia em Topico \n4 - Consultar Todas as noticias Publicadas");
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

                    Noticias novanoticia = new Noticias();
                    System.out.println("Topico para adicionar noticia");
                    s = Ler.umaString();
                    if (si.checkTopic2(s) == true) {
                        System.out.println("Escreva a noticia");
                        s2 = Ler.umaNoticia();
                        Date data = new Date();

                        
                        novanoticia.setData(data);
                        novanoticia.setAutor(id);
                        novanoticia.setConteudo(s2);
                        si.addNoticia(novanoticia, s);
                        System.out.println("Noticia adcionada com sucesso por: " + nome + " em " + data);
                        

                    } else {
                        System.out.println("Topico não existe");
                    }

                } else if (op == 4) {

                    ArrayList<Noticias> nconsultar;
                    System.out.println("Todas as noticias do Publisher: " + nome);
                    nconsultar = si.ConsultarNoticisPub(id);

                    System.out.println(nconsultar.toString());

                } else if (op == 0) {
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
