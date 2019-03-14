

public class Factorial4T{ 
   FactorialT t1;
   FactorialT t2;
   FactorialT t3;
   FactorialT t4;

   public Factorial4T(int inicio,int finale){      
      int abs=finale-inicio+1;
		boolean mod0=(abs%4==0)?true:false;
		int part=abs/4;
		int ph=part;
		int add=0;
      if(mod0){
         part++;
			add=1;
      }
      t1=new FactorialT(inicio,part);
		inicio+=ph+add;
		part+=ph;
      t2=new FactorialT(inicio,part);
		inicio+=ph;
		part+=ph;
      t3=new FactorialT(inicio,part);
		inicio+=ph;
		part+=ph;
      t4=new FactorialT(inicio,part);
		if(part==finale){
         System.out.println("Exito empresarial");
      }else{
         System.out.println("Error en este pedo");
      }
   }
	public void startOp(){
		  System.out.println("ignora esto");
   }
	public static void main(String args[]){ 
		Factorial4T try1=new Factorial4T(1,1000000);

	}
}
