import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Solsagan on 21.06.2017.
 */
public class VSGCRMIClient implements VSGCListener {

    private static int uid = 1;
    private static VSGroupChat groupChat;

    public static void main(String[] args) throws IOException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("localhost", 1234);
        groupChat = (VSGroupChat) registry.lookup("VSGroupChat");

        VSGCListener exportedClient = (VSGCListener) UnicastRemoteObject.exportObject(new VSGCRMIClient(), 0);

        Logger.log("Client started.");

        printInstructions();

        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));

        while (true) {
            try{
                String[] content = readLine(br);

                if(content.length < 2) { throw new IOException(); }

                int messageId = 0;
                String message = " ";

                //Für Lesbarkeit
                String command = content[0];
                int groupId = Integer.parseInt(content[1]);
                if (content.length >= 3)
                    messageId = Integer.parseInt(content[2]);
                if (content.length >= 4)
                    message = content[3];


                VSGCGroup correctGroup = getOrCreateGroup(groupId);

                switch (command) {
                    case "post":
                        groupChat.post(exportedClient, new VSGCMessage(uid, correctGroup, messageId, message));
                        break;
                    case "showHistory":
                        ArrayList<VSGCMessage> groupMessages = groupChat.getHistory(correctGroup);
                        Logger.log("Printing History");
                        for(VSGCMessage groupMessage : groupMessages) {
                            Utility.writeMessage(groupMessage);
                        }
                        break;
                    case "register":
                        groupChat.register(exportedClient, correctGroup);
                        Logger.log("Client registered to group " +  correctGroup.getName());
                        break;
                    default:
                        break;
                }
            } catch (IOException ex) {
                System.out.println("Wrong format of command.");
            } catch (NumberFormatException ex) {
                System.out.println("Wrong format of command.");
            }
        }
    }
    @Override
    public void newMessage(VSGCMessage message) throws RemoteException {
        System.out.println(message.getUid() + " wrote in group " + message.getGroup().getName() + ": " + message.getMessage());
    }

    private static void printInstructions() {
        System.out.println("Commands:");
        System.out.println("post GroupID MessageID Message");
        System.out.println("showHistory GroupID");
        System.out.println("register GroupId");
    }

    private static String[] readLine(BufferedReader br) throws IOException {
        String input = br.readLine();

        String[] content;
        content = input.split(" ");
        for(int i = 3; i < content.length; i++) {
            content[3] = content[3] + " " + content[i-1];
        }
        return content;
    }

    private static VSGCGroup getOrCreateGroup(int groupId) throws RemoteException, IOException {
        VSGCGroup correctGroup = null;
        ArrayList<VSGCGroup> groups = groupChat.getGroups();

        for (VSGCGroup group : groups) {
            if (group.getGid() ==  groupId) {
                correctGroup = group;
            }
        }

        //Neue Gruppe erstellen
        if (correctGroup == null) {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
            System.out.println("Group does not exist. Type in new groupname.");

            String additionalInput = br.readLine();
            correctGroup = new VSGCGroup(groupId, additionalInput);
            groupChat.addGroup(correctGroup);
        }
        return correctGroup;
    }
}
