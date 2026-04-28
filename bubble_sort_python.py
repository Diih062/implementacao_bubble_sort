class Node:
    """Representa um nó da lista encadeada simples."""

    def __init__(self, data):
        self.data = data
        self.next = None


class LinkedList:
    """Lista encadeada simples com ordenação Bubble Sort otimizada."""

    def __init__(self):
        self.head = None
        self._size = 0

    def __len__(self):
        return self._size

    def append(self, data):
        """Insere um novo nó no final da lista."""
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while current.next is not None:
                current = current.next
            current.next = new_node
        self._size += 1

    def display(self, label="Lista"):
        """Exibe o estado atual da lista com um rótulo descritivo."""
        elements = []
        current = self.head
        while current is not None:
            elements.append(str(current.data))
            current = current.next
        chain = " -> ".join(elements) + " -> None" if elements else "None"
        print(f"[{label}] {chain}")

    def _swap(self, prev, node_a, node_b):
        """
        Troca dois nós adjacentes (node_a -> node_b) rearranjando ponteiros.
        Complexidade de espaço: O(1) — nenhum objeto novo é criado.

        Args:
            prev:   nó anterior a node_a, ou None se node_a for a cabeça.
            node_a: nó que precede node_b diretamente.
            node_b: nó que sucede node_a diretamente.
        """
        if node_a is None or node_b is None:
            raise ValueError("_swap requer dois nós válidos e adjacentes.")
        if node_a.next is not node_b:
            raise ValueError("node_b deve ser o sucessor direto de node_a.")

        # node_a avança para depois de node_b
        node_a.next = node_b.next
        # node_b passa a apontar para node_a
        node_b.next = node_a

        # Atualiza o elo anterior
        if prev is None:
            self.head = node_b
        else:
            prev.next = node_b

    def bubble_sort(self, verbose=False):
        """
        Ordena a lista in-place via Bubble Sort com flag de otimização.

        Complexidade de tempo:
            - Melhor caso  O(n)   — lista já ordenada (flag `swapped` encerra na 1ª passagem)
            - Médio/pior   O(n²)

        Complexidade de espaço: O(1) — apenas ponteiros auxiliares.

        Args:
            verbose: se True, imprime o estado da lista a cada passagem e
                     loga cada troca realizada.
        """
        if self._size <= 1:
            if verbose:
                print("[bubble_sort] Lista com 0 ou 1 elemento — nada a ordenar.")
            return

        end = None          # marcador do último nó já posicionado
        pass_number = 0

        while True:
            swapped = False
            prev = None
            current = self.head
            pass_number += 1

            while current.next is not None and current.next is not end:
                next_node = current.next

                if current.data > next_node.data:
                    if verbose:
                        print(
                            f"  [Passagem {pass_number}] Trocando "
                            f"{current.data} <-> {next_node.data}"
                        )
                    self._swap(prev, current, next_node)
                    swapped = True
                    # Após a troca, `current` desceu uma posição;
                    # prev avança para next_node (que agora ocupa o lugar de current)
                    prev = next_node
                    # current permanece o mesmo objeto, agora na posição seguinte
                else:
                    prev = current
                    current = current.next

            # O maior elemento da janela está no lugar correto
            end = current

            if verbose:
                self.display(label=f"Após passagem {pass_number}")

            if not swapped:
                break

        if verbose:
            print(f"[bubble_sort] Concluído em {pass_number} passagem(ns).")


def main():
    print("=" * 50)
    print("  Bubble Sort em Lista Encadeada Simples")
    print("=" * 50)

    # --- Caso 1: lista geral ---
    print("\n[Caso 1] Lista desordenada geral")
    ll = LinkedList()
    for value in [105, 234, 25, 12, 500, 11, 90]:
        ll.append(value)

    ll.display(label="Pré-ordenação")
    print()
    ll.bubble_sort(verbose=True)
    print()
    ll.display(label="Pós-ordenação")

    # --- Caso 2: lista já ordenada (melhor caso O(n)) ---
    print("\n" + "=" * 50)
    print("\n[Caso 2] Lista já ordenada (melhor caso)")
    ll2 = LinkedList()
    for value in [1, 2, 3, 4, 5]:
        ll2.append(value)

    ll2.display(label="Pré-ordenação")
    print()
    ll2.bubble_sort(verbose=True)
    print()
    ll2.display(label="Pós-ordenação")

    # --- Caso 3: lista em ordem reversa (pior caso) ---
    print("\n" + "=" * 50)
    print("\n[Caso 3] Lista em ordem reversa (pior caso)")
    ll3 = LinkedList()
    for value in [5, 4, 3, 2, 1]:
        ll3.append(value)

    ll3.display(label="Pré-ordenação")
    print()
    ll3.bubble_sort(verbose=True)
    print()
    ll3.display(label="Pós-ordenação")

    # --- Caso 4: lista com um único elemento ---
    print("\n" + "=" * 50)
    print("\n[Caso 4] Lista com um único elemento")
    ll4 = LinkedList()
    ll4.append(42)

    ll4.display(label="Pré-ordenação")
    print()
    ll4.bubble_sort(verbose=True)
    print()
    ll4.display(label="Pós-ordenação")

    # --- Caso 5: lista vazia ---
    print("\n" + "=" * 50)
    print("\n[Caso 5] Lista vazia")
    ll5 = LinkedList()

    ll5.display(label="Pré-ordenação")
    print()
    ll5.bubble_sort(verbose=True)
    print()
    ll5.display(label="Pós-ordenação")


if __name__ == "__main__":
    main()
