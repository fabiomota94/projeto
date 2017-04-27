package Servidores;





import java.io.IOException;
import java.rmi.Naming;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD
public class ServerRMI {

    public static void main(String[] argv) throws IOException, ClassNotFoundException {

        System.setSecurityManager(new SecurityManager());
     
        try {
             
            java.rmi.registry.LocateRegistry.createRegistry(1099);

            ServerRMIIMP Server = new ServerRMIIMP();
            
            
            Naming.rebind("rmi://127.0.0.1:1099/ServerRMI", Server);
            
            System.out.println("Servidor está ONLINE");
            try {
                Server.toString();
            } catch (Exception e) {
                System.out.println("Erro no servidor " + e);
            }

        } catch (Exception e) {
            System.out.println("Erro no servidor " + e);
        }
    }
}
