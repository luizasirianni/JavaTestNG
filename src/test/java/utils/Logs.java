package utils;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Logs {

    public void registerCSV(String dataHoraLog, String casoTeste, String mensagem) throws IOException {
        String[] header = {"data e hora", "caso de teste", "mensagem"};
        List<String[]> linhas = new ArrayList<>(); //lista de linhas
        linhas.add(new String[]{"2021-05-02 20-36-12", "consultar curso Python", "iniciou o teste"});

        Writer escritor = Files.newBufferedWriter(Paths.get("target/logs/log1.csv"));
        CSVWriter escritorCSV = new CSVWriter(escritor);

        escritorCSV.writeNext(header);
        escritorCSV.writeAll(linhas);

        escritorCSV.flush(); //salvar
        escritorCSV.close();
    }
}
