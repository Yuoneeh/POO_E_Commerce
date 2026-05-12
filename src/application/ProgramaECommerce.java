package application;

import java.util.Scanner;
import java.util.Locale;


public class ProgramaECommerce {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		
		System.out.println("Bem vindo ao E-Commerce System Manager ");
		System.out.println(" \n=== Menu ===");
		System.out.println("1. Realizar Pedidos");
		System.out.println("2. Consultar Estoque");
		System.out.println("3. Consultar Pedidos");
		System.out.println("4. Sair");
		
		System.out.print("INSIRA A OPCAO DESEJADA: ");
		
		choice = sc.nextInt();
		sc.nextLine();
		
        switch (choice) {
            case 1:
                System.out.println("Realizando Pedidos");
                    break;

            case 2:
                System.out.println("Consultando Estoque");
                    break;
            case 3:
                System.out.println("Consultar Pedidos");
                break;
            case 4:
                System.out.println("Sair");
                break;
		
        }
		
		sc.close();
		
		
		
	}
	
}
