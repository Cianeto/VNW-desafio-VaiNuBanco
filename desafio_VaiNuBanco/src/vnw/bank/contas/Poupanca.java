package vnw.bank.contas;

import java.util.HashSet;

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
		
	}
	
	@Override
	public void depositar(ContaBancaria conta) {
		
	}
	
	@Override
	public void transferir(ContaBancaria contaC, HashSet<ContaBancaria> contas) {
		
	}

	@Override
	public double getLimiteDeCredito() {return 0;}

	@Override
	public void setLimiteDeCredito(double limiteDeCredito) {}
	
}
