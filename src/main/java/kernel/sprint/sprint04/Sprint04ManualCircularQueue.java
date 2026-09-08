package kernelmjr.manual;

/**
 * Fila circular encadeada manual (FIFO - First In, First Out).
 *
 * 🏦 ANALOGIA NO MUNDO REAL E EVOLUÇÃO (Sprint 04):
 * 
 * 1. O que é?
 *    Imagine a fila do banco ou esteira de supermercado. Quem entra primeiro, é atendido primeiro.
 * 
 * 2. Evolução em relação à Fila Clássica baseada em Array:
 *    - No Array clássico, se o elemento do índice 0 for atendido, todos os elementos seguintes 
 *      precisam dar um "passo à frente" (Shift O(n)), o que é extremamente lento num Kernel com milhões de processos.
 *    - A Fila *Circular* contorna a lentidão do Array. Em vez de empurrar as pessoas para frente,
 *      é o guichê (o apontador de "início") que anda para trás! Quando chega no fim do espaço, 
 *      ele "dá a volta" e reaproveita os buracos deixados no começo. Um Array que morde a própria rabo!
 *
 * Motivo didatico: Estrutura essencial para modelar algoritmos de escalonamento 
 * justos como o Round Robin. Em uma fila circular, o ultimo elemento se conecta 
 * ao primeiro, permitindo que processos voltem para o fim da fila rapidamente O(1) 
 * apos usarem sua fatia de tempo (quantum) da CPU.
 */
public final class Sprint04ManualCircularQueue<T> {
    
    /**
     * Estrutura interna do No (Node). 
     * Diferente da LinkedList duplamente encadeada, aqui precisamos de apenas
     * um ponteiro (next) para fechar o circulo.
     */
    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    // Ao inves de head e tail, em uma lista circular encadeada basta mantermos a referencia 
    // para a cauda (tail). A cabeca da fila sera sempre o (tail.next).
    private Node<T> tail;
    private int size;

    /**
     * Adiciona um elemento no final da fila (Enqueue).
     * Custo: O(1). Insercao instantanea, caracteristica padrao de Filas eficientes.
     * 
     * @param value O valor a entrar na fila.
     */
    public void enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            // Se a fila estava vazia, o novo no aponta para si mesmo formando um circulo.
            node.next = node;
            tail = node;
        } else {
            // O novo no (que sera a nova cauda) aponta para a cabeca antiga (tail.next)
            node.next = tail.next;
            // A antiga cauda aponta para o novo no
            tail.next = node;
            // Atualizamos a referencia principal para o novo no (a nova cauda)
            tail = node;
        }
        size++;
    }

    /**
     * Remove o elemento na frente da fila (Dequeue).
     * Custo: O(1). Respeita a regra FIFO (First In, First Out).
     * 
     * @return O valor que estava na cabeca da fila.
     */
    public T dequeue() {
        if (tail == null) {
            return null; // Fila vazia
        }
        
        // A cabeca (head) e sempre o no apontado pela cauda (tail.next)
        Node<T> head = tail.next;
        T value = head.value;
        
        if (head == tail) {
            // Se so existia 1 elemento na fila (head e tail sao o mesmo no), zeramos tudo.
            tail = null;
        } else {
            // A cauda agora aponta para o SEGUNDO elemento (head.next), descartando a antiga cabeca.
            tail.next = head.next;
        }
        
        size--;
        return value;
    }

    /**
     * Espia qual elemento esta na frente da fila, sem remove-lo.
     * Custo: O(1).
     * 
     * @return O primeiro elemento a ser processado.
     */
    public T peek() {
        return tail == null ? null : tail.next.value;
    }

    /**
     * Verifica se a fila circular esta vazia.
     * Custo: O(1).
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna a quantidade de itens rodando na fila.
     * Custo: O(1).
     */
    public int size() {
        return size;
    }

    /**
     * Representacao textual visual da Fila Circular.
     * Custo: O(n). Usado apenas para fins didaticos e logs visuais da aplicacao.
     */
    public String snapshot() {
        if (tail == null) {
            return "[]";
        }
        
        StringBuilder builder = new StringBuilder("[");
        Node<T> current = tail.next; // Comeca pela cabeca
        
        for (int i = 0; i < size; i++) {
            builder.append(current.value);
            current = current.next;
            if (i + 1 < size) {
                builder.append(" -> ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * Limpa completamente a fila, deixando o Garbage Collector cuidar dos nos soltos.
     */
    public void clear() {
        tail = null;
        size = 0;
    }
}
