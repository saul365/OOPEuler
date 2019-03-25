import java.math.BigInteger;

public class Factorial{	
	public static void main(String args[]){ 
		BigInteger act=BigInteger.ONE;
		BigInteger res=BigInteger.ONE;
		//int[] nums=new int[]{10,100,1000,10000,100000,1000000,10000000};
		String index="10";
		int indexN=1;
		BigInteger block=new BigInteger(index);
		for(int i=1;i<50001;i++){
			res=act.multiply(res);
			if(res==res.max(block)){
				loop:for(;;){
					index=index.concat("0");
					indexN++;
					block=new BigInteger(index);
					//block=new BigInteger(Integer.toString(nums[index]));
					if(block==res.max(block)){
						break loop;
					}
				}
				System.out.println(act.toString()+"-"+ indexN);	
			}
			//System.out.println(act.toString());	
			act=act.add(BigInteger.ONE);
		}
	}
}
