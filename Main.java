package view;

import controller.KillController;
import controller.RedesController;
import controller.DistroController;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        while (true) {
            String[] options = {"Configuração de IP", "Ping", "Listar Processos", "Matar Processo por PID", "Matar Processo por Nome", "Mostrar Distribuição Linux", "Sair"};
            int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Saindo do programa.");
                System.exit(0);
            }

            switch (choice) {
                case 0: // Configuração de IP
                    new RedesController().ip();
                    break;
                case 1: // Ping
                    new RedesController().ping();
                    break;
                case 2: // Listar Processos
                    new KillController().listaProcessos();
                    break;
                case 3: // Matar Processo por PID
                    String pid = JOptionPane.showInputDialog("Digite o PID do processo:");
                    if (pid != null) {
                        new KillController().mataPid(pid);
                    }
                    break;
                case 4: // Matar Processo por Nome
                    String nome = JOptionPane.showInputDialog("Digite o nome do processo:");
                    if (nome != null) {
                        new KillController().mataNome(nome);
                    }
                    break;
                case 5: // Mostrar Distribuição Linux
                    new DistroController().exibeDistro();
                    break;
                case 6: // Sair
                    JOptionPane.showMessageDialog(null, "Saindo do programa.");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        }
    }
}
