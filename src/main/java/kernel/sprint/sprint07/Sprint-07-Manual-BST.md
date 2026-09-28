# Sprint 07 - Manual Binary Search Tree

## 1. Nome da Sprint
Sprint 07 - Manual Binary Search Tree

## 2. Fase Didatica
Fase 1 - Fundamentos Manuais

## 3. Tema da Aula
Ordenacao estrutural por chave

## 4. Classe(s) Foco
Sprint07ManualBinarySearchTree

## 5. Problema
Sem uma árvore, se precisarmos manter dados dinâmicos ordenados e ainda assim permitir buscas rápidas, arrays falham na inserção e listas ligadas falham na busca. Uma Àrvore Binária de Busca permite consultas mais rápidas aproveitando a organização estrutural.
## 6. Objetivo da Sprint
- Implementar BST com insercao, busca e in-order
- [Avançado] Tratar edge-cases de BST: Inserção de dados já ordenados (o que causa a degeneração da árvore em uma lista ligada) e deleção de nós com dois filhos.
- [Avançado] Provar com testes de estresse que a complexidade de busca degrada de O(log N) para O(N) quando a árvore perde o balanceamento.

## 7. Conceitos-Chave
- Esquerda < no < direita, altura, custo dependente de balanceamento

## 8. Atividade em Sala
1. Revisar a estrutura foco e as operacoes principais.
2. Executar a demonstracao da sprint.
3. Pedir explicacao de custo dominante (tempo e espaco).
4. [Desafio] Simular um gargalo de performance ou falha de edge-case e demonstrar a recuperação do sistema.

## 9. Comando de Execucao
`mvn -Dexec.mainClass=kernelmjr.demo.ManualStructuresDemo exec:java`

## 10. Evidencia Obrigatoria
- Travessia in-order com valores em ordem crescente

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
- **Degeneração de Àrvores:** O pior caso de uma BST e o impacto de entradas ordenadas.
- **Algoritmos de Deleção:** Sucessor in-order e os três casos de remoção de nós.
