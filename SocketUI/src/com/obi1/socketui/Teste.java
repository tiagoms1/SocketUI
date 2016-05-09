package com.obi1.socketui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Teste {

	
	public static void main(String[] args) {
		
	}
	
	public static void main2(String[] args) {
		
		try {
			Socket socket = new Socket("localhost", 9088);
			
	        //Parametros de entrada
	        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        
	        String mensagem = "%@$&!#2700407000001000000000010150  DATJ7DATIM3DATSQ8241015030003070000169.254.80.80                                000100000000000000000001000006220140101201401012014010100000000120000065465006607920190203000000000000007000000000000000070000000000000000700000000000000007000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000090000007603S01XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	        
	        for (int i = 0; i < mensagem.length(); i++) {
		        out.print(mensagem.charAt(i));
		        out.flush();
	        }
	        
	        
	        //Lendo o retorno
	        System.out.println("Mensagem de retorno:");
	        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        
	        String character = "";
	        while ((character = String.valueOf((char) input.read())) != null) {
	        	System.out.print(character);
	        }
	        
	        System.exit(0);
		}
		catch (Exception x) {
			System.out.println(x.getMessage());
		}

	}
	
}
