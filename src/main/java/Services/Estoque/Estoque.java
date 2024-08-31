package Services.Estoque;

import SGBD.InterfacesDAO.EstoqueDAO;
import SGBD.JDBC.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Estoque {
    private int idEstoque;
    private String isbn;
    private int quantidade;

    public Estoque(){
    }

    public Estoque(String isbn, int quantidade) {
        this.isbn = isbn;
        this.quantidade = quantidade;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static Estoque instanciaEstoque(ResultSet resultSet) throws SQLException {
        Estoque estoque = new Estoque();
        estoque.setIdEstoque(resultSet.getInt("idEstoque"));
        estoque.setIsbn(resultSet.getString("isbn"));
        estoque.setQuantidade(resultSet.getInt("quantidade"));
        return estoque;
    }

    public void atualizaQuantidade(int novaQuantidade) {
        // Atualiza a quantidade do objeto em memória:
        this.quantidade = novaQuantidade;
        // Atualiza a quantidade no banco de dados:
        EstoqueDAO estoqueDAO = DaoFactory.createEstoqueDAO();
        estoqueDAO.update(this);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n");
        sb.append("Id do Estoque: ").append(idEstoque).append("\n");
        sb.append("ISBN: ").append(isbn).append("\n");
        sb.append("Quantidade: ").append(quantidade).append("\n");
        return sb.toString();
    }
}
