package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.InterfacesDAO.EmprestimoLivroDAO;
import SGBD.InterfacesDAO.EstoqueDAO;
import SGBD.JDBC.DaoFactory;
import Services.Estoque.Estoque;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.AnalistaDeSistemas;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;
import Services.Solicitacoes.Solicitacoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OperacoesBiblioteca extends Funcionario {

    private int idOperacoesBiblioteca;
    private int idFuncionarioAdministrativo;
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;
    private boolean livroEmprestado;
    private AnalistaDeSistemas analistaDeSistemas;
    private final Estoque estoque = new Estoque();
    private int idEstoque;
    private FuncionarioAdministrativo funcionarioAdministrativo;

    private final Map<String, Livro> livros;
    private final List<Cliente> listaClientes;
    private final List<EmprestimoLivro> historicoEmprestimos;

    Scanner sc = new Scanner(System.in);

    public OperacoesBiblioteca() {
        this.quantidadeLivrosVendidos = 0;
        this.quantidadeLivrosEmprestados = 0;
        this.livroEmprestado = true;
        this.livros = new HashMap<>();
        this.listaClientes = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();
    }

    public OperacoesBiblioteca(int idOperacoesBiblioteca, int idFuncionarioAdministrativo,
    int quantidadeLivrosVendidos, int quantidadeLivrosEmprestados, int idEstoque) {
        this.idOperacoesBiblioteca = idOperacoesBiblioteca;
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
        this.quantidadeLivrosVendidos = quantidadeLivrosVendidos;
        this.quantidadeLivrosEmprestados = quantidadeLivrosEmprestados;
        this.idEstoque = idEstoque;
        this.livros = new HashMap<>();
        this.listaClientes = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();
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

    public boolean getLivroEmprestado() {
        return livroEmprestado;
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

    public void setLivroEmprestado(boolean livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public void adicionarLivro(String isbn, Livro livro) {
        livros.put(isbn, livro);
        estoque.setQuantidade(estoque.getQuantidade() + 1);
    }

//    public void removerLivro(Livro livro) {
//        if (estoque.getQuantidade() > 0) {
//            livros.remove(livro);
//            estoque.setQuantidade(estoque.getQuantidade() - 1);
//        }else
//            throw new ValidacaoException("Não há mais unidades deste livro!");
//    }

    public void removerLivro(Livro livro) {
        // Verifique o estoque do livro pelo ISBN:
        Estoque estoqueLivro = encontrarEstoquePorIsbn(livro.getIsbn());

        // Verifique se há estoque suficiente:
        if (estoqueLivro == null || estoqueLivro.getQuantidade() <= 0) {
            throw new ValidacaoException("Não há mais unidades deste livro!");
        }

        // Reduza a quantidade em 1
        int novaQuantidade = estoqueLivro.getQuantidade() - 1;
        estoqueLivro.atualizaQuantidade(novaQuantidade);
    }


    public void atualizarInformacoesLivro
            (String isbn, String novoTitulo, String novoAutor, String novaDataPublicacao) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setDataPublicacao(novaDataPublicacao);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
        }
    }

    public void venderLivro(Livro livro) {
        if (checarDisponibilidadeEstoque(livro)) {
            removerLivro(livro);
            quantidadeLivrosVendidos++;
        } else {
            throw new ValidacaoException
                    ("Livro " + livro + " não está disponível para venda!");
        }
    }

    public void emprestarLivro(EmprestimoLivro emprestimoLivro, Livro livro) {
        emprestimoLivro.setIsbn(livro.getIsbn());
        emprestimoLivro.setIdCliente(emprestimoLivro.getIdCliente());
        emprestimoLivro.setDataEmprestimo(emprestimoLivro.getDataEmprestimo());
        emprestimoLivro.setDataDevolucaoEmprestimo(emprestimoLivro.getDataDevolucaoEmprestimo());

        if (checarDisponibilidadeEstoque(livro) && checarDisponibilidadeparaEmprestimo()) {
            removerLivro(livro);
            historicoEmprestimos.add(emprestimoLivro);
            livroEmprestado = true;
        } else {
            throw new ValidacaoException
                    ("Livro:  " + livro + " não está disponível para empréstimo!");
        }

        // Persistir o empréstimo no banco de dados
        EmprestimoLivroDAO emprestimoLivroDAO = DaoFactory.createEmprestimoLivroDAO();
        emprestimoLivroDAO.insert(emprestimoLivro);

        //LivroDAO livroDAO = DaoFactory.createLivroDAO();
        //livroDAO.insert(livro);
    }

    public void devolverLivro(String isbn, Livro livro) {
        adicionarLivro(isbn, livro);
        quantidadeLivrosEmprestados++;
        livroEmprestado = true;
    }

    public boolean checarDisponibilidadeEstoque(Livro livro) {
        // Verifique se o ISBN do livro não é nulo
        if (livro.getIsbn() != null) {
            // Encontre o estoque do livro pelo ISBN:
            Estoque estoqueLivro = encontrarEstoquePorIsbn(livro.getIsbn());
            // Verifique se o estoque existe e se a quantidade é maior que 0:
            return (estoqueLivro != null) && (estoqueLivro.getQuantidade() > 0);
        }
        return false;
    }

    // Método auxiliar para buscar o estoque com base no ISBN do livro:
    private Estoque encontrarEstoquePorIsbn(String isbn) {
        // Simule a busca de estoque associado ao ISBN fornecido:
        EstoqueDAO estoqueDAO = DaoFactory.createEstoqueDAO();
        return estoqueDAO.selectByIsbn(isbn);
    }


    public boolean checarDisponibilidadeparaEmprestimo() {
        return livroEmprestado;
    }

    public Livro buscarLivroIsbn(String isbn) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
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

    public void exibirSinopseLivro(Livro livro){
        System.out.println(livro.getSinopse());
    }

    public void cadastrarNovoCliente() {
        Solicitacoes solicitacoes = new Solicitacoes();

        String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
        String email = solicitacoes.solicitarEntrada("Email: ", sc);
        String CEP = solicitacoes.solicitarEntrada("CEP: ", sc);
        String endereco = solicitacoes.solicitarEntrada("Endereço: ", sc);

        System.out.print("Data de Cadastro (dd/MM/yyyy): ");
        String dateCadastro = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = String.valueOf(LocalDate.parse(dateCadastro, formatter));

        Cliente cliente = new Cliente(nome, email, CEP, endereco, data);
        listaClientes.add(cliente);

        ClienteDAO clienteDAO = DaoFactory.createClienteDAO();
        clienteDAO.insert(cliente);

        System.out.println("Cliente: "+nome+" Cadastrado!");
    }

    public void listaTodosClientes() {
        System.out.println("\nLISTA DE CLIENTES DA BIBLIOTECA:\n");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }
    }

    public void listaClienteDadoUmNome(String nomeBusca) {
        System.out.println("\nLISTA DE CLIENTES COM O NOME: " + nomeBusca + "\n");
        boolean encontrado = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Cliente cliente : listaClientes) {
            // Divida o nome do cliente em partes com base nos espaços:
            String[] partesNomeCliente = cliente.getNome().split("\\s+");

            for (String parte : partesNomeCliente) {
                if (parte.equalsIgnoreCase(nomeBusca)) {
                    System.out.println("Nome: " + cliente.getNome() + ",");
                    System.out.println("Email: " + cliente.getEmail() + ",");
                    System.out.println("CEP: " + cliente.getCEP() + ",");
                    System.out.println("Endereco: " + cliente.getEndereco() + ",");
                    System.out.println("DataCadastro: " + cliente.getDataCadastro() + "\n");
                    encontrado = true;
                    break;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum cliente encontrado com o nome: " + nomeBusca);
        }
    }

    public void atualizarTodasInformacoesCliente() {
        Cliente cliente = new Cliente();
        String novoNome = sc.nextLine();
        String novoEmail = sc.nextLine();
        String novoCEP = sc.nextLine();
        String novoEndereco = sc.nextLine();
        atualizarNomeCliente(cliente, novoNome);
        atualizarEmailCliente(cliente, novoEmail);
        atualizarCepCliente(cliente, novoCEP);
        atualizarEnderecoCliente(cliente, novoEndereco);
        System.out.println("DADOS DO CLIENTE ATUALIZADOS!");
    }

    public void removerCliente(Cliente cliente){
        listaClientes.remove(cliente);
        System.out.println("CLIENTE REMOVIDO!");
    }

    public void atualizarNomeCliente(Cliente cliente, String novoNome){
        cliente.setNome(novoNome);
    }

    public void atualizarEmailCliente(Cliente cliente, String novoEmail){
        cliente.setEmail(novoEmail);
    }

    public void atualizarCepCliente(Cliente cliente, String novoCEP){
        cliente.setNome(novoCEP);
    }
    public void atualizarEnderecoCliente(Cliente cliente, String novoEndereco){
        cliente.setNome(novoEndereco);
    }

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
