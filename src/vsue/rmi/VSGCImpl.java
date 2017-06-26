package vsue.rmi;

import vsue.Logger;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Solsagan on 21.06.2017.
 */
public class VSGCImpl implements VSGroupChat {

    private final int MAX_GROUPS = 100;

    private List<VSGCListener>[] listenerToGroup = new List[MAX_GROUPS];
    private ArrayList<VSGCMessage> messages = new ArrayList<VSGCMessage>();
    private ArrayList<VSGCGroup> groups = new ArrayList<>();

    public VSGCImpl() {
        for(int i = 0; i < MAX_GROUPS; i++) {
            listenerToGroup[i] = new ArrayList<>();
        }
    }

    @Override
    public void register(VSGCListener listener, VSGCGroup group) {
        this.listenerToGroup[group.getGid()].add(listener);
        Logger.log("Client added to Listeners of group " + group.getName());
    }

    @Override
    public void post(VSGCListener listener, VSGCMessage message) throws RemoteException {
        this.messages.add(message);
        this.register(listener, message.getGroup());
        Logger.log("Message sent to group " + message.getGroup());
        this.notifyListeners(message);
    }

    @Override
    public ArrayList<VSGCMessage> getHistory(VSGCGroup group) throws IllegalArgumentException, RemoteException {
        ArrayList<VSGCMessage> messagesOfGid = new ArrayList<>();

        for (VSGCMessage message: messages) {
            if (message.getGroup().getGid() == group.getGid()) {
                messagesOfGid.add(message);
            }
        }
        if(messagesOfGid.isEmpty()) {
            Logger.log("No Messages found for group " + group.getName());
        }
        return messagesOfGid;
    }

    private void notifyListeners(VSGCMessage message) throws RemoteException{
        for(VSGCListener listener : listenerToGroup[message.getGroup().getGid()]) {
            listener.newMessage(message);
        }
        Logger.log("Listeners notified.");
    }

    @Override
    public ArrayList<VSGCGroup> getGroups() {
        return this.groups;
    }

    public void addGroup(VSGCGroup group) {
        this.groups.add(group);
    }
}
