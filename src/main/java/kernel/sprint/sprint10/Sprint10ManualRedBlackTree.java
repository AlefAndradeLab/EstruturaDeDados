package kernelmjr.manual;

/**
 * ============================================================================
 * 🧒 PARTE 1: VISÃO DO ALUNO (Analogia no Mundo Real & Conceito Intuitivo)
 * ============================================================================
 * 
 * 1. O que é?
 *    A estrutura de dados mais respeitada e cobrada em entrevistas de Big Techs (Google, Meta, Amazon).
 *    Em vez de fazer rotações pesadas de AVL a cada pequena alteração, ela relaxa o equilíbrio usando
 *    uma bandeira inteligente de "Cores" (Vermelho/Preto). O pior caso continua sendo O(log n), porém
 *    a inserção e a deleção são mais rápidas que na AVL, pois resolvem a maioria dos conflitos
 *    apenas mudando cores em vez de remontar ponteiros na memória.
 * 
 * 2. Regra de Ouro Intuitiva:
 *    Todo nó vermelho DEVE ter filhos pretos (nunca podem existir dois nós vermelhos seguidos na vertical!).
 * 
 * ============================================================================
 * 👨‍🏫 PARTE 2: GUIA TÉCNICO DO PROFESSOR (Roteiro Didático para Sala de Aula)
 * ============================================================================
 * 
 * 1. O PROBLEMA REAL DO DIA A DIA (Onde ela é usada e por que existe?):
 *    - No Linux e Android: O escalonador da CPU (CFS - Completely Fair Scheduler)
 *      armazena todos os processos ativos em uma Red-Black Tree para escolher o próximo
 *      processo com menor tempo de execução em O(log n).
 *    - Na linguagem Java: A classe java.util.TreeMap e java.util.TreeSet rodam internamente
 *      em cima de uma Red-Black Tree. No C++, o std::map e std::set também usam Red-Black.
 *    - A GRANDE PERGUNTA: "Se a AVL (Sprint 08) já era perfeitamente equilibrada, por que
 *      o Linux e o Java escolheram a Red-Black Tree?"
 *      RESPOSTA: A AVL é neurótica por perfeição (diferença máxima de 1 nível). Para cada
 *      inserção ou deleção, ela para tudo para rotacionar ponteiros. Em sistemas que gravam
 *      dados freneticamente, isso gasta muita CPU e invalida caches de memória.
 *      A Red-Black Tree é pragmática: ela aceita que um lado tenha até o DOBRO da altura
 *      do outro, mas em troca resolve quase tudo apenas TROCANDO 1 BIT DE COR (Recoloração)
 *      em vez de ficar quebrando e remontando ponteiros de memória!
 * 
 * 2. AS 5 INVARIANTES SAGRADAS DA RED-BLACK TREE (Regras de Ouro):
 *    1. Todo nó é categoricamente VERMELHO ou PRETO.
 *    2. A Raiz da árvore é SEMPRE PRETA.
 *    3. Toda folha nula (nó null / NIL) é tratada como PRETA.
 *    4. Propriedade Vermelha (No-Red-Red): Se um nó é VERMELHO, seus filhos DEVEM ser PRETOS.
 *       (Tradução: Nunca podem existir dois nós vermelhos consecutivos na vertical!).
 *    5. Propriedade Negra (Black-Height): Para todo nó, todos os caminhos simples dele
 *       até qualquer folha nula contêm exatamente a mesma quantidade de nós pretos.
 *       (Essa regra garante que o caminho mais longo não passe do dobro do mais curto!).
 * 
 * 3. MECÂNICA DE CORREÇÃO PÓS-INSERÇÃO (fixAfterInsert):
 *    - Todo novo nó sempre nasce VERMELHO (pois nascer preto violaria imediatamente
 *      a Regra 5 em toda a árvore, o que seria desastroso).
 *    - Se o pai for PRETO: Inserção concluída com sucesso em O(1)! Nenhuma regra violada.
 *    - Se o pai for VERMELHO: Violação da Regra 4 (Dois vermelhos seguidos!).
 *      Avaliamos a cor do TIO (irmão do pai):
 * 
 *      a) Caso 1: O Tio também é VERMELHO -> RECOLORAÇÃO PURA!
 *         - Pai vira PRETO, Tio vira PRETO, Avô vira VERMELHO.
 *         - O ponteiro sobe para o Avô e continua o loop até a raiz.
 *         - Vantagem: ZERO rotações! Custo insignificante de 1 bit.
 * 
 *      b) Caso 2: O Tio é PRETO (ou nulo) -> ROTAÇÃO + TROCA DE CORES!
 *         - Se formar zig-zag (filho à direita de pai à esquerda), faz rotação simples
 *           no pai para alinhar em linha reta.
 *         - Pai vira PRETO, Avô vira VERMELHO, e faz rotação no Avô.
 *         - Custo: No máximo 2 rotações e o rebalanceamento termina!
 * 
 * 4. PERGUNTAS DE PROVOCAÇÃO PARA A TURMA (Com Respostas):
 *    - P1: "Por que novos nós sempre nascem Vermelhos e não Pretos?"
 *      R: Se nascessem Pretos, violariam imediatamente a Regra 5 (Black-Height) para todas
 *         as outras folhas da árvore, exigindo rebalanceamento global. Nascer Vermelho
 *         só pode gerar um conflito local com o pai (Regra 4), fácil de corrigir!
 *    - P2: "Em uma entrevista de emprego (Big Tech), quando você recomendaria AVL vs Red-Black?"
 *      R: Se o sistema for 90%+ de LEITURAS (ex: busca de CEP, dicionário estático), use AVL,
 *         pois ela é mais baixa e a busca é milimetricamente mais rápida.
 *         Se o sistema tiver MUITAS GRAVAÇÕES e ATUALIZAÇÕES contínuas (ex: banco de dados,
 *         kernel do SO, filas de eventos), use Red-Black, pois ela insere/deleta muito mais rápido!
 * 
 * 5. COMPLEXIDADE ASSINTÓTICA (BIG-O):
 *    - Busca:    O(log n) [Altura máxima garantida: h <= 2 * log2(n + 1)]
 *    - Inserção: O(log n) [Tempo de descer O(log n) + no máximo 2 rotações]
 *    - Remoção:  O(log n) [Tempo de busca O(log n) + no máximo 3 rotações]
 *    - Espaço:   O(n) [Gasta apenas 1 bit extra por nó para a flag booleana de cor]
 * ============================================================================
 */
public final class Sprint10ManualRedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final class Node {
        private int value;
        private boolean color = RED; // Todo novo no nasce Vermelho!
        private Node left;
        private Node right;
        private Node parent;

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
     * Insercao com custo O(log n).
     */
    public void insert(int value) {
        Node current = root;
        Node parent = null;

        // 1. Busca padrao de BST para encontrar a folha vazia
        while (current != null) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            } else {
                return; // Ignora valores duplicados
            }
        }

        Node node = new Node(value);
        node.parent = parent;

        // 2. Conecta o novo no a arvore
        if (parent == null) {
            root = node;
        } else if (value < parent.value) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        size++;

        // 3. A Magia: Conserta as violacoes de regras de cor!
        fixAfterInsert(node);
    }

    public boolean contains(int value) {
        Node node = root;
        while (node != null) {
            if (value < node.value) {
                node = node.left;
            } else if (value > node.value) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public int height() {
        return height(root);
    }

    public String inOrder() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        inOrder(root, builder);
        builder.append(']');
        return builder.toString();
    }

    public String inOrderWithColors() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        inOrderWithColors(root, builder);
        builder.append(']');
        return builder.toString();
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
    }

    private void inOrder(Node node, StringBuilder builder) {
        if (node == null) {
            return;
        }
        inOrder(node.left, builder);
        append(builder, Integer.toString(node.value));
        inOrder(node.right, builder);
    }

    private void inOrderWithColors(Node node, StringBuilder builder) {
        if (node == null) {
            return;
        }
        inOrderWithColors(node.left, builder);
        append(builder, node.value + "(" + (node.color == RED ? "R" : "B") + ")");
        inOrderWithColors(node.right, builder);
    }

    private void append(StringBuilder builder, String value) {
        if (builder.length() > 1) {
            builder.append(", ");
        }
        builder.append(value);
    }

    /**
     * O algoritmo de recoloracao e rotacao da arvore Rubro-Negra.
     * Trata o caso critico: Ocorreu um No Vermelho que e filho de outro No
     * Vermelho.
     */
    private void fixAfterInsert(Node node) {
        while (node != root && colorOf(parentOf(node)) == RED) {
            Node parent = parentOf(node);
            Node grand = parentOf(parent); // Avo

            if (parent == leftOf(grand)) {
                Node uncle = rightOf(grand); // Tio

                // Caso 1: O Tio tambem e Vermelho. Apenas recolorir e subir.
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grand, RED);
                    node = grand;
                } else {
                    // Caso 2: O Tio e Preto. Rotacao necessaria!
                    if (node == rightOf(parent)) {
                        node = parent;
                        rotateLeft(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rotateRight(parentOf(parentOf(node)));
                }
            } else {
                // Simetrico: O pai esta a direita do Avo.
                Node uncle = leftOf(grand);
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grand, RED);
                    node = grand;
                } else {
                    if (node == leftOf(parent)) {
                        node = parent;
                        rotateRight(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rotateLeft(parentOf(parentOf(node)));
                }
            }
        }

        // A regra mais inquebravel: A RAIZ SEMPRE DEVE SER PRETA!
        setColor(root, BLACK);
    }

    private void rotateLeft(Node pivot) {
        Node right = pivot.right;
        pivot.right = right.left;
        if (right.left != null) {
            right.left.parent = pivot;
        }

        right.parent = pivot.parent;
        if (pivot.parent == null) {
            root = right;
        } else if (pivot == pivot.parent.left) {
            pivot.parent.left = right;
        } else {
            pivot.parent.right = right;
        }

        right.left = pivot;
        pivot.parent = right;
    }

    private void rotateRight(Node pivot) {
        Node left = pivot.left;
        pivot.left = left.right;
        if (left.right != null) {
            left.right.parent = pivot;
        }

        left.parent = pivot.parent;
        if (pivot.parent == null) {
            root = left;
        } else if (pivot == pivot.parent.right) {
            pivot.parent.right = left;
        } else {
            pivot.parent.left = left;
        }

        left.right = pivot;
        pivot.parent = left;
    }

    private Node parentOf(Node node) {
        return node == null ? null : node.parent;
    }

    private Node leftOf(Node node) {
        return node == null ? null : node.left;
    }

    private Node rightOf(Node node) {
        return node == null ? null : node.right;
    }

    private boolean colorOf(Node node) {
        // Regra Inquebravel 2: Nos nulos (folhas vazias) sao tratados como PRETOS.
        return node == null ? BLACK : node.color;
    }

    private void setColor(Node node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }
}
