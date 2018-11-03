import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class AIReversi {
    public static ObjectOutputStream AndroidOut = null;
    public static ObjectInputStream AndroidIn = null;
    public static ServerSocket AndroidSocket;

    public static void main(String [] args) throws IOException {
        //initializeConnection();
        DisplayBoard b = new DisplayBoard();
        b.play();
    }

    public static void initializeConnection() throws IOException {
        AndroidSocket = new ServerSocket(2014, 10);
        Socket serviceSocket = AndroidSocket.accept();
        AndroidOut = new ObjectOutputStream(serviceSocket.getOutputStream());
        AndroidIn = new ObjectInputStream(serviceSocket.getInputStream());
    }
}

/*
try {

                //Run the Socket
                requestSocket = new Socket(ipadress, port);
                out = new ObjectOutputStream(requestSocket.getOutputStream());
                in = new ObjectInputStream(requestSocket.getInputStream());

                //Write the values we need to the buffer
                out.writeInt(pois);
                out.flush();
                out.writeInt(user);
                out.flush();
                out.writeInt(radius);
                out.flush();
                out.writeDouble(latit);
                out.flush();
                out.writeDouble(longt);
                out.flush();
                out.writeInt(topp);
                out.flush();

                //Receive the POIs from the server.
                for(int i = 0;i<topp;i++){
                    Poi received = (Poi) in.readObject();
                    Pois.add(received);
                }

                //Log them to the console (Yes, this is no purpose for this any more.. but you know what? I'll just leave them here..) \(O_O)/
                for(Poi p : Pois){
                    Log.e("message",p.toString());
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
 */


