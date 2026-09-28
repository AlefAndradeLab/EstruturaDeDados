package kernelmjr.demo;

import kernelmjr.manual.Sprint07ManualBinarySearchTree;

/**
 * Demo generica para a Sprint 07 (BST).
 * Ensina insercao logica e ordenacao In-Order em uma arvore usando Numeros.
 */
public final class Sprint07ManualBstDemo {
    
    private Sprint07ManualBstDemo() {
    }

    public static void main(String[] args) {
        
        Sprint07ManualBinarySearchTree bst = new Sprint07ManualBinarySearchTree();

        System.out.println("=== EXEMPLO GENERICO - ARVORE BINARIA DE BUSCA (BST) ===");
        
        // 1. Insercoes descentralizadas (Regra: Menor a esquerda, Maior a direita)
        System.out.println("Inserindo na raiz o numero 50...");
        bst.insert(50); // Raiz
        
        System.out.println("Inserindo ramificacoes (30, 20, 40, 70, 60, 80)...");
        bst.insert(30); 
        bst.insert(20); 
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);
        
        System.out.println("Altura final da arvore: " + bst.height());
        
        System.out.println("--------------------------------");
        
        // 2. O milagre do In-Order (Varredura ordenada)
        // A BST converte magicamente as insercoes numa saida Crescente.
        System.out.println("Busca profunda In-Order (Crescente):");
        System.out.println(bst.inOrder());
        
        System.out.println("--------------------------------");
        
        // 3. Busca Ultra-Rapida (O(h))
        System.out.println("Busca O(log n) ativa:");
        System.out.println("A arvore contem o numero 60? " + bst.contains(60));
        System.out.println("A arvore contem o numero 99? " + bst.contains(99));
        
        System.out.println("--------------------------------");
        
        System.out.println("Ponto de Reflexao: Insira os dados '10, 20, 30, 40' de forma sequencial na BST.");
        System.out.println("Qual sera a Altura final? (Ela vai virar uma Lista Encadeada horrivel!)");
        
        System.out.println("--------------------------------");
        System.out.println("DICA DA SPRINT: O Kernel gerencia blocos de Memoria Livre. Como voce usaria uma BST para achar o bloco com o tamanho ideal mais proximo que foi solicitado pelo Sistema Operacional?");
    }
}
