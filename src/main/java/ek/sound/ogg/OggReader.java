package ek.sound.ogg;

import java.io.File;

import ek.util.BinUtils;
import ek.util.BinaryReader;


public class OggReader
{
    private BinaryReader rd;
    private byte[] b4Buf = new byte[4];
    
    
    public OggReader(File file) throws Exception
    {
        rd = new BinaryReader(file);
        readPage();
    }

    
    public void close()
    {
        rd.close();
    }

    
    private void readPage() throws Exception
    {
        rd.readBuf(b4Buf);
        if(!BinUtils.equals("OggS", b4Buf)) throw new Exception("Invalid file format");
        
        int ver = rd.readByte();
        if(ver != 0) throw new Exception("Unsupported version: " + ver);
        
        byte hdrType = rd.readByte();
        
        boolean b = (hdrType & 0x01) == 0x01; 
        b = (hdrType & 0x02) == 0x02; 
        b = (hdrType & 0x04) == 0x04; 
        
        rd.skip(8);
        
        int serNum = rd.readInt();
        System.out.println("SerNum = " + serNum);
        
        int seqNum = rd.readInt();
        System.out.println("SeqNum = " + seqNum);
        
        rd.skip(4); // checksum
        
        short numSegments = rd.readUByte();
        System.out.println("Segments = " + numSegments);
        
        int dataSize = 0;
        for(int i = 0; i < numSegments; i++)
        {
            dataSize += rd.readUByte();
        }
        
        System.out.println("Data size = " + dataSize);
    }
    

}
