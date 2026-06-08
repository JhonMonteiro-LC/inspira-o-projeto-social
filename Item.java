

// Classe base abstrata (não pode ser instanciada diretamente)
public abstract class Item {
    protected String nome;
    protected String localEncontrado;
    protected  String dataOp; // Data da operação
    protected boolean retirado;

    public Item(String nome, String localEncontrado, String dataOp) {
        this.nome = nome;
        this.localEncontrado = localEncontrado;
        this.dataOp = dataOp;
        this.retirado = false;
    }

    // Polimorfismo: Cada tipo de item vai exibir seus detalhes de forma diferente
    public abstract String getDetalhes();

    // Encapsulamento: Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalEncontrado() { return localEncontrado; }
    public void setLocalEncontrado(String localEncontrado) { this.localEncontrado = localEncontrado; }

    public String getDataOp() { return dataOp; }
    public void setDataOp(String dataOp) { this.dataOp = dataOp; }

    public boolean isRetirado() { return retirado; }
    public void setRetirado(boolean retirado) { this.retirado = retirado; }
}