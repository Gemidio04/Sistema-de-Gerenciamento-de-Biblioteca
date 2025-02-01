package Services.Solicitacoes;

import Menu.MenuImplementacao;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.InterfacesDAO.LivroDAO;
import SGBD.JDBC.DaoFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solicitacoes {

    public String solicitarEntrada(String mensagem, Scanner sc) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }
    public String solicitarEntrada(String mensagem) {
        System.out.print(mensagem);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    Scanner sc = new Scanner(System.in);

    public int solicitarIdCliente(ClienteDAO clienteDAO) {
        int idCliente;
        int contador = 0;
        while (true) {
            System.out.print("idCliente do Cliente que pegou o Livro emprestado: ");
            idCliente = sc.nextInt();

            if (clienteDAO.selectById(idCliente) != null) {
                break; // CLIENTE ENCONTRADO, SAI DO LOOP
            }

            System.out.println("\nEsse Cliente não existe em nossa biblioteca!");

            contador++;
            if (contador == 1)
                System.out.println("1º TENTANTIVA! RESTÃO 2!");
            else if (contador == 2)
                System.out.println("2º TENTANTIVA! RESTÃO 1!");
            else {
                System.out.println("3º TENTANTIVA! RESTÃO 0!");
                System.out.println("Você digitou o idCliente errado 3 vezes!");
                MenuImplementacao.menuFuncionario();
            }
        }
        return idCliente;
    }

    public String solicitarISBN() {
        String ISBN;
        int contador = 0;
        while (true) {
            System.out.print("\nISBN: ");
            ISBN = sc.nextLine();

            LivroDAO livroDAO = DaoFactory.createLivroDAO();
            if (livroDAO.selectByIsbn(ISBN) != null) {
                break; // ISBN ENCONTRADO, SAI DO LOOP
            }

            System.out.println("\nEsse Livro não existe em nosso catálogo!");
            contador++;
            if (contador == 1)
                System.out.println("1º TENTANTIVA! RESTÃO 2!");
            else if (contador == 2)
                System.out.println("2º TENTANTIVA! RESTÃO 1!");
            else {
                System.out.println("3º TENTANTIVA! RESTÃO 0!");
                System.out.println("Os ISBNs informado não foram encontrados em nosso catálogo de livros!");
                MenuImplementacao.menuFuncionario();
            }
        }
        return ISBN;
    }

    public LocalDate solicitarDataContratacao(String mensagem, Scanner sc) {
        while (true) {
            System.out.print(mensagem);
            String dataStr = sc.nextLine().trim();
            try {
                return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
            }
        }
    }

    public String solicitarData(String mensagem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = solicitarEntrada(mensagem);
        return LocalDate.parse(data, formatter).toString();
    }

    public double solicitarSalario(String mensagem, Scanner sc) {
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextDouble()) {
                Double salario = sc.nextDouble();
                sc.nextLine(); // Consome a nova linha restante
                return salario;
            } else {
                System.out.println("Salário inválido. Digite novamente: ");
                sc.next(); // Consome a entrada inválida
            }
        }
    }
}
