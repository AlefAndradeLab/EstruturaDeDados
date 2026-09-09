package kernel.sprint.sprint05;

import kernel.sprint.sprint01.ManualArray;

/**
 * Min-heap manual (Fila de Prioridade baseada em Arvore Binaria Completa).
 *
 * 🚑 ANALOGIA NO MUNDO REAL E EVOLUÇÃO (Sprint 05):
 * 
 * 1. O que é?
 *    Imagine o Pronto-Socorro de um hospital. Diferente de uma fila de banco (FIFO), 
 *    o paciente infartado será atendido antes do paciente com dor no pé, mesmo que ele tenha chegado depois.
 *    Isso é uma Fila de Prioridade.
 * 
 * 2. Diferença em relação à Fila Normal (Sprint 04):
 *    - Uma fila comum atende pelo tempo de chegada (O(1)). 
 *    - A Fila de Prioridade atende pela *gravidade/peso* (Maior ou Menor valor).
 *    - Se usássemos uma LinkedList para ordenar a prioridade, achar o maior custaria O(n).
 *    - Com o Heap, organizamos os dados em uma Árvore Mágica onde o paciente mais grave 
 *      "flutua" sempre para o topo em tempo logarítmico O(log n).
 *
 * Motivo didatico: Ensinar como selecionar o elemento de maior prioridade 
 * (menor valor comparavel) em tempo O(log n). Essa estrutura e a base de 
 * qualquer escalonador de processos por prioridade ou tratamento de interrupcoes.
 * 
 * Por que usar um Array?
 * Apesar de ser uma Arvore conceitualmente, um Heap completo pode ser perfeitamente 
 * mapeado em um Array Sequencial (O(1) no acesso) usando matematica simples para achar pais e filhos.
 */
public final class ManualPriorityHeap<T extends Comparable<? super T>> {
    
    // O Heap usa nosso Sprint01ManualArray por tras. 
    private final ManualArray<T> heap = new ManualArray<>();

    /**
     * Adiciona um novo elemento a fila de prioridade.
     * Custo Dominante: O(log n) devido ao rebalanceamento (siftUp).
     * 
     * @param value O valor a ser adicionado.
     */
    public void add(T value) {
        // 1. Insere sempre no final absoluto do array (ultima folha da arvore)
        heap.add(value);
        // 2. Restaura a propriedade do heap "borbulhando" o menor valor para cima
        siftUp(heap.size() - 1);
    }

    /**
     * Espia quem e o elemento de MAIOR prioridade (a raiz do Heap).
     * Custo: O(1). A maior prioridade estara sempre no indice 0.
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    /**
     * Remove e retorna o elemento de MAIOR prioridade (a raiz).
     * Custo Dominante: O(log n) devido ao rebalanceamento descendo (siftDown).
     */
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        
        // A raiz (indice 0) sempre contem o elemento de maior prioridade (menor valor).
        T root = heap.get(0);
        
        // Removemos o ultimo elemento da arvore (folha mais profunda)
        T last = heap.removeAt(heap.size() - 1);
        
        // Se a arvore nao ficou vazia apos a remocao...
        if (!heap.isEmpty()) {
            // Colocamos o ultimo elemento la no topo (na raiz)
            heap.set(0, last);
            // E o "empurramos" para baixo ate ele encontrar seu lugar correto (Sift Down)
            siftDown(0);
        }
        
        return root;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    /**
     * Sift Up (Borbulhar para cima).
     * Move o no subindo pela arvore ate que ele seja maior ou igual ao seu pai.
     * O(log n), pois a altura da arvore binaria e log na base 2 de N.
     */
    private void siftUp(int index) {
        while (index > 0) {
            // Formula magica do Heap: O pai de (i) esta no indice (i-1)/2.
            int parent = (index - 1) / 2;
            
            // Se o elemento atual e MAIOR ou IGUAL ao pai, a arvore ja esta correta. Para o laco.
            if (heap.get(index).compareTo(heap.get(parent)) >= 0) {
                break;
            }
            
            // Sendo menor, ele tem mais prioridade que o pai. Logo, trocamos eles de lugar.
            swap(index, parent);
            index = parent; // O elemento subiu, continuamos verificando a partir da nova posicao.
        }
    }

    /**
     * Sift Down (Afundar).
     * Empurra o no para baixo, sempre trocando de lugar com o MENOR de seus filhos.
     * O(log n).
     */
    private void siftDown(int index) {
        while (true) {
            // Formulas do Heap: O filho da esquerda esta em (2*i + 1), e o da direita em (2*i + 2).
            int left = index * 2 + 1;
            int right = left + 1;
            int smallest = index; // Assume que o pai e o menor inicialmente.
            
            // Verifica se o filho da ESQUERDA existe e e menor que o atual
            if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                smallest = left;
            }
            
            // Verifica se o filho da DIREITA existe e e menor que o 'smallest' ate o momento
            if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                smallest = right;
            }
            
            // Se o pai ja for o menor de todos, chegamos ao lugar certo.
            if (smallest == index) {
                break;
            }
            
            // Troca de lugar com o menor filho para continuar afundando.
            swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * Auxiliar de troca (Swap) usando nosso acesso O(1) do array.
     */
    private void swap(int first, int second) {
        T value = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, value);
    }
}
