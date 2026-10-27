package kernel.demo;

import kernel.sprint.sprint03.ManualStack;


public final class Sprint03ManualStackDemo {
    
    private Sprint03ManualStackDemo() {
    }

    public static void main(String[] args) throws InterruptedException {
        
        ManualStack<String> processoStack = new ManualStack<>();

        //Variavel usada para definir quantos processos serão adicionados
        int processos = 1000;

        System.out.println("=== PROCESSOS INTERROMPIDOS ===");

        // 1. Empilhando (Push - O(1))
        for (int i = 1; i <= processos; i++){
            processoStack.push("" + i);
        }

        System.out.println("Processos interrompidos: " + processoStack.size());
        
        // 2. Espiando o topo (Peek - O(1))
        System.out.println("Topo da pilha (Processo atual): " + processoStack.peek());
        
        System.out.println("--------------------------------");
        
        // 3. Desempilhando (Pop - O(1))
        System.out.println("Devolvendo processos para a CPU...");
        String processoVolta;
        int contador = 0;
        while (processoStack.size() >= 0) {
            contador++;
            processoVolta = processoStack.pop();
            System.out.printf("[%d] - Processo devolvido: [%s]%n", contador, processoVolta);
            if (processoStack.size() > (processos*2.1)){
                System.out.println("Erro: Tamanho da pilha inconsistente...");
                System.out.println("Iniciando o processo de recuperação...");
            }                
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

        processoStack.clear();

        System.out.println("------------ Teste de concorrência ------------");

        //Bloco de código que será executado em paralelo na mesma pilha
        Runnable teste = () -> {
            for (int i = 1; i <= processos; i++){
            processoStack.push("" + i);
            }
        };

        //Criando objeto que irá executar o bloco de código paralelo
        Thread t1 = new Thread(teste);
        Thread t2 = new Thread(teste);
        
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Tamanho: " + processoStack.size());
        System.out.println("Tamanho esperado: " + processos*2);


        contador = 0;
        while (processoStack.size() != 0) {
            contador++;
            processoVolta = processoStack.pop();
            System.out.printf("[%d] - Processo devolvido: [%s]%n", contador, processoVolta);
        }

        System.out.println("------------ Fim do teste ------------");
        // System.out.println("DICA DA SPRINT: O Kernel usa uma pilha para salvar o contexto de interrupcoes aninhadas. Pense nisso para a atividade!");
    }
}