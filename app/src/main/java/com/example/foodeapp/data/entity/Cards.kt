package com.example.foodeapp.data.entity

import java.io.Serializable

data class Cards (
    var card_title: String,
    var card_number: String,
    var card_exp_date: String,
    var card_cvv: String): Serializable