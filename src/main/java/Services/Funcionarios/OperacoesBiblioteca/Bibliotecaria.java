package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.SegurancaProfissional;
import Services.Regras.Regra;

import java.time.LocalDate;
import java.util.List;

public class Bibliotecaria extends OperacoesBiblioteca {
    private Regra regra;

    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(Livro livro) {
        super.removerLivro(livro);
    }

    public void atualizarInformacoesLivro(String isbn, String novoTitulo, String novoAutor, String novaDataPublicacao) {
        super.atualizarInformacoesLivro(isbn, novoTitulo, novoAutor, novaDataPublicacao);
    }
    public void venderLivro(Livro livro) {
        super.venderLivro(livro);
    }

    public void emprestarLivro(Livro livro, Cliente cliente) {
        super.emprestarLivro(livro, cliente);
    }

    public void devolverLivro(String isbn, Livro livro) {
        super.devolverLivro(isbn, livro);
    }

    public Livro buscarLivroIsbn(String isbn) {
        return super.buscarLivroIsbn(isbn);
    }

    public Livro buscarLivroAutor(String autor) {
        return super.buscarLivroAutor(autor);
    }

    public Livro buscarLivroTitulo(String titulo) {
        return super.buscarLivroTitulo(titulo);
    }

    public boolean checarDisponibilidadeEstoque() {
        return getEstoque().getQuantidade() > 0;
    }

    public boolean checarDisponibilidadeparaEmprestimo() {
        return getLivroEmprestado();
    }

    public void exibirSinopseLivro(Livro livro){
        super.exibirSinopseLivro(livro);
    }

    public void registrarNovoCliente() {
        super.registrarNovoCliente();
    }

    public void listaTodosClientes() {
        super.listaTodosClientes();
    }

    public void listaClienteDadoUmNome(String nomeBusca) {
        super.listaClienteDadoUmNome(nomeBusca);
    }


    public void removerCliente(Cliente cliente){
        super.removerCliente(cliente);
    }

    public void atualizarTodasInformacoesCliente
            (Cliente cliente, String novoNome, String novoEmail, String novoCEP, String novoEndereco) {
        super.atualizarTodasInformacoesCliente(cliente, novoNome, novoEmail, novoCEP, novoEndereco);
    }

    public List<EmprestimoLivro> historicoDeLivro(Livro livro) {
        return super.historicoDeLivro(livro);
    }

    public List<EmprestimoLivro> historicoDeUsuario(Cliente cliente) {
        return super.historicoDeUsuario(cliente);
    }

    public List<EmprestimoLivro> historicoCompletoLivrosEmprestados() {
        return super.historicoCompletoLivrosEmprestados();
    }

    public void demitirAssistente(Funcionario funcionario) {
        if (regra.getQuantidadeAdvertencias() == 3 || regra.getAdvertencia()) {
            System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
        }
    }

    @Override
    public void verificandoTodoSistemaBiblioteca(){
        super.verificandoTodoSistemaBiblioteca();
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }
}
