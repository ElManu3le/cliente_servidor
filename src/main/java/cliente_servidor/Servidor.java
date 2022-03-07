package cliente_servidor;

import java.io.DataInputStream;
import java.io.*;
import java.net.*;
import java.util.logging.*;

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

            // Siempre estara escuchando peticiones
            while (true) {

                // Espero a que un cliente se conecte
                socket = servidor.accept();

                System.out.println("Cliente conectado..");
                //in es el puente al servidor
                in = new DataInputStream(socket.getInputStream());
                //out es el puente al cliente
                out = new DataOutputStream(socket.getOutputStream());

                // Leo el mensaje que me envia
                String mensaje = in.readUTF();
                System.out.println("Mensaje del cliente:\t [" + mensaje + "]");
                
                /**  */

                // Le envio un mensaje y se lo devuelvo al cliente
                out.writeUTF(mensaje.toLowerCase());

                // Cierro el socket cliente
                socket.close();
                System.out.println("Cliente procedio a la murision");

            }
        } catch (Exception e) {
            System.out.println("Algo salio mal en el servidor.java");
        }

    }

    /**Aqui van otros procesos como quitar tildes, minusculas 4ever y los espacios */

    public String QuitarTildes(String mensaje){

        return mensaje;

    }



}
