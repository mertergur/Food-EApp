package com.example.foodeapp.retrofit

import com.example.foodeapp.data.entity.ResponseBasket
import com.example.foodeapp.data.entity.ResponseDEGEA
import com.example.foodeapp.data.entity.ResponseFoods
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {

    // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    // http://kasimadalan.pe.hu/ -> base url
    // yemekler/tumYemekleriGetir.php -> webservice url

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFoods() : ResponseFoods

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addFoodToBasket(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): ResponseDEGEA

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getBasket(
        @Field("kullanici_adi") kullanici_adi: String
    ): ResponseBasket

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun removeFoodFromBasket(
        @Field ("sepet_yemek_id") sepet_yemek_id: Int,
        @Field ("kullanici_adi") kullanici_adi: String
    ): ResponseDEGEA
}