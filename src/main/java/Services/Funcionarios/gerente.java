package Services.Funcionarios;

import java.time.LocalDate;

public class gerente extends funcionario {
    funcionario funcionario;

    public gerente(Services.Funcionarios.funcionario funcionario){
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

    public void sobeCargo(promocao promocao, Cargo novoCargo){
        promocao.promover(funcionario);
        funcionario.setCargo(novoCargo.ProximoCargo());
    }

}
