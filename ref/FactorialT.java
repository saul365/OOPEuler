import java.math.*;
public class FactorialT extends Thread{
   int finf;
   int fsup;
   long it;
   BigInteger fact = BigInteger.ONE;
   static BigInteger resultado;

   public FactorialT(int finf, int fsup,long it){
	this.finf=finf;
	this.fsup=fsup;
	this.it=it;
        this.fact  = new BigInteger("1");
   }
	
    public void run(){
	BigInteger temp = new BigInteger("1");
	    for(int i=finf;i<=fsup;i++)
		temp=temp.multiply(BigInteger.valueOf(i));
	 System.out.println("El valor del factorial temporal es:"+temp); 
	fact=temp;
    }

    public static BigInteger getFactorial(){
	 return resultado;
    }

    public static void main( String args[] ) throws InterruptedException {
	long initialTime = System.currentTimeMillis();
	FactorialT f1 = new FactorialT(1,250000,initialTime);
	FactorialT f2 = new FactorialT(250001,500000,initialTime);
	FactorialT f3 = new FactorialT(500001,750000,initialTime);
	FactorialT f4 = new FactorialT(750001,1000000,initialTime);
	f4.start();
	f3.start();
	f2.start();
	f1.start();
	f4.join();
	f3.join();
	f2.join();
	f1.join();
	resultado=f1.fact.multiply(f2.fact.multiply(f3.fact.multiply(f4.fact)));
	System.out.println("El valor del FACTORIAL:"+getFactorial()); 
	System.out.println( "En tiempo :"+ (System.currentTimeMillis()-initialTime));
    }    
}
