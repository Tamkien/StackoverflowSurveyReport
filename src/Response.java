import com.opencsv.bean.CsvBindByPosition;

public class Response {
    @CsvBindByPosition(position = 19)
    String satisfaction;
    @CsvBindByPosition(position = 22)
    String languageWorkedWith;

}
