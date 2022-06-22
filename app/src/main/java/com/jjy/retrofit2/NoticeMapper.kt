package com.jjy.retrofit2

import com.jjy.retrofit2.data.Content
import com.jjy.retrofit2.data.NoticeModel
import java.text.DateFormat
import java.time.format.DateTimeFormatter

fun mapperToNoticeModel(contents: List<Content>): List<NoticeModel> {
    return contents.map {
        val type = when (it.noticeType) {
            "SERVICE_INFO" -> "서비스 안내"
            "SYSTEM_CHECK" -> "점검 안내"
            "POLICY" -> "약관 안내"
            else -> ""
        }

        val date = it.updatedDate.subSequence(0, 10).replace(Regex("-"), ". ")
        NoticeModel(type, it.title, date)
    }
}