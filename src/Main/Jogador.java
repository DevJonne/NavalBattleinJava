package Main;

import java.util.Scanner;
import java.util.Random;

public class Jogador {
    private Tabuleiro tabuleiro;
    private boolean isComputador;
    private Scanner scanner;
    private Random random;

    public Jogador(boolean isComputador) {
        this.isComputador = isComputador;
        tabuleiro = new Tabuleiro();
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void posicionarBarcos(boolean automatico) {
        if (automatico) {
            tabuleiro.posicionarBarcosAutomaticamente();
        } else {
            int[] tamanhos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; //def um array tamanhos com tamanhos dos barcos a serem posicionados no tabuleiro.
            for (int tamanho : tamanhos) { //em cada iteração do loop, o próximo elemento do array tamanhos é atribuído a variável tamanho
                boolean posicionado = false;
                while (!posicionado) { 
                    tabuleiro.exibirMapa(true);
                    System.out.println("Posicione um barco de tamanho " + tamanho);
                    System.out.println("Digite a linha, coluna e orientação (H para horizontal, V para vertical): ");
                    int linha = scanner.nextInt();
                    int coluna = scanner.nextInt();
                    char orientacao = scanner.next().charAt(0);
                    boolean horizontal = orientacao == 'H';
                    posicionado = tabuleiro.posicionarBarco(linha, coluna, tamanho, horizontal);
                    if (!posicionado) {
                        System.out.println("Posição inválida. Tente novamente.");
                    }
                }
            }
        }
    }

    public boolean realizarAtaque(Jogador oponente) {
        int linha, coluna;
        if (isComputador) {
            linha = random.nextInt(10);
            coluna = random.nextInt(10);
        } else {
            System.out.println("Digite a linha e coluna para atacar:");
            linha = scanner.nextInt();
            coluna = scanner.nextInt();
        }
        return oponente.getTabuleiro().receberAtaque(linha, coluna);
    }
}
//aaaaaa
//dsadsad