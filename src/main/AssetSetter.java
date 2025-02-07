package main;

import object.obj_duck;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {
        gp.obj[0] = new obj_duck();
        gp.obj[0].worldX = 30 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new obj_duck();
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 38 * gp.tileSize;

        gp.obj[2] = new obj_duck();
        gp.obj[2].worldX = 9 * gp.tileSize;
        gp.obj[2].worldY = 40 * gp.tileSize;

        gp.obj[3] = new obj_duck();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;

        gp.obj[4] = new obj_duck();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 31 * gp.tileSize;

        gp.obj[5] = new obj_duck();
        gp.obj[5].worldX = 30 * gp.tileSize;
        gp.obj[5].worldY = 29 * gp.tileSize;

        gp.obj[6] = new obj_duck();
        gp.obj[6].worldX = 22 * gp.tileSize;
        gp.obj[6].worldY = 38 * gp.tileSize;

        gp.obj[7] = new obj_duck();
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 9 * gp.tileSize;

        gp.obj[8] = new obj_duck();
        gp.obj[8].worldX = 12 * gp.tileSize;
        gp.obj[8].worldY = 21 * gp.tileSize;

        gp.obj[9] = new obj_duck();
        gp.obj[9].worldX = 36 * gp.tileSize;
        gp.obj[9].worldY = 21 * gp.tileSize;


    }
}
