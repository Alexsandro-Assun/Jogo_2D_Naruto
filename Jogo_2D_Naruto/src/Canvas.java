import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable, KeyListener {

	private Player player;
	private Image fundo;
	private Image faceIcone;
	private Tiro tiro;
	private int x = 450;
	
	
	public Canvas() {
		ImageIcon referencia = new ImageIcon("res\\Background.png");
		ImageIcon Icone = new ImageIcon("res\\Face.png");
		
		faceIcone = Icone.getImage();
		fundo = referencia.getImage();
		
		Tiro.carregarImagens();
		player = new Player();
		
		Thread gameLoop = new Thread(this);
		gameLoop.start();
		
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			atualiza();
			
			repaint();
				
			dorme();
			
		}
		
	}
	
	private void atualiza(){
	
		player.atualizar();
		
		if(x > 0) {
			x--;
		}
		
		if(tiro != null) {
			tiro.atualizar();
		}
	}
	

	@Override
	public void paintComponent(Graphics g2) {
		
		super.paintComponent(g2); //limpar rastro da imagem
		
		Graphics2D g = (Graphics2D) g2.create();
		
		//desenhar o fundo
		g.drawImage(fundo,0,0,null);
		g.drawImage(faceIcone,-30,-30,150,150,null);
		
		//barra de vida
		g.setColor(new Color(0, 160, 0));
		g.fillRect(75, 22, x, 25); // x, y da tela // x , y do tamanho 
		g.setColor(Color.black);
		g.drawRect(74, 21, 450,25);
		g.setColor(Color.black);
		g.drawRect(73, 22, 450,25);
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD,20));
		g.drawString("Naruto", 80 , 41);
		
		//desenhar o player
		player.pintar(g);
		
		//desenhar tiro
		//tiro.pintar(g);
		
	}

	private void dorme() {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.setPulo();			
		}
		
		/*else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.setDirecao(-2);
		}*/
		
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.setDirecao(-1);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			player.setDirecao(1);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.setVelocidade(10);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_H) {
		 //tiro = player.atira();
			player.setDisparando();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
			player.setDirecao(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			player.setDirecao(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.setVelocidade(3);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_H) {
			player.setDirecao(0);
		}
		
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
