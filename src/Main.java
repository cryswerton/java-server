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
            if(line == null) return;

            System.out.println("Received: " + line);

            String path = "/";
            String[] parts = line.split(" ");
            if(parts.length >= 2){
                path = parts[1];
            }

            String body;
            switch(path) {
                case "/jane":
                    body = "<html><body><h1>Hi, Jane.</h1></body></html>";
                    break;
                case "/john":
                    body = "<html><body><h1>Hi, John.</h1></body></html>";
                    break;
                case "/dexter":
                    body = "<html><body><h1>Hi, Dexter.</h1></body></html>";
                    break;
                default:
                    body = "<html><body><h1>Welcome to my Java server</h1><p>Try <a href=\"/jane\">/jane</a> or <a href=\"/john\">/john</a> or <a href=\"/dexter\">/dexter</a></p></body></html>";
            }

            String response =   "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html; charset=UTF-8\r\n" +
                                "Content-Length: " + body.getBytes().length + "\r\n" +
                                "\r\n" +
                                body;

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
