package application;

import entities.Conta;
import entities.ContaEmpresa;
import entities.ContaPoupanca;

public class ProgramContas {

	public static void main(String[] args) {
		
		Conta cont = new Conta(1001, "Elias", 0.0);
		ContaEmpresa contEmp = new ContaEmpresa(1002, "Maria", 0.0, 500.0);
		
		//Upcasting
		Conta cont1 = contEmp;
		Conta cont2 = new ContaEmpresa(1003, "Ted", 0.0, 200.0);
		Conta cont3 = new ContaPoupanca(1004, "Ana", 0.0, 0.01);
		
		//Downcasting
		ContaEmpresa cont4 = (ContaEmpresa) cont2;
		cont4.emprestimo(100.0);
		
		//ContaEmpresa cont5 = (ContaEmpresa)cont3;
		if (cont3 instanceof ContaEmpresa) {
			ContaEmpresa cont5 = (ContaEmpresa) cont3;
			cont5.emprestimo(200.0);
			System.out.println("Empréstimo realizado!");
		}
		if (cont3 instanceof ContaPoupanca) {
			ContaPoupanca cont5 = (ContaPoupanca) cont3;
			cont5.atualizaSaldo();
			System.out.println("Saldo Atualizado!");
		}
		
	}

}
