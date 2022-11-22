import java.util.Random;

public class GeraThread extends Thread {
    private Maquinista motoristaHr;
    //private Maquinista motoristaAHr;

    private Random aleatorio;
    public int tempo;

    public GeraThread(Maquinista motoristaHr) {
        this.motoristaHr = motoristaHr;
        // this.motoristaAHr = motoristaAHr;
        aleatorio = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                geraThreadAndarTrem();
                tempo = aleatorio.nextInt(500);
                Thread.sleep(tempo);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gera threads para andar o trem
     */
    public void geraThreadAndarTrem(){
        /*
        int opcoes = aleatorio.nextInt(2);
        if (opcoes == 0) {
            Trem trem1 = new Trem(motoristaHr);
            trem1.start();
        } else {
            Trem trem2 = new Trem(motoristaAHr);
            trem2.start();
        }
        */

        Trem trem1 = new Trem(motoristaHr);
        trem1.start();
    }
}
