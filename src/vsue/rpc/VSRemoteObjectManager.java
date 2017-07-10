package vsue.rpc;

import java.rmi.Remote;
import java.util.Set;

/**
 * Created by Solsagan on 04.07.2017.
 */
public class VSRemoteObjectManager {

    private Set<VSRemoteReference> remoteReferences = new Set<VSRemoteReference>();

    private static VSRemoteObjectManager manager = null;

    public static VSRemoteObjectManager getInstance () {
        if(manager==null) {
            manager = new VSRemoteObjectManager();
        }
        return manager;
    }
    public Remote exportObject (Remote object ) {

    }
    public Object invokeMethod ( int objectID , String genericMethodName , Object [] args ) {
        VSRemoteReference ref = getRefByObjId(objectID);

    }

    private VSRemoteReference getRefByObjId(int objectID) {
        for(VSRemoteReference ref : remoteReferences) {
            if (ref.getObjectID()==objectID) {
                return ref;
            }
        }
    }
}
