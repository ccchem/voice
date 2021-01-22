package tt;

import java.io.File;

import ek.sound.ogg.OggReader;


public class TestOgg
{

    public static void main(String[] args) throws Exception
    {
        File file = new File("/tmp/ame.ogg");
        OggReader rd = new OggReader(file);
        
        
        rd.close();
    }

}
