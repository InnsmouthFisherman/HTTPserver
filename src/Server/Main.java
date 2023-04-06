package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Main {

    private static BufferedReader in;
    private static BufferedWriter out;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
            try (Socket socket = server.accept()) {
                System.out.println("Connected");
                while(true) {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    String word = in.readLine();

                    if (Objects.equals(word, "kill")) {
                        break;
                    }

                    System.out.println(word);
                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                    String response = reader.readLine();
                    out.write(response);
                    out.flush();
                }
            } catch (IOException e) {

                System.err.println(e);

            }finally {
                in.close();
                out.close();
            }
        server.close();
    }

}
