/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Fábio
 */
public class ServerRMIIMP extends UnicastRemoteObject implements ServerRMIInterface{
    
    public ServerRMIIMP(String name) throws RemoteException{
        super(); 
   
        try {
        
            Naming.rebind(name, this); 
        }
        
        catch (Exception e) {
            
            if (e instanceof RemoteException)throw (RemoteException)e; 
            
            else
                
                throw new RemoteException(e.getMessage()); }
    }
    
    
}
