

// Subclasse ObjetoComum herdando de Item
public class ObjetoComum extends Item {
    private String cor;

    public ObjetoComum(String nome, String localEncontrado, String dataOp, String cor) {
        super(nome, localEncontrado, dataOp);
        this.cor = cor;
    }

    // Implementação do Polimorfismo
    @Override
    public String getDetalhes() {
        return "Tipo: Objeto [" + getNome() + "] | Cor: " + cor + " | Local: " + getLocalEncontrado() + " | Data: " + getDataOp();
    }
}