import java.awt.Color;
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
	
	
	
	public Canvas() {
		ImageIcon referencia = new ImageIcon("res\\Background.png");
		fundo = referencia.getImage();

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
		
	}
	

	@Override
	public void paintComponent(Graphics g2) {
		
		super.paintComponent(g2); //limpar rastro da imagem
		
		Graphics2D g = (Graphics2D) g2.create();
		
		//desenhar o fundo
		//g.setColor(Color.blue);
		//g.fillRect(0, 0, 1294, 728);
		g.drawImage(fundo,0,0,null);
		
		//desenhar o player
		player.pintar(g);
		
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
		
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			player.setDirecao(1);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.setVelocidade(10);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
			player.setDirecao(0);
		}
		
		/*if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			player.setDirecao(0);
		}*/
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.setVelocidade(3);
		}
		
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
