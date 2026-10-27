package kernelmjr.manual;

/**
 * ============================================================================
 * 🧒 PARTE 1: VISÃO DO ALUNO (Analogia no Mundo Real & Conceito Intuitivo)
 * ============================================================================
 * 
 * 1. O que é?
 *    Imagine um móbile pendurado no berço de um bebê. Se você pendurar elefantes só 
 *    de um lado, ele vai desabar e ficar reto feito uma corda, perdendo toda a beleza da sua "árvore".
 * 
 * 2. Corrigindo a falha da Árvore Binária (Sprint 07):
 *    - A BST da Sprint anterior tem um "calcanhar de aquiles": se inserirmos dados já em ordem
 *      (1, 2, 3, 4, 5...), ela não se bifurca. Ela cresce apenas para a direita e vira 
 *      literalmente uma LinkedList. Com isso, sua Busca cai para O(n). Que horror!
 *    - A Árvore AVL atua como um fiscal de obras: se a diferença de altura entre a esquerda e direita
 *      de qualquer galho ficar maior que 1, ela faz "rotações" acrobáticas para reequilibrar os galhos na hora,
 *      garantindo O(log n) perpétuo.
 * 
 * Motivo didático: Mostrar como a matemática resolve o "Pior Caso" da BST.
 * Através de Rotações, a árvore garante que sua altura (h) nunca passe muito 
 * de log(n). Logo, a Busca e a Inserção serão O(log n) garantidos para sempre!
 * 
 * ============================================================================
 * 👨‍🏫 PARTE 2: GUIA TÉCNICO DO PROFESSOR (Roteiro Didático para Sala de Aula)
 * ============================================================================
 * 
 * 1. O PROBLEMA REAL DO DIA A DIA (Por que ensinar isso?):
 *    - Na Sprint 07 (BST comum), se os dados entram ordenados (ex: IDs auto-incrementais
 *      1, 2, 3, 4, 5... ou datas sequenciais), a árvore não se ramifica: ela degenera
 *      em uma FILA INDIANA (LinkedList).
 *    - Consequência no mundo real: A busca que deveria ser instantânea O(log n) cai
 *      para O(n). Num banco de dados com 1 milhão de clientes, a busca passa de 20 passos
 *      para 1 milhão de passos, congelando a aplicação.
 *    - A AVL (criada em 1962 por Adelson-Velsky e Landis) foi a PRIMEIRA árvore
 *      auto-balanceada da história da computação, garantindo que o pior caso NUNCA ocorra.
 * 
 * 2. A INVARIANTE MATEMÁTICA CENTRAL (Explicada Passo a Passo Sem Jargões):
 *    a) O que é 'u'?
 *       - É apenas a notação matemática para representar "um nó qualquer" da árvore.
 * 
 *    b) O que é altura(u)?
 *       - É a quantidade de "andares" (níveis) desse nó até a folha mais profunda abaixo dele.
 *         Nó folha tem altura 0; nó inexistente/nulo (null) tem altura -1.
 * 
 *    c) O que é FB(u) (Fator de Balanceamento)?
 *       - É a física da GANGORRA entre as duas pernas do nó:
 *         FB(u) = altura(u.left) - altura(u.right)
 *       - Mede quem está pesando mais: a perna esquerda ou a perna direita.
 * 
 *    d) Regra Pétrea da AVL: Para CADA nó da árvore, FB(u) DEVE pertencer a {-1, 0, +1}:
 *       - A árvore só tolera no máximo 1 andar de diferença entre os lados:
 *         * FB =  0 -> Equilíbrio perfeito (ambos os lados têm a mesma altura).
 *         * FB = +1 -> Lado esquerdo tem 1 andar a mais (tolerável).
 *         * FB = -1 -> Lado direito tem 1 andar a mais (tolerável).
 * 
 *    e) O que significa |FB(u)| > 1 (Módulo maior que 1)?
 *       - A gangorra ENTORTOU: a diferença virou 2 andares (FB = +2 ou FB = -2).
 *       - A árvore desbalanceou e a subida da recursão EXIGE correção imediata
 *         com rotações em O(1) para puxar o nó central para cima.
 * 
 *    f) O que significa o Teorema h < 1.44 * log2(n + 2)?
 *       - Tradução prática: A árvore NUNCA ficará funda demais.
 *       - Exemplo real: Se cadastrar 1.000.000 (1 milhão) de clientes em ordem crescente:
 *         * Numa BST comum sem balanceamento (linha reta): Busca faz até 1.000.000 de passos (O(n) - trava a aplicação).
 *         * Na AVL: A altura máxima NUNCA passa de ~28 andares! A busca acha qualquer
 *           cliente em no máximo 28 comparações (O(log n) perpétuo garantido).
 * 
 * 3. OS 4 CASOS DE ROTAÇÃO (Roteiro Visual Completo para Quadro Negro):
 *    Intuição Física Central: Pense em 3 bolas amarradas num barbante. Se pendurar pela ponta,
 *    vira uma linha reta feia. Para equilibrar como um móbile de berço, você só precisa
 *    PEGAR A BOLA DO MEIO E PUXAR PRA CIMA!
 * 
 *    -------------------------------------------------------------------------
 *    a) Caso Direita-Direita (RR / Right-Right) -> Rotação Simples à Esquerda:
 *       - Quando ocorre: Inserção contínua crescente (ex: 10 -> 20 -> 30).
 *       - Sintoma: FB(u) = -2 e FB(u.right) <= 0 (peso todo caindo para a direita).
 * 
 *          (10)                             (20)  <-- Nó do meio sobe e vira raiz!
 *            \         GIRA PRA            /    \
 *            (20)      ESQUERDA          (10)   (30)
 *               \     ---------->
 *               (30)
 *       - Solução no código: rotateLeft(10). Custo: O(1) (apenas 3 ponteiros reatribuídos).
 * 
 *    -------------------------------------------------------------------------
 *    b) Caso Esquerda-Esquerda (LL / Left-Left) -> Rotação Simples à Direita:
 *       - Quando ocorre: Inserção contínua decrescente (ex: 30 -> 20 -> 10).
 *       - Sintoma: FB(u) = +2 e FB(u.left) >= 0 (peso todo caindo para a esquerda).
 * 
 *            (30)                           (20)  <-- Nó do meio sobe e vira raiz!
 *            /         GIRA PRA            /    \
 *          (20)        DIREITA           (10)   (30)
 *          /          ---------->
 *        (10)
 *       - Solução no código: rotateRight(30). Custo: O(1).
 * 
 *    -------------------------------------------------------------------------
 *    c) Caso Esquerda-Direita (LR / Left-Right) -> ROTAÇÃO DUPLA (O "Joelho Quebrado"):
 *       - Quando ocorre: Inserção em formato de joelho/zig-zag (ex: 30 -> 10 -> 20).
 *       - Sintoma: FB(u) = +2 e FB(u.left) < 0.
 *       - Por que dupla? O nó não está em linha reta; precisa alinhar antes de puxar!
 * 
 *          Passo 1: Desentorta o joelho         Passo 2: Puxa o meio pra cima
 *          (Gira o filho 10 pra esquerda)       (Gira o pai 30 pra direita)
 * 
 *               (30)              (30)                     (20)
 *               /                 /                       /    \
 *             (10)    alinhou   (20)       equilibrou   (10)   (30)
 *                \   ------->   /         ---------->
 *                (20)         (10)
 *       - Solução no código: node.left = rotateLeft(node.left); return rotateRight(node);
 * 
 *    -------------------------------------------------------------------------
 *    d) Caso Direita-Esquerda (RL / Right-Left) -> ROTAÇÃO DUPLA (Zig-Zag Inverso):
 *       - Quando ocorre: Inserção em zig-zag inverso (ex: 10 -> 30 -> 20).
 *       - Sintoma: FB(u) = -2 e FB(u.right) > 0.
 * 
 *          Passo 1: Desentorta o joelho         Passo 2: Puxa o meio pra cima
 *          (Gira o filho 30 pra direita)        (Gira o pai 10 pra esquerda)
 * 
 *             (10)              (10)                       (20)
 *                \                 \                      /    \
 *                (30)  alinhou     (20)    equilibrou   (10)   (30)
 *                /    ------->        \   ---------->
 *              (20)                   (30)
 *       - Solução no código: node.right = rotateRight(node.right); return rotateLeft(node);
 * 
 *    -------------------------------------------------------------------------
 *    e) E quem são os "órfãos" (movedSubtree) nas rotações do código?
 *       - O Drama da Adoção: Imagine que o nó 20 já tinha um filho esquerdo [15] (pois 10 < 15 < 20).
 *       - Por que ele vira órfão? O 20 vai subir para virar a nova raiz. Para abraçar o antigo pai 10
 *         com o braço esquerdo (newRoot.left = node), o 20 TEM que desocupar essa mão e soltar o [15]!
 *       - Quem adota o [15]? O nó 10 desceu e seu braço direito ficou completamente LIVRE.
 *         Como 15 > 10 e 15 < 20, o nó 10 adota o [15] na direita (node.right = movedSubtree)!
 * 
 *          1. ANTES DA ROTAÇÃO (Torta):      2. A TROCA DE BRAÇOS E ADOÇÃO:
 * 
 *                 (10) [node]                               (20) [newRoot]
 *                /    \                                    /    \
 *              (5)    (20) [newRoot]                    (10)     (30)
 *                     /  \                             /    \
 *     [órfão] -->   [15] (30)                        (5)    [15] <-- ADOTADO PELO 10!
 * 
 *       - Mapeamento exato nas 4 linhas do método rotateLeft():
 *         1) Node newRoot = node.right;        // O 20 assume o topo
 *         2) Node movedSubtree = newRoot.left; // Salva o [15] no bolso antes que se perca
 *         3) newRoot.left = node;              // O 20 abraça o 10 na esquerda
 *         4) node.right = movedSubtree;        // O 10 adota o [15] no seu braço direito livre
 * 
 *       - Prova da Invariante: 5 < 10 < 15 < 20 < 30 (Ordem BST 100% preservada em O(1)!).
 * 
 * 4. PERGUNTAS DE PROVOCAÇÃO PARA A TURMA (Com Respostas):
 *    - P1: "Professor, por que não reconstruímos a árvore do zero quando ela desbalancear?"
 *      R: Reconstruir a árvore exige percorrer todos os nós: custo O(n). A rotação da AVL
 *         opera localmente alterando apenas 3 ponteiros: custo O(1)!
 *    - P2: "Qual a diferença entre a AVL e a Red-Black Tree que veremos na Sprint 10?"
 *      R: A AVL é rigorosa (diferença máxima de 1 nível), excelente para cenários com
 *         MUITAS BUSCAS e pouca escrita. A Red-Black tolera ramos até 2x maiores, mas
 *         é mais veloz na inserção/deleção porque faz menos rotações e mais trocas de cor.
 * 
 * 5. COMPLEXIDADE ASSINTÓTICA (BIG-O):
 *    - Busca:    O(log n) [Melhor, Médio e Pior caso garantidos!]
 *    - Inserção: O(log n) [O(log n) para descer + O(1) por rotação na subida recursiva]
 *    - Remoção:  O(log n) [Pode exigir até O(log n) rotações na volta da recursão]
 *    - Espaço:   O(n) para guardar os nós + 1 inteiro por nó (height).
 * ============================================================================
 */
public final class Sprint08ManualAvlTree {
    
    private static final class Node {
        private int value;
        private int height; // Diferencial: Cada no memoriza sua propria altura.
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
            this.height = 0;
        }
    }

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    /**
     * Insere garantindo o balanceamento AVL.
     * Custo Pior Caso: O(log n).
     */
    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        // 1. Insercao padrao de BST
        if (node == null) {
            size++;
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node; // Ignora duplicatas
        }

        // 2. Atualiza a altura Deste no (afinal, algo foi inserido abaixo dele)
        updateHeight(node);
        
        // 3. Verifica o fator de balanceamento e rotaciona se necessario!
        return rebalance(node);
    }

    public boolean contains(int value) {
        // Busca padrao, igualzinha a BST (O(log n) garantido aqui).
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
        return nodeHeight(root);
    }

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
        inOrder(node.left, builder);
        if (builder.length() > 1) {
            builder.append(", ");
        }
        builder.append(node.value);
        inOrder(node.right, builder);
    }

    /**
     * O Coracao da AVL: O Rebalanceamento.
     * Verifica se o lado esquerdo esta muito maior que o direito ou vice-versa.
     */
    private Node rebalance(Node node) {
        // Calcula a diferenca de alturas entre as sub-arvores (Esquerda - Direita).
        int balance = balance(node);

        // Se o Fator for > 1, significa que o lado Esquerdo esta pesando muito.
        if (balance > 1) {
            // Caso Esquerda-Direita: o "joelho" da arvore quebrou para a direita.
            // Precisamos primeiro alinhar (rotacao esquerda) o filho.
            if (balance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            // Com tudo alinhado, tombamos a arvore para a direita, restaurando o equilibrio.
            return rotateRight(node);
        }

        // Se o Fator for < -1, significa que o lado Direito esta pesando muito.
        if (balance < -1) {
            // Caso Direita-Esquerda: "joelho" quebrado para a esquerda.
            if (balance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            // Rotacao dupla completa
            return rotateLeft(node);
        }

        return node; // Se o fator for -1, 0 ou 1, esta perfeitamente balanceado. Nao faz nada.
    }

    /**
     * Rotacao Simples a Esquerda.
     * Custo: O(1). Apenas mudamos tres ponteiros!
     */
    private Node rotateLeft(Node node) {
        Node newRoot = node.right; // A direita assume o topo
        Node movedSubtree = newRoot.left; // Os orfaos do novo topo...

        newRoot.left = node; // O antigo topo desce para a esquerda
        node.right = movedSubtree; // Os orfaos sao adotados a direita do antigo topo

        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Rotacao Simples a Direita.
     * Custo: O(1).
     */
    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        Node movedSubtree = newRoot.right;

        newRoot.right = node;
        node.left = movedSubtree;

        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Calcula o Fator de Balanceamento (AlturaEsq - AlturaDir).
     */
    private int balance(Node node) {
        return node == null ? 0 : nodeHeight(node.left) - nodeHeight(node.right);
    }

    private void updateHeight(Node node) {
        int leftHeight = nodeHeight(node.left);
        int rightHeight = nodeHeight(node.right);
        node.height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

    private int nodeHeight(Node node) {
        return node == null ? -1 : node.height;
    }
}
