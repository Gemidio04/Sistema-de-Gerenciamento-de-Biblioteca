package Services.Meta;

import Services.Funcionarios.Assistentes.assistenteEmprestimoVendaLivros;
import Services.Funcionarios.Cargo;
import Services.Funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.Period;

public class Meta {

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

        assistenteEmprestimoVendaLivros assistenteEmprestimoVendaLivros = new assistenteEmprestimoVendaLivros();
        return assistenteEmprestimoVendaLivros.getQuantidadeLivrosVendidos() >= metaLivrosVendidos && assistenteEmprestimoVendaLivros.getQuantidadeLivrosEmprestados() >= metaLivrosEmprestados && experienciaMinima;
    }
}
