package com.example.foodeapp.data.entity

data class ResponseFoods(
    var yemekler: List<Foods>? = null,
    var success: Int? = null
)