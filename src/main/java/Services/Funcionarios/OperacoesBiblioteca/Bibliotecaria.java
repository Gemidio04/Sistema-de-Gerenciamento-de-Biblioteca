package Services.Funcionarios.OperacoesBiblioteca;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;
import Services.Regras.Regra;
import java.time.LocalTime;

public class Bibliotecaria extends OperacoesBiblioteca {
    private boolean advertencia;
    private int quantidadeAdvertencias;

    public Bibliotecaria(){
        advertencia = false;
        quantidadeAdvertencias = 0;
    }

    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(String isbn) {
        super.removerLivro(isbn);
    }

    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        super.atualizarInformacoes(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
    }
    public void venderLivro(String isbn) {
        if (checarDisponibilidade()) {
            removerLivro(isbn);
            setQuantidadeLivrosVendidos(getQuantidadeLivrosVendidos() + 1);
        } else {
            throw new ValidacaoException
                    ("Livro com ISBN " + isbn + " não está disponível para venda!");
        }
    }

    public void emprestarLivro(String isbn) {
        if (checarDisponibilidade()) {
            removerLivro(isbn);
            setQuantidadeLivrosEmprestados(getQuantidadeLivrosEmprestados() + 1);
        } else {
            throw new ValidacaoException
                    ("Livro com ISBN " + isbn + " não está disponível para empréstimo!");
        }
    }

    public void devolverLivro(String isbn, Livro livro) {
        adicionarLivro(isbn, livro);
    }

    public boolean checarDisponibilidade() {
        return getEstoque().getQuantidade() > 0;
    }

    public Livro buscarLivroIsbn(String isbn) {
        return super.buscarLivroIsbn(isbn);
    }

    public Livro buscarLivroAutor(String autor) {
        return super.buscarLivroAutor(autor);
    }

    public Livro buscarLivroTitulo(String titulo) {
        return super.buscarLivroTitulo(titulo);
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

    public void demitirAssistente(Funcionario funcionario) {
        if (quantidadeAdvertencias == 3 || advertencia) {
            System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
        }
    }
}
