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
        byte[] returningBytes = null;
        try {
            InputStream in = socket.getInputStream();

            byte[] byteLength = this.readChunk(in, 0, 4);
            Utility.printByteArrayInHex(byteLength);
            int intLength = Utility.byteArrayToInt(byteLength);
            Logger.log("Read length: " + intLength);
            returningBytes = this.readChunk(in, 4, intLength);
            return returningBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returningBytes;
    }

    private byte[] readChunk(InputStream in, int off, int length) throws IOException {

        int actualRead = 0;
        byte[] readBytes = new byte[length];

        do {
            actualRead = in.read(readBytes, off, length-off);
            if (actualRead == -1) {
                Logger.log("InputStream closed.");
            }
            off += actualRead;
            Logger.log("ENDLOSSCHLEIFE!!");
        } while(off < length);
        Logger.log("Finished reading chunkpart");
        return readBytes;
    }
}
