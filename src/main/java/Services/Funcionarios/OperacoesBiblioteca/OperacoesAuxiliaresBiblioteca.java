package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.InterfacesDAO.EstoqueDAO;
import SGBD.InterfacesDAO.LivroDAO;
import SGBD.JDBC.DaoFactory;
import Services.Estoque.Estoque;
import Services.Solicitacoes.Solicitacoes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OperacoesAuxiliaresBiblioteca {

    private boolean livroEmprestado = true;

    static Scanner sc = new Scanner(System.in);
    EstoqueDAO estoqueDAO = DaoFactory.createEstoqueDAO();
    LivroDAO livroDAO = DaoFactory.createLivroDAO();

    public boolean getLivroEmprestado(){
        return livroEmprestado;
    }

    public void setLivroEmprestado(boolean livroEmprestado){
        this.livroEmprestado = livroEmprestado;
    }

    public void decrementaQuantidadeEstoqueNoBanco() {
        System.out.print("Qual o idEstoque correspondente ao Livro que deseja vender: ");
        Scanner sc = new Scanner(System.in);
        int idEstoque = sc.nextInt();

        Estoque estoque = estoqueDAO.selectById(idEstoque);

        if (estoque != null) {
            // Decrementa a quantidade
            int novaQuantidade = estoque.getQuantidade() - 1;

            if (novaQuantidade <= 0) {
                System.out.println("Não há estoque suficiente para realizar a venda.");
                return;
            }
            // ATUALIZA A QUANTIDADE DO ESTOQUE NO BANCO DE DADOS:
            estoqueDAO.updateQuantidade(idEstoque, novaQuantidade);
            System.out.println("Quantidade atualizada com sucesso!");
        } else {
            System.out.println("Estoque não encontrado para o ID fornecido!");
        }
    }

    public Livro buscarLivroPorIsbn(String ISBN) {
        Livro livro = livroDAO.selectByIsbn(ISBN);
        if (livro == null) {
            System.out.println("Esse ISBN não é de nenhum Livro presente na Biblioteca!");
        }
        return livro;
    }

    // VERIFICA SE O LIVRO EXISTE NA BIBLIOTECA E SE ELE TEM ESTOQUE:
    public boolean checarDisponibilidadeLivro(String ISBN) {
        // 1º PASSO: VERIFICAR SE O LIVRO EXISTE NA BIBLIOTECA:
        Livro livro = buscarLivroPorIsbn(ISBN);
        if (livro == null) {
            System.out.println("O livro com ISBN " + ISBN + " não existe no Catálogo de Livros da Biblioteca.");
            return false; // Livro não existe
        }

        // 2º PASSO: VERIFICAR SE O LIVRO TEM ESTOQUE:
        Estoque estoqueLivro = encontrarEstoquePorIsbn(ISBN);
        if (estoqueLivro == null || estoqueLivro.getQuantidade() <= 0) {
            System.out.println("O livro com ISBN " + ISBN + " está sem estoque.");
            return false;
        }
        // LIVRO EXISTE E TEM ESTOQUE DISPONÍVEL:
        return true;
    }

    // MÉTODO AUXILIAR PARA BUSCAR O ESTOQUE COM BASE NO ISBN DO LIVRO:
    public Estoque encontrarEstoquePorIsbn(String isbn) {
        // SIMULA A BUSCA DE ESTOQUE ASSOCIADO AO ISBN FORNECIDO:
        return estoqueDAO.selectByIsbn(isbn);
    }

    public static String solicitarEntrada(String mensagem) {
        Solicitacoes solicitacoes = new Solicitacoes();
        return solicitacoes.solicitarEntrada(mensagem);
    }

    public static void solicitarNovosDadosLivro(Livro livro) {

        livro.setTitulo(solicitarEntrada("Título: "));
        livro.setEditora(solicitarEntrada("Editora: "));
        livro.setGenero(solicitarEntrada("Gênero: "));
        livro.setAutor(solicitarEntrada("Autor: "));

        // SOLICITAR E VALIDAR A DATA:
        while (true) {
            try {
                System.out.print("Data de Publicação (dd/MM/yyyy): ");
                String dataPublicacao = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                livro.setDataPublicacao(LocalDate.parse(dataPublicacao, formatter).toString());
                break;
            } catch (Exception e) {
                System.out.println("Data inválida. Certifique-se de usar o formato dd/MM/yyyy.");
            }
        }

        livro.setSinopse(solicitarEntrada("Sinopse: "));
    }

    ClienteDAO clienteDAO = DaoFactory.createClienteDAO();

    public EmprestimoLivro solicitaDadosEmprestimoLivro() {
        Solicitacoes solicitacoes = new Solicitacoes();
        EmprestimoLivro emprestimoLivro = new EmprestimoLivro();

        try {
            String ISBN = solicitacoes.solicitarEntrada("\nQual o ISBN do Livro que deseja pegar Emprestado: ");

            if (!checarDisponibilidadeLivro(ISBN)) {
                System.out.println("Livro indisponível para empréstimo!");
                return null; // Retorna null caso o livro não esteja disponível
            }

            int idCliente = solicitacoes.solicitarIdCliente(clienteDAO);

            emprestimoLivro.setIdCliente(idCliente);
            emprestimoLivro.setIsbn(ISBN);
            emprestimoLivro.setCliente(clienteDAO.selectById(idCliente));

            System.out.println("\nDatas relacionadas ao empréstimo do Livro:");
            System.out.print("idEmprestimoLivro: ");
            emprestimoLivro.setIdEmprestimoLivro(sc.nextInt());
            emprestimoLivro.setDataEmprestimo(solicitacoes.solicitarData("Data do Empréstimo (dd/MM/yyyy): "));
            emprestimoLivro.setDataDevolucaoEmprestimo(solicitacoes.solicitarData("Data de Devolução (dd/MM/yyyy): "));

            return emprestimoLivro;
        } catch (RuntimeException ex) {
            System.out.println("Erro relacionado ao Banco de Dados: " + ex.getMessage());
            return null; // Retorna null em caso de erro
        }
    }

    public void exibirSinopseLivro(Livro livro) {
        System.out.println(livro.getSinopse());
    }

    public boolean checarDisponibilidadeparaEmprestimo() {
        return livroEmprestado;
    }

    public void atualizarIdCLiente(Cliente cliente, int idCliente) {
        cliente.setIdCliente(idCliente);
    }

    public void atualizarNomeCliente(Cliente cliente, String novoNome) {
        cliente.setNome(novoNome);
    }

    public void atualizarEmailCliente(Cliente cliente, String novoEmail) {
        cliente.setEmail(novoEmail);
    }

    public void atualizarCepCliente(Cliente cliente, String novoCEP) {
        cliente.setCEP(novoCEP);
    }

    public void atualizarEnderecoCliente(Cliente cliente, String novoEndereco) {
        cliente.setEndereco(novoEndereco);
    }

    public void atualizarDataCadastroCliente(Cliente cliente, String data) {
        cliente.setDataCadastro(data);
    }


}
