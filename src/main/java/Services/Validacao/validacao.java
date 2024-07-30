package Services.Validacao;

import Services.Exception.ValidacaoException;
import Services.Funcionarios.Cargo;
import Services.Funcionarios.funcionario;

public class validacao {

    public void validar(funcionario funcionarios){
        Cargo cargoAtual = funcionarios.getCargo();
        if(Cargo.GERENTE==cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    }
}
