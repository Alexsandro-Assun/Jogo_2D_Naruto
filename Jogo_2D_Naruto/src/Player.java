import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	private BufferedImage parado[];
	private BufferedImage correndo[];
	private BufferedImage pulando[];

	private int indiceImagemAtual;
	private int indice;
	private int indicePulando;

	private float timer;
	private int largura;
	private int altura;
	private int direcao;
	private int ultimaDir;
	private boolean pulo;
	private int posx;
	private int posy;
	private int velocidade;
	private int d;

	public Player() {

		timer = 0;
		indiceImagemAtual = 0;
		indice = 0;
		indicePulando = 0;

		parado = new BufferedImage[2];
		correndo = new BufferedImage[3];
		pulando = new BufferedImage[4];

		altura = 100;
		largura = 100;

		direcao = 0;
		ultimaDir = 1;
		pulo = false;
		velocidade = 3;

		posx = 0;
		posy = 300;

		// animação parado
		for (int i = 0; i < 2; i++) {
			try {

				parado[i] = ImageIO.read(new File("res/naruto neutro " + (i + 1) + ".png"));

			} catch (IOException e) {
				System.out.println("Erro ao carregar as imagens");
				e.printStackTrace();
			}

		}
		System.out.println("todas as imagens carregadas");

		// animação correndo/andando

		for (int i = 0; i < 3; i++) {
			try {

				correndo[i] = ImageIO.read(new File("res/correndo " + (i + 1) + ".png"));

			} catch (IOException e) {
				System.out.println("Erro ao carregar as imagens");
				e.printStackTrace();
			}

		}

		// animação pulando

		for (int i = 0; i < 4; i++) {
			try {

				pulando[i] = ImageIO.read(new File("res/pulando " + (i + 1) + ".png"));

			} catch (IOException e) {
				System.out.println("Erro ao carregar as imagens");
				e.printStackTrace();
			}

		}
	}

	public void atualizar() {
		// atualiza a animação
		timer++;
		if (pulo) {

			if (timer >= 7) {
				indicePulando++;
				if (indicePulando == 4) {
					indicePulando = 0;
					pulo = false;
				}
				System.out.println(indicePulando+" pulando ");
				timer = 0;
			}
			if(indicePulando < 2) {
				posy -= 4;
			} else {
				posy += 4;
			}
		} else {

			if (timer >= 10) {

				indiceImagemAtual++;
				indice++;
				indicePulando++;

				if (indiceImagemAtual == 2) {
					indiceImagemAtual = 0;
				}
				if (indice == 3) {
					indice = 0;
				}
				if (indicePulando == 4) {
					indicePulando = 0;
				}
				System.out.println(indiceImagemAtual);
				timer = 0;
			}
		}

		if (direcao == 1) {
			posx += velocidade;
		} else if (direcao == -1) {
			posx -= velocidade;
		}

	}

	public void pintar(Graphics2D g) {
		// parado pra frente
		if (ultimaDir == 1 & d == 0) {
			g.drawImage(parado[indiceImagemAtual], // imagens usadas
				posx, posy, // x e y
				posx + largura, posy + altura, // posição + tamanho da imagem
				0, 0, // canto da imagem original
				parado[indiceImagemAtual].getHeight(),
				parado[indiceImagemAtual].getWidth(), // tamanho da imagem
				null);
		}
		// parado pra trás

		if (ultimaDir == -1 & d == 0) {
			g.drawImage(parado[indiceImagemAtual],
				posx, posy,
				posx + largura, 
				posy + altura,
				parado[indiceImagemAtual].getWidth(), 0,
				0, parado[indiceImagemAtual].getHeight(), null);
		}

		// andando pra frente

		if (direcao == 1) {
			g.drawImage(correndo[indice], // imagens usadas
				posx, posy, // x e y
				posx + largura, posy + altura, // posição + tamanho da imagem
				0, 0, // canto da imagem original
				correndo[indice].getHeight(), correndo[indice].getWidth(), // tamanho da imagem
				null);
		}
		// andando pra trás

		if (direcao == -1) {
			g.drawImage(correndo[indice],
				posx, posy, 
				posx + largura,
				posy + altura,
				correndo[indice].getWidth(), 0,
				0, correndo[indice].getHeight(), null);
		}

		// pulando pra frente

		if (pulo) {
			
			g.drawImage(pulando[indicePulando], // imagens usadas
				posx, posy, // x e y
				posx + largura, posy + altura, // posição + tamanho da imagem
				0, 0, // canto da imagem original
				pulando[indicePulando].getHeight(),
				pulando[indicePulando].getWidth(), // tamanho da imagem
				null);
			
		}
		// pulando pra trás

		if (pulo) {
	
			if (ultimaDir == -1) {
				g.drawImage(pulando[indicePulando],
					posx, posy,
					posx + largura,
					posy + altura,
					pulando[indicePulando].getWidth(), 0,
					0, pulando[indicePulando].getHeight(), null);
			}

		}

	}

	public void setDirecao(int dir) {

		if (dir != 0) {
			this.ultimaDir = dir;
			this.direcao = dir;
			d = 1;
		} else {
			this.direcao = dir;
			d = 0;
		}

	}

	public void setPulo() {
		if(pulo == false) {
			pulo = true;
			timer = 0;
			indicePulando = 0;
		}
		
	}
	
	public void setVelocidade(int speed) {
		this.velocidade = speed;
	}

}
