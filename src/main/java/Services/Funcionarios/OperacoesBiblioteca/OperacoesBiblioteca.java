package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.InterfacesDAO.EmprestimoLivroDAO;
import SGBD.InterfacesDAO.EstoqueDAO;
import SGBD.InterfacesDAO.LivroDAO;
import SGBD.JDBC.DaoFactory;
import Services.Estoque.Estoque;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.AnalistaDeSistemas;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;
import Services.Solicitacoes.Solicitacoes;
import Menu.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class OperacoesBiblioteca extends Funcionario {

    private int idOperacoesBiblioteca;
    private int idFuncionarioAdministrativo;
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;
    private AnalistaDeSistemas analistaDeSistemas;
    private final Estoque estoque = new Estoque();
    private int idEstoque;
    private FuncionarioAdministrativo funcionarioAdministrativo;
    private static OperacoesAuxiliaresBiblioteca operacoesAuxiliaresBiblioteca = new OperacoesAuxiliaresBiblioteca();
    private final Menu Menu = new Menu();

    private final Map<String, Livro> livros;
    private final List<Cliente> listaClientes;
    private final List<EmprestimoLivro> historicoEmprestimos;
    private final List<Livro> livrosVendidos;

    ClienteDAO clienteDAO = DaoFactory.createClienteDAO();

    public OperacoesBiblioteca() {
        this.quantidadeLivrosVendidos = 0;
        this.quantidadeLivrosEmprestados = 0;
        this.livros = new HashMap<>();
        this.listaClientes = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();
        this.livrosVendidos = new ArrayList<>();
    }

    public OperacoesBiblioteca(int idOperacoesBiblioteca, int idFuncionarioAdministrativo, OperacoesAuxiliaresBiblioteca operacoesAuxiliaresBiblioteca,
                               int quantidadeLivrosVendidos, int quantidadeLivrosEmprestados, int idEstoque, List<Livro> livrosVendidos) {
        this.idOperacoesBiblioteca = idOperacoesBiblioteca;
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
        OperacoesBiblioteca.operacoesAuxiliaresBiblioteca = operacoesAuxiliaresBiblioteca;
        this.quantidadeLivrosVendidos = quantidadeLivrosVendidos;
        this.quantidadeLivrosEmprestados = quantidadeLivrosEmprestados;
        this.idEstoque = idEstoque;
        this.livros = new HashMap<>();
        this.listaClientes = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();
        this.livrosVendidos = livrosVendidos;
    }

    public int getIdOperacoesBiblioteca() {
        return idOperacoesBiblioteca;
    }

    public int getIdFuncionarioAdministrativo() {
        return idFuncionarioAdministrativo;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdOperacoesBiblioteca(int idOperacoesBiblioteca) {
        this.idOperacoesBiblioteca = idOperacoesBiblioteca;
    }

    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public Map<String, Livro> getLivros(){
        return livros;
    }

    public int getQuantidadeLivrosVendidos() {
        return quantidadeLivrosVendidos;
    }

    public void setQuantidadeLivrosVendidos(int quantidadeLivrosVendidos) {
        this.quantidadeLivrosVendidos = quantidadeLivrosVendidos;
    }

    public int getQuantidadeLivrosEmprestados() {
        return quantidadeLivrosEmprestados;
    }

    public void setQuantidadeLivrosEmprestados(int quantidadeLivrosEmprestados) {
        this.quantidadeLivrosEmprestados = quantidadeLivrosEmprestados;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    // FUNCIONÁRIOS:

    static Scanner sc = new Scanner(System.in);
    LivroDAO livroDAO = DaoFactory.createLivroDAO();
    Solicitacoes solicitacoes = new Solicitacoes();

    public static void realizarOperacaoBiblioteca() {
        int opcao = sc.nextInt();
        sc.nextLine();

        OperacoesBiblioteca operacoesBiblioteca = new OperacoesBiblioteca();
        Solicitacoes solicitacoes = new Solicitacoes();

        if (opcao == 1) {
            Livro novoLivro = new Livro();
            novoLivro.setIsbn(OperacoesAuxiliaresBiblioteca.solicitarEntrada("\nISBN: "));
            OperacoesAuxiliaresBiblioteca.solicitarNovosDadosLivro(novoLivro);

            operacoesBiblioteca.adicionarLivro(novoLivro.getIsbn(), novoLivro);
            System.out.println("Livro '" + novoLivro.getTitulo() + "' adicionado com sucesso!");
        } else if (opcao == 2) {
            System.out.print("\nQual o ISBN do livro que deseja remover: ");
            String ISBN = sc.nextLine();

            Livro livro = operacoesAuxiliaresBiblioteca.buscarLivroPorIsbn(ISBN);
            if (livro != null) {
                System.out.println("");
                operacoesBiblioteca.removerLivro(livro);
                System.out.println("Livro com ISBN " + ISBN + " removido com sucesso!");
            }
        } else if (opcao == 3) {
            operacoesBiblioteca.atualizarInformacoesLivro();
        } else if (opcao == 4) {
            operacoesBiblioteca.venderLivro();
        } else if (opcao == 5) {
            EmprestimoLivro emprestimoLivro = operacoesAuxiliaresBiblioteca.solicitaDadosEmprestimoLivro();
            operacoesBiblioteca.emprestarLivro(emprestimoLivro);
        } else if (opcao == 6) {
            String ISBN = solicitacoes.solicitarISBN();
            operacoesBiblioteca.buscarLivroIsbn(ISBN);
//        } else if (opcao == 7) {
//            operacoesBiblioteca.buscarLivroAutor();
//        } else if (opcao == 8) {
//            operacoesBiblioteca.buscarLivroTitulo();
        } else if (opcao == 7) {
            MenuImplementacao.ImplementacaoMenuBiblioteca();
        } else if (opcao == 8) {
            Exibicoes.exibirOpcaoFinal();
            // Encerra o programa:
            System.exit(0);
        } else {

        }
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados na biblioteca.");
            return;
        }

        System.out.println("Lista de Livros na Biblioteca:");
        for (Map.Entry<String, Livro> entry : livros.entrySet()) {
            String isbn = entry.getKey();
            Livro livro = entry.getValue();

            // Supondo que a classe Livro tenha métodos para obter detalhes
            System.out.println("ISBN: " + isbn);
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Data de Publicação: " + livro.getDataPublicacao());
            System.out.println("-----------------------------------");
        }
    }

    EstoqueDAO estoqueDAO = DaoFactory.createEstoqueDAO();

    public void adicionarLivro(String isbn, Livro livro) {
        livros.put(isbn, livro);
        estoque.setQuantidade(estoque.getQuantidade() + 50);
        estoque.setIsbn(livro.getIsbn());
        livroDAO.insert(livro);
        // ADICIONA AS UNIDADES DO LIVRO AO ESTOQUE:
        estoqueDAO = DaoFactory.createEstoqueDAO();
        estoqueDAO.insert(estoque);
    }

    public void removerLivro(Livro livro) {
        int idEstoque = Integer.parseInt(solicitacoes.solicitarEntrada("Qual o idEstoque correspondente a esse livro: "));
        estoqueDAO.delete(idEstoque);
        livroDAO.delete(livro.getIsbn());
    }

    public void atualizarInformacoesLivro() {
        String ISBN = solicitacoes.solicitarEntrada("\nQual o ISBN do livro que deseja atualizar: ");

        Livro livro = operacoesAuxiliaresBiblioteca.buscarLivroPorIsbn(ISBN);
        if (livro == null) return;

        System.out.println("\nNovos dados:");
        // SOLICITA E ATUALIZA OS DADOS:
        OperacoesAuxiliaresBiblioteca.solicitarNovosDadosLivro(livro);

        // PERSISTE NO BANCO:
        livroDAO.update(livro);
        System.out.println("\nLivro com ISBN " + ISBN + " atualizado com sucesso!");
    }

    public void venderLivro() {
        String ISBN = OperacoesAuxiliaresBiblioteca.solicitarEntrada("\nQual o ISBN do Livro que deseja vender: ");

        // VERIFICA A DISPONIBILIDADE DO LIVRO:
        if (operacoesAuxiliaresBiblioteca.checarDisponibilidadeLivro(ISBN)) {
            Livro livro = operacoesAuxiliaresBiblioteca.buscarLivroPorIsbn(ISBN);
            operacoesAuxiliaresBiblioteca.decrementaQuantidadeEstoqueNoBanco();
            System.out.println("Livro " + livro.getTitulo() + " com ISBN " + livro.getIsbn() + " vendido!");
            quantidadeLivrosVendidos++;
            livrosVendidos.add(livro);
        } else {
            //throw new ValidacaoException("O livro com ISBN " + ISBN + " não está disponível para venda!");
            System.out.println("O livro com ISBN " + ISBN + " não está disponível para venda!");
        }
    }

    EmprestimoLivroDAO emprestimoLivroDAO = DaoFactory.createEmprestimoLivroDAO();

    public void emprestarLivro(EmprestimoLivro emprestimoLivro) {
        if (emprestimoLivro == null) {
            System.out.println("Não foi possível realizar o empréstimo. Dados inválidos.");
            return;
        }
        // VERIFICA A DISPONIBILIDADE PARA EMPRÉSTIMO:
        if (operacoesAuxiliaresBiblioteca.checarDisponibilidadeparaEmprestimo()) {
            emprestimoLivroDAO.insert(emprestimoLivro);
            historicoEmprestimos.add(emprestimoLivro);
            operacoesAuxiliaresBiblioteca.setLivroEmprestado(true);
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Livro não disponível para empréstimo!");
        }
    }

//    public void devolverLivro(String isbn, Livro livro) {
//        adicionarLivro(isbn, livro);
//        quantidadeLivrosEmprestados++;
//        operacoesAuxiliaresBiblioteca.setLivroEmprestado(true);
//    }

//    public Livro buscarLivroIsbn(String isbn) {
//        Livro livro = livros.get(isbn);
//        if (livro != null) {
//            return livro;
//        } else {
//            throw new ValidacaoException("O livro não existe");
//        }
//    }

    public void buscarLivroIsbn(String isbn) {
        Livro livro =  livroDAO.selectByIsbn(isbn);
        System.out.print("\nResultado da busca:");
        System.out.print(livro);
    }

    public Livro buscarLivroAutor(String autor) {
        Livro livro = livros.get(autor);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    public Livro buscarLivroTitulo(String titulo) {
        Livro livro = livros.get(titulo);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    // CLIENTES:

    public void cadastrarNovoCliente() {
        Solicitacoes solicitacoes = new Solicitacoes();

        String nome = solicitacoes.solicitarEntrada("\nNome: ", sc);
        String email = solicitacoes.solicitarEntrada("Email: ", sc);
        String CEP = solicitacoes.solicitarEntrada("CEP: ", sc);
        String endereco = solicitacoes.solicitarEntrada("Endereço: ", sc);

        System.out.print("Data de Cadastro (dd/MM/yyyy): ");
        String dateCadastro = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = String.valueOf(LocalDate.parse(dateCadastro, formatter));

        Cliente cliente = new Cliente(nome, email, CEP, endereco, data);
        listaClientes.add(cliente);

        clienteDAO.insert(cliente);
        System.out.println("Cliente: " + nome + " Cadastrado!");
    }

    public void excluirCadastroCliente(Cliente cliente) {
        System.out.print("\nDigite o id do cliente que deseja remover: ");
        cliente.setIdCliente(sc.nextInt());
        sc.nextLine();
        listaClientes.remove(cliente);
        clienteDAO.delete(cliente.getIdCliente());
        System.out.println("Cliente Removido!");
    }

    public void atualizarTodasInformacoesCliente() {
        Cliente cliente = new Cliente();
        Solicitacoes solicitacoes = new Solicitacoes();

        int novoIdCliente = Integer.parseInt(solicitacoes.solicitarEntrada("\nQual é o idCliente: ", sc));
        System.out.println("\nNovos dados:");
        String novoNome = solicitacoes.solicitarEntrada("Nome: ", sc);
        String novoEmail = solicitacoes.solicitarEntrada("Email: ", sc);
        String novoCEP = solicitacoes.solicitarEntrada("CEP: ", sc);
        String novoEndereco = solicitacoes.solicitarEntrada("Endereço: ", sc);

        System.out.print("Data de Cadastro (dd/mm/yyyyy): ");
        String dateCadastro = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = String.valueOf(LocalDate.parse(dateCadastro, formatter));

        operacoesAuxiliaresBiblioteca.atualizarIdCLiente(cliente, novoIdCliente);
        operacoesAuxiliaresBiblioteca.atualizarNomeCliente(cliente, novoNome);
        operacoesAuxiliaresBiblioteca.atualizarEmailCliente(cliente, novoEmail);
        operacoesAuxiliaresBiblioteca.atualizarCepCliente(cliente, novoCEP);
        operacoesAuxiliaresBiblioteca.atualizarEnderecoCliente(cliente, novoEndereco);
        operacoesAuxiliaresBiblioteca.atualizarDataCadastroCliente(cliente, data);

        clienteDAO.update(cliente);
        System.out.println("Dados do Cliente atualzados!");
    }

    public void listaTodosClientes() {
        System.out.println("\nLISTA DE CLIENTES DA BIBLIOTECA:\n");
        clienteDAO.selectAll();
    }

//    public void listaClienteDadoUmNome(String nomeBusca) {
//        System.out.println("\nLISTA DE CLIENTES COM O NOME: " + nomeBusca + "\n");
//        boolean encontrado = false;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        for (Cliente cliente : listaClientes) {
//            // Divida o nome do cliente em partes com base nos espaços:
//            String[] partesNomeCliente = cliente.getNome().split("\\s+");
//
//            for (String parte : partesNomeCliente) {
//                if (parte.equalsIgnoreCase(nomeBusca)) {
//                    System.out.println("Nome: " + cliente.getNome() + ",");
//                    System.out.println("Email: " + cliente.getEmail() + ",");
//                    System.out.println("CEP: " + cliente.getCEP() + ",");
//                    System.out.println("Endereco: " + cliente.getEndereco() + ",");
//                    System.out.println("DataCadastro: " + cliente.getDataCadastro() + "\n");
//                    encontrado = true;
//                    break;
//                }
//            }
//        }
//
//        if (!encontrado) {
//            System.out.println("Nenhum cliente encontrado com o nome: " + nomeBusca);
//        }
//    }

    public List<EmprestimoLivro> historicoDeLivro(Livro livro) {
        return historicoEmprestimos.stream().filter(emprestimoLivro ->
                emprestimoLivro.getIsbn().equals(livro.getIsbn())).collect(Collectors.toList());
    }

    public List<EmprestimoLivro> historicoDeUsuario(Cliente cliente) {
        return historicoEmprestimos.stream().filter(emprestimo ->
                emprestimo.getCliente().equals(cliente)).collect(Collectors.toList());
    }

    public List<EmprestimoLivro> historicoCompletoLivrosEmprestados() {
        return new ArrayList<>(historicoEmprestimos);
    }

    public void verificandoTodoSistemaBiblioteca() {
        analistaDeSistemas.suporteTecnico(true);
    }

    public static OperacoesBiblioteca instanciaOperacoesBiblioteca(ResultSet resultSet) throws SQLException {
        OperacoesBiblioteca operacoesBiblioteca = new OperacoesBiblioteca();
        FuncionarioAdministrativo funcionarioAdministrativo = new FuncionarioAdministrativo();
        operacoesBiblioteca.setIdOperacoesBiblioteca(resultSet.getInt("idOperacoesBiblioteca"));
        operacoesBiblioteca.setIdFuncionarioAdministrativo(resultSet.getInt("idFuncionarioAdministrativo"));
        operacoesBiblioteca.setQuantidadeLivrosVendidos(resultSet.getInt("quantidadeLivrosVendidos"));
        operacoesBiblioteca.setQuantidadeLivrosEmprestados(resultSet.getInt("quantidadeLivrosEmprestados"));
        operacoesBiblioteca.setIdEstoque(resultSet.getInt("idEstoque"));
        return operacoesBiblioteca;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer().append("\n");
        sb.append("idOperacoesBiblioteca: ").append(idOperacoesBiblioteca).append("\n");
        sb.append("idFuncionarioAdministrativo: ").append(idFuncionarioAdministrativo).append("\n");
        sb.append("quantidadeLivrosVendidos: ").append(quantidadeLivrosVendidos).append("\n");
        sb.append("quantidadeLivrosEmprestados: ").append(quantidadeLivrosEmprestados).append("\n");
        sb.append("id do Estoque: ").append(idEstoque).append("\n");
        return sb.toString();
    }
}
