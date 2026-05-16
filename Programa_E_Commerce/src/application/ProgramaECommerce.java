package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.Scanner;

public class ProgramaECommerce {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Connection conn = Conexao.conectar();

		System.out.println("Conectado ao banco!");

		int choice;

		do {

			System.out.println("\n=== MENU ===");
			System.out.println("1. Realizar Pedido");
			System.out.println("2. Consultar Estoque");
			System.out.println("3. Consultar Pedidos");
			System.out.println("4. Sair");

			System.out.print("Escolha: ");
			choice = Integer.parseInt(sc.nextLine());

			switch (choice) {

				case 1: {

					try {

						System.out.print("Produto: ");
						String produto = sc.nextLine();

						System.out.print("Quantidade: ");
						int quantidade = sc.nextInt();
						sc.nextLine();

						String sql = "INSERT INTO pedidos(produto, quantidade) VALUES (?, ?)";

						PreparedStatement ps = conn.prepareStatement(sql);

						ps.setString(1, produto);
						ps.setInt(2, quantidade);

						ps.executeUpdate();

						System.out.println("Pedido salvo!");

					} catch (Exception e) {

						e.printStackTrace();
					}

					break;
				}

				case 2:
					System.out.println("Consultando estoque");
					break;

				case 3: {

					try {

						String sql = "SELECT * FROM pedidos";

						PreparedStatement ps = conn.prepareStatement(sql);

						ResultSet rs = ps.executeQuery();

						System.out.println("\n=== PEDIDOS ===");

						while (rs.next()) {

							System.out.println(
									"ID: " + rs.getInt("id")
											+ " | Produto: " + rs.getString("produto")
											+ " | Quantidade: " + rs.getInt("quantidade")
							);
						}

					} catch (Exception e) {

						e.printStackTrace();
					}

					break;
				}

				case 4:
					System.out.println("Saindo...");
					break;

				default:
					System.out.println("Opção inválida");
			}

		} while (choice != 4);

		sc.close();

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}