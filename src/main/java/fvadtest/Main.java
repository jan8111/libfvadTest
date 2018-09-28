package fvadtest;

import javax.sound.sampled.LineUnavailableException;

public class Main {


    public static void main(String[] args) throws LineUnavailableException {


        VadService vad = new VadService();
        new AudioRecorder().captureAudio(bytes1 -> {
            int prob1 = vad.service1(bytes1);
            System.out.print(  prob1);
            System.out.print(  " ");
        });

    }





}
