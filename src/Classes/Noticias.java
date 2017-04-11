
package Classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Fábio, João, Tiago
 */
public class Noticias implements Serializable{
 
     private String conteudo;
     //private GregorianCalendar data;
     private int dia,mes;
    
    public Noticias (){
        
       conteudo="";
       dia  = 0 ;
       mes = 0;
      // data = new GregorianCalendar();
 
    } 

    public Noticias(String conteudo ,int dia , int mes) {
        this.conteudo = conteudo;
        this.dia = dia;
        this.mes = mes;
        //this.data = data;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    
    public String getNoticia() {
        return conteudo;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Noticias{" + "conteudo=" + conteudo + ", dia=" + dia + ", mes=" + mes + '}';
    }
    
    
 
   

  
    

}
