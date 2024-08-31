package Menu;

import java.util.Scanner;

public class MenuImplementacao {

    private static final Scanner sc = new Scanner(System.in);
    static boolean clienteMenu = true;

    public static void ImplementacaoMenuBiblioteca() {
        boolean running = true;

        while (running) {
            MenuExibicoes.exibirMenuPrincipal();
            int opcao = sc.nextInt();
            // Consumir a nova linha pendente:
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    System.out.println("Opções de Funcionário (não implementado)");
                    running = false;
                    // Implemente o menu de funcionário aqui se necessário:
                    break;
                case 3:
                    MenuExibicoes.exibirOpcaoFinal();
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
            MenuExibicoes.exibirMenuCliente();
            int opcaoCliente = sc.nextInt();
            // Consumir a nova linha pendente:
            sc.nextLine();

            switch (opcaoCliente) {
                case 1:
                    System.out.println("Cadastrar Novo Cliente (não implementado)");
                    break;
                case 2:
                    System.out.println("Atualizar Cadastro (não implementado)");
                    break;
                case 3:
                    System.out.println("Excluir Cadastro (não implementado)");
                    break;
                case 4:
                    // Volta ao menu principal:
                    clienteMenu = false;
                    break;
                case 5:
                    MenuExibicoes.exibirOpcaoFinal();
                    // Encerra o programa:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

//    public static void exibirMenuContinuacao(String opcaoContinuar) {
//        System.out.println("\nDeseja Continuar?");
//        System.out.println("1. SIM");
//        System.out.println("2. NÃO");
//        System.out.println("------------------------------");
//        System.out.print("Escolha uma das opções: ");
//        opcaoContinuar = sc.nextLine();
//    }

}
