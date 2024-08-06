package Services.Funcionarios.OperacoesBiblioteca;

import Livros.Livro;
import Services.ENUM.Cargo;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.SegurancaProfissional;
import Services.Promocao.Promocao;
import Services.Regras.Regra;
import Services.Solicitacoes.Solicitacoes;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Gerente extends OperacoesBiblioteca {
    private Regra regra;

    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(String isbn) {
        super.removerLivro(isbn);
    }

    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        super.atualizarInformacoes(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
    }
    public void venderLivro(String isbn) {
        super.venderLivro(isbn);
    }

    public void emprestarLivro(String isbn) {
        super.emprestarLivro(isbn);
    }

    public void devolverLivro(String isbn, Livro livro) {
        super.devolverLivro(isbn, livro);
    }

    public boolean checarDisponibilidade() {
        return super.checarDisponibilidade();
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

    @Override
    public void verificandoTodoSistemaBiblioteca(){
        super.verificandoTodoSistemaBiblioteca();
    }

    @Override
    public void receberNotificacao(SegurancaProfissional incidente) {
        super.receberNotificacao(incidente);
    }
}
