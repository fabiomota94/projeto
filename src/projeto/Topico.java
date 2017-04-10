/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Fábio
 */
public class Topico{
    
    private String nometopico;
    private String conteudo;
    private Calendar date;
        
    public Topico (){
        
       nometopico="";
       conteudo="";
       date = Calendar.getInstance();
       
       
    }
    
    public Topico(String top, String cont, Calendar dat){
        this.nometopico=top;
        this.conteudo=cont;
        this.date = dat;
        
    }

    public String getNometopico() {
        return nometopico;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Calendar getDate() {
        return date;
    }

    public void setNometopico(String nometopico) {
        this.nometopico = nometopico;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Topico{" + "nometopico=" + nometopico + ", conteudo=" + conteudo + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Topico other = (Topico) obj;
        if (!Objects.equals(this.nometopico, other.nometopico)) {
            return false;
        }
        if (!Objects.equals(this.conteudo, other.conteudo)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
