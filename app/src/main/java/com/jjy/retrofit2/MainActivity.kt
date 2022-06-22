package com.jjy.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import com.jjy.retrofit2.data.NoticeModel
import com.jjy.retrofit2.databinding.ActivityMainBinding
import com.jjy.retrofit2.databinding.ItemNoticeBinding
import com.jjy.retrofit2.service.NoticeServiceImpl

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoticeRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoticeRVAdapter()
        binding.mainNoticeRv.adapter = adapter


        NoticeServiceImpl.getNotices(this, 1)
    }

    override fun onLoading() {
        binding.mainProgressPb.visibility = View.VISIBLE
    }

    override fun onGetNoticesSuccess(noties: List<NoticeModel>) {
        binding.mainProgressPb.visibility = View.GONE
        adapter.setNotices(noties)
    }

    override fun onGetNoticesFailure() {
        binding.mainProgressPb.visibility = View.GONE
    }

}