package Services.Promocao;


import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;
import Services.Meta.Meta;
import Services.Validacao.Validacao;

public class Promocao {
    private Validacao validador;
    private Meta verificadorMeta;

    public Promocao(Validacao validador, Meta verificadorMeta) {
        this.validador = validador;
        this.verificadorMeta = verificadorMeta;
    }

    public void promover(Funcionario funcionario, Cargo novoCargo) {
        validador.validar(funcionario);
        if (verificadorMeta.meta()) {
            System.out.println("Funcionário elegível para promoção!");
            funcionario.setCargo(novoCargo.ProximoCargo());
        } else {
            System.out.println("Funcionário não atingiu as metas para promoção!");
        }
    }
}
