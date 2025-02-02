package Menu;

import Clientes.Cliente;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.JDBC.DaoFactory;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;
import Services.Funcionarios.RH;

import java.util.Scanner;

public class MenuImplementacao {

    private static final Scanner sc = new Scanner(System.in);
    static boolean clienteMenu = true;

    public static void ImplementacaoMenuBiblioteca() {
        boolean running = true;

        while (running) {
            Exibicoes.exibirMenuPrincipal();
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuFuncionario();
                    break;
                case 3:
                    Exibicoes.exibirOpcaoFinal();
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuCliente() {
        boolean clienteMenu = true;

        while (clienteMenu) {
            Exibicoes.exibirMenuCliente();
            int opcaoCliente = sc.nextInt();
            sc.nextLine();

            OperacoesBiblioteca operacoesBiblioteca = new OperacoesBiblioteca();
            ClienteDAO clienteDAO = DaoFactory.createClienteDAO();
            Cliente cliente = new Cliente();

            switch (opcaoCliente) {
                case 1:
                    operacoesBiblioteca.cadastrarNovoCliente();
                    break;
                case 2:
                    operacoesBiblioteca.atualizarTodasInformacoesCliente();
                    break;
                case 3:
                    operacoesBiblioteca.excluirCadastroCliente(cliente);
                    break;
                case 4:
                    operacoesBiblioteca.listaTodosClientes();
                case 5:
                    // Volta ao menu principal:
                    clienteMenu = false;
                    break;
                case 6:
                    Exibicoes.exibirOpcaoFinal();
                    // Encerra o programa:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuFuncionario() {
        boolean clienteMenu = true;

        while (clienteMenu) {
            Exibicoes.exibirMenuFuncionario();
            int opcaoCliente = sc.nextInt();
            sc.nextLine();

            RH RH = new RH();
            ClienteDAO clienteDAO = DaoFactory.createClienteDAO();
            Cliente cliente = new Cliente();

            switch (opcaoCliente) {
                case 1:
                    RH.cadastrarNovoFuncionario();
                    break;
                case 2:
                    RH.atualizarDadosFuncionario();
                    break;
                case 3:
                    RH.demitirFuncionario();
                    break;
                case 4:
                    Exibicoes.exibirMenuOperacoesBiblioteca();
                    OperacoesBiblioteca.realizarOperacaoBiblioteca();
                    break;
                case 5:
                    RH.listarFuncionarios();
                    break;
                case 6:
                    // Volta ao menu principal:
                    clienteMenu = false;
                    break;
                case 7:
                    Exibicoes.exibirOpcaoFinal();
                    // Encerra o programa:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

}
