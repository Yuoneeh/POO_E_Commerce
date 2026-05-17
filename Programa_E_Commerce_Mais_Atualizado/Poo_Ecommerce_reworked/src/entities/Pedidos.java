package entities;

public class Pedidos {
	
	public String pdd_cod;
	public String nome_comp;
	public Double val_total;
	
	
	public Pedidos(String pdd_cod, String nome_comp, Double val_total) {
		this.pdd_cod = pdd_cod;
		this.nome_comp = nome_comp;
		this.val_total = val_total;
		
		
	}
	
	
	public String getPdd_cod() {
		return pdd_cod;
	}



	public void setPdd_cod(String pdd_cod) {
		this.pdd_cod = pdd_cod;
	}



	public String getNome_comp() {
		return nome_comp;
	}



	public void setNome_comp(String nome_comp) {
		this.nome_comp = nome_comp;
	}



	public Double getVal_total() {
		return val_total;
	}



	public void setVal_total(Double val_total) {
		this.val_total = val_total;
	}




	
}

