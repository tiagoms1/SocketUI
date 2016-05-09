package com.obi1.socketui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		String cmd = "";
		
		while (!cmd.equalsIgnoreCase("0")) {
			System.out.println("==========================================================================");
			System.out.println("Digite a opção desejada:");
			System.out.println("(1) - Socket Server");
			System.out.println("(2) - Socket Client");
			System.out.println("(3) - Protocolo Exemplo Server");
			System.out.println("(4) - Protocolo Exemplo Client");
			System.out.println("(5) - Protocolo Exemplo Client - Acessos aleatorios");
			System.out.println("(6) - Socket Server Infinito (sem quebra de linha)");
			System.out.println("(7) - Socket Client Infinito (sem quebra de linha)");
			System.out.println("(0) - Encerrar aplicação");
			
			cmd = br.readLine();
			System.out.println();
			
			if (cmd.equalsIgnoreCase("1")) initServer();
			if (cmd.equalsIgnoreCase("2")) initClient();
			if (cmd.equalsIgnoreCase("3")) initServerProtocol();
			if (cmd.equalsIgnoreCase("4")) initClientProtocol();
			if (cmd.equalsIgnoreCase("5")) initBatch();
			if (cmd.equalsIgnoreCase("6")) initInfiniteServer();
			if (cmd.equalsIgnoreCase("7")) initInfiniteClient();
		}
	}
	
	private static void initServerProtocol() throws IOException {
		ServerSocket listener = createServer();
		ExProtocol.initServer(listener);
	}
	
	private static void initClientProtocol() {
		
		String operacao;
		String chave;
		String nome;
		String valor;
		
		while (true) {
			try {
				Socket socket = createClient();
				
		        //Parametros de entrada
		        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		        System.out.println("Informe a operação (0 - inclusao, 1 - consulta, 2 - exclusão):");
		        operacao = br.readLine();
		        
		        System.out.println("Informe a chave:");
		        chave = br.readLine();
		        
		        if (operacao.equals("0")) {
			        System.out.println("Informe o nome:");
			        nome = br.readLine();
			        
			        System.out.println("Informe o valor:");
			        valor = br.readLine();
		        }
		        else {
		        	nome = "0";
		        	valor = "0";
		        }
		        
		        out.print(charStr(operacao, 2, "0"));
		        out.print(charStr(chave, 4, "0"));
		        out.flush();
		        
		        out.print(charStr(String.valueOf(charStr(nome, 20, " ").length()), 2, "0"));
		        out.print(charStr(String.valueOf(charStr(valor, 20, " ").length()), 2, "0"));
		        out.print(charStr(nome, 20, " ") + charStr(valor, 20, " "));
		        out.flush();
		        
		        out.print("#@#");
		        out.flush();
		        
		        //Lendo o retorno
		        System.out.println("Mensagem de retorno:");
		        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        String answer = input.readLine();
		        System.out.println(answer);
		        
		        socket.close();
			}
			catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}

	private static void initServer() throws IOException {
		ServerSocket listener = createServer();
		
		String msg;
		
		try {
			System.out.println("Server criado com sucesso!");
			System.out.println("Tecle Ctrl+C a qualquer momento para encerrar.");
			
			while (true) {
				System.out.println("Aguardando mensagem do client...");
				Socket socket = listener.accept();
				
				try {
					//Entrada
			        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        msg = input.readLine();
			        System.out.println("Mensagem recebida: "+ msg);

					//Saida
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					System.out.println("Informe a mensagem de retorno:");
					out.println(br.readLine());
					System.out.println("Retorno enviado.");
				} 
				catch (Exception x) {
					System.out.println(x.getMessage());
				}
				finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
	}

	private static void initInfiniteServer() throws IOException {
		ServerSocket listener = createServer();
		
		try {
			System.out.println("Server criado com sucesso!");
			System.out.println("Tecle Ctrl+C a qualquer momento para encerrar.");
			
			while (true) {
				System.out.println();
				System.out.println("Aguardando mensagem do client...");
				System.out.println();
				Socket socket = listener.accept();
				
				try {
					//Entrada
			        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        String character = "";
			        
			        while (!"#".equals(character)) {
			        	character = new String(new byte[]{(byte) input.read()});
			        	System.out.print(character);
			        }
			        
			        System.out.println();
				}
				catch (Exception x) {
					System.out.println(x.getMessage());
				}
				finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
	}
	
	private static ServerSocket createServer() {
		String cmd = "";
		String host;
		int port;
		
		while (true) {
			try {
				System.out.println("Informe o endereço e porta para Bind (formato: [HOST]:[PORT])");
				cmd = br.readLine();
				
				host = cmd.split(":")[0];
				port = Integer.parseInt(cmd.split(":")[1]);
				
				InetSocketAddress address = new InetSocketAddress(host, port);
				ServerSocket listener = new ServerSocket();
				listener.bind(address, 100);
				
				return listener;
			}
			catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}
	
	private static void initClient() {
		while (true) {
			try {
				Socket socket = createClient();
				
		        //Parametros de entrada
		        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		        System.out.println("Informe a mensagem a ser enviada ao server:");
		        out.println(br.readLine());
		        System.out.println("Mensagem enviada! Aguardando retorno...");
		        
		        //Lendo o retorno
		        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        String answer = input.readLine();
		        System.out.println("Mensagem de retorno:");
		        System.out.println(answer);
		        
		        socket.close();
			}
			catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}

	private static void initInfiniteClient() {
		try {
			while (true) {
				Socket socket = createClient();
				
		        //Parametros de entrada
		        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		        System.out.println("Informe a mensagem a ser enviada ao server:");
		        System.out.println("(Informe o caracter # para encerrar o envio)");
		        System.out.println();
		        
		        String character = "";
		        
		        while (!character.equals("#")) {
		        	character = new String(new byte[]{(byte) br.read()});
		        	out.println(character);
		        }
		        
		        System.out.println("Mensagem enviada!");
		        
		        socket.close();
			}
		}
		catch (Exception x) {
			System.out.println(x.getMessage());
		}
	}

	private static void initBatch() {
	
		String operacao;
		String chave;
		String nome;
		String valor;
		
		while (true) {
			try {
				Socket socket = createClient();
				
		        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		        operacao = BatchHelper.generateOperacao();
		        System.out.println("Informe a operação (0 - inclusao, 1 - consulta, 2 - exclusão):"+ operacao);
		        
		        chave = BatchHelper.generateChave();
		        System.out.println("Informe a chave:"+ chave);
		        
		        if (operacao.equals("0")) {
			        nome = BatchHelper.generateNome();
			        System.out.println("Informe o nome:"+ nome);
			        
			        valor = BatchHelper.generateValue();
			        System.out.println("Informe o valor:"+ valor);
		        }
		        else {
		        	nome = "0";
		        	valor = "0";
		        }
		        
		        out.print(charStr(operacao, 2, "0"));
		        out.print(charStr(chave, 4, "0"));
		        out.flush();
		        
		        out.print(charStr(String.valueOf(charStr(nome, 20, " ").length()), 2, "0"));
		        out.print(charStr(String.valueOf(charStr(valor, 20, " ").length()), 2, "0"));
		        out.print(charStr(nome, 20, " ") + charStr(valor, 20, " "));
		        out.flush();
		        
		        out.print("#@#");
		        out.flush();
		        
		        //Lendo o retorno
		        System.out.println("Mensagem de retorno:");
		        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        String answer = input.readLine();
		        System.out.println(answer);
		        
		        socket.close();
		        Thread.sleep(80);
			}
			catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}

	private static String host2Server;
	private static int port2Server;
	private static Socket createClient() {
		String cmd = "";
		boolean newConn = false;
		
		while (true) {
			try {
				newConn = newConn || (host2Server == null);

				if (newConn) {
					System.out.println("Informe o endereço e porta para conectar (formato: [HOST]:[PORT])");
					cmd = br.readLine();
					
					host2Server = cmd.split(":")[0];
					port2Server = Integer.parseInt(cmd.split(":")[1]);
				}
				
				Socket socket = new Socket(host2Server, port2Server);
				
				if (newConn) {
					System.out.println("Conectado com sucesso!");
					System.out.println("Tecle Ctrl+C a qualquer momento para encerrar.");
				}
				
				return socket;
			}
			catch (Exception x) {
				newConn = true;
				System.out.println(x.getMessage());
			}
		}
	}
	
	private static String charStr(String val, int size, String empty) {
		String ret = val;
		
		for (int i = ret.length(); i < size; i++) {
			ret = empty + ret;
		}
		
		return ret;
	}
}
