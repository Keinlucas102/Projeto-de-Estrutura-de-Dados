package estoque;

import java.util.Scanner;

/** Aplicação de console para gerenciamento de estoque. */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Catalogo catalogo = new Catalogo();
    private static ArvoreBusca arvore = new ArvoreBusca();
    private static final Pilha<String> historico = new Pilha<>();
    private static final Fila<String> pedidos = new Fila<>();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE ESTOQUE ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produto (árvore)");
            System.out.println("4 - Remover produto");
            System.out.println("5 - Registrar movimentação no histórico (pilha)");
            System.out.println("6 - Ver último registro do histórico");
            System.out.println("7 - Criar pedido de reposição (fila)");
            System.out.println("8 - Atender próximo pedido");
            System.out.println("9 - Mostrar percursos da árvore");
            System.out.println("10 - Comparar busca linear e binária");
            System.out.println("0 - Sair");
            opcao = lerInt("Opção: ");
            try {
                switch (opcao) {
                    case 1: cadastrar(); break;
                    case 2: catalogo.listar(); break;
                    case 3: buscar(); break;
                    case 4: remover(); break;
                    case 5: registrar(); break;
                    case 6:
                        System.out.println(historico.estaVazia() ? "Histórico vazio." : historico.peek());
                        break;
                    case 7:
                        pedidos.enfileirar(lerTexto("Descrição do pedido: "));
                        System.out.println("Pedido adicionado. Total na fila: " + pedidos.tamanho());
                        break;
                    case 8:
                        System.out.println(pedidos.estaVazia() ? "Não há pedidos." : "Atendido: " + pedidos.desenfileirar());
                        break;
                    case 9: percursos(); break;
                    case 10: compararBuscas(); break;
                    case 0: System.out.println("Encerrando..."); break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void cadastrar() {
        int codigo = lerInt("Código: ");
        String nome = lerTexto("Nome: ");
        int quantidade = lerInt("Quantidade: ");
        double preco = lerDouble("Preço: ");
        Produto p = new Produto(codigo, nome, quantidade, preco);
        catalogo.adicionar(p);
        arvore.inserir(p);
        historico.push("Cadastro do produto " + codigo + " - " + nome);
        System.out.println("Produto cadastrado.");
    }

    private static void buscar() {
        Produto p = arvore.buscar(lerInt("Código para buscar: "));
        System.out.println(p == null ? "Produto não encontrado." : p);
    }

    private static void remover() {
        int codigo = lerInt("Código para remover: ");
        Produto p = catalogo.remover(codigo);
        if (p == null) System.out.println("Produto não encontrado.");
        else {
            arvore.remover(codigo);
            historico.push("Remoção do produto " + codigo);
            System.out.println("Produto removido do catálogo e da árvore.");
        }
    }
    private static void registrar() {
        String descricao = lerTexto("Descrição da movimentação: ");
        historico.push(descricao);
        System.out.println("Movimentação registrada. Total no histórico: " + historico.tamanho());
    }

    private static void percursos() {
        System.out.print("Pré-ordem (raiz, esquerda, direita): "); arvore.preOrdem();
        System.out.print("Em-ordem (crescente por código): "); arvore.emOrdem();
        System.out.print("Pós-ordem (esquerda, direita, raiz): "); arvore.posOrdem();
    }

    private static void compararBuscas() {
        if (catalogo.tamanho() == 0) { System.out.println("Cadastre produtos primeiro."); return; }
        int codigo = lerInt("Código a pesquisar: ");
        long inicio = System.nanoTime();
        catalogo.buscarLinear(codigo);
        long tempoLinear = System.nanoTime() - inicio;
        inicio = System.nanoTime();
        catalogo.buscarBinaria(codigo);
        long tempoBinaria = System.nanoTime() - inicio;
        System.out.println("Elementos: " + catalogo.tamanho());
        System.out.println("Linear: " + catalogo.contarComparacoesLinear(codigo) +
                " comparações; " + tempoLinear + " ns");
        System.out.println("Binária: " + catalogo.contarComparacoesBinaria(codigo) +
                " comparações; " + tempoBinaria + " ns");
        System.out.println("Repita com volumes maiores; tempos muito pequenos variam entre execuções.");
    }

    private static int lerInt(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine().trim());
    }
    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
    }
    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        String texto = scanner.nextLine().trim();
        if (texto.isEmpty()) throw new IllegalArgumentException("Texto não pode ficar vazio.");
        return texto;
    }
}
