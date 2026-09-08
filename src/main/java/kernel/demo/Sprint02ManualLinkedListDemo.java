package kernel.demo;

import kernel.sprint.sprint02.ManualLinkedList;

/**
 * Demo de referencia para a Sprint 02 (Sprint02ManualLinkedList).
 * Exemplo generico ensinando o funcionamento basico de uma Lista Encadeada.
 */
public final class Sprint02ManualLinkedListDemo {
    
    private Sprint02ManualLinkedListDemo() {
    }

    public static void main(String[] args) {
        
        ManualLinkedList<String> arquivosAbertos = new ManualLinkedList<>();

        System.out.println("=== CONTROLE DE ARQUIVOS ABERTOS ===");
        
        // 1. Insercao nas Extremidades (O(1))
        arquivosAbertos.addLast("syslog.txt");
        arquivosAbertos.addFirst("error.log");
        arquivosAbertos.addLast("crash_dump.bin");

        System.out.println("-------------------------------------");
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho atual (nos): " + arquivosAbertos.size());
        System.out.println("-------------------------------------");

        // 2. Acesso Linear (O(n))
        System.out.println("## Arquivos abertos:");
        for (int i = 0; i < arquivosAbertos.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, arquivosAbertos.get(i));
        }
        
        System.out.println("-------------------------------------");
        
        // 3. Remocao Eficiente (O(1)) nas pontas
        String excluido = arquivosAbertos.removeFirst();
        System.out.println("Arquivo removido/fechado: " + excluido);
        
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho apos remocao: " + arquivosAbertos.size());
        
        System.out.println("-------------------------------------");
        System.out.println("## Arquivos abertos:");
        for (int i = 0; i < arquivosAbertos.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, arquivosAbertos.get(i));
        }
        System.out.println("-------------------------------------");

        // System.out.println("DICA DA SPRINT: O Kernel precisa encadear Controle de Arquivos Abertos. Como voce usaria a Lista para isso?");
    }
}
