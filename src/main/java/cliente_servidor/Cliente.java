package cliente_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import cliente_servidor.Leer;

public class Cliente {
    public static void main(String[] args) {

        // Host del servidor
        final String HOST = "localhost";
        // Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;
        String ey;

        try {
            Socket socket = new Socket(HOST, PUERTO);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Escriba el mesnaje a enviar al servidor");
            ey = Leer.pedirCadena();

            // Envio un mensaje al cliente
            out.writeUTF(ey);

            // Recibo el mensaje del servidor
            String mensaje = in.readUTF();

            System.out.println("El servidor ha respondido con: \t" + mensaje);

            socket.close();

        } catch (IOException ex) {
            System.out.println("Hubo un fallo en cliente.java");
        }

    }
}
