import java.util.LinkedList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.math.BigInteger;

public class Factorial4t extends Thread{ 
		  private long inicio;
		  private long finale;
		  private BigInteger curr;
	public Factorial4t(int inicio,int finale,LinkedList<BigInteger>pd){
		int tam= pd.size();
		//System.out.print( finale+",");
		if(finale==0){
			if(tam<1){
				pd.add(new BigInteger("1"));
			}
			return BigInteger.ONE;
		}
		int tmp= finale/10000;
		finale++;
      this.inicio=inicio;
		this.finale=finale;
		curr=new BigInteger("1");
		BigInteger resP=curr;
		if(tam>(tmp)&&(tam>0)){
			return pd.get(tmp);
		}else if(tam>1){
			resP=pd.getLast();
			this.inicio=tam*10000;
			//System.out.print(inicio+" ");
			curr= new BigInteger(Integer.toString(++inicio));
			//System.out.println(curr.toString());
		}
   }
	public void run(){
		BigInteger tenT= new BigInteger("10000");
		for(;inicio<finale;inicio++){
			//System.out.println(curr.toString());
			resP=curr.multiply(res);
			if(curr.mod(tenT).equals(BigInteger.ZERO)&&(inicio/10000)>pd.size()){
				System.out.println( curr.toString()+" "+curr.bitLength());
				pd.add(res);
			}
			curr=curr.add(BigInteger.ONE);
		}
		return res;
	}
	public static void main(String[] args){ 
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
		for(int i=0;i<1000001;i+=10000){
			res = fact(/*6*/i,pd);
		}
		System.out.println("En un tiempo de: "+(System.currentTimeMillis()-it)/1000);
		it= System.currentTimeMillis();
		res = fact(/*6*/1000000,pd);
		try{
			BufferedWriter archivoW= new BufferedWriter(new FileWriter(archivo));
			archivoW.write(res.toString());
			archivoW.newLine();
			archivoW.close();
		}catch(IOException ioe){
			System.out.println( "Fallo al escribir");
		}
		System.out.println("En un tiempo de: "+(System.currentTimeMillis()-it)/1000);
	}
}
