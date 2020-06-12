package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16) = this.substring(0 until value).trimEnd() + "..."

fun String.stripHtml(): String {
    var text = this

    while (text.count { it == '<' } > 0 && text.count { it == '>' } == text.count { it == '<' }) {
        text = text.substring(0 until text.indexOf('<')) + text.substring(text.indexOf('>') + 1..text.lastIndex)
    }

    val splittedText = text.split(" ").toMutableList()
    splittedText.removeAll { it == " " || it.startsWith('&') || it == "" }
    text = ""
    splittedText.forEach { text += " $it" }

    return text.trim()
}
