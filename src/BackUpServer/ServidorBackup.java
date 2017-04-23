/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUpServer;

import Classes.Topico;
import Ficheiros.LerFicheiro;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author TiagoMartins
 */
public class ServidorBackup {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket servers = null;
        Socket s;
        Connection t;
        ArrayList<Topico> topicosbackup = new ArrayList();
        LerFicheiro lf = new LerFicheiro();

        // ler no ficheiro ficheiro back
        topicosbackup = lf.LerBackup();

        servers = new ServerSocket(2222);

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
