package entity;

import static utilz.Constants.Directions.STAND;

import java.awt.Rectangle;

public class Entity {
	public int EntityWorldX, EntityWorldY;
	public int speed;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionON = false;

    public int direction = STAND;
}
