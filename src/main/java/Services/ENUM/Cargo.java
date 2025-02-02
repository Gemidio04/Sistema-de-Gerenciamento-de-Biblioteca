package Services.ENUM;

public enum Cargo {

    CARGO_PADRAO {
        @Override
        public Cargo ProximoCargo() {
            return CARGO_PADRAO;
        }
    },
    GESTOR_PROGRAMAS_EVENTOS {
        @Override
        public Cargo ProximoCargo() {
            return GESTOR_PROGRAMAS_EVENTOS;
        }
    },
    SEGURANCA_PROFISSIONAL {
        @Override
        public Cargo ProximoCargo() {
            return SEGURANCA_PROFISSIONAL;
        }
    },
    ANALISTA_DE_SISTEMAS {
        @Override
        public Cargo ProximoCargo() {
            return ANALISTA_DE_SISTEMAS;
        }
    },
    RH {
        @Override
        public Cargo ProximoCargo() {
            return RH;
        }
    },
    ASSISTENTE {
        @Override
        public Cargo ProximoCargo() {
            return BIBLIOTECARIO;
        }
    },

    BIBLIOTECARIO {
        @Override
        public Cargo ProximoCargo() {
            return GERENTE;
        }
    },
    GERENTE {
        @Override
        public Cargo ProximoCargo() {
            return GERENTE;
        }
    };

    public abstract Cargo ProximoCargo();

    public static String obterDescricao(Cargo cargo) {
        switch (cargo) {
            case CARGO_PADRAO:
                return "Cargo Padrão";
            case GESTOR_PROGRAMAS_EVENTOS:
                return "Gestor de Programas e Eventos";
            case SEGURANCA_PROFISSIONAL:
                return "Segurança";
            case ANALISTA_DE_SISTEMAS:
                return "Analista De Sistemas";
            case RH:
                return "Recursos Humanos";
            case ASSISTENTE:
                return "Assistente";
            case BIBLIOTECARIO:
                return "Bibliotecaria(o)";
            case GERENTE:
                return "Gerente";
            default:
                throw new IllegalArgumentException("Cargo desconhecido: " + cargo);
        }
    }

}
