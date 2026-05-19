package systems;

import java.util.Scanner;


import entities.Categoria;
import entities.Produto;
import entities.Pedidos;


public class Registradores extends Categoria {

	
	Scanner sc = new Scanner(System.in);
	//Produto Vars
	public Integer new_cat;
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
		int pdd_num = 0;
		System.out.println("Insira a quantidade de produtos que deseja adicionar ao pedido: ");
		pdd_num = sc.nextInt();
		sc.nextLine();
		for (int i = 1; i < pdd_num; i++) {

			System.out.println("Insira a categoria do produto (1-Roupa, 2-Acessorio, 3-Enfeite): ");
			new_cat = sc.nextInt();
			System.out.println("Insira o Nome do produto: ");
			new_Nome = sc.nextLine();
			System.out.println("Insira a Quantidade do produto: ");
			new_quant = sc.nextInt();
			sc.nextLine();
			System.out.println("Insira o Preco do produto: ");
			new_preco = sc.nextDouble();
			sc.nextLine();
			System.out.println("Produto adicionado com sucesso. \n");

			Produto Prod = new Produto(new_cat, new_quant, new_Nome, new_preco);
			//Inserir estes dados em uma planilha SQL

		}




			//Prints para testar a atribuição
			//System.out.print(Prod.getNome());

			//System.out.print(Prod.getPreco());

			//System.out.print(Prod.getQuant());

			//System.out.print(Prod.getSKU());

		}
	public void consultar_estoque() {
		//Inserir Codigo de SQL para ler a tabela de estoque, consultando todos os produtos disponiveis

	}

	public void adicionar_pedido() {
		int pdd_num;
		System.out.println("Quantos produtos deseja adicionar: ");
		pdd_num = sc.nextInt();
		for (int i = 1; i < pdd_num; i++) {
			System.out.println("Insira o codigo do pedido: ");
			pdd_cod = sc.nextLine();
			System.out.println("Qual produto deseja adicionar no pedido? (Nome do Produto): ");
			new_Nome = sc.nextLine();
			System.out.println("Insira a Quantidade do produto: ");
			new_quant = sc.nextInt();
			sc.nextLine();


			Pedidos Ped = new Pedidos(pdd_cod, nome_comp, val_total);

		}
	}

	public void consultar_pedido() {

		// Codigo para ler a tabela pedidos no SQL
		//System.out.print(Ped.getPdd_cod());

	}
	}

	
	


