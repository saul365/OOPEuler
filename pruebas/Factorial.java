import java.math.BigInteger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Factorial{	
	public static void main(String args[]){ 
		BigInteger act= new BigInteger("1");
		BigInteger res=Factorial4T.solve(1);
		//int[] nums=new int[]{10,100,1000,10000,100000,1000000,10000000};
		int indexN=0;
		String index="1";
		BigInteger block=new BigInteger(index).pow(indexN);
		index=block.toString();
		try{
			File archivo=new File("equivalencias3.txt");
			BufferedWriter bw=new BufferedWriter(new FileWriter(archivo,false));
			for(int i=1;i<1001;i++){
				res=act.multiply(res);
				if(res==res.max(block)){
					System.out.println(act.toString()+"-"+ indexN);	
					loop:for(;;){
						index=index.concat("0");
						block=new BigInteger(index);
						//block=new BigInteger(Integer.toString(nums[index]));
						if(block==res.max(block)){
							indexN++;
							break loop;
						}
						indexN++;
					}
					bw.write((indexN-1)+"-"+act.toString());
					bw.newLine();
				}
				//System.out.println(act.toString());	
				act=act.add(BigInteger.ONE);
			}
			bw.close();
		}catch(IOException ioe){
			System.out.println( "error");
		}
	}
}
