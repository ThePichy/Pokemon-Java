package com.pichy.pokemon;

public class GeneratorTerrain {
    public static void main(String[] args) {

        int largura = 32 * 137;
        int altura = 32 * 136;

        int y = 0;
        int x = 0;
        while (true) {
            System.out.print(x + "/" + y + ";GRASS,");
            if (x > largura) {
                x = 0;
                y = y + 32;
            } else
                x = x + 32;
            if (y >= altura)
                break;

        }
    }
}
