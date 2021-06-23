import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Response> responses =
                new CsvToBeanBuilder(new FileReader("src\\survey_results_public.csv")).withType(Response.class).build().parse();
        for (Response r : responses) {
            if (r.satisfaction.equals("Neither satisfied nor dissatisfied")) System.out.println(r.languageWorkedWith);
        }
    }
}
