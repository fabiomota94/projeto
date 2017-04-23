
package Subs;

import Classes.Noticias;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public interface SubscriberInterface extends java.rmi.Remote {
    
    public void EscreverNoticia (Noticias n) throws java.rmi.RemoteException;
   
}
