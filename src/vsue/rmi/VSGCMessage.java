package vsue.rmi;

import java.io.Serializable;

/**
 * Created by Solsagan on 21.06.2017.
 */
public class VSGCMessage implements Serializable{
    private final int uid;
    private final VSGCGroup group;
    private final int mid;
    private final String message;

    public VSGCMessage(int uid, VSGCGroup group, int mid, String message) {
        this.uid = uid;
        this.group = group;
        this.mid = mid;
        this.message = message;
    }

    public int getUid() {
        return uid;
    }

    public VSGCGroup getGroup() { return group; }

    public int getMid() {
        return mid;
    }

    public String getMessage() {
        return message;
    }
}
