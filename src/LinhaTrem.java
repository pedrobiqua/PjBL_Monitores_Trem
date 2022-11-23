public class LinhaTrem {
    /**
     * Ponto de junção
     */
    public static int[] pontoDeJuncao = new int[4];

    /**
     * Posições dos trens no pontos A
     */
    public static boolean[] posicaoLinhaA = new boolean[4];

    /**
     * Posições dos trens no pontos B
     */
    public static boolean[] posicaoLinhaB = new boolean[4];

    /**
     * Posição do trem 1 SENTIDO HORÁRIO
     */
    public static int posicaoTrem1 = 0;

    /**
     * Posição do trem 2 SENTIDO ANTI-HORÁRIO
     */
    public static int posicaoTrem2 = 0;

    /**
     * Linha do trem 1 SENTIDO HORÁRIO
     */
    public static char linhaTrem1 = ' ';

    /**
     * Linha do trem 2 SENTIDO ANTI-HORÁRIO
     */
    public static char linhaTrem2 = ' ';

    public static boolean usandoJunção() {
        for (int i = 0; i < pontoDeJuncao.length; i++) {
            if (pontoDeJuncao[i] == 0 || pontoDeJuncao[i] == 1) {
                return true;
            }
        }
        return false;
    }

    public static int quemEstaUsandoAJuncao() {
        if (usandoJunção()) {
            for (int i = 0; i < pontoDeJuncao.length; i++) {
                if (pontoDeJuncao[i] == 0 || pontoDeJuncao[i] == 1) {
                    return pontoDeJuncao[i];
                }
            }
            return -1;
        } else {
            return -1;
        }
    }
}
