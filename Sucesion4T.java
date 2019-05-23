//package OOPEuler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.BigInteger;

public class Sucesion4T{ 
	private SucesionT t1;
	private SucesionT t2;
	private SucesionT t3;
	private SucesionT t4;
	int inicio, finale, presicion;
	public Sucesion4T(int inicio,int finale,int precision){
		this.finale=finale;
		this.inicio=inicio;
		this.presicion=precision;	
      int abs=finale-inicio;
		boolean mod0=(abs%4==0)?true:false;
		int part=abs/4;
		int ph=part;
		int add=0;
      if(!mod0){
			add=abs-(part*4);
         part+=add;
      }
		part+=inicio;
      t1=new SucesionT(inicio,part,precision);
		//System.out.println(part+" "+ph+" "+inicio);
		inicio+=ph+add+1;
		part+=ph;
      t2=new SucesionT(inicio,part,precision);
		//System.out.println(part+" "+ph+" "+inicio);
		inicio+=ph;
		part+=ph;
      t3=new SucesionT(inicio,part,precision);
		//System.out.println(part+" "+ph+" "+inicio);
		inicio+=ph;
		part+=ph;
      t4=new SucesionT(inicio,part,precision);
		//System.out.println(part+" "+ph+" "+inicio);
		
	}
	public static BigDecimal resuelve(int number,Integer digits){
		number++;
		BigDecimal res=BigDecimal.ZERO;;
		BigInteger fact;
		for(int i=0;i<number;i++){
			res=res.add(new BigDecimal(Integer.toString(i)).divide(new BigDecimal(Factorial4T.solve(i)),digits,RoundingMode.HALF_UP));
		}
		return res;
	}
	public BigDecimal startOp() throws InterruptedException{
		long it=System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		/*t5.start();
		t6.start();
		t7.start();
		t8.start();
		t8.join();
		t7.join();
		t6.join();
		t5.join();*/
		t4.join();
		t3.join();
		t2.join();
		t1.join();
		//System.out.println("En un tiempo de: "+(System.currentTimeMillis()-it)/1000);
		BigDecimal res = t1.resultado.add(t2.resultado.add(t3.resultado.add(t4.resultado)));
		/*
		try{
			BufferedWriter archivoW= new BufferedWriter(new FileWriter(archivo));
			archivoW.write(res.toString());
			archivoW.newLine();
			archivoW.close();
		}catch(IOException ioe){
			System.out.println( "Fallo al escribir");
		}
		*/
		return res;
   }
	public static void main(String[] args){ 
		Sucesion4T prueba=new Sucesion4T(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));//inicio,final,precisión
		try{
			BigDecimal resultado=prueba.startOp();
			System.out.println(resultado.toPlainString());
		}catch(InterruptedException ie){

		}
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
		//System.out.println(fact.toString()+" in="+inicio);
		actual=new BigInteger(Integer.toString(inicio));
	}
	public void run(){
		//precision++;
		finale++;
		for(;inicio<finale;inicio++){
			System.out.println("Fact ="+fact.toString()+" act="+actual.toString());
			actual=actual.add(BigInteger.ONE);
			resultado=resultado.add(BigDecimal.ONE.divide(new BigDecimal(fact),precision,RoundingMode.HALF_UP));
			fact=actual.multiply(fact);
		}
		//System.out.println(resultado.toPlainString()+" in "+Thread.currentThread()+" in="+inicio+" fin="+finale);
	}
}
