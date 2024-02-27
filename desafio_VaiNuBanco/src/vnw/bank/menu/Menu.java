package vnw.bank.menu;

import vnw.bank.contas.*;
import java.util.HashSet;
import java.util.Scanner;

public class Menu {
	
	public static void startMenu() {
		
		int cont = 1; //A cada conta criada o contador aumenta em 1.
		int opt;
		int id;
		boolean loop1 = true;
		ContaBancaria conta = null;
		String senha;
		
		ContaBancaria corr = new Corrente();
		ContaBancaria poup = new Poupanca();
		
		Scanner input = new Scanner(System.in);
		
		HashSet<ContaBancaria> contas = new HashSet<ContaBancaria>();
		
		System.out.println("Bem-vindo ao Vai Nu Banco!\n");
		
		while(true)	{
			try {
				System.out.println("Opcao [1] - Cadastrar nova conta\nOpcao [2] - Acessar conta\nOpcao [3] - Editar conta"
						+ "\nOpcao [4] - Remover conta\nOpcao [5] - Visualizar conta"
						+ "\nOpcao [6] - Visualizar todas as contas\nOpcao [0] - Encerrar programa");
				
				opt = input.nextInt();
				input.nextLine();
				
				switch(opt) {
				case 1: //Cadastrar Conta
					
					System.out.println("Opcao [1] - Cadastrar Conta Corrente\nOpcao [2] - Cadastrar Conta Poupanca\nOpcao [0] - Voltar");
					opt = input.nextInt();
					input.nextLine();
					
					switch(opt) {
					case 1: //Cadastrar Conta Corrente
						cadastrarConta(cont, "2223-1", contas);
						cont++;
						
						break;
					case 2: //Cadastrar Conta Poupança
						cadastrarConta(cont, "2223-2", contas);
						cont++;
						
						break;
					default:
						throw new Exception("Opcao inputada nao existe");
					}
					break;
					
				case 2: //Acessar Conta
					
					loop1 = true;
					conta = null;
					
					while(loop1) {
						try {
							while(true) {
								System.out.println("Digite o numero id da conta (0 -> Voltar): ");
								id = input.nextInt();
								input.nextLine();
								
								if(id == 0) {
									loop1 = false;
								 	break;
								}
								
								conta = menuBuscarConta(id, contas);
								
								if(conta != null)
									break;
							}
							if(loop1 == false)
								break;
							
							System.out.println("Digite o numero senha da conta: ");
							senha = input.nextLine();
							
							if(conta.getSenha() == senha && conta.getAgencia() == "2223-1") {
								while(true) {
									System.out.println("Opcao [1] - Saque\nOpcao [2] - Deposito\nOpcao [3] Transferencia\nOpcao [0] - Voltar");
									opt = input.nextInt();
									
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
									default:
										throw new Exception("Opcao inputada nao existe");
									}
								}
							} else if(conta.getSenha() == senha && conta.getAgencia() == "2223-2") {
								while(true) {
									System.out.println("Opcao [1] - Saque\nOpcao [2] - Deposito\nOpcao [3] Transferencia\nOpcao [0] - Voltar");
									opt = input.nextInt();
									input.nextLine();
									
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
									default:
										throw new Exception("Opcao inputada nao existe");
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
							System.out.println("(Editar)Digite o numero id da conta (0 -> Voltar): ");
							id = input.nextInt();
							input.nextLine();
							
							if(id == 0)
								break;
							
							if(menuEditarConta(menuBuscarConta(id, contas), contas) != false) 
								break;
							
							throw new Exception("Conta nao encontrada");
						} catch(Exception e) {System.err.println(e.getMessage());}
					}
					
					
					break;
				case 4: //Excluir Conta
					
					while(true) {
						try {
							System.out.println("(Remover)Digite o numero id da conta (0 -> Voltar): ");
							id = input.nextInt();
							input.nextLine();
							
							if(id == 0)
								break;
							
							if(menuRemoverConta(menuBuscarConta(id, contas), contas) != false) 
								break;
							
							throw new Exception("Conta nao encontrada");
						} catch(Exception e) {System.err.println(e.getMessage());}
					}
					
					break;
				case 5: //Buscar Conta pelo Identificador (id||cont) e visualizar
					
					conta = null;
					
					while(true) {
						try {
							System.out.println("(Visualizar)Digite o numero id da conta (0 -> Voltar): ");
							id = input.nextInt();
							input.nextLine();
							
							if(id == 0)
								break;
							
							conta = menuBuscarConta(id, contas);
							
							if(conta != null) {
								System.out.println("\n"+conta.toString()+"\n");
								break;
							}
							throw new Exception("Conta nao encontrada");
						} catch(Exception e) {System.err.println(e.getMessage());}
					}
					
					break;
				case 6: //Visualizar Todas Contas
					System.out.println();
					contas.forEach(System.out::println);
					System.out.println();
					break;
				case 0: //Encerrar Programa
					input.close();
					return;
				default:
					throw new Exception("Opcao inputada nao existe");
				}
			} catch(Exception e) {e.printStackTrace();}
		}
	}

	public static void cadastrarConta(int cont, String agencia, HashSet<ContaBancaria> contas) {
		String titular;
		String cpf;
		String senha;
		double limiteDC;
		int bDay;
		@SuppressWarnings("resource")
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
					input.nextLine();
					
					contas.add(new Corrente(cont, agencia, titular, cpf, senha, 0, limiteDC));
					break;
				} catch(Exception e) {e.printStackTrace();}
			}
		} else if(agencia == "2223-2") {
			while(true) {
				try {
					System.out.println("Digite o aniversario desta conta poupanca: ");
					bDay = input.nextInt();
					input.nextLine();
					
					if(bDay < 1 || bDay > 31) {
						throw new Exception("Data de aniversario invalida (tem que ser entre 1 e 31)");
					} else if(bDay >= 29 && bDay <= 31) {
						bDay = 1;
					}
					
					contas.add(new Poupanca(cont, agencia, titular, cpf, senha, 0, bDay));
					break;
				} catch(Exception e) {System.err.println(e.getMessage());}
			}
		}
		System.out.println("shit");
	}
	
	private static ContaBancaria menuBuscarConta(int id, HashSet<ContaBancaria> contas) {
		
		try {
			for(ContaBancaria account : contas) {
				if(account.getId() == id) {
					if(account.getAgencia() == "2223-1") {
						return account;
						
					} else if(account.getAgencia() == "2223-2") {
						return account;
						
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
	
	private static boolean menuEditarConta(ContaBancaria conta, HashSet<ContaBancaria> contas) {
		if(conta == null)
			return false;
		
		int opt;
		String var1;
		double var2;
		int var3;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				if(conta.getAgencia() == "2223-1") {
					System.out.println("Opcao [1] - Titular\nOpcao [2] - CPF\nOpcao [3] - Senha\nOpcao [4] - Limite de Credito\nOpcao [0] - Voltar");
				} else if(conta.getAgencia() == "2223-2") {
					System.out.println("Opcao [1] - Titular\nOpcao [2] - CPF\nOpcao [3] - Senha\nOpcao [4] - Aniversario\nOpcao [0] - Voltar");
				} else {
					System.err.println("Agencia nao encontrada?");
					return false;
				}
				opt = input.nextInt();
				
				switch(opt) {
				case 1:
					System.out.println("Redigite o nome: ");
					var1 = input.nextLine();
					
					contas.remove(conta);
					conta.setTitular(var1);
					contas.add(conta);
					
					break;
				case 2:
					System.out.println("Redigite o CPF: ");
					var1 = input.nextLine();
					
					if(!verificarCPF(var1)) 
						throw new Exception("CPF precisa ser digitado no formato -> 000.000.000-00");
					
					contas.remove(conta);
					conta.setCpf(var1);
					contas.add(conta);
					
					break;
				case 3:
					System.out.println("Redigite a senha: ");
					var1 = input.nextLine();
					
					if(var1.length() < 4) 
						throw new Exception("Senha possui menos do que 4 caracteres");
					
					contas.remove(conta);
					conta.setSenha(var1);
					contas.add(conta);
					
					break;
				case 4:
					
					if(conta.getAgencia() == "2223-1") {
						System.out.println("Redigite o limite de credito: ");
						var2 = input.nextDouble();
						
						contas.remove(conta);
						conta.setLimiteDeCredito(var2);
						contas.add(conta);
						
					} else if(conta.getAgencia() == "2223-2") {
						System.out.println("Redigite a Data de Aniversario: ");
						var3 = input.nextInt();
						
						contas.remove(conta);
						conta.setbDay(var3);
						contas.add(conta);
						
					} else {
						System.err.println("Agencia nao encontrada??");
						return false;
					}
					break;
				case 0:
					return false;
				default:
					throw new Exception("Opcao inputada nao existe");
				}
				
			} catch(Exception e) {System.err.println(e.getMessage());}
		}
	}
	
	private static boolean menuRemoverConta(ContaBancaria conta, HashSet<ContaBancaria> contas) {
		if(conta == null)
			return false;
		return contas.remove(conta);
	}
	
}
