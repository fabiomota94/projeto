
package BackUpServer;

import Classes.Topico;
import Ficheiros.LerFicheiro;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public class ServidorBackup {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket servers = null;
        Socket s;
        Connection t;
        ArrayList<Topico> topicosbackup = new ArrayList();
        LerFicheiro lf = new LerFicheiro();

        // ler no ficheiro ficheiro back
        topicosbackup = lf.LerBackup();
        System.out.println(topicosbackup.toString()); //verificação apenas, apagar

        servers = new ServerSocket(2222);
        System.out.println("------Backup Server Online------\n");

        try {
            while (true) {

                s = servers.accept();
                t = new Connection(s, topicosbackup);

            }
        } catch (IOException e) {

            System.out.println("Erro!");
        }
    }

}
