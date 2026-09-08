package kernelmjr.manual;

/**
 * Pilha manual (LIFO - Last In, First Out) baseada em lista encadeada.
 *
 * 🍽️ ANALOGIA NO MUNDO REAL E EVOLUÇÃO (Sprint 03):
 * 
 * 1. O que é?
 *    Imagine uma pilha de pratos num restaurante. Você sempre coloca um prato novo no topo 
 *    e sempre tira o prato do topo para usar. Quem entrou por último, sai primeiro (LIFO).
 * 
 * 2. Diferença em relação à LinkedList:
 *    - A LinkedList permite inserir, ler e remover elementos em *qualquer lugar* da lista. 
 *    - A Pilha (Stack) é intencionalmente restrita! Ela usa a LinkedList por trás das cortinas, 
 *      mas só permite que você interaja com uma única extremidade (o Topo).
 *    - "Por que eu iria querer algo que faz MENOS coisas?"
 *      Restrição traz segurança! Em cenários como o Histórico de Navegação do browser (Botão Voltar), 
 *      ou o Call Stack do Kernel, você só deve acessar o "momento atual" (o Topo).
 *
 * Motivo didatico: Modelar contextos de execucao, como a call stack (pilha de chamadas) 
 * do SO ou tratamento de interrupcoes aninhadas. Uma Pilha garante que o ultimo processo
 * interrompido seja o primeiro a ser retomado.
 */
public final class Sprint03ManualStack<T> {
    
    // Por debaixo dos panos, nossa Pilha usa a LinkedList que acabamos de criar.
    // Isso se chama "Composicao". Reaproveitamos a logica sem herdar.
    private final Sprint02ManualLinkedList<T> list = new Sprint02ManualLinkedList<>();

    /**
     * Adiciona um elemento no topo da pilha (Push).
     * Custo: O(1).
     * Como estamos usando addFirst() da LinkedList, o topo da pilha
     * sempre ficara na cabeca (head) da lista, garantindo insercao instantanea.
     * 
     * @param value O elemento a ser empilhado.
     */
    public void push(T value) {
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
    public T pop() {
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
    public void clear() {
        list.clear();
    }
}
