import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //read from csv file
        List<Response> responses =
                new CsvToBeanBuilder(new FileReader("src\\survey_results_public.csv")).withType(Response.class).build().parse();
        List<Response> responsesOfDev = new ArrayList<>();
        List<String> jobSats = new ArrayList<>();
        jobSats.add("Very dissatisfied");
        jobSats.add("Slightly dissatisfied");
        jobSats.add("Neither satisfied nor dissatisfied");
        jobSats.add("Slightly satisfied");
        jobSats.add("Very satisfied");
        List<String> languages = new ArrayList<>();
        languages.add("Assembly");
        languages.add("Bash/Shell/PowerShell");
        languages.add("C");
        languages.add("C#");
        languages.add("C++");
        languages.add("Dart");
        languages.add("Go");
        languages.add("Haskell");
        languages.add("HTML/CSS");
        languages.add("Java");
        languages.add("JavaScript");
        languages.add("Julia");
        languages.add("Kotlin");
        languages.add("Objective-C");
        languages.add("Perl");
        languages.add("PHP");
        languages.add("Python");
        languages.add("R");
        languages.add("Ruby");
        languages.add("Rust");
        languages.add("Scala");
        languages.add("SQL");
        languages.add("Swift");
        languages.add("TypeScript");
        languages.add("VBA");
        for (Response r : responses) {
            //replace this 'if' with your codes.
            if (!r.satisfaction.equals("N/A") && !r.languageWorkedWith.equals("N/A")) {
                responsesOfDev.add(r);
            }
        }
        for (String js : jobSats) {
            int quantity = countByJobSat(responsesOfDev, js);
            System.out.println("\nIn total of " + quantity + " developers who are " + js + " with their jobs, there " +
                    "are:");
            for (String l : languages) {
                int countResponseByLanguage = countByLanguage(responsesOfDev, js, l);
                DecimalFormat df = new DecimalFormat("#.##");
                String p = df.format(countResponseByLanguage * 1.0 / quantity * 100);
                System.out.println(countResponseByLanguage + " developers worked with " + l + ", account for " + p + " percents.");
            }
        }
    }

    private static int countByLanguage(List<Response> responsesOfDev, String js, String l) {
        int count = 0;
        for (Response r : responsesOfDev) {
            if (r.satisfaction.equals(js) && r.languageWorkedWith.contains(l)) count++;
        }
        return count;
    }

    private static int countByJobSat(List<Response> responsesOfDev, String js) {
        int count = 0;
        for (Response r : responsesOfDev) {
            if (r.satisfaction.equals(js)) count++;
        }
        return count;
    }
}
