package fvadtest;

import javax.sound.sampled.*;
import java.util.function.Consumer;

public class AudioRecorder {
    private TargetDataLine targetDataLine;
    private boolean recording=true;

    public void captureAudio(Consumer<byte[]> consumer) throws LineUnavailableException {
        AudioFormat audioFormat = getAudioFormat();
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        final byte buffer[] = new byte[getBuffzie()];
        targetDataLine.open(audioFormat);
        targetDataLine.start();
        System.out.println("recording...");
        while (recording) {
            int count = targetDataLine.read(buffer, 0, buffer.length);
            if (count > 0) {
                try {
                    if(consumer!=null){
                        consumer.accept(buffer);
                    }else {
                        System.out.println("recorded: data size:" + count);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("recording end.");
    }

    public void stopThread( ) {
        recording=false;
        targetDataLine.stop();
        targetDataLine.close();
    }

      static int getBuffzie() {
        return VadService.kChunkSize*2;
    }

      static AudioFormat getAudioFormat() {
        float sampleRate = VadService.SAMPLE_RATE;
        // 8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        // 8,16
        int channels = 1;
        // 1,2
        boolean signed = true;
        // true,false
        boolean bigEndian = false;
        // true,false
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }



}