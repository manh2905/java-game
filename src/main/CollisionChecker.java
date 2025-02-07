package main;

import entity.Entity;
import static utilz.Constants.Directions.*;
public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile (Entity entity) {
		
		int entityLeftWorldX = entity.EntityWorldX + entity.solidArea.x;
		int entityRightWorldX = entity.EntityWorldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.EntityWorldY + entity.solidArea.y;
		int entityBottomWorldY = entity.EntityWorldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		
		// khi nvat di chuyển bất cứ hướng nào
		// luôn có 2 thông số kiểm tra va chạm
		// VD: đi lên có vai trái vai phải
		// đi xuống có chân trái chân phải
		int tileNum1, tileNum2; 
		
		switch (entity.direction) {
		case UP:
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case DOWN:
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
            break;
        case LEFT:
        	entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
            break;
        case RIGHT:
        	entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
            break;
		default:
			break;
		}
	}
	public int checkObject (Entity entity, boolean player) {
		int index = 999;

		for (int i = 0 ; i < gp.obj.length ; i++) {
			if(gp.obj[i] != null ) {
				//tra ve vi tri o vuong cua doi tuong
				entity.solidArea.x = entity.EntityWorldX + entity.solidArea.x;
				entity.solidArea.y = entity.EntityWorldY + entity.solidArea.y;

				//tra ve vi tri o vuong cua vat the
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				switch (entity.direction) {
					case UP :
						entity.solidArea.y -= entity.speed;
						// su dung phuong thuc intersects de kiem tra 2 vung cua nhan vat va vat the co giao nhau khong
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision = true) {
								entity.collisionON = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case DOWN:
						entity.solidArea.y += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision = true) {
								entity.collisionON = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case LEFT:
						entity.solidArea.x -= entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision = true) {
								entity.collisionON = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case RIGHT:
						entity.solidArea.x += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision = true) {
								entity.collisionON = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}

		return index;
	}
}
