package kernel.sprint.sprint02;

/**
 * Lista duplamente encadeada manual.
 *
 * 🚂 PRINCIPAIS DIFERENÇAS EM RELAÇÃO AO ARRAY (Sprint 01):
 * 
 * 1. Alocação de Memória:
 * - Array: Memória contígua (um bloco único). Quando enche, precisa criar um
 * array maior, copiar tudo e jogar o antigo fora (Resize).
 * - LinkedList: Memória dispersa. Cada "vagão" (Nó) vive isolado na memória e
 * nunca "enche". Não existe custo de realocação.
 * 
 * 2. Inserção e Remoção:
 * - Array: Custo O(n) ao remover ou inserir no meio, pois precisamos empurrar
 * todos os elementos seguintes (Shift) para tapar ou abrir o buraco.
 * - LinkedList: Custo O(1) puro. Para retirar ou colocar um vagão no meio,
 * basta trocar as referências dos "engates" do vagão anterior e do próximo.
 * Ninguém é empurrado.
 * 
 * 3. Busca e Acesso por Índice:
 * - Array: Custo O(1). A CPU faz um cálculo matemático simples e vai
 * cirurgicamente na posição de memória exata (acesso randômico). É imbatível
 * nisso.
 * - LinkedList: Custo O(n). Se você quiser acessar o índice 50, terá que
 * começar do primeiro vagão (head) e caminhar engate por engate, 50 vezes, até
 * chegar lá.
 *
 */
public final class Sprint02ManualLinkedList<T> {

    /**
     * Classe interna representando o Nó (Node) da lista.
     * 
     * O que é um Nó?
     * Imagine o Nó como um "vagão de trem". Cada vagão carrega uma carga (o
     * valor/dado)
     * e possui engates que o conectam ao vagão da frente e ao vagão de trás.
     * 
     * Como esta é uma lista duplamente encadeada (Double Linked List), os engates
     * são:
     * - next: aponta para o próximo vagão da lista.
     * - previous: aponta para o vagão anterior da lista.
     * 
     * Essa estrutura flexível permite que a gente "caminhe" pela lista tanto de
     * frente para trás,
     * quanto de trás para frente, com extrema facilidade e sem precisar realocar
     * memória!
     * 
     * <pre>
     *  +-------------+       +-------------------+       +-------------+
     *  |             | <---- |     previous      |       |             |
     *  | Nó Anterior |       +-------------------+       | Próximo Nó  |
     *  |             |       |   Carga (value)   |       |             |
     *  |             |       +-------------------+       |             |
     *  +-------------+       |       next        | ----> |             |
     *                        +-------------------+       +-------------+
     * </pre>
     */
    private static final class Node<T> {
        // A "carga" que este nó transporta (o dado em si, que pode ser um Integer, um
        // Processo do Kernel, etc).
        private T value;

        // O "engate" que aponta e conecta este nó ao próximo nó na sequência.
        private Node<T> next;

        // O "engate" que aponta e conecta este nó ao nó anterior na sequência.
        private Node<T> previous;

        // Ao criar um novo vagão, ele já recebe a sua carga inicial.
        // Os engates (next e previous) começam soltos (apontando para null) por padrão.
        private Node(T value) {
            this.value = value;
        }
    }

    // Apontador para o inicio da lista (Head)
    private Node<T> head;

    // Apontador para o final da lista (Tail). Permite acesso direto ao fim em O(1).
    private Node<T> tail;

    // Mantemos controle do tamanho para nao ter que recontar todos os nos (o que
    // custaria O(n)).
    private int size;

    /**
     * Retorna o tamanho atual da lista.
     * Custo: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Verifica se a lista esta vazia.
     * Custo: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adiciona um elemento no inicio da lista (Head).
     * Custo: O(1). Como usamos ponteiros diretos, nao e necessario deslocar
     * elementos,
     * diferente de um Array sequencial.
     * 
     * @param value O valor a ser inserido.
     */
    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        node.next = head; // O proximo do novo no sera a antiga cabeca

        if (head != null) {
            head.previous = node; // A antiga cabeca agora tem o novo no como anterior
        } else {
            // Se a lista estava vazia, o novo no e tambem a cauda (Tail)
            tail = node;
        }

        head = node; // Atualiza a cabeca da lista para o novo no
        size++;
    }

    /**
     * Adiciona um elemento no final da lista (Tail).
     * Custo: O(1). Operacao super eficiente pois mantemos um ponteiro direto para a
     * cauda (Tail),
     * essencial para implementacoes de Filas (FIFO).
     * 
     * @param value O valor a ser inserido.
     */
    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        node.previous = tail;

        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }

        tail = node;
        size++;
    }

    /**
     * Remove o primeiro elemento da lista.
     * Custo: O(1). Fundamental para Pilhas (LIFO) e Filas (FIFO).
     * 
     * @return O valor removido ou null se a lista estiver vazia.
     */
    public T removeFirst() {
        if (head == null) {
            return null;
        }

        T value = head.value;
        head = head.next; // A cabeca avanca para o proximo no

        if (head != null) {
            head.previous = null; // Remove a referencia para o no deletado
        } else {
            tail = null; // Se a lista ficou vazia, a cauda tambem zera
        }

        size--;
        return value;
    }

    /**
     * Retorna o primeiro valor sem remove-lo (Peek).
     * Custo: O(1)
     */
    public T getFirst() {
        return head == null ? null : head.value;
    }

    /**
     * Retorna o valor em um indice especifico.
     * PONTO FRACO DA LISTA ENCADEADA: Custo Dominante O(n).
     * Como a memoria nao e sequencial, a CPU precisa "pular" de no em no ate chegar
     * ao destino.
     * Diferente do Array, o acesso nao e imediato.
     * 
     * @param index A posicao do elemento.
     * @return O valor armazenado na posicao.
     */
    public T get(int index) {
        checkIndex(index);
        Node<T> node = head;
        // Caminha na lista partindo da cabeca ate o indice desejado (Busca Linear).
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    /**
     * Busca um elemento por valor e o remove.
     * Custo Dominante: O(n) para a busca. A remocao em si (unlink) e O(1).
     * 
     * @param value O valor a ser procurado e removido.
     * @return true se foi encontrado e removido, false caso contrario.
     */
    public boolean removeValue(T value) {
        Node<T> node = head;
        while (node != null) {
            boolean same = value == null ? node.value == null : value.equals(node.value);
            if (same) {
                unlink(node);
                return true;
            }
            node = node.next; // Avanca para o proximo no
        }
        return false;
    }

    /**
     * Limpa a lista removendo as referencias principais.
     * O Garbage Collector se encarregara de limpar os nos "soltos".
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Remove um no especifico do meio da lista religando seus vizinhos.
     * Custo: O(1). Essa e uma operacao extremamente rapida se ja temos a referencia
     * do No.
     * 
     * @param node O no a ser isolado e removido da corrente.
     */
    private void unlink(Node<T> node) {
        // Se o no tem um anterior, o proximo do anterior vira o proximo deste no (Pula
        // o no atual)
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            // Se nao tem anterior, e a cabeca. Logo, a nova cabeca e o proximo.
            head = node.next;
        }

        // Se o no tem um proximo, o anterior do proximo vira o anterior deste no
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            // Se nao tem proximo, e a cauda. Logo, a nova cauda e o anterior.
            tail = node.previous;
        }

        size--;
    }

    /**
     * Valida limites de indice.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
