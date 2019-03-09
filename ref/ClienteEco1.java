import java.io.*;
import java.net.*;

public class ClienteEco1{
    public static void main(String args[]){
        PrintStream salidaSocket;
	InputStreamReader isr;
        BufferedReader entradaSocket;
	try{
	    Socket miSocket =new Socket("localhost",1976);
            salidaSocket = new PrintStream(miSocket.getOutputStream());
	    isr = new InputStreamReader(miSocket.getInputStream());
            entradaSocket = new BufferedReader(isr);
	    while(true){
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));          
                String cadena = teclado.readLine();
                salidaSocket.println(cadena); 
	        System.out.println("Lo que mando: "+cadena);
                if((cadena= entradaSocket.readLine()) != null){
                    if(!(cadena.equals("ECO:salir")))
                        System.out.println(cadena);
		    else{
                        System.out.println(cadena);
			System.out.println("Cerrando puertos del cliente"); 
                        entradaSocket.close();
                        salidaSocket.close();
			System.exit(0);
	    	    }
		}
		else{
                    entradaSocket.close();
                    salidaSocket.close();
		}
	    }
         }catch(IOException ioe){
	    System.out.println(ioe);
         }
    }
}
