Comparação de Algoritmos de Ordenação

Trabalho Prático (TDE 04) — Resolução de Problemas Estruturados em Computação
Bacharelado em Sistemas de Informação — PUCPR

Descrição

Implementação em Java dos algoritmos Bubble Sort, Insertion Sort e Quick Sort, aplicados a nove conjuntos de dados (aleatório, ordenado crescente e ordenado decrescente, nos tamanhos 100, 1.000 e 10.000 elementos), com medição do tempo de execução de cada algoritmo via System.nanoTime().

Os algoritmos foram implementados utilizando apenas vetores (int[]) e laços nativos da linguagem, sem o uso de estruturas prontas do Java (ArrayList, Collections, Arrays.sort, etc.).

Detalhes da implementação


Bubble Sort: com otimização de parada antecipada caso uma passagem não realize trocas.
Insertion Sort: implementação clássica com deslocamento de elementos.
Quick Sort: recursivo, com partição de Lomuto, utilizando o último elemento de cada partição como pivô.
Leitura dos CSVs: feita com BufferedReader em duas passadas (conta linhas, depois preenche o vetor), sem Scanner/regex.
Medição de tempo: cada algoritmo é executado 5 vezes sobre uma cópia independente dos dados originais; o tempo reportado é a média (em milissegundos).
Validação: após cada execução, verifica-se se o vetor resultante está corretamente ordenado.


Estrutura do repositório

.
├── ComparacaoOrdenacao.java       # Código-fonte principal
├── datasets/                      # Conjuntos de dados (CSV)
│   ├── aleatorio_100.csv
│   ├── aleatorio_1000.csv
│   ├── aleatorio_10000.csv
│   ├── crescente_100.csv
│   ├── crescente_1000.csv
│   ├── crescente_10000.csv
│   ├── decrescente_100.csv
│   ├── decrescente_1000.csv
│   └── decrescente_10000.csv
├── relatorio_comparacao_ordenacao.pdf   # Relatório com tabelas, gráfico e análise
└── README.md

Como executar

Pré-requisitos: JDK 11 ou superior.

bash# Compilar
javac ComparacaoOrdenacao.java

# Executar (a pasta datasets/ deve estar no diretório atual)
java ComparacaoOrdenacao

A saída exibe uma tabela com o tempo médio de execução (em ms) de cada algoritmo para cada conjunto de dados.

Resultados (resumo)

Conjunto de DadosTamanhoBubble Sort (ms)Insertion Sort (ms)Quick Sort (ms)Aleatório1000,16340,05750,0248Aleatório1.0003,27102,82000,3376Aleatório10.000127,16659,14022,2552Crescente1000,00030,00030,0160Crescente1.0000,00620,00112,1734Crescente10.0000,00410,009377,5059Decrescente1000,11740,00240,0172Decrescente1.0001,37980,86280,3928Decrescente10.00064,171916,959237,8167

Principais conclusões


O Quick Sort é o mais eficiente para dados em ordem aleatória, aproximando-se de O(n log n).
Por usar o último elemento como pivô, o Quick Sort sofre o pior caso O(n²) quando os dados já estão ordenados (crescente ou decrescente), pois o pivô sempre fica numa extremidade da partição, gerando partições desbalanceadas.
Insertion Sort e Bubble Sort (com parada antecipada) são extremamente rápidos em dados já ordenados em ordem crescente.
Não existe um algoritmo universalmente melhor: a escolha depende do tamanho da entrada e do grau de ordenação prévia dos dados.


A análise completa está disponível em relatorio_comparacao_ordenacao.pdf.
