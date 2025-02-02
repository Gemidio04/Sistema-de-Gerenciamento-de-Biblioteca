package Services.Solicitacoes;

import Menu.MenuImplementacao;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.InterfacesDAO.LivroDAO;
import SGBD.JDBC.DaoFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class Solicitacoes {

    private final Scanner sc;

    public Solicitacoes(Scanner sc) {
        this.sc = sc;
    }

    public String solicitarEntrada(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    public int solicitarIdCliente(ClienteDAO clienteDAO) {
        int tentativas = 3;

        while (tentativas > 0) {
            System.out.print("ID do Cliente que pegou o livro emprestado: ");
            if (sc.hasNextInt()) {
                int idCliente = sc.nextInt();
                sc.nextLine(); // Consumir quebra de linha

                if (Optional.ofNullable(clienteDAO.selectById(idCliente)).isPresent()) {
                    return idCliente;
                }
                System.out.println("Cliente não encontrado.");
            } else {
                sc.nextLine(); // Consumir entrada inválida
            }

            tentativas--;
            System.out.printf("Tentativas restantes: %d\n", tentativas);
        }

        System.out.println("Você digitou o ID incorretamente 3 vezes!");
        MenuImplementacao.menuFuncionario();
        return -1;
    }

    public String solicitarISBN() {
        LivroDAO livroDAO = DaoFactory.createLivroDAO();
        int tentativas = 3;

        while (tentativas > 0) {
            String ISBN = solicitarEntrada("\nISBN: ");

            if (Optional.ofNullable(livroDAO.selectByIsbn(ISBN)).isPresent()) {
                return ISBN;
            }

            System.out.println("Livro não encontrado no catálogo.");
            tentativas--;
            System.out.printf("Tentativas restantes: %d\n", tentativas);
        }

        System.out.println("ISBN informado incorretamente 3 vezes.");
        MenuImplementacao.menuFuncionario();
        return null;
    }

    public String solicitarData(String mensagem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                return LocalDate.parse(solicitarEntrada(mensagem), formatter).toString();
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            }
        }
    }

    public double solicitarSalario(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextDouble()) {
                double salario = sc.nextDouble();
                sc.nextLine(); // Consumir quebra de linha
                return salario;
            }
            System.out.println("Salário inválido. Digite novamente.");
            sc.next(); // Descarta entrada inválida
        }
    }
}
