import java.util.Scanner;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.File;
import java.io.FileNotFoundException;


public class Suma{ 
		public static void main(String args[]){
			Scanner lector;
			BigInteger tot=BigInteger.ZERO;
			BigInteger totx=BigInteger.ZERO;
			BigInteger toty=BigInteger.ZERO;
			BigInteger totxp=BigInteger.ZERO;
			BigInteger tmpx;
			BigInteger tmpy;
			StringTokenizer st;
			int i= 1;
			try{
				lector=new Scanner(new File("final.txt"));
				while(lector.hasNext()){
					st=new StringTokenizer(lector.next(),"-");
					tmpy=new BigInteger(st.nextToken());
					tmpx=new BigInteger(st.nextToken());
					tot=tot.add(tmpx.multiply(tmpy));
					totx=totx.add(tmpx);
					toty=toty.add(tmpy);
					totxp=totxp.add(tmpx.pow(2));
				}
				System.out.println("Suma de las multiplicaciones: "+tot.toString());
				System.out.println("Suma de x: "+totx.toString());
				System.out.println("Suma de y: "+toty.toString());
				BigInteger xp;
				BigInteger sxp;
				System.out.println("Suma de x^2: "+(xp=totxp).toString());
				System.out.println("Suma^2 de x: "+(sxp=totx.pow(2)).toString());
				BigInteger n=new BigInteger("11000");
				BigInteger top=(n.multiply(tot)).subtract(totx.multiply(toty));
				BigInteger bottom=(n.multiply(xp).subtract(sxp));
				BigDecimal a=new BigDecimal(top).divide(new BigDecimal(bottom),10,RoundingMode.HALF_UP);
				//BigDecimal b=(new BigDecimal(toty).divide(new BigDecimal(n),10,RoundingMode.HALF_UP).subtract(a.multiply(new BigDecimal(totx)).divide(new BigDecimal(n),10,RoundingMode.HALF_UP)));
				BigDecimal b= (new BigDecimal(toty).subtract(a.multiply(new BigDecimal(totx)))).divide(new BigDecimal(n),10,RoundingMode.HALF_UP);
				System.out.println( "y=ax+b; a="+a.toPlainString()+"\nb="+b.toPlainString());
			}catch(FileNotFoundException fnfe){

			}
			
		}
}
