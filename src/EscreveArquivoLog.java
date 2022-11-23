import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class EscreveArquivoLog {
    
    public static boolean fileAExists = false;
    public static boolean fileBExists = false;

    /**
     * Escreve no arquivo de log
     * @param nomeArquivo
     * @param texto
     */
    public void escreverArquivoTxt(String nomeArquivo, ArrayList<String> texto) {
        try {
            File file = atualizaArquivo(nomeArquivo);
            if (file != null) {
                FileWriter arquivo = new FileWriter(file, true);
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                for (String linha : texto) {
                    gravarArquivo.println(linha);
                }
                arquivo.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    /**
     * Atualiza os arquivos de log
     */
    public File atualizaArquivo(String nomeArquivo) {
        try {
            Path path = FileSystems.getDefault().getPath("");
            String directoryName = path.toAbsolutePath().toString();
            File file = new File(directoryName + "\\" + nomeArquivo);

            if (!file.exists()) {
                if (nomeArquivo.equals("TremA.txt")) {
                    fileAExists = true;
                } else if (nomeArquivo.equals("TremB.txt")) {
                    fileBExists = true;
                } else {
                    System.out.println("Arquivo não encontrado, esse arquivo não bate com TremA.txt e TremB.txt, nome arquivo : " + nomeArquivo);
                    return null;
                }
                file.createNewFile();
            } else {
                if (nomeArquivo.equals("TremA.txt")) {
                    if (!fileAExists) {
                        file.delete();
                        file.createNewFile();
                        fileAExists = true;
                    }
                } else if (nomeArquivo.equals("TremB.txt")) {
                    if (!fileBExists) {
                        file.delete();
                        file.createNewFile();
                        fileBExists = true;
                    }
                } else {
                    System.out.println("Arquivo não encontrado, esse arquivo não bate com TremA.txt e TremB.txt, nome arquivo : " + nomeArquivo);
                    return null;
                }
            }
            return file;
        } catch (Exception e) {
            System.out.println("Erro ao atualizaro arquivo " + nomeArquivo + " : " + e.getMessage());
            return null;
        }
    }
}
