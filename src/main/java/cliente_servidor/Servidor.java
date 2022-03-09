package cliente_servidor;

import java.io.DataInputStream;
import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket socket = null;
        DataInputStream in;
        DataOutputStream out;

        final int PUERTO = 5000;

        try {
            // Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("El servidor esta iniciado");

            Semaforo semaforo = new Semaforo(10);

            // Siempre estara escuchando peticiones
            while (true) {

                // Espero a que un cliente se conecte
                socket = servidor.accept();

                System.out.println("Cliente conectado..");
                // in es el puente al servidor
                in = new DataInputStream(socket.getInputStream());
                // out es el puente al cliente
                out = new DataOutputStream(socket.getOutputStream());

                // Leo el mensaje que me envia
                String mensaje = in.readUTF();
                System.out.println("Mensaje del cliente:\t [" + mensaje + "]");

                //Sustituye las letras con tilde por sus correspondientes sin tilde
                String sinTilde = mensaje.replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u');

                //Funcion encargada de colocar un solo espacio entre palabras.
                String sinEspacios = sinTilde.replaceAll("\\s+", " ");



                // Le envio un mensaje al cliente con mensaje modificado en minusculas, sin tildes y con solo un espacio
                out.writeUTF(sinEspacios.toLowerCase());

                // Cierro el socket cliente
                socket.close();
                System.out.println("Cliente procedio a la murision");

            }
        } catch (Exception e) {
            System.out.println("Algo salio mal en el servidor.java");
        }

    }

}
