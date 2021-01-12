package ek.util.sound;

import java.io.File;
import java.io.FileInputStream;

import com.google.common.io.LittleEndianDataInputStream;

import ek.util.CloseUtils;


public class WaveReader
{
    private LittleEndianDataInputStream is;
    private WaveHeader header;
    
    
    public WaveReader(File file) throws Exception
    {
        is = new LittleEndianDataInputStream(new FileInputStream(file));
        readHeader();
    }
    
    
    public void close()
    {
        CloseUtils.close(is);
    }
    
    
    public WaveHeader getHeader()
    {
        return header;
    }
    
    
    private void readHeader() throws Exception
    {
        String str = readAscii(4);
        if(!"RIFF".equals(str)) throw new Exception("Invalid file format");
        
        int chunkSize = is.readInt();
        
        str = readAscii(4);
        if(!"WAVE".equals(str)) throw new Exception("Invalid file format");

        readFormatChunk();
        seekDataChunk();
    }
    
    
    private String readAscii(int len) throws Exception
    {
        StringBuilder bld = new StringBuilder();
        
        for(int i = 0; i < len; i++)
        {
            char ch = (char)is.readByte();
            bld.append(ch);
        }
        
        return bld.toString();
    }
    
    
    private void readFormatChunk() throws Exception
    {
        String str = readAscii(4);
        if(!"fmt ".equals(str)) throw new Exception("Missing format chunk");

        header = new WaveHeader();
        
        int chunkSize = is.readInt();
        
        header.format = is.readShort();
        header.channels = is.readShort();
        header.samplesPerSec = is.readInt();
        is.readInt();   // nAvgBytesPerSec
        header.sampleSize = is.readShort();
        header.bitsPerSample = is.readShort();
        
        if(chunkSize == 16) return;
        
        short extSize = is.readShort();
        if(extSize == 0) return;
        if(extSize == 22) 
        {
            is.skip(22);
        }
        else
        {
            throw new Exception("Invalid format chunk");
        }
    }

    
    private void seekDataChunk() throws Exception
    {
        while(true)
        {
            String str = readAscii(4);
            int chunkSize = is.readInt();
            
            if("data".equals(str))
            {
                header.numSamples = chunkSize / header.sampleSize;
                return;
            }
            
            is.skip(chunkSize);
        }
    }
}
