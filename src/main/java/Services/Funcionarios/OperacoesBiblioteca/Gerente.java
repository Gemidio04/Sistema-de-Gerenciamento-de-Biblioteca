package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import Services.ENUM.Cargo;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.SegurancaProfissional;
import Services.Promocao.Promocao;
import Services.Regras.Regra;
import Services.Solicitacoes.Solicitacoes;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Gerente extends OperacoesBiblioteca {
    private Regra regra;

    public void promover(Promocao promocao, Funcionario funcionario, Cargo novoCargo) {
        promocao.promover(funcionario, novoCargo);
    }

    public void contratarFuncionario(Funcionario novoFuncionario) {
        Locale.setDefault(Locale.US);
        Solicitacoes solicitacoes = new Solicitacoes();

        try (Scanner sc = new Scanner(System.in)) {
            String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
            String email = solicitacoes.solicitarEntrada("Email: ", sc);
            String CPF = solicitacoes.solicitarEntrada("CPF: ", sc);
            String turno = solicitacoes.solicitarEntrada("Turno: ", sc);
            LocalDate dataContratacao = solicitacoes.solicitarDataContratacao("Data da Contratação: ", sc);
            Double salario = solicitacoes.solicitarSalario("Salário: ", sc);

            Cargo.converteCargoEnum((OperacoesBiblioteca) novoFuncionario);

            novoFuncionario.setNome(nome);
            novoFuncionario.setEmail(email);
            novoFuncionario.setCPF(CPF);
            novoFuncionario.setTurno(turno);
            novoFuncionario.setDataContratacao(dataContratacao);
            novoFuncionario.setSalario(salario);

            novoFuncionario.getListaDeFuncionarios().add(novoFuncionario);
            System.out.println("\nFuncionário contratado: " + novoFuncionario.getNome() + ", Cargo: " + novoFuncionario.getCargo());
        }
    }

    public void demitirFuncionario(Funcionario funcionario) {
        Regra regra = new Regra();
        if (regra.getQuantidadeAdvertencias() == 3 || regra.getAdvertencia()) {
            System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
        }
    }

    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(Livro livro) {
        super.removerLivro(livro);
    }

    public void atualizarInformacoesLivro(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        super.atualizarInformacoesLivro(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
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

    public boolean checarDisponibilidadeEstoque() {
        return getEstoque().getQuantidade() > 0;
    }

    public boolean checarDisponibilidadeparaEmprestimo() {
        return getLivroEmprestado();
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

    @Override
    public void verificandoTodoSistemaBiblioteca(){
        super.verificandoTodoSistemaBiblioteca();
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }
}
