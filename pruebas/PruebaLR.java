import java.util.Scanner;
import java.math.BigDecimal;

public class PruebaLR{ 
//y=ax+b; a=0.2713492409
//b=440.3146055300
	static private BigDecimal a=new BigDecimal("0.2306756528");
	//y=ax+b
	static private BigDecimal b= new BigDecimal("-1416.8723317407"); 
	static public BigDecimal linearR(String num){
		BigDecimal number=new BigDecimal(num);
		BigDecimal pendent=a.multiply(number);
		System.out.println(pendent);
		BigDecimal res=pendent.add(b);
		return res;
	}

	public static void main(String args[]){ 
		Scanner read=new Scanner(System.in);
		System.out.println("Ingrese el número al que le gustaria saber el log10 de su factorial: ");
		String num=read.next();
		System.out.println( linearR(num).toPlainString());

	}
}
