package application;

import java.util.Scanner;
import java.util.Locale;
import entities.UserManager;
import systems.Registradores;

public class ProgramaECommerce {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		new UserManager().start();
		if (UserManager.acessoAutorizado){
			menuPrincipal();
		}
		
	}
	public static void menuPrincipal(){
		Scanner sc = new Scanner(System.in);


		int choice = -1;

		Registradores Reg =  new Registradores();

		System.out.println("Bem vindo ao E-Commerce System Manager ");
		System.out.println(" \n=== Menu ===");
		System.out.println("1. Produtos");
		System.out.println("2. Pedidos");
		System.out.println("3. Clientes");
		System.out.println("4. Categorias");
		System.out.println("5. Sair");

		System.out.print("INSIRA A OPCAO DESEJADA: ");

		choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
			case 1:
				System.out.println(" === Secao Produtos === ");
				System.out.println("Qual o proximo passo: ");
				System.out.println("1. Consultar Produtos");
				System.out.println("2. Adicionar Produto ao Estoque");
				System.out.println("3. Voltar para menu principal");
				System.out.println("4. Sair");
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
				}
				break;


			case 2:
				System.out.println(" === Secao Pedidos === ");
				System.out.println("1. Consultar Pedidos");
				System.out.println("2. Criar Pedido");
				System.out.println("3. Voltar para menu principal");
				System.out.println("4. Sair");
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
				}
				break;
			case 3:
				System.out.println(" === Secao Clientes === ");
				System.out.println("Qual o proximo passo: ");
				System.out.println("1. Consultar Clientes");
				System.out.println("2. Cadastrar Novo Cliente");
				System.out.println("3. Voltar para menu principal");
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
				}
				break;

			case 4:
				System.out.println("====================================");
				System.out.println("         SECAO CATEGORIAS");
				System.out.println("====================================");

				System.out.println("1. Consultar Categorias");
				System.out.println("2. Criar Categoria");
				System.out.println("3. Voltar para menu principal");
				System.out.println("4. Sair");

				System.out.print("\nEscolha: ");

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
				System.out.println("Sair");
				break;

		}
		sc.close();

	}
}
	
	

