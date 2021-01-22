package tt;

import java.io.File;

import ek.sound.wav.WaveFormat;
import ek.sound.wav.WaveReader;


public class TestWave
{
    public static void main(String[] args) throws Exception
    {
        test1();
    }


    public static void test1() throws Exception
    {
        File file = new File("/ws4/Jap/news/snow/snow2.wav");
        
        WaveReader rd = new WaveReader(file);
        WaveFormat format = rd.getFormat();
        format.print();
        
        for(int i = 0; i < 10; i++)
        {
            System.out.println(rd.readShort() + ", " + rd.readShort());
        }
        
        rd.close();
    }

    
}
