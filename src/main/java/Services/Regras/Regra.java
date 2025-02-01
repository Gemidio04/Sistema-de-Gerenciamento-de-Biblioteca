package Services.Regras;

import Services.Funcionarios.Funcionario;

import java.time.LocalTime;

public class Regra {
    private int quantidadeAdvertencias;

    public Regra() {
        this.quantidadeAdvertencias = 0;
    }

    public int getQuantidadeAdvertencias() {
        return quantidadeAdvertencias;
    }

    public void setQuantidadeAdvertencias(int quantidadeAdvertencias) {
        this.quantidadeAdvertencias = quantidadeAdvertencias;
    }

    // Aplica regras de advertência
    public void aplicarRegras(
            Funcionario funcionario,
            boolean ausencia,
            LocalTime horaChegada,
            int numeroReclamacoes,
            boolean desorganizado,
            boolean usoIndevido,
            boolean registroInadequado,
            boolean quebraProtocolo
    ) {
        // Consolidar verificações e emitir advertências
        verificarEEmitirAdvertencia(ausencia, funcionario, "Falta ao trabalho.");
        verificarEEmitirAdvertencia(verificarAtraso(horaChegada), funcionario, "Atraso ao trabalho.");
        verificarEEmitirAdvertencia(numeroReclamacoes > 0, funcionario, "Reclamações de atendimento.");
        verificarEEmitirAdvertencia(desorganizado, funcionario, "Desorganização na biblioteca.");
        verificarEEmitirAdvertencia(usoIndevido, funcionario, "Uso indevido dos recursos da biblioteca.");
        verificarEEmitirAdvertencia(registroInadequado, funcionario, "Falta de registro adequado.");
        verificarEEmitirAdvertencia(quebraProtocolo, funcionario, "Quebra de protocolos de segurança.");
    }

    // Método auxiliar para verificar condição e emitir advertência
    private void verificarEEmitirAdvertencia(boolean condicao, Funcionario funcionario, String motivo) {
        if (condicao) {
            quantidadeAdvertencias++;
            System.out.println("Funcionário " + funcionario.getNome() + ": Advertência emitida! Motivo: " + motivo);
        }
    }

    // Verifica atraso em relação à hora padrão de chegada
    private boolean verificarAtraso(LocalTime horaChegada) {
        return LocalTime.now().isAfter(horaChegada);
    }
}
