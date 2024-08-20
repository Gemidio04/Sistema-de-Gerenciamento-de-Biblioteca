package Livros;

import Clientes.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmprestimoLivro {
    private int idEmprestimoLivro;
    private Livro livro;
    private Cliente cliente;
    private String isbn;
    private int idCliente;
    private Date dataEmprestimo;
    private Date dataDevolucaoEmprestimo;

    public EmprestimoLivro() {
    }

    public EmprestimoLivro(Livro livro, Cliente cliente, Date date) {
    }

    public EmprestimoLivro(String isbn, int idCliente, Date dataEmprestimo) {
        this.idEmprestimoLivro = 1;
        this.isbn = isbn;
        this.idCliente = idCliente;
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setIdEmprestimoLivro(int idEmprestimoLivro) {
        this.idEmprestimoLivro = idEmprestimoLivro;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucaoEmprestimo(Date dataDevolucaoEmprestimo) {
        this.dataDevolucaoEmprestimo = dataDevolucaoEmprestimo;
    }

    public static EmprestimoLivro instanciaEmprestimoLivro(ResultSet rs) throws SQLException {
        EmprestimoLivro emprestimoLivro = new EmprestimoLivro();
        emprestimoLivro.setIdEmprestimoLivro(rs.getInt("idEmprestimoLivro"));
        emprestimoLivro.setIsbn(rs.getString("isbn"));
        emprestimoLivro.setIdCliente(rs.getInt("idCliente"));
        emprestimoLivro.setDataEmprestimo(rs.getDate("dataEmprestimo"));
        emprestimoLivro.setDataDevolucaoEmprestimo(rs.getDate("dataDevolucaoEmprestimo"));
        return emprestimoLivro;
    }

    @Override
    public String toString() {
        return "EmprestimoLivro{ " + "\n" +
                "id do EmprestimoLivro: " + idEmprestimoLivro + ".\n" +
                "ISBN: " + isbn + ".\n" +
                "id do Cliente: " + idCliente + ".\n" +
                "Data do Empréstimo: " + dataEmprestimo + ".\n" +
                "Data de devolução do Empréstimo: " + dataDevolucaoEmprestimo + ".\n" +
                '}';
    }


}


