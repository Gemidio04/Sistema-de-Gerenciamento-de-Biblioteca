package Services.Funcionarios.Tipos;

import Menu.Exibicoes;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.FuncionarioAdministrativoDAO;
import SGBD.InterfacesDAO.FuncionarioGeralDAO;
import SGBD.JDBC.DaoFactory;
import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;
import Services.Solicitacoes.Solicitacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AuxiliarOperacoesFuncionario {

    Funcionario funcionario = new Funcionario();
    Scanner sc = new Scanner(System.in);

    // Método para obter o cargo para Funcionario Geral:
    private Cargo obterCargoGeral(Solicitacoes solicitacoes) {
        Cargo cargo = null;
        boolean cargoValido = false;
        while (!cargoValido) {
            try {
                cargo = Cargo.valueOf(solicitacoes.solicitarEntrada(
                        "Cargo (RH, GESTOR_PROGRAMAS_EVENTOS, SEGURANCA_PROFISSIONAL, ANALISTA_DE_SISTEMAS): ").toUpperCase());
                if (cargo == Cargo.RH || cargo == Cargo.GESTOR_PROGRAMAS_EVENTOS ||
                        cargo == Cargo.SEGURANCA_PROFISSIONAL || cargo == Cargo.ANALISTA_DE_SISTEMAS) {
                    cargoValido = true;
                } else {
                    System.out.println("Cargo inválido. Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo inválido. Tente novamente.");
            }
        }
        return cargo;
    }

    // Método para obter o cargo para Funcionario Administrativo:
    private Cargo obterCargoAdministrativo(Solicitacoes solicitacoes) {
        Cargo cargo = null;
        boolean cargoValido = false;
        while (!cargoValido) {
            try {
                cargo = Cargo.valueOf(solicitacoes.solicitarEntrada(
                        "Cargo (ASSISTENTE, BIBLIOTECARIO, GERENTE): ").toUpperCase());
                if (cargo == Cargo.ASSISTENTE || cargo == Cargo.BIBLIOTECARIO || cargo == Cargo.GERENTE) {
                    cargoValido = true;
                } else {
                    System.out.println("Cargo inválido. Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo inválido. Tente novamente.");
            }
        }
        return cargo;
    }

    // Método genérico para inserir um Funcionário Geral no Banco de Dados:
    private void inserirFuncionarioGeralNoBanco(FuncionarioGeral funcionarioGeral) {
        FuncionarioGeralDAO funcionarioGeralDAO = DaoFactory.createFuncionarioGeralDAO();
        try {
        funcionarioGeralDAO.insert(funcionarioGeral);
        //FuncionarioGeralDaoJDBC funcionarioGeralDaoJDBC = funcionarioGeralDAO.insert(funcionarioGeral);
        } catch (DBException ex) {
            System.err.println("Erro ao inserir funcionário: " + ex.getMessage());
            throw new DBException("Erro ao inserir funcionário no banco");
        }
    }

    // Método genérico para inserir um Funcionário Administrativo no Banco de Dados:
    private void inserirFuncionarioAdministrativoNoBanco(FuncionarioAdministrativo funcionarioAdministrativo) {
        FuncionarioAdministrativoDAO funcionarioAdministrativoDAO = DaoFactory.createFuncionarioAdminstrativoDAO();
        try {
            funcionarioAdministrativoDAO.insert(funcionarioAdministrativo);
        } catch (DBException ex) {
            System.err.println("Erro ao inserir funcionário: " + ex.getMessage());
            throw new DBException("Erro ao inserir funcionário no banco");
        }
    }

    public void defineTipoFuncionarioCadastro() {
        Solicitacoes solicitacoes = new Solicitacoes();

        // Obter as informações do funcionário
        Funcionario tipoFuncionario = obterDadosCadastroFuncionario();

        // Verificar se o objeto foi criado corretamente:
        if (tipoFuncionario != null) {
            // Inserir no banco de dados com base no tipo do funcionário
            if (tipoFuncionario instanceof FuncionarioGeral) {
                inserirFuncionarioGeralNoBanco((FuncionarioGeral) tipoFuncionario);
                System.out.println("Funcionário Geral: " + tipoFuncionario.getNome() + " cadastrado com sucesso!");
            } else if (tipoFuncionario instanceof FuncionarioAdministrativo) {
                inserirFuncionarioAdministrativoNoBanco((FuncionarioAdministrativo) tipoFuncionario);
                System.out.println("Funcionário Administrativo: " + tipoFuncionario.getNome() + " cadastrado com sucesso!");
            } else {
                throw new DBException("Tipo de funcionário desconhecido!");
            }
        } else {
            throw new DBException("Erro ao criar o objeto Funcionário");
        }
    }

    public Funcionario obterDadosAtualizacaoFuncionario(int tipoFuncionario) {
        Solicitacoes solicitacoes = new Solicitacoes();

        String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
        String email = solicitacoes.solicitarEntrada("Email: ", sc);
        String CPF = solicitacoes.solicitarEntrada("CPF: ", sc);
        String turno = solicitacoes.solicitarEntrada("Turno: ", sc);

        System.out.print("Data de Cadastro (dd/MM/yyyy): ");
        String dateCadastro = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateCadastro = LocalDate.parse(dateCadastro, formatter).toString();

        double salario = solicitacoes.solicitarSalario("Salário: ", sc);

        // Criar o objeto baseado no tipo recebido:
        if (tipoFuncionario == 1) {
            Cargo cargo = obterCargoGeral(solicitacoes);
            return new FuncionarioGeral(nome, email, CPF, turno, dateCadastro, salario, cargo);
        } else if (tipoFuncionario == 2) {
            Cargo cargo = obterCargoAdministrativo(solicitacoes);
            return new FuncionarioAdministrativo(nome, email, CPF, turno, dateCadastro, salario, cargo);
        } else {
            throw new DBException("Tipo de funcionário inválido!");
        }
    }

    public Funcionario obterDadosCadastroFuncionario() {
        Exibicoes.exibirEscolhaTipoFuncionario();
        int tipoFuncionario = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        System.out.println("\nDados do novo Funcionário:");
        return obterDadosAtualizacaoFuncionario(tipoFuncionario);
    }

    private void atualizarIdFuncionario() {
        int idFuncionario = 0;

        if (funcionario instanceof FuncionarioGeral) {
            ((FuncionarioGeral) funcionario).setIdFuncionarioGeral(idFuncionario);
        } else if (funcionario instanceof FuncionarioAdministrativo) {
            ((FuncionarioAdministrativo) funcionario).setIdFuncionarioAdministrativo(idFuncionario);
        }
    }

    private void atualizarNomeFuncionario(String novoNome){
        funcionario.setNome(novoNome);
    }

    private void atualizarEmailFuncionario(String novoEmail){
        funcionario.setEmail(novoEmail);
    }

    private void atualizarCpfFuncionario(String novoCPF){
        funcionario.setCPF(novoCPF);
    }

    private void atualizarTurnoFuncionario(String novoTurno){
        funcionario.setTurno(novoTurno);
    }

    private void atualizarDataCadastroFuncionario(String data) {
        funcionario.setDataContratacao(data);
    }

    private void atualizarCargoFuncionario(Cargo novoCargo) {
        funcionario.setCargo(novoCargo);
    }

    private void atualizarSalarioFuncionario(Double novoSalario) {
        funcionario.setSalario(novoSalario);
    }

}
