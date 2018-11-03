import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    ObjectOutputStream AndroidOut = null;
    ObjectInputStream AndroidIn = null;
    ServerSocket AndroidSocket;



    public Network(){

    }


    public char[] getMove() throws IOException {
        AndroidSocket = new ServerSocket(2014, 10);
        Socket serviceSocket = AndroidSocket.accept();
        AndroidOut = new ObjectOutputStream(serviceSocket.getOutputStream());
        AndroidIn = new ObjectInputStream(serviceSocket.getInputStream());
        char s = AndroidIn.readChar();
        char p = AndroidIn.readChar();
        char[] m = {s,p};
        return m;
    }

    public void sendMove(char[][] s) throws IOException{
        AndroidSocket = new ServerSocket(2014, 10);
        Socket serviceSocket = AndroidSocket.accept();
        AndroidOut = new ObjectOutputStream(serviceSocket.getOutputStream());
        AndroidIn = new ObjectInputStream(serviceSocket.getInputStream());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                AndroidOut.writeChar(s[i][j]);
                AndroidOut.flush();
            }
        }
    }
}
