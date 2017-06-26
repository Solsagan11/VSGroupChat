package vsue.communication;

import vsue.Logger;
import vsue.Utility;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Solsagan on 25.06.2017.
 */
public class VSServer {

    private static final int PORT = 11111;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        Logger.log("Server running.");

        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Socket Accepted: " + socket.getInetAddress().getHostAddress()  + ":" + socket.getPort());
            VSConnection connection = new VSObjectConnection(socket);
            handleReceive(connection);
        }
	}

	private static void handleReceive(VSConnection connection) throws IOException {
        //echo
        byte[] bytesOfObject = connection.receiveChunk();
		Utility.printByteArrayInHex(bytesOfObject);
        connection.sendChunk(bytesOfObject);
    }
}
