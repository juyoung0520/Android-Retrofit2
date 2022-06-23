package com.jjy.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.jjy.retrofit2.data.NoticeModel
import com.jjy.retrofit2.databinding.ActivityMainBinding
import com.jjy.retrofit2.service.NoticeServiceImpl

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoticeRVAdapter
    private var noticeType = ""
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        initTabLayout()

        initBtnClick()

        callNoticeAPI()
    }

    private fun callNoticeAPI() {
        if (noticeType == "") {
            NoticeServiceImpl.getNotices(this, page)
        } else {
            NoticeServiceImpl.getDetailNotices(this, noticeType, page)
        }
    }

    private fun initBtnClick() {
        binding.mainNextBtn.setOnClickListener {
            if (page == 1) {
                binding.mainPreviousBtn.visibility = View.VISIBLE
            }
            page++
            callNoticeAPI()
        }

        binding.mainPreviousBtn.setOnClickListener {
            if (page == 2) {
                binding.mainPreviousBtn.visibility = View.GONE
            }
            page--
            callNoticeAPI()
        }
    }

    private fun initTabLayout() {
        binding.mainCategoryTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> noticeType = ""
                    1 -> noticeType = "SERVICE_INFO"
                    2 -> noticeType = "SYSTEM_CHECK"
                    3 -> noticeType = "POLICY"
                }

                page = 1
                callNoticeAPI()
                binding.mainPreviousBtn.visibility = View.GONE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    private fun initRecyclerView() {
        adapter = NoticeRVAdapter()
        binding.mainNoticeRv.adapter = adapter
    }

    override fun onLoading() {
        binding.mainProgressPb.visibility = View.VISIBLE
    }

    override fun onGetNoticesSuccess(noties: List<NoticeModel>) {
        binding.mainProgressPb.visibility = View.GONE
        adapter.setNotices(noties)

        if (noties.isNotEmpty()) {
            binding.mainNextBtn.visibility = View.VISIBLE
            binding.mainEmptyNoticeTv.visibility = View.GONE
        } else {
            binding.mainNextBtn.visibility = View.GONE
            binding.mainEmptyNoticeTv.visibility = View.VISIBLE
        }
    }

    override fun onGetNoticesFailure() {
        binding.mainProgressPb.visibility = View.GONE
    }

}