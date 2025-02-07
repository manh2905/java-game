package utilz;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class  TileManager {
	public Tile[] tile;
	GamePanel gp;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/map/worldV2.txt");
	}
	
	public void getTileImage() {
		setup(0, "grass", false);
		setup(1, "grass", false);
		setup(2, "grass", false);
		setup(3, "grass", false);
		setup(4, "grass", false);
		setup(5, "grass", false);
		setup(6, "grass", false);
		setup(7, "grass", false);
		setup(8, "grass", false);
		setup(9, "grass", false);

		//
		setup(10, "Untitled", false);
		setup(11, "Untitled", false);
		setup(12, "water00", true);
		setup(13, "water", true);
		setup(14, "water02", true);
		setup(15, "water03", true);
		setup(16, "water04", true);
		setup(17, "water05", true);
		setup(18, "water06", true);
		setup(19, "water07", true);
		setup(20, "water08", true);
		setup(21, "water09", true);
		setup(22, "water10", true);
		setup(23, "water11", true);
		setup(24, "water12", true);
		setup(25, "water13", true);
		setup(26, "road00", false);
		setup(27, "road01", false);
		setup(28, "road02", false);
		setup(29, "road03", false);
		setup(30, "road04", false);
		setup(31, "road05", false);
		setup(32, "road06", false);
		setup(33, "road07", false);
		setup(34, "road08", false);
		setup(35, "road09", false);
		setup(36, "road10", false);
		setup(37, "road11", false);
		setup(38, "road12", false);
		setup(39, "soil", false);
		setup(40, "wall", true);
		setup(41, "tree", true);
	}

	public void setup(int index, String imgPath, boolean collision) {
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imgPath + ".png"));
			tile[index].collision = collision;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {
		try {
		InputStream is = getClass().getResourceAsStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			try {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.EntityWorldX + gp.player.screenX;
			int screenY = worldY - gp.player.EntityWorldY + gp.player.screenY;


			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			worldCol++;
			
			
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
			
				worldRow++;
			}
		}
	}
}
