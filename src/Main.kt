import com.opencsv.bean.CsvToBeanBuilder
import java.io.FileNotFoundException
import java.io.FileReader
import java.util.*
import kotlin.collections.ArrayList

object Main {
    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val data: List<Student?> = readFile()
        val students: List<Student?> = verifyData(data)
        getInput(students)
    }

    private fun getInput(students: List<Student?>) {
        println("Nhập số báo danh (bỏ số 0 ở đầu): ")
        val scanner = Scanner(System.`in`)
        val sbd = scanner.nextInt()
        printOutInfo(sbd, students)
    }

    private fun printOutInfo(sbd: Int, students: List<Student?>) {
        println("Tìm số báo danh $sbd trong ${students.size} kết quả.")
        val student = students.firstOrNull { it?.id == sbd }
        if (student == null) {
            println("Không tìm thấy thông tin. Vui lòng thử lại.")
            getInput(students)
        } else {
            student.toString()
            doSortByB00(student, students)
        }
    }

    private fun doSortByB00(student: Student, students: List<Student?>) {
        val sortedStudents = students.sortedByDescending { (it!!.toan ?: 0.0) + (it.hoa ?: 0.0) + (it.sinh ?: 0.0) }
        val thisStudent = sortedStudents.firstOrNull { it!!.id == student.id }
        if (thisStudent != null) println("Thứ hạng: ${sortedStudents.indexOf(thisStudent)}")
    }

    private fun verifyData(students: List<Student?>): List<Student?> {
        return students.filter { it?.id?.div(1000000) == 1 || it?.id?.div(1000000) in (5..30) || it?.id?.div(1000000) == 3 }
    }

    private fun readFile(): List<Student?> {
        return CsvToBeanBuilder<Any?>(FileReader("src\\diem_thi_2022.csv")).withType(
            Student::class.java
        ).build().parse() as List<Student?>
    }

}