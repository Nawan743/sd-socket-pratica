package projeto01;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Servidor {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket s = new ServerSocket(2000);
		System.out.println("Esperando conexão .................");
		
		Socket conexao = s.accept();
		System.out.println("Conexão aceita, esperando dados ...");

		DataInputStream entrada = new DataInputStream(conexao.getInputStream());
		DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
		
		for(int i = 0; i < 100; i++) {
			int linha = entrada.readInt();
			System.out.println("O cliente enviou o valor: " + linha);
			saida.writeUTF("Recebi o dado: " + linha);
			TimeUnit.SECONDS.sleep(2);
		}
		
		s.close();
	}
}
