import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.math.BigInteger;

public class FactorialT extends Thread{ 
	private int inicio;
	private long finale;
	private BigInteger curr;
	BigInteger resP=BigInteger.ONE;
	static BigInteger res=BigInteger.ONE;

	public FactorialT (int inicio,int finale){
		//System.out.print( finale+",");
		int tmp= finale/10000;
		finale++;
      this.inicio=inicio;
		this.finale=finale;
		curr=new BigInteger(Integer.toString(inicio));
   }
	public void run(){
		BigInteger tenT= new BigInteger("10000");
		for(;inicio<finale;inicio++){
			//System.out.println(curr.toString());
			resP=curr.multiply(resP);
			curr=curr.add(BigInteger.ONE);
		}
		System.out.println("Multiplicado en "+this.getId());
	}
	public static BigInteger getRes(){
		return res;
	}
	public static void main(String[] args) throws InterruptedException{ 
		String archivo="archivo.txt";
		try{
			Scanner reader=new Scanner(new File(archivo));
		}catch(FileNotFoundException fnfe){

		}
		System.out.println("Este programa calcula el factorial de 1,000,000 con programacion dinamica");
		LinkedList<BigInteger> pd=new LinkedList<BigInteger>();
		long it= System.currentTimeMillis();
		BigInteger res; 
		//System.out.println( new BigInteger("1000000").bitLength());
		FactorialT t1= new FactorialT(1,250000);
		FactorialT t2= new FactorialT(250001,500000);
		FactorialT t3= new FactorialT(500001,750000);
		FactorialT t4= new FactorialT(750001,1000000);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t4.join();
		t3.join();
		t2.join();
		t1.join();
		Collections.sort(pd);
		System.out.println("En un tiempo de: "+(System.currentTimeMillis()-it)/1000);
		it= System.currentTimeMillis();
		res = t1.resP.multiply(t2.resP.multiply(t3.resP.multiply(t4.resP)));
		try{
			BufferedWriter archivoW= new BufferedWriter(new FileWriter(archivo));
			archivoW.write(res.toString());
			archivoW.newLine();
			archivoW.close();
		}catch(IOException ioe){
			System.out.println( "Fallo al escribir");
		}
	}
}