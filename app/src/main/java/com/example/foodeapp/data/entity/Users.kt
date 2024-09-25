package com.example.foodeapp.data.entity

import com.google.protobuf.Empty
import java.io.Serializable

data class Users(var user_fullname: String,
                 var user_phone: String,
                 var user_favs: ArrayList<String>,
                 var selected_address_index: String,
                 var selected_card_index: String,
    var user_adress: ArrayList<Address>,
    var user_cards: ArrayList<Cards>,
): Serializable