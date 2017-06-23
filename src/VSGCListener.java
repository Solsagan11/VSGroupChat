import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Solsagan on 21.06.2017.
 */
public interface VSGCListener extends Remote {
    public void newMessage(VSGCMessage message) throws RemoteException;
}
