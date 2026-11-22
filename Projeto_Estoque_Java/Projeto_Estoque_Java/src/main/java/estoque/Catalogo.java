package estoque;

/**
 * Catálogo baseado em array redimensionável, sempre ordenado pelo código.
 * A ordenação permite executar busca binária.
 */
public class Catalogo {
    private Produto[] dados = new Produto[10];
    private int tamanho;

    /** Insere mantendo os produtos em ordem crescente de código. */
    public void adicionar(Produto produto) {
        if (buscarBinaria(produto.getCodigo()) != null)
            throw new IllegalArgumentException("Já existe produto com esse código.");
        garantirCapacidade();
        int pos = 0;
        while (pos < tamanho && dados[pos].getCodigo() < produto.getCodigo()) pos++;
        for (int i = tamanho; i > pos; i--) dados[i] = dados[i - 1];
        dados[pos] = produto;
        tamanho++;
    }

    private void garantirCapacidade() {
        if (tamanho == dados.length) {
            Produto[] novo = new Produto[dados.length * 2];
            for (int i = 0; i < tamanho; i++) novo[i] = dados[i];
            dados = novo;
        }
    }

    public Produto buscarLinear(int codigo) {
        for (int i = 0; i < tamanho; i++)
            if (dados[i].getCodigo() == codigo) return dados[i];
        return null;
    }

    public Produto buscarBinaria(int codigo) {
        int esquerda = 0, direita = tamanho - 1;
        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            int atual = dados[meio].getCodigo();
            if (atual == codigo) return dados[meio];
            if (atual < codigo) esquerda = meio + 1;
            else direita = meio - 1;
        }
        return null;
    }

    /** Versão de busca binária que também contabiliza comparações. */
    public int contarComparacoesBinaria(int codigo) {
        int comparacoes = 0, esquerda = 0, direita = tamanho - 1;
        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            comparacoes++;
            if (dados[meio].getCodigo() == codigo) return comparacoes;
            if (dados[meio].getCodigo() < codigo) esquerda = meio + 1;
            else direita = meio - 1;
        }
        return comparacoes;
    }

    /** Versão de busca linear que também contabiliza comparações. */
    public int contarComparacoesLinear(int codigo) {
        int comparacoes = 0;
        for (int i = 0; i < tamanho; i++) {
            comparacoes++;
            if (dados[i].getCodigo() == codigo) break;
        }
        return comparacoes;
    }

    public Produto remover(int codigo) {
        int pos = -1;
        for (int i = 0; i < tamanho; i++)
            if (dados[i].getCodigo() == codigo) { pos = i; break; }
        if (pos == -1) return null;
        Produto removido = dados[pos];
        for (int i = pos; i < tamanho - 1; i++) dados[i] = dados[i + 1];
        dados[--tamanho] = null;
        return removido;
    }

    public void listar() {
        if (tamanho == 0) { System.out.println("Estoque vazio."); return; }
        for (int i = 0; i < tamanho; i++) System.out.println(dados[i]);
    }

    public int tamanho() { return tamanho; }
    public Produto obter(int indice) {
        if (indice < 0 || indice >= tamanho) throw new IndexOutOfBoundsException("Índice inválido.");
        return dados[indice];
    }
}
