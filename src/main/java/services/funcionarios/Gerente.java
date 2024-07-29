package services.funcionarios;

import java.util.Date;

public class Gerente extends Funcionarios{

    public void contrataGerente(String name, String email, String CPF, String turno, Date dataContratacao, Cargo cargo){
        this.setNome(name);
        this.setEmail(email);
        this.setCPF(CPF);
        this.setTurno(turno);
        this.setDataContratacao(dataContratacao);
        this.setCargo(cargo);
    }

//    public void sobeCargo(Promocao promocao, Cargo novoCargo){
//        Funcionarios funcionarios = new Funcionarios();
//        promocao.validar(funcionarios);
//        funcionarios.setCargo(novoCargo.ProximoCargo());
//    }

}
