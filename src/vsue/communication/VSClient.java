package vsue.communication;

import vsue.Logger;
import vsue.Utility;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Solsagan on 25.06.2017.
 */
public class VSClient {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 11111;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        VSObjectConnection connection = new VSObjectConnection(ADDRESS, PORT);
        Serializable returnObject;

        int i = 20;
        //byte[] bytesOfObject = Utility.convertToBytes(i);
        Logger.log("Sending starts...");
        byte[] returnedBytes;

        Logger.log("Object in hex:");
        Utility.printByteArrayInHex(Utility.convertToBytes(i));
        connection.sendObject(i);

        returnObject = connection.receiveObject();
        returnedBytes = Utility.convertToBytes(returnObject);
        Logger.log("Received object in hex:");
        Utility.printByteArrayInHex(returnedBytes);


    }
}
