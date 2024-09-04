package com.example.foodeapp.data.entity

import java.io.Serializable

data class Foods(
    var yemek_id: Int,
    var yemek_adi: String,
    var yemek_resim_adi: String,
    var yemek_fiyat: Int,
    var is_favoirte: Boolean
):Serializable{

}