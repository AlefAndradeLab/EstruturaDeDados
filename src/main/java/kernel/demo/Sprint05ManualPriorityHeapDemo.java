package kernel.demo;

import kernel.sprint.sprint05.ManualPriorityHeap;

/**
 * Demo generica para a Sprint 05 (Sprint05ManualPriorityHeap).
 * Ensinando Min-Heap usando Numeros Inteiros. O Menor numero tem maior prioridade na saida.
 */
public final class Sprint05ManualPriorityHeapDemo {
    
    private Sprint05ManualPriorityHeapDemo() {
    }

    public static void main(String[] args) {
        // Criando Fila de Prioridade para numeros (Menores saem primeiro - Min Heap)
        ManualPriorityHeap<Integer> senhas = new ManualPriorityHeap<>();

        System.out.println("=== EXEMPLO GENERICO - MIN HEAP (Fila de Prioridade) ===");
        
        // 1. Insercao Desordenada (O(log n) por insercao)
        System.out.println("Inserindo senhas aleatoriamente: 50, 10, 30, 5, 20");
        senhas.add(50);
        senhas.add(10);
        senhas.add(30);
        senhas.add(5);
        senhas.add(20);
        
        System.out.println("Senhas ativas na fila: " + senhas.size());
        
        System.out.println("--------------------------------");
        
        // 2. Extracao por Prioridade Absoluta (O(log n) por remocao)
        // Por ser um MIN-Heap, o numero 5 automaticamente borbulhou para o topo.
        System.out.println("Chamando senhas pela ordem de prioridade (Menor p/ Maior):");
        
        while (!senhas.isEmpty()) {
            // poll() vai buscar sempre a raiz do heap (O(1)) e depois rebalancear (O(log n))
            System.out.println("Senha chamada: " + senhas.poll()); 
        }

        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: O Kernel gerencia Processos por 'Nivel de Prioridade'. O nivel 0 e critico (Hardware), 10 e baixa prioridade. Como adaptar a Fila de Prioridade para escalar Processos de SO baseados no 'Nivel'?");
    }
}
