package fvadtest;


import static fvadtest.VadApi1.VadApi2.VadApi;

public class VadService {

    public static final int kChunkSize = 160;
    public static final int SAMPLE_RATE = 8000;
    private long inst1;

    public VadService() {
        this.inst1 = VadApi.fvad_new();
        System.out.println("inst1 = " + inst1);

        int stat1 = VadApi.fvad_set_sample_rate(inst1, SAMPLE_RATE);
        System.out.println("fvad_set_sample_rate  " + (stat1 == 0 ? "success" : "fail"));
    }

    public int service1(byte[] byte1) {
        if (byte1.length != (kChunkSize * 2)) {
            throw new RuntimeException("bytes length must equal:" + (kChunkSize * 2));
        }
        short[] chunk = ByteUtils.byteArrayToShortArray(byte1);
        return VadApi.fvad_process(inst1, chunk, kChunkSize);

    }

    public void free() {
        VadApi.fvad_free(inst1);
    }
}
