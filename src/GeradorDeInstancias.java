import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GeradorDeInstancias {

    // Função para gerar um grafo aleatório usando arrays e listas
    public static List<Integer>[] gerarGrafoAleatorio(int numVertices, double probAresta, String nomeArquivo) {
        // Cria um array de listas, onde cada índice representa um vértice
        List<Integer>[] grafo = new ArrayList[numVertices];

        // Inicializa as listas de adjacência
        for (int i = 0; i < numVertices; i++) {
            grafo[i] = new ArrayList<>();
        }

        Random rand = new Random();

        // Adiciona arestas aleatoriamente com base na probabilidade fornecida
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (rand.nextDouble() < probAresta) {
                    // Adiciona uma aresta entre i e j
                    grafo[i].add(j);
                    grafo[j].add(i);
                }
            }
        }

        // Escreve o grafo no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < grafo.length; i++) {
                writer.write(i + " -> " + grafo[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        return grafo;
    }

    // Função auxiliar para imprimir o grafo
    public static void imprimirGrafo(List<Integer>[] grafo) {
        for (int i = 0; i < grafo.length; i++) {
            System.out.println(i + " -> " + grafo[i]);
        }
    }

}