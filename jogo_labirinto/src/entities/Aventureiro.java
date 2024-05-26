package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.InputTeclado;

public class Aventureiro {
    public int mapaX, mapaY; // coordenadas do personagem
    public int velocidade; // velocidade do personagem
    public String direcao; // indicar a direcao em que o personagem está se movendo
    public int contadorSprite = 0; // indicar a frequência de alteração do tipo de movimentação
    public int numSprite = 1; // alterar o tipo de movimentação (ex: cima1, cima2)

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public BufferedImage baixo1, baixo2, cima1, cima2, direita1, direita2, esquerda1, esquerda2;

    public final int screenX;
    public final int screenY;

    GamePanel gp;
    InputTeclado teclado;

    // construtor
    public Aventureiro(GamePanel gp, InputTeclado teclado) {
        this.gp = gp;
        this.teclado = teclado;

        screenX = gp.larguraTela / 2 - (gp.tamanhoQuadrados / 2);
        screenY = gp.alturaTela / 2 - (gp.tamanhoQuadrados / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;

        setCoordenadasInicio();
        getSprites();
    }

    // onde o personagem vai iniciar no mapa
    public void setCoordenadasInicio() {
        mapaX = gp.tamanhoQuadrados * 6;
        mapaY = gp.tamanhoQuadrados * 6;
        velocidade = 4;
        direcao = "direita";
    }

    // imagens dos sprites para cada tipo de movimentação
    public void getSprites() {
        try {
            baixo1 = ImageIO.read(getClass().getResourceAsStream("/sprites/Baixo1.png"));
            baixo2 = ImageIO.read(getClass().getResourceAsStream("/sprites/Baixo2.png"));
            cima1 = ImageIO.read(getClass().getResourceAsStream("/sprites/Cima1.png"));
            cima2 = ImageIO.read(getClass().getResourceAsStream("/sprites/Cima2.png"));
            direita1 = ImageIO.read(getClass().getResourceAsStream("/sprites/Direita1.png"));
            direita2 = ImageIO.read(getClass().getResourceAsStream("/sprites/Direita2.png"));
            esquerda1 = ImageIO.read(getClass().getResourceAsStream("/sprites/Esquerda1.png"));
            esquerda2 = ImageIO.read(getClass().getResourceAsStream("/sprites/Esquerda2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (teclado.esquerda || teclado.cima || teclado.baixo || teclado.direita) { // garante que o personagem não troque de imagens sem comandos do usuário
            if (teclado.esquerda) {
                direcao = "left";
            } else if (teclado.cima) {
                direcao = "up";
            } else if (teclado.baixo) {
                direcao = "down";
            } else if (teclado.direita) {
                direcao = "right";
            }

            collisionOn = false;
            gp.checker.checkTile(this);

            if (!collisionOn) {
                switch (direcao) {
                    case "up":
                        mapaY -= velocidade;
                        break;
                    case "down":
                        mapaY += velocidade;
                        break;
                    case "left":
                        mapaX -= velocidade;
                        break;
                    case "right":
                        mapaX += velocidade;
                        break;
                }
            }

            contadorSprite++;
            if (contadorSprite > 20) {
                numSprite = (numSprite == 1) ? 2 : 1;
                contadorSprite = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage sprite = null;

        switch (direcao) {
            case "left":
                sprite = (numSprite == 1) ? esquerda1 : esquerda2;
                break;
            case "up":
                sprite = (numSprite == 1) ? cima1 : cima2;
                break;
            case "down":
                sprite = (numSprite == 1) ? baixo1 : baixo2;
                break;
            case "right":
                sprite = (numSprite == 1) ? direita1 : direita2;
                break;
        }

        g2.drawImage(sprite, screenX, screenY, gp.tamanhoQuadrados, gp.tamanhoQuadrados, null);
    }
}
