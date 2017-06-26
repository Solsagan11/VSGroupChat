package vsue.communication;

import vsue.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Solsagan on 23.06.2017.
 */
public class VSObjectConnection extends VSConnection {

    public VSObjectConnection(String hostname, int port) throws IOException {
        super(hostname, port);
    }

    public void sendObject(Serializable object) throws IOException {
        byte[] bytesOfObject = Utility.convertToBytes(object);
        sendChunk(bytesOfObject);
    }

    public Serializable receiveObject() throws IOException, ClassNotFoundException {
        byte[] bytesOfObject = receiveChunk();
        Object object = Utility.convertFromBytes(bytesOfObject);
        return (Serializable) object;
    }
}
