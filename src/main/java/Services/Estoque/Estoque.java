package Services.Estoque;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Estoque {
    private int idEstoque;
    private String isbn;
    private int quantidade;

    public Estoque(){
        this.idEstoque = 1;
        this.quantidade = 150;
    }


    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
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

    @Override
    public String toString() {
        return "Estoque{" + "\n" +
                "id do Estoque: " + idEstoque + ".\n" +
                "isbn: " + isbn + ".\n" +
                "quantidade: " + quantidade + ".\n" +
                '}';
    }
}
