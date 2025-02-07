package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainObject {
	 public BufferedImage image; 
	 public String name;
	 public boolean collision = false;
	 public int worldX, worldY;
	 public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	 public int solidAreaDefaultX = 0;
	 public int solidAreaDefaultY = 0;
	 public void draw (Graphics2D g2, GamePanel gp,  BufferedImage subImg) {
		g2.setColor(Color.white);
		int screenX = worldX - gp.player.EntityWorldX + gp.player.screenX;
		int screenY = worldY - gp.player.EntityWorldY + gp.player.screenY;


		g2.drawImage(subImg, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
