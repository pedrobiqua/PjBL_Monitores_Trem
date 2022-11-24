import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("--------------------------------------------");
        System.out.println("PjBL - Monitores com 4 variaveis");
        System.out.println("Aluno: Pedro B.");
        System.out.println("--------------------------------------------");

        // Sentido A
        boolean[] posA = new boolean[4];
        Arrays.fill(posA, false);
        posA[0] = true;

        // Sentido B
        boolean[] posB = new boolean[4];
        Arrays.fill(posB, false);
        posB[1] = true;

        Arrays.fill(LinhaTrem.pontoDeJuncao, -1);

        Maquinista maquinistaHr = new Maquinista(0, posA, true, 3);
        Maquinista maquinistaAHr = new Maquinista(1, posB, true, 3);
        GeraThread geraThread = new GeraThread(maquinistaHr, maquinistaAHr);
        
        geraThread.start();
    }
}
