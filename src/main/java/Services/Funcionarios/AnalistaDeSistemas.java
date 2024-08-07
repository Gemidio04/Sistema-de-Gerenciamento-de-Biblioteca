package Services.Funcionarios;

import Services.Exception.ValidacaoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.io.IOException;

public class AnalistaDeSistemas extends Funcionario {

    private boolean precisaDeSuporte;

    public AnalistaDeSistemas() {
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
        atualizarSoftware();

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
            System.out.println("Integridade do sistema verificada!");
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
                System.out.println("Serviços essenciais reiniciados!");
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
            System.out.println("Backup dos dados realizado!");
        } catch (IOException e) {
            System.out.println("Erro ao realizar backup dos dados: " + e.getMessage());
        }
    }

    public void atualizarSoftware() {
        System.out.println("Realizando a Atualização do Software...");
        try {
            File pastaProjeto = new File("D:\\JAVA\\PROJETOS PESSOAIS\\2º PROJETO\\Sistema-De-Gerenciamento-De-Biblioteca");
            File pastaAtualizacao = new File("D:\\JAVA\\PROJETOS PESSOAIS\\2º PROJETO\\Projeto Atualizado");

            if (pastaProjeto.exists() && pastaAtualizacao.exists()) {
                Files.walk(pastaAtualizacao.toPath())
                        .forEach(source -> {
                            try {
                                File destination = new File(pastaProjeto.toPath().resolve(pastaAtualizacao.toPath().relativize(source)).toString());
                                if (source.toFile().isDirectory()) {
                                    destination.mkdirs();
                                } else {
                                    Files.copy(source, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });

                System.out.println("Software atualizado com sucesso!");
            } else {
                System.out.println("Pasta do projeto ou pasta de atualização não encontrada.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }
}

