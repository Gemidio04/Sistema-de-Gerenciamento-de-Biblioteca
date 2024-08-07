package Services.Regras;

import Services.Funcionarios.Funcionario;
import java.time.LocalTime;

public class Regra {
    private boolean advertencia;
    private int quantidadeAdvertencias;

    public Regra(){
        advertencia = false;
        quantidadeAdvertencias = 0;
    }

    public boolean getAdvertencia() {
        return advertencia;
    }
    public int getQuantidadeAdvertencias() {
        return quantidadeAdvertencias;
    }

    private static boolean verificarAtraso(LocalTime horaChegada, LocalTime horaAtual) {
        return horaAtual.isAfter(horaChegada);
    }

    private static boolean verificarMalAtendimento(int numeroReclamacoes) {
        return numeroReclamacoes > 0;
    }

    private static boolean verificarDesorganizacao(boolean desorganizado) {
        return desorganizado;
    }

    private static boolean verificarUsoIndevido(boolean usoIndevido) {
        return usoIndevido;
    }

    private static boolean verificarFaltaDeRegistro(boolean registroInadequado) {
        return registroInadequado;
    }

    private static boolean verificarQuebraProtocoloSeguranca(boolean quebraProtocolo) {
        return quebraProtocolo;
    }

    public void verificarRegras(Funcionario funcionario) {
        LocalTime horaChegada = LocalTime.of(8, 0);  // Hora padrão de chegada
        LocalTime horaAtual = LocalTime.now();

        if (Regra.verificarAtraso(horaChegada, horaAtual)) {
            advertencia = true;
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + " chegou atrasado. Advertência emitida.");
        }

        int numeroReclamacoes = 3;  // Dados de exemplo
        if (Regra.verificarMalAtendimento(numeroReclamacoes)) {
            advertencia = true;
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + " recebeu reclamações de clientes. Advertência emitida.");
        }

        boolean desorganizado = true;  // Dados de exemplo
        if (Regra.verificarDesorganizacao(desorganizado)) {
            advertencia = true;
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + " deixou a biblioteca desorganizada. Advertência emitida.");
        }

        boolean usoIndevido = true;  // Dados de exemplo
        if (Regra.verificarUsoIndevido(usoIndevido)) {
            advertencia = true;
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + " usou indevidamente os recursos da biblioteca. Advertência emitida.");
        }

        boolean registroInadequado = true;  // Dados de exemplo
        if (Regra.verificarFaltaDeRegistro(registroInadequado)) {
            advertencia = true;
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + " não registrou adequadamente a entrada e saída de materiais. Advertência emitida.");
        }

        boolean quebraProtocolo = true;  // Dados de exemplo
        if (Regra.verificarQuebraProtocoloSeguranca(quebraProtocolo)) {
            advertencia = true;
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + " não seguiu os protocolos de segurança. Advertência emitida.");
        }
    }
}
