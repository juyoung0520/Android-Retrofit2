package com.jjy.retrofit2.data

import com.google.gson.annotations.SerializedName

data class NoticeDTO(
    val data: Date
)

data class Date(
    val content: List<Content>
)

data class Content(
    val contentId: Int,
    val contentSeq: Int,
    val title: String,
    val content: String,
    @SerializedName("created")
    val createdDate: String,
    @SerializedName("updated")
    val updatedDate: String,
    val noticeType: String
)
