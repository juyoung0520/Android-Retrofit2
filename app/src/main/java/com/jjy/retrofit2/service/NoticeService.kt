package com.jjy.retrofit2.service

import com.jjy.retrofit2.data.NoticeDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {
    @GET("contents?typeCode=notice&size=10")
    fun getNotices(@Query("page") page: Int): Call<NoticeDTO>

    @GET("contents?typeCode=notice&size=10")
    fun getDetailNotices(
        @Query("noticeType") type: String,
        @Query("page") page: Int
    ): Call<NoticeDTO>
}