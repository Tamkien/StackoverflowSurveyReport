import com.opencsv.bean.CsvBindByPosition;

public class Student {
    @CsvBindByPosition(position = 0)
    int id = 0;
    @CsvBindByPosition(position = 1)
    Double toan = 0.01;
    @CsvBindByPosition(position = 2)
    Double van = 0.01;
    @CsvBindByPosition(position = 3)
    Double anh = 0.01;
    @CsvBindByPosition(position = 4)
    Double ly = 0.01;
    @CsvBindByPosition(position = 5)
    Double hoa = 0.01;
    @CsvBindByPosition(position = 6)
    Double sinh = 0.01;
    @CsvBindByPosition(position = 7)
    Double su = 0.01;
    @CsvBindByPosition(position = 8)
    Double dia = 0.01;
    @CsvBindByPosition(position = 9)
    Double gdcd = 0.01;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", toan=" + toan +
                ", van=" + van +
                ", anh=" + anh +
                ", ly=" + ly +
                ", hoa=" + hoa +
                ", sinh=" + sinh +
                ", su=" + su +
                ", dia=" + dia +
                ", gdcd=" + gdcd +
                '}';
    }

}
