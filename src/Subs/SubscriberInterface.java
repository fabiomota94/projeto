/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subs;

import Classes.Noticias;

/**
 *
 * @author Fábio
 */
public interface SubscriberInterface extends java.rmi.Remote {
    
    public void EscreverNoticia (Noticias n) throws java.rmi.RemoteException;
    
}
