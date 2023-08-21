import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Menu;
import javax.swing.JFrame;   // inbuilt library.
// import java.awt.event;
import java.awt.event.*;

public class mainBird
{ 
    // window 
    // 1st pannel
    // 2nd pannel
    public static JFrame window;   // declaring the variable window as privately.
    public static Timer timer,timer2;     // declaring the variable timer for Timer.
    private int proceed = 4;

    public mainBird()   // constructor. 
    {
        window = new JFrame();   // initiating the window.
        // setting the features of the window.

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // it allows us to terminate the window.
        window.setSize(gamePanel.WIDTH,gamePanel.HEIGHT);                                 // width , height
        window.setLocationRelativeTo(null);                      // it will popup the window in the middle.
        window.setTitle("Flappy Bird");
        window.setResizable(false);     // cannot resize it.
        // window.setVisible(true);     // we can see the window when we run.

    }

    private void rendering()
    {
        // creating the object of respective classes.
        menuPanel mp = new menuPanel();
        gamePanel gp = new gamePanel();

        // timer variable is required here.
        timer = new Timer(20,new ActionListener()  // after 20 milisecond  below code will run.
        {
            // every 20 seconds it happening/calling again and again.
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gp.repaint();    // repaint means again and again it will run.   
                gp.Move();       // to move the walls of game. 
            }
        });

        window.add(mp);
        window.setVisible(true);

        while(mp.StartingPoint == false)
        {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        window.remove(mp);  // when startingPoint become true then we have to remove the menuPanel
        //  and we have to add the gamePAnel.
        window.add(gp);
        gp.setVisible(true);
        window.revalidate();

        // timer.start();
        timer2 = new Timer(1000,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                proceed--; // 4-1=3
                gamePanel.proceed=proceed;
                gamePanel.starting=true;
                gp.repaint();
                if(proceed==0)
                {
                    timer2.stop();
                    timer.start();
                    gamePanel.starting=false;
                }
            }
        });
        timer2.start();
    }

    public static JFrame getWindow()
    {
        return window;
    }

    public static void main(String[] args) {
        mainBird mb = new mainBird();
        mb.rendering();
        
    }
}
