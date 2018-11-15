import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class AIReversi {
    public static void main(String [] args) throws IOException {
        DisplayBoard b = new DisplayBoard();
        b.play();
    }
}