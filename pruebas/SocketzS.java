package

import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

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
		String res; 
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
private class newC extends Thread{
	Socket client;
	
	
}
