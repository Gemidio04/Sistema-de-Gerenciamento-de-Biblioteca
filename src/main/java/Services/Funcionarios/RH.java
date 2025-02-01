package Services.Funcionarios;

import Menu.Exibicoes;
import Menu.MenuImplementacao;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.FuncionarioAdministrativoDAO;
import SGBD.InterfacesDAO.FuncionarioGeralDAO;
import SGBD.JDBC.DaoFactory;
import SGBD.InterfacesDAO.FuncionarioDAO;
import SGBD.JDBC.FuncionarioAdministrativoDaoJDBC;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;
import Services.Funcionarios.Tipos.AuxiliarOperacoesFuncionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;
import Services.Funcionarios.Tipos.FuncionarioGeral;
import Services.Solicitacoes.Solicitacoes;
import java.util.*;

public class RH extends OperacoesBiblioteca {

    AuxiliarOperacoesFuncionario auxiliarOperacoesFuncionario = new AuxiliarOperacoesFuncionario();
    static Scanner sc = new Scanner(System.in);
    static Solicitacoes solicitacoes = new Solicitacoes();

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }

    public void cadastrarNovoFuncionario() {
        System.out.println("\nCadastro de Funcionário:");
        auxiliarOperacoesFuncionario.defineTipoFuncionarioCadastro();
    }

    public void atualizarDadosFuncionario() {
        System.out.print("\nQual é o idFuncionario: ");
        int idFuncionario = sc.nextInt();
        sc.nextLine(); // Consumir a nova linha

        System.out.println("1 - Funcionário Geral");
        System.out.println("2 - Funcionário Administrativo");
        System.out.print("Digite (1 ou 2): ");
        int tipoFuncionario = sc.nextInt();
        sc.nextLine(); // Consumir a nova linha

        // Encerra o método se o tipo for inválido:
        if (tipoFuncionario != 1 && tipoFuncionario != 2) {
            System.out.println("Tipo de funcionário inválido!");
            return;
        }

        System.out.println("\nNovos dados do Funcionário:");
        // Obter os novos dados do funcionário, passando o tipo como argumento:
        Funcionario funcionarioAtualizado = auxiliarOperacoesFuncionario.obterDadosAtualizacaoFuncionario(tipoFuncionario);

        if (funcionarioAtualizado != null) {
            if (tipoFuncionario == 1) {
                // Atualizar Funcionário Geral no Banco:
                FuncionarioGeralDAO funcionarioGeralDAO = DaoFactory.createFuncionarioGeralDAO();
                FuncionarioGeral funcionarioGeral = (FuncionarioGeral) funcionarioAtualizado;
                funcionarioGeral.setIdFuncionarioGeral(idFuncionario); // Garantir que o ID correto é definido
                funcionarioGeralDAO.update(funcionarioGeral);
                System.out.println("Dados do Funcionário Geral " + funcionarioAtualizado.getNome() + " atualizados com sucesso!");
            } else if (tipoFuncionario == 2) {
                // Atualizar Funcionário Administrativo no Banco:
                FuncionarioAdministrativoDAO funcionarioAdministrativoDAO = DaoFactory.createFuncionarioAdminstrativoDAO();
                FuncionarioAdministrativo funcionarioAdministrativo = (FuncionarioAdministrativo) funcionarioAtualizado;
                funcionarioAdministrativo.setIdFuncionarioAdministrativo(idFuncionario); // Garantir que o ID correto é definido
                funcionarioAdministrativoDAO.update(funcionarioAdministrativo);
                System.out.println("Dados do Funcionário Administrativo " + funcionarioAtualizado.getNome() + " atualizados com sucesso!");
            }
        } else {
            System.out.println("Erro ao obter os novos dados do funcionário.");
        }
    }

    private final List<String> advertencias = new ArrayList<>();

    private int solicitarTipoFuncionario() {
        int opcao;
        int tentativas = 0;

        do {
            Exibicoes.exibirEscolhaTipoFuncionario();
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1 || opcao == 2) {
                return opcao;
            }

            tentativas++;
            System.out.println("\nEssa opção não é válida!");
            System.out.println(tentativas + "ª tentativa! Restam " + (3 - tentativas) + "!");
        } while (tentativas < 3);

        System.out.println("Você não informou nenhuma opção válida!");
        MenuImplementacao.menuFuncionario();
        return -1;
    }

    public void demitirFuncionario() {
        int opcaoFuncionario = solicitarTipoFuncionario();
        if (opcaoFuncionario == -1) return; // Retorna se o usuário não escolheu corretamente

        System.out.print("\nID do funcionário a ser demitido: ");
        int idFuncionario = sc.nextInt();
        sc.nextLine();

        Funcionario funcionario = null;

        if (opcaoFuncionario == 1) {
            FuncionarioGeralDAO dao = DaoFactory.createFuncionarioGeralDAO();
            funcionario = dao.selectById(idFuncionario);
        } else if (opcaoFuncionario == 2) {
            FuncionarioAdministrativoDAO dao = DaoFactory.createFuncionarioAdminstrativoDAO();
            funcionario = dao.selectById(idFuncionario);
        }

        if (funcionario != null) {
            processaDemissao(funcionario);
        } else {
            System.out.println("Funcionário com ID " + idFuncionario + " não encontrado.");
        }
    }

    private void processaDemissao(Funcionario funcionario) {
        int quantidadeAdvertencias = Integer.parseInt(solicitacoes.solicitarEntrada("Quantas advertências o funcionário levou: "));

        if (quantidadeAdvertencias >= 4) {
            definirAdvertencias(quantidadeAdvertencias);

            try {
                int idFuncionario = (funcionario instanceof FuncionarioGeral)
                        ? ((FuncionarioGeral) funcionario).getIdFuncionarioGeral()
                        : ((FuncionarioAdministrativo) funcionario).getIdFuncionarioAdministrativo();

                if (funcionario instanceof FuncionarioGeral) {
                    FuncionarioGeralDAO funcionarioGeralDAO = DaoFactory.createFuncionarioGeralDAO();
                    funcionarioGeralDAO.delete(idFuncionario);
                    System.out.println("\nFuncionário Geral " + funcionario.getNome() + " removido do Banco de Dados!");
                } else {
                    FuncionarioAdministrativoDAO funcionarioAdministrativoDAO = DaoFactory.createFuncionarioAdminstrativoDAO();
                    funcionarioAdministrativoDAO.delete(idFuncionario);
                    System.out.println("\nFuncionário Administrativo " + funcionario.getNome() + " removido do Banco de Dados!");
                }

                System.out.println("\nMotivo da demissão: levou "+quantidadeAdvertencias+" advertências!");
                exibirAdvertencias();

            } catch (DBException ex) {
                System.out.println("Erro ao deletar o funcionário: " + ex.getMessage());
            }
        } else {
            System.out.println("As condições de demissão não foram atendidas para " + funcionario.getNome() + "!");
        }
    }


    private void definirAdvertencias(int quantidadeAdvertencias) {
        Map<Integer, String> tiposAdvertencia = getMapaAdvertencias();

        System.out.println("\nEscolha as advertências que levaram à demissão:");
        for (Map.Entry<Integer, String> entry : tiposAdvertencia.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("10. Outro (Especifique)");

        int contador = 0;
        while (contador < quantidadeAdvertencias) {
            int opcao = Integer.parseInt(solicitacoes.solicitarEntrada("Opção: "));

            if (opcao == 10) {
                String descricao = solicitacoes.solicitarEntrada("Descreva a advertência: ");
                advertencias.add(descricao);
            } else if (tiposAdvertencia.containsKey(opcao)) {
                advertencias.add(tiposAdvertencia.get(opcao));
            } else {
                System.out.println("Opção inválida! Escolha um número entre 1 e 10.");
                continue;
            }
            contador++;
            System.out.println("Advertência registrada!");
        }
    }

    private Map<Integer, String> getMapaAdvertencias() {
        Map<Integer, String> mapa = new LinkedHashMap<>();
        mapa.put(1, "Roubo na Biblioteca");
        mapa.put(2, "Agressão física a Funcionário");
        mapa.put(3, "Agressão verbal a Cliente");
        mapa.put(4, "Ausência no trabalho sem justificativa");
        mapa.put(5, "Uso indevido dos recursos da Biblioteca");
        mapa.put(6, "Atendimento inadequado ao público");
        mapa.put(7, "Desorganização do ambiente de trabalho");
        mapa.put(8, "Falta de registro adequado no sistema");
        mapa.put(9, "Quebra de protocolos de segurança");
        return mapa;
    }

    private void exibirAdvertencias() {
        System.out.println("\nAs advertências que levaram à demissão foram:");
        advertencias.forEach(advertencia -> System.out.println("- " + advertencia));
    }

    public void listarFuncionarios() {
        System.out.print("\nEscolha o tipo de Funcionário que você quer listar:");
        Exibicoes.exibirEscolhaTipoFuncionario();
        int tipoFuncionario = sc.nextInt();
        sc.nextLine();
        if (tipoFuncionario == 1) {
            FuncionarioGeral.retornaListaFuncionariosGerais();
        } else if (tipoFuncionario == 2) {
            FuncionarioAdministrativo.retornaListaFuncionariosAdministrativos();
        } else {
            System.out.println("Tipo de Funcionário inválido!");
            Exibicoes.exibirEscolhaTipoFuncionario();
            tipoFuncionario = sc.nextInt();
            sc.nextLine();
        }
    }

}
