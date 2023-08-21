import java.util.*;   // contains classes or modules.
import java.io.*;     // input output operation in java 
import java.awt.image.BufferedImage;  // part of java.awt. provides classes for creating and manupulating gui.
// buffer image represents n image in memory.
import java.awt.*;  //java.awt. provides classes for creating and manupulating gui.  
import javax.swing.JPanel;  // contains button, text, labels,
import javax.imageio.ImageIO;   // read and write the various images.
import java.io.File;     // we can create ,rename,delete the file.    
import java.awt.Graphics;  // provides various shapes ,images and text.
import java.util.Random;  // it will take random value.
import javax.swing.*;   // provides classes for creating gui using swing toolkit.

public class wallImage
{
    private Random r = new Random();

    public int X;  // x coordinate for the wall.

    public int Y = r.nextInt(gamePanel.HEIGHT-400)+200;  // Y coordinate for the wall.
    // max y coordiante is 600. bcoz random value take value from 0 to 400 and then add 200 then it'll become 600.
    //  min value if 200 bcoz if random value is 0 then add 200 it will become 200 min. value.

    private int width_wall = 45; // width of the wall is 45.

    private int height = gamePanel.HEIGHT-Y;

    private int gap = 200;   // the gap between the walls is 200. gap is between the upper and bottom wall is 200
    public static int speed = -6;

    private BufferedImage img = null;

    public wallImage(int X)
    {
        this.X = X;   // getting the value of X coordinate from here.

        LoadImage();
    }

    private void LoadImage()
    {
        try {
            img = ImageIO.read(new File("C:\\Users\\HP\\Documents\\SloppyBird\\Images\\wall.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void drawWall(Graphics g)
    {
        g.drawImage(img, X, Y, null); // this is for the bottom wall and 
        g.drawImage(img, X, (-gamePanel.HEIGHT)+(Y-gap), null);  // this is for the upper one.
        // -800 + (600-200)
    }
    public void wallMovement()
    {
        // in wall case we are changing the x coordinate bcoz wall is moving.
        X+=speed;
        if(X <= -width_wall)
        {
            X=gamePanel.WIDTH;
            Y = r.nextInt(gamePanel.HEIGHT-400)+200;
            height = gamePanel.HEIGHT-Y;
        }
        Rectangle lowerRect = new Rectangle(X,Y,width_wall,height); 
        Rectangle uperRect = new Rectangle(X,0,width_wall,gamePanel.HEIGHT-(height+gap)); // 800-(height of lowerrect + gap) =>height of upperrect.

        if(lowerRect.intersects(birdImage.getBirdRect()) || uperRect.intersects(birdImage.getBirdRect()))
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
                birdImage.reset();
                wall_Reset();
            }
            else
            {
                // close the entire window
                JFrame frame = mainBird.getWindow();
                frame.dispose();
                mainBird.timer.stop();
            }
        }
    }
    private void wall_Reset()
    {
        // we are reseting the y values only.
        Y = r.nextInt(gamePanel.HEIGHT-400)+200; 
        height = gamePanel.HEIGHT-Y;
        // reset the x values also.
        gamePanel.GameOver = true;  // if it is true then we have to reset the x coordinate also.
    }
}