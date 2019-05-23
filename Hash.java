//package OOPEuler;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.File;
import java.io.FileNotFoundException;


public class Hash{ 
		public static Hashtable MTable(){
			Scanner lector;
			String tmpfact,tmppow;
			StringTokenizer st;
			Hashtable tabla=new Hashtable(1000000);
			try{
				lector=new Scanner(new File("final.txt"));
				while(lector.hasNext()){
					st=new StringTokenizer(lector.next(),"-");
					tmpfact=st.nextToken();
					tmppow=st.nextToken();
					tabla.put(tmppow,tmpfact);
				}
				System.out.println( tabla.get("0"));
				lector.close();
			}catch(FileNotFoundException fnfe){

			}
			return tabla;
			
		}
}
