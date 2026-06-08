
    

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainInterface {
    // Armazenamento em memória (Sem banco de dados, como pedido)
    private static ArrayList<Item> bancoEmMemoria = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializando com alguns dados de exemplo para teste
        bancoEmMemoria.add(new ObjetoComum("Casaco Moletom", "Pátio", "08/06/2026", "Azul"));
        bancoEmMemoria.add(new Documento("Carteira de Estudante", "Refeitório", "07/06/2026", "Pedro Henrique"));

        while (true) {
            String[] opcoes = {"Cadastrar Objeto", "Cadastrar Documento", "Listar Itens", "Dar Baixa (Retirado)", "Sair"};
            
            int escolha = JOptionPane.showOptionDialog(
                    null, 
                    "--- ACHADOS E PERDIDOS ESCOLAR ---\nSelecione uma opção:", 
                    "Sistema de Gestão", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    opcoes, 
                    opcoes[0]
            );

            if (escolha == 4 || escolha == -1) { // Sair ou fechar janela
                JOptionPane.showMessageDialog(null, "Encerrando o sistema. Até logo!");
                break;
            }

            try {
                ejecutarOpcao(escolha);
            } catch (final SistemaException e) {
                // Tratamento de erro robusto exibindo um alerta gráfico
                JOptionPane.showMessageDialog(null, "ERRO NO SISTEMA: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void ejecutarOpcao(int escolha) throws SistemaException {
        if (escolha == 0 || escolha == 1) {
            String nome = JOptionPane.showInputDialog("Nome do item (ex: Chave, RG):");
            String local = JOptionPane.showInputDialog("Local onde foi encontrado:");
            String data = JOptionPane.showInputDialog("Data (DD/MM/AAAA):");

            // Validação simples gerando erro
            if (nome == null || nome.trim().isEmpty() || local == null || local.trim().isEmpty() || data == null || data.trim().isEmpty()) {
                throw new SistemaException("Todos os campos básicos devem ser preenchidos!");
            }

            if (escolha == 0) {
                String cor = JOptionPane.showInputDialog("Cor do objeto:");
                bancoEmMemoria.add(new ObjetoComum(nome, local, data, cor));
            } else {
                String dono = JOptionPane.showInputDialog("Nome do dono no documento (se houver):");
                bancoEmMemoria.add(new Documento(nome, local, data, dono.isEmpty() ? "Desconhecido" : dono));
            }
            JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");

        } else if (escolha == 2) {
            if (bancoEmMemoria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum item registrado no momento.");
                return;
            }

            StringBuilder lista = new StringBuilder("--- ITENS DISPONÍVEIS ---\n");
            for (int i = 0; i < bancoEmMemoria.size(); i++) {
                Item it = bancoEmMemoria.get(i);
                if (!it.isRetirado()) {
                    // Aqui acontece o Polimorfismo: chama o getDetalhes() específico de cada classe
                    lista.append(i).append(" - ").append(it.getDetalhes()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, lista.toString());

        } else if (escolha == 3) {
            String idStr = JOptionPane.showInputDialog("Digite o número do item que foi devolvido ao dono:");
            if (idStr == null || idStr.isEmpty()) return;

            try {
                int id = Integer.parseInt(idStr);
                if (id < 0 || id >= bancoEmMemoria.size() || bancoEmMemoria.get(id).isRetirado()) {
                    throw new SistemaException("Código de item inválido ou item já retirado.");
                }
                bancoEmMemoria.get(id).setRetirado(true);
                JOptionPane.showMessageDialog(null, "Baixa efetuada! Item entregue.");
            } catch (NumberFormatException e) {
                throw new SistemaException("Você deve digitar um número válido.");
            }
        }
    }
}

