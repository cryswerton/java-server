import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while(true){
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
        }
    }

    private static void handleClient(Socket clientSocket){
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                ){

            String line = in.readLine();
            System.out.println("Received: " + line);

            String response =   "HTTP/1.1 200 OK\r\n"+
                                "Content-Type: text/plain\r\n"+
                                "Content-Length: 13\r\n"+
                                "\r\n"+
                                "Hello, World!";

            out.write(response);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                clientSocket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
