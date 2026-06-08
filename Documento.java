
// Subclasse Documento herdando de Item
public class Documento extends Item {
    private String nomeDono;

    public Documento(String nome, String localEncontrado, String dataOp, String nomeDono) {
        super(nome, localEncontrado, dataOp);
        this.nomeDono = nomeDono;
    }

    // Implementação do Polimorfismo
    @Override
    public String getDetalhes() {
        return "Tipo: Documento [" + getNome() + "] | Dono: " + nomeDono + " | Local: " + getLocalEncontrado() + " | Data: " + getDataOp();
    }
}