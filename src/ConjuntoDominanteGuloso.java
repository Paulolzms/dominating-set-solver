import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ConjuntoDominanteGuloso {

    // Função gulosa para encontrar o conjunto dominante usando arrays
    public static List<Integer> encontrarConjuntoDominante(String nomeArquivo) {
        List<Integer>[] grafo = lerGrafoDeArquivo(nomeArquivo);
        int numVertices = grafo.length;
        boolean[] visitado = new boolean[numVertices];  // Marca os vértices visitados
        List<Integer> conjuntoDominante = new ArrayList<>();  // Armazena o conjunto dominante

        // Enquanto todos os vértices não forem cobertos
        while (!todosVisitados(visitado)) {
            // Encontra o vértice com o maior número de vizinhos ainda não cobertos
            int melhorVertice = -1;
            int maxVizinhosNaoCobertos = -1;

            for (int i = 0; i < numVertices; i++) {
                if (!visitado[i]) {
                    int vizinhosNaoCobertos = 0;
                    for (int vizinho : grafo[i]) {
                        if (!visitado[vizinho]) {
                            vizinhosNaoCobertos++;
                        }
                    }

                    if (vizinhosNaoCobertos > maxVizinhosNaoCobertos) {
                        maxVizinhosNaoCobertos = vizinhosNaoCobertos;
                        melhorVertice = i;
                    }
                }
            }

            // Adiciona o melhor vértice ao conjunto dominante
            conjuntoDominante.add(melhorVertice);
            visitado[melhorVertice] = true;

            // Marca todos os vizinhos do melhor vértice como visitados
            for (int vizinho : grafo[melhorVertice]) {
                visitado[vizinho] = true;
            }
        }

        return conjuntoDominante;
    }

    // Função auxiliar para verificar se todos os vértices foram visitados
    private static boolean todosVisitados(boolean[] visitado) {
        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    // Função para ler o grafo de um arquivo
    public static List<Integer>[] lerGrafoDeArquivo(String nomeArquivo) {
        List<Integer>[] grafo = null;
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            int numVertices = 0;

            // Primeira passada: descobre o número de vértices
            while ((linha = br.readLine()) != null) {
                if (!linha.isEmpty()) {
                    numVertices++;
                }
            }

            grafo = new ArrayList[numVertices];
            for (int i = 0; i < numVertices; i++) {
                grafo[i] = new ArrayList<>();
            }

            // Segunda passada: preenche a lista de adjacências
            br.close(); // Reinicia o leitor
            BufferedReader br2 = new BufferedReader(new FileReader(nomeArquivo));
            int verticeAtual = 0;
            while ((linha = br2.readLine()) != null) {
                if (!linha.isEmpty()) {
                    String[] partes = linha.split(" -> ");
                    String[] vizinhosStr = partes[1].substring(1, partes[1].length() - 1).split(", ");
                    for (String vizinhoStr : vizinhosStr) {
                        if (!vizinhoStr.isEmpty()) {
                            int vizinho = Integer.parseInt(vizinhoStr);
                            grafo[verticeAtual].add(vizinho);
                        }
                    }
                    verticeAtual++;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return grafo;
    }

}
