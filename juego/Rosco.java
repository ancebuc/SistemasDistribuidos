package juego;
import java.util.ArrayList;
import java.util.List;

public class Rosco {
	private List<Letra> letras;
	private int correctas;
	private int incorrectas;
	private int marcador;
	
	public Rosco() {
		this.letras=new ArrayList<Letra>(25);
		this.correctas=0;
		this.incorrectas=0;
		this.marcador=0;
	}
	
	public boolean completo() {
		return this.letras.isEmpty();
	}
	
	public Letra getLetra() {
		this.marcador=(this.marcador)%this.letras.size();
		Letra l=this.letras.get(this.marcador);
		return l;
	}
	
	public void addLetra(Letra l) {
		this.letras.add(l);
	}
	
	public void addCorrecta(Letra l) {
		this.letras.remove(l);
		this.correctas++;
	}
	
	public void addIncorrecta(Letra l) {
		this.letras.remove(l);
		this.incorrectas++;
	}
	
	public void pasapalabra() {
		this.marcador++;
	}
	
	public String estado() {
		StringBuilder s=new StringBuilder("Correctas: ");
		s.append(this.correctas);
		s.append("\n");
		s.append("Incorectas: ");
		s.append(this.incorrectas);
		return s.toString();
	}
}
