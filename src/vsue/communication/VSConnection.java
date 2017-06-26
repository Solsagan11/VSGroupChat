package vsue.communication;

import jdk.internal.util.xml.impl.Input;
import vsue.Logger;
import vsue.Utility;

import java.io.ByteArrayOutputStream;
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

    public VSConnection(String hostname, int port) throws IOException {
            socket = new Socket(hostname, port);
    }
    public VSConnection(Socket socket) {
        this.socket = socket;
    }

    public synchronized void sendChunk(byte[] chunk) throws IOException {
         OutputStream out = socket.getOutputStream();

            out.write(Utility.convertToBytes(chunk.length));
            out.write(chunk);
            out.flush();
            Utility.printByteArrayInHex(chunk);
            Logger.log("Chunk sent.");
    }

    public synchronized byte[] receiveChunk() throws IOException{
        InputStream in = socket.getInputStream();
        byte[] byteLength = new byte[4];
        in.read(byteLength, 0, 4);
        byte[] returningBytes = this.readChunk(in, byteLength);
        return returningBytes;
    }

    private byte[] readChunk(InputStream in, byte[] byteLength) {

        int readItem = 0;
        byte[] bytes = new byte[byteLength];
        int counter = 0;
        do {
            in.read(bytes, counter);
        } while(readItem > 0)

    }
}