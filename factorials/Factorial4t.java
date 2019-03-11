import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.math.BigInteger;

public class Factorial4t extends Thread{ 
	private int inicio;
	private long finale;
	private BigInteger curr;
	BigInteger resP=BigInteger.ONE;
	LinkedList<BigInteger> pd;
	static BigInteger res=BigInteger.ONE;

	public Factorial4t (int inicio,int finale,LinkedList<BigInteger> pd){
		int tam= pd.size();
		//System.out.print( finale+",");
		if(finale==0){
			if(tam<1){
				pd.add(new BigInteger("1"));
			}
		}
		int tmp= finale/10000;
		finale++;
      this.inicio=inicio;
		this.finale=finale;
		this.pd=pd;
		curr=new BigInteger(Integer.toString(inicio));
		if(tam>(tmp)&&(tam>0)){
			resP=pd.get(tmp);
			this.inicio=0;
			this.finale=0;
		}else if(false/*tam>1*/){
			resP=pd.getLast();
			this.inicio=tam*10000;
			System.out.print(inicio+" ");
			curr= new BigInteger(Integer.toString(++inicio));
			//System.out.println(curr.toString());
		}
   }
	public void run(){
		BigInteger tenT= new BigInteger("10000");
		for(;inicio<finale;inicio++){
			//System.out.println(curr.toString());
			resP=curr.multiply(resP);
			if(curr.mod(tenT).equals(BigInteger.ZERO)&&(inicio/10000)>pd.size()){
				System.out.println( curr.toString()+" "+curr.bitLength());
				//pd.add(resP);
			}
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
		Factorial4t t1= new Factorial4t(1,250000,pd);
		Factorial4t t2= new Factorial4t(250001,500000,pd);
		Factorial4t t3= new Factorial4t(500001,750000,pd);
		Factorial4t t4= new Factorial4t(750001,1000000,pd);
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
