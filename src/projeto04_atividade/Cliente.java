package projeto04_atividade;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		Socket s = new Socket("127.0.0.1", 2001);
		
		DataOutputStream saida = new DataOutputStream(s.getOutputStream());
		
		DataInputStream entrada = new DataInputStream(s.getInputStream());
		
		String linha;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			System.out.println("Você: ");
			linha = teclado.readLine();
			saida.writeUTF("Cliente: " + linha);
			linha = entrada.readUTF();
			if(linha.equalsIgnoreCase("servidor:") || linha.equalsIgnoreCase("servidor: sair")) {
				System.out.println("O servidor se desconectou!");
				break;
			}
			
			System.out.println(linha);
		}
		
		saida.close();
		entrada.close();
		
		teclado.close();
		
		s.close();
 	}

}
