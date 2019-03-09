public class ESinHilos {
    String atributo;
int a;
    
    public ESinHilos(int i) {
        atributo = "variable usando for de :" + i;
    }

    public void ejecutar() {
        for (int i = 0; i < 1000000; i++)
           System.out.println(i + ": " + atributo);
    }
    
    public static void main(String[] args) {
        long it = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            ESinHilos esh = new ESinHilos(i);
            esh.ejecutar();
        }
        System.out.println("Tiempo Transcurrido:"+(System.currentTimeMillis()-it)/1000 + " seg ");

    }

}
