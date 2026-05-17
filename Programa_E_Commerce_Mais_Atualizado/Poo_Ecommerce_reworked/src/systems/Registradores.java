package systems;

import java.util.Scanner;


import entities.Produto;
import entities.Pedidos;

public class Registradores{

	
	Scanner sc = new Scanner(System.in);
	//Produto Vars
	public String new_SKU;
	public String new_Nome;
	public Integer new_quant;
	public Double new_preco;
	
	//Pedido Vars
	public String pdd_cod;
	public Double val_total;
	public String nome_comp;
	//Aqui tem que dar um jeito de incluir os items no pedido
	
	
	public void adicionar_produto() {
		
		System.out.println("Insira o SKU do produto: ");
		new_SKU = sc.nextLine();
		System.out.println("Insira o Nome do produto: ");
		new_Nome = sc.nextLine();
		System.out.println("Insira a Quantidade do produto: ");
		new_quant = sc.nextInt();
		sc.nextLine();
		System.out.println("Insira o Preco do produto: ");
		new_preco = sc.nextDouble();
		sc.nextLine();
		
		Produto Prod = new Produto(new_SKU, new_quant, new_Nome, new_preco);
		


		//Prints para testar a atribuição
		//System.out.print(Prod.getNome());
		
		//System.out.print(Prod.getPreco());
		
		//System.out.print(Prod.getQuant());
		
		//System.out.print(Prod.getSKU());
		
	}
	
	public void consultar_estoque() {
		
		
	}
	
	public void adicionar_pedido() {
			
			System.out.println("Insira o codigo do pedido: ");
			pdd_cod = sc.nextLine();
			System.out.println("Qual produto deseja adicionar no pedido? (Nome do Produto): ");
			new_Nome = sc.nextLine();
			System.out.println("Insira a Quantidade do produto: ");
			new_quant = sc.nextInt();
			sc.nextLine();
			

			Pedidos Ped = new Pedidos(pdd_cod, nome_comp, val_total);
			

			System.out.print(Ped.getPdd_cod());
			
			
		}
	
	public void consultar_pedido() {
		
	}
	
	
	
}
