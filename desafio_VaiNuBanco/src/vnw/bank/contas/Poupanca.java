package vnw.bank.contas;

import java.util.HashSet;
import java.util.Scanner;

import vnw.bank.menu.Menu;

public class Poupanca extends Conta implements ContaBancaria {

	private int bDay; //aniversario da conta poupanca

	public Poupanca(int id, String agencia, String titular, String cpf, String senha, double saldo, int bDay) {
		super(id, agencia, titular, cpf, senha, saldo);
		this.bDay = bDay;
	}

	public Poupanca() {super();}

	public int getbDay() {
		return bDay;
	}
	public void setbDay(int bDay) {
		this.bDay = bDay;
	}
	
	@Override
	public void sacar(ContaBancaria conta, HashSet<ContaBancaria> contas) {
		
		System.out.println("\nSaldo: R$" + twoDecimalsRounder(conta.getSaldo()));
		
		double saque;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.println("\nDigite o valor para o saque (0 -> Voltar): ");
				saque = input.nextDouble();
				input.nextLine();
				
				if(saque < 0) 
					throw new Exception("Valor invalido");
				if(saque == 0)
					return;
				if(saque > conta.getSaldo())
					throw new Exception("Saque invalido");
				
				contas.remove(conta);
				conta.setSaldo(conta.getSaldo() - saque + (saque*0.02));
				contas.add(conta);
				System.out.println("\nSaldo: R$" + twoDecimalsRounder(conta.getSaldo()));
				break;
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
	}
	
	@Override
	public void depositar(ContaBancaria conta, HashSet<ContaBancaria> contas) {
		
		System.out.println("\nSaldo: R$" + twoDecimalsRounder(conta.getSaldo()));
		
		double deposito;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.println("\nDigite o valor para o deposito (0 -> Voltar): ");
				deposito = input.nextDouble();
				input.nextLine();
				
				if(deposito < 0) 
					throw new Exception("Valor invalido");
				if(deposito == 0)
					return;
				
				contas.remove(conta);
				conta.setSaldo(conta.getSaldo() + deposito);
				contas.add(conta);
				System.out.println("\nSaldo: R$" + twoDecimalsRounder(conta.getSaldo()));
				break;
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
	}
	
	@Override
	public void transferir(ContaBancaria conta, HashSet<ContaBancaria> contas) {
		
		System.out.println("\nSaldo: R$" + twoDecimalsRounder(conta.getSaldo()));
		
		ContaBancaria contaAux;
		double transf;
		int id;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				
				System.out.println("\n(Transferir)Digite o numero id da conta (0 -> Voltar): ");
				id = input.nextInt();
				input.nextLine();
				
				if(id == 0)
					return;
				
				if((contaAux = Menu.buscarConta(id, contas)) != null) {
					System.out.println("Digite o valor para a transferencia: ");
					transf = input.nextDouble();
					input.nextLine();
					
					if(transf < 0) 
						throw new Exception("Valor invalido");
					if(transf == 0)
						return;
					if(transf > conta.getSaldo())
						throw new Exception("Deposito invalido");
					
					contas.remove(conta);
					conta.setSaldo(conta.getSaldo() - transf);
					contas.add(conta);
					
					contas.remove(contaAux);
					conta.setSaldo(contaAux.getSaldo() + transf);
					contas.add(contaAux);
					System.out.println("\nSaldo: R$" + twoDecimalsRounder(conta.getSaldo()));
					break;
				}
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
	}

	@Override
	public double getLimiteDeCredito() {return 0;}

	@Override
	public void setLimiteDeCredito(double limiteDeCredito) {}
	
	@Override
	public String toString() {
		return "Conta Poupanca " + super.toString() + " / aniversario = " + bDay + "}";
	}
	
}
