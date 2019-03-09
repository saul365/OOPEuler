import java.math.*;
public class Factorial8T extends Thread{
   int finf;
   int fsup;
   long it;
   BigInteger fact = BigInteger.ONE;
   static BigInteger resultado;

   public Factorial8T(int finf, int fsup,long it){
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
         System.out.println("EN EL TIEMPO:"+(System.currentTimeMillis()-it)/1000 + "seg ");
	 fact=temp;
    }

    public static BigInteger getFactorial(){
	 return resultado;
    }

    public static void main( String args[] ) throws InterruptedException {
	long initialTime = System.currentTimeMillis();
        BigInteger resultado1= BigInteger.ONE;
        BigInteger resultado2= BigInteger.ONE;
	Factorial8T f1 = new Factorial8T(1,125000,initialTime);
	Factorial8T f2 = new Factorial8T(125001,250000,initialTime);
	Factorial8T f3 = new Factorial8T(250001,375000,initialTime);
	Factorial8T f4 = new Factorial8T(375001,500000,initialTime);
	Factorial8T f5 = new Factorial8T(500001,625000,initialTime);
	Factorial8T f6 = new Factorial8T(625001,750000,initialTime);
	Factorial8T f7 = new Factorial8T(750001,875000,initialTime);
	Factorial8T f8 = new Factorial8T(875001,1000000,initialTime);
	f8.start();
	f7.start();
	f6.start();
	f5.start();
	f4.start();
	f3.start();
	f2.start();
	f1.start();
	f8.join();
	f7.join();
	f6.join();
	f5.join();
	f4.join();
	f3.join();
	f2.join();
	f1.join();
        resultado1=f1.fact.multiply(f2.fact.multiply(f3.fact.multiply(f4.fact)));
        resultado2=f5.fact.multiply(f6.fact.multiply(f7.fact.multiply(f8.fact)));
	resultado=resultado1.multiply(resultado2);
	System.out.println("El valor del FACTORIAL:"+getFactorial()); 
        System.out.println("EN EL TIEMPO:"+(System.currentTimeMillis()-initialTime)/1000 + "seg ");

    }    
}
