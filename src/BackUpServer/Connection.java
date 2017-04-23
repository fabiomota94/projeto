
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
        start();

    }

    public void run() {

        
        try {

            /**
             * O server vai ouvir primeiro, e aqui vem o nome do Topico onde
             * queremos procurar as noticias *
             */
            ObjectInputStream ois = new ObjectInputStream(S.getInputStream());
            String nomeTopico = (String) ois.readObject();
            ArrayList<Noticias> resultado_filtrado = filtraNoticias_de_um_topico(nomeTopico);
            ArrayList<Noticias> resultado_final = new ArrayList();
            /**
             * ouve a Data *
             */
            ObjectInputStream ois2 = new ObjectInputStream(S.getInputStream());
            Date dataRecente = (Date) ois2.readObject();

            /**
             * Ouve a segunda data *
             */
            ObjectInputStream ois3 = new ObjectInputStream(S.getInputStream());
            Date dataVelha = (Date) ois3.readObject();

            resultado_final = MostarNoticiasEntreData0_backups(resultado_filtrado, dataRecente, dataVelha);

            /**
             * escre as noticias q tem a escrever *
             */
            ObjectOutputStream oos = new ObjectOutputStream(S.getOutputStream());
            oos.writeObject(resultado_final);
            oos.flush();

            ois.close();
            oos.flush();
            oos.close();

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
