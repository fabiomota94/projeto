
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fábio, João, Tiago
 */
public class Topico implements Serializable{
    
    private String nometopico;
    private ArrayList<Noticias> Noticias;
    
        
    public Topico (){
        
       nometopico="";
       Noticias = new ArrayList();
       
       
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
    
    
     public String getNometopico() {
        return nometopico;
    }

    @Override
    public String toString() {
        return "Topico{" + "nometopico=" + nometopico + ", Noticias=" + Noticias + '}';
    }

    

    

    

    

    
    
    
    
    
    
}
