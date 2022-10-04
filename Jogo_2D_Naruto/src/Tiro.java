import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tiro {

	private int posx;
	private int posy;
	private int velocidade;
	private int direcao;
	private int indiceAtual;
	private int largura;
	private int altura;
	
	private static BufferedImage animacao[];

	public static void carregarImagens() {
		
		animacao = new BufferedImage[2];
		
		
			try {
				for (int i = 0; i < 2; i++) {
				animacao[i] = ImageIO.read(new File("res/kunai " + (i + 1) + ".png"));

				}
				
			} catch (IOException e) {
				System.out.println("Erro ao carregar as imagens de disparo");
				e.printStackTrace();
			}

		
	}
	
	
	
	public Tiro(int x, int y, int dir) {
		
		
		
		this.posx = x;
		this.posy = y;
		this.direcao = dir;
		this.velocidade = 4;
		this.indiceAtual = 0;

		this.largura = 25;
		this.altura = 25;
		
	}
	
	public void atualizar() {
		
		posx += direcao * velocidade;
		indiceAtual++;
		
		if(indiceAtual >= 4) {
			indiceAtual = 0;
		}
		
	}
	
	public void pintar(Graphics2D g) {
		
		if(direcao == 1) {
			g.drawImage(animacao[indiceAtual], // imagens usadas
			posx, posy, // x e y
			posx + largura, posy + altura, // posição + tamanho da imagem
			0, 0, // canto da imagem original
			animacao[indiceAtual].getHeight(), animacao[indiceAtual].getWidth(), // tamanho da imagem
			null);
		} 
		
		else if (direcao == -1) {
			g.drawImage(animacao[indiceAtual],
			posx, posy, 
			posx + largura,
			posy + altura,
			animacao[indiceAtual].getWidth(), 0,
			0, animacao[indiceAtual].getHeight(), null);
		}
		
	}
	
}
