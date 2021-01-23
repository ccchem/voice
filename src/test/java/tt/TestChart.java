package tt;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ek.ui.SoundPanel;


public class TestChart
{
    private static void createUI()
    {
        SoundPanel sp = new SoundPanel();
        
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(sp);
        
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args)
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                createUI();
            }
        };
        
        SwingUtilities.invokeLater(r);
    }

}
