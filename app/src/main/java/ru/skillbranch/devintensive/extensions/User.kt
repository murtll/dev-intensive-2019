package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {
    val status = if (lastVisit == null) "Еще ни разу не был" else if (lastVisit == Date()) "online" else "Последний раз был ${lastVisit!!.humanizeDiff()}"
    val fullName = "$firstName $lastName"

    return UserView(id = id,
        fullName = fullName,
        nickname = Utils.transliteration(fullName),
        initials = Utils.toInitials(firstName, lastName),
        avatar = avatar,
        status = status
        )
}
