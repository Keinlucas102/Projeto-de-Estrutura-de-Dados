package estoque;

/**
 * Pilha genérica encadeada (LIFO).
 * Inserções e remoções no topo ocorrem em O(1).
 */
public class Pilha<T> {
    private No<T> topo;
    private int tamanho;

    /** Insere um elemento no topo. */
    public void push(T valor) {
        No<T> novo = new No<>(valor);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
    }

    /** Remove e retorna o elemento do topo. */
    public T pop() {
        if (estaVazia()) throw new IllegalStateException("A pilha está vazia.");
        T valor = topo.valor;
        topo = topo.proximo;
        tamanho--;
        return valor;
    }

    /** Consulta o topo sem remover. */
    public T peek() {
        if (estaVazia()) throw new IllegalStateException("A pilha está vazia.");
        return topo.valor;
    }

    /** Busca um elemento usando equals. */
    public boolean buscar(T valor) {
        for (No<T> atual = topo; atual != null; atual = atual.proximo)
            if (atual.valor.equals(valor)) return true;
        return false;
    }

    public boolean estaVazia() { return tamanho == 0; }
    public int tamanho() { return tamanho; }
}
