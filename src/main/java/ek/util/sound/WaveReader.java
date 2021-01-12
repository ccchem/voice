package ek.util.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import ek.util.CloseUtils;


public class WaveReader
{
    private byte[] b4Buf = new byte[4];
    private byte[] b2Buf = new byte[2];
    
    private InputStream is;

    private short format;
    private short numChannels;
    private int samplesPerSec;
    private short sampleSize;
    private short bitsPerSample;
    private int numSamples;
    
    
    public WaveReader(File file) throws Exception
    {
        is = new FileInputStream(file);
        readHeader();
    }
    
    
    public void close()
    {
        CloseUtils.close(is);
    }
    

    public short getFormat()
    {
        return format;
    }


    public short getNumChanels()
    {
        return numChannels;
    }


    public short getSampleSize()
    {
        return sampleSize;
    }

    
    public int getNumSamples()
    {
        return numSamples;
    }

    
    public short getBitsPerSample()
    {
        return bitsPerSample;
    }


    public int getSamplesPerSec()
    {
        return samplesPerSec;
    }

    
    public float getDurationSec()
    {
        return (float)numSamples / (float)samplesPerSec;
    }


    public void printInfo()
    {
        System.out.println("            Format:  " + getFormatString(format));
        System.out.println("          Channels:  " + numChannels);
        System.out.println("Sample size, bytes:  " + sampleSize);
        System.out.println("Samples per second:  " + samplesPerSec);
        System.out.println("   Bits per sample:  " + bitsPerSample);
        System.out.println(" Number of samples:  " + numSamples);
        System.out.format(" Duration, seconds:  %.2f\n", getDurationSec());
    }
    
    
    private static String getFormatString(short format)
    {
        switch(format)
        {
        case 1:
            return "PCM";
        }
        
        return String.valueOf(format);
    }

    
    private void readHeader() throws Exception
    {
        readBuf(b4Buf);
        if(!equals("RIFF", b4Buf)) throw new Exception("Invalid file format");

        is.skip(4);     // chunk size
        
        readBuf(b4Buf);
        if(!equals("WAVE", b4Buf)) throw new Exception("Invalid file format");

        readFormatChunk();
        seekDataChunk();
    }
    
    
    private void readFormatChunk() throws Exception
    {
        readBuf(b4Buf);
        if(!equals("fmt ", b4Buf)) throw new Exception("Missing format chunk");

        int chunkSize = readInt();
        
        format = readShort();
        numChannels = readShort();
        samplesPerSec = readInt();
        is.skip(4);   // nAvgBytesPerSec
        sampleSize = readShort();
        bitsPerSample = readShort();
        
        if(chunkSize == 16) return;
        
        short extSize = readShort();
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
        byte[] nameBuf = new byte[4];
        
        while(true)
        {
            readBuf(nameBuf);
            int chunkSize = readInt();
            
            if(equals("data", nameBuf))
            {
                numSamples = chunkSize / sampleSize;
                return;
            }
            
            is.skip(chunkSize);
        }
    }
    
    
    private void readBuf(byte[] buf) throws Exception
    {
        int rs = is.read(buf);
        if(rs != buf.length) throw new Exception("Read error");
    }
    
    
    public int readInt() throws Exception
    {
        readBuf(b4Buf);
        
        return (b4Buf[0] & 0xFF) | ((b4Buf[1] & 0xFF) << 8) 
                | ((b4Buf[2] & 0xFF) << 16 ) | ((b4Buf[3] & 0xFF) << 24 );
    }


    public short readShort() throws Exception
    {
        readBuf(b2Buf);
        
        return (short)((b2Buf[0] & 0xFF) | ((b2Buf[1] & 0xFF) << 8));
    }

    
    private boolean equals(String str, byte[] buf)
    {
        if(str.length() != buf.length) return false;
        
        for(int i = 0; i < str.length(); i++)
        {
            if((byte)str.charAt(i) != buf[i]) return false;
        }
        
        return true;
    }
}
