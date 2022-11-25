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

        // TODO: Rever as condições de andar e parar trem, prefiro apagar tudo e começar do zero

        // A variavel andandoTrem é controlada por outras variaveis,
        // fiz dessa maneira para não ficar com uma condicional muito grande
        andandoTrem = ((sentidoTrem == 0) ? LinhaTrem.trem1Andando : LinhaTrem.trem2Andando);
        while (andandoTrem == false) {
            wait();
        }

        if (sentidoTrem == 0) {
            if (casaTrem > LinhaTrem.posicaoLinhaA.length - 1) {
                casaTrem = 0;
            }
        } else {
            if (casaTrem < 0) {
                casaTrem = LinhaTrem.posicaoLinhaB.length - 1;
            }
        }

        // Coondicionais usadas para controlar o trem
        if (sentidoTrem == 0) {
            if ( andandoTrem && 
                (LinhaTrem.posicaoTrem1 == LinhaTrem.posicaoTrem2) &&
                ((LinhaTrem.linhaTrem1 == 'A' && LinhaTrem.linhaTrem2 == 'B')) &&
                (usandoTrilho()) &&
                !LinhaTrem.usandoJunção()
            )
            {
                System.out.println("SENTIDO A: ESTOU EM UM PONTO DE JUNÇÃO");
                LinhaTrem.pontoDeJuncao[casaTrem] = sentidoTrem;
                LinhaTrem.linhaTrem1 = 'J';
                andandoTrem = false;
                LinhaTrem.trem1Andando = andandoTrem;
            }
        } else {
            if ( andandoTrem && 
                (LinhaTrem.posicaoTrem2 == LinhaTrem.posicaoTrem1) &&
                ((LinhaTrem.linhaTrem2 == 'B' && LinhaTrem.linhaTrem1 == 'A')) &&
                (usandoTrilho()) &&
                !LinhaTrem.usandoJunção()
            )
            {
                System.out.println("SENTIDO B: ESTOU EM UM PONTO DE JUNÇÃO");
                LinhaTrem.pontoDeJuncao[casaTrem] = sentidoTrem;
                LinhaTrem.linhaTrem2 = 'J';
                andandoTrem = false;
                LinhaTrem.trem2Andando = andandoTrem;
            }
        }

        // Se não passar pelas condicionais o trem não anda
        if (LinhaTrem.pontoDeJuncao[casaTrem] != sentidoTrem) {
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
                    LinhaTrem.posicaoTrem1 = casaTrem;
                    LinhaTrem.linhaTrem1 = 'A';
                    LinhaTrem.posicaoLinhaB[casaTremAnterior] = false;
                    this.posicaoTremB[casaTremAnterior] = false;
                    LinhaTrem.posicaoLinhaA[casaTrem] = true;
                    this.posicaoTremA[casaTrem] = true;
                    setorTrem++;
                    printPosicaoTrem();
                    
                } else {
                    casaTremAnterior = casaTrem;
                    novaCasa = casaTrem + novaCasa;
                    LinhaTrem.posicaoTrem1 = casaTrem;
                    LinhaTrem.linhaTrem1 = 'B';
                    LinhaTrem.posicaoLinhaA[casaTrem] = false;
                    this.posicaoTremA[casaTrem] = false;
                    LinhaTrem.posicaoLinhaB[casaTrem] = true;
                    this.posicaoTremB[casaTrem] = true;
                    casaTrem = novaCasa;
                    setorTrem--;
                    if (LinhaTrem.quemEstaUsandoAJuncao() == 1) {
                        LinhaTrem.linhaTrem2 = 'B';
                        LinhaTrem.pontoDeJuncao[LinhaTrem.posicaoTrem2] = -1;
                        LinhaTrem.trem2Andando = true;
                    }
                    printPosicaoTrem();
                }
            
            // Sentido anti-horário
            } else {
                if ((setorTrem % 2) == 0) {
                    LinhaTrem.posicaoTrem2 = casaTrem;
                    LinhaTrem.posicaoLinhaA[casaTremAnterior] = false;
                    this.posicaoTremA[casaTremAnterior] = false;
                    LinhaTrem.posicaoLinhaB[casaTrem] = true;
                    LinhaTrem.linhaTrem2 = 'B';
                    this.posicaoTremB[casaTrem] = true;
                    setorTrem++;
                    printPosicaoTrem();
                } else {
                    casaTremAnterior = casaTrem;
                    novaCasa = casaTrem - novaCasa;
                    LinhaTrem.posicaoTrem2 = casaTrem;
                    LinhaTrem.linhaTrem2 = 'A';
                    LinhaTrem.posicaoLinhaB[casaTrem] = false;
                    this.posicaoTremB[casaTrem] = false;
                    LinhaTrem.posicaoLinhaA[casaTrem] = true;
                    this.posicaoTremA[casaTrem] = true;
                    casaTrem = novaCasa;
                    setorTrem--;
                    if (LinhaTrem.quemEstaUsandoAJuncao() == 0) {
                        LinhaTrem.linhaTrem1 = 'B';
                        LinhaTrem.pontoDeJuncao[LinhaTrem.posicaoTrem1] = -1;
                        LinhaTrem.trem1Andando = true;
                    }
                    printPosicaoTrem();
                }
            }
        }
        
        notifyAll();
        
    }

    public synchronized void esperarTrem() throws InterruptedException {
        if (sentidoTrem == 0) {
            while (!andandoTrem && 
                (casaTrem == 1 || casaTrem == 2 || casaTrem == 3 || casaTrem == 0) &&
                (usandoTrilho()) &&
                (LinhaTrem.usandoJunção()) &&
                LinhaTrem.linhaTrem1 == 'J'
            ) 
            {
                System.out.println("TREM NO SENTIDO A ESPERANDO B PASSAR");
                wait();
            }    
            andandoTrem = true;

        } else {
            while (!andandoTrem && 
                (casaTrem == 1 || casaTrem == 2 || casaTrem == 3 || casaTrem == 0) &&
                (usandoTrilho()) &&
                (LinhaTrem.usandoJunção()) &&
                LinhaTrem.linhaTrem2 == 'J'
            ) 
            {
                System.out.println("TREM NO SENTIDO B ESPERANDO A PASSAR");
                wait();
            }
            LinhaTrem.pontoDeJuncao[LinhaTrem.posicaoTrem1] = -1;
            andandoTrem = true;
        }
        
        notifyAll();
    }

    public boolean usandoTrilho() {
        for (int i = 0; i < posicaoTremA.length; i++) {
            if (posicaoTremA[i] == true) {
                return true;
            }
        }

        for (int i = 0; i < posicaoTremB.length; i++) {
            if (posicaoTremB[i] == true) {
                return true;
            }
        }

        return false;
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

    public void printPosicaoTrem() {
        ArrayList<String> texto = new ArrayList<String>();
        String cabecalho = "      |  1  |  2  |  3  |  4  |";
        texto.add(cabecalho);
        String linhaA = "A = " + Arrays.toString(this.posicaoTremA);
        texto.add(linhaA);
        String linhaB = "B = " + Arrays.toString(this.posicaoTremB);
        texto.add(linhaB);

        //System.out.println(cabecalho);
        //System.out.println(linhaA);
        //System.out.println(linhaB);
        EscreveArquivoLog escreveArquivoLog = new EscreveArquivoLog();
        if (sentidoTrem == 0) {
            escreveArquivoLog.escreverArquivoTxt("TremA.txt", texto);
        } else {
            escreveArquivoLog.escreverArquivoTxt("TremB.txt", texto);
        }
        
    }
}
