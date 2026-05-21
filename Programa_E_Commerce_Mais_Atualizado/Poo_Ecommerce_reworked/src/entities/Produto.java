package entities;

public class Produto {
	private String sku; // No banco: pdt_sku (É a Chave Primária!)
	private Integer quant; // No banco: pdt_quant
	private String nome; // No banco: pdt_nome
	private String descricao; //pdt_desc
	private String status; // No banco: pdt_status (Adicionamos este)
	private Double preco; // No banco: pdt_preco
	private String categoria;

//Aqui instanciamos um objeto com o nome de produto, representa tudo vendido na loja.
	public Produto(String sku, Integer quant, String nome, String descricao, String status, Double preco, String categoria) {
		this.sku = sku;
		this.quant = quant;
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.preco = preco;
		this.categoria = categoria;
	}

	// Mantenha os seus métodos Getters e Setters aqui...
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