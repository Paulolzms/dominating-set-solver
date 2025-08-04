import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nomeArquivoGrafo = "grafo.txt";
        String nomeArquivoLog = "log.txt";

        int numVertices = 1500; // Defina o tamanho da instância aqui
        double probAresta = 0.5;

        // Gera o grafo e salva no arquivo
        List<Integer>[] grafoAleatorio = GeradorDeInstancias.gerarGrafoAleatorio(numVertices, probAresta, nomeArquivoGrafo);

        // Imprime grafo
//        GeradorDeInstancias.imprimirGrafo(grafoAleatorio);

        long tempoInicio = System.currentTimeMillis();
        List<Integer> solucaoGuloso = ConjuntoDominanteGuloso.encontrarConjuntoDominante(nomeArquivoGrafo);
        long tempoFim = System.currentTimeMillis();
        long tempoGuloso = tempoFim - tempoInicio;

        System.out.println("======================Algoritmo Guloso====================");
        System.out.println("Conjunto Dominante: " + solucaoGuloso);
        System.out.println("Tamanho do conjunto: " + solucaoGuloso.size());
        System.out.println("Tempo de execução: " + tempoGuloso + " milissegundos");

        // Verifica se a solução é um conjunto dominante
        boolean ehDominante = VerificadorConjuntoDominante.isConjuntoDominante(grafoAleatorio, solucaoGuloso);
        if (ehDominante) {
            System.out.println("A solução é um conjunto dominante.");
        } else {
            System.out.println("A solução NÃO é um conjunto dominante.");
        }


        // Simulated Annealing
        SimulatedAnnealing sa = new SimulatedAnnealing(nomeArquivoGrafo);

        double temperaturaInicial = 1000;
        double taxaResfriamento = 0.95;
        int numIteracoes = 10000;

        tempoInicio = System.currentTimeMillis();
        List<Integer> solucaoSA = sa.encontrarConjuntoDominante(temperaturaInicial, taxaResfriamento, numIteracoes);
        tempoFim = System.currentTimeMillis();
        long tempoSA = tempoFim - tempoInicio;

        System.out.println("======================Simulated Annealing====================");
        System.out.println("Conjunto Dominante: " + solucaoSA);
        System.out.println("Tamanho do conjunto: " + solucaoSA.size());
        System.out.println("Tempo de execução: " + tempoSA + " milissegundos");

        // Verifica se a solução é um conjunto dominante
        ehDominante = VerificadorConjuntoDominante.isConjuntoDominante(grafoAleatorio, solucaoSA);
        if (ehDominante) {
            System.out.println("A solução é um conjunto dominante.");
        } else {
            System.out.println("A solução NÃO é um conjunto dominante.");
        }

        // Salva os resultados no arquivo de log
        salvarLog(nomeArquivoLog, solucaoGuloso, tempoGuloso, solucaoSA, tempoSA, numVertices);

    }

    public static void salvarLog(String nomeArquivo, List<Integer> solucaoGuloso, long tempoGuloso,
                                 List<Integer> solucaoSA, long tempoSA, int tamanhoInstancia) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write("Tamanho da Instância: " + tamanhoInstancia);
            writer.newLine();

            writer.write("----- Algoritmo Guloso -----");
            writer.newLine();
            writer.write("Tempo de Execução (ms): " + tempoGuloso);
            writer.newLine();
            writer.write("Solução Encontrada: " + solucaoGuloso);
            writer.newLine();
            writer.write("Tamanho da Solução: " + solucaoGuloso.size());
            writer.newLine();

            writer.write("----- Simulated Annealing -----");
            writer.newLine();
            writer.write("Tempo de Execução (ms): " + tempoSA);
            writer.newLine();
            writer.write("Solução Encontrada: " + solucaoSA);
            writer.newLine();
            writer.write("Tamanho da Solução: " + solucaoSA.size());
            writer.newLine();

            writer.newLine(); // Adiciona uma linha em branco para separar as execuções
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}