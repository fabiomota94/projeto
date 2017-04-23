/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Fábio
 */
public class ServerRMI {

    public static void main(String[] argv) {

        System.setSecurityManager(new SecurityManager());

        try { //Iniciar a execução do registry no porto desejado 

            java.rmi.registry.LocateRegistry.createRegistry(1099);

            //System.out.println("RMI registry ready.");

            ServerRMIIMP Server = new ServerRMIIMP();
            Naming.rebind("rmi://127.0.0.1:1099/ServerRMI", Server);
            System.out.println("Servidor está ON");
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
