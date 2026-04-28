class No {
    int valor;
    No proximo;

    No(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class LinkedList {

    private No primeiro;
    private int tamanho;

    public LinkedList() {
        this.primeiro = null;
        this.tamanho = 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        if (primeiro == null) {
            primeiro = novoNo;
        } else {
            No atual = primeiro;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
    }

    public void exibir(String rotulo) {
        StringBuilder texto = new StringBuilder();
        No atual = primeiro;
        while (atual != null) {
            texto.append(atual.valor).append(" -> ");
            atual = atual.proximo;
        }
        texto.append("None");
        System.out.println("[" + rotulo + "] " + texto);
    }

    private void trocar(No anterior, No noAtual, No noSeguinte) {
        if (noAtual == null || noSeguinte == null) {
            throw new IllegalArgumentException("trocar requer dois nós válidos.");
        }
        if (noAtual.proximo != noSeguinte) {
            throw new IllegalArgumentException("noSeguinte deve ser o sucessor direto de noAtual.");
        }

        // noAtual avança para depois de noSeguinte
        noAtual.proximo = noSeguinte.proximo;
        // noSeguinte passa a apontar para noAtual
        noSeguinte.proximo = noAtual;

        // Atualiza o elo anterior
        if (anterior == null) {
            primeiro = noSeguinte;
        } else {
            anterior.proximo = noSeguinte;
        }
    }

    public void bubbleSort(boolean mostrarDetalhes) {
        if (tamanho <= 1) {
            if (mostrarDetalhes) {
                System.out.println("[bubbleSort] Lista com 0 ou 1 elemento — nada a ordenar.");
            }
            return;
        }

        No limiteOrdenado = null;
        int numeroDaPassagem = 0;
        boolean houveTroca;

        do {
            houveTroca = false;
            No anterior = null;
            No atual = primeiro;
            numeroDaPassagem++;

            while (atual.proximo != null && atual.proximo != limiteOrdenado) {
                No seguinte = atual.proximo;

                if (atual.valor > seguinte.valor) {
                    if (mostrarDetalhes) {
                        System.out.printf("  [Passagem %d] Trocando %d <-> %d%n",
                                numeroDaPassagem, atual.valor, seguinte.valor);
                    }
                    trocar(anterior, atual, seguinte);
                    houveTroca = true;
                    // Após a troca, seguinte assumiu a posição de atual
                    anterior = seguinte;
                    // atual permanece o mesmo objeto, agora à direita
                } else {
                    anterior = atual;
                    atual = atual.proximo;
                }
            }

            // O maior elemento da janela está posicionado corretamente
            limiteOrdenado = atual;

            if (mostrarDetalhes) {
                exibir("Após passagem " + numeroDaPassagem);
            }

        } while (houveTroca);

        if (mostrarDetalhes) {
            System.out.printf("[bubbleSort] Concluído em %d passagem(ns).%n", numeroDaPassagem);
        }
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("  Bubble Sort em Lista Encadeada Simples");
        System.out.println("=".repeat(50));

        // --- Caso 1: lista geral ---
        System.out.println("\n[Caso 1] Lista desordenada geral");
        LinkedList lista1 = new LinkedList();
        for (int valor : new int[]{105, 234, 25, 12, 500, 11, 90}) {
            lista1.inserir(valor);
        }
        lista1.exibir("Pré-ordenação");
        System.out.println();
        lista1.bubbleSort(true);
        System.out.println();
        lista1.exibir("Pós-ordenação");

        // --- Caso 2: lista já ordenada (melhor caso O(n)) ---
        System.out.println("\n" + "=".repeat(50));
        System.out.println("\n[Caso 2] Lista já ordenada (melhor caso)");
        LinkedList lista2 = new LinkedList();
        for (int valor : new int[]{1, 2, 3, 4, 5}) {
            lista2.inserir(valor);
        }
        lista2.exibir("Pré-ordenação");
        System.out.println();
        lista2.bubbleSort(true);
        System.out.println();
        lista2.exibir("Pós-ordenação");

        // --- Caso 3: lista em ordem reversa (pior caso) ---
        System.out.println("\n" + "=".repeat(50));
        System.out.println("\n[Caso 3] Lista em ordem reversa (pior caso)");
        LinkedList lista3 = new LinkedList();
        for (int valor : new int[]{5, 4, 3, 2, 1}) {
            lista3.inserir(valor);
        }
        lista3.exibir("Pré-ordenação");
        System.out.println();
        lista3.bubbleSort(true);
        System.out.println();
        lista3.exibir("Pós-ordenação");

        // --- Caso 4: lista com um único elemento ---
        System.out.println("\n" + "=".repeat(50));
        System.out.println("\n[Caso 4] Lista com um único elemento");
        LinkedList lista4 = new LinkedList();
        lista4.inserir(42);
        lista4.exibir("Pré-ordenação");
        System.out.println();
        lista4.bubbleSort(true);
        System.out.println();
        lista4.exibir("Pós-ordenação");

        // --- Caso 5: lista vazia ---
        System.out.println("\n" + "=".repeat(50));
        System.out.println("\n[Caso 5] Lista vazia");
        LinkedList lista5 = new LinkedList();
        lista5.exibir("Pré-ordenação");
        System.out.println();
        lista5.bubbleSort(true);
        System.out.println();
        lista5.exibir("Pós-ordenação");
    }
}
