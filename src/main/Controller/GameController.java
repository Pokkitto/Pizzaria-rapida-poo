import pizzaria.model.Game;
import pizzaria.model.ManualDice;
import pizzaria.view.ConsoleView;

import java.util.List;

public class GameController {
    private final ConsoleView view;

    public GameController(ConsoleView view) {
        this.view = view;
    }

    public void executar() {
        List<String> nomes = view.pedirJogadores();
        if (nomes == null) {
            return;
        }
        ManualDice dado = new ManualDice();
        Game game = new Game(nomes, dado);
        view.mostrarEfeitos(game);

        while (true) {
            view.mostrar(game);
            String texto = view.pedir(game.isTerminado()
                    ? "r = reiniciar, q = sair: "
                    : "Valor do dado (1 a 6), r = reiniciar, q = sair: ");
            if (texto == null || texto.equalsIgnoreCase("q")) {
                break;
            }
            if (texto.equalsIgnoreCase("r")) {
                game.reiniciar();
                view.mostrarErro("Jogo reiniciado.");
            } else if (game.isTerminado()) {
                view.mostrarErro("O jogo terminou. Use r ou q.");
            } else {
                try {
                    dado.definirValor(texto);           // valida 1 a 6
                } catch (IllegalArgumentException e) {
                    view.mostrarErro(e.getMessage());   // o estado do jogo não muda
                    continue;
                }
                view.mostrarMensagens(game.jogarTurno());
            }
        }
        view.mostrarErro("Até à próxima!");