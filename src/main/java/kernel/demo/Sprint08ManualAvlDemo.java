package kernelmjr.demo;

import kernelmjr.manual.Sprint08ManualAvlTree;

/**
 * Demo generica para a Sprint 08 (AVL).
 * Demonstra como as rotacoes impedem a arvore de ficar torta/degenerada no Pior Caso.
 */
public final class Sprint08ManualAvlDemo {
    
    private Sprint08ManualAvlDemo() {
    }

    public static void main(String[] args) {
        
        Sprint08ManualAvlTree avl = new Sprint08ManualAvlTree();

        System.out.println("=== EXEMPLO GENERICO - ARVORE AVL (Balanceada) ===");
        
        // Pior cenario para uma BST comum: Insercao Sequencial de dados ordenados.
        System.out.println("Inserindo dados em ordem ABSOLUTA: 10, 20, 30, 40, 50, 60, 70");
        
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(60);
        avl.insert(70);
        
        System.out.println("--------------------------------");
        
        System.out.println("Numa BST comum, a altura seria 6 (Uma linha reta O(n)).");
        System.out.println("Altura protegida pela rotacao AVL: " + avl.height());
        System.out.println("A Busca sempre estara protegida em O(log n).");
        
        System.out.println("--------------------------------");
        
        System.out.println("Imprimindo In-Order para garantir a ordem matematica:");
        System.out.println(avl.inOrder());

        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: O Sistema de Arquivos (FAT/EXT4) do Kernel jamais pode degradar em O(n) senao o PC trava na busca. Como aplicar a AVL no Mapeamento de Arquivos/Drives?");
    }
}
