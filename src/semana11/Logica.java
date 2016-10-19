package semana11;

import processing.core.*;

public class Logica {

	private PApplet app;
	private int pantalla;

	private PImage img1, img2;
	private boolean thre, inv, gr;

	private PGraphics ban;
	private boolean pintarB;

	private PGraphics deg;
	private boolean pintarDg;

	private PImage face, faceU;
	private boolean pintarU;

	private PImage ast, astG;
	private boolean pintarGr;

	private PImage cOrg, cEdt;
	private boolean pintarP;

	private PGraphics jpn;
	private boolean mostrarC;

	public Logica(PApplet app) {
		this.app = app;

		img1 = app.loadImage("fondo1.jpg");
		img2 = app.loadImage("fondo2.jpg");

		ban = app.createGraphics(200, 200);

		deg = app.createGraphics(200, 200);

		face = app.loadImage("faceit.jpg");
		faceU = app.createImage(face.width, face.height, PApplet.ARGB);

		ast = app.loadImage("colors.jpg");
		astG = app.createImage(ast.width, ast.height, PApplet.ARGB);

		cOrg = app.loadImage("cycle.png");
		cEdt = app.createImage(cOrg.width, cOrg.height, PApplet.ARGB);

		jpn = app.createGraphics(400, 200);

	}

	public void ejecutar() {
		app.background(0);
		switch (pantalla) {
		case 0:
			filtroP();
			break;
		case 1:
			bandera();
			break;
		case 2:
			degradado();
			break;
		case 3:
			filtroUmbral(180);
			break;
		case 4:
			filtroGrises();
			break;
		case 5:
			filtroPixel(15);
			break;
		case 6:
			ubicacionInd();
			break;
		case 7:

			break;
		case 8:
			break;
		}
	}

	public void filtroP() {
		app.imageMode(3);
		app.image(img2, 250, 150, 800, 450);
		app.image(img1, 250, 450, 640, 425);

		if (thre) {
			app.filter(PApplet.THRESHOLD);
		} else if (inv) {
			app.filter(PApplet.INVERT);
		} else if (gr) {
			app.filter(PApplet.GRAY);
		}

		app.noStroke();
		app.fill(255);
		app.ellipse(50, 450, 40, 40);
		app.fill(0);
		app.ellipse(50, 450, 20, 20);

		for (int i = 0; i < 15; i++) {
			app.fill(255 - (i * 12));
			app.ellipse(50, 400, 40 - (i * 2), 40 - (i * 2));
		}

		app.fill(0, 50, 90);
		app.ellipse(50, 350, 40, 40);
		app.fill(50, 90, 0);
		app.ellipse(50, 350, 20, 20);
	}

	public void bandera() {
		ban.beginDraw();
		ban.background(255);
		ban.endDraw();

		if (pintarB) {
			ban.loadPixels();
			for (int i = 0; i < ban.pixels.length / 2; i++) {
				ban.pixels[i] = app.color(220, 220, 0);
			}

			for (int i = ban.pixels.length / 2; i < (ban.pixels.length / 2) + (ban.pixels.length / 4); i++) {
				ban.pixels[i] = app.color(0, 0, 200);
			}

			for (int i = ban.pixels.length - 1; i > (ban.pixels.length / 2) + (ban.pixels.length / 4) - 1; i--) {
				ban.pixels[i] = app.color(220, 0, 0);
			}
			ban.updatePixels();
		}

		app.image(ban, 250, 250);

		app.fill(220, 220, 0);
		app.ellipse(250, 450, 40, 40);
	}

	public void degradado() {
		deg.beginDraw();
		deg.background(255);
		deg.endDraw();

		if (pintarDg) {
			deg.loadPixels();
			for (int i = 0; i < deg.width; i++) {
				for (int j = 0; j < deg.height; j++) {
					int index = i + j * deg.width;
					float d = PApplet.dist(i, j, deg.width / 2, deg.height / 2);
					deg.pixels[index] = app.color(d);
				}
			}
			deg.updatePixels();
		}

		app.image(deg, 250, 250);

		app.fill(0, 220, 220);
		app.ellipse(250, 450, 40, 40);
	}

	public void filtroUmbral(int umbral) {
		face.loadPixels();
		faceU.loadPixels();
		if (pintarU) {
			for (int i = 0; i < face.pixels.length; i++) {
				int colorA = face.pixels[i];
				float valorR = app.red(colorA);
				float valorG = app.green(colorA);
				float valorB = app.blue(colorA);
				float prom = (valorR + valorG + valorB) / 3;

				if (prom > umbral) {
					faceU.pixels[i] = app.color(255);
				} else {
					faceU.pixels[i] = app.color(0);
				}
			}
			faceU.updatePixels();
		}

		if (pintarU) {
			app.image(faceU, 250, 250, 500, 500);
		} else {
			app.image(face, 250, 250, 500, 500);
		}

		app.fill(0);
		app.ellipse(50, 450, 40, 40);
		app.fill(255);
		app.ellipse(50, 450, 20, 20);
	}

	public void filtroGrises() {
		ast.loadPixels();
		astG.loadPixels();

		if (pintarGr) {
			for (int i = 0; i < ast.pixels.length; i++) {
				int colorA = ast.pixels[i];
				float valorR = app.red(colorA);
				float valorG = app.green(colorA);
				float valorB = app.blue(colorA);
				float promedio = (valorR + valorG + valorB) / 3;

				astG.pixels[i] = app.color(promedio);
			}
			astG.updatePixels();
		}

		if (pintarGr) {
			app.image(astG, 250, 250, 533, 695);
		} else {
			app.image(ast, 250, 250, 533, 695);
		}

		for (int i = 0; i < 15; i++) {
			app.fill(255 - (i * 12));
			app.ellipse(50, 450, 40 - (i * 2), 40 - (i * 2));
		}
	}

	public void filtroPixel(int tamPix) {
		cOrg.loadPixels();
		cEdt.loadPixels();

		if (pintarP) {
			for (int i = 0; i < cOrg.width; i += tamPix) {
				for (int j = 0; j < cOrg.height; j += tamPix) {

					float valorR = 0;
					float valorG = 0;
					float valorB = 0;

					for (int q = i; q < i + tamPix; q++) {
						if (j + tamPix < cOrg.height) {
							for (int k = j; k < j + tamPix; k++) {
								int index = q + k * (cOrg.width);
								int colorA = cOrg.pixels[index];

								valorR += app.red(colorA);
								valorG += app.green(colorA);
								valorB += app.blue(colorA);
							}
						}
					}
					valorR = valorR / (tamPix * tamPix);
					valorG = valorG / (tamPix * tamPix);
					valorB = valorB / (tamPix * tamPix);
					for (int q = i; q < i + tamPix; q++) {
						if (j + tamPix < cOrg.height) {
							for (int k = j; k < j + tamPix; k++) {
								int index = q + k * (cOrg.width);
								cEdt.pixels[index] = app.color(valorR, valorG, valorB);
							}
						}
					}
				}
			}

			cEdt.updatePixels();
		}

		if (pintarP) {
			app.image(cEdt, 250, 250);
		} else {
			app.image(cOrg, 250, 250);
		}

		app.rectMode(3);
		app.fill(20);
		app.rect(50, 450, 40, 40);
		app.fill(120);
		app.rect(50, 450, 20, 20);
	}

	public void ubicacionInd() {
		int iX=250;
		int iY=250;
		
		jpn.beginDraw();
		jpn.background(255);
		jpn.noStroke();
		jpn.fill(255, 0, 0);
		jpn.ellipse(jpn.width / 2 +50 , jpn.height / 2, 80, 80);
		jpn.endDraw();

		jpn.loadPixels();

		app.imageMode(3);
		app.image(jpn, iX, iY);

		if (mostrarC) {
			int xR = 0;
			int yR = 0;
			int contador = 0;
			for (int i = 0; i < jpn.width; i++) {
				for (int j = 0; j < jpn.height; j++) {
					if(app.red(jpn.pixels[i+(j*jpn.width)])>200 && app.green(jpn.pixels[i+(j*jpn.width)])<10){
						contador++;
						xR+=i;
						yR+=j;
					}
				}
			}

			xR = xR / contador;
			yR = yR / contador;
			
			app.rectMode(3);
			app.fill(0, 200, 100);
			app.rect(xR-200+iX, yR-100+iY, 20, 20);
		}

		app.ellipse(250, 450, 40, 40);
	}

	public void ubicacionVar() {

	}

	public void chromaKey() {

	}

	public void click() {
		if (zonaSensible(50, 350) && pantalla == 0) {
			inv = true;
			thre = false;
			gr = false;
		}

		if (zonaSensible(50, 400) && pantalla == 0) {
			inv = false;
			thre = false;
			gr = true;
		}

		if (zonaSensible(50, 450) && pantalla == 0) {
			inv = false;
			thre = true;
			gr = false;
		}

		if (zonaSensible(250, 450) && pantalla == 1) {
			pintarB = !pintarB;
		}

		if (zonaSensible(250, 450) && pantalla == 2) {
			pintarDg = !pintarDg;
		}

		if (zonaSensible(50, 450) && pantalla == 3) {
			pintarU = !pintarU;
		}

		if (zonaSensible(50, 450) && pantalla == 4) {
			pintarGr = !pintarGr;
		}

		if (zonaSensible(50, 450) && pantalla == 5) {
			pintarP = !pintarP;
		}

		if (zonaSensible(250, 450) && pantalla == 6) {
			mostrarC = !mostrarC;
		}
	}

	public void tecla() {
		switch (app.keyCode) {
		case PApplet.LEFT:
			if (pantalla > 0) {
				pantalla--;
			}
			break;

		case PApplet.RIGHT:
			if (pantalla < 8) {
				pantalla++;
			}
			break;
		}
	}

	public boolean zonaSensible(int x, int y) {
		if (PApplet.dist(app.mouseX, app.mouseY, x, y) < 40) {
			return true;
		}
		return false;
	}
}
