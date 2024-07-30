package Services.Funcionarios;

import Services.Exception.ValidacaoException;

import java.time.LocalDate;
import java.time.Period;

public class Promocao {

    public void validar(Funcionario funcionarios){
        Cargo cargoAtual = funcionarios.getCargo();
        if(Cargo.GERENTE==cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    }

    public boolean meta() {
        int metaLivrosVendidos = 0;
        int metaLivrosEmprestados = 0;
        boolean experienciaMinima = true;

        Funcionario funcionario = new Funcionario();
        if (funcionario.getCargo() == Cargo.ASSISTENTE) {
            metaLivrosVendidos = 100;
            metaLivrosEmprestados = 200;
        } else if (funcionario.getCargo() == Cargo.BIBLIOTECARIO) {
            metaLivrosVendidos = 300;
            metaLivrosEmprestados = 500;
            experienciaMinima = Period.between(funcionario.getDataContratacao(), LocalDate.now()).getYears() >= 5;
        }

        Assistente assistente = new Assistente();
        return assistente.getQuantidadeLivrosVendidos() >= metaLivrosVendidos && assistente.getQuantidadeLivrosEmprestados() >= metaLivrosEmprestados && experienciaMinima;
    }
}
