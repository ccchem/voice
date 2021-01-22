package ek.sound.wav;

public class WaveFormat
{
    short format;
    short numChannels;
    int samplesPerSec;
    short sampleSize;
    short bitsPerSample;
    int numSamples;

    
    public WaveFormat()
    {
    }

    
    public float getDurationSec()
    {
        return (float)numSamples / (float)samplesPerSec;
    }


    public short getFormat()
    {
        return format;
    }
    
    
    public String getFormatString()
    {
        switch(format)
        {
        case 1:
            return "PCM";
        }
        
        return String.valueOf(format);
    }

    
    public short getNumChannels()
    {
        return numChannels;
    }

    
    public int getSamplesPerSec()
    {
        return samplesPerSec;
    }
    
    
    public short getSampleSize()
    {
        return sampleSize;
    }

    
    public short getBitsPerSample()
    {
        return bitsPerSample;
    }
    
    
    public int getNumSamples()
    {
        return numSamples;
    }

    
    public void print()
    {
        System.out.println("            Format:  " + getFormatString());
        System.out.println("          Channels:  " + numChannels);
        System.out.println("Sample size, bytes:  " + sampleSize);
        System.out.println("Samples per second:  " + samplesPerSec);
        System.out.println("   Bits per sample:  " + bitsPerSample);
        System.out.println(" Number of samples:  " + numSamples);
        System.out.format(" Duration, seconds:  %.2f\n", getDurationSec());
    }

}
