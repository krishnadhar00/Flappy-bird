// we are going to setup the background image for gamePanel.

import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.awt.event.*; 
import java.lang.*;
import java.awt.Graphics;
import javax.swing.*;

public class gamePanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    public static boolean GameOver = false;
    public static int score=0; // this variable is used to count the score that how much wall he has passed.
    public static boolean starting= false;
    public static int proceed = -1;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    // creating the variable of x coordinate.
    private int xCoor = 0;
    // creating the object for bufferimage. 
    private BufferedImage img;

    birdImage bi = new birdImage(); // creating object of class birdImage to call the properties of birdImage.
    wallImage vi = new wallImage(gamePanel.WIDTH);  // creating the object of class wallImage to call the properties of the wallImage.
    wallImage vi2 = new wallImage(gamePanel.WIDTH+(gamePanel.WIDTH/2));

    public gamePanel()  // constructor.
    {
        LoadImage();
        // Handle the event which is going to mouse pressed event.
        // when we press the mouse the bird is going to move.
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);  //  difference between mouse clicked and mouse pressed.
                // when the mouse button is pressed and released after releasing the button then the event is going to occur
                // but in case of mouse pressed as soon as u pressed the button the event get fired regardless of releasing..
                bi.goUpwards();
            }
        });

    }

    private void LoadImage()
    {
        try {
            img = ImageIO.read(new File("C:\\Users\\HP\\Documents\\SloppyBird\\Images\\gamePanel.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // Note: with the help of paint method we are going to draw the image of the wall and birds.
    // using the paint method we just setup the background images.

    @Override
    public void paint(Graphics g)  // g is the graphics object
    {
        super.paint(g);  // awt is using the call back mechanism for painting. 

        g.drawImage(img, xCoor, 0, null);
        g.drawImage(img, xCoor+2400, 0, null);
        // we are using gamePanel.width becoz we have already fixed the value of width and height in gamePanel.
        // image is there and 0,0 shows the x and y coordinate and null is the observer.

        bi.drawBird(g);  // it will draw the bird image on the gamePanel.
        vi.drawWall(g);  // it will draw the wall image on the gamePanel.
        vi2.drawWall(g);

        g.setFont(new Font("Tahoma",Font.BOLD,40));
        g.drawString("Score "+score,WIDTH/2,100); 

        if(starting)
        {
           g.setFont(new Font("Tahoma",Font.BOLD,150)); 
           g.drawString(Integer.toString(proceed),WIDTH/2-75,250);
        }
    }

    public void Move()
    {
        bi.birdMovement();
        vi.wallMovement();
        vi2.wallMovement();

        if(GameOver)
        {
            vi.X=gamePanel.WIDTH;
            vi2.X=gamePanel.WIDTH+(gamePanel.WIDTH/2);
            GameOver = false;
        }
        xCoor+=wallImage.speed;  //-6,-6-6,-6-6-6,...-2400
        if(xCoor==-2400)
        {
            xCoor=0;
        }
        if(vi.X==birdImage.x || vi2.X==birdImage.x)
        {
            score+=1;
            // System.out.println(score);
        }
    }
    public static boolean popUpMessage()
    {
        int result = JOptionPane.showConfirmDialog(null,"Game Over, Your score is: "+score+"\n Do you want to restart the game?","Game Over", JOptionPane.YES_NO_OPTION);
        if(result==JOptionPane.YES_OPTION)
        {
            return true;
        }
        else
        {
            return false;
        }
    }   
}