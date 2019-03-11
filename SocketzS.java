import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SocketzS{ 
	public static void main(String args[]){ 
		ServerSocket server=null; 
		try{
			server = new ServerSocket(1234);
		}catch(IOException ioe){
			System.out.println( "Error al crear servidor");
			System.exit(-1);
		}
		Socket client=null;
		try{
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
		String line= null;
		loop:for(;;){
			try{
				line = in.readLine();
				out.println(line);
				System.out.println( line);
				if(line.equals("finish")){
					break loop;
				}
			}catch(IOException ioe){
				System.out.println( "Read fail");
			}
		}
	}
}
