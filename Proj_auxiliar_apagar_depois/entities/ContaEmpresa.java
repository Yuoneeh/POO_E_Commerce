package entities;

public class ContaEmpresa extends Conta {
	
	private Double limiteEmprestimo;
	
	//construtor com 4 argumentos
	public ContaEmpresa(Integer numero, String titular, Double saldo, Double limiteEmprestimo) {
		super(numero, titular, saldo);
		this.limiteEmprestimo = limiteEmprestimo;
	}

	//Getters and Setters
	public Double getLimiteEmprestimo() {
		return limiteEmprestimo;
	}

	public void setLimiteEmprestimo(Double limiteEmprestimo) {
		this.limiteEmprestimo = limiteEmprestimo;
	}
	
	//implementação a operação (método) de empréstimo
	public void emprestimo(double quantia) {
		if(quantia <= limiteEmprestimo) {
			saldo += quantia - 10.0;
		}
	}
	
}
