package Client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;
import java.util.Scanner;

import Server.Mensaje;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            InetAddress server = InetAddress.getByName("230.0.0.1");
            int puerto = 4446;

            MulticastSocket socket = new MulticastSocket(puerto);
            socket.joinGroup(server);

            System.out.println("Ingrese su nombre:");
            String remitente = scanner.nextLine();

            while (true) {
                System.out.println("Escriba su mensaje (o 'salir' para salir):");
                String mensajeTexto = scanner.nextLine();

                if (mensajeTexto.equalsIgnoreCase("salir")) {
                    break;
                }

                // Crear un objeto Mensaje con marca de tiempo actual
                Mensaje mensaje = new Mensaje(remitente, new Date(), mensajeTexto);

                // Serializar el objeto Mensaje
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
                objectOutputStream.writeObject(mensaje);
                objectOutputStream.flush();
                byte[] mensajeSerializado = byteStream.toByteArray();

                // Enviar el mensaje serializado al grupo multicast
                DatagramPacket packet = new DatagramPacket(mensajeSerializado, mensajeSerializado.length, server, puerto);
                socket.send(packet);
            }

            socket.leaveGroup(server);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
