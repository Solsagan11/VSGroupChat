package vsue.communication;

import vsue.Logger;
import vsue.Utility;

import java.io.IOException;

/**
 * Created by Solsagan on 25.06.2017.
 */
public class VSClient {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 11111;

    public static void main(String[] args) throws IOException {
        VSObjectConnection connection = new VSObjectConnection(ADDRESS, PORT);
        int i = 20;
        String string = "zwanzig";
        String[] stringArray = { "zwanzig", "einundzwanzig", "dreiundzwanzig"};

        //byte[] bytesOfObject = Utility.convertToBytes(i);
        connection.sendObject(i);
        Logger.log("int sent.");
        byte[] returnedBytes = connection.receiveChunk();
        Logger.log("int received.");
        Utility.printByteArrayInHex(returnedBytes);

        //bytesOfObject = Utility.convertToBytes(string);
        connection.sendObject(string);
        Logger.log("string sent.");
        returnedBytes = connection.receiveChunk();
        Utility.printByteArrayInHex(returnedBytes);

        //bytesOfObject = Utility.convertToBytes(stringarray);
        connection.sendObject(stringArray);
        Logger.log("stringarray sent.");
        returnedBytes = connection.receiveChunk();
        Utility.printByteArrayInHex(returnedBytes);
    }
}
