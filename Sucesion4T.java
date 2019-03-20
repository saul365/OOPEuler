import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.BigInteger;

public class Sucesion4T{ 
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
		System.out.println(Sucesion4T.solve(100000));
	}
}
