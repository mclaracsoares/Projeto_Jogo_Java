package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
	
	// configuracoes de tela
	final int tamanhoQuadradosInicial = 16; // 16x16 pixels (tamanho mais usados para criar jogos em 2D)
	final int escala = 3; 
	final int tamanhoQuadrados = tamanhoQuadradosInicial * escala; // 16x3 = 48 -> na tela vai aparecer que um quadrado eh de 48x48 pixels
	
	// quantos quadrados podem ser mostrados na tela?
	final int maxColunas = 36; // podem ter 36 quadrados de largura
	final int maxLinhas = 18; // podem ter 18 quadrados de altura
	final int larguraTela = tamanhoQuadrados * maxColunas; // 1728 pixels
	final int alturaTela = tamanhoQuadrados * maxLinhas; // 864 pixels
	
	InputTeclado teclado = new InputTeclado();
	Thread gameThread;
	
	// settar a posicao inicial do personagem -> o canto superior esquerdo vale 0:0
	int personagemX = 100;
	int personagemY = 100;
	int velocidadePersonagem = 4;
	
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
		if(teclado.esquerda == true) { // mover para esquerda
			personagemX -= velocidadePersonagem;
		}
		else if(teclado.cima == true) { // mover para cima
			personagemY -= velocidadePersonagem;
		}
		else if(teclado.baixo == true) { // mover para baixo
			personagemY += velocidadePersonagem;
		}
		else if(teclado.direita == true) { // mover para direita
			personagemX += velocidadePersonagem;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(personagemX, personagemY, tamanhoQuadrados, tamanhoQuadrados);
		g2.dispose();
	}
}
