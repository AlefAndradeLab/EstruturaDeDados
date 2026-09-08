package kernelmjr.demo;

import kernelmjr.manual.Sprint04ManualCircularQueue;

/**
 * Demo de referencia para a Sprint 04 (Sprint04ManualCircularQueue).
 * Exemplo generico ensinando Fila Circular (FIFO sem limites fixos de avanco).
 */
public final class Sprint04ManualCircularQueueDemo {
    
    private Sprint04ManualCircularQueueDemo() {
    }

    public static void main(String[] args) {
        
        Sprint04ManualCircularQueue<String> catraca = new Sprint04ManualCircularQueue<>();

        System.out.println("=== EXEMPLO GENERICO - CIRCULAR QUEUE (FIFO) ===");
        
        // 1. Enfileirando (Enqueue - O(1))
        catraca.enqueue("Pessoa A");
        catraca.enqueue("Pessoa B");
        catraca.enqueue("Pessoa C");
        
        System.out.println("Fila atual: " + catraca.snapshot());
        System.out.println("Quem esta na frente? " + catraca.peek());
        
        System.out.println("--------------------------------");
        
        // 2. Desenfileirando e Rodando a fila (Dequeue/Enqueue - O(1))
        System.out.println("Atendendo e re-enfileirando quem precisa de mais tempo...");
        
        // Pessoa A sai
        String atendido = catraca.dequeue();
        System.out.println("Saiu: " + atendido);
        
        // Pessoa A volta pro fim da fila
        catraca.enqueue(atendido);
        System.out.println("Voltou pro fim da fila: " + atendido);
        
        System.out.println("Fila apos a roda gigante: " + catraca.snapshot());
        System.out.println("Quem e o proximo da fila? " + catraca.peek());
        
        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: Isso grita 'Escalonamento Round Robin' do Kernel. Aplique esse conceito de Fila Circular la!");
    }
}
