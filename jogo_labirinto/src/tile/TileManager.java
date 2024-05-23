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
	Tile[] tile;
	int mapTileNum[][];
	
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
		
		}catch(IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void carregarMapa(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath); // ler o arquivo de texto para representar cada tile do mapa
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" "); // espacar numeros
					int num = Integer.parseInt(numbers[col]); // mudar de string p int
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tamanhoQuadrados;
			int worldY = worldRow * gp.tamanhoQuadrados;
			int screenX = worldX - gp.aventureiro.mapaX + gp.aventureiro.screenX;
			int screenY = worldY - gp.aventureiro.mapaY + gp.aventureiro.screenY;

			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tamanhoQuadrados, gp.tamanhoQuadrados, null);
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
				
			}
			
		}

	}
}