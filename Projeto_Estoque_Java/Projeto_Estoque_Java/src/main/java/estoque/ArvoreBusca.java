package estoque;

/** Árvore binária de busca de produtos, indexada pelo código. */
public class ArvoreBusca {
    private static class NoArvore {
        Produto produto;
        NoArvore esquerda, direita;
        NoArvore(Produto produto) { this.produto = produto; }
    }

    private NoArvore raiz;

    /** Insere produto; códigos repetidos são ignorados. */
    public void inserir(Produto produto) { raiz = inserir(raiz, produto); }

    private NoArvore inserir(NoArvore atual, Produto produto) {
        if (atual == null) return new NoArvore(produto);
        int cmp = Integer.compare(produto.getCodigo(), atual.produto.getCodigo());
        if (cmp < 0) atual.esquerda = inserir(atual.esquerda, produto);
        else if (cmp > 0) atual.direita = inserir(atual.direita, produto);
        return atual;
    }

    /** Remove um produto pelo código, preservando as propriedades da ABB. */
    public void remover(int codigo) { raiz = remover(raiz, codigo); }

    private NoArvore remover(NoArvore atual, int codigo) {
        if (atual == null) return null;
        if (codigo < atual.produto.getCodigo()) atual.esquerda = remover(atual.esquerda, codigo);
        else if (codigo > atual.produto.getCodigo()) atual.direita = remover(atual.direita, codigo);
        else {
            if (atual.esquerda == null) return atual.direita;
            if (atual.direita == null) return atual.esquerda;
            NoArvore sucessor = atual.direita;
            while (sucessor.esquerda != null) sucessor = sucessor.esquerda;
            atual.produto = sucessor.produto;
            atual.direita = remover(atual.direita, sucessor.produto.getCodigo());
        }
        return atual;
    }

    public Produto buscar(int codigo) {
        NoArvore atual = raiz;
        while (atual != null) {
            if (codigo == atual.produto.getCodigo()) return atual.produto;
            atual = codigo < atual.produto.getCodigo() ? atual.esquerda : atual.direita;
        }
        return null;
    }

    public void preOrdem() { preOrdem(raiz); System.out.println(); }
    private void preOrdem(NoArvore n) {
        if (n != null) { System.out.print(n.produto.getCodigo() + " "); preOrdem(n.esquerda); preOrdem(n.direita); }
    }

    public void emOrdem() { emOrdem(raiz); System.out.println(); }
    private void emOrdem(NoArvore n) {
        if (n != null) { emOrdem(n.esquerda); System.out.print(n.produto.getCodigo() + " "); emOrdem(n.direita); }
    }

    public void posOrdem() { posOrdem(raiz); System.out.println(); }
    private void posOrdem(NoArvore n) {
        if (n != null) { posOrdem(n.esquerda); posOrdem(n.direita); System.out.print(n.produto.getCodigo() + " "); }
    }
}
