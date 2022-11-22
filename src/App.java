import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        // TODO: Fazer andar dois trens
        System.out.println("PjBL - Monitores com 4 variaveis");
        System.out.println("Aluno: Pedro B.");

        // Sentido A
        boolean[] posA = new boolean[4];
        Arrays.fill(posA, false);
        posA[0] = true;

        // Sentido B
        boolean[] posB = new boolean[4];
        Arrays.fill(posB, false);
        posB[1] = true;

        Maquinista maquinistaHr = new Maquinista(1, posA, true, 2);
        // Maquinista maquinistaAHr = new Maquinista(1, posB, false, false);
        GeraThread geraThread = new GeraThread(maquinistaHr);
        
        geraThread.start();
    }
}
