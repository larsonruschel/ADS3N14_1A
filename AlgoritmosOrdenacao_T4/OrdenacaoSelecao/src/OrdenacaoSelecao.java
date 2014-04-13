
import static java.lang.System.out;

import javax.swing.*;
import java.util.Scanner;

public class OrdenacaoSelecao {
    public static void main(String[] args) {
        // valores a serem ordenados
        int vetor[] = {2,7,3,6,9,4,1,5,8,10};
        // armazenam o menor valor e o índice do menor valor
        int menor, indiceMenor;

        for (int i = 0; i < vetor.length - 1; i++) {
            // antes de comparar, considera-se menor o valor atual do loop
            menor = vetor[i];
            indiceMenor = i;

            // compara com os outros valores do vetor
            for (int j = i + 1; j < vetor.length; j++){
                if (vetor[j] < menor){
                    menor = vetor[j];
                    indiceMenor = j;
                }
            }

            // troca os valores menor e maior
            vetor[indiceMenor] = vetor[i];
            vetor[i] = menor;
        }

        // exibe os números na tela
        String numerosOrdenados = "";
        for (int n : vetor) {
            numerosOrdenados += n+"-";
        }
        
        out.println("Ordenação: "+numerosOrdenados);
       
    }
}