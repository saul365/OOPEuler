import java.util.LinkedList;
import java.util.Scanner;
import java.math.BigInteger;

public class Factorial{ 
	public static void main(String[] args){ 
		System.out.println("Este programa calcula el factorial de 1,000,000 con programacion dinamica");
		LinkedList<BigInteger> pd=new LinkedList<BigInteger>();
		BigInteger res = fact(6/*1000000*/,pd);
		System.out.println("El resultado es: "+res.toString());
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
			return pd.get(tmp);
		}else if(tam>1){
			curr=pd.getLast();
			inicio=tmp*10000;
		}
		BigInteger res=curr;
		for(;inicio<finale;inicio++){
			res=curr.multiply(res);
			curr=curr.add(BigInteger.ONE);
		}
		return res;
	}
}
