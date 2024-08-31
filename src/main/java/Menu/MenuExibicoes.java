package Menu;

public class MenuExibicoes {

    public static void exibirMensagemApresentacao() {
        System.out.println("BEM-VINDO À BIBLIOTECA MUNICIPAL!\n");
        System.out.println("NOSSO ESPAÇO É DEDICADO À PROMOÇÃO DA LEITURA, CONHECIMENTO E CULTURA PARA TODAS AS IDADES.");
        System.out.print("AQUI VOCÊ ENCONTRA UM ACERVO DIVERSIFICADO DE LIVROS, REVISTAS, JORNAIS E MULTIMÍDIA,\n");
        System.out.print("ALÉM DE UM AMBIENTE AGRADÁVEL E TRANQUILO PARA ESTUDO E PESQUISA.\n");
        System.out.println("APROVEITE NOSSOS SERVIÇOS DE EMPRÉSTIMO, ACESSO À INTERNET, EVENTOS CULTURAIS, OFICINAS E PALESTRAS.");
        System.out.println("NOSSA EQUIPE ESTÁ SEMPRE À DISPOSIÇÃO PARA AJUDAR E ORIENTAR EM SUAS NECESSIDADES DE INFORMAÇÃO.\n");
        System.out.println("VENHA DESCOBRIR UM MUNDO DE POSSIBILIDADES! A BIBLIOTECA É SEU LUGAR DE ENCONTRO COM A LEITURA E O SABER.\n");
    }

    public static void exibirMenuCliente(){
        System.out.println("\n==============================");
        System.out.println("       MENU DO CLIENTE         ");
        System.out.println("==============================");
        System.out.println("1. Cadastrar Novo Cliente");
        System.out.println("2. Atualizar Cadastro");
        System.out.println("3. Excluir Cadastro");
        System.out.println("4. Voltar ao Menu Principal");
        System.out.println("5. Sair");
        System.out.println("------------------------------");
        System.out.print("Escolha uma das opções: ");
    }

    public static void exibirMenuPrincipal() {
        System.out.println("\n==============================");
        System.out.println("        MENU PRINCIPAL          ");
        System.out.println("==============================");
        System.out.println("1. Cliente");
        System.out.println("2. Funcionário");
        System.out.println("3. Sair");
        System.out.println("------------------------------");
        System.out.print("Digite 1 para Cliente, 2 para Funcionário ou 3 para Sair.\nEscolha uma opção: ");
    }

    public static void exibirOpcaoFinal() {
        System.out.println("\nVocê saiu!");
        System.out.println("Volte sempre!\nAté a próxima!");
    }
}
