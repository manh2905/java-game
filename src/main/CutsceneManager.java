package main;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    public Font maruMonica;

    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //Scene Number
    public final int NA = 0;



    public CutsceneManager(GamePanel gp)
    {
        this.gp = gp;
        try
        {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
        endCredit = "\n\n\n\n\n\n\n"
                + "----------------\n"
                + "Developed by\n"
                + "Vũ Tiến Mạnh B22DCPT163\n"
                + "Điêu Chính Hiếu B22DCPT087\n"
                + "----------------"
                + "\n\n\n\n\n\n"
                + "Bài Tập Lớn\n\n"
                + "Môn Học : Ngôn ngữ lập trình JAVA\n"
                + "\n\n\n\n\n\n"
                + "Thank you for playing!";
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        scene_ending();

    }
    public void scene_ending() {
        if(scenePhase == 0)
        {
            if(counterReached(100) == true)
            {
                scenePhase++;
            }
        }
        if(scenePhase == 1)
        {
            //The screen gets darker
            alpha += 0.05f;
            if (alpha > 1f){
                alpha = 1f;
            }

            drawBlackBackground(alpha);

            if(alpha == 1f)
            {
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 2)
        {
            drawBlackBackground(1f);
            alpha += 0.05f;
            if (alpha > 1f){
                alpha = 1f;
            }
            String text = "Chúc mừng !\n"
                          +"Bạn đã hoàn thành trò chơi.\n";

            drawString(alpha, 45f, gp.screenHeight/2, text, 70);

            if(counterReached(300) == true )
            {
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 3)
        {
            drawBlackBackground(1f);

            drawString(1f,45f, gp.screenHeight/2, "Duck Collector", 40);

            if(counterReached(200) == true)
            {
                scenePhase++;
                alpha = 0;
            }
        }
        if(scenePhase == 4)
        {
            //First Credits
            drawBlackBackground(1f);

            y = gp.screenHeight/2;

            drawString1(alpha, 38f,  y, endCredit, 40);

            if(counterReached(200) == true )
            {
                scenePhase++;
            }
        }
        if(scenePhase == 5)
        {
            drawBlackBackground(1f);

            //Scrolling the credit
            y--;
            drawString1(1f, 38f,  y, endCredit, 40);
            if(counterReached(1220) == true)
            {
                //Reset
                sceneNum = NA;
                scenePhase = 0;
                gp.sound.stop();

                //Transition to game again
                gp.gameState = gp.titleState;
                gp.resetGame(true);

            }
        }
    }

    public boolean counterReached(int target)
    {
        boolean counterReached = false;
        counter++;
        if(counter > target)
        {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackground(float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n"))
        {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x-15, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString1(float alpha, float fontSize, int y, String text, int lineHeight)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n"))
        {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
