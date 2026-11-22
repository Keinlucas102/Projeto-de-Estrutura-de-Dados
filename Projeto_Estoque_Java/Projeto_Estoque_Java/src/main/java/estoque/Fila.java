package estoque;

/**
 * Fila genérica encadeada (FIFO).
 * Inserção no fim e remoção no início ocorrem em O(1).
 */
public class Fila<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    /** Adiciona um elemento ao fim da fila. */
    public void enfileirar(T valor) {
        No<T> novo = new No<>(valor);
        if (estaVazia()) inicio = fim = novo;
        else { fim.proximo = novo; fim = novo; }
        tamanho++;
    }

    /** Remove e retorna o primeiro elemento. */
    public T desenfileirar() {
        if (estaVazia()) throw new IllegalStateException("A fila está vazia.");
        T valor = inicio.valor;
        inicio = inicio.proximo;
        tamanho--;
        if (inicio == null) fim = null;
        return valor;
    }

    /** Busca um elemento usando equals. */
    public boolean buscar(T valor) {
        for (No<T> atual = inicio; atual != null; atual = atual.proximo)
            if (atual.valor.equals(valor)) return true;
        return false;
    }

    public boolean estaVazia() { return tamanho == 0; }
    public int tamanho() { return tamanho; }
}
