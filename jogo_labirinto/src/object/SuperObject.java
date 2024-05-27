package object;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,16,16);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        if (image != null) {
            int screenX = worldX - gp.aventureiro.mapaX + gp.aventureiro.screenX;
            int screenY = worldY - gp.aventureiro.mapaY + gp.aventureiro.screenY;

            if (worldX + gp.tamanhoQuadrados > gp.aventureiro.mapaX - gp.aventureiro.screenX &&
                    worldX - gp.tamanhoQuadrados < gp.aventureiro.mapaX + gp.aventureiro.screenX &&
                    worldY + gp.tamanhoQuadrados > gp.aventureiro.mapaY - gp.aventureiro.screenY &&
                    worldY - gp.tamanhoQuadrados < gp.aventureiro.mapaY + gp.aventureiro.screenY) {
                g2.drawImage(image, screenX, screenY, gp.tamanhoQuadrados, gp.tamanhoQuadrados, null);
            }
        }
    }
}
