package Services.Funcionarios;

import Services.Exception.ValidacaoException;

public class Promocao {

    public void validar(Funcionario funcionarios){
        Cargo cargoAtual = funcionarios.getCargo();
        if(Cargo.GERENTE==cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    }
}
