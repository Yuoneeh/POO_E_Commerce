package entities;

public class Produto {
	private String sku;
	private Integer quant;
	private String nome;
	private String descricao;
	private String status;
	private Double preco;
	private String categoria;

	public Produto(String sku, Integer quant, String nome, String descricao, String status, Double preco, String categoria) {
		this.sku = sku;
		this.quant = quant;
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.preco = preco;
		this.categoria = categoria;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPreco() {
		return preco;
	}

	public String getDescricao() {return descricao;}

	public void setDescricao(String descricao) {this.descricao = descricao;}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {return categoria;}

	public void setCategoria(String categoria) {this.categoria = categoria;}
}