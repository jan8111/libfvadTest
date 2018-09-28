package fvadtest;


import java.io.FileInputStream;
import java.io.InputStream;

import static fvadtest.VadApi1.VadApi2.VadApi;

public class VadService {

    public static final int kChunkSize = 160;

    static void service1() {
        long inst1 = VadApi.fvad_new();
        System.out.println("inst1 = " + inst1);

        int stat1 = VadApi.fvad_set_sample_rate(inst1, 8000);
        System.out.println("fvad_set_sample_rate  " + (stat1==0?"success":"fail"));

        try (InputStream is = new FileInputStream("022_8k.wav")) {
            byte[] bb = new byte[kChunkSize * 2];
            int len = 0;
            while ((len = is.read(bb)) != -1) {
                short[] chunk = ByteUtils.byteArrayToShortArray(bb);
                int processStat1 = VadApi.fvad_process(inst1, chunk, kChunkSize);
                System.out.print(" " + processStat1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            VadApi.fvad_free(inst1);
        }
    }


}
