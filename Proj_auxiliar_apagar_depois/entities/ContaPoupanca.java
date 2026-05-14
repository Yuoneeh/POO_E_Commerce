package entities;

public class ContaPoupanca extends Conta {
	
	//campo taxa de juros
	private Double taxaJuros;

	//construtor com os argumentos
	public ContaPoupanca(Integer numero, String titular, Double saldo, Double taxaJuros) {
		super(numero, titular, saldo);
		this.taxaJuros = taxaJuros;
	}

	//implementação dos getters and setters
	public Double getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(Double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	
	//método de atualização do saldo
	public void atualizaSaldo() {
		saldo += saldo * taxaJuros;
	}

}
