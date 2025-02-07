package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import static utilz.Constants.Directions.*;
public class Player extends Entity {
    private GamePanel gp;
    public boolean moving = false;
    public BufferedImage img;

	public int numberKey = 0;

	public BufferedImage[] rightAni;
	public BufferedImage[] leftAni;
	public BufferedImage[] upAni;
	public BufferedImage[] downAni;
	public BufferedImage[] idleAni;
	
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp) {
        this.gp = gp;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;
        

        setDefaultValues();
        importImg();
        loadAninmations(img);

    }

    public void setDefaultValues() {
    	EntityWorldX = gp.tileSize * 23;
    	EntityWorldY = gp.tileSize * 21;
        speed = 3;
        System.out.println(EntityWorldX+ " "+ EntityWorldY);
    }
    public int getDirection() {
        return this.direction;
    }
 // Setter và Getter cho hướng và trạng thái
    public void setDirection(int direction) {
        this.direction = direction;
    }


    public void setMoving(boolean moving) {
       this.moving = moving;
    }

    

    public void update() {
            // Kiểm tra vật cản
            collisionON = false;
            gp.collisionChecker.checkTile(this);

			// kiem tra va cham vat the
			int objindex = gp.collisionChecker.checkObject(this , true);
			pickUpObject(objindex);

            // Nếu kiểm tra vật cản là FALSE, nhân vật có thể di chuyển
            if (collisionON == false) {
            	if (moving) {
                    switch (direction) {
                        case UP:
                        	EntityWorldY -= speed;
                            break;
                        case DOWN:
                        	EntityWorldY += speed;
                            break;
                        case LEFT:
                        	EntityWorldX -= speed;
                            break;
                        case RIGHT:
                        	EntityWorldX += speed;
                            break;
                        default:
                            break;
                    }
            }
            // Nếu kiểm tra vật cản là TRUE thì không thể
        }
    }
    private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player/player_sprite.png");
//		C:\Users\FPT-HIEU\WorkSpace\Java\_My2DGame\sprite\player_sprite.png
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    public void loadRightAni(BufferedImage img) {
		rightAni = new BufferedImage[10];
		int index = 0;
		for (int i = 1; i < 11; i++) {
			rightAni[index] = img.getSubimage(i * 64, 0 * 64, 64, 64);
			index++;
		}
	}
	public void loadLeftAni(BufferedImage img) {
		leftAni = new BufferedImage[11];
		int index = 0;
		for (int i = 0; i < 11; i++) {
			leftAni[index] = img.getSubimage(i * 64, 1 * 64, 64, 64);
			index++;
		}
	}
	public void loadUpAni(BufferedImage img) {
		upAni = new BufferedImage[11];
		int index = 0;
		for (int i = 3; i < 11; i++) {
			upAni[index] = img.getSubimage(i * 64, 3 * 64, 64, 64);
			index++;
		}
		for (int i = 0; i < 3; i++) {
			upAni[index] = img.getSubimage(i * 64, 4 * 64, 64, 64);
			index++;
			
		}
	}
	public void loadDownAni(BufferedImage img) {
		
		downAni = new BufferedImage[14];
		int index = 0;
		for (int i = 0; i < 11; i++) {
			downAni[index] = img.getSubimage(i * 64, 2 * 64, 64, 64);
			index++;
		}
		for (int i = 0; i < 3; i++) {
			downAni[index] = img.getSubimage(i * 64, 3 * 64, 64, 64);
			index++;
		}
	}
	private void loadAninmations(BufferedImage img) {
		// width 56, height 64
		loadRightAni(img);
		loadLeftAni(img);
		loadUpAni(img);
		loadDownAni(img);
	}

	public void pickUpObject (int i) {
		if(i != 999 ) {
			gp.playSE(1);
			gp.obj[i] = null;
			numberKey++;
			if (numberKey == 10) {
				gp.gameState = gp.endState;
				numberKey = 0;
			}
			System.out.println(numberKey);
		}
	}
    public void draw(Graphics2D g2, BufferedImage subImg) {
        g2.setColor(Color.white);
        g2.drawImage(subImg, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
