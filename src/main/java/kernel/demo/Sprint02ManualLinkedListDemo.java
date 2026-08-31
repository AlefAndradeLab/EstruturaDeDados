package sprint02;

/**
 * Demo de referencia para a Sprint 02 (Sprint02ManualLinkedList).
 * Exemplo generico ensinando o funcionamento basico de uma Lista Encadeada.
 */
public final class Sprint02ManualLinkedListDemo {
    
    private Sprint02ManualLinkedListDemo() {
    }

    public static void main(String[] args) {
        
        Sprint02ManualLinkedList<String> vagao = new Sprint02ManualLinkedList<>();

        System.out.println("=== EXEMPLO GENERICO - LINKED LIST ===");
        
        // 1. Insercao nas Extremidades (O(1))
        vagao.addLast("Vagao de Carga");
        vagao.addFirst("Locomotiva");
        vagao.addLast("Vagao de Passageiros");
        
        System.out.println("Tamanho atual (nos): " + vagao.size());
        
        // 2. Acesso Linear (O(n))
        System.out.println("Ponto Fraco (Leitura Linear):");
        for (int i = 0; i < vagao.size(); i++) {
            System.out.printf("No[%d]: %s%n", i, vagao.get(i));
        }
        
        System.out.println("--------------------------------");
        
        // 3. Remocao Eficiente (O(1)) nas pontas
        String excluido = vagao.removeFirst();
        System.out.println("Desengatou do inicio: " + excluido);
        
        System.out.println("Cabeca atual: " + vagao.getFirst());
        System.out.println("Tamanho apos remocao: " + vagao.size());
        
        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: O Kernel precisa encadear Controle de Arquivos Abertos. Como voce usaria a Lista para isso?");
    }
}
