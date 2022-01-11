package jugadores;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ordenador2 {
	public static void main(String[] args) {
		try(Socket s=new Socket("192.168.0.161", 5555);
				BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				Scanner sc=new Scanner(System.in);){
			
			//introducir nombre
			System.out.println(reader.readLine());
			writer.write(sc.nextLine());
			writer.newLine();
			writer.flush();
			
			//Leer y responder
			String st=reader.readLine();
			while(!st.contains("fin")) {
				System.out.println(st);
				System.out.println(reader.readLine());
				writer.write(sc.nextLine());
				writer.newLine();
				writer.flush();
				System.out.println(reader.readLine());
				st=reader.readLine();
			}
			
			//Leer el resultado
			int numJugadores=2;
			System.out.println(reader.readLine());
			for(int i=0; i<numJugadores; i++) {
				System.out.println(reader.readLine());
				System.out.println(reader.readLine());
				System.out.println(reader.readLine());
			}
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
