package tt;

import java.io.File;
import ek.util.sound.WaveReader;


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
        rd.printInfo();
        
        rd.close();
    }

    
}
