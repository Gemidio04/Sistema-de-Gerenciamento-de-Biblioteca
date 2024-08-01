package Services.Regras;

import Livros.Livro;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Regra {

    public static boolean verificarAtraso(LocalTime horaChegada, LocalTime horaAtual) {
        return horaAtual.isAfter(horaChegada);
    }
    public static boolean verificarMalAtendimento(int numeroReclamacoes) {
        return numeroReclamacoes > 0;
    }

    public static boolean verificarDesorganizacao(boolean desorganizado) {
        return desorganizado;
    }

    public static boolean verificarUsoIndevido(boolean usoIndevido) {
        return usoIndevido;
    }

    public static boolean verificarFaltaDeRegistro(boolean registroInadequado) {
        return registroInadequado;
    }

    public static boolean verificarQuebraProtocoloSeguranca(boolean quebraProtocolo) {
        return quebraProtocolo;
    }
}
