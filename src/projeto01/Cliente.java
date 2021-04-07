package projeto01;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		Socket s = new Socket("localhost", 2000);
		
		DataOutputStream saida = new DataOutputStream(s.getOutputStream());
		
		DataInputStream entrada = new DataInputStream(s.getInputStream());
		
		for(int i = 0; i < 100; i++) {
			saida.writeInt(i);
			System.out.println("Enviei: " + i);
			String en = entrada.readUTF();
			System.out.println("Recebi: " + en);
			TimeUnit.SECONDS.sleep(2);
		}
		
		s.close();
 	}

}
