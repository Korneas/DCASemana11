package semana11;
import processing.core.PApplet;

public class Main extends PApplet {
	
	private Logica app;
	
	static public void main(String[] args) {
		PApplet.main("semana11.Main");
	}

	@Override
	public void settings() {
		size(500, 500);
	}

	@Override
	public void setup() {
		app = new Logica(this);

	}

	@Override
	public void draw() {
		background(0);
		app.ejecutar();
	}
	
	@Override
	public void mouseClicked(){
		app.click();
	}
	
	@Override
	public void keyPressed(){
		app.tecla();
	}
}

