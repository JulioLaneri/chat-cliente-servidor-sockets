package Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;

public class Server {
    public static void main(String[] args) {
        MulticastSocket socket = null;
        
        try {
            InetAddress server = InetAddress.getByName("230.0.0.1");
            int puerto = 4446;
            
            socket = new MulticastSocket(puerto);
            socket.joinGroup(server);
            
            System.out.println("El servidor está listo para recibir mensajes");
            
            while (true) {
                byte[] buffer = new byte[1024]; // fuera del bucle?
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
                Mensaje mensaje = (Mensaje) objectInputStream.readObject();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = dateFormat.format(mensaje.getTime());
                
                System.out.println(
                    "[" + formattedTime + "] " +
                    "[" + mensaje.getRemitente() + "] " +
                    mensaje.getMensaje()
                );
                
                byteStream.close();
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
                socket.close();
        }
    }
}
