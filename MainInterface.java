import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainInterface {
    private static ArrayList<Item> bancoEmMemoria = new ArrayList<>();

    public static void main(String[] args) {
        // Dados iniciais de exemplo
        bancoEmMemoria.add(new ObjetoComum("Casaco Moletom", "Patio", "08/06/2026", "Azul"));
        bancoEmMemoria.add(new Documento("Carteira de Estudante", "Refeitorio", "07/06/2026", "Pedro Henrique"));

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

            if (escolha == 4 || escolha == -1) { 
                JOptionPane.showMessageDialog(null, "Encerrando o sistema. Até logo!");
                break;
            }

            try {
                executarOpcao(escolha);
            } 
            // Captura erros de validação textual (Cor com números, local inválido, etc)
            catch (SistemaException e) {
                JOptionPane.showMessageDialog(null, "DADO INVÁLIDO: " + e.getMessage(), "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            } 
            // Captura erro se digitarem letras no ID de dar baixa
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO DE FORMATO: Digite apenas números inteiros nesta operação!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } 
            // Captura erro se o ID não existir na lista
            catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Esse código de item não foi encontrado na lista!", "Erro de Índice", JOptionPane.ERROR_MESSAGE);
            } 
            // Qualquer outro erro inesperado
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO CRÍTICO: " + e.getMessage(), "Erro Inesperado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void executarOpcao(int escolha) throws SistemaException {
        if (escolha == 0 || escolha == 1) {
            String nome = JOptionPane.showInputDialog("Nome do item (ex: Chave, Casaco):");
            validarApenasLetras(nome, "Nome do Item");

            String local = JOptionPane.showInputDialog("Local onde foi encontrado (ex: Patio, Sala):");
            validarApenasLetras(local, "Local Encontrado");

            String data = JOptionPane.showInputDialog("Data (Formato: DD/MM/AAAA):");
            validarFormatoData(data);

            if (escolha == 0) {
                String cor = JOptionPane.showInputDialog("Cor do objeto (ex: Azul, Preto):");
                validarApenasLetras(cor, "Cor do Objeto");
                bancoEmMemoria.add(new ObjetoComum(nome, local, data, cor));
            } else {
                String dono = JOptionPane.showInputDialog("Nome do dono no documento (ex: Joao Silva):");
                validarApenasLetras(dono, "Nome do Dono");
                bancoEmMemoria.add(new Documento(nome, local, data, dono));
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
                    lista.append("[").append(i).append("] -> ").append(it.getDetalhes()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, lista.toString());

        } else if (escolha == 3) {
            String idStr = JOptionPane.showInputDialog("Digite o número (ID) do item para dar baixa:");
            if (idStr == null || idStr.isEmpty()) return;

            int id = Integer.parseInt(idStr); 
            Item selecionado = bancoEmMemoria.get(id); 

            if (selecionado.isRetirado()) {
                throw new SistemaException("Este item já foi entregue anteriormente.");
            }

            selecionado.setRetirado(true);
            JOptionPane.showMessageDialog(null, "Baixa efetuada! Item entregue com sucesso.");
        }
    }

    // --- MÉTODOS AUXILIARES DE VALIDAÇÃO RESTRITA ---

    // Garante que o texto tenha letras e espaços, bloqueando números e símbolos vazios
    private static void validarApenasLetras(String texto, String nomeCampo) throws SistemaException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new SistemaException("O campo '" + nomeCampo + "' não pode ficar vazio!");
        }
        // Expressão regular: aceita apenas letras (com acentos) e espaços de A a Z
        if (!texto.matches("^[A-Za-zA-Y-À-ž\\s]+$")) {
            throw new SistemaException("O campo '" + nomeCampo + "' deve conter apenas letras de verdade! Números ou símbolos não são aceitos.");
        }
    }

    // Garante que a data siga rigidamente o padrão DD/MM/AAAA com números legítimos
    private static void validarFormatoData(String data) throws SistemaException {
        if (data == null || data.trim().isEmpty()) {
            throw new SistemaException("O campo 'Data' não pode ficar vazio!");
        }
        // Expressão regular que valida a máscara estrutural DD/MM/AAAA
        if (!data.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            throw new SistemaException("A data deve seguir rigorosamente o padrão de texto DD/MM/AAAA (ex: 08/06/2026).");
        }
    }
}
