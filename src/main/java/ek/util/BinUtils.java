package ek.util;


public class BinUtils
{
    public static boolean equals(String str, byte[] buf)
    {
        if(str.length() > buf.length) return false;
        
        for(int i = 0; i < str.length(); i++)
        {
            if((byte)str.charAt(i) != buf[i]) return false;
        }
        
        return true;
    }

}
