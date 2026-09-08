# Sprint 03 - Manual Stack

## 1. Nome da Sprint
Sprint 03 - Manual Stack

## 2. Fase Didatica
Fase 1 - Fundamentos Manuais

## 3. Tema da Aula
LIFO, push, pop e peek

## 4. Classe(s) Foco
Sprint03ManualStack

## 5. Problema
Sem pilha, fica dificil modelar contexto de interrupcao e chamada

## 6. Objetivo da Sprint
- Implementar pilha manual e validar comportamento LIFO
- [Avançado] Implementar tratamento de edge-cases e cenários de falha (ex: Overflow, OutOfMemory, Concorrência).
- [Avançado] Analisar complexidade (Big-O) no melhor e pior caso sob estresse.

## 7. Conceitos-Chave
- LIFO, topo da pilha, custo O(1) para operacoes principais

## 8. Atividade em Sala
1. Revisar a estrutura foco e as operacoes principais.
2. Executar a demonstracao da sprint.
3. Pedir explicacao de custo dominante (tempo e espaco).
4. [Desafio] Simular um gargalo de performance ou falha de edge-case e demonstrar a recuperação do sistema.

## 9. Comando de Execucao
`mvn -Dexec.mainClass=kernelmjr.demo.ManualStructuresDemo exec:java`

## 10. Evidencia Obrigatoria
- Sequencia de desempilhamento em ordem inversa da entrada

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
- **Análise de Complexidade (Big-O):** Como medir o tempo e o espaço consumidos (melhor e pior caso).
- **Edge-Cases (Casos Extremos):** Comportamento da estrutura em limites críticos (ex: vazia, lotada, concorrência).
- **Graceful Degradation:** Técnicas para evitar falhas abruptas (crashes), permitindo que o sistema lide com erros de forma controlada.
- **Stress Tests (Testes de Carga):** Como simular gargalos e testar a estabilidade da estrutura com volume alto de interações.
