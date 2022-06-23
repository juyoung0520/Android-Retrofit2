package com.jjy.retrofit2

import com.jjy.retrofit2.data.NoticeModel

interface MainView {
    fun onLoading()
    fun onGetNoticesSuccess(notices: List<NoticeModel>)
    fun onGetNoticesFailure()
}