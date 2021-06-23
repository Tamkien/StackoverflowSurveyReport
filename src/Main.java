import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //read from csv file
        List<Response> responses =
                new CsvToBeanBuilder(new FileReader("src\\survey_results_public.csv")).withType(Response.class).build().parse();

        for (Response r : responses) {
            //replace this 'if' with your codes.
            if (r.satisfaction.equals("Neither satisfied nor dissatisfied")) System.out.println(r.languageWorkedWith);
        }
    }
}
