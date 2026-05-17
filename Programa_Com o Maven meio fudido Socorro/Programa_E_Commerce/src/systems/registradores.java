package systems;

import java.util.Scanner;

import application.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import entities.Produto;

public class registradores {
	Connection conn = Conexao.conectar();
	
	Scanner sc = new Scanner(System.in);
	//Produto Vars
	public String new_SKU;
	public String new_Nome;
	public Integer new_quant;
	public Double new_preco;
	
	//Pedido Vars
	public String pdd_cod;
	public Integer val_total;
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
		
		String sql = "INSERT INTO produtos(SKU, quantidade, prod_nome, preco) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, new_SKU);
			ps.setInt(2, new_quant);
			ps.setString(3, new_Nome);
			ps.setDouble(4, new_preco);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		System.out.print(Prod.getNome());
		
		
	}
	public void consultar_estoque() {
		try {

			String sql = "SELECT * FROM produtos";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			System.out.println("\n=== Produtos ===");

			while (rs.next()) {

				System.out.println(
						"ID: " + rs.getInt("id")
								+ " | SKU: " + rs.getString("SKU")
								+ " | Quantidade: " + rs.getInt("quantidade")
								+ " | Produto: " + rs.getString("prod_nome")
								+ " | Preco: " + rs.getDouble("preco")
				);
			}

		} catch (Exception e) {

			e.printStackTrace();
			}
		}
		public void adicionar_pedido() {
			
			System.out.println("Insira o codigo do pedido: ");
			pdd_cod = sc.nextLine();
			System.out.println("Qual produto deseja adicionar no pedido? (Nome): ");
			new_Nome = sc.nextLine();
			System.out.println("Insira a Quantidade do produto: ");
			new_quant = sc.nextInt();
			sc.nextLine();
			
			Produto Prod = new Produto(new_SKU, new_quant, new_Nome, new_preco);
			
			String sql = "INSERT INTO pedidos() VALUES (?, ?, ?, ?)";
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, new_SKU);
				ps.setInt(2, new_quant);
				ps.setString(3, new_Nome);
				ps.setDouble(4, new_preco);
				
				ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			

			System.out.print(Prod.getNome());
			
			
		}
		public void consultar_pedidos() {
			try {

				String sql = "SELECT * FROM pedidos";

				PreparedStatement ps = conn.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();

				System.out.println("\n=== Produtos ===");

				while (rs.next()) {

					System.out.println(
							"ID: " + rs.getInt("id")
									+ " | SKU: " + rs.getString("SKU")
									+ " | Quantidade: " + rs.getInt("quantidade")
									+ " | Produto: " + rs.getString("prod_nome")
									+ " | Preco: " + rs.getDouble("preco")
					);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

	}
	
	
	
}
