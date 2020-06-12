package ru.skillbranch.devintensive.models

class UserView (val id: String,
                val fullName: String,
                val nickname: String,
                val initials: String?,
                var avatar: String? = null,
                var status: String = "offline") {
}