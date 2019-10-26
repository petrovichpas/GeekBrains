package JavaCoreProfessionalLevel.Task3;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args)  {
        Socket client = null;
        ObjectOutputStream out = null;
        try {
             client = new Socket("localhost", 8189);
             out = new ObjectOutputStream(client.getOutputStream());
             out.writeObject(new Main());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                client.close();
            } catch (IOException e) {
                System.out.println(2);
                e.printStackTrace();
            }
        }
    }
}
