
package projeto;

import java.util.Calendar;

/**
 *
 * @author Fábio, João, Tiago
 */
public class Noticias {
 
     private String conteudo;
     private Calendar data;
     
    
    public Noticias (){
        
       conteudo="";
       data = Calendar.getInstance();
 
    } 

    public Noticias(String conteudo, Calendar data) {
        this.conteudo = conteudo;
        this.data = data;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
     
    
    public String getNoticia() {
        return conteudo;
    }
    
    
    public Calendar getDate() {
        return data;
    }

    @Override
    public String toString() {
        return "Noticias{" + "conteudo=" + conteudo + ", data=" + data + '}';
    }

    

}
