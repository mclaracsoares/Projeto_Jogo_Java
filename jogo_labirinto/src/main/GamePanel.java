package main;

import javax.swing.JPanel;
import entities.Aventureiro;
import tile.TileManager;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
	

	private static final long serialVersionUID = 1L;
	// configuracoes de tela
	final int tamanhoQuadradosInicial = 16; // 16x16 pixels (tamanho mais usados para criar jogos em 2D)
	final int escala = 3; 
	public final int tamanhoQuadrados = tamanhoQuadradosInicial * escala; // 16x3 = 48 -> na tela vai aparecer que um quadrado eh de 48x48 pixels
	
	// quantos quadrados podem ser mostrados na tela?
	public final int maxColunas = 16; // podem ter 36 quadrados de largura
	public final int maxLinhas = 12; // podem ter 18 quadrados de altura
	public final int larguraTela = tamanhoQuadrados * maxColunas; // 1728 pixels
	public final int alturaTela = tamanhoQuadrados * maxLinhas; // 864 pixels
	
	// configuracoes do mapa1
	public final int maxWorldCol = 30;
	public final int maxWorldRow = 30;
	public final int worldWith = tamanhoQuadrados * maxWorldCol;
	public final int worldHeight = tamanhoQuadrados * maxWorldRow;

	
	TileManager tileM = new TileManager(this);
	InputTeclado teclado = new InputTeclado();
	Thread gameThread;
	public Aventureiro aventureiro = new Aventureiro(this, teclado);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(larguraTela, alturaTela)); // panel size
		this.setBackground(Color.black); 
		this.setDoubleBuffered(true); // melhora a performance
		this.addKeyListener(teclado);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	int FPS = 60;
	
	// game loop
	@Override
	public void run() {
		double intervaloImpressao = 1000000000/FPS; // 0,016666 segundos
		double proxImpressao = System.nanoTime() + intervaloImpressao;
		
		while(gameThread != null) {
			// update -> fazer o update das informacoes do personagem
			update();
			
			// imprimir a tela com as informacoes apos o update
			repaint();
			
			// aloca 0,016666 segundos para a impressao
			try {
				double tempoRestante = proxImpressao - System.nanoTime();
				tempoRestante = tempoRestante/1000000; // transformar em milisegundos para 'Thread.sleep' aceitar
				
				Thread.sleep((long) tempoRestante); 
				
				proxImpressao += intervaloImpressao;
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		aventureiro.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		
		aventureiro.draw(g2);
		g2.dispose();
	}
}