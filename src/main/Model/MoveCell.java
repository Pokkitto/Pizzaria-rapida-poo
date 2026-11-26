public class MoveCell extends Cell {
    private final String nome;
    private final Cell destino;

    public MoveCell(int numero, String nome, Cell destino) {
        super(numero);
        this.nome = nome;
        this.destino = destino;
    }

    public String getNome() {
        return nome;
    }

    public Cell getDestino() {
        return destino;
    }

    /** Avanço se o destino está à frente, recuo caso contrário. */
    public boolean isAvanco() {
        return destino.getNumero() > getNumero();
    }

    @Override
    public String applyEffect(Player p) {
        p.move(destino);
        return nome + ": " + (isAvanco() ? "avança" : "recua") + " para a casa " + destino.getNumero() + ".";
    }
}