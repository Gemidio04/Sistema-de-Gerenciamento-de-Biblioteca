package Services.Validacao;

import Services.Exception.ValidacaoException;
import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

public class Validacao {

    public void validar(Funcionario funcionario){
        Cargo cargoAtual = funcionario.getCargo();
        if(Cargo.GERENTE==cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    }
}
