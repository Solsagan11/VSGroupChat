import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Solsagan on 21.06.2017.
 */
public interface VSGroupChat extends Remote {

    void register(VSGCListener listener, VSGCGroup group) throws RemoteException;
    void post(VSGCListener listener, VSGCMessage message) throws RemoteException;
    ArrayList<VSGCMessage> getHistory(VSGCGroup group) throws IllegalArgumentException, RemoteException;
    ArrayList<VSGCGroup> getGroups() throws RemoteException;
    void addGroup(VSGCGroup group) throws RemoteException;
}
