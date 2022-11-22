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

    public Maquinista(int sentidoTrem, boolean[] posicaoTrem, boolean andandoTrem, int posicaoInicial) {
        this.sentidoTrem = sentidoTrem;
        this.posicaoTrem = posicaoTrem;
        this.andandoTrem = andandoTrem;
        if (posicaoInicial == 1) {
            this.casaTrem = 0;
        } else if (posicaoInicial == 2) {
            this.casaTrem = 1;
        } else if (posicaoInicial == 3) {
            this.casaTrem = 2;
        } else if (posicaoInicial == 4) {
            this.casaTrem = 3;
        } else {
            this.casaTrem = 0;
        }
    }


    public synchronized void andarTrem(int novaCasa) throws InterruptedException {

        // TODO: Tem que adicionar as condiçoes do trem andar
        System.out.println("Motorista<STATUS>: Andando trem");
        System.out.println("Motorista<STATUS>: Trem no sentido " + (sentidoTrem == 0 ? "A" : "B"));

        if (sentidoTrem != 0) {
            if (casaTrem == -1) {
                casaTrem = posicaoTrem.length - 1;
            }
        } else {
            if ( casaTrem > posicaoTrem.length - 1) {
                casaTrem = 0;
            }
        }

        // Sentido horário
        if (sentidoTrem == 0) {
            if ((setorTrem % 2) == 0) {
                Arrays.fill(LinhaTrem.posicaoLinhaB, false);
                LinhaTrem.posicaoLinhaA[casaTrem] = true;
                setorTrem++;
                System.out.println("      |  1  |  2  |  3  |  4  |");
                System.out.println("A = " + Arrays.toString(LinhaTrem.posicaoLinhaA));
                System.out.println("B = " + Arrays.toString(LinhaTrem.posicaoLinhaB));
            } else {
                novaCasa = casaTrem + novaCasa;
                Arrays.fill(LinhaTrem.posicaoLinhaA, false);
                LinhaTrem.posicaoLinhaB[casaTrem] = true;
                casaTrem = novaCasa;
                setorTrem--;
                System.out.println("      |  1  |  2  |  3  |  4  |");
                System.out.println("A = " + Arrays.toString(LinhaTrem.posicaoLinhaA));
                System.out.println("B = " + Arrays.toString(LinhaTrem.posicaoLinhaB));
            }
        
        // Sentido anti-horário
        } else {
            if ((setorTrem % 2) == 0) {
                Arrays.fill(LinhaTrem.posicaoLinhaA, false);
                LinhaTrem.posicaoLinhaB[casaTrem] = true;
                setorTrem++;
                System.out.println("      |  1  |  2  |  3  |  4  |");
                System.out.println("A = " + Arrays.toString(LinhaTrem.posicaoLinhaA));
                System.out.println("B = " + Arrays.toString(LinhaTrem.posicaoLinhaB));
            } else {
                novaCasa = casaTrem - novaCasa;
                Arrays.fill(LinhaTrem.posicaoLinhaB, false);
                LinhaTrem.posicaoLinhaA[casaTrem] = true;
                casaTrem = novaCasa;
                setorTrem--;
                System.out.println("      |  1  |  2  |  3  |  4  |");
                System.out.println("A = " + Arrays.toString(LinhaTrem.posicaoLinhaA));
                System.out.println("B = " + Arrays.toString(LinhaTrem.posicaoLinhaB));
            }
        }

        Thread.sleep(2000);
        
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
