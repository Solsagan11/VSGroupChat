package vsue.communication;

import jdk.internal.util.xml.impl.Input;
import vsue.Logger;
import vsue.Utility;

import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by Solsagan on 23.06.2017.
 */
public class VSConnection {

    protected Socket socket;

    public VSConnection(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
    }

    public VSConnection(Socket socket) {
        this.socket = socket;
    }

    public synchronized void sendChunk(byte[] chunk) throws IOException {
        OutputStream out = socket.getOutputStream();

        out.write(Utility.intToByteArray(chunk.length));
        out.write(chunk);
        out.flush();
    }

    public synchronized byte[] receiveChunk() throws IOException {
        byte[] returningBytes = null;

        InputStream in = socket.getInputStream();
        byte[] byteLength = this.readChunk(in, 4);
        int intLength = Utility.byteArrayToInt(byteLength);
        returningBytes = this.readChunk(in, intLength);

        return returningBytes;
    }

    private byte[] readChunk(InputStream in, int length) throws IOException {
        int off = 0;
        int actualRead ;
        byte[] readBytes = new byte[length];

        do {
            actualRead = in.read(readBytes, off, length - off);
            if (actualRead == -1) {
                Logger.log("InputStream closed.");
            }
            off += actualRead;
        } while (off < length);
        return readBytes;
    }
}
