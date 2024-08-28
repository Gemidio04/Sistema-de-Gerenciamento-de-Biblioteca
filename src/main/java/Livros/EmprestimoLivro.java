package Livros;

import Clientes.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmprestimoLivro {
    private int idEmprestimoLivro;
    private Cliente cliente;
    private String isbn;
    private int idCliente;
    private String dataEmprestimo;
    private String dataDevolucaoEmprestimo;

    public EmprestimoLivro() {
    }

    public EmprestimoLivro(int idEmprestimoLivro, String isbn, int idCliente, String dataEmprestimo, String dataDevolucaoEmprestimo) {
        this.idEmprestimoLivro = idEmprestimoLivro;
        this.isbn = isbn;
        this.idCliente = idCliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoEmprestimo = dataDevolucaoEmprestimo;
    }

    public int getIdEmprestimoLivro() {
        return idEmprestimoLivro;
    }

    public void setIdEmprestimoLivro(int idEmprestimoLivro) {
        this.idEmprestimoLivro = idEmprestimoLivro;
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

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucaoEmprestimo() {
        return dataDevolucaoEmprestimo;
    }

    public void setDataDevolucaoEmprestimo(String dataDevolucaoEmprestimo) {
        this.dataDevolucaoEmprestimo = dataDevolucaoEmprestimo;
    }

    public static EmprestimoLivro instanciaEmprestimoLivro(ResultSet rs) throws SQLException {
        EmprestimoLivro emprestimoLivro = new EmprestimoLivro();
        emprestimoLivro.setIdEmprestimoLivro(rs.getInt("idEmprestimoLivro"));
        emprestimoLivro.setIsbn(rs.getString("isbn"));
        emprestimoLivro.setIdCliente(rs.getInt("idCliente"));
        emprestimoLivro.setDataEmprestimo(rs.getString("dataEmprestimo"));
        emprestimoLivro.setDataDevolucaoEmprestimo(rs.getString("dataDevolucaoEmprestimo"));
        return emprestimoLivro;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n");
        sb.append("id do Empréstimo do Livro: ").append(idEmprestimoLivro).append(",\n");
        sb.append("ISBN: ").append(isbn).append(",\n");
        sb.append("id do Cliente: ").append(idCliente).append(",\n");
        sb.append("Data do Empréstimo: ").append(dataEmprestimo).append(",\n");
        sb.append("Data da devolucao do Empréstimo: ").append(dataDevolucaoEmprestimo).append(".\n");
        return sb.toString();
    }
}


