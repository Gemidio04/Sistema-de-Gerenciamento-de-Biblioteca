package services.funcionarios;

public enum Cargo {

    PRESERVADOR {
        @Override
        public Cargo ProximoCargo() {
            return PRESERVADOR;
        }
    },
    DESENVOLVERDOR_COMUNITARIO {
        @Override
        public Cargo ProximoCargo() {
            return DESENVOLVERDOR_COMUNITARIO;
        }
    },
    SEGURANCA {
        @Override
        public Cargo ProximoCargo() {
            return SEGURANCA;
        }
    },
    ANALISTA_DO_SISTEMA {
        @Override
        public Cargo ProximoCargo() {
            return ANALISTA_DO_SISTEMA;
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
}
