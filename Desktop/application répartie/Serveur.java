//Serveur
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Serveur {
    public static void main(String[] test){
        final ServerSocket serveurSocket;
        final Socket clientSocket ;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc=new Scanner(System.in);
        try{
            serveurSocket=new ServerSocket(5000);
            clientSocket=serveurSocket.accept();
            out=new PrintWriter(clientSocket.getOutputStream());
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread envoi=new Thread(new Runnable(){
                String msg;
                @Override
                public void run(){
                    while (true) {
                        msg=sc.nextLine();
                        out.printLn(msg);
                        out.flush();
                    }
                }
            });
            envoi.start();
            Thread recevoir=new Thread(new Runnable(){
                String msg;
                @Override
                public void run(){
                    try{
                        msg=in.readLine();
                        //client connecté
                        while (msg!=null) {
                            System.out.println();
                        }
                        //client déconnecte
                        System.out.println("client déconnecté");
                        //fermer le flux et la session socket
                        out.close();
                        clientSocket.close();
                        serveurSocket.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
