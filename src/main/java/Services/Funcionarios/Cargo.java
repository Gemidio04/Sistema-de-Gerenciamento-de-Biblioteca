package Services.Funcionarios;

import Services.Funcionarios.OperacoesBiblioteca.Gerente;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

import java.util.Scanner;

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

    public static void converteCargoEnum(OperacoesBiblioteca novoFuncionario){
        Scanner sc = new Scanner(System.in);
        Gerente gerente = new Gerente();

        try (sc) {
            System.out.print("Cargo: (ASSISTENTE, BIBLIOTECARIO, GERENTE): ");
            String cargoInput = sc.nextLine().toUpperCase();
            Cargo cargo = Cargo.valueOf(cargoInput);
            novoFuncionario.setCargo(cargo);
            gerente.getListaDeFuncionarios().add(novoFuncionario);
        } catch (IllegalArgumentException e) {
            System.out.println("Cargo inválido. Por favor, insira um cargo válido.");
        }
    }
}
