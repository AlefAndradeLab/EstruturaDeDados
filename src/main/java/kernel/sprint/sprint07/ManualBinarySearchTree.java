package kernel.sprint.sprint07;

/**
 * Arvore Binaria de Busca (BST - Binary Search Tree) manual.
 *
 * 📖 ANALOGIA NO MUNDO REAL E EVOLUÇÃO (Sprint 07):
 * 
 * 1. O que é?
 *    Imagine procurar uma palavra num dicionário gigante. Você não lê folha por folha (LinkedList). 
 *    Você abre no meio: se a palavra começa com 'T' e você caiu no 'M', joga a metade inteira 
 *    para trás fora e busca só na frente. E faz isso repetidamente.
 * 
 * 2. Juntando o melhor dos dois mundos (Array vs LinkedList):
 *    - Array Ordenado é rápido para buscar O(log n), mas detesta inserções (Shift O(n)).
 *    - LinkedList é ótima para inserção O(1), mas péssima na busca O(n).
 *    - A BST resolve isso: Ela espalha "Vagões" (Nós) pela memória (como a LinkedList), mas 
 *      organiza os engates criando duas ramificações (Esquerda: Menores. Direita: Maiores). 
 *      Resultando em Busca E Inserção de custo O(log n)!
 *
 * Motivo didatico: Ensinar como a ordenacao estrutural por chave permite a
 * busca binaria, cortando o espaco de busca pela metade a cada passo.
 * Demonstra como a complexidade (Big O) e diretamente ligada a Altura da arvore.
 */
public final class ManualBinarySearchTree {
    
    /**
     * O No de uma Arvore Binaria possui ate dois filhos: um a Esquerda e um a Direita.
     */
    private static final class Node {
        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Insere um novo valor na arvore.
     * Custo Dominante: O(h), onde 'h' e a altura da arvore. 
     * No melhor caso (balanceada), h = log(n). No pior caso (arvore degenerada/reta), h = n.
     */
    public void insert(int value) {
        // A insercao e recursiva e garante a regra de ouro da BST:
        // Valores menores vao para a Esquerda. Valores maiores vao para a Direita.
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        // Caso base: encontrou um espaco vazio (null), cria o no aqui.
        if (node == null) {
            size++;
            return new Node(value);
        }
        
        // Se for menor, continua procurando/descendo pela esquerda.
        if (value < node.value) {
            node.left = insert(node.left, value);
        } 
        // Se for maior, continua procurando/descendo pela direita.
        else if (value > node.value) {
            node.right = insert(node.right, value);
        }
        // Se for igual, nao fazemos nada (nesta implementacao ignoramos chaves duplicadas).
        
        return node;
    }

    /**
     * Busca um valor na arvore.
     * Custo: O(h). Ponto forte absoluto da arvore em relacao a Lista Encadeada.
     */
    public boolean contains(int value) {
        // Busca iterativa (com laco while em vez de recursao para evitar estouro de pilha).
        Node node = root;
        
        while (node != null) {
            if (value < node.value) {
                node = node.left; // Corta toda a sub-arvore da direita da busca!
            } else if (value > node.value) {
                node = node.right; // Corta toda a sub-arvore da esquerda!
            } else {
                return true; // Encontrou (value == node.value)
            }
        }
        return false;
    }

    /**
     * Calcula a altura da arvore.
     * A altura e o maior caminho da raiz ate a folha mais profunda.
     * O desempenho da arvore (Busca/Insercao) e 100% dependente desse numero.
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        // Verifica as alturas da esquerda e direita e pega a maior (+1 para contar o nivel atual).
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
    }

    /**
     * Retorna os valores da arvore formatados em String.
     * O percurso "In-Order" (Esquerda -> Raiz -> Direita) em uma BST 
     * magicamente gera os elementos em ORDEM CRESCENTE perfeita.
     * Custo: O(n), pois visita todos os nos.
     */
    public String inOrder() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        inOrder(root, builder);
        builder.append(']');
        return builder.toString();
    }

    private void inOrder(Node node, StringBuilder builder) {
        if (node == null) {
            return;
        }
        
        inOrder(node.left, builder); // 1. Visita tudo que e Menor
        appendValue(builder, node.value); // 2. Visita o Atual
        inOrder(node.right, builder); // 3. Visita tudo que e Maior
    }

    private void appendValue(StringBuilder builder, int value) {
        if (builder.length() > 1) {
            builder.append(", ");
        }
        builder.append(value);
    }

}
