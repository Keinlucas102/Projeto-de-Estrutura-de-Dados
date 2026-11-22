package estoque;

/** Representa um produto cadastrado no estoque. */
public class Produto implements Comparable<Produto> {
    private final int codigo;
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(int codigo, String nome, int quantidade, double preco) {
        if (codigo <= 0 || nome == null || nome.isBlank() || quantidade < 0 || preco < 0) {
            throw new IllegalArgumentException("Dados do produto inválidos.");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public double getPreco() { return preco; }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva.");
        this.quantidade += quantidade;
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > this.quantidade)
            throw new IllegalArgumentException("Quantidade inválida ou estoque insuficiente.");
        this.quantidade -= quantidade;
    }

    @Override
    public int compareTo(Produto outro) {
        return Integer.compare(this.codigo, outro.codigo);
    }

    @Override
    public String toString() {
        return String.format("Código: %d | Produto: %s | Quantidade: %d | Preço: R$ %.2f",
                codigo, nome, quantidade, preco);
    }
}
