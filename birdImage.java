import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import javax.swing.*;
// 

public class birdImage
{
    private BufferedImage img = null;
    private static int bird_dia = 36;    // diameter of the bird is 40.
    public static int x = (gamePanel.WIDTH/2)-bird_dia/2; // setting the width or x coordinate of the bird.
    public static int y = (gamePanel.HEIGHT/2); // setting the height or y coordinate the bird.

    private static int speed = 2;
    private int acce = 1;

    public birdImage()
    {
        LoadImage();
    }
    private void LoadImage()
    {
        try {
            img = ImageIO.read(new File("C:\\Users\\HP\\Documents\\SloppyBird\\Images\\bird1.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    } 

    public void drawBird(Graphics g)
    {
        g.drawImage(img, x, y, null);  // aproximately x coordinate is 280 and y coordinate is 400 of bird. 
    }

    public void birdMovement()
    {
        // In case of bird movement we are using only y coordinate.
        if(y>=0 && y<=gamePanel.HEIGHT)
        {
            speed += acce;  // speed = 3 , 4 , 5
            y += speed;     // y = y+3 => 400+3 =>403 , 400+ 3 +4, 400+3+4+5
        }
        else 
        {
            boolean option = gamePanel.popUpMessage();
            if(option)
            {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                reset();
            }
            else
            {
                // close the entire window
                JFrame frame = mainBird.getWindow();
                frame.dispose();     // it helps us to destroy the entire window.
                mainBird.timer.stop();   // it will stop the execution when we click on the no button.
            }
        }
    }
    public void goUpwards()
    {
        speed = -17;  // the bird go upward.
    }
    public static void reset()
    {
        speed = 2;
        y = gamePanel.HEIGHT/2;
        gamePanel.GameOver = true; 
        gamePanel.score=0;
    }
    public static Rectangle getBirdRect()
    {
        Rectangle birdRect = new Rectangle(x,y,bird_dia,35);
        return birdRect;
    }

}
