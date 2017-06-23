import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Solsagan on 21.06.2017.
 */
public class VSGCRMIServer {

    public static void main(String[] args) throws Exception {
        VSGroupChat groupChatImpl = new VSGCImpl();
        VSGroupChat groupChat = (VSGroupChat) UnicastRemoteObject.exportObject(groupChatImpl, 0);

        Registry registry = LocateRegistry.createRegistry(1234);
        registry.bind("VSGroupChat", groupChat);

        Logger.log("Server running");
    }
}
