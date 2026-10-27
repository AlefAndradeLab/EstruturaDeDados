package kernel.sprint.sprint03;

import kernel.sprint.sprint02.ManualLinkedList;

public final class ManualStackSyncro<T> {
    
    // Por debaixo dos panos, nossa Pilha usa a LinkedList que acabamos de criar.
    // Isso se chama "Composicao". Reaproveitamos a logica sem herdar.
    private final ManualLinkedList<T> list = new ManualLinkedList<>();

    /**
     * Adiciona um elemento no topo da pilha (Push).
     * Custo: O(1).
     * Como estamos usando addFirst() da LinkedList, o topo da pilha
     * sempre ficara na cabeca (head) da lista, garantindo insercao instantanea.
     * 
     * @param value O elemento a ser empilhado.
     */
    public synchronized void push(T value) {
        list.addFirst(value);
    }

    /**
     * Remove o elemento no topo da pilha (Pop).
     * Custo: O(1).
     * Aplicacao pratica do LIFO (Last In, First Out). O ultimo a entrar (Push) 
     * esta na cabeca da lista e sera o primeiro a sair (Pop).
     * 
     * @return O elemento removido ou null se vazia.
     */
    public synchronized T pop() {
        return list.removeFirst();
    }

    /**
     * Apenas espia o elemento no topo sem remove-lo (Peek).
     * Custo: O(1).
     * 
     * @return O elemento no topo.
     */
    public T peek() {
        return list.getFirst();
    }

    /**
     * Verifica se a pilha esta vazia.
     * Custo: O(1).
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Retorna a quantidade de itens empilhados.
     * Custo: O(1).
     */
    public int size() {
        return list.size();
    }

    /**
     * Esvazia a pilha.
     */
    public synchronized void clear() {
        list.clear();
    }
}
