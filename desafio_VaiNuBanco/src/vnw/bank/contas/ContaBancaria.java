package vnw.bank.contas;

import java.util.HashSet;

public interface ContaBancaria {
	
	void sacar(ContaBancaria conta, HashSet<ContaBancaria> contas);
	void depositar(ContaBancaria conta, HashSet<ContaBancaria> contas);
	void transferir(ContaBancaria contaC, HashSet<ContaBancaria> contas);
	
	int getId();
	String getAgencia();
	String getTitular();
	String getCpf();
	String getSenha();
	double getSaldo();
	double getLimiteDeCredito();
	int getbDay();
	void setTitular(String titular);
	void setCpf(String cpf);
	void setSenha(String senha);
	void setLimiteDeCredito(double limiteDeCredito);
	void setbDay(int bDay);
	void setSaldo(double saldo);
	String toString();
}
