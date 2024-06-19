package Main;

import java.util.Scanner;

public class BatalhaNaval {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Bem-vindo ao jogo de Batalha Naval!");
        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Jogar contra o computador");
        System.out.println("2 - Jogar contra outro jogador");
        int modoDeJogo = ler.nextInt();

        Jogo jogo;
        if (modoDeJogo == 1) {
            jogo = new Jogo(true);
        } else {
            jogo = new Jogo(false);
        }

        jogo.iniciarJogo();
    }
}

