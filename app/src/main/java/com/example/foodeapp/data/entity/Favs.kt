package com.example.foodeapp.data.entity

data class Favs(
    var fav_id: Int,
    var fav_yemek_adi: String,
    var fav_yemek_resim_adi: String,
    var fav_yemek_fiyat: Int,
    var fav_kullanici_id: Int,
) {
}