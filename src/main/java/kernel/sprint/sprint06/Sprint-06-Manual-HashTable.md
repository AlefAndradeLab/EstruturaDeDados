# Sprint 06 - Manual HashTable

## 1. Nome da Sprint
Sprint 06 - Manual HashTable

## 2. Fase Didatica
Fase 1 - Fundamentos Manuais

## 3. Tema da Aula
Mapeamento por chave

## 4. Classe(s) Foco
Sprint06ManualHashTable

## 5. Problema
A busca sequencial por uma chave em uma lista inteira aumenta a latência de forma linear (O(N)). Precisamos de uma estrutura capaz de mapear chaves diretamente para endereços de memória, visando uma busca quase instantânea.
## 6. Objetivo da Sprint
- Implementar hash com insercao, busca e colisao
- [Avançado] Tratar edge-cases de Hash: Colisões extremas (muitas chaves no mesmo bucket) e redimensionamento da tabela (Rehashing) quando o Load Factor for ultrapassado.
- [Avançado] Analisar complexidade (Big-O) provando o O(1) médio para buscas, e demonstrar como colisões massivas (ataque de colisão) podem degradar o tempo para O(N).

## 7. Conceitos-Chave
- Hash, bucket, colisao, custo medio O(1)

## 8. Atividade em Sala
1. Revisar a estrutura foco e as operacoes principais.
2. Executar a demonstracao da sprint.
3. Pedir explicacao de custo dominante (tempo e espaco).
4. [Desafio] Simular um gargalo de performance ou falha de edge-case e demonstrar a recuperação do sistema.

## 9. Comando de Execucao
`mvn -Dexec.mainClass=kernelmjr.demo.ManualStructuresDemo exec:java`

## 10. Evidencia Obrigatoria
- Caso de colisao resolvido com busca correta

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
- **Funções de Hash e Load Factor:** Como o fator de carga impacta o desempenho e quando disparar o Rehashing.
- **Tratamento de Colisões:** Encadeamento separado (LinkedLists nos buckets) ou Endereçamento Aberto (Linear Probing).
