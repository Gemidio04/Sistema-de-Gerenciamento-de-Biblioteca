package Services.Funcionarios.OperacoesBiblioteca;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.SegurancaProfissional;
import Services.Promocao.Promocao;
import Services.Regras.Regra;

import java.util.List;

public class Gerente extends OperacoesBiblioteca {
    private Regra regra;

    public void promover(Promocao promocao, Funcionario funcionario, Cargo novoCargo) {
        promocao.promover(funcionario, novoCargo);
    }

//    public void contratarFuncionario(Funcionario novoFuncionario) {
//        Locale.setDefault(Locale.US);
//        Solicitacoes solicitacoes = new Solicitacoes();
//
//        try (Scanner sc = new Scanner(System.in)) {
//            String nome = solicitacoes.solicitarEntrada("Nome: ", sc);
//            String email = solicitacoes.solicitarEntrada("Email: ", sc);
//            String CPF = solicitacoes.solicitarEntrada("CPF: ", sc);
//            String turno = solicitacoes.solicitarEntrada("Turno: ", sc);
//            String dataContratacao = solicitacoes.solicitarEntrada("Data da Contratação: ", sc);
//            Double salario = solicitacoes.solicitarSalario("Salário: ", sc);
//
//            Cargo.converteCargoEnum((OperacoesBiblioteca) novoFuncionario);
//
//            novoFuncionario.setNome(nome);
//            novoFuncionario.setEmail(email);
//            novoFuncionario.setCPF(CPF);
//            novoFuncionario.setTurno(turno);
//            novoFuncionario.setDataContratacao(dataContratacao);
//            novoFuncionario.setSalario(salario);
//
//            novoFuncionario.getListaDeFuncionarios().add(novoFuncionario);
//            System.out.println("\nFuncionário contratado: " + novoFuncionario.getNome() + ", Cargo: " + novoFuncionario.getCargo());
//        }
//    }
//
//    public void demitirFuncionario(Funcionario funcionario) {
//        Regra regra = new Regra();
//        if (regra.getQuantidadeAdvertencias() == 3 || regra.getAdvertencia()) {
//            System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
//        }
//    }

    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(Livro livro) {
        super.removerLivro(livro);
    }

    public void atualizarInformacoesLivro() {
        super.atualizarInformacoesLivro();
    }

    public void venderLivro() {
        super.venderLivro();
    }

    public void emprestarLivro(EmprestimoLivro emprestimoLivro) {
        super.emprestarLivro(emprestimoLivro);
    }

//    public void devolverLivro(String isbn, Livro livro) {
//        super.devolverLivro(isbn, livro);
//    }

    public boolean checarDisponibilidadeEstoque() {
        return getEstoque().getQuantidade() > 0;
    }


    public void buscarLivroIsbn(String isbn) {
        super.buscarLivroIsbn(isbn);
    }

    public Livro buscarLivroAutor(String autor) {
        return super.buscarLivroAutor(autor);
    }

    public Livro buscarLivroTitulo(String titulo) {
        return super.buscarLivroTitulo(titulo);
    }

    public void cadastrarNovoCliente() {
        super.cadastrarNovoCliente();
    }

    public void listaTodosClientes() {
        super.listaTodosClientes();
    }

    public void excluirCadastroCliente(Cliente cliente){
        super.excluirCadastroCliente(cliente);
    }

    public void atualizarTodasInformacoesCliente() {
        super.atualizarTodasInformacoesCliente();
    }

    public List<EmprestimoLivro> historicoDeLivro(Livro livro) {
        return super.historicoDeLivro(livro);
    }

    public List<EmprestimoLivro> historicoDeUsuario(Cliente cliente) {
        return super.historicoDeUsuario(cliente);
    }

    public List<EmprestimoLivro> historicoCompletoLivrosEmprestados() {
        return super.historicoCompletoLivrosEmprestados();
    }

    @Override
    public void verificandoTodoSistemaBiblioteca(){
        super.verificandoTodoSistemaBiblioteca();
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }
}
