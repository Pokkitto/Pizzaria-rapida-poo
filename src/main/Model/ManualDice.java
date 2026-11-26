public class ManualDice implements Dice {
    private int valor = 0; // 0 = ainda sem valor

    /** Valida e guarda o valor. Se for inválido lança erro e não altera nada. */
    public void definirValor(String texto) {
        String t = texto == null ? "" : texto.trim();
        if (!t.matches("[1-6]")) {
            throw new IllegalArgumentException("Valor inválido: introduza um inteiro de 1 a 6.");
        }
        valor = Integer.parseInt(t);
    }

    @Override
    public int lancar() {
        if (valor == 0) {
            throw new IllegalStateException("Nenhum valor definido.");
        }
        int v = valor;
        valor = 0; // cada valor só é usado uma vez
        return v;
    }
}
