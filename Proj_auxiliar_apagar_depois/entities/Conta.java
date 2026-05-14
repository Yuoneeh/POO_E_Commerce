package entities;

public class Conta {
	
	private Integer numero;
	private String titular;
	protected Double saldo;	
		
	//construtor com argumentos
	public Conta(Integer numero, String titular, Double saldo) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}

	//Getters and Setters
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	//métodos
	public void saque(double quantia) {
		saldo -= quantia;		
	}
	
	public void deposito(double quantia) {
		saldo += quantia;		
	} 
	
}
