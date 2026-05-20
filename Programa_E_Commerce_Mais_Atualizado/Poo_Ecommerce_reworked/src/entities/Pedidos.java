package entities;

import java.time.LocalDate;

public class Pedidos {
	private String pdd_cod;
	private LocalDate pdd_data;
	private Double pdd_valor;
	private String pdd_status;
	private String cli_cpf;   // No banco: pdd_nf

	public Pedidos(String pdd_cod, LocalDate pdd_data, Double pdd_valor, String pdd_status, String cli_cpf) {
		this.pdd_cod = pdd_cod;
		this.pdd_data = pdd_data;
		this.pdd_valor = pdd_valor;
		this.pdd_status = pdd_status;
		this.cli_cpf = cli_cpf;
	}

	// Getters e Setters...
	public String getPdd_cod()      { return pdd_cod; }
	public LocalDate getPdd_data()  { return pdd_data; }
	public Double getPdd_valor()    { return pdd_valor; }
	public String getPdd_status()   { return pdd_status; }
	public String getCli_cpf()      { return cli_cpf; }
}