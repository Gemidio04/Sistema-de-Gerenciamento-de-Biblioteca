package Livros;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Livro {
    private String isbn;
    private String titulo;
    private String editora;
    private String genero;
    private String autor;
    private String dataPublicacao;
    private String sinopse;

    public Livro() {
    }

    public Livro(String isbn, String titulo, String editora, String genero, String autor, String dataPublicacao, String sinopse) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.genero = genero;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.sinopse = sinopse;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public static Livro instanciaLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setIsbn(rs.getString("isbn"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setEditora(rs.getString("editora"));
        livro.setGenero(rs.getString("genero"));
        livro.setAutor(rs.getString("autor"));
        livro.setDataPublicacao(rs.getString("dataPublicacao"));
        livro.setSinopse(rs.getString("sinopse"));
        return livro;
    }

    @Override
    public String toString() {
        return "Livro {" + "\n" +
                "ISBN: " + isbn + ".\n" +
                "Titulo: " + titulo + ".\n" +
                "Editora: " + editora + ".\n" +
                "Genero: " + genero + ".\n" +
                "Autor: " + autor + ".\n" +
                "Data de Publicação: " + dataPublicacao + ".\n" +
                "Sinopse: " + sinopse + "\n" +
                '}';
    }

}
