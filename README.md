# Grupo 5 — Bubble Sort em Lista Encadeada com Flag de Troca

**Disciplina:** Projeto e Análise de Algoritmos I — CMP 1046  
**Professor:** Me. Ricardo de Andrade Kratz  
**Entrega/Apresentação:** 29/04

---

## 👥 Integrantes

- Alexandre Henrique Pereira Pires
- Diogenes Varelo Correia
- Lucas Henrique Fogaça Pimenta
- Vitor Hugo Ferreira Lima
- Lucas de Oliveira Fernandes
- Gustavo Souza Rocha
- Luiz Felipe Lobo Felix de Paula

---

## 📋 Sobre o Projeto

Implementação do algoritmo **Bubble Sort com flag de troca** sobre uma **lista encadeada simples**, desenvolvida do zero em Java, sem uso de estruturas ou ordenações prontas da linguagem.

A flag de troca (`houveTroca`) permite que o algoritmo **pare antecipadamente** quando a lista já estiver ordenada, garantindo o melhor caso **O(n)**.

---

## 📁 Estrutura do Projeto

```
.
├── LinkedList.java   # Contém a classe No, a lista encadeada e o Bubble Sort
└── README.md         # Este arquivo
```

### Classes

- **`No`** — representa cada elemento da lista, com um valor inteiro e um ponteiro para o próximo nó.
- **`LinkedList`** — implementa a lista encadeada simples com os métodos:
    - `inserir(int valor)` — insere um elemento no final da lista
    - `exibir(String rotulo)` — imprime todos os elementos da lista
    - `tamanho()` — retorna a quantidade de elementos
    - `trocar(No anterior, No noAtual, No noSeguinte)` — troca dois nós adjacentes reencadeando os ponteiros
    - `bubbleSort(boolean mostrarDetalhes)` — ordena a lista com Bubble Sort + flag de troca

---

## ▶️ Como Compilar e Rodar

### Pré-requisito
Ter o **Java JDK** instalado (versão 8 ou superior).

### Compilar
```bash
javac LinkedList.java
```

### Executar
```bash
java LinkedList
```

### Saída esperada
O programa executa automaticamente 5 casos de teste, exibindo a lista antes, durante e após a ordenação:

- **Caso 1:** Lista desordenada geral `[105, 234, 25, 12, 500, 11, 90]`
- **Caso 2:** Lista já ordenada — melhor caso `[1, 2, 3, 4, 5]`
- **Caso 3:** Lista em ordem inversa — pior caso `[5, 4, 3, 2, 1]`
- **Caso 4:** Lista com um único elemento `[42]`
- **Caso 5:** Lista vazia

---

## 🔍 Como o Algoritmo Funciona

### Ideia geral
Percorre a lista comparando pares de nós adjacentes. Quando um par está fora de ordem, os nós são trocados **reencadeando os ponteiros** (não apenas os valores). O processo se repete até que nenhuma troca ocorra em uma passagem completa.

### Flag de troca
A variável `houveTroca` é reiniciada como `false` no início de cada passagem. Se ao menos uma troca ocorrer, ela vira `true` e o algoritmo faz mais uma passagem. Se terminar uma passagem sem nenhuma troca, a lista está ordenada e o algoritmo para.

### Troca de ponteiros (não de valores)
Dado o estado:
```
[anterior] → [A] → [B] → [próximo]
```
Após a troca de A e B:
```
[anterior] → [B] → [A] → [próximo]
```
Isso é feito em 3 passos na ordem correta:
```java
noAtual.proximo  = noSeguinte.proximo; // A aponta para o que vinha depois de B
noSeguinte.proximo = noAtual;          // B aponta para A
anterior.proximo = noSeguinte;         // quem vinha antes passa a apontar para B
```

---

## 📊 Análise de Complexidade

| Caso | Comparações | Trocas | Espaço |
|---|---|---|---|
| **Melhor caso** (lista já ordenada) | O(n) | O(1) | O(1) |
| **Caso médio** | O(n²) | O(n²) | O(1) |
| **Pior caso** (lista invertida) | O(n²) | O(n²) | O(1) |

### Por que o melhor caso é O(n)?
Graças à **flag de troca**: se a lista já estiver ordenada, o algoritmo faz apenas **1 passagem** completa sem nenhuma troca e para imediatamente. Sem a flag, o Bubble Sort clássico sempre faria O(n²) comparações independentemente da entrada.

### Por que O(1) de espaço?
A ordenação é feita **in-place**, apenas reencadeando os ponteiros existentes. Nenhum nó extra ou estrutura auxiliar é criada durante a ordenação.