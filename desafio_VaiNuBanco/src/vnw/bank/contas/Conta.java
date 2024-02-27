package vnw.bank.contas;

public abstract class Conta {
	
	private int id; // gerado automaticamente, de 1 pra cima
	private String agencia; // 2223-1 = corrente 2223-2 = poupança
	private String titular;
	private String cpf; // xxx.xxx.xxx-xx
	private String senha;
	private double saldo;
	
	
	public Conta(int id, String agencia, String titular, String cpf, String senha, double saldo) {
		super();
		this.id = id;
		this.agencia = agencia;
		this.titular = titular;
		this.cpf = cpf;
		this.senha = senha;
		this.saldo = saldo;
	}
	
	public Conta() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
}
