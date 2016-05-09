package com.obi1.socketui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ExProtocol {

	private static HashMap<String, DataTO> hmData = new HashMap<String, DataTO>();
	
	public static void initServer(ServerSocket listener) throws IOException {
		
		System.out.println("Escutando...");
		
		String msg = "";
		Socket socket = listener.accept();
		System.out.println("Client conectado, aguardando mensagem...");
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		while (true) {
			try {
				msg += String.valueOf((char)input.read());
				if (checkMessageBody(msg, out)) {
					msg = "";
					socket = listener.accept();
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
				}
			}
			catch (Exception x) {
				System.out.println("Client desconectado...");
				System.out.println("Escutando...");
				
				msg = "";
				socket = listener.accept();
				System.out.println("Client conectado, aguardando mensagem...");
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
			}
		}
		
	}
	
	/*
	 * 00 - operacao (0 - inclusao - 1 consulta - 2 exclusao)
	 * 0000 - chave
	 * 00 - tamanho do nome
	 * 00 - tamanho do valor
	 * xxxxxxx - nome (20 caracteres)
	 * xxxxxxx - valor (20 caracteres)
	 * #@# - caracteres que indicam o termino de uma mensagem
	 */
	private static boolean checkMessageBody(String msg, PrintWriter out) {
		boolean msgCompleta = false;
		
		String operacao;
		String chave;
		int tamNome;
		int tamValor;
		String nome;
		String valor;
		
		if (msg.length() > 10 && msg.indexOf("#@#") > 0){
			operacao = msg.substring(0, 2);
			chave = msg.substring(2, 6);
			tamNome = Integer.parseInt(msg.substring(6, 8));
			tamValor = Integer.parseInt(msg.substring(8, 10));
			
			if ((msg.length() - 3) == (tamNome + tamValor + 10)) {
				msgCompleta = true;
				nome = msg.substring(10, (10 + tamNome));
				valor = msg.substring(10 + tamNome, msg.length() - 3);
				
				if (operacao.equals("00")) {
					System.out.println("Recebido pedido de inclusão. Chave "+ chave);
					hmData.put(chave, new DataTO(nome, valor));
					out.println(operacao + chave +"OK!#@#");
					out.flush();
				}
				else if (operacao.equals("01")) {
					System.out.println("Recebido pedido de consulta. Chave "+ chave);
					out.println(operacao + chave + "2020" + hmData.get(chave) +"#@#");
					out.flush();
				}
				else if (operacao.equals("02")) {
					System.out.println("Recebido pedido de remoção. Chave "+ chave);
					hmData.remove(chave);
					out.println(operacao + chave + "OK!#@#");
					out.flush();
				}
				else {
					System.out.println("Mensagem invalida recebida.");
					out.println(operacao + chave + "Invalid Message!#@#");
					out.flush();
				}
			}
		}
		else if (msg.length() > 200) {
			msgCompleta = true;
			out.println("Invalid Message! - Overflow");
			out.flush();
		}
		
		return msgCompleta;
	}
}
