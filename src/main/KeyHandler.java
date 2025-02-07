package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;
import static utilz.Constants.Directions.*;

public class KeyHandler implements KeyListener {
    private Player player;

    GamePanel gp;

    public KeyHandler(Player player, GamePanel gp) {

        this.player = player;
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState)
        {
            playState(code);
        }

    }

    public void playState(int code) {
        switch (code) {
            case KeyEvent.VK_W:
                player.setDirection(UP);
                player.setMoving(true);
                break;
            case KeyEvent.VK_S:
                player.setDirection(DOWN);
                player.setMoving(true);
                break;
            case KeyEvent.VK_A:
                player.setDirection(LEFT);
                player.setMoving(true);
                break;
            case KeyEvent.VK_D:
                player.setDirection(RIGHT);
                player.setMoving(true);
                break;
        }
    }

    public void titleState(int code)
    {
        //MAIN MENU
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1; // mission
                }
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }
        //SECOND SCREEN
        else if (gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_ENTER) {

                //BACK
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_S ||
            code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
            player.setMoving(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không sử dụng
    }
}
