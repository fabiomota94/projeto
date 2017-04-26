
package BackUpServer;

import Classes.Noticias;
import Classes.Topico;
import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public class Connection extends Thread {

    private Socket S = null;
    ArrayList<Topico> tpbackup = new ArrayList();
    GuardarDados gd = new GuardarDados();
    LerFicheiro lf = new LerFicheiro();

    public Connection(Socket s, ArrayList<Topico> topicosbackup) throws IOException, ClassNotFoundException {

        super();

        tpbackup = lf.LerBackup();
        S = s;
        tpbackup = topicosbackup;
        System.out.println(tpbackup.toString()); //só para verificação
        start();

    }

    public void run() {

        
        
        
        try {
            
            ObjectInputStream ouvir = new ObjectInputStream(S.getInputStream());
            ObjectOutputStream falar = new ObjectOutputStream(S.getOutputStream());
            
            
            int opcao = (int) ouvir.readInt();
            
            System.out.println("Opção:" + opcao);
            
            if(opcao == 1){ //verificar datas no backup
            
            
            String nomeTopico = (String) ouvir.readObject(); //ouve o nome do topico
            
            ArrayList<Noticias> resultado_filtrado = filtraNoticias_de_um_topico(nomeTopico);
            ArrayList<Noticias> resultado_final = new ArrayList();
            
            
            Date dataRecente = (Date) ouvir.readObject(); //ouve a Data 

            Date dataVelha = (Date) ouvir.readObject(); //Ouve a segunda data 
                  

            resultado_final = MostarNoticiasEntreData0_backups(resultado_filtrado, dataRecente, dataVelha);

            // escreve as noticias q tem a escrever 
            
            falar.writeObject(resultado_final);
            falar.flush();

            }
            
        if (opcao==2){ //verificar noticias de um publisher já em backup
             
            ArrayList<Noticias> aux = new ArrayList();
            ArrayList<Noticias> aux1 = null;
             
             int id = ouvir.readInt();
             
             for (int i = 0; i < tpbackup.size(); i++) {
                 
                 aux1 = tpbackup.get(i).getNoticias();
                 System.out.println(aux1.toString());
                 if(!aux1.isEmpty()){
                    for (int j = 0; j < aux1.size(); j++) {
                     
                        if(aux1.get(j).getAutor()==id){
                     
                        aux.add(aux1.get(j));
                        }
                     
                    }
                }     
                 
            }
            
             falar.writeObject(aux);
             falar.flush();
             
             System.out.println("ULTIMA LINHA OPCAO 2: " + tpbackup.toString());
 
        }
        
        if (opcao==3){ //adicionar noticias no backup
             
            
             
             String ntopico = (String) ouvir.readObject();
             
             ArrayList<Noticias> noticias_receber = new ArrayList();
             
             noticias_receber = (ArrayList<Noticias>) ouvir.readObject();
             
             for (int i = 0; i < tpbackup.size(); i++) {
                 
                 if(tpbackup.get(i).getNometopico().equals(ntopico))
                     
                    tpbackup.get(i).getNoticias().addAll(noticias_receber);
                     
             }
             
             System.out.println("OPCAO 3: " + tpbackup.toString());
        }
        
        if (opcao==4){ //adicionar topico no ficheiro de backup
             
             String ntopico = (String) ouvir.readObject();
             
             Topico tp = new Topico();
             
             tp.setNomeTopico(ntopico);
             
             tpbackup.add(tp);
             
             gd.guardarbackup(tpbackup);
             
             falar.writeObject("ok");
             
             System.out.println("OPCAO 4: " + tpbackup.toString());
        }
                 
        
        ouvir.close();
        falar.close();
        S.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Noticias> filtraNoticias_de_um_topico(String nomeTopico) {

        //System.out.println("NOME TOPICO "  + nomeTopico );
        ArrayList<Noticias> resultado = new ArrayList();

        for (int i = 0; i < tpbackup.size(); i++) {
            //  System.out.println("SOUT DENTRO DA FUNCAO " + tpbackup.get(i).getNometopico());
            if (tpbackup.get(i).getNometopico().equals(nomeTopico)) {

                //System.out.println("DEVERIA PASSAR NO if ");
                System.out.println(tpbackup.get(i).getNoticias().size());

                for (int j = 0; j < tpbackup.get(i).getNoticias().size(); j++) {

                    resultado.add(tpbackup.get(i).getNoticias().get(j));

                    //  System.out.println("PRINT TESTE " + tpbackup.get(i).getNoticias().get(j));
                }

            }
        }

        return resultado;
    }

    public ArrayList<Noticias> MostarNoticiasEntreData0_backups(ArrayList<Noticias> array_de_noticias, Date dataMaisRecente, Date dataMaisAntiga) {

        ArrayList<Noticias> noticiasResult = new ArrayList<Noticias>();

        for (int i = 0; i < array_de_noticias.size(); i++) {

            if (dataMaisRecente.before(array_de_noticias.get(i).getData()) && dataMaisAntiga.after(array_de_noticias.get(i).getData())) {
                noticiasResult.add(array_de_noticias.get(i));
            }

        }

        return noticiasResult;
    }

}
