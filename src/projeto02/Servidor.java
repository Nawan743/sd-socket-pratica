package projeto02;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Servidor {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket s = new ServerSocket(2000);
		System.out.println("Esperando conex?o .................");
		
		Socket conexao = s.accept();
		System.out.println("Conex?o aceita, esperando dados ...");
		
		InetAddress endereco_remoto;
		int porta_remota;
		
		endereco_remoto = conexao.getInetAddress();
		porta_remota = conexao.getPort();
		
		System.out.println("Nome da m?quina remota: " + endereco_remoto.getHostName());
		System.out.println("IP da m?quina remota: " + endereco_remoto.getHostAddress());
		System.out.println("Porta da m?quina remota: " + porta_remota);

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
