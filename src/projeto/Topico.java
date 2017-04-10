/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    
    public Topico(String top, String cont, Calendar dat)
    {
        this.nometopico=top;
        this.conteudo=cont;
        this.date = dat;
        
    }
    
    
    
}
