package vsue.communication;

import vsue.Logger;
import vsue.Utility;

import java.io.*;
import java.net.Socket;

/**
 * Created by Solsagan on 23.06.2017.
 */
public class VSObjectConnection extends VSConnection {

    public VSObjectConnection(String hostname, int port) throws IOException {
        super(hostname, port);
    }
    public VSObjectConnection(Socket socket) {
        super(socket);
    }

    public void sendObject(Serializable object) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
			objectOutputStream.writeObject(object);
			byte[] bytes = outputStream.toByteArray();
			sendChunk(bytes);
		}
		finally {
			outputStream.close();
		}
    }

    public Serializable receiveObject() throws IOException, ClassNotFoundException {

			Serializable object;
			ByteArrayInputStream inputStream = new ByteArrayInputStream(receiveChunk());

			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			object = (Serializable) objectInputStream.readObject();
			objectInputStream.close();

			return object;
    }
}
