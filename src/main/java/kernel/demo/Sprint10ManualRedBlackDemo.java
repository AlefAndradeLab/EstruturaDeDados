package kernelmjr.demo;

import kernelmjr.manual.Sprint10ManualRedBlackTree;

/**
 * Demo didática dedicada para a Sprint 10 (Árvore Rubro-Negra / Red-Black Tree).
 * Demonstra as 5 propriedades sagradas, a recoloração ("troca de roupa")
 * e as rotações ("troca de cadeiras").
 */
public final class Sprint10ManualRedBlackDemo {

    private Sprint10ManualRedBlackDemo() {
    }

    public static void main(String[] args) {
        Sprint10ManualRedBlackTree rb = new Sprint10ManualRedBlackTree();

        System.out.println("=== SPRINT 10: DEMO DA ÁRVORE RUBRO-NEGRA (Red-Black Tree) ===");
        System.out.println("A estrutura de dados oficial do Kernel do Linux (CFS) e do java.util.TreeMap!\n");

        // 1. Inserindo dados que forçam recolorações e rotações
        System.out.println("1. Inserindo nós: 10, 20, 30, 15, 25, 5, 1");
        int[] values = { 10, 20, 30, 15, 25, 5, 1 };
        for (int value : values) {
            rb.insert(value);
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Percurso Em-Ordem (Crescente)      : " + rb.inOrder());
        System.out.println("Percurso com Cores dos Nós [R / B] : " + rb.inOrderWithColors());
        System.out.println("Altura Estabilizada da Árvore      : " + rb.height());
        System.out.println("Quantidade total de nós            : " + rb.size());
        System.out.println("-----------------------------------------------------------------");

        // 2. Demonstração de Busca O(log n)
        System.out.println("\n2. Teste de Busca (contains):");
        System.out.println("Busca pelo valor 15 (Existe?) : " + rb.contains(15));
        System.out.println("Busca pelo valor 99 (Existe?) : " + rb.contains(99));

        // 3. Testes Avançados: Edge-Cases & Invariantes
        System.out.println("\n--- [Avançado] Edge-Cases & Invariantes ---");
        try {
            rb.insert(10); // Ignora duplicatas com elegância
            System.out.println("Duplicata (10) tratada sem erro. Size mantido em: " + rb.size());
            System.out.println("Busca por elemento inexistente (999): " + rb.contains(999));
        } catch (Exception e) {
            System.out.println("FALHA: " + e.getMessage());
        }

        // 4. Stress Test: 10.000 Inserções Sequenciais
        System.out.println("\n--- [Avançado] Stress Test & Big-O (10.000 Inserções Sequenciais) ---");
        long inicio = System.currentTimeMillis();
        Sprint10ManualRedBlackTree stressTree = new Sprint10ManualRedBlackTree();
        for (int i = 0; i < 10000; i++) {
            stressTree.insert(i);
        }
        long tempo = System.currentTimeMillis() - inicio;
        System.out.println("10.000 inserções em ordem concluídas em: " + tempo + " ms");
        System.out.println("Altura da árvore resultante: " + stressTree.height() + " (Garantida <= 2 * log2(10001) ≈ 28)");

        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("💡 DICA DIDÁTICA PARA A AULA:");
        System.out.println("- Todo novo nó nasce VERMELHO.");
        System.out.println("- Se o Tio for Vermelho: Resolve em O(1) APENAS RECOLORINDO ('troca de roupa')!");
        System.out.println("- Se o Tio for Preto: Faz no máximo 2 rotações ('troca de cadeiras').");
        System.out.println("Por isso o Linux e o Java preferem Red-Black em vez da AVL para gravações!");
    }
}
