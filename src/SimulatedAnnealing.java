import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimulatedAnnealing {

    private List<Integer>[] grafo;
    private int numVertices;
    private Random random;

    public SimulatedAnnealing(String nomeArquivo) {
        this.grafo = lerGrafoDeArquivo(nomeArquivo);
        this.numVertices = grafo.length;
        this.random = new Random();
    }

    public List<Integer> encontrarConjuntoDominante(double temperaturaInicial, double taxaResfriamento, int numIteracoes) {
        List<Integer> solucaoAtual = gerarSolucaoInicial();
        List<Integer> melhorSolucao = new ArrayList<>(solucaoAtual);
        double temperatura = temperaturaInicial;

        for (int i = 0; i < numIteracoes; i++) {
            List<Integer> novaSolucao = gerarVizinho(solucaoAtual);
            int delta = calcularCusto(novaSolucao) - calcularCusto(solucaoAtual);

            if (delta < 0 || Math.exp(-delta / temperatura) > random.nextDouble()) {
                solucaoAtual = novaSolucao;
            }

            if (calcularCusto(solucaoAtual) < calcularCusto(melhorSolucao)) {
                melhorSolucao = new ArrayList<>(solucaoAtual);
            }

            temperatura *= taxaResfriamento;
        }

        return melhorSolucao;
    }

    private List<Integer> gerarSolucaoInicial() {
        List<Integer> solucao = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (random.nextDouble() < 0.01) {
                solucao.add(i);
            }
        }
        return solucao;
    }

    private List<Integer> gerarVizinho(List<Integer> solucao) {
        List<Integer> vizinho = new ArrayList<>(solucao);
        int indice;

        // Tenta remover um vértice com maior probabilidade
        if (random.nextDouble() < 0.9 && !solucao.isEmpty()) {
            // 90% de chance de tentar remover um vértice
            indice = solucao.get(random.nextInt(solucao.size()));
            vizinho.remove((Integer) indice);
        } else {
            // 10% de chance de adicionar um vértice
            do {
                indice = random.nextInt(numVertices);
            } while (vizinho.contains(indice)); // Garante que o vértice não está na solução
            vizinho.add(indice);
        }

        return vizinho;
    }

    private int calcularCusto(List<Integer> solucao) {
        boolean[] coberto = new boolean[numVertices];
        for (int vertice : solucao) {
            coberto[vertice] = true;
            for (int vizinho : grafo[vertice]) {
                coberto[vizinho] = true;
            }
        }

        int custo = solucao.size();
        for (boolean b : coberto) {
            if (!b) {
                custo++; // Penaliza vértices não cobertos
            }
        }
        return custo;
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