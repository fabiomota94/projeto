
package Classes;

import java.io.Serializable;
import java.util.Date;

// @authors: Tiago Jesus – a30961, João Saraiva, – a33345 Fábio Mota – a34693 UBI 2016/2017-SD

public class Noticias implements Serializable {

    private String conteudo;
    private Date data;
    private int autor;

    public Noticias() {

        conteudo = "";
        data = null;
        autor = 0;

    }

    public Noticias(String conteudo, int autor, Date data) {
        this.conteudo = conteudo;
        this.autor = autor;
        this.data = data;
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

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getNoticia() {
        return conteudo;
    }

    @Override
    public String toString() {
        return "Noticias{" + "Conteudo=" + conteudo + ", data=" + data + ", Autor=" + autor + '}';
    }

}
