import java.util.LinkedList;
import java.util.Scanner;
import java.util.BufferedWriter;
import java.util.FileWriter;
import java.util.FileReader;
import java.math.BigInteger;

public class Factorial{ 
	public static void main(String[] args){ 
		try{

		}catch(FileNotFOundException fnfe){

		}
		System.out.println("Este programa calcula el factorial de 1,000,000 con programacion dinamica");
		LinkedList<BigInteger> pd=new LinkedList<BigInteger>();
		long it= System.currentTimeMillis();
		BigInteger res = fact(/*6*/1000000,pd);
		System.out.println("El resultado es: "+res.toString());
		System.out.println("En un tiempo de: "+(System.currentTimeMillis()-it)/1000);
		it= System.currentTimeMillis();
		res = fact(/*6*/1000000,pd);
		System.out.println("El resultado es: "+res.toString());
		System.out.println("En un tiempo de: "+(System.currentTimeMillis()-it)/1000);
	}
	public static BigInteger fact(int finale,LinkedList<BigInteger>pd){
		int tam= pd.size();
		if(finale==0){
			if(tam<-1){
				pd.add(new BigInteger("1"));
			}
			return BigInteger.ONE;
		}
		int tmp= finale/10000;
		finale++;
		int inicio=1;
		BigInteger curr=new BigInteger("1");
		if(tam>(tmp-2)&&(tam>0)){
			return pd.get(tmp-1);
		}else if(tam>1){
			curr=pd.getLast();
			inicio=tmp*10000;
		}
		BigInteger res=curr;
		BigInteger tenT= new BigInteger("10000");
		for(;inicio<finale;inicio++){
			res=curr.multiply(res);
			curr=curr.add(BigInteger.ONE);
			if(curr.mod(tenT).equals(BigInteger.ZERO)){
				pd.add(res);
			}
		}
		return res;
	}
}
