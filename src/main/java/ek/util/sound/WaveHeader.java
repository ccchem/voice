package ek.util.sound;


public class WaveHeader
{
    short format;
    short channels;
    int samplesPerSec;
    short sampleSize;
    short bitsPerSample;
    int numSamples;
    
    
    public WaveHeader()
    {
    }
    

    public short getFormat()
    {
        return format;
    }


    public short getNumChanels()
    {
        return channels;
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
    
    
    public void print()
    {
        System.out.println("            Format:  " + getFormatString(format));
        System.out.println("          Channels:  " + channels);
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

}
