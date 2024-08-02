package Services.Funcionarios.OperacoesBiblioteca;

import Services.Funcionarios.Cargo;
import Services.Funcionarios.Funcionario;
import Services.Promocao.Promocao;
import Services.Regras.Regra;
import Services.Solicitacoes.Solicitacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Gerente extends OperacoesBiblioteca {
    private List<Funcionario> listaDeFuncionarios;
    private Regra regra;

    public Gerente(){
        listaDeFuncionarios = new LinkedList<>();
    }

    public List<Funcionario> getListaDeFuncionarios() {
        return listaDeFuncionarios;
    }

    public void setListaDeFuncionarios(List<Funcionario> listaDeFuncionarios) {
        this.listaDeFuncionarios = listaDeFuncionarios;
    }

    public void promover(Promocao promocao, Cargo novoCargo){
        Funcionario funcionario = new Funcionario();
        promocao.promover(funcionario);
        funcionario.setCargo(novoCargo.ProximoCargo());
    }

    public void contratarFuncionario(OperacoesBiblioteca novoFuncionario) {
        Locale.setDefault(Locale.US);
        Solicitacoes solicitacoes = new Solicitacoes();

        try (Scanner sc = new Scanner(System.in)) {
            String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
            String email = solicitacoes.solicitarEntrada("Email: ", sc);
            String CPF = solicitacoes.solicitarEntrada("CPF: ", sc);
            String turno = solicitacoes.solicitarEntrada("Turno: ", sc);
            LocalDate dataContratacao = solicitacoes.solicitarDataContratacao("Data da Contratação: ", sc);
            Double salario = solicitacoes.solicitarSalario("Salário: ", sc);

            Cargo.converteCargoEnum(novoFuncionario);

            novoFuncionario.setNome(nome);
            novoFuncionario.setEmail(email);
            novoFuncionario.setCPF(CPF);
            novoFuncionario.setTurno(turno);
            novoFuncionario.setDataContratacao(dataContratacao);
            novoFuncionario.setSalario(salario);

            listaDeFuncionarios.add(novoFuncionario);
            System.out.println("\nFuncionário contratado: " + novoFuncionario.getNome() + ", Cargo: " + novoFuncionario.getCargo());
        }
    }

    public void demitirFuncionario(Funcionario funcionario) {
        if (regra.getQuantidadeAdvertencias() == 3 || regra.getAdvertencia()) {
            System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
        }
    }

    public void verificandoBiblioteca(){
        super.verificandoBiblioteca();
    }
}
