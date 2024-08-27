package Services.Funcionarios;

import Services.Ausencia.Ferias;
import Services.Ausencia.Folga;
import Services.ENUM.Cargo;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;
import Services.Promocao.Promocao;
import Services.Regras.Regra;
import Services.Solicitacoes.Solicitacoes;

import java.util.Locale;
import java.util.Scanner;

public class RH extends OperacoesBiblioteca {

    public void contratarFuncionario(Funcionario novoFuncionario) {
        Locale.setDefault(Locale.US);
        Solicitacoes solicitacoes = new Solicitacoes();

        try (Scanner sc = new Scanner(System.in)) {
            String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
            String email = solicitacoes.solicitarEntrada("Email: ", sc);
            String CPF = solicitacoes.solicitarEntrada("CPF: ", sc);
            String turno = solicitacoes.solicitarEntrada("Turno: ", sc);
            String dataContratacao = solicitacoes.solicitarEntrada("Data da Contratação: ", sc);
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

    public void promover(Promocao promocao, Funcionario funcionario, Cargo novoCargo) {
        promocao.promover(funcionario, novoCargo);
    }

    public void demitirFuncionario(Funcionario funcionario) {
        Regra regra = new Regra();
        if((regra.getQuantidadeAdvertencias() == 3 || regra.getAdvertencia())){
            if (funcionario.getCargo()==Cargo.ASSISTENTE) {
                System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
            }if (funcionario.getCargo()==Cargo.BIBLIOTECARIO) {
                System.out.println("Bibliotecaria(o): " + funcionario.getNome() + " demitido!");
            }if (funcionario.getCargo()==Cargo.GERENTE) {
                System.out.println("Gerente: " + funcionario.getNome() + " demitido!");
            }if (funcionario.getCargo()==Cargo.ANALISTA_DE_SISTEMAS) {
                System.out.println("Analista De Sistemas: " + funcionario.getNome() + " demitido!");
            }if (funcionario.getCargo()==Cargo.GESTOR_PROGRAMAS_EVENTOS) {
                System.out.println("Gestor de Programas e Eventos: " + funcionario.getNome() + " demitido!");
            }if (funcionario.getCargo()==Cargo.SEGURANCA_PROFISSIONAL) {
                System.out.println("Segurança: " + funcionario.getNome() + " demitido!");
            }
        }

    }

    public void verificaTirarFogal(Funcionario funcionario){
        Folga folga = new Folga();
        folga.tirarFolga(funcionario);
    }

    public void verificaTirarFerias(Funcionario funcionario){
        Ferias ferias = new Ferias();
        ferias.tirarFerias(funcionario);
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }
}
