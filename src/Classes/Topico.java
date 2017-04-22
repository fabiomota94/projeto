
package Classes;

import Subs.Subscriber;
import Subs.SubscriberInterface;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fábio, João, Tiago
 */
public class Topico implements Serializable{
    
    private String nometopico;
    private ArrayList<Noticias> Noticias;
    private ArrayList<Subscritores> subscribers;
        
    public Topico (){
        
       nometopico="";
       Noticias = new ArrayList();
       subscribers = new ArrayList();
       
       
    }
    
    public Topico(String top, ArrayList<Noticias> Noticias){
        this.nometopico=top;
        this.Noticias = Noticias;
        
    }

   
    public void setNomeTopico(String nometopico) {
        
       this.nometopico=nometopico;
    }
    
    
    public void addNovaNoticia(Noticias NewNoticia) {
        
       this.Noticias.add(NewNoticia);
    }

    public ArrayList<Noticias> getNoticias() {
        return Noticias;
    }

    

    public void setNoticias(ArrayList<Noticias> Noticias) {
        this.Noticias = Noticias;
    }

    public void setSubscribers(ArrayList<Subscritores> subscribers) {
        this.subscribers = subscribers;
    }
    
    
    
    
     public String getNometopico() {
        return nometopico;
    }

    @Override
    public String toString() {
        return "Topico{" + "nometopico=" + nometopico + ", Noticias=" + Noticias + ", subscribers=" + subscribers + '}';
    }

    
   
    
}
