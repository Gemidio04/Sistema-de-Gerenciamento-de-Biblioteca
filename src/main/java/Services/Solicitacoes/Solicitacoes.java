package Services.Solicitacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solicitacoes {
    public String solicitarEntrada(String mensagem, Scanner sc) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
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
