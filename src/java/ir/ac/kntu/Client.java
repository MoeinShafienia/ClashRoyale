import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private Scanner input;
    private DataOutputStream output;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            input = new Scanner(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        String line = "";
        while(!line.equals("Over")){
            try {
                line = input.next();
                output.writeUTF(line);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // public static void main(String[] args) {
    //     new Client("127.0.0.1", 5000);
    // }
}