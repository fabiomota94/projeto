
package Classes;

import java.io.Serializable;

import java.util.Date;


/**
 *
 * @author Fábio, João, Tiago
 */
public class Noticias implements Serializable{
 
     private String conteudo;
     private Date data;
     private int dia,mes;
     private int autor;
    public Noticias (){
        
       conteudo="";
       dia  = 0 ;
       mes = 0;
       autor = 0 ;
       
 
    } 

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public Noticias(String conteudo ,int dia , int mes,int autor , Date data) {
        this.conteudo = conteudo;
        this.dia = dia;
        this.mes = mes;
        this.autor = autor;
        this.data = data;
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
