package Menu;

public class Exibicoes {

    public static void exibirMensagemApresentacao() {
        System.out.println("BEM-VINDO À BIBLIOTECA MUNICIPAL!\n");
        System.out.println("NOSSO ESPAÇO É DEDICADO À PROMOÇÃO DA LEITURA, CONHECIMENTO E CULTURA PARA TODAS AS IDADES.");
        System.out.print("AQUI VOCÊ ENCONTRA UM ACERVO DIVERSIFICADO DE LIVROS, REVISTAS, JORNAIS E MULTIMÍDIA,\n");
        System.out.print("ALÉM DE UM AMBIENTE AGRADÁVEL E TRANQUILO PARA ESTUDO E PESQUISA.\n");
        System.out.println("APROVEITE NOSSOS SERVIÇOS DE EMPRÉSTIMO, ACESSO À INTERNET, EVENTOS CULTURAIS, OFICINAS E PALESTRAS.");
        System.out.println("NOSSA EQUIPE ESTÁ SEMPRE À DISPOSIÇÃO PARA AJUDAR E ORIENTAR EM SUAS NECESSIDADES DE INFORMAÇÃO.\n");
        System.out.println("VENHA DESCOBRIR UM MUNDO DE POSSIBILIDADES! A BIBLIOTECA É SEU LUGAR DE ENCONTRO COM A LEITURA E O SABER.\n");
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

    public static void exibirMenuCliente(){
        System.out.println("\n==============================");
        System.out.println("       MENU DO CLIENTE         ");
        System.out.println("==============================");
        System.out.println("1. Cadastrar Novo Cliente");
        System.out.println("2. Atualizar Cadastro");
        System.out.println("3. Excluir Cadastro");
        System.out.println("4. Listas Clientes");
        System.out.println("5. Voltar ao Menu Principal");
        System.out.println("6. Sair");
        System.out.println("------------------------------");
        System.out.print("Escolha uma das opções: ");
    }

    public static void exibirMenuFuncionario(){
        System.out.println("\n=================================");
        System.out.println("       MENU DO FUNCIONÁRIO        ");
        System.out.println("=================================");
        System.out.println("1. Contratar Novo Funcionário");
        System.out.println("2. Atualizar Dados");
        System.out.println("3. Demitir Funcionário");
        System.out.println("4. Realizar Operação da Biblioteca");
        System.out.println("5. Listar Funcionários");
        System.out.println("6. Voltar ao Menu Principal");
        System.out.println("7. Sair");
        System.out.println("------------------------------");
        System.out.print("Escolha uma das opções: ");
    }

    public static void exibirMenuOperacoesBiblioteca(){
        System.out.println("\n=====================================");
        System.out.println("     MENU OPERAÇÕES DA BIBLIOTECA   ");
        System.out.println("=====================================");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Atualizar Livro");
        System.out.println("4. Vender Livro");
        System.out.println("5. Emprestar Livro");
        System.out.println("6. Buscar um livro pelo ISBN");
        System.out.println("7. Voltar ao Menu Principal");
        System.out.println("8. Sair");
        System.out.println("------------------------------");
        System.out.print("Escolha uma das opções: ");
    }

    public static void exibirMenuTipoFuncionario(){
        System.out.println("\n======================================");
        System.out.println("       MENU DO TIPO FUNCIONÁRIO        ");
        System.out.println("======================================");
        System.out.println("1. Funcionário Geral");
        System.out.println("2. Funcionário Administrativo");
        System.out.println("------------------------------");
        System.out.print("Digite 1 para Funcionário Geral ou 2 para Funcionário Administrativo.\nEscolha uma opção: ");
    }

    public static void exibirEscolhaTipoFuncionario(){
        System.out.println("\n1-Funcionário Geral");
        System.out.println("2-Funcionário Administrativo");
        System.out.print("Tipo do Funcionário (1 ou 2): ");
    }

    public static void exibirMenuTipoAdvertencia() {
        System.out.println("\n======================================");
        System.out.println("       MENU DO TIPO DE ADVERTÊNCIA        ");
        System.out.println("======================================");
        System.out.println("1. Roubo na Biblioteca");
        System.out.println("2. Agressão física a Funcionário");
        System.out.println("3. Agressão verbal a Cliente");
        System.out.println("4. Ausência no trabalho sem justificativa");
        System.out.println("5. Uso indevido dos recursos da Biblioteca");
        System.out.println("6. Atendimento inadequado ao público");
        System.out.println("7. Desorganização do ambiente de trabalho");
        System.out.println("8. Falta de registro adequado no sistema");
        System.out.println("9. Quebra de protocolos de segurança");
        System.out.println("10. Outro (Especificar)");
        System.out.println("11. Sair");
        System.out.println("------------------------------");
    }


    public static void exibirOpcaoFinal() {
        System.out.println("\nSaindo do Sistema da Biblioteca...");
        System.out.println("Você Saiu!");
    }
}
