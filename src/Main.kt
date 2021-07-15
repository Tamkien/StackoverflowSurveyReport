import com.opencsv.bean.CsvToBeanBuilder
import java.io.FileNotFoundException
import java.io.FileReader

object Main {
    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val responses: List<Response?> = readFile()
        val verifiedResponds: MutableList<Response> = verifyResponds(responses)
        println(verifiedResponds.size)
        calculateByYearAndSalary(verifiedResponds)
        calculateByLanguageAndSalary(verifiedResponds)
        calculateByWorkHrsAndSalary(verifiedResponds)
        calculateByOrgSizeAndSalary(verifiedResponds)
        calculateByEduLevelAndSalary(verifiedResponds)
    }

    private fun calculateByEduLevelAndSalary(responds: MutableList<Response>) {
        println("\nBang cap co quyet dinh muc luong hay khong")
        val edLevels: MutableList<String> = ArrayList()
        edLevels.add("I never completed any formal education")
        edLevels.add("Primary/elementary school")
        edLevels.add("Secondary school (e.g. American high school, German Realschule or Gymnasium, etc.)")
        edLevels.add("Some college/university study without earning a degree")
        edLevels.add("Associate degree (A.A., A.S., etc.)")
        edLevels.add("Bachelor’s degree (B.A., B.S., B.Eng., etc.)")
        edLevels.add("Master’s degree (M.A., M.S., M.Eng., MBA, etc.)")
        edLevels.add("Professional degree (JD, MD, etc.)")
        edLevels.add("Other doctoral degree (Ph.D., Ed.D., etc.)")
        val results: MutableList<Triple<Int, String, Double>> = ArrayList()
        for (el in edLevels) {
            var count = 0.0
            var sum = 0.0
            for (r in responds) {
                if (r.edLevel.equals(el)) {
                    count++
                    sum += r.salary.toDouble()
                }
            }
            results.add(Triple(edLevels.indexOf(el), el, sum / count))
        }
        results.sortBy { pair -> pair.third }
        for (r in results) println("${r.first}\t${r.second}\t${r.third}")
    }

    private fun calculateByOrgSizeAndSalary(responds: MutableList<Response>) {
        println("\nDo lon cua cong ty co ty le thuan voi luong")
        val orgSizes: MutableList<String> = ArrayList()
        orgSizes.add("Just me - I am a freelancer, sole proprietor, etc.")
        orgSizes.add("2 to 9 employees")
        orgSizes.add("10 to 19 employees")
        orgSizes.add("20 to 99 employees")
        orgSizes.add("100 to 499 employees")
        orgSizes.add("500 to 999 employees")
        orgSizes.add("1,000 to 4,999 employees")
        orgSizes.add("5,000 to 9,999 employees")
        orgSizes.add("10,000 or more employees")
        val results: MutableList<Pair<String, Double>> = ArrayList()
        for (os in orgSizes) {
            var count = 0.0
            var sum = 0.0
            for (r in responds) {
                if (r.orgSize.equals(os)) {
                    count++
                    sum += r.salary.toDouble()
                }
            }
            results.add(Pair(os, sum / count))
        }
        results.sortBy { pair -> pair.second }
        for (r in results) println("${r.first}\t${r.second}")
    }

    private fun verifyResponds(responses: List<Response?>): MutableList<Response> {
        val verifiedResponds: MutableList<Response> = ArrayList()
        for (r in responses) {
            try {
                if (!r?.salary.equals("NA")
                    && r?.isCoder.equals("I am a developer by profession")
                    && !r?.workWeekHrs.equals("NA")
                    && !r?.languagesWorkedWith.equals("NA")
                    && !r?.yearsCodePro.equals("NA")
                    && !r?.edLevel.equals("NA")
                    && !r?.orgSize.equals("NA")
                ) {
                    if (r?.yearsCodePro.equals("Less than 1 year")) r?.yearsCodePro = "0"
                    if (r?.yearsCodePro.equals("More than 50 years")) r?.yearsCodePro = "51"
                    if (r?.salary?.toDouble()!! > 0.0
                        && r.workWeekHrs.toDouble() > 0.0
                        && r.workWeekHrs.toDouble() <= 168.0

                    ) {
                        verifiedResponds.add(r)
                    }
                }
            } catch (e: NumberFormatException) {
                continue
            }
        }
        return verifiedResponds
    }

    private fun calculateByWorkHrsAndSalary(responses: List<Response?>) {
        println("\nCuong do lam viec co lien quan den muc luong hay khong")
        val responsesOfDev: MutableList<Pair<Double, Double>> = ArrayList()
        for (r in responses) {
            try {
                responsesOfDev.add(Pair(r?.workWeekHrs?.toDouble(), r?.salary?.toDouble()) as Pair<Double, Double>)
            } catch (e: NumberFormatException) {
                continue
            }
        }
        val results: MutableList<Pair<Double, Double>> = ArrayList()
        var hrs = 0.0
        while (hrs <= 475.0) {
            var count = 0.0
            var sum = 0.0
            for (r in responsesOfDev) {
                if (r.first == hrs) {
                    count++
                    sum += r.second
                }
            }
            if (count != 0.0) results.add(Pair(hrs, sum / count))
            hrs += 0.5
        }
        results.sortByDescending { pair: Pair<Double, Double> -> pair.second }
        for (r in results) println("${r.first}\t${r.second}")
    }

    private fun readFile(): List<Response?> {
        return CsvToBeanBuilder<Any?>(FileReader("src\\survey_results_public.csv")).withType(
            Response::class.java
        ).build().parse() as List<Response?>
    }

    private fun calculateByLanguageAndSalary(responses: List<Response?>) {
        println("\nDo pho bien cua ngon ngu co quyet dinh muc luong dc chi tra?")
        val responsesOfDev: MutableList<Pair<Double, String>> = ArrayList()
        for (r in responses) {
            try {
                responsesOfDev.add(Pair(r?.salary?.toDouble(), r?.languagesWorkedWith) as Pair<Double, String>)
            } catch (e: NumberFormatException) {
                continue
            }
        }
        val languages: MutableList<String> = ArrayList()
        languages.add("Assembly");
        languages.add("Bash/Shell/PowerShell");
        languages.add("C")
        languages.add("C#")
        languages.add("C++")
        languages.add("Dart")
        languages.add("Go")
        languages.add("Haskell")
        languages.add("HTML/CSS")
        languages.add("Java")
        languages.add("JavaScript")
        languages.add("Julia")
        languages.add("Kotlin")
        languages.add("Objective-C")
        languages.add("Perl")
        languages.add("PHP")
        languages.add("Python")
        languages.add("R")
        languages.add("Ruby")
        languages.add("Rust")
        languages.add("Scala")
        languages.add("SQL")
        languages.add("Swift")
        languages.add("TypeScript")
        languages.add("VBA")
        val results: MutableList<Triple<String, Int, Double>> = ArrayList()
        for (l in languages) {
            var count = 0
            var sum = 0.0
            for (r in responsesOfDev) {
                if (r.second.contains(l)) {
                    count++
                    sum += r.first
                }
            }
            results.add(Triple(l, count, sum / count))
        }
        results.sortBy { triple -> triple.third }
        for (r in results) println("${r.first}\t${r.second}\t${r.third}")
    }

    private fun calculateByYearAndSalary(responses: List<Response?>) {
        println("\nCo phai muc luong dua theo nam kinh nghiem?")
        val responsesOfDev: MutableList<Pair<Double, Int>> = ArrayList()
        for (r in responses) {
            try {
                responsesOfDev.add(Pair(r?.salary?.toDouble(), r?.yearsCodePro?.toInt()) as Pair<Double, Int>)
            } catch (e: NumberFormatException) {
                continue
            }
        }
        val results: MutableList<Pair<Int, Double>> = ArrayList()
        var i = 0
        while (i <= 51) {
            var count = 0.0
            var sum = 0.0
            for (r in responsesOfDev) {
                if (r.second == i) {
                    count++
                    sum += r.first
                }
            }
            results.add(Pair(i, sum / count))
            i++
        }
        results.sortBy { pair -> pair.second }
        for (r in results) println("${r.first}\t${r.second}")
    }
}