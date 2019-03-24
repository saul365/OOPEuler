import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.BigInteger;

public class Sucesion4T{ 

/*
	public Sucesion4T(int inicio,in finale){
		
	}
*/
	public static BigDecimal solve(int number){
		number++;
		BigDecimal res=BigDecimal.ZERO;;
		BigInteger fact;
		for(int i=0;i<number;i++){
			res=res.add(new BigDecimal(Integer.toString(i)).divide(new BigDecimal(Factorial4T.solve(i)),20,RoundingMode.HALF_UP));
		}
		return res;
	}
	public static void main(String[] args){ 
		SucesionT prueba=new SucesionT(0,20,10);
		prueba.start();
		System.out.println(prueba.resultado.toString());
	}
}

class SucesionT extends Thread{
	BigDecimal resultado;
	//private BigDecimal tmp;
	private BigInteger fact;
	private BigInteger actual;
	private int inicio;
	private int finale;
	private int precision;

	
	public SucesionT(int inicio,int finale,int precision){
		this.inicio=inicio;
		this.finale=finale;
		this.precision=precision;
		resultado=BigDecimal.ZERO;
		fact=Factorial4T.solve(inicio);
		actual=new BigInteger(Integer.toString(inicio+1));
	}
	public void run(){
		precision++;
		finale++;
		for(;inicio<finale;inicio++){
			resultado=resultado.add(BigDecimal.ONE.divide(new BigDecimal(fact),precision,RoundingMode.HALF_UP));
			System.out.println(resultado.toString());
			fact=actual.multiply(fact);
			actual=actual.add(BigInteger.ONE);
		}
	}
}
