package entities;

public class Pedidos {

    private String produto;
    private int quantidade;

    public Pedidos(String produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}