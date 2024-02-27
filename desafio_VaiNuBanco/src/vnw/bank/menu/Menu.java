package vnw.bank.menu;

import vnw.bank.contas.*;
import java.util.HashSet;
import java.util.Scanner;

public class Menu {
	
	public static void startMenu() {
		
		int cont = 1; //A cada conta criada o contador aumenta em 1.
		int opt;
		int id;
		
		ContaBancaria corr = new Corrente();
		ContaBancaria poup = new Corrente();
		
		Scanner input = new Scanner(System.in);
		HashSet<ContaBancaria> contas = new HashSet<ContaBancaria>();
		
		System.out.println("Bem-vindo ao Vai Nu Banco!\n");
		
		while(true)	{
			try {
				System.out.println("Opcao [1] - Cadastrar nova conta\nOpcao [2] - Acessar conta\nOpcao [0] - Encerrar programa");
				opt = input.nextInt();
				
				if(opt < 0 || opt > 6) 
					throw new Exception("Opcao inputada nao existe");
				
				switch(opt) {
				case 1: //Cadastrar Conta
					
					System.out.println("Opcao [1] - Cadastrar Conta Corrente\nOpcao [2] - Cadastrar Conta Poupanca\nOpcao [0] - Voltar");
					opt = input.nextInt();
					
					if(opt < 0 || opt > 2)
						throw new Exception("Opcao inputada nao existe"); 
					
					
					switch(opt) {
					case 1: //Cadastrar Conta Corrente
						cadastrarConta(cont, "2223-1", contas);
						cont++;
						
						break;
					case 2: //Cadastrar Conta Poupança
						cadastrarConta(cont, "2223-2", contas);
						cont++;
						
						break;
					}
					break;
					
				case 2: //Acessar Conta
					
					boolean loop1 = true;
					boolean idLoop = true;
					ContaBancaria conta = null;
					String senha;
					
					while(loop1) {
						try {
							while(idLoop) {
								System.out.println("Digite o numero id da conta (0 -> Voltar): ");
								id = input.nextInt();
								
								if(id == 0) {
									loop1 = false;
								 	break;
								}
								
								idLoop = false;
								conta = menuBuscarConta(id, contas);
							}
							if(loop1 == false)
								break;
							
							System.out.println("Digite o numero senha da conta: ");
							senha = input.nextLine();
							
							if(conta.getSenha() == senha && conta.getAgencia() == "2223-1") {
								while(true) {
									System.out.println("Opcao [1] - Saque\nOpcao [2] - Deposito\nOpcao [3] Transferencia\nOpcao [0] - Voltar");
									opt = input.nextInt();
									
									if(opt < 0 || opt > 3) 
										throw new Exception("Opcao inputada nao existe");
									
									switch(opt) {
									case 1: //Sacar
										corr.sacar(conta, contas);
										
										break;
									case 2: //Depositar
										corr.depositar(conta);
										
										break;
									case 3: //Transferir
										corr.transferir(conta, contas);
										
										break;
									case 0:
										//return; quando ficar dentro de um metodo fara sentido
									}
								}
							} else if(conta.getSenha() == senha && conta.getAgencia() == "2223-2") {
								while(true) {
									System.out.println("Opcao [1] - Saque\nOpcao [2] - Deposito\nOpcao [3] Transferencia\nOpcao [0] - Voltar");
									opt = input.nextInt();
									
									if(opt < 0 || opt > 3) 
										throw new Exception("Opcao inputada nao existe");
									
									switch(opt) {
									case 1: //Sacar
										poup.sacar(conta, contas);
										
										break;
									case 2: //Depositar
										poup.depositar(conta);
										
										break;
									case 3: //Transferir
										poup.transferir(conta, contas);
										
										break;
									case 0:
										//return; quando ficar dentro de um metodo fara sentido
									}
								}
							}
								
							throw new Exception("");
							
						} catch(Exception e) {System.err.println(e.getMessage());}
					} //loop1 end
					
					break;
					
				case 3: //Editar Conta
					
					while(true) {
						try {
							System.out.println("Digite o numero id da conta (0 -> Voltar): ");
							id = input.nextInt();
							
							if(id == 0)
								break;
							
							if(menuEditarConta(menuBuscarConta(id, contas)) != false) 
								break;
							
							throw new Exception("Conta nao encontrada");
						} catch(Exception e) {System.err.println(e.getMessage());}
					}
					
					
					break;
				case 4: //Excluir Conta
					
					break;
				case 5: //Buscar Conta pelo Identificador (id||cont) e visualizar
					
					break;
				case 6: //Visualizar Todas Contas
					
					break;
				case 0: //Encerrar Programa
					input.close();
					return;
				}
				
					
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
	}
	
	public static void cadastrarConta(int cont, String agencia, HashSet<ContaBancaria> contas) {
		String titular;
		String cpf;
		String senha;
		double limiteDC;
		int bDay;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Digite o nome do titular: ");
		titular = input.nextLine();
		
		while(true) { //Validar cpf
			try {
				System.out.println("Digite o CPF (no formato 000.000.000-00): ");
				cpf = input.nextLine();
				
				if(!verificarCPF(cpf)) 
					throw new Exception("CPF precisa ser digitado no formato -> 000.000.000-00");
				
				break;
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
		while(true) { //Validar senha
			try {
				System.out.println("Crie uma senha (no minimo 4 caracteres): ");
				senha = input.nextLine();
				
				if(senha.length() < 4) 
					throw new Exception("Senha possui menos do que 4 caracteres");
				
				break;
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
		
		if(agencia == "2223-1") {
			while(true) {
				try {
					System.out.println("Digite o limite de credito desta conta corrente: ");
					limiteDC = input.nextDouble();
					
					contas.add(new Corrente(cont, agencia, titular, cpf, senha, 0, limiteDC));
				
				} catch(Exception e) {e.printStackTrace();}
			}
		} else if(agencia == "2223-2") {
			while(true) {
				try {
					System.out.println("Digite o aniversario desta conta poupanca: ");
					bDay = input.nextInt();
					
					if(bDay < 1 || bDay > 31) {
						throw new Exception("Data de aniversario invalida (tem que ser entre 1 e 31)");
					} else if(bDay >= 29 && bDay <= 31) {
						bDay = 1;
					}
					
					contas.add(new Corrente(cont, agencia, titular, cpf, senha, 0, bDay));
				
				} catch(Exception e) {System.err.println(e.getMessage());}
			}
		}
		
		cont++;
		input.close();
	}
	
	private static ContaBancaria menuBuscarConta(int id, HashSet<ContaBancaria> contas) {
		
		ContaBancaria conta = null;
		
		try {
			for(ContaBancaria account : contas) {
				if(account.getId() == id) {
					if(account.getAgencia() == "2223-1") {
						conta = new Corrente(account.getId(), account.getAgencia(), account.getTitular(),
								account.getCpf(), account.getSenha(), account.getSaldo(), account.getLimiteDeCredito());
						
						return conta;
					} else if(account.getAgencia() == "2223-2") {
						conta = new Poupanca(account.getId(), account.getAgencia(), account.getTitular(),
								account.getCpf(), account.getSenha(), account.getSaldo(), account.getbDay());
						
						return conta;
					} else {
						throw new Exception("Agencia da conta nao encontrada");
					}
				}
			}
		throw new Exception("Id nao encontrado");
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	private static boolean verificarCPF(String cpf) {
		
		try {
			char n1 = cpf.charAt(0); char n2 = cpf.charAt(1); char n3 = cpf.charAt(2);
			
			char n4 = cpf.charAt(4); char n5 = cpf.charAt(5); char n6 = cpf.charAt(6);
			
			char n7 = cpf.charAt(8); char n8 = cpf.charAt(9); char n9 = cpf.charAt(10);
			
			char n10 = cpf.charAt(12); char n11 = cpf.charAt(13);
			
			char p1 = cpf.charAt(3); char p2 = cpf.charAt(7);
			
			char h1 = cpf.charAt(11);
			
			if( !Character.isDigit(n1) || !Character.isDigit(n2) || !Character.isDigit(n3) || !Character.isDigit(n4) || 
				!Character.isDigit(n5) || !Character.isDigit(n6) || !Character.isDigit(n7) || !Character.isDigit(n8) || 
				!Character.isDigit(n9) || !Character.isDigit(n10) || !Character.isDigit(n11) || p1 != '.' || p2 != '.' || h1 != '-') {
				
				throw new Exception(); //Não esta digitado corretamente.
			}
			
		} catch(Exception e) {return false;}
		
		return true;
		
	}
	private static boolean menuEditarConta(ContaBancaria conta) {
		if(conta == null)
			return false;
		
		int opt;
		String var1;
		double var2;
		int var3;
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				if(conta.getAgencia() == "2223-1") {
					System.out.println("Opcao [1] Titular\nOpcao [2] - CPF\nOpcao [3] - Senha\nOpcao [4] - Limite de Credito\nOpcao [0] - Voltar");
				} else if(conta.getAgencia() == "2223-2") {
					System.out.println("Opcao [1] Titular\nOpcao [2] - CPF\nOpcao [3] - Senha\nOpcao [4] - Aniversario\nOpcao [0] - Voltar");
				} else {
					System.err.println("Agencia nao encontrada?");
					return false;
				}
				opt = input.nextInt();
				
				if(opt < 0 || opt > 6) 
					throw new Exception("Opcao inputada nao existe");
				
				switch(opt) {
				case 1:
					System.out.println("Redigite o nome: ");
					var1 = input.nextLine();
					
					conta.setTitular(var1);
					
					break;
				case 2:
					System.out.println("Redigite o CPF: ");
					var1 = input.nextLine();
					
					if(!verificarCPF(var1)) 
						throw new Exception("CPF precisa ser digitado no formato -> 000.000.000-00");
					
					conta.setCpf(var1);
					
					break;
				case 3:
					System.out.println("Redigite a senha: ");
					var1 = input.nextLine();
					
					if(var1.length() < 4) 
						throw new Exception("Senha possui menos do que 4 caracteres");
					
					conta.setSenha(var1);
					
					break;
				case 4:
					
					if(conta.getAgencia() == "2223-1") {
						System.out.println("Redigite o limite de credito: ");
						var2 = input.nextDouble();
						
						conta.setLimiteDeCredito(var2);
						
					} else if(conta.getAgencia() == "2223-2") {
						System.out.println("Redigite a Data de Aniversario: ");
						var3 = input.nextInt();
						
						conta.setbDay(var3);
						
					} else {
						System.err.println("Agencia nao encontrada??");
						return false;
					}
					break;
				case 0:
					input.close();
					return false;
				}
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
		
		
	}
}
