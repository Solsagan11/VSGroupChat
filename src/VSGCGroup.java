import java.io.Serializable;

/**
 * Created by Solsagan on 21.06.2017.
 */
public class VSGCGroup implements Serializable{
    private int gid;
    private String name;

    public VSGCGroup(int gid, String name) {
        this.gid = gid;
        this.name = name;
    }

    public int getGid() {
        return this.gid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
