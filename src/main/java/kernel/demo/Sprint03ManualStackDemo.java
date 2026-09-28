package kernel.demo;

import kernel.sprint.sprint03.ManualStack;

/**
 * Demo de referencia para a Sprint 03 (Sprint03ManualStack).
 * Exemplo generico ensinando Pilhas (LIFO).
 */
public final class Sprint03ManualStackDemo {
    
    private Sprint03ManualStackDemo() {
    }

    public static void main(String[] args) {
        
        ManualStack<String> processoStack = new ManualStack<>();

        System.out.println("=== PROCESSOS INTERROMPIDOS ===");
        
        // 1. Empilhando (Push - O(1))
        for (int i = 1; i <= 100000; i++){
            processoStack.push("0" + i);
        }
        
        System.out.println("Processos interrompidos: " + processoStack.size());
        
        // 2. Espiando o topo (Peek - O(1))
        System.out.println("Topo da pilha (Processo atual): " + processoStack.peek());
        
        System.out.println("--------------------------------");
        
        // 3. Desempilhando (Pop - O(1))
        System.out.println("Devolvendo processos para a CPU...");
        String processoVolta;
        int contador = 0;
        while (processoStack.size() != 0) {
            contador++;
            processoVolta = processoStack.pop();
            System.out.printf("[%d] - Processo devolvido: [%s]%n", contador, processoVolta);
        }

        System.out.println("*** Teste 1 - Pilha vazia ***");
        System.out.println("Novo topo da pilha (Novo processo interrompido): " + processoStack.peek());
        System.out.println("Processos restantes na pilha: " + processoStack.size());
        
        System.out.println("--------------------------------");

        processoStack.pop();

        System.out.println("*** Teste 2 - Removendo item da fila vazia ***");
        System.out.println("Novo topo da pilha (Novo processo interrompido): " + processoStack.peek());
        System.out.println("Processos restantes na pilha: " + processoStack.size());
        
        System.out.println("--------------------------------");

        processoStack.push(null);
        System.out.println("*** Teste 3 - Adicionando item null ***");
        System.out.println("Novo topo da pilha (Novo processo interrompido): " + processoStack.peek());
        System.out.println("Processos restantes na pilha: " + processoStack.size());
        
        System.out.println("--------------------------------");
        
        // System.out.println("DICA DA SPRINT: O Kernel usa uma pilha para salvar o contexto de interrupcoes aninhadas. Pense nisso para a atividade!");
    }
}