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
        
        ManualStack<String> historicoNav = new ManualStack<>();

        System.out.println("=== EXEMPLO GENERICO - MANUAL STACK (LIFO) ===");
        
        // 1. Empilhando (Push - O(1))
        historicoNav.push("Google.com");
        historicoNav.push("Youtube.com");
        historicoNav.push("Github.com");
        
        System.out.println("Paginas empilhadas: " + historicoNav.size());
        
        // 2. Espiando o topo (Peek - O(1))
        System.out.println("Topo da pilha (Pagina atual): " + historicoNav.peek());
        
        System.out.println("--------------------------------");
        
        // 3. Desempilhando (Pop - O(1))
        System.out.println("Clicando no botao 'Voltar'...");
        String fechada = historicoNav.pop();
        System.out.println("Fechou a aba: " + fechada);
        
        System.out.println("Novo topo da pilha (Nova Pagina): " + historicoNav.peek());
        System.out.println("Paginas restantes na pilha: " + historicoNav.size());
        
        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: O Kernel usa uma pilha para salvar o contexto de interrupcoes aninhadas. Pense nisso para a atividade!");
    }
}
