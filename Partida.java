package juego;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Partida {
	private List<Jugador> jugadores;
	private int turno;
	private creadorRoscos c=new creadorRoscos();
	
	public Partida(Socket s) throws IOException {
		this.jugadores=new ArrayList<>();
		
		
		Jugador j=new Jugador(this.c.getRosco(),s);
		this.jugadores.add(j);
	}
	
	public void jugada() throws IOException {
		boolean paso=false;
		Jugador j=this.jugadores.get(this.turno);
		while(j.puedeJugar() && !paso) {
			Letra x=j.getLetra();
			j.preguntar(x.toString());
			String resp=j.responder();
			if(!resp.equals("P")) {
				if(resp.equals(x.getRespuesta())) {
					j.correcta(x);
				}
				else {
					j.incorrecta(x);
					paso=true;
				}
			}
			else {
				j.pasapalabra();
				paso=true;
			}
		}
	}
	
	public boolean hasFinished() {
		boolean f=false;
		if(this.jugadores.size()==2) {
			if(!this.jugadores.get(0).puedeJugar() && !this.jugadores.get(1).puedeJugar()) {
				f=true;
			}
		}
		else {
			if(!this.jugadores.get(0).puedeJugar()) {
				f=true;
			}
		}
		return f;
	}
	
	public void jugar() throws IOException {
		this.turno=0;
		
		while(!hasFinished()) {
			this.jugada();
			this.turno=(this.turno+1)%this.jugadores.size();
		}
		
		if(this.jugadores.size()==1) {
			this.jugadores.get(0).preguntar("fin");
			this.jugadores.get(0).preguntar(this.resultado());
		}
		else {
			for(Jugador j: this.jugadores) {
				j.preguntar("fin");
				j.preguntar(this.resultado());
			}
		}
	}
	
	public String resultado() {
		StringBuilder f=new StringBuilder("-----Resultado-----\n");
		for(Jugador j: this.jugadores) {
			f.append(j.resultado());
			f.append("\n");
		}
		return f.toString();
	}
	
	public void addJugador(Socket s) throws IOException {
		Jugador j=new Jugador(this.c.getRosco(), s);
		this.jugadores.add(j);
	}
}
