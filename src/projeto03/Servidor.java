package projeto03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		@SuppressWarnings("resource")
		ServerSocket s = new ServerSocket(2001);
		
		while(true) {
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
	
			String linha = entrada.readUTF();
			while(linha != null && !(linha.trim().equals(""))) {
				saida.writeUTF("echo servidor " + linha);
				linha = entrada.readUTF();
			}
			
			saida.writeUTF(linha);
			
			conexao.close();
		}
	}
}
