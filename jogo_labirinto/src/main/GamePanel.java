package main;

import javax.swing.JPanel;
import entities.Aventureiro;
import object.SuperObject;
import tile.TileManager;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    // configurações de tela
    final int tamanhoQuadradosInicial = 16; // 16x16 pixels (tamanho mais usados para criar jogos em 2D)
    final int escala = 3;
    public final int tamanhoQuadrados = tamanhoQuadradosInicial * escala; // 16x3 = 48 -> na tela vai aparecer que um quadrado é de 48x48 pixels

    // quantos quadrados podem ser mostrados na tela?
    public final int maxColunas = 16; // podem ter 16 quadrados de largura
    public final int maxLinhas = 12; // podem ter 12 quadrados de altura
    public final int larguraTela = tamanhoQuadrados * maxColunas; // 768 pixels
    public final int alturaTela = tamanhoQuadrados * maxLinhas; // 576 pixels

    // configurações do mapa
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 30;

    TileManager tileM = new TileManager(this);
    InputTeclado teclado = new InputTeclado();
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Aventureiro aventureiro = new Aventureiro(this, teclado);
    public SuperObject obj[] = new SuperObject[10];
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(larguraTela, alturaTela)); // panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // melhora a performance
        this.addKeyListener(teclado);
        this.setFocusable(true);
    }
    public void setupGame() {
    	
    	aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public CollisionChecker checker = new CollisionChecker(this);
    int FPS = 60;
	private int i;

    // game loop
    @Override
    public void run() {
        double intervaloImpressao = 1000000000 / FPS; // 0,016666 segundos
        double proxImpressao = System.nanoTime() + intervaloImpressao;

        while (gameThread != null) {
            // update -> fazer o update das informações do personagem
            update();

            // imprimir a tela com as informações após o update
            repaint();

            // aloca 0,016666 segundos para a impressão
            try {
                double tempoRestante = proxImpressao - System.nanoTime();
                tempoRestante = tempoRestante / 1000000; // transformar em milissegundos para 'Thread.sleep' aceitar

                Thread.sleep((long) tempoRestante);

                proxImpressao += intervaloImpressao;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        aventureiro.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        // tile
        tileM.draw(g2);
       
        //object
        for(int i = 0; i < obj.length; i++) {}
			if(obj[i] != null ) {
        		obj[i].draw(g2, this);
        	}
        
        // jogador
        aventureiro.draw(g2);
        g2.dispose();
    }
}
