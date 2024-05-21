package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fechar a tela
		window.setResizable(true); // mudar o tamanho da tela
		window.setTitle("Jogo Labirinto"); // titulo
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null); //display no meio da tela
		window.setVisible(true); // tornar a tela visivel
		
		gamePanel.startGameThread();
	}

}