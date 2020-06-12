package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(val id: String,
                           val from: User?,
                           val chat: Chat,
                           val isIncoming: Boolean = false,
                           val date: Date = Date()) {

    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var id = -1

        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), payload: Any?, type: String = "text"): BaseMessage {
            id++
            return when (type) {
                "text" -> TextMessage("$id", from, chat, date = date, text = payload as String)
                else -> ImageMessage("$id", from, chat, date = date, image = payload as String)
            }
        }
    }
}