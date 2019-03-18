import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Socketc { 
	public static void main(String args[]){ 
		Socket client=null;
		BufferedReader in=null;
		PrintWriter out=null;
		String ipv4="192.168.0.11";//lap="192.168.0.11" termuxU="201.175.205.20"
		try{
			client= new Socket(InetAddress.getByName(ipv4),1234);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
		} catch(UnknownHostException uhe){
			System.out.println( "Host desconocido");
			System.exit(-1);
		} catch(IOException ioe){
			System.out.println( "Error de IO");
			System.exit(-1);
		}
		loop:for(;;){
			System.out.print( "Ingrese algo para mandarlo al servidor: ");
			/*Scanner sc= new Scanner(System.in);
			out.println(smt);
			*/
			String smt=null; 
			try{
				smt=in.readLine();
				System.out.println( smt);
				if(smt.equals("finish")){
					break loop;
				}if(smt.endsWith("sucesion")){
					StringTokenizer strtFn= new StringTokenizer(smt,",");	
					String start= strtFn.nextToken();
					String finish= strtFn.nextToken();
					try{
						Factorial4T resolve =new Factorial4T(Integer.parseInt(start),Integer.parseInt(finish));
						BigInteger result=resolve.startOp();
						System.out.println("res");
						out.println(result.toString());
					}catch(InterruptedException ie){
						System.out.println("Error de hilos");
					}
				}
			}catch(IOException ioe){
				System.out.println( "Error al leer del servidor");
				System.exit(-1);
			}
		}	
	}
}
