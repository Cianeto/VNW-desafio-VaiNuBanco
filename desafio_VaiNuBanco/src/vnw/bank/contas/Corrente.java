package vnw.bank.contas;

import java.util.HashSet;

public class Corrente extends Conta implements ContaBancaria {

	private double limiteDeCredito;

	public Corrente(int id, String agencia, String titular, String cpf, String senha, double saldo, double limiteDeCredito) {
		super(id, agencia, titular, cpf, senha, saldo);
		this.limiteDeCredito = limiteDeCredito;
	}
	
	public Corrente() {super();}

	public double getLimiteDeCredito() {
		return limiteDeCredito;
	}
	public void setLimiteDeCredito(double limiteDeCredito) {
		this.limiteDeCredito = limiteDeCredito;
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
	public int getbDay() {return 0;}

	@Override
	public void setbDay(int bDay) {}
	
	
	
}
