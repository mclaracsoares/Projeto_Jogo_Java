package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        carregarMapa("/mapas/mapa1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Areia.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Pedra.png"));
            tile[1].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarMapa(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tamanhoQuadrados;
            int worldY = worldRow * gp.tamanhoQuadrados;
            int screenX = worldX - gp.aventureiro.mapaX + gp.aventureiro.screenX;
            int screenY = worldY - gp.aventureiro.mapaY + gp.aventureiro.screenY;

            if (worldX + gp.tamanhoQuadrados > gp.aventureiro.mapaX - gp.aventureiro.screenX &&
                    worldX - gp.tamanhoQuadrados < gp.aventureiro.mapaX + gp.aventureiro.screenX &&
                    worldY + gp.tamanhoQuadrados > gp.aventureiro.mapaY - gp.aventureiro.screenY &&
                    worldY - gp.tamanhoQuadrados < gp.aventureiro.mapaY + gp.aventureiro.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tamanhoQuadrados, gp.tamanhoQuadrados, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
