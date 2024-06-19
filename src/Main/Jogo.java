package Main;
import java.util.Scanner;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private boolean jogoContraComputador;

    public Jogo(boolean jogoContraComputador) {
        this.jogoContraComputador = jogoContraComputador;
        jogador1 = new Jogador(false);
        jogador2 = new Jogador(jogoContraComputador);
    }

    public void iniciarJogo() {
        System.out.println("Jogador 1, escolha como deseja posicionar seus barcos:");
        System.out.println("1 - Automaticamente");
        System.out.println("2 - Manualmente");
        int escolha = new Scanner(System.in).nextInt();
        jogador1.posicionarBarcos(escolha == 1);

        if (!jogoContraComputador) {
            System.out.println("Jogador 2, escolha como deseja posicionar seus barcos:");
            System.out.println("1 - Automaticamente");
            System.out.println("2 - Manualmente");
            escolha = new Scanner(System.in).nextInt();
            jogador2.posicionarBarcos(escolha == 1);
        } else {
            jogador2.posicionarBarcos(true);
        }

        boolean turnoDoJogador1 = true;
        while (true) {
            Jogador atacante;
            Jogador defensor;

            if (turnoDoJogador1) {
                atacante = jogador1;
                defensor = jogador2;
            } else {
                atacante = jogador2;
                defensor = jogador1;
            }

            if (turnoDoJogador1) {
                System.out.println("Turno do Jogador 1");
            } else {
                System.out.println("Turno do Jogador 2");
            }

            defensor.getTabuleiro().exibirMapa(false); //mostra o mapa do defensor pro atacande (mascarado)

            boolean acerto = atacante.realizarAtaque(defensor); //true se acertar um barco, false se acertar a agua

            if (defensor.getTabuleiro().todosBarcosAfundados()) { //se retornar true o atacante venceu
                if (turnoDoJogador1) {
                    System.out.println("O Jogador 1 venceu!");
                } else {
                    System.out.println("O Jogador 2 venceu!");
                }
                break; 
            }

            if (!acerto) { //se acertor for true, o atacante continua jogando, se for false o turno altera pro outro jogador.
                turnoDoJogador1 = !turnoDoJogador1;//obs, turnoDoJogador1 = !turnoDoJogador1; inverte o valor de turnoDoJogador1.
            }
        }
    }
}

