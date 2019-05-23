//package OOPEuler;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Hashtable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SocketzS{ 
	public static void main(String args[]) throws InterruptedException{ 
		ServerSocket server=null; 
		try{
			server = new ServerSocket(1234);
		}catch(IOException ioe){
			System.out.println( "Error al crear servidor");
			System.exit(-1);
		}
		Hashtable tabla=Hash.MTable();
		Socket client=null;
		NewC acceptor=new NewC(server);
		acceptor.start();
		String line= null;
		String res; 
		BufferedReader in=null;
		PrintWriter out=null;
		loop:for(;;){
				System.out.println( "Ingrese cuantos digitos desea del número de euler");
				Scanner cm = new Scanner(System.in);
				line= cm.next();
				int key=Integer.parseInt(line);
				key++;
				String tmp=Integer.toString(key);
				while(!tabla.containsKey(tmp)){
					key++;
					tmp=Integer.toString(key);
				}
				String pres=line;
				String fact=tabla.get(tmp).toString();
			try{
				acceptor.sendT(fact,pres);
				while(!acceptor.getReady()){
				}
				res= acceptor.getRes().toString();
				if(res.length()>100){
					BufferedWriter archivoW= new BufferedWriter(new FileWriter("archivoF.txt"));
					archivoW.write(res.toString());
					archivoW.newLine();
					archivoW.close();
					System.out.println("Resultado escrito en achivoF.txt, Escriba finish para terminar el programa");
				}else{
					System.out.println( res);
					System.out.println("Escriba finish para terminar el programa");
				}
			}catch(IOException ioe){
				System.out.println( "Read fail");
			}
			if(line.endsWith("inish")){
				acceptor.endT();
				System.exit(-1);
				break loop;
			}
		}
	}
}
class NewC extends Thread{
	LinkedList<ActualC> clientList;
	Socket client=null;
	ServerSocket server;
	volatile boolean ended,ready=false;;
	BigDecimal finalRes;
	
	
	public NewC(ServerSocket server){
		this.server=server;
		clientList=new LinkedList<ActualC>();
		finalRes=BigDecimal.ZERO;
		ended=true;
	}

	public void endT(){
		for(ActualC x : clientList){
			x.endT();
		}
		ended=false;
	}
	public void sendT(String finale,String precision){
			int abs=Integer.parseInt(finale);
			int clients=clientList.size();
     		boolean mod0=(abs%clients==0)?true:false;
     		int part=abs/clients;
     		int ph=part;
     		int add=0;
     		if(!mod0){
     		add=abs-(part*clients);
     		part+=add;
     		}
			int inicio=0;
			int index=0;
			part=ph;
			ActualC tmpC=clientList.get(index);
			tmpC.setParams(inicio,part+add,Integer.parseInt(precision));
			tmpC.start();
     		inicio+=ph+add+1;
     		part+=ph;
			index++;
			for(;index<clients;index++){
				tmpC=clientList.get(index);
				tmpC.setParams(inicio,part,Integer.parseInt(precision));
				tmpC.start();
     			inicio+=ph;
     			part+=ph;
			}
			LinkedList<ActualC> finished=new LinkedList<ActualC>();
			while(finished.size()!=clients){
				index=0;
				for(ActualC x : clientList){
					if(x.finish){
						finalRes=finalRes.add(new BigDecimal(x.getRes()));
						finished.add(clientList.remove(index));
						System.out.println("finished: "+finished.size());
					}else{
						index++;
					}
				}
			}
		for(ActualC x: finished){
			clientList.add(x);
		}
		ready=true;
	}
	public boolean getReady(){
		return ready;
	}
	public BigDecimal getRes(){
		return finalRes;
	}
	public void run(){
		try{
			while(ended==true){
				client = server.accept();
				clientList.add(new ActualC(client));
				System.out.println(clientList.size());
			}
		}catch(IOException ioe){
			System.out.println( "Error em accept");
			System.exit(-1);
		}
	}
}

class ActualC extends Thread{
	Socket client;
	BufferedReader in=null;
	PrintWriter out=null;
	volatile boolean halt=true,finish=false;
	int inicio,finale,precision;
	private String res;
	
	public ActualC(Socket client){
		this.client=client;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
		}catch(IOException ioe){
			System.out.println( "Couldnt acces client IO");
			System.exit(-1);
		}
	}
	public void run(){
		try{
			while(halt){
				
			}
			out.println(Integer.toString(inicio)+","+Integer.toString(finale)+","+Integer.toString(precision)+",sucesion");
			res=in.readLine();
			finish=true;
			halt=true;
		}catch(IOException ioe){
			System.out.println( "Error em accept");
			System.exit(-1);
		}
	}
	public void setParams(int inicio,int finale, int precision){
		this.inicio=inicio;
		this.finale=finale;
		this.precision=precision;
		halt=false;
	}
	public String getRes(){
		return res;
	}
	
	public void endT(){
		out.println("finish");
		
	}
	
}
