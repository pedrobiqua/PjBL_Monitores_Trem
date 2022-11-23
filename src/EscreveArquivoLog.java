import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EscreveArquivoLog {
    
    /**
     * Escreve no arquivo de log
     * @param nomeArquivo
     * @param texto
     */
    public void escreverArquivoTxt(String nomeArquivo, ArrayList<String> texto) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, true);
            PrintWriter gravarArquivo = new PrintWriter(arquivo);
            for (String linha : texto) {
                gravarArquivo.println(linha);
            }
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
