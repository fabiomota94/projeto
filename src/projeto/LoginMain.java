package projeto;

import Ficheiros.GuardarDados;
import Ficheiros.LerFicheiro;
import Publisher.Publisher;
import Servidores.ServerRMIInterface;
import Subs.Subscriber;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.rmi.Naming;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD
public class LoginMain implements Serializable {

    public int tipo;
    public String nome;
    public String pass;
    public int id = 0;

    public LoginMain() {

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
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginMain{" + "tipo=" + tipo + ", nome=" + nome + ", pass=" + pass + " id " + id + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        LerFicheiro LF = new LerFicheiro();
        GuardarDados GD = new GuardarDados();

        System.setSecurityManager(new SecurityManager());

        try {
            ServerRMIInterface si = (ServerRMIInterface) Naming.lookup("rmi://127.0.0.1:1099/ServerRMI");

            while (true) {
                System.out.println("--------Bemvindo--------");
                System.out.println("| 1 - Registar \n| 2 - Login \n| 3 - Subscribers sem registo \n| 0 - Sair\n----------------------");
                int opcaologin = Ler.umInt(); //Ler a opçao de cima

                if (opcaologin == 1) { //Registar
                    System.out.println("------Registar------\n");
                    int tipo;
                    String user = "";
                    String pw = "";

                    System.out.println("1 - Publisher \n2 - Subscribers");
                    tipo = Ler.umInt();

                    if (tipo == 1) {

                        System.out.println("Username:");
                        user = Ler.umaString();
                        if (si.checkuser(user, tipo) == false) {

                            System.out.println("Passowrd:");
                            pw = Ler.umaString();

                            if (si.createUser(user, pw, tipo)) {

                                System.out.println("Registado como publisher!"); //registar publisher do lado do servidor;
                            } else {
                                System.out.println("User não registado!");
                            }
                        } else {
                            System.out.println("User já registado!");
                        }

                    }
                    if (tipo == 2) {

                        System.out.println("Username:");
                        user = Ler.umaString();

                        if (si.checkuser(user, tipo) == false) {

                            System.out.println("Passowrd:");
                            pw = Ler.umaString();

                            if (si.createUser(user, pw, tipo)) {

                                System.out.println("Registado como subscriber!"); //registar subscriber do lado do servidor;
                            } else {
                                System.out.println("User não registado!");
                            }
                        } else {
                            System.out.println("User já registado!");
                        }

                    }
                }

                if (opcaologin == 2) {//Login
                    System.out.println("------Login------\n");
                    int tipouser;
                    String user = "";
                    String password = "";

                    int id = 0;
                    Publisher p;
                    Subscriber s = null;

                    System.out.println("1 - Publisher \n2 - Subscriber");
                    tipouser = Ler.umInt(); //tipo de utilizador

                    if (tipouser == 1) {
                        System.out.println("Username?");
                        user = Ler.umaString();
                        System.out.println("Password?");
                        password = Ler.umaString();

                        if ((id = si.LOGIN(user, password, tipouser)) != -1) //se for diferente -1
                        {
                            p = new Publisher(id, user, (ServerRMIInterface) si);
                        } else {
                            System.out.println("Dados Errados de Login");
                        }

                    }

                    if (tipouser == 2) {
                        System.out.println("Username?");
                        user = Ler.umaString();
                        System.out.println("Password?");
                        password = Ler.umaString();

                        if ((id = si.LOGIN(user, password, tipouser)) != -1) //se for diferente de -1
                        {

                            s = new Subscriber(user, id, (ServerRMIInterface) si);

                        } else {
                            System.out.println("Dados Errados de Login");
                        }

                    }

                } else if (opcaologin == 3) {
                    Subscriber sub = new Subscriber(3, si);
                } else if (opcaologin == 0) {

                    exit(0);

                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
