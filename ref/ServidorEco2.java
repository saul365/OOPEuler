import java.io.*;
import java.net.*;
public class ServidorEco2 {
    Socket s;
    public ServidorEco2(int puerto){
	ServerSocket ss;
        try{
            ss = new ServerSocket(puerto);
	    System.out.println("Servidor corriendo en el puerto..."+puerto); 
	    while(true){
                s = ss.accept();
	        System.out.println("Accesaron desde .." + "[" + s.toString().substring(12) + "\n");
		ServidorEcoT c = new ServidorEcoT(s);
	        c.start();
	    }
        }catch(IOException ioe){
            System.out.println("Un error:"+ioe);
        }
    }

    public static void main(String args[]){ 
	ServidorEco2 se2 =new ServidorEco2(1976);
    }
}

class ServidorEcoT extends Thread{
    Socket s;
    public ServidorEcoT(Socket socket){
	s=socket;
    }
    public void run(){ 
	try{
           PrintStream salidaSocket=null;
           InputStreamReader isr;
           BufferedReader entradaSocket=null;
	   while(true){
	        System.out.println(s);
                salidaSocket = new PrintStream(s.getOutputStream());
                isr = new InputStreamReader(s.getInputStream());
                entradaSocket = new BufferedReader(isr);
	        String cadena;
                while((cadena= entradaSocket.readLine()) != null){
		    if(cadena.equals("salir")){
                       salidaSocket.println("ECO:"+cadena);
		       s.close();
		    }
		    else
                        salidaSocket.println("ECO:"+cadena);
		}
		System.out.println("Cerrando puertos del Servidor"); 
                salidaSocket.close();
                isr.close();
	   }
	}catch(SocketException se){
	   System.out.println("Cerando el socket:"+s);
	   System.out.println("Error en el socket:"+se);
	}catch(IOException ioe){
	   System.out.println("Un error:"+ioe);
	}catch(Exception e){
	   System.out.println("Un error:"+e);
	}
    }
}

