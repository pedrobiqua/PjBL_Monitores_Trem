import java.util.ArrayList;
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
     * Posição do trem no ponto A
     */
    private boolean[] posicaoTremA;

    /**
     * Posição do trem no ponto B
     */
    private boolean[] posicaoTremB;

    /**
     * Posição onde o trem está
     */
    private int casaTrem;

    /**
     * Posição onde o trem estava
     */
    private int casaTremAnterior;

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
            this.casaTremAnterior = 3;
            this.casaTrem = 0;
        } else if (posicaoInicial == 2) {
            this.casaTremAnterior = 0;
            this.casaTrem = 1;
        } else if (posicaoInicial == 3) {
            this.casaTremAnterior = 1;
            this.casaTrem = 2;
        } else if (posicaoInicial == 4) {
            this.casaTremAnterior = 2;
            this.casaTrem = 3;
        } else {
            this.casaTremAnterior = 3;
            this.casaTrem = 0;
        }
        this.posicaoTremA = new boolean[4];
        this.posicaoTremB = new boolean[4];
    }


    public synchronized void andarTrem(int novaCasa) throws InterruptedException {

        // TODO: Tem que adicionar as condiçoes do trem andar
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
                //Arrays.fill(LinhaTrem.posicaoLinhaB, false);
                LinhaTrem.posicaoLinhaB[casaTremAnterior] = false;
                this.posicaoTremB[casaTremAnterior] = false;
                LinhaTrem.posicaoLinhaA[casaTrem] = true;
                this.posicaoTremA[casaTrem] = true;
                setorTrem++;

                ArrayList<String> texto = new ArrayList<String>();
                String cabecalho = "      |  1  |  2  |  3  |  4  |";
                texto.add(cabecalho);
                String linhaA = "A = " + Arrays.toString(this.posicaoTremA);
                texto.add(linhaA);
                String linhaB = "B = " + Arrays.toString(this.posicaoTremB);
                texto.add(linhaB);

                System.out.println(cabecalho);
                System.out.println(linhaA);
                System.out.println(linhaB);
                EscreveArquivoLog escreveArquivoLog = new EscreveArquivoLog();
                escreveArquivoLog.escreverArquivoTxt("TremA.txt", texto);
                
            } else {
                casaTremAnterior = casaTrem;
                novaCasa = casaTrem + novaCasa;
                //Arrays.fill(LinhaTrem.posicaoLinhaA, false);
                LinhaTrem.posicaoLinhaA[casaTrem] = false;
                this.posicaoTremA[casaTrem] = false;
                LinhaTrem.posicaoLinhaB[casaTrem] = true;
                this.posicaoTremB[casaTrem] = true;
                casaTrem = novaCasa;
                setorTrem--;
                ArrayList<String> texto = new ArrayList<String>();
                String cabecalho = "      |  1  |  2  |  3  |  4  |";
                texto.add(cabecalho);
                String linhaA = "A = " + Arrays.toString(this.posicaoTremA);
                texto.add(linhaA);
                String linhaB = "B = " + Arrays.toString(this.posicaoTremB);
                texto.add(linhaB);

                System.out.println(cabecalho);
                System.out.println(linhaA);
                System.out.println(linhaB);
                EscreveArquivoLog escreveArquivoLog = new EscreveArquivoLog();
                escreveArquivoLog.escreverArquivoTxt("TremA.txt", texto);
            }
        
        // Sentido anti-horário
        } else {
            if ((setorTrem % 2) == 0) {
                //Arrays.fill(LinhaTrem.posicaoLinhaA, false);
                LinhaTrem.posicaoLinhaA[casaTremAnterior] = false;
                this.posicaoTremA[casaTremAnterior] = false;
                LinhaTrem.posicaoLinhaB[casaTrem] = true;
                this.posicaoTremB[casaTrem] = true;
                setorTrem++;
                ArrayList<String> texto = new ArrayList<String>();
                String cabecalho = "      |  1  |  2  |  3  |  4  |";
                texto.add(cabecalho);
                String linhaA = "A = " + Arrays.toString(this.posicaoTremA);
                texto.add(linhaA);
                String linhaB = "B = " + Arrays.toString(this.posicaoTremB);
                texto.add(linhaB);

                System.out.println(cabecalho);
                System.out.println(linhaA);
                System.out.println(linhaB);
                EscreveArquivoLog escreveArquivoLog = new EscreveArquivoLog();
                escreveArquivoLog.escreverArquivoTxt("TremB.txt", texto);
            } else {
                casaTremAnterior = casaTrem;
                novaCasa = casaTrem - novaCasa;
                //Arrays.fill(LinhaTrem.posicaoLinhaB, false);
                LinhaTrem.posicaoLinhaB[casaTrem] = false;
                this.posicaoTremB[casaTrem] = false;
                LinhaTrem.posicaoLinhaA[casaTrem] = true;
                this.posicaoTremA[casaTrem] = true;
                casaTrem = novaCasa;
                setorTrem--;
                ArrayList<String> texto = new ArrayList<String>();
                String cabecalho = "      |  1  |  2  |  3  |  4  |";
                texto.add(cabecalho);
                String linhaA = "A = " + Arrays.toString(this.posicaoTremA);
                texto.add(linhaA);
                String linhaB = "B = " + Arrays.toString(this.posicaoTremB);
                texto.add(linhaB);

                System.out.println(cabecalho);
                System.out.println(linhaA);
                System.out.println(linhaB);
                EscreveArquivoLog escreveArquivoLog = new EscreveArquivoLog();
                escreveArquivoLog.escreverArquivoTxt("TremB.txt", texto);
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
