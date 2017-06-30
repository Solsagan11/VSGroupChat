package vsue;

import vsue.rmi.VSGCMessage;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by Solsagan on 21.06.2017.
 */
public class Utility {

    public static void writeMessage(VSGCMessage message) {
        System.out.println(message.getUid() + " wrote: " + message.getMessage());
    }

    public static byte[] intToByteArray(int i) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }

    public static int byteArrayToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    public static void printByteArrayInHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for(byte b: bytes)
            sb.append(String.format("%02x", b));
        System.out.println(sb.toString() + ":" + bytes.length);
    }
}
