
// ======================================================================
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        int port = 8000;
        String host = "localhost";         
        InetAddress addr = InetAddress.getByName("x");
        System.out.println("SimpleTextTesterClient attempting to connect to server.");
        Socket serverSocket = new Socket(addr, port);
        System.out.println("Successfully connected to host " + host + " at port " + port + ".");
        Scanner in = new Scanner(System.in);
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (true) {
                String st = console.nextLine();
                bufferedWriter.write(st);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                Thread t = new Thread(() ->{
                    while(true){
                        try{
                            System.out.println(bufferedReader.readLine());
                        }catch(Exception e){}
                    }
                });
                t.start();
                

            }  // while
        } catch (Exception e){}

    } // main
}  // SimpleTextTesterClient 
