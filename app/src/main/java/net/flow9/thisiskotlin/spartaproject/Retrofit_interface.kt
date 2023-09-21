package net.flow9.thisiskotlin.spartaproject


import net.flow9.thisiskotlin.spartaproject.Model.ImgModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Call

interface Retrofit_interface {

    @GET("v2/search/image") //V2가 아니라 소문자 v2
    fun image_search(
        @Header("Authorization") apiKey: String?,
        @Query("query") query: String,
        @Query("sort") sort:String?,
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Call<ImgModel?>?

}