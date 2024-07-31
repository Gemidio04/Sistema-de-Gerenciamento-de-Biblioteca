package Services.Estoque;

import Livros.Livro;
import Services.Exception.ValidacaoException;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private int quantidade;

    public Estoque(){
        quantidade = 150;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


}
