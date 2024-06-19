package Main;

import java.util.Random;

public class Tabuleiro {
    private static final int TAMANHO = 10; //eu falto mas olho as aulas :>
    private char[][] mapa;
    private char[][] ataques;

    public Tabuleiro() {
        mapa = new char[TAMANHO][TAMANHO]; //localização verdadeira dos barcos 
        ataques = new char[TAMANHO][TAMANHO]; //vai registrar onde os ataques são feitos
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                mapa[i][j] = '~'; // Água
                ataques[i][j] = '~';
            }
        }
    }

    public void exibirMapa(boolean mostrarBarcos) { //se mostrar barco for true, mostra os barcos.
        System.out.print("  ");                     //se for false, exibe só o mapa de atk.
        for (int i = 0; i < TAMANHO; i++) {         //resumindo exibirMapa(false)=ataque.
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < TAMANHO; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < TAMANHO; j++) {
                if (mostrarBarcos) {
                    System.out.print(mapa[i][j] + " ");
                } else {
                    System.out.print(ataques[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean posicionarBarco(int linha, int coluna, int tamanho, boolean horizontal) {
        if (horizontal) {
            if (coluna + tamanho > TAMANHO) return false; //L horizontal
            for (int i = 0; i < tamanho; i++) { //livre
                if (mapa[linha][coluna + i] != '~') return false; 
            }
            for (int i = 0; i < tamanho; i++) { //p
                mapa[linha][coluna + i] = 'B';
            }
        } else {
            if (linha + tamanho > TAMANHO) return false; //L vertical
            for (int i = 0; i < tamanho; i++) { //livre
                if (mapa[linha + i][coluna] != '~') return false;
            }
            for (int i = 0; i < tamanho; i++) { //p
                mapa[linha + i][coluna] = 'B';
            }
        }
        return true;
    }

    public void posicionarBarcosAutomaticamente() {
        Random random = new Random();
        int[] tamanhos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; 
        for (int tamanho : tamanhos) { 
            boolean posicionado = false; //var usada pra saber se foi p corretamente o barco
            while (!posicionado) { //para cada valor (tamanho do barco), tenta posicionar o barco no tabuleiro.
                int linha = random.nextInt(TAMANHO);
                int coluna = random.nextInt(TAMANHO);
                boolean horizontal = random.nextBoolean(); //boolenao aleatorio pra o p. h. ou v. 
                posicionado = posicionarBarco(linha, coluna, tamanho, horizontal); 
            }//se der certo, retonar true e encerra o loop, se der errado repete até retornar true.
        }
    }

    public boolean receberAtaque(int linha, int coluna) {
        if (ataques[linha][coluna] != '~') {
            System.out.println("Você já atirou aqui. Tente novamente.");
            return false;
        }
        if (mapa[linha][coluna] == 'B') {
            ataques[linha][coluna] = 'X'; //ac barco
            mapa[linha][coluna] = 'X';
            System.out.println("Acertou um barco!");
            return true;
        } else {
            ataques[linha][coluna] = 'O'; //ac água
            System.out.println("Acertou na água.");
            return false;
        }
    }

    public boolean todosBarcosAfundados() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (mapa[i][j] == 'B') {
                    return false;
                }
            }
        }
        return true;
    }
}
