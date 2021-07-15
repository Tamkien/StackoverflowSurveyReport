import com.opencsv.bean.CsvBindByPosition;

public class Response {
    //19 is the index of "JobSat" column. Count from 0, ofc.
    @CsvBindByPosition(position = 1)
    String isCoder;
    @CsvBindByPosition(position = 7)
    String salary;
    @CsvBindByPosition(position = 14)
    String edLevel;
    @CsvBindByPosition(position = 22)
    String languagesWorkedWith;
    @CsvBindByPosition(position = 42)
    String orgSize;
    @CsvBindByPosition(position = 58)
    String workWeekHrs;
    @CsvBindByPosition(position = 60)
    String yearsCodePro;

    @Override
    public String toString() {
        return "Response{" +
                "isCoder='" + isCoder + '\'' +
                ", salary='" + salary + '\'' +
                ", edLevel='" + edLevel + '\'' +
                ", languagesWorkedWith='" + languagesWorkedWith + '\'' +
                ", orgSize='" + orgSize + '\'' +
                ", workWeekHrs='" + workWeekHrs + '\'' +
                ", yearsCodePro='" + yearsCodePro + '\'' +
                '}';
    }
}
