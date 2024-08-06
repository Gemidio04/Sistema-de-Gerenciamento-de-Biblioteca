package Livros;

import Clientes.Cliente;

import java.util.Date;

public class EmprestimoLivro {

    private Livro livro;
    private Cliente cliente;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public EmprestimoLivro(Livro livro, Cliente cliente, Date dataEmprestimo) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Livro getLivro(){
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }
}

