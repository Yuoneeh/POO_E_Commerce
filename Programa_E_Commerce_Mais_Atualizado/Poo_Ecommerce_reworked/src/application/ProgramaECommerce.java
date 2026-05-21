package application;

import java.util.Scanner;
import java.util.Locale;
import entities.UserManager;
import systems.Registradores;
import ui.menu;

public class ProgramaECommerce {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		new UserManager().start();
		menu ui = new menu();
	}
	public static void menuVisualOpen(){
		System.out.println("============================================");
		System.out.println(" BEM VINDO AO E-COMMERCE MANAGEMENT SYSTEM");
		System.out.println("============================================");
		menuPrincipal();
	}
	public static void menuPrincipal(){
		Scanner sc = new Scanner(System.in);


		int choice = -1;

		Registradores Reg =  new Registradores();

		//Toda UI do menu
		menu.menuPrincipalUI();


		choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
			case 1:
				menu.menuProdutosUI();

				choice = -1;
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
					case 1:

						Reg.consultar_estoque();
						menuPrincipal();
						break;
					case 2:
						Reg.adicionar_produto();
						menuPrincipal();
						break;
					case 3:
						menuPrincipal();
						break;
					default:
						System.out.println(" Opção invalida, inserir numero de 1-4 ");
						menuPrincipal();
				}
				break;


			case 2:
				menu.menuPedidosUI();

				choice = -1;
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
					case 1:

						Reg.consultar_pedido();
						menuPrincipal();
						break;
					case 2:
						Reg.adicionar_pedido();
						menuPrincipal();
						break;
					case 3:
						menuPrincipal();
						break;
					default:
						System.out.println(" Opcao invalida, inserir numero de 1-3 ");
						menuPrincipal();
				}
				break;
			case 3:
				menu.menuClientesUI();

				choice = -1;
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
					case 1:
						Reg.consultar_clientes();
						menuPrincipal();
						break;
					case 2:
						Reg.adicionar_cliente();
						menuPrincipal();
						break;
					case 3:
						menuPrincipal();
						break;
					default:
					System.out.println(" Opção invalida, inserir numero de 1-3 ");
					menuPrincipal();
				}
				break;

			case 4:
				menu.menuCategoriasUI();

				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

					case 1:

						Reg.consultar_categorias();

						System.out.println("\nPressione ENTER para voltar...");
						sc.nextLine();

						menuPrincipal();
						break;

					case 2:

						Reg.adicionar_Categoria();

						System.out.println("\nPressione ENTER para voltar...");
						sc.nextLine();

						menuPrincipal();
						break;

					case 3:

						menuPrincipal();
						break;

					case 4:

						System.out.println("Saindo...");
						break;

					default:

						System.out.println("Opcao invalida.");
						menuPrincipal();
						break;

				}

			case 5:
				menu.menuFornecedoresUI();

				choice = -1;
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
					case 1:
						Reg.consultar_fornecedores(); // Alterado para Reg maiúsculo
						System.out.println("\nPressione ENTER para voltar...");
						sc.nextLine();
						menuPrincipal();
						break;
					case 2:
						Reg.adicionar_fornecedor(); // Alterado para Reg maiúsculo
						System.out.println("\nPressione ENTER para voltar...");
						sc.nextLine();
						menuPrincipal();
						break;
					case 3:
						menuPrincipal();
						break;
					case 4:
						System.out.println("Saindo...");
						break;
					default:
						System.out.println("Opção invalida.");
						menuPrincipal();
						break;
				}
				break;

			case 6:
				menu.menuMembrosUI();
				System.out.println("\nPressione ENTER para voltar...");
				sc.nextLine();

				menuPrincipal();
				break;
			case 7:
				menu.menuinstrucoesUI();
				System.out.println("\nPressione ENTER para voltar...");
				sc.nextLine();
				menuPrincipal();
				break;
			case 8:
				System.out.println("Sair");
				break;

			default:
				System.out.println("Opção invalida."); //
				menuPrincipal(); //
				break;
		}
		sc.close();

	}
}
	
	

