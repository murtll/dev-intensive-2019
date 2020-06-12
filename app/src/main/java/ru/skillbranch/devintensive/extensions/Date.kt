package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String = SimpleDateFormat(pattern, Locale("ru")).format(this)

fun Date.add(value: Int, units: TimeUnits): Date = this.also { time += value * units.factor }

enum class TimeUnits(val factor: Long, private val unitName: List<String>) {

    SECOND(1000, listOf("секунду", "секунды", "секунд")),
    MINUTE(SECOND.factor * 60, listOf("минуту", "минуты", "минут")),
    HOUR(MINUTE.factor * 60, listOf("час", "часа", "часов")),
    DAY(HOUR.factor * 24, listOf("день", "дня", "дней"));

    fun plural(value: Long): String {
        val end = when(value % 10) {
            1L -> {
                if (value % 100 / 10 == 1L) unitName[2] else unitName.first()
            }
            in 2L..4L -> {
                if (value % 100 / 10 == 1L) unitName[2] else unitName[1]
            }
            else -> unitName[2]
        }
        return "$value $end"
    }
}


fun Date.humanizeDiff(date: Date = Date()): String {

    val diff = date.time - this.time

    val forward = if (diff < 0) "через " else ""
    val back = if (diff > 0) " назад" else ""

    return when (abs(diff)) {
        in 0..1 * TimeUnits.SECOND.factor -> "только что"
        in 1 * TimeUnits.SECOND.factor..45 * TimeUnits.SECOND.factor -> forward + "несколько секунд" + back
        in 45 * TimeUnits.SECOND.factor..75 * TimeUnits.SECOND.factor -> forward + "минуту" + back
        in 75 * TimeUnits.SECOND.factor..45 * TimeUnits.MINUTE.factor -> forward + TimeUnits.MINUTE.plural(abs(diff) / TimeUnits.MINUTE.factor) + back
        in 45 * TimeUnits.MINUTE.factor..75 * TimeUnits.MINUTE.factor -> forward + "час" + back
        in 75 * TimeUnits.MINUTE.factor..22 * TimeUnits.HOUR.factor -> forward + TimeUnits.HOUR.plural(abs(diff) / TimeUnits.HOUR.factor) + back
        in 22 * TimeUnits.HOUR.factor..26 * TimeUnits.HOUR.factor -> forward + "день" + back
        in 26 * TimeUnits.HOUR.factor..360 * TimeUnits.DAY.factor -> forward + TimeUnits.DAY.plural(abs(diff) / TimeUnits.DAY.factor) + back
        else -> {
            if (diff > 0) "более года назад" else "более чем через год"
        }
    }
}
