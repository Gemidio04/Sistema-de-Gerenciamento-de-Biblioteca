package Livros;

public class Livro {
    private String isbn;
    private String titulo;
    private String email;
    private String genero;
    private String autor;
    private Integer AnoPublicacao;
    private boolean disponilidade;

    public Livro(String isbn){
        this.isbn = isbn;
    }

    public Livro(String isbn, String titulo, String email, String genero, String autor, Integer AnoPublicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.email = email;
        this.genero = genero;
        this.autor = autor;
        this.AnoPublicacao = AnoPublicacao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean disponibilidade(){
        if(disponilidade){
            System.out.println("Livro Disponível!");
            return true;
        }else{
            System.out.println("Livro Indisponível!");
            return false;
        }
    }
}
