import java.util.Random;

public class GeraThread extends Thread {
    private Maquinista motoristaHr;
    private Maquinista motoristaAHr;

    private Random aleatorio;
    public int tempo;

    public GeraThread(Maquinista motoristaHr, Maquinista motoristaAHr) {
        this.motoristaHr = motoristaHr;
        this.motoristaAHr = motoristaAHr;
        aleatorio = new Random();
    }

    int contador = 0;

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
        try {
            int usandoJuncao = LinhaTrem.quemEstaUsandoAJuncao();
            if (usandoJuncao == 0) {
                Trem trem = new TremParado(motoristaHr);
                trem.start();

                Trem trem2 = new TremAndando(motoristaAHr);
                trem2.start();

                contador++;

            } else if (usandoJuncao == 1) {
                Trem trem = new TremParado(motoristaAHr);
                trem.start();

                Trem trem1 = new TremAndando(motoristaHr);
                trem1.start();

                contador++;
            } else {
                if (contador % 2 == 0) {
                    Trem trem = new TremAndando(motoristaHr);
                    trem.start();
                    //trem.join();
                } else {
                    Trem trem = new TremAndando(motoristaAHr);
                    trem.start();
                    //trem.join();
                }
                contador++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
