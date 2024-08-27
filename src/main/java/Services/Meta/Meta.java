package Services.Meta;

import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Meta {

    public boolean meta() {
        int metaLivrosVendidos = 0;
        int metaLivrosEmprestados = 0;
        boolean experienciaMinima = true;

        Funcionario funcionario = new Funcionario();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataContratacao = LocalDate.parse(funcionario.getDataContratacao(), formatter);

        if (funcionario.getCargo() == Cargo.ASSISTENTE) {
            metaLivrosVendidos = 100;
            metaLivrosEmprestados = 200;
        } else if (funcionario.getCargo() == Cargo.BIBLIOTECARIO) {
            metaLivrosVendidos = 300;
            metaLivrosEmprestados = 500;
            experienciaMinima = Period.between(dataContratacao, LocalDate.now()).getYears() >= 5;
        }

        return experienciaMinima;
    }
}
