package Application;

import Services.Funcionarios.AnalistaDeSistemas;

public class Principal {

    public static void main(String[] args) {

//        Connection connection = BancoDeDados.getConnection();
//        BancoDeDados.closeConnection();

        AnalistaDeSistemas analista = new AnalistaDeSistemas();
        analista.suporteTecnico(true);
    }
}
