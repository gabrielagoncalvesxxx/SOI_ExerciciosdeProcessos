package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DistroController {

    // Método privado para identificar o Sistema Operacional
    private String os() {
        return System.getProperty("os.name").toLowerCase();
    }

    // Método para exibir a distribuição Linux
    public void exibeDistro() {
        String os = os();
        if (os.contains("nix") || os.contains("nux")) {
            ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "cat /etc/os-release");

            try {
                Process process = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("NAME=") || line.startsWith("VERSION=")) {
                        System.out.println(line);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Este sistema não é Linux.");
        }
    }
}
