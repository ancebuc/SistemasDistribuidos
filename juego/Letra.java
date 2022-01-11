package juego;

public class Letra {
	private String letra;
	private String definicion;
	private String respuesta;
	
	public Letra(String letra, String definicion, String respuesta) {
		this.letra=letra;
		this.definicion=definicion;
		this.respuesta=respuesta;
	}
	
	public String toString() {
		StringBuilder s=new StringBuilder("");
		s.append(this.letra);
		s.append("\n");
		s.append(this.definicion);
		return s.toString();
	}
	
	public String getRespuesta() {
		return this.respuesta;
	}
	
	public String getLetra() {
		return this.letra;
	}
}
