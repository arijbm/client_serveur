//client
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class client{
    public static void main(String[] test){
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc=new Scanner(System.in); //lire à partir de clavier
        try{
            /*
             * les infos du serveur(port  et adr IP ou nom d'hote)
             * 127.0.0.1: l'adr local de la machine
             */
            clientSocket=new Socket("127.0.0.1",5000);
            //flux pour envoyer
            out=new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread envoyer =new Thread( new Runnable(){
                String msg;
                @Override
                public void run(){
                    while(true){
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
                        while (msg!=null) {
                            System.out.println("Serveur:"+msg);
                            msg=in.readLine();
                        }
                        System.out.println("Serveur déconnecté");
                        out.close();
                        clientSocket.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}