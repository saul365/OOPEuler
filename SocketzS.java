import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.LinkedList;;

public class SocketzS{ 
	public static void main(String args[]) throws InterruptedException{ 
		ServerSocket server=null; 
		try{
			server = new ServerSocket(1234);
		}catch(IOException ioe){
			System.out.println( "Error al crear servidor");
			System.exit(-1);
		}
		Socket client=null;
		newC acceptor=new newC(server);
		acceptor.start();
		/*try{
			client = server.accept();
		}catch(IOException ioe){
			System.out.println( "Error em accept");
			System.exit(-1);
		}
		BufferedReader in=null;
		PrintWriter out=null;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
		}catch(IOException ioe){
			System.out.println( "Couldnt acces client IO");
			System.exit(-1);
		}
		*/
		String line= null;
		String res; 
		BufferedReader in=null;
		PrintWriter out=null;
		loop:for(;;){
				Scanner cm = new Scanner(System.in);
				line= cm.next();
				System.out.println( line);
				out.println(line);
				System.out.println( line);
			try{
				res= in.readLine();
				if(res.length()>100){
					BufferedWriter archivoW= new BufferedWriter(new FileWriter("archivoF.txt"));
					archivoW.write(res.toString());
					archivoW.newLine();
					archivoW.close();
					System.out.println("Resultado escrito en achivoF.txt, Escriba finish para terminar el programa");
				}else{
					System.out.println( res);
				}
			}catch(IOException ioe){
				System.out.println( "Read fail");
			}
			if(line.endsWith("inish")){
				break loop;
			}
		}
	}
}
class newC extends Thread{
	LinkedList<actualC> clientList;
	Socket client=null;
	ServerSocket server;
	
	public newC(ServerSocket server){
		this.server=server;
	}

	public void run(){
		try{
			for(;;){
				client = server.accept();
				clientList.add(new actualC(client));
				System.out.println(clientList.size());
			}
		}catch(IOException ioe){
			System.out.println( "Error em accept");
			System.exit(-1);
		}

	}
	
	
}

class actualC extends Thread{
	Socket client;
	
	public actualC(Socket client){
		this.client=client;
		BufferedReader in=null;
		PrintWriter out=null;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
		}catch(IOException ioe){
			System.out.println( "Couldnt acces client IO");
			System.exit(-1);
		}
	}
}
