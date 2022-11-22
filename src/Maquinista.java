import java.lang.reflect.Array;
import java.util.Arrays;

public class Maquinista {

    /**
     * 0 - Sentido A
     * 1 - Sentido B
     */
    private int sentidoTrem;

    /**
     * Posição do trem : |
     * 0 - Posição 1 |
     * 1 - Posição 2 |
     * 2 - Posição 3 |
     * 3 - Posição 4 |
     */
    private boolean[] posicaoTrem;

    /**
     * Posição onde o trem está
     */
    private int casaTrem;

    /**
     * Setor do trem
     */
    private int setorTrem = 0;

    /**
     * True - Trem andando
     * False - Trem parado
     */
    private boolean andandoTrem;

    // TODO: Refazer a logica do motorista, pois com a lógica atual não vai ser possivel validar o ponto de junção
    public Maquinista(int sentidoTrem, boolean[] posicaoTrem, boolean pontoDeJuncao, boolean andandoTrem) {
        this.sentidoTrem = sentidoTrem;
        this.posicaoTrem = posicaoTrem;
        this.andandoTrem = andandoTrem;
        andandoTrem = true;
    }


    public synchronized void andarTrem(int novaCasa) throws InterruptedException {

        // TODO: Tem que adicionar as condiçoes do trem andar
        System.out.println("Motorista<STATUS>: Andando trem");
        System.out.println("Motorista<STATUS>: Trem no sentido " + (sentidoTrem == 0 ? "A" : "B"));

        if ( casaTrem > posicaoTrem.length - 1) {
            casaTrem = 0;
        }

        if (!posicaoTrem[novaCasa]) {
            /*
            System.out.println("Motorista<STATUS>: Trem na posição " + (novaCasa + 1));
            posicaoTrem[casaTrem] = false;
            casaTrem = novaCasa;
            posicaoTrem[casaTrem] = true;
            andandoTrem = true;
            */

            // Sentido horário
            if (sentidoTrem == 0) {
                if ((setorTrem % 2) == 0) {
                    Arrays.fill(LinhaTrem.posicaoLinhaB, false);
                    //LinhaTrem.posicaoLinhaB[casaTrem] = false;
                    //casaTrem = novaCasa;
                    LinhaTrem.posicaoLinhaA[casaTrem] = true;
                    //casaTrem = novaCasa;
                    setorTrem++;
                    System.out.println(Arrays.toString(LinhaTrem.posicaoLinhaA));
                    System.out.println(Arrays.toString(LinhaTrem.posicaoLinhaB));
                } else {
                    novaCasa = casaTrem + novaCasa;
                    Arrays.fill(LinhaTrem.posicaoLinhaA, false);
                    //LinhaTrem.posicaoLinhaA[casaTrem] = false;
                    LinhaTrem.posicaoLinhaB[casaTrem] = true;
                    casaTrem = novaCasa;
                    setorTrem--;
                    System.out.println(Arrays.toString(LinhaTrem.posicaoLinhaA));
                    System.out.println(Arrays.toString(LinhaTrem.posicaoLinhaB));
                }
            
            // Sentido anti-horário
            } else {
                if ((casaTrem % 2) == 0) {
                    LinhaTrem.posicaoLinhaA[casaTrem] = false;
                    casaTrem = novaCasa;
                    LinhaTrem.posicaoLinhaB[casaTrem] = true;
                } else {
                    LinhaTrem.posicaoLinhaB[casaTrem] = false;
                    LinhaTrem.posicaoLinhaA[casaTrem] = true;
                    casaTrem = novaCasa;
                }
            }
            
        }

        Thread.sleep(1000);
        
    }

    public synchronized void estacionarTrem() throws InterruptedException {
        System.out.println("Motorista<STATUS>: Estacionando trem");
        Thread.sleep(1000);
        
    }

    public synchronized void esperarTrem() throws InterruptedException {
        System.out.println("Motorista<STATUS>: Esperando trem");
        Thread.sleep(1000);
    }


    // Getters e Setters
    public int getSentidoTrem() {
        return sentidoTrem;
    }

    public boolean[] getPosicaoTrem() {
        return posicaoTrem;
    }

    public void setPosicaoTrem(boolean[] posicaoTrem) {
        this.posicaoTrem = posicaoTrem;
    }

    public void setAndandoTrem(boolean andandoTrem) {
        this.andandoTrem = andandoTrem;
    }

    public void setSentidoTrem(int sentidoTrem) {
        this.sentidoTrem = sentidoTrem;
    }

    // Fim Getters e Setters

    public boolean isAndandoTrem() {
        return andandoTrem;
    }
}
