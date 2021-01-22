package ek.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class BinaryReader
{
    private InputStream is;
    private byte[] _buf = new byte[16];

    
    public BinaryReader(File file) throws Exception
    {
        is = new FileInputStream(file);
    }
    
    
    public void close()
    {
        CloseUtils.close(is);
    }

    
    public void skip(long n) throws Exception
    {
        is.skip(n);
    }

    
    public void readBuf(byte[] buf) throws Exception
    {
        readBuf(buf, buf.length);
    }

    
    public void readBuf(byte[] buf, int len) throws Exception
    {
        int rs = is.read(buf, 0, len);
        if(rs != len) throw new Exception("Read error");
    }

    
    public short readUByte() throws Exception
    {
        return (short)is.read();
    }

    
    public byte readByte() throws Exception
    {
        return (byte)is.read();
    }

    
    public int readInt() throws Exception
    {
        readBuf(_buf, 4);
        
        return (_buf[0] & 0xFF) 
                | ((_buf[1] & 0xFF) << 8) 
                | ((_buf[2] & 0xFF) << 16 ) 
                | ((_buf[3] & 0xFF) << 24 );
    }
    
    
    public short readShort() throws Exception
    {
        readBuf(_buf, 2);
        
        return (short)((_buf[0] & 0xFF) | ((_buf[1] & 0xFF) << 8));
    }


}
