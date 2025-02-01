package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import Services.Funcionarios.SegurancaProfissional;

import java.util.List;

public class Assistente extends OperacoesBiblioteca{
    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(Livro livro) {
        super.removerLivro(livro);
    }

    public void atualizarInformacoesLivro() {
        super.atualizarInformacoesLivro();
    }

    public void venderLivro() {
        super.venderLivro();
    }

    public void emprestarLivro(EmprestimoLivro emprestimoLivro) {
        super.emprestarLivro(emprestimoLivro);
    }

    public void buscarLivroIsbn(String isbn) {
        super.buscarLivroIsbn(isbn);
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


    public void cadastrarNovoCliente() {
        super.cadastrarNovoCliente();
    }

    public void listaTodosClientes() {
        super.listaTodosClientes();
    }

//    public void listaClienteDadoUmNome(String nomeBusca) {
//        super.listaClienteDadoUmNome(nomeBusca);
//        }


    public void excluirCadastroCliente(Cliente cliente){
        super.excluirCadastroCliente(cliente);
    }

    public void atualizarTodasInformacoesCliente() {
        super.atualizarTodasInformacoesCliente();
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

    @Override
    public void verificandoTodoSistemaBiblioteca(){
        super.verificandoTodoSistemaBiblioteca();
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }

}
