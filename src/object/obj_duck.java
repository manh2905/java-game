package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class obj_duck extends MainObject {
	public BufferedImage[] duckAni;
	public BufferedImage img;
	public obj_duck () {
		name = "Duck";
		importImg();
		loadAnimation(img);

		collision = true;

	}
	private void importImg() {
		name = "Duck";
		InputStream is = getClass().getResourceAsStream("/player/player_sprite.png");
		try {
			img = ImageIO.read(is);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void loadAnimation(BufferedImage img) {
		duckAni = new BufferedImage[6];
		int index = 0;
		for (int i = 3; i < 9; i++) {
			duckAni[index] = img.getSubimage(i * 64, 4 * 64, 64, 64);
			index++;
		}
	}

}
