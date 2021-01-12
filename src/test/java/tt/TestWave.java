package tt;

import java.io.File;

import ek.util.sound.WaveHeader;
import ek.util.sound.WaveReader;


public class TestWave
{
    public static void main(String[] args) throws Exception
    {
        File file = new File("/ws4/Jap/news/snow/snow2.wav");
        
        WaveReader rd = new WaveReader(file);
        WaveHeader hdr = rd.getHeader();
        hdr.print();
        
        rd.close();
    }

    
}
