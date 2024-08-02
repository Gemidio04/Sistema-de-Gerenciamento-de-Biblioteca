package Services.ENUM;

public enum MesesAno {
    JANEIRO,
    FEVEREIRO,
    MARCO,
    ABRIL,
    MAIO,
    JUNHO,
    JULHO,
    AGOSTO,
    SETEMBRO,
    OUTUBRO,
    NOVEMBRO,
    DEZEMBRO;

    static int quantidadeMeses = 0;

    public static int getQuantidadeMeses() {
        return quantidadeMeses;
    }

    public static void setQuantidadeMeses(int quantidadeMeses) {
        MesesAno.quantidadeMeses = quantidadeMeses;
    }

    public static void verificaMeses() {
        MesesAno mes = null;

        switch (mes) {
            case JANEIRO:
                String mesesAno = JANEIRO.toString();
                quantidadeMeses++;
                break;
            case FEVEREIRO:
                mesesAno = FEVEREIRO.toString();
                quantidadeMeses++;
                break;
            case MARCO:
                mesesAno = MARCO.toString();
                quantidadeMeses++;
                break;
            case ABRIL:
                mesesAno = ABRIL.toString();
                quantidadeMeses++;
                break;
            case MAIO:
                mesesAno = MAIO.toString();
                quantidadeMeses++;
                break;
            case JUNHO:
                mesesAno = JUNHO.toString();
                quantidadeMeses++;
                break;
            case JULHO:
                mesesAno = JULHO.toString();
                quantidadeMeses++;
                break;
            case AGOSTO:
                mesesAno = AGOSTO.toString();
                quantidadeMeses++;
                break;
            case SETEMBRO:
                mesesAno = SETEMBRO.toString();
                quantidadeMeses++;
                break;
            case OUTUBRO:
                mesesAno = OUTUBRO.toString();
                quantidadeMeses++;
                break;
            case NOVEMBRO:
                mesesAno = NOVEMBRO.toString();
                quantidadeMeses++;
                break;
            case DEZEMBRO:
                mesesAno = DEZEMBRO.toString();
                quantidadeMeses++;
                break;
            default:
                throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }
}

