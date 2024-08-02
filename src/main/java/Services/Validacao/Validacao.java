package Services.Validacao;

import Services.Exception.ValidacaoException;
import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

public class Validacao {

    public void validar(Funcionario funcionarios){
        Cargo cargoAtual = funcionarios.getCargo();
        if(Cargo.GERENTE==cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    }
}
