package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.Objects;

public class Main {

    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 8080);){
            while(true) {
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                String word = reader.readLine();

                if(Objects.equals(word, "kill")){
                    break;
                }

                out.write(word + "\n");
                out.flush();
                String serverWord = in.readLine();
                System.out.println(serverWord);
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            System.out.println("Клиент был закрыт...");
            socket.close();
            in.close();
            out.close();
        }
    }
}
