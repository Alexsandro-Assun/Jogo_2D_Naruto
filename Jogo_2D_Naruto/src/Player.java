import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	private BufferedImage parado[];
	private BufferedImage correndo[];
	private BufferedImage pulando[];
	private BufferedImage atirando[];

	private int indiceImagemAtual;
	private int indice;
	private int indicePulando;
	private int indiceA;

	private float timer;
	private int largura;
	private int altura;
	private int direcao;
	private int ultimaDir;
	private boolean pulo;
	private boolean disparando;

	private int alturaPulo;
	private int posx;
	private int posy;
	private int velocidade;
	private int d;

	public Player() {

		timer = 0;
		indiceImagemAtual = 0;
		indice = 0;
		indicePulando = 0;
		indiceA = 0;

		parado = new BufferedImage[2];
		correndo = new BufferedImage[3];
		pulando = new BufferedImage[4];
		atirando = new BufferedImage[4];

		altura = 100;
		largura = 100;

		direcao = 0;
		ultimaDir = 1;
		disparando = false;
		pulo = false;
		alturaPulo = 4;
		velocidade = 3;

		posx = 0;
		posy = 350;
		
		//animação atirando
		
		for (int i = 0; i < 4; i++) {
			try {

				atirando[i] = ImageIO.read(new File("res/tiro " + (i + 1) + ".png"));

			} catch (IOException e) {
				System.out.println("Erro ao carregar as imagens de tiro");
				e.printStackTrace();
			}

		}

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
				
				timer = 0;
			}
			
			//altura do pulo
			if(indicePulando < 2) {
				posy -= alturaPulo;
			} else {
				posy += alturaPulo;
			}
			
		} else  {

			if (timer >= 7) {

				indiceImagemAtual++;
				indice++;
				
				if(disparando) {
					if(timer>=5) {
						
						indiceA++;
						if(indiceA == 4) {
							indiceA = 0;
						}
					
					}
					timer = 0;
				}
				
				if (indiceImagemAtual == 2) {
					indiceImagemAtual = 0;
				}
				if (indice == 3) {
					indice = 0;
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
		if (ultimaDir == 1 & d == 0 & !pulo) {
			g.drawImage(parado[indiceImagemAtual], // imagens usadas
				posx, posy, // x e y
				posx + largura, posy + altura, // posição + tamanho da imagem
				0, 0, // canto da imagem original
				parado[indiceImagemAtual].getHeight(),
				parado[indiceImagemAtual].getWidth(), // tamanho da imagem
				null);
		}
		// parado pra trás

		if (ultimaDir == -1 & d == 0 & !pulo) {
			g.drawImage(parado[indiceImagemAtual],
				posx, posy,
				posx + largura, 
				posy + altura,
				parado[indiceImagemAtual].getWidth(), 0,
				0, parado[indiceImagemAtual].getHeight(), null);
		}

		// andando pra frente

		if (direcao == 1 & !pulo) {
			g.drawImage(correndo[indice], // imagens usadas
				posx, posy, // x e y
				posx + largura, posy + altura, // posição + tamanho da imagem
				0, 0, // canto da imagem original
				correndo[indice].getHeight(), correndo[indice].getWidth(), // tamanho da imagem
				null);
		}
		// andando pra trás

		if (direcao == -1 & !pulo) {
			g.drawImage(correndo[indice],
				posx, posy, 
				posx + largura,
				posy + altura,
				correndo[indice].getWidth(), 0,
				0, correndo[indice].getHeight(), null);
		}

		// pulando pra frente

		if (pulo) {
			if(ultimaDir == 1) {
				g.drawImage(pulando[indicePulando], // imagens usadas
					posx, posy, // x e y
					posx + largura, posy + altura, // posição + tamanho da imagem
					0, 0, // canto da imagem original
					pulando[indicePulando].getHeight(),
					pulando[indicePulando].getWidth(), // tamanho da imagem
					null);
			} else {
				
				// pulando pra trás
				g.drawImage(pulando[indicePulando],
					posx, posy,
					posx + largura,
					posy + altura,
					pulando[indicePulando].getWidth(), 0,
					0, pulando[indicePulando].getHeight(), null);
			}
		
			
		}
		
		//atirando frente
		
		
		if (disparando & !pulo & d == 3) {
			if(ultimaDir == 1) {
				g.drawImage(atirando[indiceA], // imagens usadas
					posx, posy, // x e y
					posx + largura, posy + altura, // posição + tamanho da imagem
					0, 0, // canto da imagem original
					atirando[indiceA].getHeight(),
					atirando[indiceA].getWidth(), // tamanho da imagem
					null);
			} else {
				
				// atirando pra trás
				g.drawImage(atirando[indiceA],
					posx, posy,
					posx + largura,
					posy + altura,
					atirando[indiceA].getWidth(), 0,
					0, atirando[indiceA].getHeight(), null);
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
	
	public Tiro atira(){
		
		Tiro tiro = new Tiro(posx, posy, ultimaDir);
		return tiro;
	}
	
	public void setVelocidade(int speed) {
		this.velocidade = speed;
	}

	public void setDisparando() {
		d = 3;
		if(disparando == false) {
			disparando = true;
			timer = 0;
			indiceA = 0;
		} 
		
	}
}
