# Sprint 05 - Manual Priority Heap

## 1. Nome da Sprint
Sprint 05 - Manual Priority Heap

## 2. Fase Didatica
Fase 1 - Fundamentos Manuais

## 3. Tema da Aula
Estrutura para prioridade

## 4. Classe(s) Foco
Sprint05ManualPriorityHeap

## 5. Problema
Em uma fila normal ou array, encontrar o item de maior prioridade exige varrer todos os elementos O(N). Manter a estrutura sempre ordenada a cada inserção também é lento O(N). Precisamos de uma estrutura que insira e remova a maior prioridade de forma otimizada.
## 6. Objetivo da Sprint
- Implementar heap manual e extracao por prioridade
- [Avançado] Tratar edge-cases de Heap: o que ocorre ao extrair de um Heap vazio (Underflow) ou inserir além da capacidade inicial do array (Overflow)? Aplicar degradação graciosa.
- [Avançado] Comprovar a complexidade O(log N) das operações up-heap (inserção) e down-heap (extração) utilizando testes de estresse, medindo a degradação conforme o heap cresce.

## 7. Conceitos-Chave
- Heap binario, subir/descer, custo O(log n)

## 8. Atividade em Sala
1. Revisar a estrutura foco e as operacoes principais.
2. Executar a demonstracao da sprint.
3. Pedir explicacao de custo dominante (tempo e espaco).
4. [Desafio] Simular um gargalo de performance ou falha de edge-case e demonstrar a recuperação do sistema.

## 9. Comando de Execucao
`mvn -Dexec.mainClass=kernelmjr.demo.ManualStructuresDemo exec:java`

## 10. Evidencia Obrigatoria
- Ordem de extracao do maior para o menor

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
- **Matemática do Heap:** Como calcular índices de pai ((i-1)/2), filho esquerdo (2i+1) e filho direito (2i+2).
- **Edge-Cases do Heap:** OutOfBounds no array e como prever a necessidade de redimensionamento.
