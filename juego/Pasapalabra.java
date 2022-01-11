package juego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Pasapalabra {

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(5555)) {
			while (true) {
				try (Socket j1 = server.accept()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(j1.getInputStream()));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(j1.getOutputStream()));

					writer.write("Te has conectado a la partida\nCuantos jugadores vais a jugar?");
					writer.newLine();
					writer.flush();

					String s = reader.readLine();
					System.out.println("Partida de " + s + " jugadores");
					int js = Integer.parseInt(s);

					Partida p = new Partida(j1);
					if (js == 1) {
						p.jugar();
					} else {
						Socket j2 = server.accept();
						p.addJugador(j2);
						p.jugar();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
