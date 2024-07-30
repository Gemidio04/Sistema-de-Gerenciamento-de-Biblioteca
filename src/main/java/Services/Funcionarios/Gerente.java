package Services.Funcionarios;

import java.time.LocalDate;
import java.util.Date;

public class Gerente extends Funcionario {
    Funcionario funcionario = new Funcionario();

    public void contrataGerente(String name, String email, String CPF, String turno, LocalDate dataContratacao, Cargo cargo){
        this.setNome(name);
        this.setEmail(email);
        this.setCPF(CPF);
        this.setTurno(turno);
        this.setDataContratacao(dataContratacao);
        this.setCargo(cargo);
    }

    public void sobeCargo(Promocao promocao, Cargo novoCargo){
        promocao.meta();
        promocao.validar(funcionario);
        funcionario.setCargo(novoCargo.ProximoCargo());
    }

}
