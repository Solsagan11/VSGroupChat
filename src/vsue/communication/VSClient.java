package vsue.communication;

import vsue.Utility;

import java.io.IOException;

/**
 * Created by Solsagan on 25.06.2017.
 */
public class VSClient {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        VSConnection connection = new VSObjectConnection(ADDRESS, PORT);
        int i = 20;
        String string = "zwanzig";
        String[] stringarray = { "zwanzig", "einundzwanzig", "dreiundzwanzig"};

        byte[] bytesOfObject = Utility.convertToBytes(i);
        connection.sendChunk(bytesOfObject);
        byte[] returnedBytes = connection.receiveChunk();
        Utility.printByteArray(returnedBytes);

        bytesOfObject = Utility.convertToBytes(string);
        connection.sendChunk(bytesOfObject);
        returnedBytes = connection.receiveChunk();
        Utility.printByteArray(returnedBytes);

        bytesOfObject = Utility.convertToBytes(stringarray);
        connection.sendChunk(bytesOfObject);
        returnedBytes = connection.receiveChunk();
        Utility.printByteArray(returnedBytes);
    }
}
