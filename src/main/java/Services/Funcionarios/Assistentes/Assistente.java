package Services.Funcionarios.Assistentes;

import Livros.Livro;
import Services.Estoque.EstoqueImplements;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;
import Services.Interfaces.gerenciamentoLivros;

import java.util.HashMap;
import java.util.Map;

public class Assistente extends Funcionario implements gerenciamentoLivros{
    private EstoqueImplements estoqueImplements;
    protected Map<String, Livro> livros;

    public Assistente(){
    }
    public Assistente(EstoqueImplements estoqueImplements) {
        this.estoqueImplements = estoqueImplements;
        this.livros = new HashMap<>();
    }

    @Override
    public void adicionarLivro(String isbn, Livro livro) {
        livros.put(isbn, livro);
        estoqueImplements.setQuantidade(estoqueImplements.getQuantidade() + 1);
    }

    @Override
    public void removerLivro(String isbn) {
        livros.remove(isbn);
        if(estoqueImplements.getQuantidade() > 0)
            estoqueImplements.setQuantidade(estoqueImplements.getQuantidade() - 1);
        else
            throw new ValidacaoException("Não há mais unidades deste livro!");
    }

    @Override
    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAnoPublicacao);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
        }
    }

}
