/**
 * Created by Solsagan on 21.06.2017.
 */
public class Utility {

    public static void writeMessage(VSGCMessage message) {
        System.out.println(message.getUid() + " wrote: " + message.getMessage());
    }
}
