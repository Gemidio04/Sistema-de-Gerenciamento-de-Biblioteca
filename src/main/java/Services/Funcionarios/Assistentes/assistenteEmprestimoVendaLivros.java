package Services.Funcionarios.Assistentes;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Interfaces.*;

public class assistenteEmprestimoVendaLivros extends Assistente implements emprestimoVendaLivros, estoque {
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;
    private Assistente assistente;

    public assistenteEmprestimoVendaLivros(assistenteGerenciamentoLivros assistente){
        quantidadeLivrosVendidos = 0;
        quantidadeLivrosEmprestados = 0;
        this.assistente=assistente;
    }

    public int getQuantidadeLivrosVendidos() {
        return quantidadeLivrosVendidos;
    }

    public void setQuantidadeLivrosVendidos(int quantidadeLivrosVendidos) {
        this.quantidadeLivrosVendidos = quantidadeLivrosVendidos;
    }

    public int getQuantidadeLivrosEmprestados() {
        return quantidadeLivrosEmprestados;
    }

    public void setQuantidadeLivrosEmprestados(int quantidadeLivrosEmprestados) {
        this.quantidadeLivrosEmprestados = quantidadeLivrosEmprestados;
    }

    @Override
    public void venderLivro(String isbn) {
        if (checarDisponibilidade(isbn)) {
            assistente.removerLivro(isbn);
            quantidadeLivrosVendidos++;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não está disponível para venda!");
        }
    }

    @Override
    public void emprestarLivro(String isbn) {
        if (checarDisponibilidade(isbn)) {
            assistente.removerLivro(isbn);
            quantidadeLivrosEmprestados++;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não está disponível para empréstimo!");
        }
    }

    @Override
    public void devolverLivro(String isbn, Livro livro) {
        assistente.adicionarLivro(isbn, livro);
    }

}
