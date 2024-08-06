package Livros;

public class Livro {
    private String isbn;
    private String titulo;
    private String editora;
    private String genero;
    private String autor;
    private Integer AnoPublicacao;
    private String sinopse;

    public Livro(){
    }

    public Livro(String isbn, String titulo, String editora, String genero, String autor, Integer AnoPublicacao, String sinopse) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.genero = genero;
        this.autor = autor;
        this.AnoPublicacao = AnoPublicacao;
        this.sinopse = sinopse;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacao() {
        return AnoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        AnoPublicacao = anoPublicacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
}
