package vsue.communication;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Solsagan on 25.06.2017.
 */
public class VSServer {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {

         VSConnection connection = new VSObjectConnection(ADDRESS, PORT);
         byte[] bytesOfObject = connection.receiveChunk();
         connection.sendChunk(bytesOfObject);
    }
}
