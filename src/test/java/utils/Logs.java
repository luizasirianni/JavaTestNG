package utils;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logs {

    String dataHora;
    public void initializeCSV(String dataHora) throws IOException {
        //cria o arquivo de Log no início do teste
        String[] header = {"data e hora", "caso de teste", "mensagem"};
        this.dataHora = dataHora;
        Writer escritor = Files.newBufferedWriter(Paths.get("target/logs/log -" + dataHora + ".csv"));
        CSVWriter escritorCSV = new CSVWriter(escritor);

        escritorCSV.writeNext(header);

        escritorCSV.flush(); //salvar
        escritorCSV.close();
    }


    public void registerCSV(String casoTeste, String mensagem) throws IOException {
        // grava cada linha no log
        String dataHoraLog = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss.SSS]").format(Calendar.getInstance().getTime());
        String[] linha = new String[]{dataHoraLog,casoTeste,mensagem};

        FileWriter escritor = new FileWriter("target/logs/log - " + dataHora +".csv", true);
        CSVWriter escritorCSV = new CSVWriter(escritor);

        escritorCSV.writeNext(linha);

        escritorCSV.flush(); //salvar
        escritor.close();
    }
}
