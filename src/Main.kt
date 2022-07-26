import com.opencsv.bean.CsvToBeanBuilder
import java.io.FileNotFoundException
import java.io.FileReader
import java.util.*

object Main {
    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val data: List<Student?> = readFile()
//        getBestDisqualified(data)
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
            doSort(student, students, "B00")
            tinhPhoDiem(students)
        }
    }

    private fun tinhPhoDiem(students: List<Student?>) {
        for (i in 0..599) {
            val thisPoints = mutableListOf<Student>()
            for (s in students) {
                if (s hasBetterB00MarkThan i
//                    && s!!.hasBestMarkInB00()
                ) thisPoints.add(s!!)
            }
            println("> ${i * 1.0 / 20} ${thisPoints.size} ")
        }
    }

    private infix fun Student?.hasBetterB00MarkThan(i: Int): Boolean {
        return this!!.getB00Mark() > i / 20.0
    }

    private fun Student.hasBestMarkInB00(): Boolean {
        return this.getB00Mark() > this.getA01Mark()
                && this.getB00Mark() > this.getA00Mark()
                && this.getB00Mark() > this.getA02Mark()
    }

    private fun doSort(student: Student, students: List<Student?>, s: String) {
        val sortedStudents = students.sortedByDescending {
            when (s) {
                "A00" -> it!!.getA00Mark()
                "A01" -> it!!.getA01Mark()
                "D01" -> it!!.getD01Mark()
                "A02" -> it!!.getA02Mark()
                else -> it!!.getB00Mark()
            }
        }
        val thisStudent = sortedStudents.firstOrNull { it!!.id == student.id }
        if (thisStudent != null) println("Thứ hạng: ${sortedStudents.indexOf(thisStudent)}")
    }

    private fun verifyData(students: List<Student?>): List<Student?> {
        return students.filter {
            (it?.id?.div(1000000) == 1
                    || it?.id?.div(1000000) in (5..28)
                    || it?.id?.div(1000000) == 3)
                    && it!!.hasBestMarkInB00()
                    && it!!.isQualified()
        }
    }

    private fun readFile(): List<Student?> {
        return CsvToBeanBuilder<Any?>(FileReader("src\\diem_thi_2022.csv")).withType(
            Student::class.java
        ).build().parse() as List<Student?>
    }

}

private fun getBestDisqualified(data: List<Student?>) {
    var bestDisqualified = Student()
    data.forEach {
        if (it!!.getB00Mark() >= bestDisqualified.getB00Mark() && !it.isQualified())
            bestDisqualified = it
    }
    println(bestDisqualified.toString())
}

private fun Student.isQualified(): Boolean {
    if (this.toan != 0.01 && this.toan <= 1.0) return false
    if (this.van != 0.01 && this.van <= 1.0) return false
    if (this.anh != 0.01 && this.anh <= 1.0) return false
    if (this.ly != 0.01 && this.ly <= 1.0) return false
    if (this.hoa != 0.01 && this.hoa <= 1.0) return false
    if (this.sinh != 0.01 && this.sinh <= 1.0) return false
    if (this.su != 0.01 && this.su <= 1.0) return false
    if (this.dia != 0.01 && this.dia <= 1.0) return false
    if (this.gdcd != 0.01 && this.gdcd <= 1.0) return false
    return true
}

private fun Student.getB00Mark(): Double {
    return this.toan + this.hoa + this.sinh
}

private fun Student.getA01Mark(): Double {
    return this.toan + this.hoa + this.ly
}

private fun Student.getA00Mark(): Double {
    return this.toan + this.ly + this.anh
}

private fun Student.getA02Mark(): Double {
    return this.toan + this.hoa + this.anh
}

private fun Student.getD01Mark(): Double {
    return this.toan + this.van + this.anh
}
