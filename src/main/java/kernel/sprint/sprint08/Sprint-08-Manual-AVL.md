# Sprint 08 - Manual AVL Tree

## 1. Nome da Sprint
Sprint 08 - Manual AVL Tree

## 2. Fase Didatica
Fase 1 - Fundamentos Manuais

## 3. Tema da Aula
Arvore auto-balanceada

## 4. Classe(s) Foco
Sprint08ManualAvlTree

## 5. Problema
A BST comum sofre de degeneração (vira uma lista ligada) se os dados entrarem já ordenados, perdendo a eficiência O(log N). Precisamos de uma árvore que se rebalanceie automaticamente para garantir performance previsível.
## 6. Objetivo da Sprint
- Implementar AVL com rotacoes e manter altura controlada
- [Avançado] Tratar edge-cases: Balanceamento contínuo após deleções em cascata e verificação de integridade do Fator de Balanceamento.
- [Avançado] Medir o overhead (custo extra) das rotações na inserção comparado a uma BST comum sob estresse (inserção massiva).

## 7. Conceitos-Chave
- Fator de balanceamento, rotacoes simples/duplas, O(log n)

## 8. Atividade em Sala
1. Revisar a estrutura foco e as operacoes principais.
2. Executar a demonstracao da sprint.
3. Pedir explicacao de custo dominante (tempo e espaco).
4. [Desafio] Simular um gargalo de performance ou falha de edge-case e demonstrar a recuperação do sistema.

## 9. Comando de Execucao
`mvn -Dexec.mainClass=kernelmjr.demo.ManualStructuresDemo exec:java`

## 10. Evidencia Obrigatoria
- Altura balanceada apos insercoes desbalanceadas

## 11. Criterios de Aceitacao
- Funcionalidade principal da sprint concluida.
- Evidencia apresentada no console ou snapshot.
- Explicacao tecnica coerente sobre escolha da estrutura.
- Tratar casos extremos e falhas de limites estruturais sem ocasionar crashs não tratados (Graceful Degradation).

## 12. Entrega de Casa
- Escrever resumo curto (10 a 15 linhas) sobre a estrutura/modulo da sprint.
- Registrar um exemplo de entrada e saida observado na execucao.
- Descrever o custo dominante da operacao principal.
- Incluir análise detalhada de complexidade Big-O e como a estrutura se comporta sob testes de carga (stress tests).

## 13. Erros Comuns
- Implementar sem demonstrar evidencia objetiva.
- Nao justificar a escolha da estrutura.
- Ignorar custo dominante da operacao principal.

## 14. Material de Estudo Recomendado (Tópicos Avançados)
Para ajudar a responder as exigências avançadas desta sprint, pesquise sobre:
- **Fator de Balanceamento:** Como calcular as alturas e definir quando rotacionar.
- **Rotações AVL:** Rotação Simples (LL, RR) e Dupla (LR, RL) e seus impactos de performance na inserção.
