package vsue.communication;

import jdk.internal.util.xml.impl.Input;
import vsue.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

/**
 * Created by Solsagan on 23.06.2017.
 */
public class VSConnection {

    protected Socket socket;

    private Object outLock = new Object();
    private Object inLock = new Object();

    public VSConnection(String hostname, int port) throws IOException {
            socket = new Socket(hostname, port);
    }

    public void sendChunk(byte[] chunk) throws IOException {
        OutputStream out = socket.getOutputStream();

        synchronized (outLock) {
            out.write(chunk);
            out.flush();
        }
    }

    public byte[] receiveChunk() throws IOException{
        InputStream in = socket.getInputStream();
        synchronized (inLock) {
            return Utility.intToByteArray(in.read());
        }
    }
}