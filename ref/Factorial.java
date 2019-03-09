import java.math.*;
public class Factorial{

    public void getFactorial(int esup,long it){
	BigDecimal temp = new BigDecimal("1");
	for(int i=1;i<=esup;i++){
	    BigDecimal ii  = new BigDecimal(i);
	    temp=temp.multiply(ii);
	}
        System.out.println("El valor del factorial es:"+temp); 
        System.out.println("EN EL TIEMPO:"+(System.currentTimeMillis()-it)/1000 + "seg");
    }

    public static void main( String args[] ) {
	long initialTime = System.currentTimeMillis();
	Factorial f = new Factorial();
	f.getFactorial(1000000,initialTime);
    }    
}
