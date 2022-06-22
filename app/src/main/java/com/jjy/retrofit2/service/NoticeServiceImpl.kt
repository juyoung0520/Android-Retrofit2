package com.jjy.retrofit2.service

import android.util.Log
import com.jjy.retrofit2.GlobalApplication.Companion.retrofit
import com.jjy.retrofit2.MainView
import com.jjy.retrofit2.data.NoticeDTO
import com.jjy.retrofit2.mapperToNoticeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NoticeServiceImpl {
    fun getNotices(mainView: MainView, page: Int) {
        val noticeService = retrofit.create(NoticeService::class.java)

        noticeService.getNotices(page).enqueue(object : Callback<NoticeDTO> {
            override fun onResponse(call: Call<NoticeDTO>, response: Response<NoticeDTO>) {
                if (response.isSuccessful) {
                    val content = response.body()!!.data.content
                    mainView.onGetNoticesSuccess(mapperToNoticeModel(content))
                } else {
                    mainView.onGetNoticesFailure()
                }
            }

            override fun onFailure(call: Call<NoticeDTO>, t: Throwable) {
                Log.d("NOTICE/ERROR", t.message.toString())
                mainView.onGetNoticesFailure()
            }

        })
    }
}