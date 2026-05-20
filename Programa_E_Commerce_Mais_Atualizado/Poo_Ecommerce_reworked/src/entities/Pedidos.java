package entities;

public class Pedidos {
	private String pdd_cod; // No banco: pdd_id
	private String pdd_nf; // No banco: pdd_nf

	public Pedidos(String pdd_cod, String pdd_nf) {
		this.pdd_cod = pdd_cod;
		this.pdd_nf = pdd_nf;
	}

	// Getters e Setters...

	public String getPdd_cod() {
		return pdd_cod;
	}

	public void setPdd_cod(String pdd_cod) {
		this.pdd_cod = pdd_cod;
	}

	public String getPdd_nf() {
		return pdd_nf;
	}

	public void setPdd_nf(String pdd_nf) {
		this.pdd_nf = pdd_nf;
	}
}