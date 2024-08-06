package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import Services.ENUM.Cargo;
import Services.Estoque.Estoque;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.AnalistaDeSistemas;
import Services.Funcionarios.Funcionario;
import Services.Solicitacoes.Solicitacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OperacoesBiblioteca extends Funcionario {

    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;
    private boolean livroEmprestado;
    private AnalistaDeSistemas analistaDeSistemas;
    private Estoque estoque;

    private Map<String, Livro> livros;
    private List<Cliente> listaClientes;
    private List<EmprestimoLivro> historicoEmprestimos;

    public OperacoesBiblioteca() {
        super();
        quantidadeLivrosVendidos = 0;
        quantidadeLivrosEmprestados = 0;
        livroEmprestado = true;
        livros = new HashMap<>();
        listaClientes = new ArrayList<>();
        historicoEmprestimos = new ArrayList<>();
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Map<String, Livro> getLivros() {
        return livros;
    }

    public void setLivros(Map<String, Livro> livros) {
        this.livros = livros;
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

    public boolean getLivroEmprestado() {
        return livroEmprestado;
    }

    public void setLivroEmprestado(boolean livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public void adicionarLivro(String isbn, Livro livro) {
        livros.put(isbn, livro);
        estoque.setQuantidade(estoque.getQuantidade() + 1);
    }

    public void removerLivro(Livro livro) {
        if (estoque.getQuantidade() > 0) {
            livros.remove(livro.getIsbn(), livro);
            estoque.setQuantidade(estoque.getQuantidade() - 1);
        }else
            throw new ValidacaoException("Não há mais unidades deste livro!");
    }

    public void atualizarInformacoesLivro
            (String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAnoPublicacao);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
        }
    }

    public void venderLivro(Livro livro) {
        if (checarDisponibilidadeEstoque()) {
            removerLivro(livro);
            quantidadeLivrosVendidos++;
        } else {
            throw new ValidacaoException
                    ("Livro " + livro + " não está disponível para venda!");
        }
    }

    public void emprestarLivro(Livro livro, Cliente cliente) {
        EmprestimoLivro emprestimoLivro = new EmprestimoLivro(livro, cliente, new Date());
        if (checarDisponibilidadeEstoque() && checarDisponibilidadeparaEmprestimo()) {
            removerLivro(livro);
            historicoEmprestimos.add(emprestimoLivro);
            livroEmprestado = false;
        } else {
            throw new ValidacaoException
                    ("Livro " + livro + " não está disponível para empréstimo!");
        }
    }

    public void devolverLivro(String isbn, Livro livro) {
        adicionarLivro(isbn, livro);
        quantidadeLivrosEmprestados++;
        livroEmprestado = true;
    }

    public boolean checarDisponibilidadeEstoque() {
        return estoque.getQuantidade() > 0;
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

    public void registrarNovoCliente() {
        Scanner sc = new Scanner(System.in);
        Solicitacoes solicitacoes = new Solicitacoes();

        String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
        String email = solicitacoes.solicitarEntrada("Email: ", sc);
        String CEP = solicitacoes.solicitarEntrada("CEP: ", sc);
        String endereco = solicitacoes.solicitarEntrada("Endereço: ", sc);

        System.out.print("Data de Cadastro (dd/MM/yyyy): ");
        String dateCadastro = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Use the correct pattern
        LocalDate data = LocalDate.parse(dateCadastro, formatter);

        Cliente cliente = new Cliente(nome, email, CEP, endereco, data);
        listaClientes.add(cliente);
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
                    System.out.println("DataCadastro: " + cliente.getDataCadastro().format(formatter) + "\n");
                    encontrado = true;
                    break;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum cliente encontrado com o nome: " + nomeBusca);
        }
    }

    public void removerCliente(Cliente cliente){
        listaClientes.remove(cliente);
    }

    public void atualizarTodasInformacoesCliente(Cliente cliente, String novoNome, String novoEmail, String novoCEP, String novoEndereco) {
        atualizarNomeCliente(cliente, novoNome);
        atualizarEmailCliente(cliente, novoEmail);
        atualizarCepCliente(cliente, novoCEP);
        atualizarEnderecoCliente(cliente, novoEndereco);
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
        emprestimoLivro.getLivro().equals(livro)).collect(Collectors.toList());
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

}
