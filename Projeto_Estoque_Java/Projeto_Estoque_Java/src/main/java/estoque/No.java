package estoque;

/** Nó genérico usado nas estruturas lineares encadeadas. */
public class No<T> {
    T valor;
    No<T> proximo;

    public No(T valor) { this.valor = valor; }
}
