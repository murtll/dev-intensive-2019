package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        when (fullName) {
            null, "", " " -> return null to null
        }

        val names = fullName!!.split(" ")

        return if (names.size > 1) names.first() to names.last() else names.first() to null
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщьыъэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ]")) {
            when(it.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е", "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и", "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш", "щ" -> "sh"
                "ъ", "ь" -> ""
                "ы" -> "i"
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                "А" -> "A"
                "Б" -> "B"
                "В" -> "V"
                "Г" -> "G"
                "Д" -> "D"
                "Е", "Ё" -> "E"
                "Ж" -> "Zh"
                "З" -> "Z"
                "И", "Й" -> "I"
                "К" -> "K"
                "Л" -> "L"
                "М" -> "M"
                "Н" -> "N"
                "О" -> "O"
                "П" -> "P"
                "Р" -> "R"
                "С" -> "S"
                "Т" -> "T"
                "У" -> "U"
                "Ф" -> "F"
                "Х" -> "H"
                "Ц" -> "C"
                "Ч" -> "Ch"
                "Ш", "Щ" -> "Sh"
                "Ъ", "Ь" -> ""
                "Ы" -> "I"
                "Э" -> "E"
                "Ю" -> "Yu"
                "Я" -> "Ya"
                " " -> divider
                else -> it.value
            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val firstLetter = when(firstName) {
            "", " ", null -> null
            else -> firstName.first().toUpperCase()
        }

        val lastLetter = when(lastName) {
            "", " ", null -> null
            else -> lastName.first().toUpperCase()
        }

        if (firstLetter != null && lastLetter != null) return "$firstLetter$lastLetter"
        if (firstLetter != null && lastLetter == null) return "$firstLetter"
        if (firstLetter == null && lastLetter != null) return "$lastLetter"
        return null
    }

}