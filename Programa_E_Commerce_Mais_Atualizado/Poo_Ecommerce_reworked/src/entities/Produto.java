package entities;

public class Produto extends Categoria{


	public String SKU;
	public String Nome;
	public Integer quant;
	public Double preco;
		
	
	public Produto(String SKU, Integer quant, String Nome, Double preco) {
		super();
		this.SKU = SKU;
		this.Nome = Nome;
		this.quant = quant;
		this.preco = preco;
	}
	
	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}

