package Services.Funcionarios;

import java.nio.file.*;
import java.io.IOException;

public class AnalistaDeSistemas extends Funcionario {

    private boolean precisaDeSuporte;

    public AnalistaDeSistemas(){
        precisaDeSuporte = false;
    }

    public void suporteTecnico(boolean precisaDeSuporte) {
        this.precisaDeSuporte = precisaDeSuporte;
        if (this.precisaDeSuporte) {
            fazerManutencao();
        }
    }

    private void fazerManutencao() {
        System.out.println("Iniciando manutenção do sistema...");

        verificarIntegridadeDoSistema();
        reiniciarServicosEssenciais();
        realizarBackup();
//        atualizarSoftware();

        System.out.println("Manutenção concluída com sucesso!");
    }

    private void verificarIntegridadeDoSistema() {
        System.out.println("Verificando a integridade do sistema...");
        boolean sistemaIntegro = true;

        try {
            if (!Files.exists(Paths.get("pom.xml"))) {
                sistemaIntegro = false;
                System.out.println("Arquivo crítico ausente.");
            }
        } catch (Exception e) {
            sistemaIntegro = false;
            System.out.println("Erro ao verificar a integridade do sistema: " + e.getMessage());
        }

        if (sistemaIntegro) {
            System.out.println("Integridade do sistema verificada.");
        } else {
            System.out.println("Problemas encontrados na integridade do sistema.");
            verificarIntegridadeDoSistema();
        }
    }

    private void reiniciarServicosEssenciais() {
        System.out.println("Reiniciando serviços essenciais...");
        try {
            Process process = Runtime.getRuntime().exec("wuauclt.exe /detectnow");
            process.waitFor();
            if (process.exitValue() == 0) {
                System.out.println("Serviços essenciais reiniciados.");
            } else {
                System.out.println("Falha ao reiniciar serviços essenciais.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao reiniciar serviços essenciais: " + e.getMessage());
        }
    }

    private void realizarBackup() {
        System.out.println("Realizando backup dos dados...");
        try {
            Path source = Paths.get("D:\\JAVA\\PROJETOS GITHUB");
            Path backup = Paths.get("D:\\JAVA\\PROJETOS PESSOAIS\\2º PROJETO\\Sistema-De-Gerenciamento-De-Biblioteca\\Backup");
            Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup dos dados realizado.");
        } catch (IOException e) {
            System.out.println("Erro ao realizar backup dos dados: " + e.getMessage());
        }
    }

//    private void atualizarSoftware() {
//        System.out.println("Atualizando software...");
//        try {
//            String projectPath = "D:\\JAVA\\PROJETOS PESSOAIS\\2º PROJETO\\Sistema-De-Gerenciamento-De-Biblioteca"; // Caminho do seu projeto
//            String mavenPath = "mvn"; // Assume que o Maven está no PATH
//
//            // Monta o comando para mudar para o diretório do projeto e executar o Maven
//            String[] command = {"cmd.exe", "/c", "cd " + projectPath + " && " + mavenPath + " clean install"};
//
//            Process process = Runtime.getRuntime().exec(command);
//
//            // Captura a saída padrão do processo
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            // Captura a saída de erro do processo
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//            String s;
//            System.out.println("Saída do processo:\n");
//            while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
//
//            System.out.println("Erros do processo (se houver):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                System.out.println("Software atualizado com sucesso.");
//            } else {
//                System.out.println("Falha ao atualizar software. Código de saída: " + exitCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            System.out.println("Erro ao atualizar software: " + e.getMessage());
//        }
//    }

//        private void atualizarSoftware() {
//            System.out.println("Atualizando software...");
//            try {
//                String mavenPath = "D:\\JAVA\\Maven\\apache-maven-4.0.0-beta-3\\bin\\mvn.cmd"; // Caminho completo para o executável do Maven
//                Process process = Runtime.getRuntime().exec("cmd /c " + mavenPath + " clean install");
//                process.waitFor();
//                if (process.exitValue() == 0) {
//                    System.out.println("Software atualizado.");
//              } else {
//                System.out.println("Falha ao atualizar software.");
//              }
//            } catch (IOException | InterruptedException e) {
//                System.out.println("Erro ao atualizar software: " + e.getMessage());
//            }
//        }

}
