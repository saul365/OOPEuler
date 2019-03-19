import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.math.MathContext;
import java.util.LinkedList;

public class Sucesion{ 
	public static void main(String args[]){ 
		LinkedList<BigInteger> pd= new LinkedList<BigInteger>();
		BigDecimal resultadoI = new BigDecimal("0");
		BigDecimal current = new BigDecimal("0",MathContext.UNLIMITED);
		BigDecimal dac = new BigDecimal("1",MathContext.UNLIMITED);
		int digits=10000;
		
		System.out.print( "Este programa calcula la serie de euler hasta el decimo elemento usando BigDecimal para almacenar los resultados.\n");
		long iT= System.currentTimeMillis();
		for(int i=0; i<digits;i++){
			resultadoI=dac.divide(new BigDecimal(factorial(i,pd)),digits,RoundingMode.HALF_UP);
			current=current.add(resultadoI);
			//System.out.println( "Numero "+ i +" de la sucesion"+" = "+ current.toPlainString()); 
		}
		System.out.println( "Numero "+ digits +" de la sucesion"+" = "+ current.toPlainString()); 
		System.out.println("Tiempo: "+ (System.currentTimeMillis()-iT)/1000 + "seg");
	}
	static public BigInteger factorial(int j,LinkedList<BigInteger> pd){
		BigInteger curr=new BigInteger("0");
		BigInteger res=new BigInteger("1");
		int tam=pd.size();
		int inicio=0;
		j-=2;
		if(tam>=j&&tam<0){
			return(pd.get(j-1));
		}else if(tam>2){
			curr= new BigInteger(Integer.toString(tam));
			res=pd.getLast();
			inicio=tam;
		}
		j+=3;
		for(int i=inicio;i<j;i++){
			if(i<2){
				curr=curr.add(BigInteger.ONE);
				if((i==1&&tam<2)||(i==0 && tam<1)){
					pd.add(new BigInteger("1"));
				}
			}else{
				res=curr.multiply(res);
				curr=curr.add(BigInteger.ONE);
				pd.add(res);
			}
		}
		return res;
	}
}
