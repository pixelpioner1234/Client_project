import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    Socket socket;
    PrintWriter output;
    BufferedReader input;

    ClientThread(String host, int port) throws IOException {
        socket = new Socket(host,port);
    }

    public void run() {
        try {
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
            String msg;
            while((msg = input.readLine()) != null) {
                System.out.println("Message: " + msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcast(String msg) {
        output.println(msg);
    }
}
