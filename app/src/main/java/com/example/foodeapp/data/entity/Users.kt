package com.example.foodeapp.data.entity

import java.io.Serializable

data class Users(var user_id: Int,
                 var user_fullname: String,
                 var user_email: String,
                 var user_phone: String,
                 var user_password: String,
): Serializable {
}