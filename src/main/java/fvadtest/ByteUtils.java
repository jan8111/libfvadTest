package fvadtest;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by xiaoi-010 on 2017-12-19.
 */
public class ByteUtils {
    public static short[] byteArrayToShortArray(byte[] data){
        short[] t = new short[data.length/2];
        for (int i = 0; i < t.length; i++) {
            byte[] b = new byte[2];
            b[0] = data[2*i];
            b[1] = data[2*i+1];
            t[i] = byteToShort2(b[0], b[1]);
        }
        return t;
    }

    public static byte[] shortArrayToByteArray(short[] data){
        byte[] t = new byte[data.length*2];
        for (int i = 0; i < data.length; i++) {
            byte[] b = shortToByte2(data[i]);
            t[2*i] = b[1];
            t[2*i+1] = b[0];
        }
        return t;
    }

    public static byte[] shortToByte2(short number) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.putShort(0, number);
        return bb.array();
    }

    public static short byteToShort2(byte b1, byte b2) {
        byte[] bs = new byte[2];
        bs[0] = b1; bs[1] = b2;
        ByteBuffer bb = ByteBuffer.wrap(bs);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getShort();
    }
}
