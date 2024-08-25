package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class KillController {

    // Método privado para identificar o Sistema Operacional
    private String os() {
        return System.getProperty("os.name").toLowerCase();
    }

    // Método para listar processos ativos
    public void listaProcessos() {
        String os = os();
        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "tasklist /FO TABLE");
        } else {
            processBuilder = new ProcessBuilder("sh", "-c", "ps -ef");
        }

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para matar processo por PID
    public void mataPid(String pid) {
        String os = os();
        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "taskkill /PID " + pid);
        } else {
            processBuilder = new ProcessBuilder("sh", "-c", "kill -9 " + pid);
        }

        try {
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Processo com PID " + pid + " terminado.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para matar processo por nome
    public void mataNome(String nome) {
        String os = os();
        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "taskkill /IM " + nome);
        } else {
            processBuilder = new ProcessBuilder("sh", "-c", "pkill -f " + nome);
        }

        try {
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Processo com nome " + nome + " terminado.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
