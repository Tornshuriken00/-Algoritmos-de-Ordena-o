import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static int[] lerCSV(String caminho) throws IOException {
        int quantidade = 0;
        BufferedReader leitor = new BufferedReader(new FileReader(caminho));
        String linha = leitor.readLine(); 
        while ((linha = leitor.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                quantidade++;
            }
        }
        leitor.close();

        int[] dados = new int[quantidade];
        leitor = new BufferedReader(new FileReader(caminho));
        leitor.readLine(); 
        int indice = 0;
        while ((linha = leitor.readLine()) != null) {
            linha = linha.trim();
            if (!linha.isEmpty()) {
                dados[indice] = Integer.parseInt(linha);
                indice++;
            }
        }
        leitor.close();

        return dados;
    }

    public static int[] copiarVetor(int[] original) {
        int[] copia = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }

    public static boolean estaOrdenado(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            if (vetor[i] > vetor[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        String[] arquivos = {
            "datasets/aleatorio_100.csv",
            "datasets/aleatorio_1000.csv",
            "datasets/aleatorio_10000.csv",
            "datasets/crescente_100.csv",
            "datasets/crescente_1000.csv",
            "datasets/crescente_10000.csv",
            "datasets/decrescente_100.csv",
            "datasets/decrescente_1000.csv",
            "datasets/decrescente_10000.csv"
        };

        String[] nomesAlgoritmos = {"Bubble Sort", "Insertion Sort", "Quick Sort"};

        System.out.println("=================================================================================");
        System.out.printf("%-22s %-10s %-15s %-15s %-15s%n",
                "Conjunto de Dados", "Tamanho", "Bubble (ms)", "Insertion (ms)", "Quick (ms)");
        System.out.println("=================================================================================");

        final int REPETICOES = 5;

        for (String arquivo : arquivos) {
            int[] dadosOriginais = lerCSV(arquivo);
            int tamanho = dadosOriginais.length;

            double[] tempos = new double[3];

            for (int algoritmo = 0; algoritmo < 3; algoritmo++) {
                double somaTempos = 0;

                for (int rep = 0; rep < REPETICOES; rep++) {
                    int[] vetor = copiarVetor(dadosOriginais);

                    long inicio = System.nanoTime();

                    if (algoritmo == 0) {
                        AlgoritmosOrdenacao.bubbleSort(vetor);
                    } else if (algoritmo == 1) {
                        AlgoritmosOrdenacao.insertionSort(vetor);
                    } else {
                        AlgoritmosOrdenacao.quickSort(vetor, 0, vetor.length - 1);
                    }

                    long fim = System.nanoTime();

                    if (!estaOrdenado(vetor)) {
                        System.out.println("ERRO: " + nomesAlgoritmos[algoritmo] + " falhou em " + arquivo);
                    }

                    somaTempos += (fim - inicio) / 1_000_000.0; 
                }

                tempos[algoritmo] = somaTempos / REPETICOES;
            }

            String nomeConjunto = arquivo.substring(arquivo.lastIndexOf('/') + 1);
            System.out.printf("%-22s %-10d %-15.4f %-15.4f %-15.4f%n",
                    nomeConjunto, tamanho, tempos[0], tempos[1], tempos[2]);
        }

        System.out.println("=================================================================================");
    }
}
