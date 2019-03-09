import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class Escritor{
	public static void main(String[] args){
		try{
			BufferedWriter bw= new BufferedWriter(new FileWriter("prueba2.txt"));
			bw.write(Integer.toString(547));
			bw.newLine();
			bw.write(Integer.toString(81));
			bw.newLine();
			bw.close();
		}
		catch(IOException ex){
			System.out.println( "no jaló compa");
		}
	}	
}

