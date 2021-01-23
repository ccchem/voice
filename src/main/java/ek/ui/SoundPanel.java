package ek.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SoundPanel extends JPanel
{
    private RenderingHints rh;
 
    
    public SoundPanel()
    {
        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }


    private void doDrawing(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHints(rh);

        g2.setPaint(Color.blue);

        int w = getWidth();
        int h = getHeight();

        g2.drawLine(0, 0, w, h);
    }

}
