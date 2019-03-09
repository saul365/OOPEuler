public class EConHilos extends Thread{
    String atributo;
    
    public EConHilos(int i) {
        atributo = "variable usando for de :" +i; 
    }

    public void run() {
        long it = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++)
            System.out.println(i + ": " + atributo);
        System.out.println("Tiempo Transcurrido:"+(System.currentTimeMillis()-it)/1000 + " seg ");
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            EConHilos ech = new EConHilos(i);
	    ech.setPriority(10);
            ech.start();
        }
    }

}

