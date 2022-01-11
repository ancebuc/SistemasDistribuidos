package juego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Jugador {
	private String name;
	private Rosco rosco;
	private Socket s;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public Jugador(Rosco r, Socket s) throws IOException {
		this.rosco=r;
		this.s=s;
		this.reader = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
		this.writer = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
		this.preguntar("Introduce el nombre");
		this.name=this.responder();
	}
	
	public String getNombre() {
		return this.name;
	}

	public String responder() throws IOException {
		return this.reader.readLine();
	}
	
	public void preguntar(String p) throws IOException {
		this.writer.write(p);
		this.writer.newLine();
		this.writer.flush();
	}
	
	public boolean puedeJugar() {
		return !this.rosco.completo();
	}
	
	public Letra getLetra() {
		return this.rosco.getLetra();
	}
	
	public void correcta(Letra l) throws IOException {
		this.rosco.addCorrecta(l);
		this.preguntar("CORRECTA");
	}
	
	public void incorrecta(Letra l) throws IOException {
		this.rosco.addIncorrecta(l);
		this.preguntar("INCORRECTA, la respuesta correcta era: "+l.getRespuesta());
	}
	
	public void pasapalabra() throws IOException {
		this.rosco.pasapalabra();
		this.preguntar("PASAPALABRA");
	}
	
	public String resultado() {
		StringBuilder res=new StringBuilder("");
		res.append("---"+this.name+"---\n");
		res.append(this.rosco.estado());
		return res.toString();
	}
}
