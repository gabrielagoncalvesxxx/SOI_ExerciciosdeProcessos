package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class RedesController {

    // Método privado para identificar o Sistema Operacional
    private String os() {
        return System.getProperty("os.name").toLowerCase();
    }

    // Método para obter e exibir o IP
    public void ip() {
        String os = os();
        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "ipconfig");
        } else {
            processBuilder = new ProcessBuilder("sh", "-c", "ip addr show");
        }

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (os.contains("win")) {
                    if (line.contains("Adaptador") || line.contains("IPv4")) {
                        System.out.println(line.trim());
                    }
                } else {
                    if (line.contains("inet ") && !line.contains("127.0.0.1")) {
                        System.out.println(line.trim());
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para realizar o ping e exibir o tempo médio
    public void ping() {
        String os = os();
        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "ping -4 -n 10 www.google.com.br");
        } else {
            processBuilder = new ProcessBuilder("sh", "-c", "ping -4 -c 10 www.google.com.br");
        }

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (os.contains("win")) {
                    if (line.contains("Tempo médio")) {
                        String[] parts = line.split(" ");
                        for (String part : parts) {
                            if (part.endsWith("ms")) {
                                System.out.println("Tempo médio do ping: " + part);
                                break;
                            }
                        }
                    }
                } else {
                    if (line.contains("avg")) {
                        String[] parts = line.split(" ");
                        for (String part : parts) {
                            if (part.endsWith("ms")) {
                                System.out.println("Tempo médio do ping: " + part);
                                break;
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
