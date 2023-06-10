
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args){
        try{
            InetAddress addr = InetAddress.getByName("x");
            ServerSocket serverSocket = new ServerSocket(8000, 50, addr);
            System.out.println("Established Build");
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                Handler h = new Handler(socket);
                Handler.handlers.add(h);
                Thread thread = new Thread(h);
                thread.start();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    } // main
    public static class Handler implements Runnable {
        public static ArrayList<Handler> handlers = new ArrayList<Handler>();
        private Socket socket;
        public BufferedReader bufferedReader;
        public BufferedWriter bufferedWriter;
        private String ipAdd;
        public InetAddress IA;
        public Handler(Socket socket){
            try{
                this.socket = socket;
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                IA = socket.getInetAddress();
                ipAdd = IA.getHostAddress();

            } catch (Exception e){} 

        }

        public void run(){
            while(socket.isConnected()){
                try{
                    String message = bufferedReader.readLine();
                    printAcross(message);
                } catch(Exception e){}
            }
            try{
                bufferedReader.close();
            } catch (Exception e){}

        }

        private void printAcross(String message){

            try{
                for(Handler h: handlers){
                    //if(h.IA.getHostAddress() != IA.getHostAddress()){
                        h.bufferedWriter.write(IA.getHostAddress() + " " + message); 
                        h.bufferedWriter.newLine();
                        h.bufferedWriter.flush();
                   // }
                }
                System.out.println(IA.getHostAddress() + " " + message); 

            } catch (Exception e){
            }

        }
    }
}// SimpleTextTesterServer 

