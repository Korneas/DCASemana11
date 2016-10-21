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

	private PImage aros;
	private boolean mostrarA;
	
	private PImage per,perEdt,fondo;
	private boolean mostrarCh;

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

		aros = app.loadImage("anillos.jpg");
		
		per = app.loadImage("chromaPer.jpg");
		perEdt = app.createImage(per.width, per.height, PApplet.ARGB);
		fondo = app.loadImage("chromaFondo.jpg");

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
			filtroUmbral(70);
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
			ubicacionVar();
			break;
		case 8:
			chromaKey();
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
		app.fill(360);
		app.ellipse(50, 450, 40, 40);
		app.fill(0);
		app.ellipse(50, 450, 20, 20);

		for (int i = 0; i < 15; i++) {
			app.fill(360 - (i * 12));
			app.ellipse(50, 400, 40 - (i * 2), 40 - (i * 2));
		}

		app.fill(150, 50, 40);
		app.ellipse(50, 350, 40, 40);
		app.fill(200, 50, 40);
		app.ellipse(50, 350, 20, 20);
	}

	public void bandera() {
		ban.beginDraw();
		ban.background(255);
		ban.endDraw();

		if (pintarB) {
			ban.loadPixels();
			for (int i = 0; i < ban.pixels.length / 2; i++) {
				ban.pixels[i] = app.color(50, 100, 100);
			}

			for (int i = ban.pixels.length / 2; i < (ban.pixels.length / 2) + (ban.pixels.length / 4); i++) {
				ban.pixels[i] = app.color(225, 100, 100);
			}

			for (int i = ban.pixels.length - 1; i > (ban.pixels.length / 2) + (ban.pixels.length / 4) - 1; i--) {
				ban.pixels[i] = app.color(0, 100, 100);
			}
			ban.updatePixels();
		}

		app.image(ban, 250, 250);

		app.fill(50, 80, 100);
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
					deg.pixels[index] = app.color(d*2);
				}
			}
			deg.updatePixels();
		}

		app.image(deg, 250, 250);

		app.fill(200, 100, 80);
		app.ellipse(250, 450, 40, 40);
	}

	public void filtroUmbral(int umbral) {
		face.loadPixels();
		faceU.loadPixels();
		if (pintarU) {
			for (int i = 0; i < face.pixels.length; i++) {
				int colorA = face.pixels[i];
				float brillo = app.brightness(colorA);

				if (brillo > umbral) {
					faceU.pixels[i] = app.color(0, 0, 100);
				} else {
					faceU.pixels[i] = app.color(0, 0, 0);
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
		app.fill(360);
		app.ellipse(50, 450, 20, 20);
	}

	public void filtroGrises() {
		ast.loadPixels();
		astG.loadPixels();

		if (pintarGr) {
			for (int i = 0; i < ast.pixels.length; i++) {
				int colorA = ast.pixels[i];
				float brillo = app.brightness(colorA);

				astG.pixels[i] = app.color(brillo * 3);
			}
			astG.updatePixels();
		}

		if (pintarGr) {
			app.image(astG, 250, 250, 533, 695);
		} else {
			app.image(ast, 250, 250, 533, 695);
		}

		for (int i = 0; i < 15; i++) {
			app.fill(360 - (i * 12));
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
		app.fill(100);
		app.rect(50, 450, 40, 40);
		app.fill(200);
		app.rect(50, 450, 20, 20);
	}

	public void ubicacionInd() {
		int iX = 250;
		int iY = 250;

		jpn.beginDraw();
		jpn.colorMode(PApplet.HSB,360,100,100);
		jpn.background(360);
		jpn.noStroke();
		jpn.fill(0, 100, 100);
		jpn.ellipse(jpn.width / 2 + 50, jpn.height / 2, 80, 80);
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
					if (app.hue(jpn.pixels[i + (j * jpn.width)]) >= -10
							&& app.hue(jpn.pixels[i + (j * jpn.width)]) <= 10
							&& app.saturation(jpn.pixels[i + (j * jpn.width)]) > 80) {
						contador++;
						xR += i;
						yR += j;
					}
				}
			}

			if (contador != 0) {
				xR = xR / contador;
				yR = yR / contador;
			}

			app.rectMode(3);
			app.fill(150, 80, 60);
			app.rect(xR - 200 + iX, yR - 100 + iY, 20, 20);
		}

		app.fill(360);
		app.ellipse(250, 450, 40, 40);
	}

	public void ubicacionVar() {
		app.imageMode(3);
		app.image(aros, 250, 250);

		if (mostrarA) {
			detColor(aros, 350);
			detColor(aros, 50);
			detColor(aros, 150);
			detColor(aros, 200);
			detNegro(aros, 0);
		}

		app.fill(140, 70, 80);
		app.ellipse(250, 450, 40, 40);
	}

	public void chromaKey() {
		per.loadPixels();
		perEdt.loadPixels();
		
		if (mostrarCh){
			for (int i = 0; i < per.width; i++) {
				for (int j = 0; j < per.height; j++) {
					int index = i + (j*per.width);
					int px = per.pixels[index];
					if(app.green(px)>=100 && app.red(px)<80){
						perEdt.pixels[index] = fondo.pixels[index];
					} else {
						perEdt.pixels[index] = per.pixels[index];
					}
				}
			}
		}
		
		if(mostrarCh){
			app.image(perEdt, 250, 250);
		} else {
			app.image(per, 250, 250);
		}
		
		perEdt.updatePixels();
		
		app.fill(200, 50, 40);
		app.ellipse(250, 450, 40, 40);
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

		if (zonaSensible(250, 450) && pantalla == 7) {
			mostrarA = !mostrarA;
		}
		
		if (zonaSensible(250, 450) && pantalla == 8) {
			mostrarCh = !mostrarCh;
		}
	}

	public void tecla() {
		switch (app.keyCode) {
		case PApplet.LEFT:
			if (pantalla > 0) {
				pantalla--;
			}
			if (pantalla==5){
				app.colorMode(PApplet.RGB,255,255,255);
			} else {
				app.colorMode(PApplet.HSB,360,100,100);
			}
			break;

		case PApplet.RIGHT:
			if (pantalla < 8) {
				pantalla++;
			}
			
			if (pantalla==5 || pantalla==8){
				app.colorMode(PApplet.RGB,255,255,255);
			} else {
				app.colorMode(PApplet.HSB,360,100,100);
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

	public void detColor(PImage img, int col) {
		int x = 0;
		int y = 0;
		int cont = 0;
		for (int i = 0; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {
				if (app.hue(img.pixels[i + (j * img.width)]) >= col - 10
						&& app.hue(img.pixels[i + (j * img.width)]) <= col + 10
						&& app.saturation(img.pixels[i + (j * img.width)]) > 85) {
					cont++;
					x += i;
					y += j;
				}
			}
		}

		x = x / cont;
		y = y / cont;

		app.rectMode(3);
		app.fill(120, 80, 80);
		app.rect(x - (img.width / 2) + 250, y - (img.height / 2) + 250, 20, 20);
	}
	
	public void detNegro(PImage img,int brg){
		int x = 0;
		int y = 0;
		int cont = 0;
		for (int i = 0; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {
				if (app.brightness(img.pixels[i + (j * img.width)]) >= brg - 10
						&& app.brightness(img.pixels[i + (j * img.width)]) <= brg + 10
						&& app.saturation(img.pixels[i + (j * img.width)]) < 40) {
					cont++;
					x += i;
					y += j;
				}
			}
		}

		x = x / cont;
		y = y / cont;

		app.rectMode(3);
		app.fill(120, 80, 80);
		app.rect(x - (img.width / 2) + 250, y - (img.height / 2) + 250, 20, 20);
	}

}
