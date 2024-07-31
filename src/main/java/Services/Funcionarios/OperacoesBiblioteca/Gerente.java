package Services.Funcionarios.OperacoesBiblioteca;

import Services.Funcionarios.Cargo;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Promocao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Gerente extends OperacoesBiblioteca {
    private boolean advertencia;
    private int quantidadeAdvertencias;
    private List<Funcionario> listaDeFuncionarios;
    private Assistente assistente;

    public Gerente(){
        advertencia = false;
        quantidadeAdvertencias = 0;
        listaDeFuncionarios = new LinkedList<>();
        assistente = new Assistente();
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
        try (Scanner sc = new Scanner(System.in)) {
            String nome = solicitarEntrada("Nome: ", sc);
            String email = solicitarEntrada("Email: ", sc);
            String CPF = solicitarEntrada("CPF: ", sc);
            String turno = solicitarEntrada("Turno: ", sc);
            LocalDate dataContratacao = solicitarDataContratacao("Data da Contratação: ", sc);
            Double salario = solicitarSalario("Salário: ", sc);

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

    private String solicitarEntrada(String mensagem, Scanner sc) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    private LocalDate solicitarDataContratacao(String mensagem, Scanner sc) {
        while (true) {
            System.out.print(mensagem);
            String dataStr = sc.nextLine().trim();
            try {
                return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
            }
        }
    }

    private double solicitarSalario(String mensagem, Scanner sc) {
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextDouble()) {
                Double salario = sc.nextDouble();
                sc.nextLine(); // Consome a nova linha restante
                return salario;
            } else {
                System.out.println("Salário inválido. Digite novamente: ");
                sc.next(); // Consome a entrada inválida
            }
        }
    }

    public void adicionarAdvertencia() {
        if(advertencia)
            quantidadeAdvertencias++;
    }

    public boolean verificarAdvertencias() {
        if(quantidadeAdvertencias == 3) {
            System.out.println("Número de advertências: " + quantidadeAdvertencias);
            return advertencia = true;
        }
        return false;
    }

    public void demitirFuncionario(Funcionario funcionario) {
        if (verificarAdvertencias()) {
            System.out.println("Funcionário " + funcionario.getNome() + " demitido!");
        }
    }

}
