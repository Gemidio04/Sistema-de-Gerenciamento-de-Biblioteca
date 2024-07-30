package Services.Meta;

import Services.Funcionarios.assistente;
import Services.Funcionarios.Cargo;
import Services.Funcionarios.funcionario;

import java.time.LocalDate;
import java.time.Period;

public class meta {

    public boolean meta() {
        int metaLivrosVendidos = 0;
        int metaLivrosEmprestados = 0;
        boolean experienciaMinima = true;

        funcionario funcionario = new funcionario();
        if (funcionario.getCargo() == Cargo.ASSISTENTE) {
            metaLivrosVendidos = 100;
            metaLivrosEmprestados = 200;
        } else if (funcionario.getCargo() == Cargo.BIBLIOTECARIO) {
            metaLivrosVendidos = 300;
            metaLivrosEmprestados = 500;
            experienciaMinima = Period.between(funcionario.getDataContratacao(), LocalDate.now()).getYears() >= 5;
        }

        assistente assistente = new assistente();
        return assistente.getQuantidadeLivrosVendidos() >= metaLivrosVendidos && assistente.getQuantidadeLivrosEmprestados() >= metaLivrosEmprestados && experienciaMinima;
    }
}
