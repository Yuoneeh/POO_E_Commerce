package application;

import java.util.Scanner;
import java.sql.Connection;
import java.util.Locale;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import systems.registradores;
import entities.Produto;

public class ProgramaECommerce {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Connection conn = Conexao.conectar();
		
		int choice = -1;
		
		registradores Reg =  new registradores();
		
		System.out.println("Bem vindo ao E-Commerce System Manager ");
		System.out.println(" \n=== Menu ===");
		System.out.println("1. Produtos");
		System.out.println("2. Pedidos");
		System.out.println("3. Placeholder");
		System.out.println("4. Sair");
		
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
        				 break;
        			case 2: 
        				 Reg.adicionar_produto();
        				 main(args);
        				 break;
        			case 3: 
        				 main(args);
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
        				 
        				 Reg.consultar_estoque();
        				 break;
        			case 2: 
        				 Reg.adicionar_produto();
        				 main(args);
        				 break;
        			case 3: 
        				 main(args);
        				 break;
        		}
        		break;
            case 3:
                System.out.println("PlaceHolder");
                break;
            case 4:
                System.out.println("Sair");
                break;
		
        }
		
		sc.close();
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
