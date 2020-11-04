package app.alhyatt.center.data.reponse

import app.alhyatt.center.data.pojo.User


data class ResponseUser(
        val status: Boolean,
        val user: User?,
        val message:String?
)