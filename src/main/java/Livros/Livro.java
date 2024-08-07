package Livros;

public class Livro {
    private String isbn;
    private String titulo;
    private String editora;
    private String genero;
    private String autor;
    private Integer anoPublicacao;
    private String sinopse;

    public Livro(){
    }

    public Livro(String isbn, String titulo, String editora, String genero, String autor, Integer anoPublicacao, String sinopse) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.genero = genero;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.sinopse = sinopse;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

      public void setAnoPublicacao(Integer anoPublicacao) {
          anoPublicacao = anoPublicacao;
      }

    public String getSinopse() {
        return sinopse;
    }

}
