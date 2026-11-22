# Sistema de Gerenciamento de Estoque — Java

Projeto acadêmico para demonstrar arrays, estruturas lineares encadeadas, buscas e árvore binária de busca.

## Requisitos
- Java 11 ou superior.
- Não utiliza ArrayList, LinkedList, Stack, Queue, TreeMap, TreeSet ou Arrays.binarySearch como substitutos.

## Compilar e executar
Abra o terminal na pasta `src/main/java` e execute:

```bash
javac estoque/*.java
java estoque.Main
```

## Estruturas
- `Catalogo`: array redimensionável, mantido em ordem crescente pelo código.
- `Pilha<T>`: pilha encadeada genérica para histórico (LIFO).
- `Fila<T>`: fila encadeada genérica para pedidos (FIFO).
- `ArvoreBusca`: árvore binária de busca com busca e três percursos.

## Observações antes da entrega
A remoção de produto atualiza tanto o catálogo quanto a árvore. O relatório contém campos para preencher com medições reais. Não invente tempos: execute os testes no computador usado na apresentação. Faça testes próprios e revise o código antes de entregar.

## Git
Crie um repositório e faça commits durante a evolução. Exemplo:
1. `estrutura inicial do projeto`
2. `implementa pilha e fila encadeadas`
3. `implementa catálogo e buscas`
4. `implementa árvore e percursos`
5. `documentação e testes finais`

## Uso de IA
O documento do professor permite IA como apoio, desde que declarada no termo de autoria e que o aluno compreenda e consiga explicar o código na defesa.
