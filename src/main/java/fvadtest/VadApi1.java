package fvadtest;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class VadApi1 {
    public interface VadApi2 extends Library {
        VadApi2 VadApi = (VadApi2) Native.loadLibrary("fvad", VadApi2.class);

        long  fvad_new();
        void fvad_free(long inst);
        void fvad_reset(long inst);

        /*
         * Changes the VAD operating ("aggressiveness") mode of a VAD instance.
         *
         * A more aggressive (higher mode) VAD is more restrictive in reporting speech.
         * Put in other words the probability of being speech when the VAD returns 1 is
         * increased with increasing mode. As a consequence also the missed detection
         * rate goes up.
         *
         * Valid modes are 0 ("quality"), 1 ("low bitrate"), 2 ("aggressive"), and 3
         * ("very aggressive"). The default mode is 0.
         *
         * Returns 0 on success, or -1 if the specified mode is invalid.
         */
        int fvad_set_mode(long inst, int mode);


        /*
         * Sets the input sample rate in Hz for a VAD instance.
         *
         * Valid values are 8000, 16000, 32000 and 48000. The default is 8000. Note
         * that internally all processing will be done 8000 Hz; input data in higher
         * sample rates will just be downsampled first.
         *
         * Returns 0 on success, or -1 if the passed value is invalid.
         */
        int fvad_set_sample_rate(long inst, int sample_rate);


        /*
         * Calculates a VAD decision for an audio frame.
         *
         * `frame` is an array of `length` signed 16-bit samples. Only frames with a
         * length of 10, 20 or 30 ms are supported, so for example at 8 kHz, `length`
         * must be either 80, 160 or 240.
         *
         * Returns              : 1 - (active voice),
         *                        0 - (non-active Voice),
         *                       -1 - (invalid frame length).
         */
        int fvad_process(long inst, short[] frame, int length);

    }




}
