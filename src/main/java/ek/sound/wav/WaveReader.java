package ek.sound.wav;

import java.io.File;

import ek.util.BinUtils;
import ek.util.BinaryReader;


public class WaveReader
{
    private BinaryReader rd;
    private byte[] b4Buf = new byte[4];
    private WaveFormat format = new WaveFormat();
    
    
    public WaveReader(File file) throws Exception
    {
        rd = new BinaryReader(file);
        readHeader();
    }
    
    
    public void close()
    {
        rd.close();
    }
    

    public short readShort() throws Exception
    {
        return rd.readShort();
    }
    
    
    public WaveFormat getFormat()
    {
        return format;
    }

    
    private void readHeader() throws Exception
    {
        rd.readBuf(b4Buf);
        if(!BinUtils.equals("RIFF", b4Buf)) throw new Exception("Invalid file format");

        rd.skip(4);     // chunk size
        
        rd.readBuf(b4Buf);
        if(!BinUtils.equals("WAVE", b4Buf)) throw new Exception("Invalid file format");

        readFormatChunk();
        seekDataChunk();
    }
    
    
    private void readFormatChunk() throws Exception
    {
        rd.readBuf(b4Buf);
        if(!BinUtils.equals("fmt ", b4Buf)) throw new Exception("Missing format chunk");

        int chunkSize = rd.readInt();
        
        format.format = rd.readShort();
        format.numChannels = rd.readShort();
        format.samplesPerSec = rd.readInt();
        rd.skip(4);   // Avg bytes per sec
        format.sampleSize = rd.readShort();
        format.bitsPerSample = rd.readShort();
        
        if(chunkSize == 16) return;
        
        short extSize = rd.readShort();
        if(extSize == 0) return;
        if(extSize == 22) 
        {
            rd.skip(22);
        }
        else
        {
            throw new Exception("Invalid format chunk");
        }
    }

    
    private void seekDataChunk() throws Exception
    {
        byte[] nameBuf = new byte[4];
        
        while(true)
        {
            rd.readBuf(nameBuf);
            int chunkSize = rd.readInt();
            
            if(BinUtils.equals("data", nameBuf))
            {
                format.numSamples = chunkSize / format.sampleSize;
                return;
            }
            
            rd.skip(chunkSize);
        }
    }
    
}
