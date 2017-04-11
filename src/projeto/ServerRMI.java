/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author Fábio
 */
public class ServerRMI {
  
    public static void main(String[] argv) {
        
        System.setSecurityManager(new SecurityManager());
        
        try { //Iniciar a execução do registry no porto desejado 
            
            java.rmi.registry.LocateRegistry.createRegistry(1099); 
            System.out.println("RMI registry ready.");

        } catch (Exception e) {
            System.out.println("Exception starting RMI registry:"); 
            e.printStackTrace();
        }
        try {
            ServerRMIIMP implementaInterface = new ServerRMIIMP("ServerRMIMP"); 
            System.out.println("Servidor está OK");
        }
        catch (Exception e) {
            System.out.println("Erro no servidor " + e); }
    } 
}
