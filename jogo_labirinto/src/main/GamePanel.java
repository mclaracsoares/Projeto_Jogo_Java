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
    final int tamanhoQuadradosInicial = 16;
    final int escala = 3;
    public final int tamanhoQuadrados = tamanhoQuadradosInicial * escala;

    // quantos quadrados podem ser mostrados na tela?
    public final int maxColunas = 16;
    public final int maxLinhas = 12;
    public final int larguraTela = tamanhoQuadrados * maxColunas;
    public final int alturaTela = tamanhoQuadrados * maxLinhas;

    // configurações do mapa
    public final int maxWorldCol = 46;
    public final int maxWorldRow = 42;

    TileManager tileM = new TileManager(this);
    InputTeclado teclado = new InputTeclado();
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Aventureiro aventureiro = new Aventureiro(this, teclado);
    public SuperObject obj[] = new SuperObject[5];
    private UI ui;

    public GamePanel() {
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(teclado);
        this.setFocusable(true);

        ui = new UI(this);
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

    @Override
    public void run() {
        double intervaloImpressao = 1000000000 / FPS;
        double proxImpressao = System.nanoTime() + intervaloImpressao;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double tempoRestante = proxImpressao - System.nanoTime();
                tempoRestante = tempoRestante / 1000000;

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
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null ) {
                obj[i].draw(g2, this);
            }
        }

        // jogador
        aventureiro.draw(g2);

        // UI
        ui.draw(g2); // Desenha a interface do usuário
        g2.dispose();
    }

}
