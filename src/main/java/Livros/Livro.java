package Livros;

public class Livro {
    private String id;
    private String titulo;
    private String email;
    private String genero;
    private String autor;
    private int anoPublicao;
    private boolean disponilidade;

    public Livro(){
    }

    public Livro(String id, String titulo, String email, String genero, String autor, int anoPublicao, boolean disponilidade) {
        this.id = id;
        this.titulo = titulo;
        this.email = email;
        this.genero = genero;
        this.autor = autor;
        this.anoPublicao = anoPublicao;
        this.disponilidade = disponilidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getAnoPublicao() {
        return anoPublicao;
    }

    public void setAnoPublicao(int anoPublicao) {
        this.anoPublicao = anoPublicao;
    }

    public boolean getDisponilidade() {
        return disponilidade;
    }

    public void setDisponilidade(boolean disponilidade) {
        this.disponilidade = disponilidade;
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
