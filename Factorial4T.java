

public class Factorial4T{ 
   FactorialT t1;
   FactorialT t2;
   FactorialT t3;
   FactorialT t4;

   public Factorial4T(int inicio,int finale){      
      int abs=finale-inicio;
		boolean mod0=(abs%4==0)?true:false;
		int part=abs/4;
		int ph=part;
		int add=1;
      if(mod0){
         part++;
			add=2;
      }
      t1=new FactorialT(inicio,part);
		inicio+=ph+add;
		part+=ph;
      t2=new FactorialT(inicio,part);
		inicio+=ph+1;
		part+=ph;
      t3=new FactorialT(inicio,part);
		inicio+=ph+1;
		part+=ph;
      t4=new FactorialT(inicio,part);
		if(part==finale){
         System.out.println("Exito empresarial");
      }else{
         System.out.println("Error en este pedo");
      }
   }
	public startOp(){
		  System.out.println("ignora esto");
   }
	public static void main(String args[]){ 

	}
}
