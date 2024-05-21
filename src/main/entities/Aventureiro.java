package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.InputTeclado;

public class Aventureiro{
	public int x, y; // coordenadas do personagem
	public int velocidade; // velocidade do personagem
	public String direcao; // indicar a direcao em que o personagem esta se movendo
	public int contadorSprite = 0; // indicar a frequencia de alteracao do tipo de movimentacao
	public int numSprite = 1; // alterar o tipo de movimentacao (ex: cima1, cima2)
	
	public BufferedImage baixo1, baixo2, cima1, cima2, direita1, direita2, esquerda1, esquerda2;
	
	GamePanel gp;
	InputTeclado teclado;
	
	// construtor
	public Aventureiro(GamePanel gp, InputTeclado teclado) {
		this.gp =gp;
		this.teclado = teclado;
		setCoordenadasInicio();
		getSprites();
	}
	
	// onde o personagem vai iniciar no mapa
	public void setCoordenadasInicio() {
		x = 100;
		y = 100;
		velocidade = 4;
		direcao = "direita";
	}
	
	// imagens dos sprites para cada tipo de movimentacao
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

			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(teclado.esquerda == true || teclado.cima == true ||
		   teclado.baixo == true || teclado.direita == true) { // garante que o personagem nao troque de imagens sem comandos do usuario
		   
			if(teclado.esquerda == true) { // mover para esquerda
				direcao = "esquerda";
				x -= velocidade;
			}
			else if(teclado.cima == true) { // mover para cima
				direcao = "cima";
				y -= velocidade;
			}
			else if(teclado.baixo == true) { // mover para baixo
				direcao = "baixo";
				y += velocidade;
			}
			else if(teclado.direita == true) { // mover para direita
				direcao = "direita";
				x += velocidade;
			}
		
			// mudar o tipo da imagem para 'animar' o personagem
			contadorSprite++;
			if(contadorSprite > 20) { // o personagem vai mudar o tipo da imagem 1 vez a cada 20 FPS
				if(numSprite == 1) {
					numSprite = 2;
				}
				else if(numSprite == 2) {
					numSprite = 1;
				}
				contadorSprite = 0;	
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage sprite = null;
		
		// atribuir para a variavel sprite cada imagem para representar a animacao do personagem
		switch(direcao) {
		case "esquerda":
			if(numSprite == 1) {
				sprite = esquerda1;
			} else {
				sprite = esquerda2;
			}
			break;
		case "cima":
			if(numSprite == 1) {
				sprite = cima1;
			} else {
				sprite = cima2;
			}
			break;
		case "baixo":
			if(numSprite == 1) {
				sprite = baixo1;
			} else {
				sprite = baixo2;
			}
			break;
		case "direita":
			if(numSprite == 1) {
				sprite = direita1;
			} else {
				sprite = direita2;
			}
			break;
		}
		
		// drawImage -> desenha a imagem nas coordenadas x, y com o tamanhoQuadrados
		g2.drawImage(sprite, x, y, gp.tamanhoQuadrados, gp.tamanhoQuadrados, null);
	}
}