# Sprint 10 - Manual Red-Black Tree (Árvore Rubro-Negra)

## 1. Nome da Sprint
Sprint 10 - Manual Red-Black Tree (Árvore Rubro-Negra)

## 2. Fase Didatica
Fase 1 - Fundamentos Manuais (Árvores Auto-Balanceadas Avançadas)

## 3. Tema da Aula
Árvores Balanceadas com Recoloração e Rotações — O padrão de mercado do Linux e do Java (`TreeMap`)

## 4. Classe(s) Foco
`Sprint10ManualRedBlackTree` + `Sprint10ManualRedBlackDemo`

## 5. Problema
Embora a Árvore AVL (Sprint 08) mantenha uma altura perfeitamente rígida, ela exige rotações de ponteiros a quase toda inserção e remoção, o que invalida cache de CPU e degrada a performance em cenários de alta taxa de escrita (write-heavy). Precisamos de uma árvore auto-balanceada mais pragmática, que tolere alturas ligeiramente mais flexíveis mas resolva a maioria das violações apenas trocando 1 bit de cor (recoloração em O(1)), reduzindo drasticamente o número de rotações.

## 6. Objetivo da Sprint
- Compreender e implementar as 5 Invariantes Sagradas da Red-Black Tree.
- Dominar a mecânica de correção pós-inserção (`fixAfterInsert`): Caso do Tio Vermelho (recoloração pura) vs. Caso do Tio Preto (rotações).
- [Avançado] Tratar edge-cases: Violação consecutiva de nós vermelhos (No-Red-Red), garantia da invariante Black-Height em todos os caminhos até nós folhas nulos (NIL) e raiz compulsoriamente preta.
- [Avançado] Análise de Benchmark: Comparar o número de rotações e tempo de inserção massiva entre a AVL Tree e a Red-Black Tree.

## 7. Conceitos-Chave
- 5 Propriedades Rubro-Negras (Black Root, Black NIL, No Red-Red, Equal Black-Height).
- Recoloração (Color Flip) vs. Rotação (Left/Right Rotate).
- Altura Máxima Garantida: $h \le 2 \log_2(n + 1)$.
- Complexidade: Busca $O(\log n)$, Inserção $O(\log n)$ com no máximo 2 rotações, Deleção $O(\log n)$ com no máximo 3 rotações.

## 8. Atividade em Sala
1. Revisar as 5 Invariantes Sagradas e a motivação arquitetural no Kernel do Linux / `java.util.TreeMap`.
2. Executar `Sprint10ManualRedBlackDemo` observando a marcação de cores `[R]` e `[B]`.


## 9. Comando de Execucao
`mvn -Dexec.mainClass=kernelmjr.demo.Sprint10ManualRedBlackDemo exec:java`  
*(Ou via Java direto: `java -cp target/classes kernelmjr.demo.Sprint10ManualRedBlackDemo`)*

## 10. Evidencia Obrigatoria
- Saída no console exibindo o percurso Em-Ordem com as cores de cada nó (`inOrderWithColors`), confirmando ausência de nós vermelhos adjacentes e raiz preta.

## 11. Criterios de Aceitacao
- Inserção correta mantendo a árvore balanceada em $O(\log n)$.
- Recoloração aplicada com sucesso quando o tio é vermelho sem disparar rotações desnecessárias.
- Rotações executadas corretamente quando o tio é preto.
- Raiz terminando sempre preta após qualquer inserção.

## 12. Entrega de Casa
- Resumo técnico (10 a 15 linhas) explicando por que o Kernel do Linux (CFS Scheduler) e o `java.util.TreeMap` optaram pela Red-Black Tree em vez da AVL Tree.
- Rastrear manualmente a inserção dos números `10, 20, 30, 15` desenhando as cores e indicando quando ocorreu recoloração e quando ocorreu rotação.

## 13. Erros Comuns
- Permitir que a raiz fique vermelha ao final de uma inserção.
- Esquecer que nós nulos (folhas vazias) contam como nós pretos para a propriedade de Black-Height.
- Fazer rotação quando o tio é vermelho (quando bastava apenas recolorir).

