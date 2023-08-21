import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.JPanel;
import javax.imageio.ImageIO; 
import java.awt.event.*; 
import java.lang.*; 
import java.awt.Graphics;

public class menuPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private BufferedImage img = null;    // img is a object of buffered image.
    // java buffered image class is a subclass of image class.It is used to handle and manipulate the data.
    
    public boolean StartingPoint = false;  // when we click anywhere on the screen it going to change the startingPoint to true.
    // so that next interface will open to run the game.
    
    public menuPanel()  // constructor
    {
        // Load image
        LoadImage();
        // Handle a mouse click event 
        this.addMouseListener(new MouseAdapter()  // we are using the mouseListener bcoz when we click on the screen it'll open the new interface.
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);     // clicking anywhere on the screen.
                StartingPoint = true;     // when we click on the screen the startingPoint will become true.
            }
        }
        );
    }
    private void LoadImage()
    {
        try {
            img = ImageIO.read(new File("C:\\Users\\HP\\Documents\\SloppyBird\\Images\\menupanel.png"));  // reading the image.
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g)  // g is the graphics object
    {
        super.paint(g);  // awt is using the call back mechanism for painting. 

        g.drawImage(img, 0, 0, gamePanel.WIDTH, gamePanel.HEIGHT, null);
        // we are using gamePanel.width becoz we have already fixed the value of width and height in gamePanel.
        // image is there and 0,0 shows the x and y coordinate and null si teh observer.
    }
}