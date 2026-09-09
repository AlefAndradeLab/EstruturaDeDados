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
        
        // Antes das inserções
        System.out.println("-------------------------------------");
        System.out.println("========= Início da listagem =========");
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho atual (nos): " + arquivosAbertos.size());

        // Insercao nas Extremidades (O(1))
        arquivosAbertos.addLast("syslog.txt");
        arquivosAbertos.addFirst("error.log");
        arquivosAbertos.addLast("crash_dump.bin");
        arquivosAbertos.addLast("usuario_sessao.tmp");
        arquivosAbertos.addFirst("memoria.map");


        // Após as inserções
        System.out.println("========= Primeiras inserções =========");

        // Acesso Linear (O(n))
        for (int i = 0; i < arquivosAbertos.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, arquivosAbertos.get(i));
        }
        System.out.println("-------------------------------------");
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho atual (nos): " + arquivosAbertos.size());
        System.out.println("-------------------------------------");
        
        // Remocao Eficiente (O(1)) nas pontas
        String excluido = arquivosAbertos.removeFirst();
        System.out.println("Arquivo removido/fechado: " + excluido);
        
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho apos remocao: " + arquivosAbertos.size());
        
        System.out.println("-------------------------------------");
        System.out.println("## Arquivos abertos atualmente:");
        for (int i = 0; i < arquivosAbertos.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, arquivosAbertos.get(i));
        }
        System.out.println("-------------------------------------");

        // Remoção por valor (O(n))
        if(arquivosAbertos.removeValue("crash_dump.bin")) {
            System.out.println("Arquivo removido por valor: crash_dump.bin");
        } else {
            System.out.println("Remoção inválida!");
        }
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho atual: " + arquivosAbertos.size());

        System.out.println("-------------------------------------");

        System.out.println("## Arquivos abertos atualmente:");
        for (int i = 0; i < arquivosAbertos.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, arquivosAbertos.get(i));
        }

        System.out.println("-------------------------------------");

        // Teste remoção por valor inválido
        if(arquivosAbertos.removeValue("crash_dump.bin")) {
            System.out.println("Arquivo removido por valor: crash_dump.bin");
        } else {
            System.out.println("Tentativa de remoção por valor inválida!");
        }
        System.out.println("Cabeca atual: " + arquivosAbertos.getFirst());
        System.out.println("Tamanho atual: " + arquivosAbertos.size());

        System.out.println("-------------------------------------");

        System.out.println("## Arquivos abertos atualmente:");
        for (int i = 0; i < arquivosAbertos.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, arquivosAbertos.get(i));
        }

        System.out.println("-------------------------------------");
        // System.out.println("DICA DA SPRINT: O Kernel precisa encadear Controle de Arquivos Abertos. Como voce usaria a Lista para isso?");
    }
}
