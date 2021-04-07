package projeto04_atividade;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket s = new ServerSocket(2001);
		
		System.out.println("Esperando conexão .................");
		
		Socket conexao = s.accept();
		System.out.println("Conectou!");
		
		InetAddress endereco_remoto;
		int porta_remota;
		
		endereco_remoto = conexao.getInetAddress();
		porta_remota = conexao.getPort();
		
		System.out.println("Nome da máquina remota: " + endereco_remoto.getHostName());
		System.out.println("IP da máquina remota: " + endereco_remoto.getHostAddress());
		System.out.println("Porta da máquina remota: " + porta_remota);
		
		DataInputStream entrada = new DataInputStream(conexao.getInputStream());
		DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {	
			String linha = entrada.readUTF();
			if (linha != null && !(linha.trim().equals("cliente:")) && !linha.equalsIgnoreCase("cliente: sair")) {
				System.out.println(linha);
				System.out.println("Você: ");
				linha = teclado.readLine();
				saida.writeUTF("Servidor: " + linha);
			}
			else {
				System.out.println("O cliente se desconectou!");
				break;
			}
		}
		
		saida.close();
		entrada.close();
		
		teclado.close();
		
		conexao.close();
		s.close();
	}
}
