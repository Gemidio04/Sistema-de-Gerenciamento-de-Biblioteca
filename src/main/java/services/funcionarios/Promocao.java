package services.funcionarios;

import services.exception.ValidacaoException;

public class Promocao {

    public void validar(Funcionarios funcionarios){
        Cargo cargoAtual = funcionarios.getCargo();
        if(Cargo.GERENTE==cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
    }

//    public void meta(Funcionarios funcionarios){
//        int meta = 0;
//        if(funcionarios.getCargo()==Cargo.ASSISTENTE){
//            meta = 150;
//            if(Cargo.)
//        };
//    }
}
