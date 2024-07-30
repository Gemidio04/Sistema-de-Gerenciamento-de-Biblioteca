package Services.Funcionarios;


import Services.Meta.meta;
import Services.Validacao.validacao;

public class promocao {
    private validacao validador;
    private meta verificadorMeta;

    public promocao(validacao validador, meta verificadorMeta) {
        this.validador = validador;
        this.verificadorMeta = verificadorMeta;
    }

    public void promover(funcionario funcionario) {
        validador.validar(funcionario);
        if (verificadorMeta.meta()) {
            System.out.println("Funcionário elegível para promoção!");
        } else {
            System.out.println("Funcionário não atingiu as metas para promoção!");
        }
    }
}
