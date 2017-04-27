
package Ficheiros;

import Classes.Topico;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import projeto.LoginMain;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public class LerFicheiro {

    public ArrayList<LoginMain> LerFilePublishers() throws IOException, ClassNotFoundException {
        ArrayList<LoginMain> p1 = new ArrayList();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("publishers.dat"));
            p1 = (ArrayList<LoginMain>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        return p1;

    }

    public int LerID() throws IOException, ClassNotFoundException {

        int idi = 0;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ids.dat"));

            idi = ois.readInt();
            ois.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return idi;

    }

    public ArrayList<LoginMain> LerFileSubscribers() throws IOException, ClassNotFoundException {

        ArrayList<LoginMain> subs = new ArrayList();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("subscribers.dat"));
            subs = (ArrayList<LoginMain>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        return subs;

    }

    public ArrayList<Topico> LerTopico() throws IOException, ClassNotFoundException {

        ArrayList<Topico> tops = new ArrayList();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("topicos.dat"));
            tops = (ArrayList<Topico>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        return tops;

    }

    public ArrayList<Topico> LerBackup() throws IOException, ClassNotFoundException {

        ArrayList<Topico> tops = new ArrayList();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("topicosbackup.dat"));
            tops = (ArrayList<Topico>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        return tops;

    }

    public int LerLimite() throws IOException, ClassNotFoundException {

        File ficheiro1 = new File("limite.txt");
        int max;

        Scanner scanner = new Scanner(ficheiro1);

        max = scanner.nextInt();

        return max;
    }
    
    public String Lerip() throws IOException, ClassNotFoundException {

        File ficheiro1 = new File("config.txt");
        String ip;

        Scanner scanner = new Scanner(ficheiro1);

        ip= scanner.next();
      
        return ip;
    }
    
     public int LerPort() throws IOException, ClassNotFoundException {

         System.out.println("asdasd");
        FileInputStream stream = new FileInputStream("config.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        System.out.println("1 "+ linha);
        String linha2 = br.readLine();
        System.out.println("2 "+ linha2);
        
        int max = Integer.parseInt(linha2);
        
        

       
        return max;
    }
}
