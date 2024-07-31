package Services.Funcionarios;

import java.time.LocalDate;

public class Gerente extends Funcionario {
    Funcionario funcionario;

    public Gerente(Funcionario funcionario){
        this.funcionario = funcionario;
    }
    public void contrataGerente(String name, String email, String CPF, String turno, LocalDate dataContratacao, Cargo cargo){
        this.setNome(name);
        this.setEmail(email);
        this.setCPF(CPF);
        this.setTurno(turno);
        this.setDataContratacao(dataContratacao);
        this.setCargo(cargo);
    }

    public void sobeCargo(Promocao promocao, Cargo novoCargo){
        promocao.promover(funcionario);
        funcionario.setCargo(novoCargo.ProximoCargo());
    }

}
