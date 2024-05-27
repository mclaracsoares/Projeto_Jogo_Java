package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.GamePanel;

public class UI {

    private GamePanel gp;
    private Font Onyx_35;
    private BufferedImage chaveImage; // Adicione um campo para armazenar a imagem da chave

    public UI(GamePanel gp) {
        this.gp = gp;

        // Carrega a fonte
        Onyx_35 = new Font("Onyx", Font.BOLD, 35);

        // Carrega a imagem da chave
        try {
            chaveImage = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
            chaveImage = resizeImage(chaveImage, 60, 60); // Redimensiona a imagem da chave para 35x35 pixels
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Desenha a imagem da chave
        if (chaveImage != null) {
            g2.drawImage(chaveImage, 7, 10, null); // Ajuste as coordenadas conforme necessário
        }

        // Desenha o número de chaves
        g2.setFont(Onyx_35);
        g2.setColor(Color.WHITE);
        g2.drawString(" X " + gp.aventureiro.temChave, 70, 50); // Desenha o número de chaves obtidas
    }

    // Método para redimensionar a imagem
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }
}
