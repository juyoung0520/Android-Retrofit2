package com.jjy.retrofit2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjy.retrofit2.data.NoticeModel
import com.jjy.retrofit2.databinding.ItemNoticeBinding

class NoticeRVAdapter() :
    RecyclerView.Adapter<NoticeRVAdapter.NoticeViewHolder>() {
    private val notices = arrayListOf<NoticeModel>()

    fun setNotices(notices: List<NoticeModel>) {
        this.notices.clear()
        this.notices.addAll(notices)

        notifyAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyAdapter() {
        notifyDataSetChanged()
    }

    inner class NoticeViewHolder(val binding: ItemNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val binding = holder.binding

        binding.itemCategoryTv.text = notices[position].type
        binding.itemTitleTv.text = notices[position].title
        binding.itemDateTv.text = notices[position].date
    }

    override fun getItemCount(): Int {
        return notices.size
    }
}