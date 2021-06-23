import com.opencsv.bean.CsvBindByPosition;

public class Response {
    //19 is the index of "JobSat" column. Count from 0, ofc.
    @CsvBindByPosition(position = 19)
    String satisfaction;
    @CsvBindByPosition(position = 22)
    String languageWorkedWith;

}
