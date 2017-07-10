package vsue.rpc;

import java.io.Serializable;

/**
 * Created by Solsagan on 04.07.2017.
 */
public class VSRemoteReference implements Serializable {
    private String host;
    private int port;
    private int objectID;

    public VSRemoteReference(String host, int port, int objectID) {
        this.host = host;
        this.port = port;
        this.objectID = objectID;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getObjectID() {
        return objectID;
    }
}
