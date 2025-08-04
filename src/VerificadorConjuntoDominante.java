import java.util.List;

public class VerificadorConjuntoDominante {

    public static boolean isConjuntoDominante(List<Integer>[] grafo, List<Integer> conjuntoDominante) {
        int numVertices = grafo.length;
        boolean[] coberto = new boolean[numVertices];

        // Marca todos os vértices do conjunto dominante como cobertos
        for (int vertice : conjuntoDominante) {
            coberto[vertice] = true;
        }

        // Verifica se todos os vértices são cobertos pelo conjunto dominante ou por seus vizinhos
        for (int i = 0; i < numVertices; i++) {
            if (!coberto[i]) {
                boolean cobertoPorVizinho = false;
                for (int vizinho : grafo[i]) {
                    if (coberto[vizinho]) {
                        cobertoPorVizinho = true;
                        break;
                    }
                }
                if (!cobertoPorVizinho) {
                    return false; // Vértice não coberto nem por ele mesmo nem por vizinhos
                }
            }
        }

        return true; // Todos os vértices estão cobertos
    }
}