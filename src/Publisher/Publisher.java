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

/**
 *
 * @author Fábio
 */
public class Publisher {

    private int id;
    private String nome;
    private ServerRMIInterface si;

    public Publisher(int idi, String nome, ServerRMIInterface si) {

        id = idi;
        this.si = si;
        this.nome = nome;
        ArrayList<Topico> consultar = null;

        System.out.println("Publisher: " + nome);

        try {

            while (true) {
                System.out.println("1 - Add topico \n2 - Consultar Tópicos Existentes \n3 - Inserir Noticia em Topico \n4 - Consultar  Todas as noticias Publicadas");
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

                        System.out.println("Data " + data);
                        novanoticia.setData(data);
                        novanoticia.setAutor(id);
                        novanoticia.setConteudo(s2);
                        System.out.println("Autor que criou foi o " + id);
                        si.addNoticia(novanoticia, s);
                        System.out.println("Noticia adicionada com sucesso");

                    } else {
                        System.out.println("Topic não existe");
                    }

                } else if (op == 4) {

                    ArrayList<Noticias> nconsultar;
                    System.out.println("Vamos consultar as noticias do publisher com o id " + id);
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
