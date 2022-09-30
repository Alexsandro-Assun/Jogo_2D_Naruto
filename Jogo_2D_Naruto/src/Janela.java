import javax.swing.JFrame;

public class Janela {
	
	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Naruto Java Adventure");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(1294,728);
		janela.setLocationRelativeTo(null); //deixar a janela no centro
		janela.setResizable(false); 

		
		Canvas canvas = new Canvas();
		janela.add(canvas);
		
		janela.addKeyListener(canvas);
		janela.setVisible(true);

	}
	
	
}
